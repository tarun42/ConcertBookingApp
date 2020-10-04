package com.example.mvvmarchitecture.ui.auth

import com.example.mvvmarchitecture.data.db.entity.User

interface AuthListener {
    fun onstarted()
    fun onsuccess(user : User)
    fun onfaliure(message : String)

}