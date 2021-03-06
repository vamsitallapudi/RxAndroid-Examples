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
import java.util.concurrent.TimeUnit


private const val TAG = "IntervalOperator"
class IntervalOperatorActivity : AppCompatActivity() {
    lateinit var disposable :Disposable

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_interval_operator)

        val clock = Observable.interval(1, TimeUnit.SECONDS)

        disposable = clock.subscribe {
            Log.d(TAG, it.toString())
        }
    }

    override fun onDestroy() {
        disposable.dispose()
        super.onDestroy()
    }
}
