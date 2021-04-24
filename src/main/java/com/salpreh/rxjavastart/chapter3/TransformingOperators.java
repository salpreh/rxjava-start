package com.salpreh.rxjavastart.chapter3;

import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;

public class TransformingOperators {
    public static void main(String[] args) throws Exception {
        scanOp();
    }


    public static void mapOp() {
        getStringObservable()
            .map(String::length)
            .subscribe(System.out::println);
    }

    public static void castOp() {
        getStringObservable()
            .map(s -> s.charAt(0))
            .cast(Object.class)
            .subscribe(System.out::println);
    }

    public static void defaultIfEmptyOp() {
        getStringObservable()
            .filter(s -> s.startsWith("F"))
            .defaultIfEmpty("None")
            .subscribe(System.out::println);
    }

    public static void switchIfEmptyOp() {
        getStringObservable()
            .filter(s -> s.startsWith("F"))
            .switchIfEmpty(Observable.just("Sigma", "Omega"))
            .subscribe(System.out::println);
    }

    public static void delayOp() throws InterruptedException {
        getStringObservable()
            .delay(1, TimeUnit.SECONDS)
            .subscribe(System.out::println);

        TimeUnit.SECONDS.sleep(4);
    }

    public static void scanOp() {
        getIntegerObservable()
            .scan((acc, n) -> acc + n)
            .subscribe(System.out::println);
    }

    private static Observable<String> getStringObservable() {
        return Observable.just(
            "Alpha", "Beta", "Gamma", "Delta", "Epsilon"
        );
    }

    private static Observable<Integer> getIntegerObservable() {
        return Observable.just(
            1, 2, 3, 4, 5, 5, 4, 6, 7
        );
    }
}
