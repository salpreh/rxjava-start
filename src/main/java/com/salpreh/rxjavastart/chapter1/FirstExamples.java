package com.salpreh.rxjavastart.chapter1;

import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;

public class FirstExamples {
    public static void main(String[] args) throws Exception {
        // ex1();
        ex2();
    }

    public static void ex1() {
        Observable<String> strings = Observable.just(
            "Alpha", "Beta", "Gamma", "Delta", "Epsilon"
        );

        strings
            .map(String::length)
            .subscribe(System.out::println);
    }

    public static void ex2() throws InterruptedException {
        Observable<Long> interval = Observable.interval(1, TimeUnit.SECONDS);

        interval.subscribe(i -> System.out.println("-s1: " + i));
        Thread.sleep(2000);

        interval.subscribe(i -> System.out.println("*s2: " + i));
        Thread.sleep(5000);
    }
}
