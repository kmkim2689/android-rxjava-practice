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