package com.example.rxjava_practice

import android.util.Log
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Observer
import io.reactivex.rxjava3.disposables.Disposable
import java.util.Arrays
import java.util.concurrent.TimeUnit

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

fun fromIterableOperator() {
    //Converts an Iterable sequence into an Observable that emits the items in the sequence.
    // it's emitting each item in an iterable object, not an object!
    // emits the item in the sequence.
    val observable = Observable.fromIterable(mListNum)

    // list 내부의 값들은 Int로 이뤄져 있음. 따라서 observe의 대상 역시 Int가 되어야함.
    val observer = object : Observer<Int> {
        override fun onSubscribe(d: Disposable) {
            Log.d(MainActivity.TAG, "onSubscribe")
        }

        override fun onError(e: Throwable) {
            Log.d(MainActivity.TAG, "onError, $e")
        }

        override fun onComplete() {
            Log.d(MainActivity.TAG, "onComplete")
        }

        override fun onNext(t: Int) {
            Log.d(MainActivity.TAG, "onNext, $t")
        }

    }

    observable.subscribe(observer)
}

fun rangeOperator(): Observable<Int> {
    return Observable.range(2, 5)
}

fun repeatOperator(): Observable<Int> {
    return Observable.range(1, 10).repeat(2)
}

// interval : Observable<Long> 타입을 반환한다.
fun intervalOperator(): Observable<Long> {
    // initialDelay => 처음 시작되기까지 걸리는 시간은 2초. 생략 시, period의 값으로 대체
    // period => 1초(timeUnit이 Second이므로)마다 한번씩 발행. 계속 발행되는 사이사이의 시간이 모두 1초로 동일.
    // 1초마다 한번씩 '0'***부터 정수가 계속 발행된다. 액티비티가 destroy되지 않는 한, 발행을 멈추지 않는다.
    // takeWhile()을 사용하여, 멈출 시점을 명시
    return Observable.interval(2, 1, TimeUnit.SECONDS).takeWhile { value ->
        // 10초 전까지만 발행한다.
        value < 10
    }
}

fun timerOperator(): Observable<Long> {
    // 5초 뒤 value를 하나 반환하고, complete
    return Observable.timer(5, TimeUnit.SECONDS)
}