package com.example.samuelectionapp.home

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.example.samuelectionapp.R
import com.example.samuelectionapp.databinding.FragmentHomeBinding
import com.example.samuelectionapp.globalData.GlobalData


class HomeFragment : Fragment(), HomeRecyclerViewAdapter.ItemClickListener {
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

        //adapter
        val myAdapter = HomeRecyclerViewAdapter(this)

        //swt data on recyclerView
        val homeData = GlobalData.HomeData
        myAdapter.setData(homeData)

        binding.apply {
            homeRecyclerview.adapter = myAdapter
            homeRecyclerview.layoutManager = GridLayoutManager(requireContext(), 2)
        }


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
                findNavController().navigate(R.id.action_homeFragment_to_addVotersFragment)
            }
            R.id.addContestantsFragment ->{
                findNavController().navigate(R.id.action_homeFragment_to_addContestantsFragment)
            }
            R.id.setElectionTimeFragment ->{
                findNavController().navigate(R.id.action_homeFragment_to_setElectionTimeFragment)
            }
        }
        return super.onOptionsItemSelected(item)
    }

    //recyclerView item click listener
    override fun onItemClicked(view: View, homeItem: HomeItems, position: Int) {
        when(position){
           0 -> { findNavController().navigate(R.id.action_homeFragment_to_contestantsFragment)}
           1 -> { findNavController().navigate(R.id.action_homeFragment_to_chooseSchoolVoteFragment)}
           2 -> { findNavController().navigate(R.id.action_homeFragment_to_chooseSchoolResultsFragment)}
           3 -> { findNavController().navigate(R.id.action_homeFragment_to_helpFragment)}
        }
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }
}