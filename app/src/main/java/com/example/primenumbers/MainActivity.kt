package com.example.primenumbers

import android.content.res.Configuration
import android.graphics.drawable.GradientDrawable
import android.os.AsyncTask
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.PersistableBundle
import android.view.View
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
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
        task = PrimeNumbersTask(adapter)
        if (primeNumbers.isNotEmpty()) task?.execute()
    }

    override fun onSaveInstanceState(outState: Bundle, outPersistentState: PersistableBundle) {
        super.onSaveInstanceState(outState, outPersistentState)
        outState.putLongArray("list", adapter.numbers.toLongArray())
    }

//    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
//        super.onRestoreInstanceState(savedInstanceState)
//        adapter.numbers = savedInstanceState.getLongArray("list")?.toMutableList() ?: mutableListOf()
//    }

    override fun onClick(view: View?) {
        when(view) {
            start_btn -> {
//                if(task?.status == AsyncTask.Status.RUNNING)
//                    task?.cancel(true)
                task?.execute()
            }
            stop_btn -> task?.cancel(true)
        }
    }
}
