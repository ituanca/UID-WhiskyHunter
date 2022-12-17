package com.example.whiskyhunter

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ListView
import com.example.whiskyhunter.adapters.DistilleriesInfoAdapter
import com.example.whiskyhunter.apis.DistilleriesInfoAPI
import com.example.whiskyhunter.models.DistilleriesInfo
import com.example.whiskyhunter.services.DistilleriesInfoService
import retrofit2.Call
import retrofit2.Response

class MainActivity : AppCompatActivity(), View.OnClickListener {

    lateinit var adapter: DistilleriesInfoAdapter
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
                        val listView = findViewById<ListView>(R.id.distilleries_info_list)
                        distilleriesInfoArrayList = DistilleriesInfoService().getDistilleriesInfoList(response.body())
                        adapter = DistilleriesInfoAdapter(this@MainActivity, R.layout.distilleries_info_item_align_left, distilleriesInfoArrayList)
                        listView.adapter = adapter
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
        if(item.itemId==R.id.align_left){

        }else if(item.itemId==R.id.align_right){

        } else if(item.itemId == R.id.align_center){

        } else if(item.itemId == R.id.mixed){

        }
        return super.onOptionsItemSelected(item)
    }

    override fun onClick(p0: View?) {
        val countryName = findViewById<EditText>(R.id.country_name).text.toString()
        val localDistilleriesInfoArrayList = distilleriesInfoArrayList
        val listView = findViewById<ListView>(R.id.distilleries_info_list)
        adapter = if(countryName.isEmpty()){
            DistilleriesInfoAdapter(this@MainActivity, R.layout.distilleries_info_item_align_left, localDistilleriesInfoArrayList)
        }else{
            val distilleriesInfoArrayListByCountry = DistilleriesInfoService().getDistilleriesInfoListByCountry(localDistilleriesInfoArrayList, countryName)
            DistilleriesInfoAdapter(this@MainActivity, R.layout.distilleries_info_item_align_left, distilleriesInfoArrayListByCountry)
        }
        listView.adapter = adapter
    }

}