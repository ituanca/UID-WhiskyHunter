package com.example.whiskyhunter.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView
import com.example.whiskyhunter.R
import com.example.whiskyhunter.models.DistilleriesInfo

class DistilleriesInfoAdapter(context:Context, resource:Int, objects:ArrayList<DistilleriesInfo>):
    ArrayAdapter<DistilleriesInfo>(context, resource, objects)  {
    private val inflater: LayoutInflater =
        context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val rowView = convertView ?: inflater.inflate(R.layout.distilleries_info_item_align_left, parent, false)
        rowView.findViewById<TextView>(R.id.distilleries_info_item_name).text = getItem(position)!!.name
        rowView.findViewById<TextView>(R.id.distilleries_info_item_country).text = getItem(position)!!.country
        rowView.findViewById<TextView>(R.id.distilleries_info_item_whiskybase_whiskies).text = getItem(position)!!.whiskybase_whiskies
        rowView.findViewById<TextView>(R.id.distilleries_info_item_whiskybase_votes).text = getItem(position)!!.whiskybase_votes
        rowView.findViewById<TextView>(R.id.distilleries_info_item_whiskybase_rating).text = getItem(position)!!.whiskybase_rating
        return rowView
    }
}