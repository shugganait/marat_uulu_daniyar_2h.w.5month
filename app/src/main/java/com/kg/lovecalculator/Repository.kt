package com.kg.lovecalculator

import androidx.lifecycle.MutableLiveData
import com.kg.lovecalculator.remote.LoveModel
import com.kg.lovecalculator.remote.RetrofitService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class Repository {
    fun getLove(fName:String, sName:String): MutableLiveData<LoveModel> {
        val liveLove = MutableLiveData<LoveModel>()

        RetrofitService().api.percentageName(fName, sName).enqueue(object : Callback<LoveModel> {
            override fun onResponse(call: Call<LoveModel>, response: Response<LoveModel>) {
                if(response.isSuccessful){
                    liveLove.postValue(response.body())
                }
            }

            override fun onFailure(call: Call<LoveModel>, t: Throwable) {

            }

        })
        return liveLove
    }
}