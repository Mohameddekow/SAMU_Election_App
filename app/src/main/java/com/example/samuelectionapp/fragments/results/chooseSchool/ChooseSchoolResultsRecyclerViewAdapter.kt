package com.example.samuelectionapp.fragments.results.chooseSchool

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.samuelectionapp.databinding.ChooseSchoolResultRecycleviewRowLayoutBinding
import com.example.samuelectionapp.fragments.results.ChooseSchoolItems

class ChooseSchoolResultsRecyclerViewAdapter(private val clickListener: ItemClickListener ):
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

        holder.onBind(currentChooseSchoolItem, clickListener)
    }

    override fun getItemCount(): Int {
        return  chooseSchoolList.size
    }


    inner class ChooseSchoolResultsViewHolder(
        val binding: ChooseSchoolResultRecycleviewRowLayoutBinding
    ): RecyclerView.ViewHolder(binding.root){

        fun onBind(chooseSchoolItem: ChooseSchoolItems, action:ItemClickListener){
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