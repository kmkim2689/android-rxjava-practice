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
