package com.example.samuelectionapp.fragments.contestants

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.samuelectionapp.databinding.ContestantsRecyclerviewRowLayoutBinding

class ContestantsRecyclerViewAdapter():
    RecyclerView.Adapter<ContestantsRecyclerViewAdapter.ContestantsRecyclerViewViewHolder>() {

    private var contestantsList = emptyList<ContestantsItems>()

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ContestantsRecyclerViewViewHolder {
        val binding = ContestantsRecyclerviewRowLayoutBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false)

        return ContestantsRecyclerViewViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ContestantsRecyclerViewViewHolder, position: Int) {
        val currentContestant = contestantsList[position]

        holder.onBind(currentContestant)
    }

    override fun getItemCount(): Int {
        return contestantsList.size
    }

    inner class ContestantsRecyclerViewViewHolder(
        private val binding: ContestantsRecyclerviewRowLayoutBinding
        ): RecyclerView.ViewHolder(binding.root){

            fun onBind(contestants: ContestantsItems){
                binding.apply {
                    contestantsImage.setImageResource(contestants.image)
                    contestantsName.text = contestants.name
                    contestantsSchool.text = contestants.school
                    contestantsPosition.text = contestants.position
                }

            }
    }
    fun setData(list: List<ContestantsItems>){
        contestantsList = list
        notifyDataSetChanged()
    }
}