package com.example.puffandpoof

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.Settings.Global
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.example.puffandpoof.databinding.ActivityMainBinding


// Assuming your Database class is in the same package or imported appropriately

class MainActivity : AppCompatActivity() {
    private lateinit var etUsername: EditText
    private lateinit var etPassword: EditText
    private lateinit var RegisterButton: Button
    private lateinit var LoginButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        var binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val phonenum = intent.getStringExtra("phonenumber")
        etUsername = binding.etUsername
        etPassword = binding.etPassword
        RegisterButton = binding.RegisterButton
        LoginButton = binding.LoginButton


        val database = Variables // Create an instance of the Database class

        RegisterButton.setOnClickListener{
            val move = Intent(this, RegisterPage::class.java)
            startActivity(move)
        }



        LoginButton.setOnClickListener {
            val username = etUsername.text.toString()
            val password = etPassword.text.toString()

            val user = Variables.arraylist.find { it.username == username } // Temukan pengguna berdasarkan nama pengguna
            if (user == null) {
                Toast.makeText(this, "Username not found. Please register first!", Toast.LENGTH_SHORT).show()
            } else if (user.password != password) {
                Toast.makeText(this, "Incorrect password!", Toast.LENGTH_SHORT).show()
            } else {
                var ind = 0
                val intent = Intent(this, HomePage::class.java)
                Variables.usernamePass = username
                Variables.phonenumPass = user.phonenum
                Variables.passwordPass = password

                // Reset keranjang belanja untuk pengguna saat ini
                Variables.cartList.clear()

                startActivity(intent)
            }
        }
    }
}
