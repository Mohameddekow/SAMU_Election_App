package com.example.samuelectionapp.fragments.vote.chooseSchools.engineering

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.samuelectionapp.databinding.SchoolRecycerviewRowVoteLayoutBinding
import com.example.samuelectionapp.fragments.contestants.ContestantsObject
import com.example.samuelectionapp.utils.setImageWithPicasso

class EngineeringVoteRecyclerViewAdapter(
    private var clickListener: ItemClickListener
): RecyclerView.Adapter<EngineeringVoteRecyclerViewAdapter.EngineeringVoteRecyclerViewHolder>() {

    private var listItems =  emptyList<ContestantsObject>()


    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): EngineeringVoteRecyclerViewHolder {
        val binding = SchoolRecycerviewRowVoteLayoutBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return EngineeringVoteRecyclerViewHolder(binding)
    }

    override fun onBindViewHolder(holder: EngineeringVoteRecyclerViewHolder, position: Int) {
        val currentItem = listItems[position]
        holder.onBind(currentItem, clickListener)

    }

    override fun getItemCount(): Int {
        return listItems.size
    }

    inner class EngineeringVoteRecyclerViewHolder(
        private val binding: SchoolRecycerviewRowVoteLayoutBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun onBind(contestantItem: ContestantsObject, action: ItemClickListener){

               setImageWithPicasso(contestantItem.contestantsImageUrl, binding.schoolRepProfileImage)
               binding.schoolRepName.text = "Name: ${contestantItem.contestantsName}"
               binding.schoolRepSchool.text = "School: ${contestantItem.contestantsSchool}"
               binding.schoolRepPosition.text = "Position: ${ contestantItem.contestantsPosition}"

            binding.root.setOnClickListener {
                action.onItemClicked(it, contestantItem, adapterPosition)
            }

        }
    }

    fun setData(items: List<ContestantsObject> ){
        listItems = items
        notifyDataSetChanged()
    }

    interface ItemClickListener{
        fun onItemClicked(view: View, contestantItem: ContestantsObject, position: Int)
    }

}