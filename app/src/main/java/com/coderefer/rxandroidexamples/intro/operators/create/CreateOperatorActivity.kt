/*
 * Copyright  © www.coderefer.com All Rights reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.coderefer.rxandroidexamples.intro.operators.create

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.coderefer.rxandroidexamples.R
import io.reactivex.Observable
import io.reactivex.Observer
import io.reactivex.disposables.Disposable

private const val TAG = "CreateOperatorActivity"

/**
 * Class to demonstrate create Operator
 * */
class CreateOperatorActivity : AppCompatActivity() {
    private val numList = listOf(1, 2, 3, 4, 5)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_operator)
        val observable = initializeObservable()
        val observer = initializeObserver()
        observable.subscribe(observer)
    }

    private fun initializeObserver(): Observer<Int> {
        return object : Observer<Int> {
            override fun onComplete() {
                Log.d(TAG, "onComplete")
            }

            override fun onSubscribe(d: Disposable) {
            }

            override fun onNext(t: Int) {
                Log.d(TAG, t.toString())
            }

            override fun onError(e: Throwable) {

            }
        }
    }

    private fun initializeObservable(): Observable<Int> {
//        using create operator to create a new Observable
        return Observable.create {
            for (i in numList)
                it.onNext(i)
            it.onComplete()
        }
    }
}
