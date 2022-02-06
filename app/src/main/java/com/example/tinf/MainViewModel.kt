package com.example.tinf

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.tinf.domain.Post
import com.example.tinf.domain.PostsRepository
import kotlinx.coroutines.launch

class MainViewModel(
    private val repository: PostsRepository
) : ViewModel() {

    val post = MutableLiveData<Post?>()

    fun getPost() {
        viewModelScope.launch {
            post.value = repository.getPost()
        }
    }

    fun getPreviousPost() {
        viewModelScope.launch {
            post.value = repository.getPreviousPost()
        }
    }
}
