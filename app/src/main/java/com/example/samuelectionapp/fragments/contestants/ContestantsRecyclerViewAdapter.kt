package com.example.samuelectionapp.fragments.contestants

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.samuelectionapp.databinding.ContestantsRecyclerviewRowLayoutBinding
import com.example.samuelectionapp.utils.setImageWithPicasso

class ContestantsRecyclerViewAdapter(
   // val context: Context,
   // private var contestantsList: List<ContestantsObject>
    ):
    RecyclerView.Adapter<ContestantsRecyclerViewAdapter.ContestantsRecyclerViewViewHolder>() {

//    private var contestantsList = emptyList<ContestantsObject>()

    private  var contestantsList: MutableList<ContestantsObject> = mutableListOf()

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ContestantsRecyclerViewViewHolder {
        val binding = ContestantsRecyclerviewRowLayoutBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )

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

            fun onBind(contestants: ContestantsObject){
                binding.apply {
                    //contestantsImage.setImageResource(contestants.image)
                    contestantsName.text =  "Name:   ${contestants.contestantsName}"
                    contestantsSchool.text = "School:   ${contestants.contestantsSchool}"
                    contestantsPosition.text = "Position:   ${contestants.contestantsPosition}"

                    setImageWithPicasso(contestants.contestantsImageUrl.toString(), contestantsImage)
                    //contestants.contestantsImageUrl?.let { contestantsImage.loadImageWithGlide(it) }
                }

            }
    }
    fun setData(list: MutableList<ContestantsObject>){
        contestantsList = list
        notifyDataSetChanged()
    }
}