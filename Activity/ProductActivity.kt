package com.example.furniturekart.Activity

import android.annotation.SuppressLint
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.graphics.Paint
import android.net.ConnectivityManager
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.furniturekart.Adapter.ProductAdapter
import com.example.furniturekart.Client.ApiClient
import com.example.furniturekart.Interface.ApiInterface
import com.example.furniturekart.Model.ProductDetailModel
import com.example.furniturekart.R
import com.example.furniturekart.databinding.ActivityProductBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ProductActivity : AppCompatActivity() {

    private lateinit var binding: ActivityProductBinding
    lateinit var apiinterface: ApiInterface
    lateinit var call: Call<List<ProductDetailModel>>
    lateinit var list:MutableList<ProductDetailModel>

    private var broadcastReceiver: BroadcastReceiver = object : BroadcastReceiver() {
        override fun onReceive(context: Context, intent: Intent) {
            val notConnected = intent.getBooleanExtra(ConnectivityManager.EXTRA_NO_CONNECTIVITY, false)
            if (notConnected)
            {
                disconnected()
            }
            else
            {
                connected()
            }
        }
    }

    @SuppressLint("ResourceType")
    private fun disconnected()
    {
        Toast.makeText(applicationContext,"not Connected", Toast.LENGTH_LONG).show()

        val inflatLayout = findViewById<View>(R.id.networkError)
        inflatLayout.visibility = View.VISIBLE
        binding.producterror.visibility = View.GONE
    }

    private fun connected()
    {
        // Toast.makeText(applicationContext,"Connected",Toast.LENGTH_LONG).show()
        val inflatLayout = findViewById<View>(R.id.networkError)
        inflatLayout.visibility = View.GONE
        binding.producterror.visibility = View.VISIBLE
    }


    @SuppressLint("NotifyDataSetChanged")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityProductBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)


        setSupportActionBar(binding.toolbar)
        //supportActionBar!!.setTitle("")

        apiinterface=  ApiClient.getapiclient().create(ApiInterface::class.java)
        list = ArrayList()

        var layoutManager: RecyclerView.LayoutManager = LinearLayoutManager(this)
        binding.recycler.layoutManager=layoutManager

        var i = intent
        var id = i.getIntExtra("mypos",101)

        var call:Call<List<ProductDetailModel>> = apiinterface.ProductDetailsviewdata(id)
        call.enqueue(object: Callback<List<ProductDetailModel>>
        {
            override fun onResponse(call: Call<List<ProductDetailModel>>, response: Response<List<ProductDetailModel>>) {
                list = response.body() as MutableList<ProductDetailModel>

                var cadapter = ProductAdapter(applicationContext,list)
                binding.recycler.adapter=cadapter




               // cadapter.notifyDataSetChanged()

             //   binding.recycler.adapter!!.notifyDataSetChanged()
            }
            override fun onFailure(call: Call<List<ProductDetailModel>>, t: Throwable) {
            }
        })


    }
    override fun onStart() {
        super.onStart()
        registerReceiver(broadcastReceiver, IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION))
    }
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.cart_option,menu)
        return super.onCreateOptionsMenu(menu)
    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        return super.onOptionsItemSelected(item)
    }


}