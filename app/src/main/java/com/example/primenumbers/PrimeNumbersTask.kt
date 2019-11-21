
package com.example.primenumbers

import android.os.AsyncTask

class PrimeNumbersTask (private val adapter: Adapter) : AsyncTask<Long, Long, Unit>(){

    private val primeNumbers = PrimeNumbers(10000000, adapter.numbers) {
        Thread.sleep(100)
        publishProgress(it)
    }

    override fun doInBackground(vararg params: Long?) = primeNumbers.findPrimeNumbers()

    override fun onProgressUpdate(vararg values: Long?) {
        adapter.notifyDataSetChanged()
    }

    override fun onCancelled() {
        primeNumbers.isCancelled = true
    }

//    override fun onPostExecute(result: List<Long>?) {
//        super.onPostExecute(result)
//        if (isFinished) {
//            AlertDialog.Builder(context)
//                .setTitle("Вычисление выполнено")
//                .setMessage("Сгенерировано ${result?.size} простых чисел")
//                .setPositiveButton("OK", null)
//                .show()
//        }
//    }
}