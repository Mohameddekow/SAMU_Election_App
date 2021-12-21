package com.example.samuelectionapp.fragments.results.chooseSchool

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import com.example.samuelectionapp.databinding.FragmentChooseSchoolResultsBinding
import com.example.samuelectionapp.globalData.GlobalData

class ChooseSchoolResultsFragment : Fragment() {

    private var _binding: FragmentChooseSchoolResultsBinding? = null
    private val binding get() = _binding!!


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentChooseSchoolResultsBinding.inflate(inflater, container, false)

        //adapter
        val chooseSchoolAdapter = ChooseSchoolResultsRecyclerViewAdapter()
        val data = GlobalData.chooseSchool
        binding.apply {
            chooseSchoolResultRecyclerView.adapter = chooseSchoolAdapter

            chooseSchoolResultRecyclerView.layoutManager = GridLayoutManager(requireContext(), 2)

            chooseSchoolAdapter.setData(data)
        }

        return binding.root
    }


    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }
}