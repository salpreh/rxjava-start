package com.salpreh.rxjavastart.chapter3;

import io.reactivex.Observable;

public class ReducingOperators {
    public static void main(String[] args) { }

    public static void reduceOp() {
        getIntegerObservable()
            .reduce(Integer::sum)
            .subscribe(System.out::println);
    }

    public static void allOp() {
        getIntegerObservable()
            .all(i -> i < 6)
            .subscribe(System.out::println);
    }

    public static void anyOp() {
        getIntegerObservable()
            .any(i -> i < 6)
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
