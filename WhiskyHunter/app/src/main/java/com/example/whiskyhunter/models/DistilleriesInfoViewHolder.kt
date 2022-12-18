package com.example.whiskyhunter.models

import android.annotation.SuppressLint
import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.whiskyhunter.R
import com.example.whiskyhunter.adapters.DistilleriesInfoViewAdapter

class DistilleriesInfoViewHolder(private val view: View, private val adapter: DistilleriesInfoViewAdapter) :
    RecyclerView.ViewHolder(view),
    View.OnClickListener {

    private lateinit var name: TextView
    private lateinit var country: TextView
    private lateinit var whiskybase_whiskies: TextView
    private lateinit var whiskybase_votes: TextView
    private lateinit var whiskybase_rating: TextView
    private lateinit var item: DistilleriesInfo

    init {
        name = view.findViewById(R.id.distilleries_info_item_name)
        country = view.findViewById(R.id.distilleries_info_item_country)
        whiskybase_whiskies = view.findViewById(R.id.distilleries_info_item_whiskybase_whiskies)
        whiskybase_votes = view.findViewById(R.id.distilleries_info_item_whiskybase_votes)
        whiskybase_rating = view.findViewById(R.id.distilleries_info_item_whiskybase_rating)
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
        adapter.notifyDataSetChanged()
    }

}