package com.example.puffandpoof

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.puffandpoof.R.id.cart_list_recycler_view2
import com.example.puffandpoof.databinding.FragmentTransactionBinding
import java.util.*
import kotlin.collections.HashSet


// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"


/**
 * A simple [Fragment] subclass.
 * Use the [TransactionFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class TransactionFragment : Fragment() {
    private var generatedIds = HashSet<String>()
    private lateinit var cartAdapter: CartAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_transaction, container, false)
        cartAdapter = CartAdapter(getCurrentUserTransactions())
        val cartRecyclerView: RecyclerView = view.findViewById(R.id.cart_list_recycler_view2)
        cartRecyclerView.layoutManager = LinearLayoutManager(requireContext())
        cartRecyclerView.adapter = cartAdapter

        val checkoutButton: Button = view.findViewById(R.id.coButton)
        checkoutButton.setOnClickListener {
            performCheckout()
        }

        return view
    }

    private fun getCurrentUserTransactions(): List<Cart> {
        val currentUser = Variables.arraylist.find { it.username == Variables.usernamePass }
        return currentUser?.transactions ?: emptyList()
    }

    private fun performCheckout() {
        val currentUser = Variables.arraylist.find { it.username == Variables.usernamePass }
        if (currentUser == null) {
            Toast.makeText(requireContext(), "User not found", Toast.LENGTH_SHORT).show()
            return
        }

        if (currentUser.transactions.isEmpty()) {
            Toast.makeText(requireContext(), "Cart is empty. Cannot proceed with checkout.", Toast.LENGTH_SHORT).show()
            return
        }

        val checkoutId = generateUniqueCheckoutId()

        // Update all data in CartData with the checkoutId
        currentUser.transactions.forEach { it.checkoutId = checkoutId }

        // Clear the cart after checkout
        currentUser.transactions.clear()
        cartAdapter.notifyDataSetChanged()

        Variables.resetCart()

        // Show a message or navigate to a confirmation page
        Toast.makeText(requireContext(), "Checkout successful with ID: $checkoutId", Toast.LENGTH_SHORT).show()
    }

    private fun generateUniqueCheckoutId(): String {
        var id: String
        do {
            id = UUID.randomUUID().toString()
        } while (generatedIds.contains(id))
        generatedIds.add(id)
        return id
    }

    // Inside TransactionFragment
    fun resetCart() {
        Variables.cartList.clear()
        cartAdapter.notifyDataSetChanged()
    }

}
