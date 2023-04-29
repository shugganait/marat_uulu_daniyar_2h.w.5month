package com.kg.lovecalculator

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.kg.lovecalculator.remote.LoveModel

class CalcLoveViewModel: ViewModel() {
    private val repository = Repository()

    fun liveLove(fName: String, sName: String): LiveData<LoveModel>{
        return repository.getLove(fName,sName)
    }
}