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
    public static void main(String[] args) {
        permute("abcd",0,3);
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
