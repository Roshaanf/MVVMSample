package com.roshaan.githubapp.presentation.view.adapter

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.roshaan.githubapp.data.model.Repository

class RepositoriesAdapter : RecyclerView.Adapter<RepositoriesAdapter.ViewHolder>() {

    fun addItems(list: List<Repository>?) {
    }

    class ViewHolder (val view: View): RecyclerView.ViewHolder(view)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        TODO("Not yet implemented")
    }

    override fun getItemCount(): Int {
        TODO("Not yet implemented")
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        TODO("Not yet implemented")
    }
}