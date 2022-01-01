package com.example.samuelectionapp.fragments.vote.chooseSchools

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.example.samuelectionapp.R
import com.example.samuelectionapp.databinding.FragmentChooseSchoolVoteBinding
import com.example.samuelectionapp.fragments.results.ChooseSchoolItems
import com.example.samuelectionapp.globalData.GlobalData

class ChooseSchoolVoteFragment : Fragment(), ChooseSchoolVoteFragmentRecyclerViewAdapter.ItemClickListener {

    private var _binding: FragmentChooseSchoolVoteBinding? = null
    private val binding get() = _binding!!


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentChooseSchoolVoteBinding.inflate(inflater, container, false)


        //adapter
        val chooseSchoolAdapter = ChooseSchoolVoteFragmentRecyclerViewAdapter(this)
        val data = GlobalData.chooseSchool
        binding.apply {
            chooseSchoolVoteRecyclerView.adapter = chooseSchoolAdapter

            chooseSchoolVoteRecyclerView.layoutManager = GridLayoutManager(requireContext(), 2)

            chooseSchoolAdapter.setData(data)
        }

        return binding.root
    }

    override fun onItemClicked(view: View, chooseSchoolItem: ChooseSchoolItems, position: Int) {
        when(position){
            2 -> { findNavController().navigate(R.id.action_chooseSchoolVoteFragment_to_computingVoteFragment)}
            3 -> { findNavController().navigate(R.id.action_chooseSchoolVoteFragment_to_engineeringVoteFragment)}
        }
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }


}