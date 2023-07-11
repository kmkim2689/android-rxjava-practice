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
          
    * Single <> SingleObserver ***
      * when to use : network calls or calls to access database
        * when the Observable has to emit **only one value**
          * ex : a response from ***network call***
          * the most common Observable Android developer will be using, as most of the applications involve network calls.
      * two callbacks
        * onSubscribe
        * onSuccess
          * !!! onNext와는 달리 **단 한 번만** 호출됨에 유의
          * 즉, 반복문을 통해 1회 초과하여 발행하는 것이 필요하다면, 그 때는 Observer를 사용해야 한다.
          * SingleObserver가 아무런 아이템도 emit할 수 없다면(예를 들어, 빈 리스트 호출), onSuccess가 호출되며 t는 빈 리스트가 된다.
            * 즉, onError가 호출되는 것이 아님.
        * onError

    * Maybe <> MaybeObserver
      * when to use : 1개 또는 0개 item을 emit해야할 때.
        * when the observable has to emit a value or no value
        * but, not recommended to use for Android App development.
      * callbacks
        * onSubscribe, onSuccess, onError, onComplete
      * MaybeObserver의 경우, Observer과는 달리 onNext가 존재하지 않음.
      * 또한, onSubscribe와 onSuccess가 둘 다 존재
      * 아무런 아이템도 emit하지 않는다면, onSuccess는 호출되지 않는다.
        * singleObserver과 비교되는 점...
      

---