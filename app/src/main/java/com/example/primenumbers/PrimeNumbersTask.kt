package com.example.primenumbers

import android.app.AlertDialog
import android.content.Context
import android.os.AsyncTask
import com.google.android.material.dialog.MaterialAlertDialogBuilder

class PrimeNumbersTask (private val adapter: Adapter, private val context: Context) : AsyncTask<Long, Long, List<Long>>(){
    private lateinit var mContext: Context

    init {
        mContext = context
    }

    private val primeNumbers = PrimeNumbers(1000000, adapter.numbers) {
        publishProgress(it)
    }

    override fun doInBackground(vararg params: Long?): List<Long> = primeNumbers.findPrimeNumbers()

    override fun onProgressUpdate(vararg values: Long?) {
        adapter.addItem(values.first())
    }

    override fun onCancelled() {
        primeNumbers.isCancelled = true
    }

    override fun onPostExecute(result: List<Long>?) {
        super.onPostExecute(result)
        MaterialAlertDialogBuilder(context)
            .setTitle("Вычисление выполнено")
            .setMessage("Сгенерировано ${result?.size} простых чисел")
            .setPositiveButton("OK", null)
            .show()
    }
}