package com.example.rxjava_practice

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Observer
import io.reactivex.rxjava3.disposables.Disposable

class MainActivity : AppCompatActivity() {

    // 1. observable과 observer 객체를 만든다.
    // 2. observer에 subscribe한다.
    // subscribe하지 않으면, observer로부터 어떠한 아웃풋도 얻지 못한다.
    companion object {
        const val TAG = "MainActivity"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val mList = mutableListOf(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12)

        // observable object
        // Observable(choose 'io.reactive.rxjava3.core')

        // operator #1. just()
        /*
        just는 인자로 전달된 값들을 순서대로 발행하는 옵저버블을 생성합니다.
        예를 들어, Observable.just(1, 2, 3)은 1, 2, 3을 차례로 발행하는 옵저버블을 생성합니다.
        just는 최대 10개의 ***Object***까지 받을 수 있습니다.

        just는 주로 테스트용으로 사용되거나, 단일 값을 발행하는 옵저버블을 생성할 때 유용합니다.
        예를 들어, 네트워크 호출의 결과나 데이터베이스 쿼리의 단일 결과를 발행하는데 사용할 수 있습니다.
         */
        // emitting(발행) 5 items
        val observable = Observable.just(1, 2, 3, 4, 5)

        // observable is gonna emit the value(1)
        // so, need to observe the value that this object will be emitting.
        // observer 객체(singleton 형태) 제작
        // Observer는 기본적으로 interface 형태로 제공되며, 자세한 사항들을 사용자가 명시해주어야함.
        // Observer<T> { } => T : the type of item the Observer expects to observe!
        // 여기서 T는 Int가 될 것임...(정수이므로)
        // an observer is observing the type of the Int
        val observer = object : Observer<Int> {

            // implement되어야 할 4개의 메소드들을 오버라이드 해야함.
            override fun onSubscribe(d: Disposable) {
                // is called whenever the object(observer) is subscribing to the observable
                Log.d(TAG, "onSubscribe")
            }

            override fun onError(e: Throwable) {
                // if there is any error
                Log.d(TAG, "onError, $e")
            }

            override fun onComplete() {
                // called after all of the emissions
                Log.d(TAG, "onComplete")
            }

            override fun onNext(t: Int) {
                // 매개변수가 Int인 것은 observer의 type이 Int이기 때문
                // whenever the observable is emitting the values
                Log.d(TAG, "onNext, $t")
            }

        }

        // observable이 emit하는 데이터를 받아오는 observer에 subscribe
        // 이것을 작성해주어야 실질적으로 동작
        // meaning : subscribe to this 'observable', and the subscriber is 'observer'
        observable.subscribe(observer)

        /*
        출력 결과 Log
        onSubscribe => subscribe 한 순간 가장 처음에 호출되는 메소드
        onNext, 1 => whenever some value is emitted and observer receives that value
        onNext, 2 => whenever some value is emitted and observer receives that value
        onNext, 3 => whenever some value is emitted and observer receives that value
        onNext, 4 => whenever some value is emitted and observer receives that value
        onNext, 5 => whenever some value is emitted and observer receives that value
        onComplete => 하나의 value만을 받기 때문에 onComplete가 호출되고, subscribe 종료
         */

    }
}