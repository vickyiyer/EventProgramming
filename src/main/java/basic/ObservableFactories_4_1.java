package basic;

import io.reactivex.Completable;
import io.reactivex.Maybe;
import io.reactivex.Observable;
import io.reactivex.Single;

public class ObservableFactories_4_1 {

    static int start=1;
    static int numbers=5;

    public static void main(String[] args) {

        //Observable.defer()
        /**
         * Observable.defer() is a powerful factory due to its ability to create a separate state
         * for each Observer. When using certain Observable factories, you may run into some nuances
         * if your source is stateful and you want to create a separate state for each Observer.
         * Your source Observable may not capture something that has changed about its parameters and
         * send emissions that are obsolete. Here is a simple example: we have an Observable.range()
         * built off two static int properties, start and count.
         */

        Observable<Integer> intRange=Observable.range(start, numbers);

        intRange.subscribe(s-> System.out.println("First sub "+ s));

        numbers =10; //This change is not reflected. one way to avoid this problem is to
                     // create 2 observables, but that becomes unwieldy, here is where defer offers an elegant soln

        intRange.subscribe(s-> System.out.println("Second sub "+ s));

        numbers=5;
        /**
         * Observable.defer(), which accepts a lambda instructing how to create an Observable for every
         * subscription. Because this creates a new Observable each time, it will reflect any changes
         * driving its parameters:
         */
        Observable<Integer> rangeObservable= Observable.defer(()-> Observable.range(start, numbers));


        rangeObservable.subscribe(s-> System.out.println("First sub "+ s));

        numbers=10;

        rangeObservable.subscribe(s-> System.out.println("Second sub "+ s));

        //Observable.fromCalable()
        /**
         * If you need to perform a calculation or action and then emit it, you can use Observable.just()
         * (or Single.just() or Maybe.just(), which we will learn about later). But sometimes, we want to do
         * this in a lazy or deferred manner. Also, if that procedure throws an error, we want it to be emitted
         * up the Observable chain through onError() rather than throw the error at that location in traditional
         * Java fashion
         */

        //With just the error will not get send to the observer, also just is not lazy


//        Observable.just(1/0).subscribe(s-> System.out.println("The value is "+ s),
//                e-> System.err.println("boom ..."+ e.getMessage()));

        //the last expression to get this working.
        Observable.fromCallable(()->1/0).subscribe(s-> System.out.println("The value is "+ s),
                e-> System.err.println("boom ..."+ e.getMessage()));

        //Single completable and mayBe
        /**
         * There are a few specialized flavors of Observable that are explicitly set up for one or no emissions:
         * Single, Maybe, and Completable. These all follow the Observable closely and should be intuitive to use in
         * your reactive coding workflow
         */
        //Certain RxJava Observable operators will yield a Single
        Single.just("Hello").map(String::toUpperCase)
                .subscribe(System.out::println);

        //Maybe
        /**
         * A given Maybe<T> will only emit 0 or  1 emissions. It will pass the possible emission to onSuccess(),
         * and in either case, it will call onComplete() when done. Maybe.just() can be used to create a Maybe
         * emitting the single item. Maybe.empty() will create a Maybe that yields no emission:
         */

        Maybe<String> maybeValue=Maybe.just("Hello");
        maybeValue.subscribe(System.out::println);

        Maybe<String> maybeEmpty=Maybe.empty();
        maybeEmpty.subscribe(System.out::println);

        Observable<String> source =
                Observable.just("Alpha","Beta","Gamma","Delta","Epsilon");

        source.firstElement().subscribe(System.out::println, Throwable::printStackTrace, ()-> System.out.println("Done..."));

        //Completable
        /**
         * Completable is simply concerned with an action being executed, but it does not receive
         * any emissions. Logically, it does not have  onNext() or onSuccess() to receive emissions,
         * but it does have onError() and onComplete():
         */

        Completable.fromRunnable(()->runIt()).subscribe(()-> System.out.println("Done..."));
    }

    public static void runIt(){
        System.out.println("Running..");
    }
}
