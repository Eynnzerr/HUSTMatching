package com.example.hustmatching.response

import com.google.gson.annotations.SerializedName
import java.util.*

data class DailyResopnse(val status: String, val result: Result) {
    data class Result(val daily: Daily)
    data class Daily(
        val temperature: List<Temperature>)

    data class Temperature(val max: Float, val min: Float)
//    data class Skycon(val value:String , val date: Date)
//    data class LifeIndex(
//        val coldRisk: List<LifeDescription>, val ultraviolet: List<LifeDescription>)
//
//    data class LifeDescription(val desc: String,val index:String)
}
