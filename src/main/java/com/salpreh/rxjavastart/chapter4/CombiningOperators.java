package com.salpreh.rxjavastart.chapter4;

import java.util.List;
import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.Single;
import io.reactivex.disposables.Disposable;

public class CombiningOperators {
    public static void main(String[] args) throws Exception {
        // mergeWithOp();
        // System.out.println("-------");
        // flatMapOp();
        // System.out.println("-------");
        // mergeOpWithIntervalObs();
        // System.out.println("-------");
        // concatOp();
        System.out.println("-------");
        zipOp();
        System.out.println("-------");
        groupByOp();
    }

    public static void mergeOp() {
        Observable.merge(getStringObservable(), getStringObservable2())
            .subscribe(System.out::println);
    }

    public static void mergeWithOp() {
        getStringObservable()
            .map(String::length)
            .mergeWith(getIntegerObservable())
            .subscribe(System.out::println);
    }

    public static void mergeOpWithIntervalObs() throws InterruptedException {
        Observable<Double> obs1 = Observable.interval(1, TimeUnit.SECONDS)
            .map(i -> i + 1D);

        Observable<Double> obs2 = Observable.interval(250, TimeUnit.MILLISECONDS)
            .map(i -> i * 0.250 + 0.250);

        Disposable d = Observable.merge(obs1, obs2)
            .subscribe(s -> System.out.println("SECONDS: " + s));

        TimeUnit.SECONDS.sleep(3);
        d.dispose();
    }

    public static void flatMapOp() {
        getStringObservable()
            .flatMap(s -> Observable.fromArray(s.split("")))
            .subscribe(System.out::println);
    }

    public static void concatOp() throws InterruptedException {
        Observable<Double> obs1 = Observable.interval(1, TimeUnit.SECONDS)
            .map(i -> i + 1D)
            .take(3);

        Observable<Double> obs2 = Observable.interval(250, TimeUnit.MILLISECONDS)
            .map(i -> i * 0.250 + 0.250)
            .take(12);

        Disposable d = Observable.concat(obs1, obs2)
            .subscribe(s -> System.out.println("SECONDS: "+ s));

        TimeUnit.SECONDS.sleep(6);
        d.dispose();
    }

    public static void concatMapOp() {
        getStringObservable()
            .concatMap(s -> Observable.fromArray(s.split("")))
            .subscribe(System.out::println);
    }

    public static void ambiguousOp() throws InterruptedException {
        Observable<Double> obs1 = Observable.interval(1, TimeUnit.SECONDS)
            .map(i -> i + 1D);

        Observable<Double> obs2 = Observable.interval(250, TimeUnit.MILLISECONDS)
            .map(i -> i * 0.250 + 0.250);

        Disposable d = Observable.amb(List.of(obs1, obs2))
            .subscribe(s -> System.out.println("SECONDS: " + s));

        TimeUnit.SECONDS.sleep(3);
        d.dispose();
    }

    public static void zipOp() {
        Observable.zip(getStringObservable(), Observable.range(1, 10), (s, i) -> s +"-"+ i)
            .subscribe(System.out::println);
    }

    public static void groupByOp() {
        getStringObservable()
            .groupBy(String::length, String::toUpperCase)
            .flatMapMaybe(g -> g.reduce((a, i) -> a + "," + i)
                .map(r -> g.getKey() +": "+ r)
            )
            // TODO: This version keeps blocked indefinitely IDK why
            // .flatMapSingle(g -> Single.just(
            //     g.getKey() +": "+ g.reduce((a, i) -> a + "," + i).blockingGet()
            // ))
            .subscribe(System.out::println);
    }

    private static Observable<String> getStringObservable() {
        return Observable.just(
            "Alpha", "Beta", "Gamma", "Delta", "Epsilon"
        );
    }

    private static Observable<String> getStringObservable2() {
        return Observable.just(
            "Sigma", "Tau", "Phi"
        );
    }

    private static Observable<Integer> getIntegerObservable() {
        return Observable.just(
            1, 2, 3, 4, 5, 5, 4, 6, 7
        );
    }
}
