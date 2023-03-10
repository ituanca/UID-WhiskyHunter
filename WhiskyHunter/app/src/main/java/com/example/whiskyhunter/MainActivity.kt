package com.example.whiskyhunter

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Button
import android.widget.EditText
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.whiskyhunter.adapters.DistilleriesInfoViewAdapter
import com.example.whiskyhunter.apis.DistilleriesInfoAPI
import com.example.whiskyhunter.models.DistilleriesInfo
import com.example.whiskyhunter.services.DistilleriesInfoService
import retrofit2.Call
import retrofit2.Response

class MainActivity : AppCompatActivity(), View.OnClickListener {

    lateinit var adapter: DistilleriesInfoViewAdapter
    lateinit var distilleriesInfoArrayList : ArrayList<DistilleriesInfo>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val distilleriesInfoAPI = DistilleriesInfoAPI.createApi()
        distilleriesInfoAPI.getDistilleriesInfo(DistilleriesInfoSingleton.token).enqueue(
            object: retrofit2.Callback<List<DistilleriesInfo>>{
                override fun onResponse(
                    call: Call<List<DistilleriesInfo>>,
                    response: Response<List<DistilleriesInfo>>
                ) {
                    DistilleriesInfoSingleton.token = ""
                    Log.e("SUCCESSFUL", response.body().toString())
                    if(response.isSuccessful){
                        distilleriesInfoArrayList = DistilleriesInfoService().getDistilleriesInfoList(response.body())
                        adapter = DistilleriesInfoViewAdapter(this@MainActivity, distilleriesInfoArrayList)
                        val recyclerView = findViewById<RecyclerView>(R.id.distilleries_info_list_recycler_view)
                        recyclerView.layoutManager = LinearLayoutManager(this@MainActivity)
                        recyclerView.adapter = adapter
                    }
                }
                override fun onFailure(call: Call<List<DistilleriesInfo>>, t: Throwable) {
                    Log.e("FAIL", t.toString())
                }
            }
        )
        val searchButton = findViewById<Button>(R.id.search)
        searchButton.setOnClickListener(this)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.options_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.align_left -> {
                adapter.setDesignType(DistilleriesInfoItemDesign.ALIGN_LEFT)
            }
            R.id.align_right -> {
                adapter.setDesignType(DistilleriesInfoItemDesign.ALIGN_RIGHT)
            }
            R.id.align_center -> {
                adapter.setDesignType(DistilleriesInfoItemDesign.ALIGN_CENTER)
            }
            R.id.mixed -> {
                adapter.setDesignType(DistilleriesInfoItemDesign.ALIGN_MIXED)
            }
            else -> {
                adapter.setDesignType(DistilleriesInfoItemDesign.ALIGN_LEFT)
            }
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onClick(p0: View?) {
        val countryName = findViewById<EditText>(R.id.country_name).text.toString()
        val recyclerView = findViewById<RecyclerView>(R.id.distilleries_info_list_recycler_view)
        adapter = if(countryName.isEmpty()){
            DistilleriesInfoViewAdapter(this@MainActivity, distilleriesInfoArrayList)
        }else{
            val distilleriesInfoArrayListByCountry =
                DistilleriesInfoService().getDistilleriesInfoListByCountry(distilleriesInfoArrayList, countryName)
            DistilleriesInfoViewAdapter(this@MainActivity, distilleriesInfoArrayListByCountry)
        }
        recyclerView.adapter = adapter
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (requestCode == 0) {
            adapter.onActivityResult(requestCode, resultCode, data)
        }
        super.onActivityResult(requestCode, resultCode, data)
    }

}