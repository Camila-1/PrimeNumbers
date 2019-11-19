package com.example.primenumbers

import android.os.AsyncTask
import android.os.Parcelable
import kotlinx.android.parcel.Parcelize
import kotlinx.android.parcel.RawValue

@Parcelize
class PrimeNumbersTask(private val adapter: @RawValue Adapter) : AsyncTask<Long, Long, List<Long>>(), Parcelable {

    private val primeNumbers = PrimeNumbers(1000000) {
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