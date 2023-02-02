package com.example.projectacafateria20.viewmodel.listAdapter

import androidx.recyclerview.widget.ListAdapter
import com.example.projectacafateria20.model.Plat

class PlatsAdapter : ListAdapter<Plat, PlatsAdapter.ClientHolder>(DiffCallback()) {


}