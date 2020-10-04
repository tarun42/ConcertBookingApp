package com.example.mvvmarchitecture.ui.auth

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.mvvmarchitecture.R
import com.example.mvvmarchitecture.data.db.entity.User
import com.example.mvvmarchitecture.databinding.ActivityLoginBinding
import com.example.mvvmarchitecture.ui.home.HomeActivity
import com.example.mvvmarchitecture.util.hide
import com.example.mvvmarchitecture.util.show
import com.example.mvvmarchitecture.util.snackbar
import kotlinx.android.synthetic.main.activity_login.*
import org.kodein.di.android.kodein
import org.kodein.di.KodeinAware
import org.kodein.di.generic.instance


class LoginActivity : AppCompatActivity(),AuthListener,KodeinAware {

    override val kodein by kodein()
    private val factory: AuthViewModelFactory  by instance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)



        setContentView(R.layout.activity_login)
        val binding : ActivityLoginBinding = DataBindingUtil.setContentView(this,R.layout.activity_login)
        val viewModel = ViewModelProviders.of(this,factory).get(AuthViewModel::class.java)

        binding.viewmodel =viewModel
        viewModel.authListener=this
        viewModel.getLoggedInUser().observe(this, Observer {
            user ->
            if(user != null)
            {
                Intent(this,HomeActivity::class.java).also {
                    it.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                    startActivity(it)
                }
            }

        })

    }

    override fun onstarted() {
//        toast("Login Strated")
        progress_bar.show()
    }

    override fun onsuccess(user : User) {
        progress_bar.hide()
//        toast("$(user.name) is logged in")
    root_layout.snackbar("$(user.name) is logged in")
    }


    override fun onfaliure(message: String) {
        progress_bar.hide()
//        toast(message)
        root_layout.snackbar(message)
    }



}