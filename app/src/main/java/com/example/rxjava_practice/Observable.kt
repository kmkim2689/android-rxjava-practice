package com.example.rxjava_practice

import android.util.Log
import com.example.rxjava_practice.MainActivity.Companion.TAG
import com.example.rxjava_practice.data.User
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.CompletableObserver
import io.reactivex.rxjava3.core.Flowable
import io.reactivex.rxjava3.core.Maybe
import io.reactivex.rxjava3.core.MaybeObserver
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Observer
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.core.SingleObserver
import io.reactivex.rxjava3.disposables.Disposable
import java.lang.Exception

lateinit var disposable: Disposable

// the basic observable
fun createObservable(): Observable<Int> {
    return Observable.create { emitter ->
        try {
            if (!emitter.isDisposed) {
                for (i in 0..100000) {
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
            d?.let {
                disposable = d
            }
//            disposable = d
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

fun createMaybeObservable(): Maybe<List<User>> {
    // 만약 빈 리스트를 넘겨준다면, onSuccess는 호출되지 않는다!
    return Maybe.just(mUserList)
}

fun maybeObserver(): MaybeObserver<List<User>> {
    return object : MaybeObserver<List<User>> {
        override fun onSubscribe(d: Disposable) {
            Log.d(TAG, "onSubscribe")
        }

        override fun onError(e: Throwable) {
            Log.d(TAG, "onError")
        }

        override fun onComplete() {
            Log.d(TAG, "onComplete")
        }

        override fun onSuccess(t: List<User>) {
            Log.d(TAG, "onSuccess, $t")
            t.forEach { user ->
                // User 하나하나에 대한 처리가 필요하면 forEach 활용
                Log.d(TAG, "onSuccess, $user")
            }
        }

    }
}

fun createCompletableObservable(): Completable {
    return Completable.create { emitter ->
        try {
            if (!emitter.isDisposed) {
                // 아무런 값도 emit하지 않기 때문에, onNext 혹은 onSuccess는 존재하지 않음.
                // onComplete 전에 해야 할 모든 작업들을 수행한다.
                getLocation()
                emitter.onComplete()

            }
        } catch (e: Exception) {
            emitter.onError(e)
        }
    }
}

fun completableObserver(): CompletableObserver {
    return object : CompletableObserver {
        override fun onSubscribe(d: Disposable) {
            Log.d(TAG, "onSubscribe")
        }

        override fun onComplete() {
            Log.d(TAG, "onComplete")
        }

        override fun onError(e: Throwable) {
            Log.d(TAG, "onError")
        }

    }
}

private fun getLocation() {
    Thread.sleep(2000)
    Log.d(TAG, "latitude: 100, longitude: 1")
}

fun createFlowableObservable(): Flowable<Int> {
    // 100개 정도면, observer가 충분히 감당 가능함.
    return Flowable.range(1, 100)
}

// how to change Observable into Flowable?
fun createFlowableObservable2(): Observable<Int> {
    // 100개 정도면, observer가 충분히 감당 가능함.
    return Observable.range(1, 100)
}
