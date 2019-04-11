package basicoperator;

import io.reactivex.Observable;

public class SuppressingOperator_2 {


    public static void main(String[] args) {
        /**
         * The distinct() operator will emit each unique emission, but it will suppress any
         * duplicates that follow. Equality is based on hashCode()/equals() implementation of
         * the emitted objects.
         * Keep in mind that if you have a wide, diverse spectrum of unique values, distinct() can use a bit
         * of memory. Imagine that each subscription results in a HashSet that tracks previously captured
         * unique values.
         */

        Observable.just("Alpha", "Beta", "Gamma", "Delta", "Epsilon")
                .map(String::length)
                .distinct()
                .subscribe(System.out::println);

        /**
         * You can also add a lambda argument that maps each emission to a key used for equality logic.
         * This allows the emissions, but not the key, to go forward while using the key for distinct logic.
         * For instance, we can key off each string's length and use it for uniqueness, but emit the strings
         * rather than their lengths:
         */
        Observable.just("Alpha", "Beta", "Gamma", "Delta", "Epsilon")
                .distinct(String::length)
                .subscribe(System.out::println);

        /**
         * The distinctUntilChanged() function will ignore duplicate consecutive emissions. It
         * is a helpful way to ignore repetitions until they change.
         */
        Observable.just(1,1,2,1,2,3,3,3,4,4).distinctUntilChanged().subscribe(System.out::println);

        Observable.just("Alpha", "Beta", "Zeta", "Eta", "Gamma",
                "Delta").distinct(String::length).subscribe(System.out::println);

        /**
         * You can get a specific emission by its index specified by a Long, starting at 0. After
         * that item is found and emitted, onComplete() will be called and the subscription will
         * be disposed of.
         */

        Observable.just(1,2,3,4)
                .elementAt(0)
                .subscribe(i-> System.out.println("The number is: "+ i));










    }


}
