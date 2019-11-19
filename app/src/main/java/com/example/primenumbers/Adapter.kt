package com.example.primenumbers

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.prime_number.view.*

class Adapter(val numbers: MutableList<Long>, private val callback: (Int) -> Unit) : RecyclerView.Adapter<Adapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder = with(parent){
        return ViewHolder(LayoutInflater.from(context).inflate(R.layout.prime_number, this, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) = holder.bind(numbers[position])

    override fun getItemCount(): Int = numbers.size

    fun addItem(item: Long?){
        if (item != null) {
            numbers.add(item)
            super.notifyItemInserted(itemCount)
            callback(itemCount)
        }
    }

    inner class ViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView) {
        fun bind(number: Long) = with(itemView) {
            prime.text = number.toString()
        }
    }
}