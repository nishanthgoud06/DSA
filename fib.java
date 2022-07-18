public class fib {
    //first we are going to use recursion to solve Fibonacci series
    public static int print(int n){
       if(n<=1)
           return n;
       return print(n-1)+print(n-2);
    }
    //in this we are going to use iterative
    public static void  fi(int num){
        int first=0;
        int second=1;
        int number=0;
        int count=0;
        while(count<num){
            System.out.println(first);
            number=first+second;
            first=second;
            second=number;

            count++;
        }
    }
    public static void main(String[] args) {
//       for(int i=0;i<10;i++){
//           System.out.println(print(i));
//       }
        fi(10);
    }
}
