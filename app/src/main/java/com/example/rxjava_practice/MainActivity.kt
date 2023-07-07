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

        fromOperator()

        /*
        log 결과
        onSubscribe
        onNext, [1, 2, 3, 4, 5, 6, 7, 8, 9, 10]
        onNext, [10, 20, 30, 40, 50, 60, 70, 80, 90, 100]
        onComplete
         */
    }
}