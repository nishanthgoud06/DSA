import java.lang.reflect.Array;
import java.util.Arrays;

public class gfgEasy {
    public void Arrange(long[] a,long n){
        int j=0;
        long temp;
        for(int i=0;i<n;i++){
            if(a[i]<0){
                temp=a[i];
                int k=i;
                while(k>j){
                    a[k]=a[k-1];
                    k=k-1;
                }
                a[j]=temp;
                j++;
            }
        }
    }
    public static void print(long[] a){
        for(long i:a){
            System.out.println(i);
        }
    }
    public static int swapAndMax(int[] a){
        int i=0;
        int j=a.length-1;
        int sum=0;
        Arrays.sort(a);
        while(i<j){
            sum+=Math.abs(a[i]-a[j]);
            sum+=Math.abs(a[i+1]-a[j]);
            i++;
            j--;
        }
        sum+=Math.abs(a[0]-a[i]);
        return sum;
    }
    public static Boolean isMultipleof3(int n){
        if(n==0)
            return true;
        if(n<0)
            n=~n;
        if(n==1)
            return false;
        int odd_count=0;
        int even_count=0;
        while(n!=0){
            if((n&1)!=0){
                odd_count++;
            }
            if((n&2)!=0){
                even_count++;
            }
            n=n>>2;
        }
        return isMultipleof3(even_count-odd_count);
    }
    public static void main(String[] args) {
//        long[] a={-3,2,-2,2};
//        gfgEasy g=new gfgEasy();
//        g.Arrange(a,4);
//        print(a);
       // System.out.println(swapAndMax(new int[] {4,2,1,8}));
        System.out.println(isMultipleof3(23));
    }
}
