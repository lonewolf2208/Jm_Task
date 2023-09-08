package com.example.jumpingmindstask.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import coil.load
import com.example.jumpingmindstask.R
import com.example.jumpingmindstask.databinding.FragmentDetailsBinding
import com.example.jumpingmindstask.databinding.FragmentDogsInfoBinding
import com.example.jumpingmindstask.model.DogsDataItem
import com.example.jumpingmindstask.utils.Resource
import com.google.android.material.bottomnavigation.BottomNavigationView


class DogsInfo : Fragment() {
    private var _binding: FragmentDogsInfoBinding?=null
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
         _binding=FragmentDogsInfoBinding.inflate(inflater, container, false)
        var data  = arguments?.getParcelable<DogsDataItem>("data")
        bottomNavView?.visibility = View.GONE

        binding.apply {
            name.text=data?.breed.toString()
            dogOrigin.text=data?.origin.toString()
            image.load(data?.img.toString())
            commonName.text=data?.meta?.notes.toString()
            status.text=data?.meta?.breed_status.toString()
            otherName.text=data?.meta?.other_names.toString()
        }
        return binding.root
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding=null
    }
}