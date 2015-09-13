# TestingRxJava

RxJava is asynchronous by nature, so unit testing it might seem a daunting at first, especially if you use that asynchronous interaction to test stuff. Luckily, RxJava (and RxAndroid) come with a couple of tools that will make our life a lot easier. 

This sample projects demonstrates how to:

* test an observable (or a transformation of one or more observables) by using [TestSubscriber](http://reactivex.io/RxJava/javadoc/rx/observers/TestSubscriber.html)
* test how your app react to a subscription by mocking the real observable and overriding the default rxjava / rxandroid schedulers

More details can be found in [this post from my blog](http://fedepaol.github.io/blog/2015/09/13/testing-rxjava-observables-subscriptions/)
