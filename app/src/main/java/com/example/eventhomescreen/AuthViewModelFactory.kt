package com.example.mvvmarchitecture.ui.auth

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.mvvmarchitecture.data.reposistries.UserReposotory

@Suppress("UNCHECKED_CAST")
class AuthViewModelFactory(
    private val repository : UserReposotory
) : ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return AuthViewModel(repository) as T
    }
}