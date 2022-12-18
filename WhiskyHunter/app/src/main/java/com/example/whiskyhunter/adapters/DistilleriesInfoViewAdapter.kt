package com.example.whiskyhunter.adapters

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.whiskyhunter.DistilleriesInfoItemDesign
import com.example.whiskyhunter.R
import com.example.whiskyhunter.models.DistilleriesInfo
import com.example.whiskyhunter.models.DistilleriesInfoViewHolder


class DistilleriesInfoViewAdapter(
    private val context: Context,
    private val dataSource: ArrayList<DistilleriesInfo>
    ) : RecyclerView.Adapter<DistilleriesInfoViewHolder>() {

    private val inflater: LayoutInflater =
        context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
    private var designType = DistilleriesInfoItemDesign.ALIGN_LEFT

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DistilleriesInfoViewHolder {
        val view = inflater.inflate(viewType, parent, false)
        return DistilleriesInfoViewHolder(view, this)
    }

    override fun onBindViewHolder(holder: DistilleriesInfoViewHolder, position: Int) {
        holder.bindData(dataSource[position])
    }

    override fun getItemViewType(position: Int): Int {
        return when (designType) {
            DistilleriesInfoItemDesign.ALIGN_LEFT -> {
                R.layout.distilleries_info_item_align_left
            }
            DistilleriesInfoItemDesign.ALIGN_RIGHT -> {
                R.layout.distilleries_info_item_align_right
            }
            DistilleriesInfoItemDesign.ALIGN_CENTER -> {
                R.layout.distilleries_info_item_align_center
            }
            else -> {
                R.layout.distilleries_info_item_align_left
            }
        }
    }

    override fun getItemCount(): Int {
        return dataSource.size
    }

    @SuppressLint("NotifyDataSetChanged")
    fun addItem(distilleriesInfo: DistilleriesInfo) {
        dataSource.add(0,distilleriesInfo)
        notifyDataSetChanged()
    }

    @SuppressLint("NotifyDataSetChanged")
    fun setDesignType(design: DistilleriesInfoItemDesign) {
        designType = design
        notifyDataSetChanged()
    }
}