import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class example {
    static int o=1;
    //here we are trying to find the permutation of a string
    private static void permute(String str, int l, int r)
    {
        if (l == r)
            System.out.println(str+" "+o++);
        else
        {
            for (int i = l; i <= r; i++)
            {
                str = swap(str,l,i);
                permute(str, l+1, r);
            }
        }
    }
    public static String swap(String s,int i,int j){
        char[] a=s.toCharArray();
        char first=a[i];
        char temp=a[j];
        a[i]=temp;
        a[j]=first;
        return String.valueOf(a);
    }
    //we can cod ein a different way to find the Permutation of a string
    public static void permutation(String s1,String s2){
        if(s1.length()==0)
            System.out.println(s2);
        else{
            for(int i=0;i<s1.length();i++){
                String rem=s1.substring(0,i)+s1.substring(i+1);
                permutation(rem,s2+s1.charAt(i));
            }
        }
    }
    //a program to print the two factorial
    public static int Powerof2(int n){
        if(n==0)
            return 1;
        else if(n==1){
            System.out.println(1);
            return 1;
        }
        else{
            int a=Powerof2(n/2);
            int sum=a*2;
            System.out.println(sum);
            return sum;
        }
    }
    static boolean isperfect(int a){
        int num=(int)Math.sqrt(a);
        if(num*num-a!=0)
            return false;
        return true;
    }
    public static void perfectSquare(int number){
        if(isperfect(number)){
            System.out.println(number +"is a perfect square");
        }
        int upper=-1;
        int lower=-1;
        int increasingnumber=number+1;
        int decreasingnumber=number-1;
        while(true){
            if(isperfect(increasingnumber)){
                upper=increasingnumber;
                break;
            }else{
                increasingnumber++;
            }
        }
        while(true){
            if(isperfect(decreasingnumber)){
                lower=decreasingnumber;
                break;
            }else{
                decreasingnumber--;
            }
        }
        int large=increasingnumber-number;
        int small=number-decreasingnumber;
        if(small>large){
            System.out.println("the closest is higher that the given number "+ increasingnumber+" difference "+large);
        }else{
            System.out.println("the closest is lower then the given number "+ decreasingnumber+" difference "+small);
        }
    }
    //now we are going to implement more optimized version
    public static void perSquare(int number){
        int check=(int)Math.sqrt(number);
        if(check*check==number)
            System.out.println("the given number is a perfect square "+ number);
        int upper=(check+1)*(check+1);
        int lower=check*check;
        int small=number-lower;
        int big=upper-number;
        if(small>big){
            System.out.println("the closest perfect square is higher than the given number "+ upper);
        }else{
            System.out.println("the closest perfect square is lower than the given number "+lower);
        }
    }
    //now we going to desing hashmap;
    //right now i am thinking of implementing this the in the same approach as hashset
    //but i a bit confused on how to handle conlission in hashmap hopefully by 30 min i can figure out
    //how to design it

    public static void main(String[] args) {
//        Powerof2(25);
        permutation("abc","");
        permute("abc",0,2);
//        //1.extract numbers from text
//        String text="one1two2THREE3Four4";
//        String dilimitter="\\d";
//        Pattern pattern=Pattern.compile(dilimitter,Pattern.CASE_INSENSITIVE);
//        String[] result=pattern.split(text);
//        for(String s:result){
//            System.out.println(s);
//        }
    }
}
