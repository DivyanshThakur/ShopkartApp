package com.example.ecommerceappkotlin

import android.app.AlertDialog
import android.app.DialogFragment
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.ImageButton
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import kotlinx.android.synthetic.main.fragment_amount.*

class AmountFragment : DialogFragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        var fragmentView = inflater.inflate(R.layout.fragment_amount, container, false)

        var edtEnterAmount = fragmentView.findViewById<EditText>(R.id.edtEnterAmount)
        var btnAddToCart = fragmentView.findViewById<ImageButton>(R.id.btnAddToCart)

        btnAddToCart.setOnClickListener {

            var ptoUrl = "http://192.168.43.186/OnlineStoreApp/insert_into_temporary_order.php?email=${Person.email}&product_id=${Person.addToCartProductID}&amount=${edtEnterAmount.text.toString()}"
            var requestQ = Volley.newRequestQueue(activity)
            var stringRequest = StringRequest(Request.Method.GET, ptoUrl, Response.Listener {
                response ->

                var intent = Intent(activity, CartProductsActivity::class.java)
                startActivity(intent)

            }, Response.ErrorListener { error ->

                val dialog = AlertDialog.Builder(activity)
                dialog.setTitle("Error").setMessage(error.message).create().show()
            })

            requestQ.add(stringRequest)

        }


        return fragmentView
    }
}