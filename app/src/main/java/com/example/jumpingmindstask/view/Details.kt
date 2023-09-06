package com.example.jumpingmindstask.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.jumpingmindstask.R
import com.example.jumpingmindstask.databinding.FragmentDetailsBinding
import com.google.android.material.bottomnavigation.BottomNavigationView

class Details : Fragment() {

    private var _binding:FragmentDetailsBinding?=null
    private val binding
        get()=_binding!!
    private val bottomNavView by lazy  { activity?.findViewById<BottomNavigationView>(R.id.bottomNavigationView) }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        // Inflate the layout for this fragment
        _binding=FragmentDetailsBinding.inflate(inflater,container,false)
        bottomNavView?.visibility = View.GONE
        binding.getStart.setOnClickListener {
            findNavController().navigate(R.id.home)
        }
        return binding.root
    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding=null
    }


}