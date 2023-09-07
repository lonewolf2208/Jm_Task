package com.example.jumpingmindstask.view

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.jumpingmindstask.R
import com.example.jumpingmindstask.adapter.DogsAdapter
import com.example.jumpingmindstask.adapter.FavoriteAdapter
import com.example.jumpingmindstask.databinding.FragmentDogsInfoBinding
import com.example.jumpingmindstask.databinding.FragmentFavoriteBinding
import com.example.jumpingmindstask.utils.Resource
import com.example.jumpingmindstask.viewModel.HomeViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class Favorite : Fragment() {
    private var _binding: FragmentFavoriteBinding?=null
    private val binding
        get()=_binding!!
    lateinit var viewModel: HomeViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(this)[HomeViewModel::class.java]
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        // Inflate the layout for this fragment
       _binding=FragmentFavoriteBinding.inflate(inflater, container, false)
        var adapter= FavoriteAdapter()
        lifecycleScope.launch {
            viewModel.data.collectLatest {
                when(it){

                    is Resource.Loading->{
                    }
                    else ->
                    {
                        adapter.submitList(it.data)
                        var layoutManager = StaggeredGridLayoutManager(2, LinearLayoutManager.VERTICAL)
                        binding.favoritesRecyclerView.adapter= adapter
                        binding.favoritesRecyclerView.layoutManager=layoutManager
                        adapter.onClickListener(object : FavoriteAdapter.ClickListener {
                            override fun OnClick(position: Int) {
                                var data = it.data?.get(position)
                                var bundle = Bundle()
                                bundle.putParcelable("data",data)
                                findNavController().navigate(R.id.dogsInfo,bundle)
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