package com.example.rxjava_practice

import android.util.Log
import com.example.rxjava_practice.data.User
import com.example.rxjava_practice.data.UserProfile
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Observer
import io.reactivex.rxjava3.disposables.Disposable
import java.lang.Exception
import java.util.Arrays
import java.util.concurrent.TimeUnit

val mListNum = mutableListOf(1, 2, 3, 4, 5, 6, 7, 8, 9, 10)
val arrayNum1 = arrayOf(1, 2, 3, 4, 5, 6, 7, 8, 9, 10)
val arrayNum2 = arrayOf(10, 20, 30, 40, 50, 60, 70, 80, 90, 100)
val mUserList = mutableListOf<User>(
    User(1, "demo1", 15),
    User(2, "demo2", 18),
    User(3, "demo3", 20),
    User(4, "demo4", 21),
    User(5, "demo5", 23),
    User(6, "demo6", 23),
    User(7, "demo7", 22),
    User(8, "demo8", 23),
    User(9, "demo9", 23)
)
val mUserEmptyList = emptyList<User>()
val mUserProfileList = mutableListOf<UserProfile>(
    UserProfile(1, "demo1", 15, "https://test.com/1"),
    UserProfile(2, "demo2", 18, "https://test.com/2"),
    UserProfile(3, "demo3", 20, "https://test.com/3"),
    UserProfile(4, "demo4", 21, "https://test.com/4"),
    UserProfile(5, "demo5", 23, "https://test.com/5"),
    UserProfile(6, "demo6", 23, "https://test.com/6"),
    UserProfile(7, "demo7", 22, "https://test.com/7"),
    UserProfile(8, "demo8", 23, "https://test.com/8"),
    UserProfile(9, "demo9", 23, "https://test.com/9")
)

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

// createOperator()가 return하는 Observable의 type은 정하기 나름...
fun createOperator(): Observable<Int> {
    // ObserveOnSubscribe 매개변수
    // 구현부에서 작동할 콜백에 대한 자세한 구현을 정한다. 특히 onNext와 onError의 매개변수를 정함으로써 customizing이 가능해진다.
    return Observable.create { emitter ->
        try {
            for (i in mListNum) {
                emitter.onNext(i * 5)
            }
            // 모든 데이터가 emit된 뒤 complete로 가게 하려면 꼭 이 코드를 작성해야 한다.
            // 이렇게 해야 실제 구현하는 부분에서 onComplete 콜백이 동작한다.
            emitter.onComplete()

        } catch(e: Exception) {
            emitter.onError(e)
        }
    }
}

fun filterOperator(): Observable<User> {
    // 일단 mutableList(sequence)에 있는 모든 데이터들을 순회해야 하므로 fromIterable() 메소드 사용
    // pretend the case getting a list of users from the api
    return Observable.fromIterable(mUserList)
}

fun lastOperator(): Observable<User> {
    return Observable.fromIterable(mUserList)
}

fun distinctOperator(): Observable<User> {
    return Observable.fromIterable(mUserList)
}

fun skipOperator(): Observable<User> {
    return Observable.fromIterable(mUserList)
}

fun bufferOperator(): Observable<User> {
    return Observable.fromIterable(mUserList)
}

fun mapOperator(): Observable<User> {
    return Observable.fromIterable(mUserList)
}

fun flatMapOperator(): Observable<User> {
    return Observable.fromIterable(mUserList)
}

fun getUserProfile(id: Long): Observable<UserProfile> {
    return Observable.fromIterable(mUserProfileList)
        .filter {
            // 넘겨받은 아이디값과 일치하는 값만 발행
            it.id == id
        }
}

fun flatMapOperator2(): Observable<List<User>> {
    return Observable.just(mUserList)
}

