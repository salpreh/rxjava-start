package com.salpreh.rxjavastart.chapter2;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

public class ObserverExamples {
    public static void main(String[] args) {
        // observerImp();
        observerImpLamb();
    }

    public static void observerImp() {
        Observable<String> strings = Observable.just(
            "Alpha", "Beta", "Gamma", "Delta", "Epsilon"
        );

        Observer<String> observer = new Observer<>() {

            @Override
            public void onSubscribe(Disposable d) {
                // No implemented
            }

            @Override
            public void onNext(String t) {
                System.out.println("EV: " + t);
            }

            @Override
            public void onError(Throwable e) {
                e.printStackTrace();
            }

            @Override
            public void onComplete() {
                System.out.println("Completed!");
            }
        };

        strings.subscribe(observer);
    }

    public static void observerImpLamb() {
        Observable<String> strings = Observable.just(
            "Alpha", "Beta", "Gamma", "Delta", "Epsilon"
        );

        strings
            .map(s -> {
                if (s.equals("Delta")) throw new RuntimeException("Delta not allowed"); // Force an error
                return s;
            })
            .map(String::toUpperCase)
            .subscribe(
                System.out::println, 
                Throwable::printStackTrace
            );
    }
}
