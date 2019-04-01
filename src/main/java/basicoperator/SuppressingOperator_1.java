package basicoperator;

import basic.Utility;
import io.reactivex.Observable;

import java.util.concurrent.TimeUnit;

public class SuppressingOperator_1 {

    /**
     * You should strive to execute as much logic as possible using RxJava operators,
     * and you should use an Observer to receive the end product emissions that are ready
     * to be consumed.
     * There are a number of operators that will suppress emissions that fail to meet a
     * specified criterion. These operators work by simply not calling the onNext() function
     * downstream for a disqualified emission, and therefore does not go down the chain to Observer.
     */

    public static void main(String[] args) {

        //filter()
        Observable.just("Alpha", "Beta", "Gamma", "Delta", "Epsilon")
                .filter(s->s.length()>5)
                .subscribe(System.out::println);

        System.out.println("-----------");

        //take() One will take a specified number of emissions and then call  onComplete() after it captures all of them
        Observable.just("Alpha", "Beta", "Gamma", "Delta", "Epsilon")
                .take(2)
                .subscribe(System.out::println);


        //Also you can take in specified time interval
        /**
         * Note that there is also a takeLast() operator, which will take the last specified number
         * of emissions (or time duration) before the onComplete()  function is called. Just keep in
         * mind that it will internally queue emissions until its onComplete() function is called, and then
         * it can logically identify and emit the last emissions.
         */

        Observable.interval(300, TimeUnit.MILLISECONDS)
                .take(3, TimeUnit.SECONDS)
                .subscribe(System.out::println);

        Utility.sleep(5000);

        //skip()
        /**
         * The skip() operator does the opposite of the take() operator. It will ignore the specified number
         * of emissions and then emit the ones that follow. Skip also has SkipLast().
         */


        Observable.interval(300, TimeUnit.MILLISECONDS)
                .skip(2, TimeUnit.SECONDS)
                .subscribe(i-> System.out.println("Skip: "+ i));


        Utility.sleep(5000);


        //takeWhile() skipWhile(), will only take emissions while the predicate is tru from begining.
        Observable.just("Alpha", "Beta", "Gamma", "Delta", "Epsilon", "Omega", "Gigamage")
                .takeWhile(s->s.length()<=5)
                .subscribe(s -> System.out.println("takewhile "+ s));

        Observable.just("Alpha", "Beta", "Gamma", "Delta", "Epsilon", "Omega", "Gigamage")
                .skipWhile(s->s.length()<6)
                .subscribe(s -> System.out.println("skipWhile "+ s));





    }
}
