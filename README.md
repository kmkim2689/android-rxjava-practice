## Operators By Category

* references
    * https://reactivex.io/documentation/operators.html
    * 각 operator들의 이름을 클릭하면 시각화된 설명 확인 가능.

### 0. the form of observer
* verbose codes -> causes boilerplate codes

  ```
  val observer = object : Observer<Int> {
      override fun onSubscribe(d: Disposable) {
          Log.d(MainActivity.TAG, "onSubscribe")
      }
    
      override fun onError(e: Throwable) {
          Log.d(MainActivity.TAG, "onError, $e")
      }
    
      override fun onComplete() {
          Log.d(MainActivity.TAG, "onComplete")
      }
  
      override fun onNext(t: Int) {
          Log.d(MainActivity.TAG, "onNext, $t")
      }
  }
  ```
* using lambda functions, more concise. readability increases
  * 인터페이스의 각 메소드를 람다식으로 표현 가능
  
    
        


### 1. Creating Observables
* just() : convert an object or a set of objects into an Observable that emits that or those objects
  * just emits one by one!!!
  * up to 10 'objects' can be checked up
  * 즉, just에 11개의 Int를 넘겨줄 수는 없지만, 11개의 정수가 들어있는 mutableList는 넘겨줄 수 있음
  
* from : convert 'various' other object's' and data type's' into observables
  * can create observables from different type of objects!!
  * fromArray()
  * fromIterable()
    * Converts an Iterable sequence into an Observable that emits the items in the sequence.
    * it's emitting each item in an iterable object, not an object!
    * emits the item in the sequence.

* range() => 파이썬 for문의 range와 유사함.
  * fun range(start: Int, count: Int): Observable<Int>
  * start : the value of 'the first integer' in the 'sequence'
  * count : the number of sequential integers to generate
    * how many sequential you want to generate
  * return : an observable that emits a sequence of integer within a specified range

* repeat()
  * range operator와 함께 사용
  * range(start, range).repeat(iteration)
  * range operator를 반복할 횟수를 정함

* interval()
  * Returns an Observable that emits 'a sequential number every specified interval of time.'
  * 정해진 시작 시점과 정해진 간격에 따라 0부터 1씩 늘려가면서 정수를 하나씩 발행한다.
  * parameters
    * initialDelay : Long => the initial delay time to wait before emitting the 'first' value
    * => 즉, 첫 발행 시까지 걸리는 시간을 조절
    * period: Long => the period of time 'between' emissions of subsequent nums
    * => 즉, 각 발행 사이에 걸리는 시간
    * unit: TimeUnit => the time unit for both initialDelay and period
    * => 시간 단위 day / minute / millisecond / seconds 등등...
  * 정해진 시간 간격마다 어떠한 작업을 수행해야 할 때, interval 함수가 유용하다.
  * 또한, 정해진 시간에 따라 특정 작업을 수행할 때도 이것을 사용하면 유용하다
  * 특정 시점에서 멈추고 싶다면, .takeWhile() 메소드를 함께 사용
  * interval().takeWhile() => takeWhile 안에는 함수가 들어감. 매개변수는 onNext로 나오는 value를 가리킴(인덱스)
    * 구현부에서 value에 대한 조건문이 false라면 발행을 멈춘다.
    
* timer()
  * one time emission of a value
  * returns an observable that emits a value after a specified delay,
    * and then ***completes***
    * not emitting continuously compared to interval()
  * parameters
    * delay : the initial delay before emitting a single value => Long
    * unit : time units to use for delay => timeUnit
    
* create() ***
  * for ***customizing*** our own implementation!!!
  * Emitter를 이용하여 직접 아이템을 발행하고, 아이템 발행의 완료나 오류(Complete/Error)의 알림을 직접 설정
  * parameter
    * ObserveOnSubscribe(메소드)
    * 메소드에서 어느 상황에서 어떠한 방식으로 onNext, onComplete, onError 콜백이 수행될지 직접 구현
    * 원시 데이터를 이용하여 onNext의 발행이 어떻게 될지 정할 수 있음

### 2. Filtering Observables

* filter() ***
  * emit only those items from an Observable that pass a predicate test
  * parameter : function to filter(넘겨주는 값 -> 넘겨주는 값을 이용한 조건(t/f))
    * ex) Observable.filter { x -> x > 10 }
    * 10이 넘는 숫자만 emit
    
* last()
  * emits 'only' the 'last' item emitted by an observable
  * or the last item that meets some condition => can add some conditions
  * last item that has been emitted by an observable
  * parameter
    * defaultItem : the default item to emit 'if the current Observable is ***empty***'
  * last에서 observer의 이벤트 콜백은 onSuccess와 onError 두 가지임.
    * 이유 : last()의 return type은 Observable이 아닌 ***Single***이기 때문이다.
    * 이것은 last가 오직 하나의 데이터만 emit하는 특성에 기인한다고 볼 수 있음.
    * Observable vs Single
      * 데이터 방출 개수: Observable은 0개 이상의 데이터 아이템을 방출할 수 있지만, Single은 오직 1개의 데이터 아이템 또는 에러만을 방출합니다. 즉, Single은 데이터 스트림의 종료 시점을 명확하게 정의할 수 있습니다. 
      * 구독자의 처리 방식: Observable은 데이터 스트림을 구독하는 구독자에게 onNext, onError, onComplete와 같은 세 가지 이벤트를 통해 데이터를 처리할 수 있습니다. 반면에 Single은 구독자에게 onSuccess 또는 onError 이벤트 중 하나만을 전달합니다. 따라서 Single은 성공적인 결과 또는 에러 결과만을 처리하는 경우에 유용합니다. 
      * 에러 처리: Observable은 여러 개의 에러 이벤트를 방출할 수 있습니다. 예를 들어 네트워크 호출 중 여러 개의 예외가 발생할 수 있습니다. 반면에 Single은 오직 단 하나의 결과나 에러만을 방출하므로, 예외가 발생할 경우 onError 이벤트를 통해 처리됩니다. 
      * 편의성: Single은 데이터 아이템이 한 개 뿐이라는 제한을 가지고 있기 때문에, 데이터 아이템을 처리하는데 간편함을 제공합니다. 단일 결과를 처리하고자 할 때 Single은 적합한 선택일 수 있습니다. Observable은 여러 개의 데이터 아이템을 처리할 수 있기 때문에 더 다양한 상황에 적합하게 사용될 수 있습니다. 
      * 이러한 차이점들을 고려하여 Observable과 Single 중에서 적합한 타입을 선택할 수 있습니다. 데이터 스트림의 특성과 요구사항에 맞게 올바른 타입을 선택하여 사용하면 RxJava를 보다 효과적으로 활용할 수 있습니다.
  * 참고1 : lastElement()
    * last()의 기본적 기능 +
    * 기본값을 넘겨주지 않는다.
    * emit할 것이 없다면, ***아무것도 출력하지 않는다.***
  * 참고2 : lastOnError()
    * last()의 기본적 기능 +
    * emit할 것이 없는 경우, onFailure 콜백을 호출한다.
    
* distinct()
  * suppress 'duplicate' items by an observable
  * Observable에서 아이템들이 발행되는 동안 이미 발행된 아이템이라면 해당 아이템을 발행하지 않음. 
  * 이름 그대로, distinct item만 발행
  * 예를 들어, observable에서 1, 2, 2, 1, 3 순서대로 발행이 이뤄진다면, distinct()를 거친다면 실질적으로 발행되는 것들은 1, 2, 3뿐이다.
  * parameter : ketSelector 함수
    * 예를 들어, 데이터 클래스의 어떤 항목이 같은 것을 걸러낼 것인지 함수로 명시
    * 아무것도 넘겨주지 않으면, 완전히 일치하는 것을 걸러냄.

* skip()
  * suppress 'the first n(defined number of...) items' emitted by an observable
  * 처음 n개 item을 skip하고 발행
  * 예를 들어, observable에서 1, 2, 3, 4를 발행하는데 skip(2)를 적용한다면, 3, 4만 발행
  * parameter case1 : count based skip, num of items to skip(처음 n개): Long
  * parameter case2 : time based skip, 처음 n 시간 단위 동안 emit될 item들을 skip
    * time: Long => n 시간 동안
    * unit: TimeUnit => 시간 단위 결정
  * 참고
    * skipLast() : suppress 'the final n items' emitted by an Observable
      * skip()과는 달리 마지막 n개를 skip
    * skipWhile()
    
### 3. Transforming Observables

* buffer()
  * Observable로부터 emit되는 아이템 하나하나를 그대로 발행하지 않고,
  * 정해진 개수의 아이템으로 이뤄진 'Bundle'단위로 발행
  * 즉 한 번 발행 시 여러 아이템으로 이뤄진 'Bundle' 단위로 이뤄진다는 것
  * parameter
    * count: Int => n개의 아이템으로 이뤄진 bundle로 만들어 발행하겠다
    * 여기서 bundle의 자료형은 순회 가능한 자료형이어야 함. mutable여부와는 상관 없음
      * (mutable)List
    * 즉 같은 자료형 여러 개로 쪼개진다는 의미
    
* map() ***
  * 일대일 함수
  * 'transform' the items emitted by an Observable by 'applying a function to each item'
  * js의 map와 같은 기능이라고 볼 수 있음.
  * parameter : 넘겨 받은 값을 변형하는 함수
  * 예를 들어, map { x -> x * 10 }이 적용된다면, 각 값에 10이 곱해진 값이 emit될 것이다.
    * 1, 2, 3 -> 10, 20, 30
  * 단순히 기존의 값을 변경할 뿐만 아니라, 아예 새로운 Object로 변형도 가능
    * 데이터 클래스 종류 변경 등...
    
* flatMap()
  * 일대다 혹은 일대일의 Observable 함수
  * transform the items emitted by an Observable into other Observable(s) (one or more),
  * then 'flatten' the emissions from those into a 'single' Observable.=> 최종적으로는 1개의 Observable
  * flatMap() always returns another observable
    * 문자열로 된 리스트를 가지고 있고 각 문자열을 단어로 분할하고 싶다고 가정
    * 각 단어를 대문자로 변환하여 최종적으로 변환된 단어들을 포함하는 리스트를 얻고자 함

      fun main() {
        val stringList = listOf("Hello World", "RxJava is awesome")

        val resultObservable = Observable.fromIterable(stringList)
          .flatMap { sentence ->
             Observable.fromIterable(sentence.split(" "))
                  .map { word -> word.toUpperCase() }
          }

         resultObservable.subscribe { word ->
            println(word)
         }
      }
    
    위의 코드에서, 우리는 stringList라는 문자열 리스트를 가지고 시작합니다. Observable.fromIterable을 사용하여 이를 Observable로 변환합니다. 
    그런 다음 flatMap 연산자를 사용하여 각 문자열을 단어로 분할하고, 각 단어를 대문자로 변환하는 작업을 수행하는 Observable로 변환합니다. 
    이 작업은 flatMap 블록 내부의 Observable.fromIterable과 map을 통해 이루어집니다.
  
    **flatMap은 '각' 문자열을 Observable로 '변환한 후'에, 이들을 '병합'하여 하나의 Observable로 만들어줍니다.** 따라서 resultObservable은 변환된 단어들을 포함하는 하나의 Observable입니다.
    
    마지막으로, subscribe를 통해 결과 Observable을 구독하고 변환된 단어를 출력합니다.

* groupBy()
  * divide an Observable into a **set** of Observables
  * each set, emit a different subset of items from the original Observable
  * parameter : 정렬 기준 -> 이것은 곧 key를 나타내는 값이 됨
  
### 4. Combining Observables

* merge()
  * combine multiple observables into one by '**merging emissions**'.
  * 각각 다른 타이밍에 다른 아이템을 발행되는 2개의 Observable들을 하나의 Observable로 합침
  * parameter
    * 합치고자 하는 Observables 나열
    
* concat()
  * emit the emissions from two or more Observables without interleaving them
  * 들어간 Observable 순서에 따른 발행.
    * merge()의 특징이 바로 합쳐진 Observable들의 발행에 있어 interleaving이 이뤄짐
    * 즉, merge 함수에 들어간 순서에 따른 발행이 보장되지 않는다는 것이다.
  * concat()에서는 끼어들기 없음. 즉 merge와는 달리 
  * 앞의 Observable의 발행이 끝나고 나서야 다음 Observable의 발행이 시작됨.
  * merge vs concat 예시
    * Observable 1 : 1 -- 1초 뒤 -- 2 -- 2초 뒤 -- 3 -- end
    * Observable 2 : 2 -- 0.5초 뒤 -- 2 -- end
    * merge()인 경우, 순서 보장 못함.
    * concat()인 경우, 1 -- 1초 뒤 -- 2 -- 2초 뒤 -- 3 -- end -- 2 -- 0.5초 뒤 -- 2
  * 참고 : Observable변수.concatWith(다른 Observable 변수)
    * 두 observable를 concat하는 다른 방법
    
* startWith()
  * parameter에 특정 값을 넣으면, 그 값이 발행되고 나서야 Observable의 item에 대한 발행이 시작됨
  * Observable변수명.startWith(값 혹은 다른 Observable 혹은 Single)
  
* zip()
  * 여러 개의 observable에서 발행되는 아이템을 짝지어 특정 함수처리하여 새로운 아이템을 발행할 수 있도록 하는 operator
  * 매개변수
    * zip할 Observer들(최대 9개), zip에 이용할 함수인 zipper(BiFunction)
  * 만약 두 Observable에서 발행되는 아이템의 개수가 다르다면, 적은 쪽의 개수만큼만 발행된다.
  * ex) 
    * observable 1 : 1  2  3  4  5
    * observable 2 : A  B  C  D
    * result : 1A  2B  3C  4D

---

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

## Disposables

### What is a Disposable?
* 왜 쓰는가?
  * Observable + Observer를 만들어 비동기 처리에 사용
  * 값이 어떤 Observable로부터 emit되면, 
    * Observer는 그 Observable를 관찰하며 onNext를 통해 해당 데이터를 받음
      * 그 데이터를 활용하여, UI 업데이트 등의 작업을 수행
    * 그러다가, 더 이상 유저가 해당 UI를 볼 수 없는 상태가 되었다고 가정
      * Activity의 실행 종료(finish), Activity/Fragment의 Destroy 등
      * 이 때 Observer에는 어떤 일이 일어나는가?
        * Activity가 Destroy된다고 해도, Observer는 여전히 Observable를 관찰한다.
    * 그 이유로, UI가 Destroy되는 시점에, 이에 해당하는 **모든 Observable들을 처리**(Dispose, clear up)하는 것이 필요하다.
    * 이 때 사용하는 것이 바로 Disposable
  
* 사용법
  * Observer 클래스에서는 onSubscribe 콜백이 존재하는데, 이 함수의 매개변수가 바로 Disposable이다.
    * disposable을 해당 observer의 disposable 매개변수로 설정(d)
  * Activity/Fragment에서, onDestroy에서 disposable.dispose()를 호출하여 처리함
  * 재사용성을 높이기 위하여, CompositeDisposable을 이용
    * 액티비티에서 compositeDisposable 변수를 하나 선언
      > val compositeDisposable = CompositeDisposable()
      > onCreate에서
        > compositeDisposable.add(Disposable.subscribe(...)구독하는 코드 작성)   
      > onDestroy에서
        > compositeDisposable.clear()  
    * add된 subscription들은 동시에 수행된다는 것이 특징이다.
   
---

## Schedulers

### For **Threading** in RxJava
* What is a Scheduler?
  * A thread pool managing 1 or more threads
  * Asynchronous tasks are possible only with the separation of threads using a Scheduler!!!

* The principle of schedulers
  * Whenever a scheduler needs to execute a task, the scheduler takes a thread from its pool and run the task in that thread
  * 특정 쓰레드를 가져다가 어떤 동작이 해당 쓰레드 내부에서 실행되도록 하는 역할 수행



* Type of Schedulers

  * Main Thread
    * AndroidSchedulers.mainThread() ***
    * provided by the RxAndroid Extension library to RxJava
      * RxJava doesn't have any option to access Android main thread
      * to use this, RxAndroid should be implemented
    * Access to the main thread -> User interaction happens here!
    * performing some tasks(network calls...) in main thread makes UI unresponsive, eventually leads to ANR dialog.

  * Schedulers.io() ***
    * backed by an **unbounded** thread pool. 별도의 쓰레드 풀을 사용
    * used for...
      * non CPU-intensive I/O works
      * ex) interaction with the file system, network calls, database interactions

  * Schedulers.computation() ***
    * backed by a **bounded** thread pool. 고정 크기의 쓰레드 풀을 사용
    * size : "up to"(-> bounded) the number of available processors(the num of threads == the num of processors)
    * used for...
      * computational or CPU-intensive works
      * ex) resizing images, processing large data sets
    * should be careful about...
      * when allocating more computation threads than available cores, performance will degrade
      * due to context switching and thread creation overhead
      
  * Schedulers.newThread()
    * create a new thread for each unit of work scheduled
      * 새로운 쓰레드를 계속 생성
    * new thread is spawned every time and no reuse happens => expensive scheduler
    * seldom used
    
  * Schedulers.single() : 순차적으로 작업을 수행할 때
    * backed up by a single thread executing tasks sequentially in the order requested

* Default Threading in RxJava
  * if threading not specified? - ex) subscribeOn, observeOn, or both
    * the data will be emitted and processed by the current scheduler or thread(mainly by **main thread**)
    * cf) **interval()** : operate on a **computation thread** by default
* Upstream and Downstream
  Observable.just("Hello")
  .subscribe(System.out::println)
  * Upstream: Observable.just("Hello")
    Upstream은 Observable의 데이터 흐름에서 데이터를 생성하고 변환하는 부분을 나타냅니다. Upstream은 데이터를 발행하는 Observable이나 다른 연산자를 의미합니다. Upstream에서 발생한 데이터는 연속된 작업을 통해 downstream으로 전달됩니다.
  * Downstream: .subscribe(System.out::println)
    Downstream은 Observable의 데이터 흐름에서 데이터를 소비하고 처리하는 부분을 나타냅니다. Downstream은 Observer나 다른 연산자로 표현됩니다. Downstream에서는 Upstream에서 발행된 데이터를 받아들여 최종 결과를 생성하거나 추가적인 처리를 수행할 수 있습니다.    

* How to specify a thread to execute an operator?
  * use subscribeOn and(or) observeOn
  * 쓰레드를 지정하여 작업을 스케줄링하는 데 사용되는 연산자
  * Observable의 데이터 흐름과 관련된 쓰레드를 제어
    * subscribeOn : upstream, Observable의 구독 단계에서 실행되는 쓰레드 지정
      * Observable이 'subscribe될 때' 사용할 스케줄러를 지정
        * Observable의 **생성** 및 데이터 **발행** 작업이 지정한 스케줄러에서 진행
        * affects the upstream operators
        * network calls, applying a filter
        * can be called only once
        
          Observable.just("Hello")
          .subscribeOn(Schedulers.io()) // Observable은 IO Thread에서 실행
          .subscribe(System.out::println);

    * observeOn : downstream, Observable의 데이터 처리 단계에서 실행되는 쓰레드 지정
      * Observable이 **데이터**를 **처리**할 때 사용할 스케줄러 지정
      * 데이터를 처리하는 작업이 지정한 스케줄러에서 진행
      * affects the downstream operators 
      * processing data, UI updates
      * can be called multiple times
      
        Observable.just("Hello")
        .subscribeOn(Schedulers.io()) // 데이터를 발행하는 Observable이 IO 쓰레드에서 실행
        .observeOn(AndroidSchedulers.mainThread()) // 데이터 처리는 main thread에서 실행
        .subscribe(System.out::println)
      
---

## Hot and Cold Observables
https://velog.io/@haero_kim/RxJava-Cold-Hot-Observable

### Cold Observables - subscription needed + same value
* **subscription**을 전제로 순서에 따른 아이템을 발행할 수 있는 Observable
  * observer가 observable을 subscribe하지 않으면, 발행이 작동하지 않음
* Observers는 발행받은 아이템 셋을 보유
  * Observable이 제작된 방식에 따라 발행되는 아이템의 인스턴스는 다름
  * 한 Observable에 대한 subscription이 진행 시, 여러 번 진행하면 항상 같은 값(들)이 발행된다.
  * every time we observe the same observable, we get the same value(s) -> 손실도 없고, 새로 생기는 것도 없음
* 유튜브에서 이미 올라온 영상을 클릭하는 상황에 비유 가능
  * 그 누가 되었든, 특정 영상을 클릭하면 0초부터 영상의 끝까지 같은 정보를 제공받음. 시점은 중요하지 않음
* 실제 구현에서 데이터를 취급하는 Observable들은 대부분 Cold Observable
  * Retrofit, Room Queries

### Hot Observables
* Observer의 존재 여부와 상관 없이, Hot Observable은 아이템을 발행한다.
* 특정 Observable을 언제 subscribe하느냐에 따라 observer가 발행받는 데이터가 달라짐
  * 유튜브의 구독 및 알림설정 예시 - 구독 시점에 따라 다른 결과물 발행받음
    * 우리가 특정 채널의 유튜브 채널을 구독하기 전에는, 구독 이전 시점에 올라온 영상들에 대한 알림을 받지 못함
    * 다만 해당 채널에서는 쭉 영상을 올려오고 있었음
    * 그러다가 구독을 한 이후, 올라오는 영상들에 대한 통지를 받을 수 있음.
      * 영상이 올라오면, 모든 구독자들이 같은 데이터를 동시에 접하게 됨.
* 정해진 데이터셋을 발행하는 Cold Observable과는 달리, Hot Observable은 이벤트를 발행한다.
* Hot Observable을 구현하는 방법
  * Observable + publish() + connect() => ConnectableObservable
    * doesn't begin emitting an item when it is subscribed -> only when connect method is called, emitting starts
    * to start emitting, call connect method
  * Subjects

### ConnectableObservable
https://velog.io/@haero_kim/RxJava-Cold-Hot-Observable
* takes any Observable and makes it ***hot***
* convert cold observable into hot observable
* Single Observable Source for different (multiple) observers
  * using connect() -> emit
* multicasting -> emit되기 시작하면, 같은 시간에 같은 데이터는 subscribe된 모든 observer에게 발행

