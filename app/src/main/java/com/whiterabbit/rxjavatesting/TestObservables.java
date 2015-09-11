package com.whiterabbit.rxjavatesting;

import java.util.List;
import java.util.concurrent.TimeUnit;

import rx.Observable;
import rx.Subscriber;
import rx.functions.Func1;

public class TestObservables {
    public Observable<RubberChicken> getObservable() {
        return Observable.create(new Observable.OnSubscribe<RubberChicken>() {
            @Override
            public void call(Subscriber<? super RubberChicken> subscriber) {
                try {
                    TimeUnit.SECONDS.sleep(5); // some delay..
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                subscriber.onNext(new RubberChicken("fedepaol"));
                subscriber.onNext(new RubberChicken("arubberchicken"));
                subscriber.onCompleted();
            }
        });
    }

    public Observable<List<RubberChicken>> getTransformedObservable(Observable<RubberChicken> o) {
                return o.map(new Func1<RubberChicken, RubberChicken>() {
                         @Override
                         public RubberChicken call(RubberChicken rubberChicken) {
                             rubberChicken.setName(rubberChicken.getName().toUpperCase());
                             return rubberChicken;
                         }
                     }).toList();
    }
}
