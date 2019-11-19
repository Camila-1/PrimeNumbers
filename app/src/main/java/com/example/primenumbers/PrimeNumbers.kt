package com.example.primenumbers

class PrimeNumbers(private val max: Long, private val callback: (Long) -> Unit) {

    var isCancelled: Boolean = false

    fun findPrimeNumbers(): MutableList<Long> {
        return (3..max).fold(mutableListOf(2L)) label@{ primes, item ->
            if (isCancelled) return primes

            if(isPrime(item, primes)) {
                primes.add(item)
                callback(item)
            }
            return@label primes
        }
    }

    private fun isPrime(num: Long, primes: List<Long>) = !primes.any { num % it == 0L }

}