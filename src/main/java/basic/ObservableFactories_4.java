package basic;
/**
 * Observable factories
 */

import io.reactivex.Observable;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.observables.ConnectableObservable;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class ObservableFactories_4 {

    public static void main(String[] args) {

        //Using Observable.just()
        Observable.just("Alpha", "Beta", "Gamma", "Delta", "Epsilon")
                .map(String::length).filter(l->l<=4)
                .subscribe(System.out::println);

        // Observable.fromIterable() to emit the items from any Iterable type, such as a List.
        List<String> nameList=List.of("Alpha", "Beta", "Gamma", "Delta", "Epsilon");
        Observable.fromIterable(nameList)
                .map(String::length).filter(l->l>5)
                .subscribe(System.out::println);

        //Observable.range, long equivalent called Observable.rangeLong() if you need to emit larger numbers.
        Observable.range(3, 5).subscribe(num-> System.out.println("Number: "+ num));

        //Observable using interval
        Observable<Long> observableInterval=
                Observable.interval(1, TimeUnit.SECONDS);
        /**
         * However, because it operates on a timer, it needs to run on a separate thread and will run on the
         * computation Scheduler by default.
         */
        Disposable disposable_1= observableInterval.subscribe(s-> System.out.println("First: "+ s));

        sleep(5000);

        //Hot or cold observable, the below answers the question, it is cold.

        Disposable disposable_2=observableInterval.subscribe(s-> System.out.println("Second: "+ s));

        sleep(5000);

        CompositeDisposable masterDisposable=new CompositeDisposable();
        masterDisposable.addAll(disposable_1, disposable_2);
        masterDisposable.dispose();
        //disposable_1.dispose();
        //disposable_2.dispose();

        //Let's make this observable hot by using the the connectable observable and then see what happens
        //when we connect 2 observables.

        ConnectableObservable<Long> connectableInterval=
                Observable.interval(1, TimeUnit.SECONDS).publish();

        connectableInterval.connect();

        connectableInterval.subscribe(s-> System.out.println("First_hot: "+ s));

        sleep(5000);

        connectableInterval.subscribe(s-> System.out.println("Second_hot: "+ s));
        sleep(5000);


        //Observable.empty(),  create an Observable that emits nothing and calls onComplete()
        //Remember An empty Observable is essentially RxJava's concept of null

        Observable.empty().
                subscribe(System.out::println, Throwable::printStackTrace, ()-> System.out.println("Done"));

        /**
         * cousin of Observable.empty() is Observable.never(). The only difference between them is that it
         * never calls onComplete(), forever leaving observers waiting for emissions but never actually giving any.
         * This Observable is primarily used for testing and not that often in production.
         */

        Observable.never().
                subscribe(System.out::println, Throwable::printStackTrace, ()-> System.out.println("Done"));

        sleep(1000);


        /**
         * Observable.error(), This too is something you likely will only do with testing, but you can
         * create an Observable that immediately calls onError() with a specified exception:
         */

        Observable.error(()->new Exception("hack and rank")).
                subscribe(System.out::println, Throwable::printStackTrace, ()-> System.out.println("Done"));


    }

    public static void sleep(long milliSec){
        try {
            Thread.sleep(milliSec);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
