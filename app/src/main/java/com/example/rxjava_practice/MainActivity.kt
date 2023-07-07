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
        fromIterableOperator()


        /*
        log 결과
        onSubscribe
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