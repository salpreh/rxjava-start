package com.salpreh.rxjavastart.chapter3;

import java.util.HashSet;

import io.reactivex.Observable;

public class CollectionOperators {

    public static void main(String[] args) {
        sortedListOp();
        mapOp();
        multiMapOp();
    }

    public static void sortedListOp() {
        getStringObservable()
            .toSortedList((a, b) -> Integer.compare(a.length(), b.length()))
            .subscribe(System.out::println);
    }

    public static void mapOp() {
        getStringObservable()
            .toMap((s) -> s.charAt(0), String::toUpperCase)
            .subscribe(System.out::println);
    }

    public static void multiMapOp() {
        getStringObservable()
            .toMultimap(String::length, String::toUpperCase)
            .subscribe(System.out::println);
    }

    public static void collectOp() {
        getStringObservable()
            .collect(HashSet::new, HashSet::add)
            .subscribe(System.out::println);
    }

    private static Observable<String> getStringObservable() {
        return Observable.just(
            "Alpha", "Beta", "Gamma", "Delta", "Epsilon"
        );
    }
}
