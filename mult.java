import java.lang.*;
public class mult implements Runnable {
    private int threadnumber;
    public mult(int j){
       this.threadnumber=j;
    }
    @Override
    public void run() {
        for (int i = 0; i < 5; i++) {
            System.out.println(i+" is running for the thread "+ threadnumber);
            try {
                Thread.sleep(1234);
            } catch (Exception e) {
                System.out.println(e);
            }
        }
    }
}
