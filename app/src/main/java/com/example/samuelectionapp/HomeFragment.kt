package com.example.samuelectionapp

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.samuelectionapp.databinding.FragmentHomeBinding


class HomeFragment : Fragment() {
    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentHomeBinding.inflate(inflater, container, false)


        //setup option menu
        setHasOptionsMenu(true)


        return binding.root
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.home_menu,menu)

        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        when(item.itemId){
            R.id.profileFragment ->{
                findNavController().navigate(R.id.action_homeFragment_to_profileFragment)
            }
            R.id.settingsFragment ->{
                findNavController().navigate(R.id.action_homeFragment_to_settingsFragment)
            }
            R.id.addVotersFragment ->{
                findNavController().navigate(R.id.action_homeFragment_to_adminLoginFragment)
            }
            R.id.addContestantsFragment ->{
                findNavController().navigate(R.id.action_homeFragment_to_adminLoginFragment)
            }
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }
}