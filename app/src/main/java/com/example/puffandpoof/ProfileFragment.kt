package com.example.puffandpoof

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.example.puffandpoof.databinding.FragmentProfileBinding

class ProfileFragment : Fragment() {
    private lateinit var usernameTextView: TextView
    private lateinit var phoneNumberTextView: TextView
    var binding: FragmentProfileBinding ?= null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentProfileBinding.inflate(inflater,container,false)

        val usernameProfileText = binding!!.usernameProfileText
        usernameProfileText.setText(Variables.usernamePass)

        val phonenumberProfileText = binding!!.phonenumberProfileText
        phonenumberProfileText.setText(Variables.phonenumPass)

        val logoutButton = binding!!.logoutButton
        logoutButton.setOnClickListener{
            val intent = Intent(requireContext(), MainActivity::class.java)
            startActivity(intent)
        }


        return binding!!.root
    }
}


