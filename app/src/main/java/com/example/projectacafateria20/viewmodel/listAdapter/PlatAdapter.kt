package com.example.projectacafateria20.viewmodel.listAdapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.projectacafateria20.R
import com.example.projectacafateria20.model.Plat

class PlatAdapter : ListAdapter<Plat, PlatAdapter.PlatHolder>(DiffCallback()) {

    class PlatHolder(view: View) : RecyclerView.ViewHolder(view)

    private lateinit var listener: RecyclerClickListener

    fun setItemListener(listener: RecyclerClickListener) {
        this.listener = listener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PlatHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.card_layout, parent, false)
        val PlatHolder = PlatHolder(v)

        val plat = PlatHolder.itemView.findViewById<CardView>(R.id.card_view)

        plat.setOnClickListener {
            listener.onItemClick(PlatHolder.adapterPosition)
        }

        return PlatHolder
    }

    override fun onBindViewHolder(holder: PlatHolder, position: Int) {
        val currentItem = getItem(position)

        val nom = holder.itemView.findViewById<TextView>(R.id.textViewNomPrimerPlat)
        nom.text = currentItem.nom

        val preu = holder.itemView.findViewById<TextView>(R.id.textViewPreuPrimerPlat)
        preu.text = currentItem.preu.toString()
    }

    class DiffCallback : DiffUtil.ItemCallback<Plat>() {
        override fun areItemsTheSame(oldItem: Plat, newItem: Plat) =
            oldItem.nom == newItem.nom

        override fun areContentsTheSame(oldItem: Plat, newItem: Plat) =
            oldItem == newItem
    }

}