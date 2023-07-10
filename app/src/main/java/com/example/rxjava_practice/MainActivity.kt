package com.example.rxjava_practice

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.rxjava_practice.data.User
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Observer
import io.reactivex.rxjava3.disposables.Disposable
import java.util.concurrent.TimeUnit

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
        /*
        rangeOperator().subscribe(
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
                */

        /*        intervalOperator().subscribe(
                    {
                        Log.d(TAG, "onNext, $it")
                        // every second, get location
                        getLocation()
                    },
                    {
                        Log.d(TAG, "onError, $it")
                    },
                    {
                        Log.d(TAG, "onComplete")
                    }
                )*/

        /*        timerOperator().subscribe(
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
                )*/

        /*        createOperator().subscribe(
                    {
                        Log.d(TAG, "onNext, $it")
                    },
                    {
                        Log.d(TAG, "onError, $it")
                    },
                    {
                        Log.d(TAG, "onComplete")
                    }
                )*/

        /*
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
*/

        /*
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
        */

        /*distinctOperator()
            // 데이터클래스의 구체적인 필드 조건을 줄 수도 있고,
//                    .distinct {
//                        // 나이가 같은 아이템은 최초 1번만 발행
//                        it.age
//                    }
            // User데이터가 완전히 같은 것들을 걸러낼 수도 있다.
            // distinct() 매개변수에 아무것도 넘겨주지 않는다.
            .distinct()
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
            )*/

        // 사용되는 operator의 순서에 따라 emit되는 아이템의 개수와 순서가 달라질 수 있음에 유의
        /*        skipOperator()
        //            .skipLast(2)
                    // .skip(2)
                    // 첫 2개 스킵하고, 그 이후의 아이템 중에서 중복되는 것 제거하고 싶으면 함께 다른 operator 사용 가능
                    // .distinct()
                    .skip(2, TimeUnit.MILLISECONDS)
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
                    )*/

        bufferOperator()
            .buffer(3)
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
    }

    // get location every min
    private fun getLocation() {
        Log.d(TAG, "latitude: 100, longitude: 1")
    }

    /*
    Log 결과
    onNext, [User(id=1, name=demo1, age=15), User(id=2, name=demo2, age=18), User(id=3, name=demo3, age=20)]
    onNext, [User(id=4, name=demo4, age=21), User(id=5, name=demo5, age=23), User(id=6, name=demo6, age=23)]
    onNext, [User(id=7, name=demo7, age=22), User(id=8, name=demo8, age=22), User(id=8, name=demo8, age=22)]
    onComplete
     */
}