package vivek.stream;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Annonymous {

    public static void main(String[] args) {

        ExecutorService executorService= Executors.newSingleThreadScheduledExecutor();

        executorService.submit(new Runnable() {
            @Override
            public void run() {
                System.out.println("Hello World");
            }
        });

        executorService.submit(()->System.out.println("Hello World again"));

        executorService.shutdown();
    }
}
