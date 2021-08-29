package com.coherentsolutions.max.sir.androidintro.myapplication.light.wordslist.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

typealias WordLine=String

@Entity(tableName = "word_table")
data class Word(
    @PrimaryKey(autoGenerate = true)
    var wordId:Long=0L,
    @ColumnInfo(name = "word")
    val word:WordLine?
)
