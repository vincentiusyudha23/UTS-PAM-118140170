package com.example.utspam2.network

import android.content.Context
import android.content.SharedPreferences

class SharedPreferenManage(context: Context) {
    val sharedPreferences: SharedPreferences
    var editor : SharedPreferences.Editor? = null
    val keyPreferences = "SMILEDB"
    val login = "login"
    val keyUserName = "Username"
    val keyPassword = "Password"
    val keyGithub = "GithubUsername"
    val keyNim = "Nim"
    val keyEmail = "Email"

    init{
        sharedPreferences = context.getSharedPreferences(keyPreferences, Context.MODE_PRIVATE)
        editor = sharedPreferences?.edit()
    }

    fun setAccount(username: String, password: String, githubname: String, nim: String, email: String){
        editor?.putString(keyUserName, username)
        editor?.putString(keyPassword, password)
        editor?.putString(keyGithub, githubname)
        editor?.putString(keyNim, nim)
        editor?.putString(keyEmail, email)
        editor?.apply()
    }

    fun setStatusLogin(status: Boolean){
        sharedPreferences.edit().putBoolean(login, status).apply()
    }

    fun getStatusLogin(): Boolean{
        return sharedPreferences.getBoolean(login, false)
    }

    fun getRememberUser(): ArrayList<String>{
        var userList = arrayListOf<String>()
        userList.add(sharedPreferences.getString(keyUserName,"null")!!)
        userList.add(sharedPreferences.getString(keyPassword,"null")!!)
        userList.add(sharedPreferences.getString(keyGithub,"null")!!)
        userList.add(sharedPreferences.getString(keyNim, "null")!!)
        userList.add(sharedPreferences.getString(keyEmail, "null")!!)
        return userList
    }


}