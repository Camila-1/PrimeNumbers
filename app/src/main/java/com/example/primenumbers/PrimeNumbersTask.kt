package com.example.primenumbers

import android.os.AsyncTask

class PrimeNumbersTask (private val adapter: Adapter, private val showAlertDialog: (Int) -> Unit) : AsyncTask<Long, Long, List<Long>>(){

    private val primeNumbers = PrimeNumbers(100, adapter.numbers.toList().toMutableList()) {
        Thread.sleep(100)
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
        if (!isExecuted) {
            showAlertDialog(result!!.size)
            isExecuted = true
        }
    }

    companion object {
        var isExecuted = false
    }
}