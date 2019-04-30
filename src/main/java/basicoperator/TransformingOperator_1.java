package basicoperator;

import basic.Utility;
import io.reactivex.Observable;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Comparator;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class TransformingOperator_1 {

    public static void main(String[] args) {

        /**
         * For a given Observable<T>, the map() operator will transform a T emission into an R emission
         * using the provided Function<T,R> lambda.
         *
         */

        DateTimeFormatter dtf=DateTimeFormatter.ofPattern("M/d/yyyy");

        Observable.just("12/25/2019", "1/31/2019", "6/15/2019", "7/20/2018")
                .map(s-> LocalDate.parse(s, dtf))
                .subscribe(s->System.out.println("Date is "+ s));

        /**
         * A simple, map-like operator to cast each emission to a different type is cast().
         * If we want to take Observable<String> and cast each emission to an object (and return an Observable<Object>),
         */

        Observable<Object> objectList=
                Observable.just("this", "that", "what")
                .cast(Object.class);

        Observable<String> menu =
                Observable.just("Coffee", "Tea", "Espresso", "Latte");


        /**
         * For a given Observable<T>, the startWith() operator allows you to insert a T emission that
         * precedes all the other emissions.
         */
                menu
                .startWith("Cofee Menu")
                .subscribe(System.out::println);


                menu.startWithArray("Tea Menu ", "----------------")
                        .subscribe(System.out::println);


        /**
         * If we want to resort to a single emission if a given Observable comes out empty, we can use
         * defaultIfEmpty(). For a given Observable<T>, we can specify a default T emission if no emissions occur
         * when onComplete() is called.
         */

        Observable.just("Coffee", "Tea", "Espresso", "Latte")
                .filter(s->s.startsWith("x")).defaultIfEmpty("noMatch").subscribe(System.out::println);

        /**
         * Similar to defaultIfEmpty(), switchIfEmpty() specifies a different Observable to emit values from
         * if the source Observable is empty. This allows you specify a different sequence of emissions in the
         * event that the source is empty rather than emitting just one value.
         */
        Observable.just("Coffee", "Tea", "Espresso", "Latte")
                .filter(s->s.startsWith("x")).switchIfEmpty(Observable.just("Vivek", "Vinayak"))
                .subscribe(System.out::println);


        /**
         * If you have a finite Observable<T> emitting items that implement Comparable<T>, you can use
         * sorted() to sort the emissions. Internally, it will collect all the emissions and then re-emit
         * them in their sorted order. In the following code snippet, we sort emissions from Observable<Integer>so
         * that they are emitted in their natural order:
         */

        Observable.just(5,2,1,3,6,4)
                .sorted()
                .subscribe(System.out::println);

        Observable.just(5,2,1,3,6,4)
                .sorted(Comparator.reverseOrder())
                .subscribe(System.out::println);

        Observable.just(5,2,1,3,6,4)
                .sorted((x, y)->x.compareTo(y))
                .subscribe(System.out::println);

        /**
         * We can postpone emissions using the delay() operator. It will hold any received emissions and
         * delay each one for the specified time period. If we wanted to delay emissions by three seconds,
         * we could do it like this:
         */

        Observable.just("a","b","c","d","e")
                .delay(2, TimeUnit.SECONDS)
                .subscribe(System.out::println);

        Utility.sleep(5000);

        /**
         * The repeat() operator will repeat subscription upstream after onComplete() a specified number of times.
         */

        Observable.just("Alpha", "Beta", "Gamma" ,"Delta", "Epsilon")
                .repeat(2)
                .subscribe(System.out::println);
    }
}
