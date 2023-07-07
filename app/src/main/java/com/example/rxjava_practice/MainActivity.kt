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

        repeatOperator().subscribe(
            {
                // onNext 람다식
                Log.d(TAG, "onNext, $it")
            },
            {
                // onError 람다식
                Log.d(TAG, "onError, $it")
            },
            {
                // onComplete 람다식
                Log.d(TAG, "onComplete")
            }
        )

        /*
        log 결과 (range start : 1, count : 10, repeat : 2)
        onNext, 1
        onNext, 2
        onNext, 3
        onNext, 4
        onNext, 5
        onNext, 6
        onNext, 7
        onNext, 8
        onNext, 9
        onNext, 10
        onNext, 1
        onNext, 2
        onNext, 3
        onNext, 4
        onNext, 5
        onNext, 6
        onNext, 7
        onNext, 8
        onNext, 9
        onNext, 10
        onComplete
         */
    }
}