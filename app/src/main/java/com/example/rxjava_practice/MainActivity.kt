package com.example.rxjava_practice

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

        timerOperator().subscribe(
            {
                Log.d(TAG, "onNext, $it")
                getLocation()
            },
            {
                Log.d(TAG, "onError, $it")
            },
            {
                Log.d(TAG, "onComplete")
            }
        )


    }

    // get location every min
    private fun getLocation() {
        Log.d(TAG, "latitude: 100, longitude: 1")
    }

    /*
    Log 결과
    ...5초 후
    onNext, 0
    latitude: 100, longitude: 1
    onComplete
     */
}