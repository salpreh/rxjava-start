package com.salpreh.rxjavastart.chapter2;

import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.observables.ConnectableObservable;

public class ObservableExamples {
    public static void main(String[] args) throws Exception {
        // exCreate();
        // exCreateErr();
        exHotObservable();
    }

    public static void exCreate() {
        Observable<String> strings = Observable.create(emitter -> {
            emitter.onNext("Alpha");
            emitter.onNext("Beta");
            emitter.onNext("Gamma");
            emitter.onNext("Delta");
            emitter.onNext("Epsilon");
        });

        strings
            .map(String::toUpperCase)
            .subscribe(System.out::println);
    }

    public static void exCreateErr() {
        Observable<String> strings = Observable.create(emitter -> {
            emitter.onNext("Alpha");
            emitter.onNext("Beta");
            emitter.onNext("Gamma");
            emitter.onNext("Delta");
            emitter.onError(new RuntimeException("rxerror"));
            emitter.onNext("Epsilon");
        });

        strings
            .map(String::toUpperCase)
            .subscribe(System.out::println);
    }

    public static void exHotObservable() throws InterruptedException {
        ConnectableObservable<Long> infCount = Observable.interval(1, TimeUnit.SECONDS)
            .publish();

        infCount.subscribe(i -> System.out.println("-s1: " + i));
        infCount.subscribe(i -> System.out.println("+s2: " + i));
        
        infCount.connect();
        Thread.sleep(3000);

        infCount.subscribe(i -> System.out.println("*s3: " + i));
        Thread.sleep(5000);
    }
}
