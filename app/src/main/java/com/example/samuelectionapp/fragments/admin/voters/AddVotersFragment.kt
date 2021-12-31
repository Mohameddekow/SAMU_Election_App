package com.example.samuelectionapp.fragments.admin.voters

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.example.samuelectionapp.R
import com.example.samuelectionapp.databinding.FragmentAddVotersBinding

class AddVotersFragment : Fragment() {

    private var _binding: FragmentAddVotersBinding? = null
    private val binding get() = _binding!!


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentAddVotersBinding.inflate(inflater, container, false)


        binding.btnRegister.setOnClickListener {
            val names = binding.etName.text.toString()
            var name: String = "jjjj"
            val  c = "c"
            val d = "d"
            val e = "e"
            val f = "f"
            if ( c in names){
                name = "ComputerCCCCCCCC"
            }
            if ( d in names){
                name = "ComputerDDDDDDDDD"
            }
            if ( e in names){
                name = "ComputerEEEEEE"
            }
            if ( f in names){
                name = "ComputerFFFFFF"
            }
            Toast.makeText(requireContext(), name.toString(), Toast.LENGTH_SHORT).show()
        }

        return binding.root
    }




    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }
}