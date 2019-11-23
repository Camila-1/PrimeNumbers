package com.example.primenumbers

import android.content.res.Configuration
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), View.OnClickListener{
    private lateinit var adapter: Adapter
    private var task: PrimeNumbersTask? = null
    private var isStopped: Boolean = false
    private val showDialog: (Int) -> Unit = { count ->
        AlertDialog.Builder(this)
            .setTitle("Вычисление выполнено")
            .setMessage("Сгенерировано $count простых чисел")
            .setPositiveButton("OK", null)
            .show()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        start_btn.setOnClickListener(this)
        stop_btn.setOnClickListener(this)

        val primeNumbers = savedInstanceState?.getLongArray("list")?.toMutableList() ?: mutableListOf()
        adapter = Adapter(primeNumbers) { recycler_view.scrollToPosition(it - 1) }
        isStopped = savedInstanceState?.getBoolean("isStopped") ?: false

        if(resources.configuration.orientation == Configuration.ORIENTATION_LANDSCAPE) {
            recycler_view.layoutManager = GridLayoutManager(this, 3)
        } else recycler_view.layoutManager = LinearLayoutManager(this)

        recycler_view.adapter = adapter

        if (primeNumbers.isNotEmpty() && !isStopped) {
            task = PrimeNumbersTask(adapter, showDialog)
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
        outState.putBoolean("isStopped", isStopped)
    }

    override fun onClick(view: View?) {
        when(view) {
            start_btn -> {
                isStopped = false
                task = PrimeNumbersTask(adapter, showDialog)
                task?.execute()
            }
            stop_btn -> {
                isStopped = true
                task?.cancel(true)
            }
        }
    }
}
