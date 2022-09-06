import java.lang.Thread;
public class BurgerMaker {

    public void toastBuns() {
        try {
            System.out.println("Toasting buns...");
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            System.out.println(e);
        }
    }

    public void grillPatty() throws InterruptedException {
        System.out.println("Grilling patty...");
        Thread.sleep(1500);
    }

    public void meltCheese() throws InterruptedException {
        System.out.println("Melting cheese...");
        Thread.sleep(800);
    }

    public void fryOnions() throws InterruptedException {
        System.out.println("Frying onions...");
        Thread.sleep(1000);
    }

    public void assembleBurger() throws InterruptedException {
        System.out.println("Assembling burger...");
        Thread.sleep(1500);
    }

    public static void main(String[] args) throws InterruptedException {
        BurgerMaker burgerMaker = new BurgerMaker();
        Thread t1 = new Thread(() -> burgerMaker.toastBuns());
        Thread t2 = new Thread(() -> {
            try {
                burgerMaker.grillPatty();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        Thread t3 = new Thread(() -> {
            try {
                burgerMaker.meltCheese();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        Thread t4 = new Thread(() -> {
            try {
                burgerMaker.fryOnions();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        Thread t5 = new Thread(() -> {
            try {
                burgerMaker.assembleBurger();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        t1.start();
        t2.start();
        t4.start();
        t2.join(); // must grill patty before melting cheese on it
        t3.start(); // ready to melt cheese
        t1.join();
        t3.join();
        t4.join();
        // must complete all other steps before assembling burger
        t5.start();
        t5.join(); // waiting for the burger assembly task to complete
        System.out.println("Burger complete!");
    }

}
