import java.lang.reflect.Array;
import java.util.Arrays;

public class gfgBasic {
    //this class includes all the basic level form gfg practise section
    //Cyclically rotate an array by one
    public static void rotateCy(int[] a){
        int temp=a[a.length-1];
        for(int i=a.length-1;i>=1;i--){
            a[i]=a[i-1];
        }
        a[0]=temp;
    }
    public static void main(String[] args) {
     int[] a={9,8,7,6,4,2,1,3};
        System.out.println(Arrays.toString(a));
        rotateCy(a);
        System.out.println(Arrays.toString(a));
    }
}
