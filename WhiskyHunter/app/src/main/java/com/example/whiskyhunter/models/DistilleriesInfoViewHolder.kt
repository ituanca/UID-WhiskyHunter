package com.example.whiskyhunter.models

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Intent
import android.util.Log
import android.view.View
import android.widget.AdapterView
import android.widget.PopupMenu
import android.widget.TextView
import androidx.activity.result.contract.ActivityResultContracts
import androidx.recyclerview.widget.RecyclerView
import com.example.whiskyhunter.AddDistilleryActivity
import com.example.whiskyhunter.OnIntentReceived

import com.example.whiskyhunter.R
import com.example.whiskyhunter.adapters.DistilleriesInfoViewAdapter

class DistilleriesInfoViewHolder(private val view: View, private val adapter: DistilleriesInfoViewAdapter) :
    RecyclerView.ViewHolder(view), View.OnClickListener {

    private var name: TextView = view.findViewById(R.id.distilleries_info_item_name)
    private var country: TextView = view.findViewById(R.id.distilleries_info_item_country)
    private var whiskybase_whiskies: TextView = view.findViewById(R.id.distilleries_info_item_whiskybase_whiskies)
    private var whiskybase_votes: TextView = view.findViewById(R.id.distilleries_info_item_whiskybase_votes)
    private var whiskybase_rating: TextView = view.findViewById(R.id.distilleries_info_item_whiskybase_rating)
    private lateinit var item: DistilleriesInfo

    init {
        view.setOnClickListener(this)
    }

    fun bindData(newItem: DistilleriesInfo) {
        name.text = newItem.name
        country.text = newItem.country
        whiskybase_whiskies.text = newItem.whiskybase_whiskies
        whiskybase_votes.text = newItem.whiskybase_votes
        whiskybase_rating.text = newItem.whiskybase_rating
        item = newItem
    }

    @SuppressLint("NotifyDataSetChanged")
    override fun onClick(p0: View?) {
        val popupMenu = PopupMenu(view.context, p0)
        popupMenu.inflate(R.menu.contextual_menu)
        popupMenu.setOnMenuItemClickListener { itemLocal ->
            when(itemLocal.itemId) {
                R.id.insert_at_the_beginning->{
                    val intent = Intent(view.context, AddDistilleryActivity::class.java)
                    intent.putExtra("placeOfInsertion", "at_the_beginning")
                    (view.context as Activity).startActivityForResult(intent, 0)
                }
                R.id.insert_at_the_end->{
                    val intent = Intent(view.context, AddDistilleryActivity::class.java)
                    intent.putExtra("placeOfInsertion", "at_the_end")
                    (view.context as Activity).startActivityForResult(intent, 0)
                }
            }
            true
        }
        popupMenu.show()
        adapter.notifyDataSetChanged()
    }

}
