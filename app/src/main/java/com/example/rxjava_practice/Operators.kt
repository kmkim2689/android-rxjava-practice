package com.example.rxjava_practice

import android.util.Log
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Observer
import io.reactivex.rxjava3.disposables.Disposable
import java.util.Arrays

val mListNum = mutableListOf(1, 2, 3, 4, 5, 6, 7, 8, 9, 10)
val arrayNum1 = arrayOf(1, 2, 3, 4, 5, 6, 7, 8, 9, 10)
val arrayNum2 = arrayOf(10, 20, 30, 40, 50, 60, 70, 80, 90, 100)

fun fromOperator() {
    val observable = Observable.fromArray(arrayNum1, arrayNum2)

    val observer = object : Observer<Array<Int>> {
        override fun onSubscribe(d: Disposable) {
            Log.d(MainActivity.TAG, "onSubscribe")
        }

        override fun onError(e: Throwable) {
            Log.d(MainActivity.TAG, "onError, $e")
        }

        override fun onComplete() {
            Log.d(MainActivity.TAG, "onComplete")
        }

        override fun onNext(t: Array<Int>) {
            Log.d(MainActivity.TAG, "onNext, ${Arrays.toString(t)}")

        }


    }

    observable.subscribe(observer)
}