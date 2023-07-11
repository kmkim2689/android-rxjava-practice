## Observables and Observers

### 1. Type of Observables and Observers
* Observable Types
    * Observable
        * 가장 일반적
    * Flowable
    * Single
    * Maybe
    * Completable

* Observer Types
    * Observer
        * 가장 일반적 - used with Observable
    * SingleObserver
        * used with Single Observable
    * MaybeObserver
        * used with Maybe Observable
    * CompletableObserver
        * used with Completable Observable

* Use Cases
    * Observable <> Observer
        * Observable : the observable that emits **more than one** value
        * when 2 use
            * main use : if a user wants to download a file, the user should be ***continuously*** provided with the progress of the download.
            * so, the Observable has to emit values at ***regular intervals***.
            * that is, the developer should provide the value for once or more times.
          
    * Single <> SiingleObserver ***
      * when to use : network calls or calls to access database
        * when the Observable has to emit **only one value**
          * ex : a response from ***network call***
          * the most common Observable Android developer will be using, as most of the applications involve network calls.
      * two callbacks
        * onSuccess
          * !!! onNext와는 달리 **단 한 번만** 호출됨에 유의
          * 즉, 반복문을 통해 1회 초과하여 발행하는 것이 필요하다면, 그 때는 Observer를 사용해야 한다.
        * onError
---