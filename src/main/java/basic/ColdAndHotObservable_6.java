package basic;

import io.reactivex.Observable;
import io.reactivex.observables.ConnectableObservable;

/**
 * There are subtle behaviors in a relationship between an Observable and an Observer
 * depending on how the Observable is implemented. A major characteristic to be aware of is cold versus
 * hot Observables, which defines how Observables behave when there are multiple Observers
 */

public class ColdAndHotObservable_6{

    /**
     * Cold Observables are much like a music CD that can be replayed to each listener, so each person can hear all
     * the tracks at any time. In the same manner, cold Observables will replay the emissions to each Observer,
     * ensuring that all Observers get all the data. Most data-driven Observables are cold, and this includes the
     * Observable.just() and Observable.fromIterable() factories.
     */

    public static void main(String[] args) {

        //Example of a cold observable
        Observable<String> source =
                Observable.just("Alpha","Beta","Gamma","Delta","Epsilon");

        source.subscribe(s-> System.out.println("Source 1 "+ s));
        source.map(String::toUpperCase).subscribe(s-> System.out.println("Source 2 "+ s));

        // hot Observables that resemble events more than data.
        /**
         * the cold Observable, which works much like a music CD. A hot Observable is more like a radio station.
         * It broadcasts the same emissions to all Observers at the same time. If an Observer subscribes to a
         * hot Observable, receives some emissions, and then another Observer comes in afterwards, that second
         * Observer will have missed those emissions. Just like a radio station, if you tune in too late,
         * you will have missed that song.
         */

       //We will take this up later. Just understand the concept.

        /**
         * A helpful form of hot Observable is ConnectableObservable. It will take any Observable,
         * even if it is cold, and make it hot so that all emissions are played to all Observers at once.
         * To do this conversion, you simply need to call publish() on any Observable, and it will yield
         * a ConnectableObservable. But subscribing will not start the emissions yet. You need to call its connect()
         * method to start firing the emissions.
         */

        //Using ConnectableObservable to force each emission to go to all Observers simultaneously is
        // known as multicasting
        ConnectableObservable<String> connectableObservable=
                Observable.just("Alpha","Beta","Gamma","Delta","Epsilon").publish();


        connectableObservable.subscribe(s-> System.out.println("First observer: "+ s));
        connectableObservable.map(String::length).subscribe(s-> System.out.println("Second observer: "+ s));

        connectableObservable.connect();


    }


}
