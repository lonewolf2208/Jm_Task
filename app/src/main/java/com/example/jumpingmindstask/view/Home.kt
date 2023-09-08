package com.example.jumpingmindstask.view

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.jumpingmindstask.R
import com.example.jumpingmindstask.adapter.DogsAdapter
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
        var adapter= DogsAdapter()

        bottomNavView?.visibility=View.VISIBLE
        lifecycleScope.launch {
            viewModel.data.collectLatest {
                when(it){

                    is Resource.Loading->{
                        binding.progressBar.visibility=View.VISIBLE
                    }
                    else ->
                    {
                        binding.progressBar.visibility=View.GONE
                        adapter.submitList(it.data)
                        adapter.notifyDataSetChanged()
                        binding.SearchContainer.addTextChangedListener(object : TextWatcher {
                            override fun beforeTextChanged(
                                p0: CharSequence?,
                                p1: Int,
                                p2: Int,
                                p3: Int,
                            ) {

                            }

                            override fun onTextChanged(
                                p0: CharSequence?,
                                p1: Int,
                                p2: Int,
                                p3: Int,
                            ) {
                                var data = it.data?.filter {
                                    it.breed.contains(binding.SearchContainer.text.toString())
                                }
                                adapter.submitList(data)
                            }

                            override fun afterTextChanged(p0: Editable?) {
                            }
                        })
                        var layoutManager = StaggeredGridLayoutManager(2, LinearLayoutManager.VERTICAL)
                        binding.homeRecycleView.adapter= adapter
                        binding.homeRecycleView.layoutManager=layoutManager
                        adapter.onClickListener(object : DogsAdapter.ClickListener {
                            override fun OnClick(position: Int) {
                                var data = it.data?.get(position)
                                var bundle = Bundle()
                                bundle.putParcelable("data",data)
                                findNavController().navigate(R.id.action_home_to_dogsInfo,bundle)
                            }
                        })

                    }
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