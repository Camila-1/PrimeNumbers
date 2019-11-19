package com.example.primenumbers

import android.os.AsyncTask

class PrimeNumbersTask(private val adapter: Adapter) : AsyncTask<Long, Long, Long>() {

    private val primeNumbers = PrimeNumbers(1000000000) {
        publishProgress(it)
    }

    override fun onPreExecute() {
        super.onPreExecute()
    }

    override fun doInBackground(vararg params: Long?): Long? {
        if (!isCancelled) {
            return primeNumbers.findPrimeNumbers().last()
        }
        return 0
    }


    override fun onProgressUpdate(vararg values: Long?) {
        //super.onProgressUpdate(*values)
        adapter.addItem(values.first())
    }

    override fun onCancelled() {
        primeNumbers.isCancelled = true
    }


    override fun onPostExecute(result: Long?) {
        super.onPostExecute(result)
    }
}