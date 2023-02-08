package com.example.projectacafateria20.view.fragments

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.projectacafateria20.R
import com.example.projectacafateria20.databinding.FragmentPrimerPlatBinding
import com.example.projectacafateria20.viewmodel.CafeteriaViewmodel
import com.example.projectacafateria20.viewmodel.listAdapter.PlatAdapter
import com.example.projectacafateria20.viewmodel.listAdapter.RecyclerClickListener
import androidx.lifecycle.Observer
import androidx.navigation.findNavController
import androidx.navigation.ui.NavigationUI
import com.example.projectacafateria20.viewmodel.CafeteriaSharedViewModel.Companion.listPlats
import com.example.projectacafateria20.viewmodel.CafeteriaSharedViewModel.Companion.listPreus

class PrimerPlatFragment : Fragment() {
    lateinit var binding: FragmentPrimerPlatBinding
    lateinit var cafeteriaViewmodel: CafeteriaViewmodel
    private lateinit var adapter: PlatAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = DataBindingUtil.inflate<FragmentPrimerPlatBinding>(
            inflater,
            R.layout.fragment_primer_plat,
            container,
            false
        );

        cafeteriaViewmodel = ViewModelProvider(this).get(CafeteriaViewmodel::class.java)

        setRecyclerView()
        observeClient()

        binding.buttonGoToSegonPlat.setOnClickListener {
            view?.findNavController()
                ?.navigate(R.id.action_primerPlatFragment_to_segonPlatFragment);
        }

        return binding.root

    }

    private fun setRecyclerView() {

        val platsRecyclerview = binding.recyclerViewPrimersPlats
        platsRecyclerview.layoutManager = LinearLayoutManager(requireContext())
        platsRecyclerview.setHasFixedSize(true)

        adapter = PlatAdapter()

        adapter.setItemListener(object : RecyclerClickListener {
            override fun onItemClick(position: Int) {
                val primersPlatsList = adapter.currentList.toMutableList().get(position)
                Toast.makeText(
                    requireContext(),
                    "Nom: ${primersPlatsList.nom} , Preu: ${primersPlatsList.preu}€",
                    Toast.LENGTH_SHORT
                ).show()
                listPlats.add(primersPlatsList.nom)
                listPreus.add(primersPlatsList.preu)

            }
        })
        platsRecyclerview.adapter = adapter
    }

    private fun observeClient() {
        cafeteriaViewmodel.obtenirPlats(requireContext(), 1)!!
            .observe(viewLifecycleOwner, Observer { llistaPrimersPlats ->
                adapter.submitList(llistaPrimersPlats)
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