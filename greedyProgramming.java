import java.util.Arrays;

public class greedyProgramming {
    //bulbs
    public static int bulbs(int[] A) {
        int find = 0;
        int count = 0;
        for (int i = 0; i < A.length; i++) {
            if (A[i] == find) {
                count++;
                if (find == 0)
                    find = 1;
                else
                    find = 0;
            }
        }
        return count;
    }
    //highest product
    public static int highproduct(int[] arr){
        int len=arr.length;
        Arrays.sort(arr);
        int a=0,b=0;
        a=arr[len-1]*arr[len-2]*arr[len-3];
        b=arr[0]*arr[1]*arr[len-1];
        return Math.max(a,b);
    }
    //
    public static void main(String[] args) {
        System.out.println(highproduct(new int[]{-5,-2,-1,0,0,3,4,5}));
        System.out.println(highproduct(new int[]{-5,-2,-1,0,0,1,1,5}));
//        System.out.println(bulbs(new int[]{0,1,0,1,1,0,1,1}));
    }
}
