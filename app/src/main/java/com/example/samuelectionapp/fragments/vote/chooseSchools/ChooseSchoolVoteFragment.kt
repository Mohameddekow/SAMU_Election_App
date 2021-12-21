package com.example.samuelectionapp.fragments.vote.chooseSchools

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import com.example.samuelectionapp.R
import com.example.samuelectionapp.databinding.FragmentChooseSchoolVoteBinding
import com.example.samuelectionapp.fragments.results.chooseSchool.ChooseSchoolResultsRecyclerViewAdapter
import com.example.samuelectionapp.globalData.GlobalData

class ChooseSchoolVoteFragment : Fragment() {

    private var _binding: FragmentChooseSchoolVoteBinding? = null
    private val binding get() = _binding!!


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentChooseSchoolVoteBinding.inflate(inflater, container, false)


        //adapter
        val chooseSchoolAdapter = ChooseSchoolVoteFragmentRecyclerViewAdapter()
        val data = GlobalData.chooseSchool
        binding.apply {
            chooseSchoolVoteRecyclerView.adapter = chooseSchoolAdapter

            chooseSchoolVoteRecyclerView.layoutManager = GridLayoutManager(requireContext(), 2)

            chooseSchoolAdapter.setData(data)
        }

        return binding.root
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }
}