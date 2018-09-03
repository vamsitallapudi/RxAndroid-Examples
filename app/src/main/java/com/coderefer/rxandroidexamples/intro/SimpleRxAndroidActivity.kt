package com.coderefer.rxandroidexamples.intro

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast


import com.coderefer.rxandroidexamples.R

import io.reactivex.Observable
import io.reactivex.Observer
import io.reactivex.disposables.Disposable

class SimpleRxAndroidActivity : AppCompatActivity() {

    private var mObservable: Observable<Int>? = null
    private var mObserver: Observer<Int>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    //    code to initialize Observable
    private fun initializeObservable() {
        mObservable = Observable.create { e ->
            for (i in 1..5) {
                e.onNext(i)
            }
            e.onComplete()
        }
    }

    //    Code to initialize Observer
    private fun initializeObserver() {
        mObserver =  object: Observer<Int> {
            override fun onSubscribe(d: Disposable) {
                Toast.makeText(this@SimpleRxAndroidActivity, "onSubscribe called", Toast.LENGTH_SHORT).show()
            }

            override fun onNext(integer: Int) {
                Toast.makeText(this@SimpleRxAndroidActivity, "onNext called: " + integer!!, Toast.LENGTH_SHORT).show()
            }

            override fun onError(e: Throwable) {
                Toast.makeText(this@SimpleRxAndroidActivity, "onError called", Toast.LENGTH_SHORT).show()
            }

            override fun onComplete() {
                Toast.makeText(this@SimpleRxAndroidActivity, "onComplete called", Toast.LENGTH_SHORT).show()
            }
        }
    }

    //    method which will be called on button click
    fun performAction(view: View) {
        initializeObservable()
        initializeObserver()
        //        subscribing observer to observable
        mObservable!!.subscribe(mObserver!!)
    }
}
