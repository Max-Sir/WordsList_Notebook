package com.example.android.roomwordssample.recycleview

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.android.roomwordssample.R
import com.example.android.roomwordssample.database.entity.Word
import com.example.android.roomwordssample.recycleview.WordListAdapter.WordViewHolder
import java.lang.Math.abs
import kotlin.random.Random

class WordListAdapter : ListAdapter<Word, WordViewHolder>(WORDS_COMPARATOR) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WordViewHolder {
        return WordViewHolder.create(parent)
    }

    override fun onBindViewHolder(holder: WordViewHolder, position: Int) {
        val current = getItem(position)
        holder.bind(current.word)
    }

    class WordViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val wordItemView: TextView = itemView.findViewById(R.id.textView)

        fun bind(text: String?) {
            wordItemView.text = text
        }

        companion object {
            fun create(parent: ViewGroup): WordViewHolder {
                val view: View = LayoutInflater.from(parent.context)
                    .inflate(R.layout.recyclerview_item, parent, false)
                view.setOnClickListener {
                    view.setBackgroundColor(Color.parseColor(getRandomColor()))
                }
                return WordViewHolder(view)
            }

            fun getRandomColor() = '#' + createRandomIntegerString(0x08)
        }
    }

    companion object {
        fun createRandomIntegerString(length: Int): String {
            val stringBuilder = StringBuilder()
            val hexsInterpolation = "0123456789abcdef"
            for (i in 0 until length) {
                val tmpInt = Random.nextInt()
                val x = kotlin.math.abs(tmpInt) % 16
                stringBuilder.append(hexsInterpolation[x])
            }
            return stringBuilder.toString()
        }

        private val WORDS_COMPARATOR = object : DiffUtil.ItemCallback<Word>() {
            override fun areItemsTheSame(oldItem: Word, newItem: Word): Boolean {
                return oldItem === newItem
            }

            override fun areContentsTheSame(oldItem: Word, newItem: Word): Boolean {
                return oldItem.word == newItem.word
            }
        }
    }
}
