package com.example.jumpingmindstask.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import com.example.jumpingmindstask.R
import com.example.jumpingmindstask.databinding.FragmentDetailsBinding
import com.example.jumpingmindstask.databinding.FragmentHomeBinding
import com.example.jumpingmindstask.utils.Resource
import com.example.jumpingmindstask.viewModel.HomeViewModel
import com.google.android.material.bottomnavigation.BottomNavigationView
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.observeOn
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


@AndroidEntryPoint
class Home : Fragment() {
    private var _binding: FragmentHomeBinding?=null
    lateinit var viewModel: HomeViewModel
    private val binding
        get()=_binding!!
    private val bottomNavView by lazy  { activity?.findViewById<BottomNavigationView>(R.id.bottomNavigationView) }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(this)[HomeViewModel::class.java]
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        // Inflate the layout for this fragment
        _binding= FragmentHomeBinding.inflate(inflater, container, false)
        bottomNavView?.visibility=View.VISIBLE
        lifecycleScope.launch {
            viewModel.data.collectLatest {
                when(it){
                    is Resource.Success -> Toast.makeText(requireContext(), it.data.toString(), Toast.LENGTH_SHORT).show()
                    else ->Toast.makeText(requireContext(), it.error.toString(), Toast.LENGTH_SHORT).show()
                }
            }
        }
        return binding.root
    }
    override fun onDestroy() {
        super.onDestroy()
        _binding=null
    }
}