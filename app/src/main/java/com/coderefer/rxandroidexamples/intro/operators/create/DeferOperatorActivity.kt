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
import io.reactivex.disposables.Disposable

private const val TAG = "DeferOperatorActivity"

class DeferOperatorActivity : AppCompatActivity() {

    private lateinit var disposable1 :Disposable
    private lateinit var disposable2 :Disposable

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_defer_operator)

        val testClass = TestClass()
        val observable = testClass.strObservable()
        val deferObservable = testClass.deferObservable()
        testClass.str = "World"
       disposable1 = observable.subscribe {
            Log.d(TAG, it) // prints "Hello" only since it is the value when Observable is created / initialized
        }
        observable.subscribe()

        disposable2 =deferObservable.subscribe {
            Log.d(TAG, it) // prints "World" since it creates a fresh Observable when subscribed with fresh data.. hence prints "World"
        }
    }

    override fun onDestroy() {
        disposable1.dispose() // to avoid memory leaks
        disposable2.dispose()
        super.onDestroy()
    }
}

class TestClass {

    var str: String? = "Hello"
    fun strObservable() : Observable<String> {
        return Observable.just(str)
    }

//    defer observable
    fun deferObservable() : Observable<String> {
        return Observable.defer {
            Observable.just(str)
        }
    }

}

