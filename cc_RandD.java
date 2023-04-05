import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class cc_RandD {
    //Triple Step
    public static int tripleSteps(int n) {
        int[] dp = new int[n+1];
        dp[0] = 1;
        dp[1] = 1;
        dp[2] = 2;
        for (int i = 3; i <= n; i++) {
            dp[i] = dp[i-1] + dp[i-2] + dp[i-3];
        }
        return dp[n];
    }
    //robort in a grid
    public static int robogrid(int i,int j){
        int[][] grid=new int[i+1][j+1];
        for(int a=0;a<=i;a++){
            grid[a][0]=1;
        }
        for(int a=0;a<=j;a++){
            grid[0][a]=1;
        }
        for(int a=1;a<=i;a++){
            for(int b=1;b<=j;b++){
                grid[a][b]=1+Math.max(grid[a-1][b],grid[a][b-1]);
            }
        }
        return grid[i][j];
    }
    //magic index
    //test1:if the elements in the array are distinct
    public static int magicIndex1(int[] arr){
        return magicIndex1H(arr,0,arr.length-1);
    }
    public static int magicIndex1H(int[] arr,int low,int high){
        while(low<high){
            int mid=low+(high-low)/2;
            if(arr[mid]==mid){
                return mid;
            }else if(arr[mid]<mid){
                magicIndex1H(arr,low,mid-1);
            }else{
                magicIndex1H(arr,mid+1,high);
            }
        }
        return -1;
    }
    //test case-2:if the elements need not to be distinct
    public static int magicIndex(int[] arr) {
        return magicIndexHelper(arr, 0, arr.length - 1);
    }

    private static int magicIndexHelper(int[] arr, int low, int high) {
        if (low > high) {
            return -1;
        }
        int mid = low + (high - low) / 2;
        if (arr[mid] == mid) {
            return mid;
        }
        int leftIndex = Math.min(mid - 1, arr[mid]);
        int leftResult = magicIndexHelper(arr, low, leftIndex);
        if (leftResult != -1) {
            return leftResult;
        }
        int rightIndex = Math.max(mid + 1, arr[mid]);
        int rightResult = magicIndexHelper(arr, rightIndex, high);
        return rightResult;
    }
    //Power set
    //Back Tracking
    public static List<List<Integer>> PowerSet(int[] num){
        List<List<Integer>> result=new ArrayList<>();
        PowerSetH(num,result,new ArrayList<>(),0);
        return result;
    }
    public static void PowerSetH(int[] nums,List<List<Integer>> result,List<Integer> temp,int index){
        result.add(new ArrayList<>(temp));
        for(int i=index;i<nums.length;i++){
            temp.add(nums[i]);
            PowerSetH(nums,result,temp,i+1);
            temp.remove(temp.size()-1);
        }
    }
    //Power Set problem-2
    public static Set<Set<Integer>> PowerSet1(Set<Integer> nums){
        Set<Set<Integer>> result=new HashSet<>();
        result.add(new HashSet<>());
        if(nums.size()==0)
            return result;
        for(int i:nums){
            Set<Set<Integer>> tempSet=new HashSet<>();
            for(Set<Integer> set:result){
                Set<Integer> temp=new HashSet<>(set);
                temp.add(i);
                tempSet.add(temp);
            }
            result.addAll(tempSet);
        }
        return result;
    }
    public static void main(String[] args) {
        //test case for Power Set
        System.out.println(PowerSet(new int[]{1,2,3}));
        //test case for power Set 2
        Set<Integer> test=new HashSet<>();
        test.add(1);
        test.add(2);
        test.add(3);
        System.out.println(PowerSet1(test));
        //test case for magic Index
//        int[] test1={-40,-20,-1,1,2,3,5,7,9,12,13};
//        int[] test2={-10,-5,2,2,2,3,4,7,9,12,13};
//        System.out.println(magicIndex1(test1));
        //test case for Robot in the grid
//        System.out.println(robogrid(2,4));
        //test case for Triple Steps
//        System.out.println(tripleSteps(3));
    }
}
