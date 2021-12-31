package com.example.samuelectionapp.fragments.contestants

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.samuelectionapp.R
import com.example.samuelectionapp.databinding.FragmentContestantsBinding
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.storage.FirebaseStorage


class ContestantsFragment : Fragment() {

    private var _binding: FragmentContestantsBinding? = null
    private val binding get() = _binding!!

    private lateinit var firebaseStorage: FirebaseStorage
    private lateinit var firebaseFirestore: FirebaseFirestore


    private lateinit var viewModel: ContestantsViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentContestantsBinding.inflate(inflater, container, false)


        // initialize adapter
        val contestantAdapter = ContestantsRecyclerViewAdapter()

        //initialize viewModel
        viewModel = ContestantsViewModel()

        //call the the fun the fetches all data from ua viewModel
        viewModel.fetchContestants()

        //show the progress bar b4 data is ready
        binding.progressBar.visibility = View.VISIBLE




        //listen for errors and success
        viewModel.result.observe(viewLifecycleOwner, Observer {
            val errorOrSuccessMessage = if (it == null){
                getString(R.string.contestant_details)
            }else{
                getString(R.string.contestant_details_error, it.message)
            }

            val snackbar = Snackbar.make(
                binding.root,
                errorOrSuccessMessage,
                Snackbar.LENGTH_SHORT
            )
            snackbar.setAction(" ") {
                //no action
            }
            snackbar.show()

        })


        //observer the live data that is being fetched from the database
        viewModel.contestants.observe(viewLifecycleOwner, Observer {
            //hide the progress bar when data is ready
            binding.progressBar.visibility = View.GONE

            //the the data in the adapter
            contestantAdapter.setData(it as MutableList<ContestantsObject>)

            println(it)
            Log.d("conte", it.toString())
        })

        //damy data
        //val data = GlobalData.ContData
        binding.apply {

            contestantsRecyclerView.adapter = contestantAdapter
            contestantsRecyclerView.layoutManager = LinearLayoutManager(requireContext())
        }


        return binding.root
    }


    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }
}