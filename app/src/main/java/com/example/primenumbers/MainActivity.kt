package com.example.primenumbers

import android.content.res.Configuration
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), View.OnClickListener{
    private lateinit var adapter: Adapter
    private var task: PrimeNumbersTask? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        start_btn.setOnClickListener(this)
        stop_btn.setOnClickListener(this)

        val primeNumbers = savedInstanceState?.getLongArray("list")?.toMutableList() ?: mutableListOf()
        adapter = Adapter(primeNumbers) { recycler_view.scrollToPosition(it - 1) }

        if(resources.configuration.orientation == Configuration.ORIENTATION_LANDSCAPE) {
            recycler_view.layoutManager = GridLayoutManager(this, 3)
        } else recycler_view.layoutManager = LinearLayoutManager(this)

        recycler_view.adapter = adapter

        if (primeNumbers.isNotEmpty()) {
            task = PrimeNumbersTask(adapter)
            task?.execute()
        }
    }

    override fun onPause() {
        super.onPause()
        task?.cancel(true)
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putLongArray("list", adapter.numbers.toLongArray())
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        adapter.numbers = savedInstanceState.getLongArray("list")?.toMutableList() ?: mutableListOf()
    }

    override fun onClick(view: View?) {
        when(view) {
            start_btn -> {
                task = PrimeNumbersTask(adapter)
                task?.execute()
            }
            stop_btn -> task?.cancel(true)
        }
    }
}
