package com.coderefer.rxandroidexamples

import io.reactivex.Observable
import org.junit.Test

import org.junit.Assert.*

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see [Testing documentation](http://d.android.com/tools/testing)
 */
class ExampleUnitTest {
    @Test
    @Throws(Exception::class)
    fun addition_isCorrect() {
        assertEquals(4, (2 + 2).toLong())
    }

    @Test
    fun testCreate_withSubscriber() {
        val observable = Observable.create<Int> {
            subscriber->
            run {
                System.out.println("starting")
                subscriber.onNext(5)
                subscriber.onNext(6)
                subscriber.onNext(7)
                subscriber.onComplete()
                System.out.println("completed")

            }
        }
        observable.subscribe { System.out.println("next: $it") }
    }

    @Test
    @Throws(Exception::class)
    fun addition_isCorrectFail() {
        assertEquals(4, (2 + 5).toLong())
    }
}