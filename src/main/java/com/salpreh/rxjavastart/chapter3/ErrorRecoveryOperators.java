package com.salpreh.rxjavastart.chapter3;

import io.reactivex.Observable;

public class ErrorRecoveryOperators {
    public static void main(String[] args) {
        onErrorResuemOp();
        System.out.println("-------");
        mapErrorHandling();
        System.out.println("-------");
        retryOp();
    }

    public static void onErrorResuemOp() {
        getIntegerObservable()
            .map(i -> 100 / i)
            .onErrorReturnItem(-1)
            .subscribe(System.out::println);
    }

    /**
     * {@link Observable#onErrorReturnItem(Object)} stops emisions after treating 
     *  the error. For treat the error and keep emissions treat error where it will occur
     */
    public static void mapErrorHandling() {
        getIntegerObservable()
            .map(i -> {
                try {
                    return 100 / i;
                } catch (ArithmeticException e) {
                    return -1;
                }
            })
            .subscribe(System.out::println);
    }

    /**
     * NOTE: Retry will resubscribe, so for cold Observables all events will be re-emitted
     */
    public static void retryOp() {
        getIntegerObservable()
            .map(i -> 100 / i)
            .retry(2)
            .subscribe(System.out::println, Throwable::printStackTrace);
    }

    private static Observable<Integer> getIntegerObservable() {
        return Observable.just(
            2, 4, 5, 6, 8, 0 ,10
        );
    }
}
