public class multi {
    public static void main(String[] args) {
        for(int i=0;i<5;i++){
            mult thread1=new mult(i);
            Thread t=new Thread(thread1);
            t.start();
        }

    }
}
