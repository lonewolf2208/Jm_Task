package com.example.jumpingmindstask.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.jumpingmindstask.repository.HomeRepository
import com.example.jumpingmindstask.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    repo:HomeRepository
):ViewModel(){
    val data =repo.getCars()
}