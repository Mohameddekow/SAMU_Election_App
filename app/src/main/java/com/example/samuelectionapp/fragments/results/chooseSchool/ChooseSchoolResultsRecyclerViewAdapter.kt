package com.example.samuelectionapp.fragments.results.chooseSchool

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.samuelectionapp.databinding.ChooseSchoolResultRecycleviewRowLayoutBinding
import com.example.samuelectionapp.fragments.results.ChooseSchoolItems

class ChooseSchoolResultsRecyclerViewAdapter():
    RecyclerView.Adapter<ChooseSchoolResultsRecyclerViewAdapter.ChooseSchoolResultsViewHolder>() {

    private var chooseSchoolList = emptyList<ChooseSchoolItems>()

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ChooseSchoolResultsViewHolder {
        val binding = ChooseSchoolResultRecycleviewRowLayoutBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )

        return ChooseSchoolResultsViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ChooseSchoolResultsViewHolder, position: Int) {
        val currentChooseSchoolItem = chooseSchoolList[position]

        holder.onBind(currentChooseSchoolItem)
    }

    override fun getItemCount(): Int {
        return  chooseSchoolList.size
    }


    inner class ChooseSchoolResultsViewHolder(
        val binding: ChooseSchoolResultRecycleviewRowLayoutBinding
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