package com.example.rxjava_practice

import android.util.Log
import com.example.rxjava_practice.MainActivity.Companion.TAG
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Observer
import io.reactivex.rxjava3.disposables.Disposable
import java.lang.Exception

// the basic observable
fun createObservable(): Observable<Int> {
    return Observable.create { emitter ->
        try {
            if (!emitter.isDisposed) {
                for (i in 0..100) {
                    emitter.onNext(i)
                }
            }
            emitter.onComplete()
        } catch (e: Exception) {
            emitter.onError(e)
        }
    }
}

fun observer(): Observer<Int> {
    return object : Observer<Int> {
        override fun onSubscribe(d: Disposable) {
            Log.d(TAG, "onSubscribe")
        }

        override fun onError(e: Throwable) {
            Log.d(TAG, "onError")
        }

        override fun onComplete() {
            Log.d(TAG, "onComplete")

        }

        override fun onNext(t: Int) {
            Log.d(TAG, "onNext, $t")

        }

    }
}