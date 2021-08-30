package com.example.android.roomwordssample

import androidx.lifecycle.*
import com.example.android.roomwordssample.database.WordRepository
import com.example.android.roomwordssample.database.entity.Word
import kotlinx.coroutines.launch

/**
 * View Model to keep a reference to the word repository and
 * an up-to-date list of all words.
 */

class WordViewModel(private val repository: WordRepository) : ViewModel() {

    // Using LiveData and caching what allWords returns has several benefits:
    // - We can put an observer on the data (instead of polling for changes) and only update the
    //   the UI when the data actually changes.
    // - Repository is completely separated from the UI through the ViewModel.
    val allWords: LiveData<List<Word>> = repository.allWords.asLiveData()



    init {
    }


    /**
     * Launching a new coroutine to insert the data in a non-blocking way
     */
    fun insert(word: Word) = viewModelScope.launch {
        repository.insert(word)
    }

    fun clear() = viewModelScope.launch {
        repository.clear()
    }

    fun onClearEventStarted(){
    }

    fun onCleareEventCompleted() {
    }

    override fun onCleared() {
        super.onCleared()
    }
}

