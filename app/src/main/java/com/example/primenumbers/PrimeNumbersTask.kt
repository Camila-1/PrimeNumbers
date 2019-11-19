package com.example.primenumbers

import android.os.AsyncTask

class PrimeNumbersTask (private val adapter: Adapter) : AsyncTask<Long, Long, List<Long>>(){

    private val primeNumbers = PrimeNumbers(1000000, adapter.numbers) {
        publishProgress(it)
    }

    override fun onPreExecute() {
        super.onPreExecute()
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
    }
}