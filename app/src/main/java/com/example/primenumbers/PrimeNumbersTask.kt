package com.example.primenumbers

import android.content.Context
import android.os.AsyncTask

class PrimeNumbersTask (private val adapter: Adapter, private val context: Context) : AsyncTask<Long, Long, List<Long>>(){
    private var mContext: Context
    var isFinished = false

    init {
        mContext = context
    }

    private val primeNumbers = PrimeNumbers(100, adapter.numbers) {
        Thread.sleep(200)
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
//        if (isFinished) {
//            AlertDialog.Builder(context)
//                .setTitle("Вычисление выполнено")
//                .setMessage("Сгенерировано ${result?.size} простых чисел")
//                .setPositiveButton("OK", null)
//                .show()
//        }
    }
}