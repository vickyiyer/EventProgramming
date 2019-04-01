package basic;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

/**
 * When you call the subscribe() method on an Observable, an Observer is used to consume these
 * three events by implementing its methods.
 */
public class ObserverWorking_5 {
    public static void main(String[] args) {

        //Let's create an Observer here.
        Observer<String> nameObserver=new Observer<String>() {
            @Override
            public void onSubscribe(Disposable disposable) {
                //We will work about this method afterwards
            }

            @Override
            public void onNext(String s) {
                System.out.println("Got the name: "+ s);

            }

            @Override
            public void onError(Throwable throwable) {

                System.err.println(throwable.getStackTrace());
            }

            @Override
            public void onComplete() {
                System.out.println("Completed, have consumed all events emitted by observable");

            }
        };


        Observable.just("Alpha", "Beta", "Gamma", "Delta", "Epsilon")
                .subscribe(nameObserver);


        /**
         * Instead of implementing the observer, we can use lambdas to implement
         * the observer methods
         * Consumer<Integer> onNext = i ->  System.out.println("RECEIVED: "          + i);
         * Action onComplete = () -> System.out.println("Done!");
         * Consumer<Throwable> onError = Throwable::printStackTrace;
         */

        Observable.just("Alpha", "Beta", "Gamma", "Delta", "Epsilon")
                .subscribe(s-> System.out.println("Emission: "+ s), Throwable::printStackTrace,
                        ()-> System.out.println("Completed Emission"));

        /**
         * Note that there are other overloads for subscribe().
         * You can omit onComplete() and only implement onNext() and onError().
         * This will no longer perform any action for onComplete(), but there will
         * likely be cases where you do not need one:
         */

        Observable.just("Alpha", "Beta", "Gamma", "Delta", "Epsilon")
                .subscribe(s-> System.out.println("Emission: "+ s), Throwable::printStackTrace);

        /**
         * However, not implementing onError() is something you want to avoid doing in production.
         * Errors that happen anywhere in the Observable chain will be propagated to onError() to be handled
         * and then terminate the Observable with no more emissions. If you do not specify an action for onError,
         * the error will go unhandled.
         *
         */

    }
}
