package com.example.samuelectionapp.fragments.results.chooseSchool.engineering

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.samuelectionapp.databinding.FragmentEngineeringResultBinding
import com.example.samuelectionapp.fragments.contestants.ContestantsObject
import com.google.android.material.snackbar.Snackbar


class EngineeringResultFragment : Fragment(), EngineeringResultRecyclerViewAdapter.ItemClickListener {

    private var _binding: FragmentEngineeringResultBinding? = null
    private val binding get() = _binding!!

    private lateinit var viewModel: EngineeringResultViewModel
    private lateinit var adapter: EngineeringResultRecyclerViewAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentEngineeringResultBinding.inflate(inflater, container, false)


        viewModel = EngineeringResultViewModel()

        //observer errors
        viewModel.result.observe(viewLifecycleOwner, Observer {
            if (it != null){

                val snackbar = Snackbar.make(
                    binding.root,
                    it.toString(),
                    Snackbar.LENGTH_LONG
                )
                snackbar.setAction(" ") {
                    //no action
                }
                snackbar.show()
            }
        })

        adapter = EngineeringResultRecyclerViewAdapter(this)

        binding.apply {
            engineeringRecyclerView.adapter = adapter
            engineeringRecyclerView.layoutManager = LinearLayoutManager(requireContext())
            engineeringRecyclerView.addItemDecoration(DividerItemDecoration(requireContext(), LinearLayoutManager.VERTICAL))

        }
        //fetch all data
        viewModel.fetchEngineeringSchool()

        //observe the live data
        binding.progressBar.visibility = View.VISIBLE

        viewModel.engineering.observe(viewLifecycleOwner, Observer {
            binding.progressBar.visibility = View.GONE

            adapter.setData(it)
        })





        return binding.root
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }

    override fun onItemClicked(view: View, contestantItem: ContestantsObject, position: Int) {

        val builder = AlertDialog.Builder(requireContext())
        builder.setPositiveButton("Ok") { _, _ ->

        }
        builder.setNegativeButton("") { _, _ ->

        }
        builder.setTitle("${contestantItem.contestantsName}-Result")
        builder.setMessage("${contestantItem.contestantsName}, ${contestantItem.contestantsPosition} has ${contestantItem.contestantsCounter}% of the votes")
        builder.create()
        builder.show()
    }
}