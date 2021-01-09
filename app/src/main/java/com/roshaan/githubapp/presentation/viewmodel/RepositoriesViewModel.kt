package com.roshaan.githubapp.presentation.viewmodel

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.roshaan.githubapp.data.repository.GithubRepository
import com.roshaan.githubapp.presentation.view.adapter.RepositoriesAdapter
import kotlinx.coroutines.launch

class RepositoriesViewModel @ViewModelInject constructor(
    private val githubRepository: GithubRepository,
    val adapter: RepositoriesAdapter
) :
    ViewModel() {


    private val _shimmerVisibility = MutableLiveData<Boolean>().apply { value = true }
    val shimmerVisibility: LiveData<Boolean>
        get() = _shimmerVisibility

    private val _errorVisibility = MutableLiveData<Boolean>()
    val errorVisibility: LiveData<Boolean>
        get() = _errorVisibility

    private val _reyclerViewVisibility = MutableLiveData<Boolean>()
    val reyclerViewVisibility: LiveData<Boolean>
        get() = _reyclerViewVisibility

    private val _swipeLoaderVisibility = MutableLiveData<Boolean>()
    val swipeLoaderVisibility: LiveData<Boolean>
        get() = _swipeLoaderVisibility

    init {
        fetchData(false)
    }

    fun fetchData(isHardrefresh: Boolean) {
        viewModelScope.launch {

            if (isHardrefresh || adapter.itemCount == 0) {
                updateMutableLiveData(_shimmerVisibility, true)
                updateMutableLiveData(_errorVisibility, false)
                updateMutableLiveData(_reyclerViewVisibility, false)
            }

            val repositories = githubRepository.getRepositories(true)

            updateMutableLiveData(_shimmerVisibility, false)
            _swipeLoaderVisibility.value = false

            adapter.addItems(repositories)

//            error in fetching
            if (repositories == null) {

                updateMutableLiveData(_errorVisibility, true)
                updateMutableLiveData(_reyclerViewVisibility, false)
            } else {

                updateMutableLiveData(_reyclerViewVisibility, true)
                updateMutableLiveData(_errorVisibility, false)
            }


        }
    }

    private fun updateMutableLiveData(mutableLiveData: MutableLiveData<Boolean>, value: Boolean) {
        if (mutableLiveData.value != value)
            mutableLiveData.value = value
    }

}