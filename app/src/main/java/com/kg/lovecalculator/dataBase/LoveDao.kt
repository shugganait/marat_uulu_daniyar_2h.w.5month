package com.kg.lovecalculator.dataBase

import androidx.room.*
import com.kg.lovecalculator.simpleModels.Love

@Dao
interface LoveDao {

    @Insert
    fun insert(love: Love)

    @Query("SELECT * FROM Love ORDER BY id DESC")
    fun getAll(): List<Love>

    @Query("SELECT * FROM Love ORDER BY firstName ")
    fun getFirstName(): List<Love>

}