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