package com.coherentsolutions.max.sir.androidintro.myapplication.light.wordslist.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.coherentsolutions.max.sir.androidintro.myapplication.light.wordslist.dao.WordListDao
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors

@Database(entities = [WordListDao::class],version = 1,exportSchema = false)
abstract class WordsDatabase:RoomDatabase() {
    abstract val wordListDao:WordListDao
    ///

    companion object{
        @Volatile
        private var INSTANCE:WordsDatabase?=null
        fun getInstance(context:Context):WordsDatabase{
            synchronized(this){
                var instance= INSTANCE

                if(instance==null){
                    instance= Room.databaseBuilder(context.applicationContext,WordsDatabase::class.java,"words_database")
                        .fallbackToDestructiveMigration()
                        .build()
                    INSTANCE=instance
                }
                return instance
            }
        }
    }
}