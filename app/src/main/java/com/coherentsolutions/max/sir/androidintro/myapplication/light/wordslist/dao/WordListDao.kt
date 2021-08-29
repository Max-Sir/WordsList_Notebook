package com.coherentsolutions.max.sir.androidintro.myapplication.light.wordslist.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.coherentsolutions.max.sir.androidintro.myapplication.light.wordslist.entity.Word
import com.coherentsolutions.max.sir.androidintro.myapplication.light.wordslist.entity.WordLine

@Dao
interface WordListDao {
    @Insert
    fun insert(word:Word)

    @Query("DELETE FROM word_table")
    fun clear()

    @Query("SELECT * FROM word_table ORDER BY word ASC")
    fun getSortedWordsList():LiveData<List<Word>>
}