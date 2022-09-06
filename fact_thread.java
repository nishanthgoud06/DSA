import java.time.Instant;

public class fact_thread {
    public int factorial(int n){
        if(n==1)
            return 1;
        return n*factorial(n-1);
    }
    public static Thread createSupervisor(Thread t1){
        Thread supervisor=new Thread(()->{
            //Instant startTime= Instant.now();
        while(t1.isAlive()){
            System.out.println(Thread.currentThread().getName()+" is still running");

        }
        });
        supervisor.setName("Supervisor");
        return supervisor;
    }

    public static void main(String[] args) {
        fact_thread f=new fact_thread();
        Thread t1=new Thread(()->{
            System.out.print("factorial of a number is");
            System.out.println(f.factorial(25));
        });
        Thread supervisor=createSupervisor(t1);
        t1.start();
        supervisor.start();
        System.out.println("SuperVisor '"+ supervisor.getName()+"'/ watching worker "+t1.getName());
    }
}
