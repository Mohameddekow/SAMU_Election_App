package com.example.samuelectionapp.fragments.results.chooseSchool

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.example.samuelectionapp.R
import com.example.samuelectionapp.databinding.FragmentChooseSchoolResultsBinding
import com.example.samuelectionapp.fragments.results.ChooseSchoolItems
import com.example.samuelectionapp.globalData.GlobalData

class ChooseSchoolResultsFragment : Fragment(), ChooseSchoolResultsRecyclerViewAdapter.ItemClickListener {

    private var _binding: FragmentChooseSchoolResultsBinding? = null
    private val binding get() = _binding!!


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentChooseSchoolResultsBinding.inflate(inflater, container, false)

        //adapter
        val chooseSchoolAdapter = ChooseSchoolResultsRecyclerViewAdapter(this)
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

    override fun onItemClicked(view: View, chooseSchoolItem: ChooseSchoolItems, position: Int) {
        when(position){
            2 -> { findNavController().navigate(R.id.action_chooseSchoolResultsFragment_to_computingResultFragment)}
            3 -> { findNavController().navigate(R.id.action_chooseSchoolResultsFragment_to_engineeringResultFragment)}
            else -> {
                val builder = AlertDialog.Builder(requireContext())
                builder.setPositiveButton("Ok") { _, _ ->

                }
                builder.setNegativeButton("") { _, _ ->

                }
                builder.setTitle("Progress Info")
                builder.setMessage("We're working on the rest of the schools, try to have the latest version of the app next time. Thanks")
                builder.create()
                builder.show()
            }

        }
    }
}