package com.coderefer.rxandroidexamples

import io.reactivex.Observable
import io.reactivex.Observer
import io.reactivex.disposables.Disposable
import io.reactivex.functions.Consumer
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
    fun testObserver_pattern() {
//        this uses the consumer interface of java which only consists of a single method: accept()
        val observer = object : Observer<Int> {
            override fun onComplete() {
                println("On Complete")
            }

            override fun onSubscribe(d: Disposable) {
                println("on subscribe")
            }

            override fun onNext(t: Int) {
                println("on next: $t")
            }

            override fun onError(e: Throwable) {
                println("on error")
            }
        }

        val observable =Observable.create<Int> {
            it.onNext(5)
            it.onNext(6)
            it.onNext(7)
            it.onComplete()
        }
        observable.subscribe(observer)
    }

    @Test
    fun testObserver_simple() {
//        this uses the consumer interface of java which only consists of a single method: accept()

        val observer = Consumer<Int> {println("next: $it")}

        val observable =Observable.create<Int> {
            it.onNext(5)
            it.onNext(6)
            it.onNext(7)
            it.onComplete()
        }
        observable.subscribe(observer)
    }

    @Test
    fun testObservable_verySimple1() {
//        observable just emits the values
        Observable.just(5,6,7)
                .subscribe{ println(it)}
    }

    @Test
    fun testObservable_verySimple_with_map() {
//        observable just emits the values
        Observable.just(5,6,7)
                .map { ";-) ".repeat(it)}
                .subscribe{ println(it)}
    }


    @Test
    fun testObservable_Example() {
//        observable just emits the values
        Observable.just(1,2,3)
                .map {it * 2}
                .filter{it<6}
                .subscribe{ println(it)}
    }

    @Test
    fun testflatMap_Example() {
//        observable just emits the values
        Observable.just(";-(",";-(")
                .flatMap {Observable.just(";-)")}
//                .map { ";-)" }
                .subscribe{ println(it)}
    }
}