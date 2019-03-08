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

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.coderefer.rxandroidexamples.R
import io.reactivex.Observable
import java.util.concurrent.TimeUnit
import kotlin.random.Random
import io.reactivex.schedulers.TestScheduler




private const val TAG = "FlatMapOperator"
class FlatMapOperatorActivity : AppCompatActivity() {

    val items: List<Int> = listOf(1,2,3,4,5)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_flat_map_operator)
        val scheduler = TestScheduler()



        Observable.fromIterable(items)
                .flatMap {
                    val delay = Random.nextLong(10L)
                    Observable.just(it.toString() + "x")
                            .delay(delay, TimeUnit.SECONDS, scheduler)
                }
                .doOnNext{
                    Log.d(TAG, it)
                }
                .subscribe()

        scheduler.advanceTimeBy(1, TimeUnit.MINUTES)
    }
}
