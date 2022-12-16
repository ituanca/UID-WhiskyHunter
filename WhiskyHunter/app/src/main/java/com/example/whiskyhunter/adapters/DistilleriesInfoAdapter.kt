package com.example.whiskyhunter.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView
import com.example.whiskyhunter.R
import com.example.whiskyhunter.models.DistilleriesInfo
import retrofit2.Callback

class DistilleriesInfoAdapter(context: Callback<List<DistilleriesInfo>>, resource:Int, objects:ArrayList<DistilleriesInfo>):
    ArrayAdapter<DistilleriesInfo>(context, resource, objects)  {
    private val inflater: LayoutInflater =
        context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val rowView = convertView ?: inflater.inflate(R.layout.distilleries_info_item, parent, false)
        rowView.findViewById<TextView>(R.id.distilleries_info_item_title).text = getItem(position)!!.name
        return rowView
    }
}