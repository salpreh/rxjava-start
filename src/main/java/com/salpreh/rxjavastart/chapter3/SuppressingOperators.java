package com.salpreh.rxjavastart.chapter3;

import io.reactivex.Observable;

public class SuppressingOperators {
    public static void main(String[] args) {}

    public static void filterOp() {
        getStringObservable()
            .filter(s -> s.length() > 4)
            .subscribe(System.out::println);
    }

    public static void takeOp() {
        getStringObservable()
            .take(3)
            .subscribe(System.out::println);
    }

    public static void skipOp() {
        getStringObservable()
            .skip(2)
            .subscribe(System.out::println);
    }

    public static void takeWhileOp() {
        getStringObservable()
            .takeWhile(s -> !s.equals("Gamma"))
            .subscribe(System.out::println);
    }

    public static void distinctOp() {
        getIntegerObservable()
            .distinct()
            .subscribe(System.out::println);
    }

    public static void distinctUntilChangedOp() {
        getIntegerObservable()
            .distinctUntilChanged()
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
