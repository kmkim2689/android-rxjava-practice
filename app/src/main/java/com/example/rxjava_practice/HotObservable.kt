package com.example.rxjava_practice

import com.example.rxjava_practice.data.User
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.observables.ConnectableObservable
import java.util.concurrent.TimeUnit

fun hotObservable(): ConnectableObservable<User> {
    // convert cold observable into hot observable : publish()
    return Observable.fromIterable(mUserList).publish()
}
fun hotObservable2(): ConnectableObservable<Long> {
    // convert cold observable into hot observable : publish()
    return Observable.interval(1, TimeUnit.SECONDS).publish()
}