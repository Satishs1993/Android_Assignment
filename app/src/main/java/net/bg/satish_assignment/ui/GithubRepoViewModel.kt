package net.bg.satish_assignment.ui

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import net.bg.satish_assignment.data.models.GithubResponse
import net.bg.satish_assignment.data.models.Item
import net.bg.satish_assignment.data.network.RetrofitCallbackResource
import net.bg.satish_assignment.data.repository.GithubRepository


class GithubRepoViewModel @ViewModelInject constructor(private val repo : GithubRepository) : ViewModel() {

    private val _repoList : MutableLiveData<RetrofitCallbackResource<GithubResponse>> = MutableLiveData()
    private val _dbRepoList : MutableLiveData<List<Item>> = MutableLiveData()

    val repoList : LiveData<RetrofitCallbackResource<GithubResponse>>
    get() = _repoList

    val dbRepoList : LiveData<List<Item>>
        get() = _dbRepoList

    fun getListData() = viewModelScope.launch {
        _repoList.value = repo.getRepository("android")
    }

    fun getDbData() = viewModelScope.launch {
        _dbRepoList.value = repo.getDbData()
    }
}