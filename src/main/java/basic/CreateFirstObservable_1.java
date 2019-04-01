package basic;

import io.reactivex.Observable;

public class CreateFirstObservable_1 {

    public static void main(String[] args) {

        Observable<String> observable=
                Observable.just("suraj", "chandan", "raghu", "manoj", "raju");

        //Just having observable is meaningless, the observable will not push until you have a
        //Subscriber
        observable.subscribe(System.out::println);

        //Observable will replay the events right from start for all subscribers
        observable.subscribe(s-> System.out.println("Again "+ s));

        /**
         * We can also use several operators between Observable and Observer to transform each pushed item
         * or manipulate them in some way. Each operator returns a new Observable derived-off the previous
         * one but reflects that transformation.
         */

        observable.map(s->s.toUpperCase()).subscribe(s-> System.out.println("Uppsercase: "+ s));



    }
}
