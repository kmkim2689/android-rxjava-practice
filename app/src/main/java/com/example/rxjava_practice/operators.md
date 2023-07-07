### Operators By Category

* references
    * https://reactivex.io/documentation/operators.html
    * 각 operator들의 이름을 클릭하면 시각화된 설명 확인 가능.

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