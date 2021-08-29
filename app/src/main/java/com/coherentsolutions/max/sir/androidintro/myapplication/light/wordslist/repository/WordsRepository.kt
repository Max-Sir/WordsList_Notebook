package com.coherentsolutions.max.sir.androidintro.myapplication.light.wordslist.repository

import android.app.Application
import androidx.lifecycle.LiveData
import com.coherentsolutions.max.sir.androidintro.myapplication.light.wordslist.dao.WordListDao
import com.coherentsolutions.max.sir.androidintro.myapplication.light.wordslist.database.WordsDatabase
import com.coherentsolutions.max.sir.androidintro.myapplication.light.wordslist.entity.Word
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext


class WordsRepository(application: Application?) {
    private var mWordDao: WordListDao? = null



    // Note that in order to unit test the WordRepository, you have to remove the Application
    // dependency. This adds complexity and much more code, and this sample is not about testing.
    // See the BasicSample in the android-architecture-components repository at
    // https://github.com/googlesamples
    init {
        val db: WordsDatabase = WordsDatabase.getInstance(application!!)
        mWordDao = db.wordListDao
    }

    // Room executes all queries on a separate thread.
    // Observed LiveData will notify the observer when the data has changed.
    suspend fun getAllWords(): LiveData<List<Word>> {
        return withContext(Dispatchers.IO){
            mWordDao?.getSortedWordsList()!!
        }
    }

    suspend fun clearAll(){
        withContext(Dispatchers.IO){
            mWordDao?.clear()
        }
    }

    // You must call this on a non-UI thread or your app will throw an exception. Room ensures
    // that you're not doing any long running operations on the main thread, blocking the UI.
    suspend fun insert(word: Word) {
        withContext(Dispatchers.IO) {
            mWordDao?.insert(word)
        }
    }
}