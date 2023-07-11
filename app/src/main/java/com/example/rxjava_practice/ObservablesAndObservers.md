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
        
    * Completable <> CompletableObserver
      * when to use
        * when the observable has to ***do some tasks*** **without emitting a value**
      * callbacks => item을 발행하지 않기 때문에, onNext와 onSuccess가 존재하지 않음.
        * onSubscribe
        * onComplete => 이것 호출 전에 필요한 작업들을 수행
        * onError => Exception 발생 시 호출

    * Flowable <> Observer
      * Flowable is **similar to Observable**
      * but, what if the Observable is emitting too many values that cannot be received and consumed by the Observer?
        * at that time, an error would be caused...
        * in this case, the Observable needs to ***skip some values*** by strategy...
          * to avoid an exception...
        * For using this kind of strategy, the **Flowable** Observable can be used!
          * handles the exception with a strategy
          * the exception is called "**MissingBackPressureException**"
          * the strategy is called "**BackPressureStrategy**"
      
      * BackPressure Strategies
        * BackPressure.DROP
          * discard the events that cannot be consumed by the Observer.
          * 못 받을 것들은 그냥 버림
        * BackPressure.BUFFER
          * the source will buffer all the events until the subscriber can consume them
          * 당장 못 쓰지만, 모든 것들을 저장해놓음. 그리고 subscriber가 사용할 여유가 생기면 그 때 사용
          * 버퍼할 기본 사이즈를 지정 가능(최소 1)
        * BackPressure.LATEST
          * force to the source to keep only the latest items
          * to do that, source may need to overwrite some previous values
          * 최신 아이템만 저장해놓음. 이전 값들은 덮어씀
        * BackPressure.MISSING
          * don't want any backpressure strategy
          * 만약 어떠한 전략도 쓰지 않아야 할 상황이라면, MISSING을 넘겨주면 됨.
        * BackPressure.ERROR
          * if we don't expect any backpressure, this can be used...
          * MISSING과 ERROR의 경우, 만약 Observer가 감당하지 못하면(데이터가 emit되는 속도를 따라가지 못하면) MissingBackpressureException 발생
          
      * the way to convert Observable into Flowable
        * Observable변수명.toFlowable(strategy)
          * strategy 자리에는 위의 strategies 중 하나를 넣으면 됨

---