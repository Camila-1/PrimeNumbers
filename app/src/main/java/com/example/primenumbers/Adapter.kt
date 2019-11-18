package com.example.primenumbers

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.view.menu.MenuView
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.prime_number.view.*

class Adapter(private val numbers : List<Long>) : RecyclerView.Adapter<Adapter.ViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder = with(parent){
        return ViewHolder(LayoutInflater.from(context).inflate(R.layout.prime_number, this, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) = holder.bind(numbers[position])

    override fun getItemCount(): Int = numbers.size

    inner class ViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView) {
        fun bind(number: Long) = with(itemView) {
            prime_number.text = number.toString()
        }
    }
}