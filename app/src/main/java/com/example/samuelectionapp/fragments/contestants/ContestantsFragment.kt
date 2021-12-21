package com.example.samuelectionapp.fragments.contestants

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.samuelectionapp.R
import com.example.samuelectionapp.databinding.FragmentContestantsBinding
import com.example.samuelectionapp.globalData.GlobalData


class ContestantsFragment : Fragment() {

    private var _binding: FragmentContestantsBinding? = null
    private val binding get() = _binding!!


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentContestantsBinding.inflate(inflater, container, false)


        //adapter
        val contestantAdapter = ContestantsRecyclerViewAdapter()

        //damy data
        val data = GlobalData.ContData
        binding.apply {
            contestantsRecyclerView.adapter = contestantAdapter
            contestantsRecyclerView.layoutManager = LinearLayoutManager(requireContext())
            contestantAdapter.setData(data)
        }


        return binding.root
    }


    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }
}