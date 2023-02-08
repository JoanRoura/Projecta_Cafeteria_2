package com.example.projectacafateria20.view.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.projectacafateria20.R
import com.example.projectacafateria20.databinding.FragmentPagarComandaBinding
import com.example.projectacafateria20.viewmodel.CafeteriaSharedViewModel
import com.example.projectacafateria20.viewmodel.CafeteriaViewmodel
import com.example.projectacafateria20.viewmodel.listAdapter.PlatAdapter
import com.example.projectacafateria20.viewmodel.listAdapter.RecyclerClickListener
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import com.example.projectacafateria20.viewmodel.CafeteriaSharedViewModel.Companion.listPlats
import com.example.projectacafateria20.viewmodel.CafeteriaSharedViewModel.Companion.sumarPreus

class PagarComandaFragment : Fragment() {
    lateinit var binding: FragmentPagarComandaBinding
    lateinit var cafeteriaViewmodel: CafeteriaViewmodel
    lateinit var sharedViewModel: CafeteriaSharedViewModel
    private lateinit var adapter: PlatAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = DataBindingUtil.inflate<FragmentPagarComandaBinding>(
            inflater,
            R.layout.fragment_pagar_comanda,
            container,
            false
        );

        cafeteriaViewmodel = ViewModelProvider(this).get(CafeteriaViewmodel::class.java)
        sharedViewModel = ViewModelProvider(this).get(CafeteriaSharedViewModel::class.java)

        val arrayAdapter: ArrayAdapter<*>

        val listViewPlatsPagarComanda = binding.listViewPlatsPagarComanda

        arrayAdapter = ArrayAdapter(requireContext(), android.R.layout.simple_list_item_1, listPlats)
        listViewPlatsPagarComanda.adapter = arrayAdapter

        printProducts()

        val textView: TextView = binding.textViewPreuTotalComanda
        textView.findViewById<TextView>(R.id.textViewPreuTotalComanda)
        textView.setText(sumarPreus().toString())

        binding.buttonPagarComanda.setOnClickListener {
            view?.findNavController()
                ?.navigate(R.id.action_pagarComandaFragment_to_iniciFragment);
        }

        return binding.root
    }

    fun printProducts() {
        for (item in listPlats) {
            println(item)
        }

    }
}