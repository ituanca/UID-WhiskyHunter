package com.example.whiskyhunter.services

import com.example.whiskyhunter.models.DistilleriesInfo

class DistilleriesInfoService {
    fun getDistilleriesInfoList(list: List<DistilleriesInfo>?): ArrayList<DistilleriesInfo>{
        val distilleriesInfoArrayList = ArrayList<DistilleriesInfo>();
        if (list != null) {
            for (item in list){
                distilleriesInfoArrayList.add(item)
            }
        }
        return distilleriesInfoArrayList;
    }

    fun getDistilleriesInfoListByCountry(list: ArrayList<DistilleriesInfo>, country_name: String): ArrayList<DistilleriesInfo>{
        val distilleriesInfoArrayListByCountry = ArrayList<DistilleriesInfo>();
        for (item in list){
            if(item.country == country_name){
                distilleriesInfoArrayListByCountry.add(item)
            }
        }
        return distilleriesInfoArrayListByCountry;
    }
}