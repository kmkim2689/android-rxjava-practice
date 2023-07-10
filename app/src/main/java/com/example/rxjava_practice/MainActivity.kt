package com.example.rxjava_practice

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Observer
import io.reactivex.rxjava3.disposables.Disposable

class MainActivity : AppCompatActivity() {
    companion object {
        const val TAG = "MainActivity"
    }

    @SuppressLint("CheckResult")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

//        fromOperator()
//        fromIterableOperator()

        // subscribe : onNext 인자가 있는 메소드 선택
//        rangeOperator().subscribe(
//            {
//                // onNext 람다식
//                Log.d(TAG, "onNext, $it")
//            },
//            {
//                // onError 람다식
//                Log.d(TAG, "onError, $it")
//            },
//            {
//                // onComplete 람다식
//                Log.d(TAG, "onComplete")
//            }
//        )

//        intervalOperator().subscribe(
//            {
//                Log.d(TAG, "onNext, $it")
//                // every second, get location
//                getLocation()
//            },
//            {
//                Log.d(TAG, "onError, $it")
//            },
//            {
//                Log.d(TAG, "onComplete")
//            }
//        )

//        timerOperator().subscribe(
//            {
//                Log.d(TAG, "onNext, $it")
//                getLocation()
//            },
//            {
//                Log.d(TAG, "onError, $it")
//            },
//            {
//                Log.d(TAG, "onComplete")
//            }
//        )

//        createOperator().subscribe(
//            {
//                Log.d(TAG, "onNext, $it")
//            },
//            {
//                Log.d(TAG, "onError, $it")
//            },
//            {
//                Log.d(TAG, "onComplete")
//            }
//        )

        filterOperator()
            // filter를 이용하기 위하여 함수 선언부에서 fromIterable()를 이용
            .filter {
                // it : User. 즉 리스트 안의 User 데이터를 하나하나 거름
                // User의 age가 18을 초과하는 데이터만 emit(onNext)
                it.age > 18 && it.name != "demo6"
            }
            .subscribe(
            {
                Log.d(TAG, "onNext, $it")
            },
            {
                Log.d(TAG, "onError, $it")
            },
            {
                Log.d(TAG, "onComplete")
            }
        )

        // 이렇게 간단한 예시에서는 rxjava의 필요성이 낮으나,
        // 더욱 복잡한 조건이 걸리는 경우 그리고 filter외에도 다른 많은 작업을 행해야 하는 경우
        // rxjava를 사용하지 않으면 많은 코드가 작성되어야 한다는 문제점이 있기 때문에 rxjava 사용을 익혀둬야 함.


    }

    // get location every min
    private fun getLocation() {
        Log.d(TAG, "latitude: 100, longitude: 1")
    }

    /*
    Log 결과
    onNext, User(id=3, name=demo3, age=20)
    onNext, User(id=4, name=demo4, age=21)
    onNext, User(id=5, name=demo5, age=23)
    onComplete
     */
}