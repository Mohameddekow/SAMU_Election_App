package com.example.samuelectionapp.splash

import android.os.Bundle
import android.os.Handler
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.samuelectionapp.R
import com.example.samuelectionapp.databinding.FragmentSplashBinding
import com.google.firebase.auth.FirebaseAuth


class SplashFragment : Fragment() {
    private var _binding: FragmentSplashBinding? = null
    private val binding get() = _binding!!


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentSplashBinding.inflate(inflater, container, false)

        //check if user is already logged in if so skipp the login process
        val  auth: FirebaseAuth = FirebaseAuth.getInstance()

        Handler().postDelayed(
            {
                if (auth.currentUser != null){
                    findNavController().navigate(R.id.action_splashFragment_to_homeFragment)
                }else{
                    findNavController().navigate(R.id.action_splashFragment_to_loginFragment)
                }


            //findNavController().navigate(R.id.action_splashFragment_to_loginFragment)
            //findNavController().navigate(R.id.action_splashFragment_to_addContestantsFragment)


            },2000
        )

        return binding.root
    }




    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }
}