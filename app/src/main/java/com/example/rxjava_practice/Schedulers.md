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
      
* How to use Schedulers?
  * 