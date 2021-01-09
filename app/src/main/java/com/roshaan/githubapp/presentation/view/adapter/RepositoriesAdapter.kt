package com.avanza.sadapayrough.presentation.view.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.RequestManager
import com.roshaan.githubapp.data.model.Repository
import com.roshaan.githubapp.databinding.LiRepositoryBinding
import com.roshaan.githubapp.di.qualifier.Glide
import javax.inject.Inject

class RepositoriesAdapter @Inject constructor(@Glide private val glide: RequestManager) :
    RecyclerView.Adapter<RepositoriesAdapter.ViewHolder>() {

    private val list = mutableListOf<Repository>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
        ViewHolder(
            LiRepositoryBinding.inflate(LayoutInflater.from(parent.context), parent, false),
            glide
        )

    override fun getItemCount(): Int = list.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.setData(list[position], position)
    }

    fun addItems(list: List<Repository>?) {

//        clearing and adding new items
        this.list.clear()
        if (list != null)
            this.list.addAll(list)

        notifyDataSetChanged()
    }

    class ViewHolder(
        private val binding: LiRepositoryBinding,
        private val glide: RequestManager
    ) : RecyclerView.ViewHolder(binding.root) {

        fun setData(repository: Repository, position: Int) {

            repository.run {
                binding.ownerName.text = ownerName
                binding.fullName.text = fullName
                binding.description.text = description

                binding.starCount.text = starsCount.toString()

                glide.load(avatar).into(binding.avatar)
            }
        }
    }
}