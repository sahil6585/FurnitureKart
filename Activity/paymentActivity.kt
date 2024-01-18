package com.example.furniturekart.Activity

import android.content.Context
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.example.furniturekart.R
import com.example.furniturekart.databinding.ActivityPaymentBinding
import com.razorpay.Checkout
import com.razorpay.PaymentResultListener
import com.squareup.picasso.Picasso
import org.json.JSONException
import org.json.JSONObject

class paymentActivity : AppCompatActivity(), PaymentResultListener
{
    private lateinit var binding: ActivityPaymentBinding
    lateinit var sharedPreferences: SharedPreferences
    var price=0
    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        binding = ActivityPaymentBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        val i = intent
        val id = i.getIntExtra("id", 0)
        val name = i.getStringExtra("name")
      /*  val description = i.getStringExtra("description")*/
        price = i.getIntExtra("price",101)
        val image = i.getStringExtra("image")
        sharedPreferences = getSharedPreferences("USER", Context.MODE_PRIVATE)


        binding.giftName.text = name
       /* binding.giftDesc.text = description*/
        binding.Price.text = price.toString()
        Picasso.get().load(image).into(binding.imageView)

        binding.makePaymentBtn.setOnClickListener{

            var finaldata= price
            Log.d("topsprice",finaldata.toString())
            val pPrice = Integer.parseInt(price.toString())*100
            val checkout = Checkout()
            checkout.setKeyID("rzp_test_pJ8ElvmChXIyZC")
            /*rzp_test_KOyE4EKQ2KOwOS*/
            val obj = JSONObject()
            try {
                obj.put("name", name)
               // obj.put("description", "Test Payment")
                obj.put("theme.color", "")
                obj.put("currency", "INR")
              /*  obj.put("price",finaldata)
                obj.put("prefill.contact", sharedPreferences.getString("mob", ""))
                obj.put("prefill.email", "smjagani6585@gmail.com")
                checkout.open(this, obj) */
                obj.put("amount", pPrice)
                obj.put("prefill.contact", sharedPreferences.getString("mob", ""))
                obj.put("prefill.email", "jchirag2000@gmail.com")
                checkout.open(this, obj)

            } catch (e: JSONException) {
                e.printStackTrace()
            }


        }

    }

    override fun onPaymentSuccess(p0: String?) {
        Toast.makeText(this, "Payment Success $p0", Toast.LENGTH_SHORT).show()
    }

    override fun onPaymentError(p0: Int, p1: String?) {
        Toast.makeText(this, "Payment Failed ", Toast.LENGTH_SHORT).show()    }
}