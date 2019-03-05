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

package com.coderefer.rxandroidexamples.intro.operators.transform

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import com.coderefer.rxandroidexamples.R
import io.reactivex.Observable
import io.reactivex.disposables.Disposable

private const val TAG = "BufferOperator"
class BufferOperatorActivity : AppCompatActivity() {
    private lateinit var disposable: Disposable

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_buffer)

        val observable =
                Observable.just(10, 20, 30, 40, 50, 60)
                        .buffer(3)

        disposable = observable.subscribe {
            Log.d(TAG, "onNext: ")
            for(s in  it) {
                Log.d(TAG, s.toString())
            }
        }

    }

    override fun onDestroy() {
        disposable.dispose()
        super.onDestroy()
    }
}
