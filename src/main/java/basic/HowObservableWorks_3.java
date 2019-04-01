package basic;

import io.reactivex.Observable;

import java.util.List;

/**
 * At the highest level, an Observable works by passing three types of events
 *
 * onNext(): This passes each item one at a time from the source Observable all the way down to the Observer.
 *
 * onComplete(): This communicates a completion event all the way down to the Observer, indicating that no more
 * onNext() calls will occur.
 *
 * onError(): This communicates an error up the chain to the Observer, where the Observer typically
 * defines how to handle it. Unless a retry() operator is used to intercept the error, the Observable
 * chain typically terminates, and no more emissions will occur.
 */
public class HowObservableWorks_3 {

    public static void main(String[] args) {



        Observable<String> observableUsingCreate= Observable.create(observableEmitter -> {

            //The onNext() method is a way to hand each item, starting with rinki, to the next
            // step in the chain. In this example, the next step is the Observer

            /**
             * Note that the Observable contract (http://reactivex.io/documentation/contract.html) dictates
             * that emissions must be passed sequentially and one at a time. Emissions cannot be passed by
             * an Observable concurrently or in parallel. This may seem like a limitation, but it does in fact
             * simplify programs and make Rx easier to reason with. We will learn some powerful tricks to
             * effectively leverage concurrency and parallelization , without breaking the Observable contract.
             */

            try {
                observableEmitter.onNext("rinki");
                observableEmitter.onNext("pinki");
                observableEmitter.onNext("dinki");
                observableEmitter.onNext("tinki");
                observableEmitter.onNext("minkiii");
                observableEmitter.onComplete();
            } catch (Throwable e) {
                e.printStackTrace();
            }
        });


        observableUsingCreate.subscribe(System.out::println, Throwable::printStackTrace);

        //You can also create intermediate observables using the operators.
        Observable<Integer> nameLength=observableUsingCreate.map(String::length);

        Observable<Integer> filterLength=nameLength.filter(l->l<=5);

        filterLength.subscribe(System.out::println);

        //Since map and filter and producing another obeservable, hence we can chain these operators
        //Chaining operators in this way is common (and encouraged) in reactive programming.
        observableUsingCreate.map(String::length).filter(l->l<=5).subscribe(System.out::println);

        /**
         * In RxJava 2.0, Observables no longer support emitting null values. You will
         * immediately get a non-null exception if you create an Observable that attempts
         * to emit a null value. If you need to emit a null, consider wrapping it in a Java 8
         * or Google Guava Optional.
         */




    }

}
