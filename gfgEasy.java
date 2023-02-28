import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.LinkedList;

//in this class we will be executing all the easy level problems by gfg
public class gfgEasy {
    //1.You are given an array of size N. Rearrange the given array in-place such that all the negative numbers
    // occur before positive numbers.(Maintain the order of all -ve and +ve numbers as given in the original array).
    //the approach is just placing the negative number first in the order in which they come
    //and the positive numbers are moved to there above number
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
    //Smallest subset with sum greater than all other elements
    public static int smallSub(int[] a){
        if(a.length==1)
            return 1;
        int total=0;
        for(int i:a){
            total+=i;
        }
        int result=0,sub=0;
        Arrays.sort(a);
        for(int i=a.length-1;i>=0;i--){
            result+=a[i];
            total-=a[i];
            sub++;
            if(result>total)
                return sub;
        }
        return a.length;
    }
    //Minimum Sum of Absolute Differences of Pairs
    static long findMinSum(int[] A,int[] B,int N) {
        Arrays.sort(A);
        Arrays.sort(B);
        long total=0;
        for(int i=0;i<N;i++){
            total+=Math.abs(A[i]-B[i]);
        }
        return total;
    }
    //First negative integer in every window of size k
    //the first aproach that we are goibg to follow is the brute Force Approach
    public static void sizeSearch(int[] a,int len,int size){
        boolean point;
        for(int i=0;i<len-size+1;i++){
            point=false;
            for(int j=0;j<size;j++){
                if(a[i+j]<0){
                    point =true;
                    System.out.print(a[i+j]+" ");
                    break;
                }
            }
            if(!point)
                System.out.print("0");
        }
    }
    //program that use sliding window to get the max value that can be obtained of ceratin size
    public static int Slidemax(int[] array,int size){
        int result=0;
        for(int i=0;i<size;i++){
            result+=array[i];
        }
        int temp=result;
        for(int i=size;i< array.length;i++){
            temp+=-array[i-size]+array[i];
            result=Math.max(temp,result);
        }
        return result;
    }
//    Java implementation to find the
//    first negative integer in
//    every window of size k
    public static int[] Limin(int[] array,int len,int size){
        LinkedList<Integer> ll=new LinkedList<>();
        int[] result=new int[len-size+1];
        int i,k=0;
        for( i=0;i<size;i++){
            if(array[i]<0){
                ll.add(i);
            }
        }
        for(i=3;i<len;i++){
            if(!ll.isEmpty()){
                result[k]=array[ll.peek()];
                k++;
            }else{
                result[k]=0;
                k++;
            }
            while((!ll.isEmpty()) &&
                    ll.peek() < (i - size + 1)){
                ll.remove();
            }
            if(array[i]<0)
                ll.add(i);
        }
        return result;
    }
    //remove duplicates
    public static int[] removeDuplicates(int[] arr){
        if(arr.length==0||arr==null)
            return new int[0];
        int current=1;
        Arrays.sort(arr);
        for(int i=1;i<arr.length;i++){
            if(arr[i]!=arr[i-1]){
                arr[current++]=arr[i];
            }
        }
        return Arrays.copyOfRange(arr,0,current);
    }
    public static void main(String[] args) {
        System.out.println(Arrays.toString(removeDuplicates(new int[]{3,5,4,1,2,6,4,3,5})));
//        System.out.println(Arrays.toString(Limin(new int[] {12, -1, -7, 8, -15, 30, 16, 28},8,3)));
//        System.out.println(Slidemax(new int[] {1, 4, 2, 10, 2, 3, 1, 0, 20},4));
//        sizeSearch(new int[] {2,-1,-7,8,-15,30,16,28},8,3);
//        System.out.println(findMinSum(new int[]{4,1,8,7},new int[] {2,3,6,5},4));
//        System.out.println(smallSub(new int[] { 3 , 1 , 7, 1}));
//        long[] a={-3,2,-2,2};
//        gfgEasy g=new gfgEasy();
//        g.Arrange(a,4);
//       print(a);
//        System.out.println(swapAndMax(new int[] {4,2,1,8}));
//        System.out.println(isMultipleof3(9));
    }
}
