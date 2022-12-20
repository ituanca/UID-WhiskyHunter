package com.example.whiskyhunter.adapters

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.whiskyhunter.DistilleriesInfoItemDesign
import com.example.whiskyhunter.OnIntentReceived
import com.example.whiskyhunter.R
import com.example.whiskyhunter.models.DistilleriesInfo
import com.example.whiskyhunter.models.DistilleriesInfoViewHolder


class DistilleriesInfoViewAdapter(
    private val context: Context,
    private val dataSource: ArrayList<DistilleriesInfo>
    ) : RecyclerView.Adapter<DistilleriesInfoViewHolder>(), OnIntentReceived{

    private val inflater: LayoutInflater =
        context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
    private var designType = DistilleriesInfoItemDesign.ALIGN_LEFT

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DistilleriesInfoViewHolder {
        val view = inflater.inflate(viewType, parent, false)
        return DistilleriesInfoViewHolder(view, this)
    }

    override fun onBindViewHolder(holder: DistilleriesInfoViewHolder, position: Int) {
        val item = dataSource[position]
        holder.bindData(item)
    }

    override fun getItemViewType(position: Int): Int {
        when (designType) {
            DistilleriesInfoItemDesign.ALIGN_LEFT -> {
                return R.layout.distilleries_info_item_align_left
            }
            DistilleriesInfoItemDesign.ALIGN_RIGHT -> {
                return R.layout.distilleries_info_item_align_right
            }
            DistilleriesInfoItemDesign.ALIGN_CENTER -> {
                return R.layout.distilleries_info_item_align_center
            }
            DistilleriesInfoItemDesign.ALIGN_MIXED -> {
                return if(position % 3 == 0){
                    R.layout.distilleries_info_item_align_left
                }else if (position % 3 == 1){
                    R.layout.distilleries_info_item_align_right
                }else{
                    R.layout.distilleries_info_item_align_center
                }
            }
            else ->{
                return R.layout.distilleries_info_item_align_left
            }
        }
    }

    override fun getItemCount(): Int {
        return dataSource.size
    }

    @SuppressLint("NotifyDataSetChanged")
    fun addItemAtTheBeginning(item: DistilleriesInfo) {
        dataSource.add(0, item)
        notifyDataSetChanged()
    }

    @SuppressLint("NotifyDataSetChanged")
    fun addItemAtTheEnd(item: DistilleriesInfo) {
        dataSource.add(dataSource.size, item)
        notifyDataSetChanged()
    }

    @SuppressLint("NotifyDataSetChanged")
    fun setDesignType(design: DistilleriesInfoItemDesign) {
        designType = design
        notifyDataSetChanged()
    }

    @SuppressLint("NotifyDataSetChanged")
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (requestCode == 0) {
            if (resultCode == Activity.RESULT_OK) {
                //val addedDistilleries = data!!.getStringArrayListExtra("addedDistilleries")
                val addedDistillery = data!!.getSerializableExtra("addedDistillery")
                val placeOfInsertion = data.getStringExtra("placeOfInsertion")
                Log.e("RECEIVED", addedDistillery.toString())
                Log.e("place of insertion", placeOfInsertion.toString())
                if(addedDistillery != null){
                    if(placeOfInsertion == "at_the_beginning"){
                        addItemAtTheBeginning(addedDistillery as DistilleriesInfo)
                    }else if(placeOfInsertion == "at_the_end"){
                        addItemAtTheEnd(addedDistillery as DistilleriesInfo)
                    }
                    notifyDataSetChanged()
                }else{
                    Log.ERROR
                }
            }
        }
    }
}