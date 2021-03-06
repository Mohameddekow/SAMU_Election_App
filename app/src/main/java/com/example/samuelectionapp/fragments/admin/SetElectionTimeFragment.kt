package com.example.samuelectionapp.fragments.admin

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.samuelectionapp.R
import com.example.samuelectionapp.databinding.FragmentSetElectionTimeBinding


class SetElectionTimeFragment : Fragment() {

    private var _binding: FragmentSetElectionTimeBinding? = null
    private val binding get() = _binding!!


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentSetElectionTimeBinding.inflate(inflater, container, false)



        return binding.root
    }



    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }
}