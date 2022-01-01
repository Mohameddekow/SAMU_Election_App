package com.example.samuelectionapp.fragments.results.chooseSchool.computing

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.samuelectionapp.databinding.FragmentComputingResultBinding
import com.example.samuelectionapp.fragments.contestants.ContestantsObject
import com.google.android.material.snackbar.Snackbar
import okhttp3.internal.notifyAll


class ComputingResultFragment : Fragment(), ComputingResultRecyclerViewAdapter.ItemClickListener {

    private var _binding: FragmentComputingResultBinding? = null
    private val binding get() = _binding!!

    private lateinit var viewModel: ComputingResultViewModel
    private lateinit var adapter: ComputingResultRecyclerViewAdapter
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentComputingResultBinding.inflate(inflater, container, false)

        viewModel = ComputingResultViewModel()

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

        adapter = ComputingResultRecyclerViewAdapter(this)

        binding.apply {
            computingRecyclerView.adapter = adapter
            computingRecyclerView.layoutManager = LinearLayoutManager(requireContext())
            computingRecyclerView.addItemDecoration(DividerItemDecoration(requireContext(), LinearLayoutManager.VERTICAL))
        }
        //fetch all data
        viewModel.fetchComputingSchool()

        binding.progressBar.visibility = View.VISIBLE
        viewModel.computing.observe(viewLifecycleOwner, Observer {
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
        val snackbar = Snackbar.make(
            binding.root,
            "${contestantItem.contestantsName}, ${contestantItem.contestantsPosition} has ${contestantItem.contestantsCounter}% of the votes",
            Snackbar.LENGTH_LONG
        )
        snackbar.setAction(" ") {
            //no action
        }
        snackbar.show()
    }
}