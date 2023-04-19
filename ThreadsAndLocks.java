import java.util.HashMap;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ThreadsAndLocks {
//    static class test1 implements Runnable{
//        @Override
//        public void run() {
//            int count=0;
//            System.out.println("the thread has been started");
//            try{
//                while(count<5){
//                    System.out.println("we are currently at count "+count);
//                    count++;
//                }
//            }catch (Exception e){
//                System.out.println("the exception is "+e);
//            }
//        }
//    }
//
//    public static void main(String[] args) {
//        test1 a=new test1();
//        Thread t=new Thread(a);
//        t.start();
//    }
    public static class MyClass extends Thread{
        private String name;
        private MyObject myobj;
        public MyClass(String name,MyObject myobj){
            this.name=name;
            this.myobj=myobj;
        }
        public void run(){
            myobj.foo(name);
        }
}
        static public class MyObject{
            public synchronized void foo(String name){
                try{
                    System.out.println("Thread "+name+".foo(): starting");
                    Thread.sleep(3000);
                    System.out.println("Thread "+name+".foo(): ending");
                }catch(Exception e){
                    System.out.println(e);
                }
            }
        }
    //Locks
    static class bank{
        int balance;
        private Lock lock;
        public bank(int current){
            balance=current;
            lock=new ReentrantLock();
        }
        public int withdraw(int amount){
            lock.lock();
            int temp=balance;
            try{
                Thread.sleep(100);
                temp=temp-amount;
                Thread.sleep(100);
                balance=temp;
            }catch(Exception e){
                System.out.println(e);
            }
            lock.unlock();
            return temp;
        }
        public int deposit(int amount){
            int temp=balance;
            lock.lock();
            try{
                Thread.sleep(100);
                temp=temp+amount;
                Thread.sleep(100);
                balance=temp;
            }catch(Exception e){
                System.out.println(e);
            }
            lock.unlock();
            return temp;
        }
        public int currentBalance(){
            return balance;
        }
    }
    static class ChopStick{
        private Lock lock;
        public ChopStick(){
            lock=new ReentrantLock();
        }
        public void pickup(){
            lock.lock();
        }
        public void putDown(){
            lock.unlock();
        }
        public boolean pickUp(){
            return lock.tryLock();
        }
    }
    static class Philosopher extends Thread{
        private int count=10;
        private ChopStick left,right;
        public Philosopher(ChopStick left,ChopStick right){
            this.left=left;
            this.right=right;
        }
        public boolean pickUp(){
            if(!left.pickUp())
                return false;
            if(!right.pickUp()){
                left.putDown();
                return false;
            }

            return true;
//            left.pickup();
//            right.pickup();
        }
        public void putDown(){
            right.putDown();
            left.putDown();
        }
        public void chew(){
            try {
                Thread.sleep(100);
            }catch(Exception e){
                System.out.println(e);
            }
        }
        public void eat(){
            if(pickUp()){
                chew();
                putDown();
            }
//            pickUp();

        }
        @Override
        public void run() {
            for(int i=0;i<count;i++){
                eat();
            }
        }
    }
    //deadlock free Class
    public class Dead extends Thread{
        private HashMap<Long,Thread> hashmap=new HashMap<>();
        public synchronized void lock(long id) throws InterruptedException{
            while(hashmap.containsKey(id)){
                wait();
            }
            hashmap.put(id,Thread.currentThread());
        }
        public synchronized void unclock(long id){
            if(Thread.currentThread()==hashmap.get(id)){
                hashmap.remove(id);
                notifyAll();
            }
        }
    }
    public static class testing implements Runnable{
        @Override
        public void run() {
            System.out.println("current thread name "+Thread.currentThread().getName());
            System.out.println("current Thread priority "+Thread.currentThread().getPriority());
        }
    }
    //design a safe deadlock
    static class Safe{
        HashMap<Long,Thread> hashmap=new HashMap<>();
        public synchronized void lock(long id) throws InterruptedException{
            while(hashmap.containsValue(Thread.currentThread())){
                wait();
                System.out.println(Thread.currentThread().getName());
            }
            while(hashmap.containsKey(id)){
                wait();
            }
            hashmap.put(id,Thread.currentThread());
        }
        public synchronized void unlock(long id){
            if(hashmap.get(id)==Thread.currentThread()){
                hashmap.remove(id);
                notifyAll();
            }
        }
    }
    //call in order
    static class Order{
        private boolean firstFinished;
        private boolean secondFinished;
        private Object lock=new Object();
        public void first() throws InterruptedException{
            System.out.println("First Method is called");
            Thread.sleep(1000);
            firstFinished=true;
            synchronized (lock){
                lock.notifyAll();
            }
        }
        public void second() throws InterruptedException{
            synchronized (lock){
                while(!firstFinished){
                    lock.wait();
                }
                System.out.println("Second Method is called");
                secondFinished=true;
                Thread.sleep(1000);
                lock.notifyAll();
            }

        }
        public void third() throws InterruptedException{
            synchronized (lock){
                while(!secondFinished){
                    lock.wait();
                }
            }
            System.out.println("Third Method is called");
            Thread.sleep(1000);
        }
    }
    //Synchronized Methods
    static class synco{
        public synchronized void methodA() throws InterruptedException{
            System.out.println("i am method -A");
            Thread.sleep(1000);
        }
        public synchronized void methodB() throws InterruptedException{
            System.out.println("i am method -B");
        }
    }
    //FizzBuzz
    static class FizzBuzz{
        private int n;
        private int current=1;
        private Object lock=new Object();
        public FizzBuzz(int n){
            this.n=n;
        }
        public void Fizz() throws InterruptedException{
            while(true){
                synchronized (lock){
                    if(current> n){
                        return;
                    }
                    if(current%3==0 &&current%5!=0){
                        System.out.println("Fizz");
                        current++;
                        lock.notifyAll();
                    }else{
                        lock.wait();
                    }
                }
            }
        }
        public void buzz() throws InterruptedException{
            while(true){
                synchronized (lock){
                    if(current>n){
                        return;
                    }
                    if(current%5==0 && current%3!=0){
                        System.out.println("Buzz");
                        current++;
                        lock.notifyAll();
                    }else{
                        lock.wait();
                    }
                }
            }
        }
        public void fizzbuzz() throws InterruptedException{
            while(true){
                synchronized (lock){
                    if(current> n){
                        return;
                    }
                    if(current%3==0 && current%5==0){
                        System.out.println("FixxBuzz");
                        current++;
                        lock.notifyAll();
                    }else{
                        lock.wait();
                    }
                }
            }
        }
        public void number() throws InterruptedException{
            while(true){
                synchronized (lock){
                    if(current> n){
                        return;
                    }
                    if(current%3!=0 && current%5!=0){
                        System.out.println(current);
                        current++;
                        lock.notifyAll();
                    }else{
                        lock.wait();
                    }
                }
            }
        }
    }
    public static void main(String[] args) {
        //test case for FizzBizz
        FizzBuzz test=new FizzBuzz(20);
        Thread thread1=new Thread(new Runnable() {
            @Override
            public void run() {
                try{
                    test.Fizz();
                }catch (Exception e){
                    System.out.println(e);
                }
            }
        });
        Thread thread2=new Thread(new Runnable() {
            @Override
            public void run() {
                try{
                    test.buzz();
                }catch (Exception e){
                    System.out.println(e);
                }
            }
        });
        Thread thread3=new Thread(new Runnable() {
            @Override
            public void run() {
                try{
                    test.fizzbuzz();
                }catch (Exception e){
                    System.out.println(e);
                }
            }
        });
        Thread thread4=new Thread(new Runnable() {
            @Override
            public void run() {
                try{
                    test.number();
                }catch(Exception e){
                    System.out.println(e);
                }
            }
        });
        thread1.start();
        thread2.start();
        thread3.start();
        thread4.start();
        //test case for Synchronized Methods
//        synco test=new synco();
//        Thread thread1=new Thread(new Runnable() {
//            @Override
//            public void run() {
//                try {
//                    test.methodA();
//                    test.methodB();
//                } catch (InterruptedException e) {
//                    throw new RuntimeException(e);
//                }
//            }
//        });
//        Thread thread2=new Thread(new Runnable() {
//            @Override
//            public void run() {
//                try {
//                    test.methodB();
//                } catch (InterruptedException e) {
//                    throw new RuntimeException(e);
//                }
//            }
//        });
//        thread1.start();
//        thread2.start();
        //test case for Call in Order
//        Order test=new Order();
//        Thread t1=new Thread(new Runnable() {
//            @Override
//            public void run() {
//                try {
//                    test.first();
//                } catch (InterruptedException e) {
//                    throw new RuntimeException(e);
//                }
//            }
//        });
//        Thread t2=new Thread(new Runnable() {
//            @Override
//            public void run() {
//                try{
//                    test.second();
//                }catch (InterruptedException e){
//                    System.out.println(e);
//                }
//            }
//        });
//        Thread t3=new Thread(new Runnable() {
//            @Override
//            public void run() {
//                try{
//                    test.third();
//                }catch (Exception e){
//                    System.out.println(e);
//                }
//            }
//        }) ;
//        t3.start();
//        t1.start();
//        t2.start();
        //designing safe Lock testing
//        Safe test=new Safe();
//        Thread t1=new Thread(new Runnable() {
//            @Override
//            public void run() {
//                try{
//                    test.lock(1L);
//                    System.out.println("the lock is currently assigned to Thread 1");
//                    Thread.sleep(1000);
//                    test.unlock(1L);
//                    System.out.println("the lock has been released from Thread 1");
//                }catch(Exception e){
//                    System.out.println(e);
//                }
//            }
//        },"Thread1");
//        Thread t2=new Thread(new Runnable() {
//            @Override
//            public void run() {
//                try{
//                    test.lock(1L);
//                    System.out.println("the lock is currently has been assigned to Thread 2");
//                    Thread.sleep(1000);
//                    test.unlock(1L);
//                    System.out.println("the lock has been released from Thread-2");
//                }catch(Exception e){
//                    System.out.println(e);
//                }
//
//            }
//        },"Thread-2");
//        t1.start();
//        t2.start();
        //test case for testing
//        testing test=new testing();
//        Thread thread1=new Thread(test,"Thread-1");
//        Thread thread2=new Thread(new testing(),"Thread-2");
//        thread1.setPriority(Thread.MAX_PRIORITY);
//        thread2.setPriority(Thread.MIN_PRIORITY);
//        thread1.start();
//        thread2.start();
        //test case for the Phiospher
//        ChopStick left=new ChopStick();
//        ChopStick right=new ChopStick();
//        Philosopher test1=new Philosopher(left,right);
//        test1.start();
        //test case for Bank
//        bank test=new bank(100);
//        System.out.println(test.balance);
//        System.out.println(test.withdraw(20));
//        System.out.println(test.balance);
//        System.out.println(test.deposit(100));
//        System.out.println(test.withdraw(50));
//        System.out.println(test.balance);
        //for test case 1
//        MyObject obj1=new MyObject();
//        MyClass thread1=new MyClass("1",obj1);
//        MyClass thread2=new MyClass("2",obj1);
//        thread1.start();
//        thread2.start();
        //output
//        Thread 1.foo(): starting
//        Thread 1.foo(): ending
//        Thread 2.foo(): starting
//        Thread 2.foo(): ending
        //test case 2
//        MyObject object1=new MyObject();
//        MyObject object2=new MyObject();
//        MyClass t1=new MyClass("thread1",object1);
//        MyClass t2=new MyClass("thread2",object2);
//        t1.start();
//        t2.start();
    }
}
