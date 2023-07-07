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

* range => 파이썬 for문의 range와 유사함.
  * fun range(start: Int, count: Int): Observable<Int>
  * start : the value of 'the first integer' in the 'sequence'
  * count : the number of sequential integers to generate
    * how many sequential you want to generate
  * return : an observable that emits a sequence of integer within a specified range

* repeat
  * range operator와 함께 사용
  * range(start, range).repeat(iteration)
  * range operator를 반복할 횟수를 정함