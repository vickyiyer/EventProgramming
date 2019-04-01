package basic;


import io.reactivex.Observable;

import java.util.concurrent.TimeUnit;



/**
 * As we saw earlier, you can push not only data, but also events.
 * For instance, Observable.interval()
 * will push a consecutive Long at each specified time interval,
 * as shown in the following code snippet. This Long emission is not only data,
 * but also an event! Let's take a look:
 */

public class PushEvents_2 {

    public static void main(String[] args) {
        Observable<Long> pushEventInterval=
                Observable.interval(1, TimeUnit.SECONDS);

        pushEventInterval.subscribe(System.out::println);

        /**Hold main thread for 5 sec so that the observable above
         * has a chance to fire
         */

        sleep(5000);

        //This simple idea that data is a series of events over time will unlock new possibilities in
        // how we tackle programming.

        /**
         * On a side note, we will get more into concurrency later, but we had to create a sleep() method because
         * this Observable fires emissions on a computation thread when subscribed to. The main thread used to
         * launch our application is not going to wait on this Observable since it fires on a computation thread,
         * not the main thread. Therefore, we use sleep() to pause the main thread for 5000 milliseconds and then
         * allow it to reach the end of the main() method (which will cause the application to terminate).
         * This gives Observable.interval() a chance to fire for a five second window before the application quits.
         */

    }


    static void sleep(long milliSec){

        try {
            Thread.sleep(milliSec);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
