package com.example.samuelectionapp.home

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.samuelectionapp.databinding.HomeRecyclerviewRowLayoutBinding

class HomeRecyclerViewAdapter(private val clickListener: ItemClickListener):
    RecyclerView.Adapter<HomeRecyclerViewAdapter.HomeRecyclerViewViewHolder>() {

    private var listHomeItems =  emptyList<HomeItems>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeRecyclerViewViewHolder {
        val binding = HomeRecyclerviewRowLayoutBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false)

        return HomeRecyclerViewViewHolder(binding)
    }

    override fun onBindViewHolder(holder: HomeRecyclerViewViewHolder, position: Int) {
        val currentHomeItem = listHomeItems[position]
        holder.onBind(currentHomeItem, clickListener)
    }

    override fun getItemCount(): Int {
        return listHomeItems.size
    }


    inner class HomeRecyclerViewViewHolder(
        private val binding: HomeRecyclerviewRowLayoutBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun onBind(homeItem: HomeItems, action: ItemClickListener){
            binding.apply {
                contestantsImageView.setImageResource(homeItem.image)
                contestantsTextView.text = homeItem.name
            }
            binding.root.setOnClickListener {
                action.onItemClicked(it, homeItem, adapterPosition)
            }
        }
    }

    fun setData(items: List<HomeItems> ){
        listHomeItems = items
        notifyDataSetChanged()
    }

    interface ItemClickListener{
        fun onItemClicked(view: View, homeItem: HomeItems, position: Int)
    }
}