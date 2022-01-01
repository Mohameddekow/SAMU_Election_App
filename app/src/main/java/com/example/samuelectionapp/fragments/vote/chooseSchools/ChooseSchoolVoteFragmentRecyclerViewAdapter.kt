package com.example.samuelectionapp.fragments.vote.chooseSchools

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.samuelectionapp.databinding.ChooseSchoolVoteRecycleviewRowLayoutBinding
import com.example.samuelectionapp.fragments.results.ChooseSchoolItems
import com.example.samuelectionapp.home.HomeItems
import com.example.samuelectionapp.home.HomeRecyclerViewAdapter

class ChooseSchoolVoteFragmentRecyclerViewAdapter(
    private val clickListener: ItemClickListener
):
    RecyclerView.Adapter<ChooseSchoolVoteFragmentRecyclerViewAdapter.ChooseSchoolVoteViewHolder>() {

    private var chooseSchoolList = emptyList<ChooseSchoolItems>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ChooseSchoolVoteViewHolder {
        val binding = ChooseSchoolVoteRecycleviewRowLayoutBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )

        return ChooseSchoolVoteViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ChooseSchoolVoteViewHolder, position: Int) {
        val currentChooseSchoolItem = chooseSchoolList[position]

        holder.onBind(currentChooseSchoolItem, clickListener)
    }

    override fun getItemCount(): Int {
        return  chooseSchoolList.size
    }


    inner class ChooseSchoolVoteViewHolder(
        val binding: ChooseSchoolVoteRecycleviewRowLayoutBinding
    ): RecyclerView.ViewHolder(binding.root){

        fun onBind(chooseSchoolItem: ChooseSchoolItems, action: ItemClickListener){
            binding.apply {
                chooseSchoolImageView.setImageResource(chooseSchoolItem.image)
                chooseSchoolTextView.text = chooseSchoolItem.name
            }
            binding.root.setOnClickListener {
                action.onItemClicked(it, chooseSchoolItem, adapterPosition)
            }

        }

    }
    fun setData(chooseSchool: List<ChooseSchoolItems>){
        chooseSchoolList = chooseSchool
        notifyDataSetChanged()
    }

    interface ItemClickListener{
        fun onItemClicked(view: View, chooseSchoolItem: ChooseSchoolItems, position: Int)
    }

}