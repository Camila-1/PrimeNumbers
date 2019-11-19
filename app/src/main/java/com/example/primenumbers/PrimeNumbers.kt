package com.example.primenumbers


class PrimeNumbers(private val max: Long, private val list: MutableList<Long>, private val callback: (Long) -> Unit){

    var isCancelled: Boolean = false

    fun findPrimeNumbers(): MutableList<Long> {
        if (list.isEmpty()) list.add(2L)
        return ((list.last() + 1)..max).fold(list.toMutableList()) label@{ primes, item ->
            if (isCancelled) return primes

            if(isPrime(item, primes)) {
                primes.add(item)
                callback(item)
            }
            return@label primes
        }
    }

    private fun isPrime(num: Long, primes: List<Long>) = !primes
        .any { num % it == 0L }

}