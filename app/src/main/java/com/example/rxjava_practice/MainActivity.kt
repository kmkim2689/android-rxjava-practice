package com.example.rxjava_practice

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.rxjava_practice.data.User
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

//        filterOperator()
//            // filter를 이용하기 위하여 함수 선언부에서 fromIterable()를 이용
//            .filter {
//                // it : User. 즉 리스트 안의 User 데이터를 하나하나 거름
//                // User의 age가 18을 초과하는 데이터만 emit(onNext)
//                it.age > 18 && it.name != "demo6"
//            }
//            .subscribe(
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

        lastOperator()
            // 매개변수에는 Observable이 발행하는 데이터가 아무것도 없을 때(예를 들어, 리스트가 비어있을 때)
            // 기본적으로 어느것을 발행시킬지 결정
//            .last(User(1, "demo1", 15))
            // lastElement() 사용 시 기본값 넘겨주지x
            // emit할 것이 없으면 아무것도 발행하지 x
//            .lastElement()
            // emit할 것이 없으면 onError 호출
            .lastOrError()
            // last가 return하는 type은 Single이다. emit하는 것이 오직 하나이기 때문.
            // onSuccess와 onError 두 가지 이벤트만을 지원. 즉 데이터가 하나이므로 성공/실패 여부만을 처리해도 충분
            .subscribe(
                {
                    // onSuccess
                    Log.d(TAG, "onSuccess, $it")
                },
                {
                    // onError
                    Log.d(TAG, "onError, $it")
                }
            )


    }

    // get location every min
    private fun getLocation() {
        Log.d(TAG, "latitude: 100, longitude: 1")
    }

    /*
    Log 결과
    onSuccess, User(id=6, name=demo6, age=22)
     */
}