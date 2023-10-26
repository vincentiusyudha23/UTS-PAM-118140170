package com.example.utspam2

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Patterns
import android.widget.Toast
import com.example.utspam2.databinding.ActivityRegisterBinding
import com.example.utspam2.network.SharedPreferenManage


class RegisterActivity : AppCompatActivity() {

    private lateinit var binding: ActivityRegisterBinding
    private lateinit var sh: SharedPreferenManage

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.hide()

        sh = SharedPreferenManage(this)


        binding.btnRegister.setOnClickListener{
            val userName = binding.rgUsername.text.toString()
            val passWord = binding.rgPassword.text.toString()
            val githubName = binding.rgGithub.text.toString()
            val nim = binding.rgNim.text.toString()
            val email = binding.rgEmail.text.toString()

            if (userName.isEmpty()){
               binding.rgUsername.error = "UserName harus diisi!"
                binding.rgUsername.requestFocus()
                return@setOnClickListener
            }

            if (passWord.isEmpty()){
                binding.rgPassword.error = "Password harus diisi!"
                binding.rgPassword.requestFocus()
                return@setOnClickListener
            }

            if (githubName.isEmpty()){
                binding.rgGithub.error = "Username Github harus diisi!"
                binding.rgGithub.requestFocus()
                return@setOnClickListener
            }

            if (nim.isEmpty()){
                binding.rgNim.error = "Nim harus diisi!"
                binding.rgNim.requestFocus()
                return@setOnClickListener
            }

            if (email.isEmpty()){
                binding.rgEmail.error = "Email harus diisi!"
                binding.rgEmail.requestFocus()
                return@setOnClickListener
            }

            if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
                binding.rgEmail.error = "Email Tidak Valid"
                binding.rgEmail.requestFocus()
                return@setOnClickListener
            }

            if(passWord.length < 8){
                binding.rgPassword.error = "Password Minimal 8 Karakter"
                binding.rgPassword.requestFocus()
                return@setOnClickListener
            }
            if(
                !userName.isNullOrEmpty()&&
                !passWord.isNullOrEmpty()&&
                !githubName.isNullOrEmpty()&&
                !nim.isNullOrEmpty()&&
                !email.isNullOrEmpty()
                ){
                sh.setAccount(userName,passWord,githubName,nim,email)
                startActivity(Intent(this, LoginActivity::class.java))
                finish()
            }
        }

        binding.btRgSignin.setOnClickListener{
            startActivity(Intent(this, LoginActivity::class.java))
            finish()
        }
    }

}