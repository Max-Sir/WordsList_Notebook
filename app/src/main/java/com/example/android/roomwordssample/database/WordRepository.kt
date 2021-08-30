
package com.example.android.roomwordssample.database

import com.example.android.roomwordssample.database.WordDao
import com.example.android.roomwordssample.database.entity.Word
import kotlinx.coroutines.flow.Flow

class WordRepository(private val wordDao: WordDao) {

    // Room executes all queries on a separate thread.
    // Observed Flow will notify the observer when the data has changed.
    val allWords: Flow<List<Word>> = wordDao.getAlphabetizedWords()

    // By default Room runs suspend queries off the main thread, therefore, we don't need to
    // implement anything else to ensure we're not doing long running database work
    // off the main thread.
    suspend fun insert(word: Word) {
        wordDao.insert(word)
    }

    suspend fun clear(){
        wordDao.deleteAll()
    }

}
