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


class DogsInfo : Fragment() {
    private var _binding: FragmentDogsInfoBinding?=null
    private val binding
        get()=_binding!!
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
        binding.name.text=data?.breed.toString()
        binding.dogOrigin.text=data?.origin.toString()
        binding.image.load(data?.img.toString())
//        Toast.makeText(requireContext(), data?.meta.toString(), Toast.LENGTH_SHORT).show()
        binding.commonName.text=data?.meta?.notes.toString()
        return binding.root
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding=null
    }
}