package com.example.mvvmarchitecture.ui.auth

import android.content.Intent
import android.view.View
import androidx.lifecycle.ViewModel
import com.example.mvvmarchitecture.data.reposistries.UserReposotory
import com.example.mvvmarchitecture.util.ApiException
import com.example.mvvmarchitecture.util.Coroutines
import com.example.mvvmarchitecture.util.NoInternetExceptiom

class AuthViewModel(
    private val repository : UserReposotory
) : ViewModel() {
    var email: String? =null
    var password: String?=null
    var name: String?=null
    var passwordconfirm: String?=null

    var authListener :AuthListener? = null
    fun getLoggedInUser()=repository.getUser()

    fun onSignup(view: View)
    {
        Intent(view.context,SignupActivity::class.java).also {
            view.context.startActivity(it)
        }
    }
    fun onLogin(view : View)
    {
        Intent(view.context,LoginActivity::class.java).also {
            view.context.startActivity(it)
        }
    }
    fun onLoginButtonClick(view : View){

        if(email.isNullOrEmpty() || password.isNullOrEmpty())
        {
            authListener?.onfaliure("Invalid Credentials")
            return
        }


        Coroutines.main {
            try {
                val authresponse =repository.userlogin(email!!,password!!)
                authresponse.user?.let {
                    authListener?.onsuccess(it)
                    repository.saveUser(it)
                    return@main
                }
                authListener?.onfaliure(authresponse.message!!)

            }catch (e : ApiException)
            {
                authListener?.onfaliure(e.message!!)
            }catch (e :NoInternetExceptiom)
            {
                authListener?.onfaliure(e.message!!)
            }


        }

//        val loginresponse =UserReposotory().userlogin(email!!, password!!)
//        authListener?.onsuccess(loginresponse)
    }

    fun onSignButtonClick(view : View){

        if(name.isNullOrEmpty())
        {
            authListener?.onfaliure("name is requied")
            return
        }
        if(email.isNullOrEmpty() || password.isNullOrEmpty())
        {
            authListener?.onfaliure("Invalid email or password")
            return
        }
        if(password != passwordconfirm)
        {
            authListener?.onfaliure("pass did not match")
        }

        Coroutines.main {
            try {
                val authresponse =repository.userSignup(name!!,email!!,password!!)
                authresponse.user?.let {
                    authListener?.onsuccess(it)
                    repository.saveUser(it)
                    return@main
                }
                authListener?.onfaliure(authresponse.message!!)

            }catch (e : ApiException)
            {
                authListener?.onfaliure(e.message!!)
            }catch (e :NoInternetExceptiom)
            {
                authListener?.onfaliure(e.message!!)
            }


        }

//        val loginresponse =UserReposotory().userlogin(email!!, password!!)
//        authListener?.onsuccess(loginresponse)
    }
}