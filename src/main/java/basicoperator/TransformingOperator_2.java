package basicoperator;

import io.reactivex.Observable;

public class TransformingOperator_2 {

    public static void main(String[] args) {

        Observable.just(1,2,3,4,5,6)
                .scan((accumlator, sum)->accumlator+sum)
                .subscribe(System.out::println);


    }
}
