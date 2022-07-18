public class climbD {
    public static int count(int n){
        if(n<0)
            return 0;
        if(n==0)
            return 1;
        return count(n-1)+count(n-2);
    }

    public static void main(String[] args) {
        System.out.println(count(5));
    }
}
