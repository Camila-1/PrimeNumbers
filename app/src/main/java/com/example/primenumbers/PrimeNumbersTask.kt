package com.example.primenumbers

import android.os.AsyncTask

class PrimeNumbersTask() : AsyncTask<Long, Long, Long>() {

    override fun onPreExecute() {
        super.onPreExecute()
    }

    override fun doInBackground(vararg params: Long?): Long {
        val primeNumbers = PrimeNumbers(1000000) {
            publishProgress(it)
        }

        return primeNumbers.findPrimeNumbers().last() //переделать потом так, чтоб одно число возвращалось
    }

    override fun onProgressUpdate(vararg values: Long?) {
        super.onProgressUpdate(*values)

    }

    //сюда передается результат выполнения doInBackground.
    //вывести при завершении вычисления диалоговое окно с сообщением об окончании.
    override fun onPostExecute(result: Long?) {
        super.onPostExecute(result)
    }
}