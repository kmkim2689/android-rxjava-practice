package com.example.rxjava_practice

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.rxjava_practice.data.User
import com.example.rxjava_practice.data.UserProfile
import io.reactivex.rxjava3.core.BackpressureStrategy
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Observer
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.disposables.Disposable
import io.reactivex.rxjava3.schedulers.Schedulers
import java.util.concurrent.TimeUnit

class MainActivity : AppCompatActivity() {
    companion object {
        const val TAG = "MainActivity"
        const val TAG2 = "MainActivity2"
    }

    val compositeDisposable = CompositeDisposable()

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

        /*        bufferOperator()
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
                    )*/

        // 단순히 데이터를 변경하는 용도로 map을 이용한 경우
        /*mapOperator()
            .map {
                it.age * 2
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
            )*/

        // Object의 타입을 바꾸고자 할 때... User -> UserProfile
        /*        mapOperator()
                    .map {
                        // User -> UserProfile
                        UserProfile(
                            it.id,
                            it.name,
                            it.age,
                            "https://test.com/${it.id}"
                        )
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
                    )*/

        /*flatMapOperator()
            .flatMap {
                // observable(Observable<User>) -> (an)other observable(s) (Observable<UserProfile>)
                // in flatMap, should return observable(s), not other type of objects.
                // return Observable<UserProfile>
                getUserProfile(it.id)
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
            )*/

        // 같은 작업을 하는 함수(flatmap)
        /*flatMapOperator2()
            .flatMap {
                // get the list of Users and flatten!
                // User 데이터를 하나하나 순회하고
                Observable.fromIterable(it)
                // 각 User 아이템들을 모아 하나의 Observable로 병합
            }
            .flatMap {
                // User 데이터를 가져와 함수를 이용해 각 아이템을 UserProfile로 변환
                getUserProfile(it.id)
                // UserProfile 아이템들을 모아 하나의 Observable로 병합
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
            )*/

        /*        groupByOperator()
                    .groupBy {
                        // in groupBy, add some group(s) to the particular User item!
                        // group by the age of users
                        // 여기서 key가 어떻게 될 지 결정되는 것. 즉 여기서는 나이가 key가 됨
                        it.age
                    }
                    .filter {
                        // 23세인 사람만 가져오고 싶은 경우
                        it.key == 23
                    }
                    .subscribe(
                        {
                            // onNext : GroupedObservable<Int, User> 얻어옴.
                            // 여기서 Int값이 key에 해당되고, User가 value에 해당
                            // flattened
                            group ->
                                group.subscribe(
                                    // subscribe를 통해 얻어오는 값은 User, 즉 value에 해당
                                    {
                                        // onNext
                                        Log.d(TAG, "onNext, key: ${group.key}, value: $it")
                                    },
                                    {
                                        // onError
                                        Log.d(TAG, "onError, $it")
                                    }
                                )
                        },
                        {
                            Log.d(TAG, "onError, $it")
                        },
                        {
                            Log.d(TAG, "onComplete")
                        }
                    )*/

        // get lists by key using groupBy and flatMapSingle
        /*groupByOperator()
            .groupBy {
                // in groupBy, add some group(s) to the particular User item!
                // group by the age of users
                // 여기서 key가 어떻게 될 지 결정되는 것. 즉 여기서는 나이가 key가 됨
                it.age
            }
            // rxJava의 flatMapSingle() :
            // Single type을 return해야한다. 따라서 flatMapSingle을 사용
            .flatMapSingle { group ->
                // rxJava의 toList() operator : 한 Observable에서 emit될 모든 아이템들을 모아 List인 Observable로 만들어 발행
                group.toList()
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
            )*/

        /*mergeOperator()
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

        /*concatOperator()
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

        /*startWithOperator()
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

        /*zipOperator2()
            .subscribe(
                {
                    it.forEach {
                        Log.d(TAG, "onNext, $it")
                    }

                },
                {
                    Log.d(TAG, "onError, $it")
                },
                {
                    Log.d(TAG, "onComplete")
                }
            )*/

        // createObservable().subscribe(observer())

        /*createObservable().subscribe(
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

//        createSingleObservable().subscribe(singleObserver())

//        createSingleObservableUsers().subscribe(singleObserverUsers())

//        createMaybeObservable().subscribe(maybeObserver())

//        createCompletableObservable().subscribe(completableObserver())

        /*createFlowableObservable()

            // observer가 감당 못하면 그 이후로 오는 것들은 떨굼
            // 1부터 10까지
            // .onBackpressureDrop()
            // observer가 감당 못하면 가장 마지막 것들만 발행
            // 1부터 10까지 담겨 꽉 찬 상태에서, 가장 마지막 값인 100만 출력
            // .onBackpressureLatest()
            // buffer : subscriber가 감당 가능할 때 사용하기 위해 모두 담아놓음.
            .onBackpressureBuffer()
            // 버퍼 사이즈는 10으로 제한
            .observeOn(Schedulers.io(), false, 10)
            .subscribe(
                {
                    // onNext 람다식
                    // 1부터 10까지만 출력됨!
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
            )*/

        // convert observable into flowable
        /*createFlowableObservable2()
            // error 혹은 missing으로 설정해놓았는데, 버퍼 사이즈가 10이라 부족 -> 예외 발생...
//            .toFlowable(BackpressureStrategy.ERROR)
            .toFlowable(BackpressureStrategy.DROP)
            .observeOn(Schedulers.io(), false, 10)
            .subscribe(
                {
                    // onNext 람다식
                    // 1부터 10까지만 출력됨!
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
            )*/

//        createObservable()
//            .subscribeOn(Schedulers.io())
//            .subscribe(
//                observer()
//            )

        // add된 2개의 구독은 비동기적으로 동시에 수행된다...
        compositeDisposable.add(
            createObservable()
                .subscribeOn(Schedulers.io())
                .subscribe(
                    {
                        // onNext
                        Log.d(TAG, "onNext, $it")
                    },
                    {
                        // onError
                        Log.d(TAG, "onError, $it")
                    },
                    {
                        // onComplete
                        Log.d(TAG, "onComplete")
                    }
                )
        )

        // 여러 개의 subscribe 추가 가능
        compositeDisposable.add(
            intervalOperator().subscribe(
                {
                    // onNext
                    Log.d(TAG2, "onNext, $it")
                },
                {
                    // onError
                    Log.d(TAG2, "onError, $it")
                },
                {
                    Log.d(TAG2, "onComplete")
                }
            )
        )

    }

    // get location every min
    private fun getLocation() {
        Log.d(TAG, "latitude: 100, longitude: 1")
    }

    override fun onDestroy() {
        super.onDestroy()
        // 이러한 식으로 onDestroy에서 dispose해주지 않으면
        // destroy되어도 계속 observing이 진행된다!!!
        disposable.dispose()
        Log.d(TAG, "onDestroy")
    }

    /*
    Log 결과
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

    /*
    onSubscribe
    onSuccess, User(id=1, name=demo1, age=15)
    onSuccess, User(id=2, name=demo2, age=18)
    onSuccess, User(id=3, name=demo3, age=15)
    onSuccess, User(id=4, name=demo4, age=21)
    onSuccess, User(id=5, name=demo5, age=23)
    onSuccess, User(id=6, name=demo6, age=23)
    onSuccess, User(id=7, name=demo7, age=21)
    onSuccess, User(id=8, name=demo8, age=22)
     */
}