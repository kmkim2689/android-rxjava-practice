### Operators By Category

* references
    * https://reactivex.io/documentation/operators.html
    * 각 operator들의 이름을 클릭하면 시각화된 설명 확인 가능.

### 0. the form of observer
* verbose codes -> causes boilerplate codes

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
    
* create()
  * for ***customizing*** our own implementation!!!
  * Emitter를 이용하여 직접 아이템을 발행하고, 아이템 발행의 완료나 오류(Complete/Error)의 알림을 직접 설정
  * parameter
    * ObserveOnSubscribe(메소드)
    * 메소드에서 어느 상황에서 어떠한 방식으로 onNext, onComplete, onError 콜백이 수행될지 직접 구현
    * 원시 데이터를 이용하여 onNext의 발행이 어떻게 될지 정할 수 있음

* filter()
  * emit only those items from an Observable that pass a predicate test
  * parameter : function to filter(넘겨주는 값 -> 넘겨주는 값을 이용한 조건(t/f))
    * ex) Observable.filter { x -> x > 10 }
    * 10이 넘는 숫자만 emit