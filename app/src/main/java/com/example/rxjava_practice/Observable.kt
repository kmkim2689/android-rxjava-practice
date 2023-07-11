package com.example.rxjava_practice

import android.util.Log
import com.example.rxjava_practice.MainActivity.Companion.TAG
import com.example.rxjava_practice.data.User
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Observer
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.core.SingleObserver
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

fun createSingleObservable(): Single<Int> {
    return Single.create { emitter ->
        try {
            if (!emitter.isDisposed) {
                /*
                for (i in 0..100) {
                    // 주의 !!! onSuccess는 단 한 번만 호출된다.
                    // 따라서, 0 이후에 숫자는 출력되지 않는다.
                    emitter.onSuccess(i)
                }
                */
                emitter.onSuccess(0)
            }
        } catch (e: Exception) {
            emitter.onError(e)
        }
    }
}

// network call을 가정한 Single 사용 예시
fun createSingleObservableUsers(): Single<List<User>> {
    return Single.create { emitter ->
        try {
            if (!emitter.isDisposed) {
                /*
                for (i in 0..100) {
                    // 주의 !!! onSuccess는 단 한 번만 호출된다.
                    // 따라서, 0 이후에 숫자는 출력되지 않는다.
                    emitter.onSuccess(i)
                }
                */
                emitter.onSuccess(mUserList)
            }
        } catch (e: Exception) {
            emitter.onError(e)
        }
    }
}

fun singleObserver(): SingleObserver<Int> {
    return object : SingleObserver<Int> {
        override fun onSubscribe(d: Disposable) {
            Log.d(TAG, "onSubscribe")
        }

        override fun onError(e: Throwable) {
            Log.d(TAG, "onError")
        }

        override fun onSuccess(t: Int) {
            Log.d(TAG, "onSuccess, $t")
        }

    }
}

fun singleObserverUsers(): SingleObserver<List<User>> {
    return object : SingleObserver<List<User>> {
        override fun onSubscribe(d: Disposable) {
            Log.d(TAG, "onSubscribe")
        }

        override fun onError(e: Throwable) {
            Log.d(TAG, "onError")
        }

        override fun onSuccess(t: List<User>) {
            // 이렇게 하면 리스트 전체가 출력되어버림.
            // Log.d(TAG, "onSuccess, $t")
            t.forEach { user ->
                // User 하나하나에 대한 처리가 필요하면 forEach 활용
                Log.d(TAG, "onSuccess, $user")
            }
        }

    }
}