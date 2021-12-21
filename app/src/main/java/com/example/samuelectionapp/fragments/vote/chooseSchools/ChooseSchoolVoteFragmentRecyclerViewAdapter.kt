package com.example.samuelectionapp.fragments.vote.chooseSchools

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.samuelectionapp.databinding.ChooseSchoolVoteRecycleviewRowLayoutBinding
import com.example.samuelectionapp.fragments.results.ChooseSchoolItems

class ChooseSchoolVoteFragmentRecyclerViewAdapter():
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

        holder.onBind(currentChooseSchoolItem)
    }

    override fun getItemCount(): Int {
        return  chooseSchoolList.size
    }


    inner class ChooseSchoolVoteViewHolder(
        val binding: ChooseSchoolVoteRecycleviewRowLayoutBinding
    ): RecyclerView.ViewHolder(binding.root){

        fun onBind(chooseSchool: ChooseSchoolItems){
            binding.apply {
                chooseSchoolImageView.setImageResource(chooseSchool.image)
                chooseSchoolTextView.text = chooseSchool.name
            }
        }

    }
    fun setData(chooseSchool: List<ChooseSchoolItems>){
        chooseSchoolList = chooseSchool
        notifyDataSetChanged()
    }


}