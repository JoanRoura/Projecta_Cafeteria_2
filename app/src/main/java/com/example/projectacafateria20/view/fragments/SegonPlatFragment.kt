package com.example.projectacafateria20.view.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.navigation.ui.NavigationUI
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.projectacafateria20.R
import com.example.projectacafateria20.databinding.FragmentSegonPlatBinding
import com.example.projectacafateria20.viewmodel.CafeteriaSharedViewModel.Companion.listPlats
import com.example.projectacafateria20.viewmodel.CafeteriaSharedViewModel.Companion.listPreus
import com.example.projectacafateria20.viewmodel.CafeteriaViewmodel
import com.example.projectacafateria20.viewmodel.listAdapter.PlatAdapter
import com.example.projectacafateria20.viewmodel.listAdapter.RecyclerClickListener

class SegonPlatFragment : Fragment() {
    lateinit var binding: FragmentSegonPlatBinding
    lateinit var cafeteriaViewmodel: CafeteriaViewmodel
    private lateinit var adapter: PlatAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = DataBindingUtil.inflate<FragmentSegonPlatBinding>(
            inflater,
            R.layout.fragment_segon_plat,
            container,
            false
        );

        cafeteriaViewmodel = ViewModelProvider(this).get(CafeteriaViewmodel::class.java)

        setRecyclerView()
        observeClient()

        binding.buttonGoToTercerPlat.setOnClickListener {
            view?.findNavController()
                ?.navigate(R.id.action_segonPlatFragment_to_tercerPlatFragment);
        }

        return binding.root
    }

    private fun setRecyclerView() {

        val platsRecyclerview = binding.recyclerViewSegonsPlats
        platsRecyclerview.layoutManager = LinearLayoutManager(requireContext())
        platsRecyclerview.setHasFixedSize(true)

        adapter = PlatAdapter()

        adapter.setItemListener(object : RecyclerClickListener {
            override fun onItemClick(position: Int) {
                val segonsPlatsList = adapter.currentList.toMutableList()
                Toast.makeText(
                    requireContext(),
                    "Nom: ${segonsPlatsList[position].nom}, Preu: ${segonsPlatsList[position].preu}€",
                    Toast.LENGTH_SHORT
                ).show()
                listPlats.add(segonsPlatsList[position].nom)
                listPreus.add(segonsPlatsList[position].preu)
            }
        })
        platsRecyclerview.adapter = adapter
    }

    private fun observeClient() {
        cafeteriaViewmodel.obtenirPlats(requireContext(), 2)!!
            .observe(viewLifecycleOwner, Observer { llistaSegonsPlats ->
                adapter.submitList(llistaSegonsPlats)
            })
    }

    //TODO: Menu superior
//    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
//        super.onCreateOptionsMenu(menu, inflater)
//        inflater.inflate(R.menu.options_menu, menu)
//    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return NavigationUI.onNavDestinationSelected(item, requireView().findNavController())
                || super.onOptionsItemSelected(item)
    }
}