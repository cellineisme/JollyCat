package com.example.puffandpoof

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.example.puffandpoof.Variables.arraylist
import com.example.puffandpoof.databinding.ActivityRegisterPageBinding



class RegisterPage : AppCompatActivity() {

    private lateinit var binding: ActivityRegisterPageBinding
    private lateinit var etUsername: EditText
    private lateinit var etPassword: EditText
    private lateinit var PhoneET: EditText
    private lateinit var RegisterButton: Button
    private lateinit var LoginButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityRegisterPageBinding.inflate(layoutInflater)
        setContentView(binding.root)

        etUsername = binding.etUsername
        etPassword = binding.etPassword
        PhoneET = binding.phonenumberEditText
        RegisterButton = binding.RegisterButton
        LoginButton = binding.LoginButton

        LoginButton.setOnClickListener {
            val move = Intent(this, MainActivity::class.java)
            startActivity(move)
        }

        RegisterButton.setOnClickListener {
            val username = etUsername.text.toString()
            val password = etPassword.text.toString()
            val phonenum = PhoneET.text.toString()

            if (Variables.arraylist.any { it.username == username }) {
                Toast.makeText(this, "You have already registered!", Toast.LENGTH_SHORT).show()
            } else if (username.isEmpty() || password.isEmpty() || phonenum.isEmpty()) {
                Toast.makeText(this, "Please complete your data", Toast.LENGTH_SHORT).show()
            } else if (username.length < 8) {
                Toast.makeText(this, "Username must be at least 8 characters", Toast.LENGTH_SHORT).show()
            } else if (!username.matches("[a-zA-Z0-9]+".toRegex())) {
                Toast.makeText(this, "Username must consist of alphanumeric characters only", Toast.LENGTH_SHORT).show()
            } else if (password.length < 5) {
                Toast.makeText(this, "Password must be at least 5 characters", Toast.LENGTH_SHORT).show()
            } else if (!password.matches(".*[a-zA-Z].*".toRegex())) {
                Toast.makeText(this, "Password must contain at least one letter", Toast.LENGTH_SHORT).show()
            } else if (!password.matches(".*\\d.*".toRegex())) {
                Toast.makeText(this, "Password must contain at least one number", Toast.LENGTH_SHORT).show()
            } else if (!password.matches(".*[@#\$%^&].*".toRegex())) {
                Toast.makeText(this, "Password must contain at least one symbol", Toast.LENGTH_SHORT).show()
            } else if (phonenum.length < 8 || phonenum.length > 20) {
                Toast.makeText(this, "Phone number must be between 8-20 characters", Toast.LENGTH_SHORT).show()
            } else if (!phonenum.startsWith("0") && !phonenum.startsWith("+")) {
                Toast.makeText(this, "Phone number must start with '0' or '+'", Toast.LENGTH_SHORT).show()
            } else if (!phonenum.substring(2).matches("\\d+".toRegex())) {
                Toast.makeText(this, "Phone number must consist of digits after the first two characters", Toast.LENGTH_SHORT).show()
            } else {
                val move = Intent(this, RegisterPage::class.java)
                startActivity(move)
                val newUser = Variables.User(username, password, phonenum)
                arraylist.add(newUser)

                val transactionFragment = supportFragmentManager.findFragmentByTag("TransactionFragment") as? TransactionFragment
                transactionFragment?.resetCart()

                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
            }



        }
    }
}
