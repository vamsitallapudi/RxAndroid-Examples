package com.coderefer.rxandroidexamples

import android.os.Handler
import io.reactivex.Observable
import org.junit.Test

import org.junit.Assert.*
import org.mockito.Mockito.mock
import java.util.concurrent.TimeUnit

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
                println("starting")
                subscriber.onNext(5)
                subscriber.onNext(6)
                subscriber.onNext(7)
                subscriber.onComplete()
                println("completed")

            }
        }
        observable.subscribe { System.out.println("next: $it") }
    }

    @Test
    @Throws(Exception::class)
    fun addition_isCorrectFail() {
        assertEquals(4, (2 + 2).toLong())
    }

    @Test
    fun testColdObservables() {
        val cold = Observable.interval(200,TimeUnit.MILLISECONDS)
        cold.subscribe { i-> println("First: $i") }
        Thread.sleep(1000)
        cold.subscribe{j ->println("Second: $j")}
        Thread.sleep(10000)
    }
}