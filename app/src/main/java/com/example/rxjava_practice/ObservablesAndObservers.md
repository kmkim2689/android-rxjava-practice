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
---