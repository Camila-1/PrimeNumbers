package com.example.primenumbers

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), View.OnClickListener{
    private val adapter: Adapter = Adapter { recycler_view.scrollToPosition(it - 1) }
    private var task: PrimeNumbersTask? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        start_btn.setOnClickListener(this)
        stop_btn.setOnClickListener(this)

        recycler_view.layoutManager = LinearLayoutManager(this)
        recycler_view.adapter = adapter

    }

    override fun onClick(view: View?) {
        when(view) {
            start_btn -> {
                task?.cancel(true)
                task = PrimeNumbersTask(adapter)
                task?.execute()
            }
            stop_btn -> task?.cancel(true)
        }
    }
}
