package com.example.utspam2

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.utspam2.databinding.ActivityLoginBinding
import com.example.utspam2.network.SharedPreferenManage

class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding
    private lateinit var sh: SharedPreferenManage

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.hide()

        sh = SharedPreferenManage(this)

        binding.btRegister.setOnClickListener{
            startActivity(Intent(this, RegisterActivity::class.java))
            finish()
        }
        binding.btSignin.setOnClickListener{
            val username = binding.lgUsername.text.toString()
            val password = binding.lgPassword.text.toString()
            var currentuser = arrayListOf<String>()
            if(!username.isNullOrEmpty()&&!password.isNullOrEmpty()){
                currentuser.add(username)
                currentuser.add(password)
                validationUser(currentuser)
            }else{
                Toast.makeText(this, "Isikan Username/Password", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun validationUser(_currentuser: ArrayList<String>){
        val user = sh.getRememberUser()

        if(user[0] == _currentuser[0] && user[1] == _currentuser[1]){
            Toast.makeText(this, "Berhasil Login", Toast.LENGTH_SHORT).show()
            sh.setStatusLogin(true)
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        } else {
            Toast.makeText(this, "Invalid Username/Password", Toast.LENGTH_SHORT).show()
        }
    }
}