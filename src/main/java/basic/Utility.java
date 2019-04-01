package basic;

public class Utility {

    public static void sleep(long milliSec){
        try {
            Thread.sleep(milliSec);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
