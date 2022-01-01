package com.example.samuelectionapp.fragments.results.chooseSchool.engineering

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.samuelectionapp.databinding.SchoolRecycerviewRowResultLayoutBinding
import com.example.samuelectionapp.fragments.contestants.ContestantsObject
import com.example.samuelectionapp.utils.setImageWithPicasso

class EngineeringResultRecyclerViewAdapter(
    private val clickListener: ItemClickListener
): RecyclerView.Adapter<EngineeringResultRecyclerViewAdapter.EngineeringResultRecyclerViewHolder>() {

    private var listItems =  emptyList<ContestantsObject>()


    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): EngineeringResultRecyclerViewHolder {
        val binding = SchoolRecycerviewRowResultLayoutBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return EngineeringResultRecyclerViewHolder(binding)
    }

    override fun onBindViewHolder(holder: EngineeringResultRecyclerViewHolder, position: Int) {
        val currentItem = listItems[position]
        holder.onBind(currentItem, clickListener)
    }

    override fun getItemCount(): Int {
         return listItems.size
    }



    inner class EngineeringResultRecyclerViewHolder(
        private val binding: SchoolRecycerviewRowResultLayoutBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun onBind(contestantItem: ContestantsObject, action: ItemClickListener){

            setImageWithPicasso(contestantItem.contestantsImageUrl, binding.schoolRepProfileImage)
            binding.schoolRepName.text = "Name: ${contestantItem.contestantsName}"
            binding.schoolRepSchool.text = "School: ${contestantItem.contestantsSchool}"
            binding.schoolRepPosition.text = "Position: ${ contestantItem.contestantsPosition}"
            binding.schoolRepResult.text = " ${contestantItem.contestantsCounter.toString()} %"

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