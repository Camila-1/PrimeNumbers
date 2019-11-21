
package com.example.primenumbers


class PrimeNumbers(private val max: Long, private val list: MutableList<Long>, private val callback: (Long) -> Unit){

    var isCancelled: Boolean = false

    fun findPrimeNumbers(){
        if (list.isEmpty()) list.add(2L)
        ((list.last() + 1)..max).forEach {item ->
            if (isCancelled) return

            if(isPrime(item, list)) {
                list.add(item)
                callback(item)
            }
        }
    }

    private fun isPrime(num: Long, primes: List<Long>) = !primes
        .any { num % it == 0L }

}