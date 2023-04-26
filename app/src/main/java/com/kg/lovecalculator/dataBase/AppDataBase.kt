package com.kg.lovecalculator.dataBase

import androidx.room.Database
import androidx.room.RoomDatabase
import com.kg.lovecalculator.simpleModels.Love

@Database(entities = [Love::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun loveDao(): LoveDao
}