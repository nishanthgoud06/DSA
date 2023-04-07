import java.util.*;

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
    //recusrsive mutiply
    public static int mutiply(int a,int b){
        if(a==0||b==0){
            return 0;
        }
        if(a==1)
            return b;
        return mutiply(a-1,b)+b;
    }
    //book approch
    public static int muliplytwo(int a,int b){
        int small=a<b?a:b;
        int big=b>a?b:a;
        return mutiplyhelper(small,big);
    }
    public static int mutiplyhelper(int small,int big){
        if(small==0)
            return 0;
        if(small==1)
            return big;
        int current=small>>1;
        int half=mutiplyhelper(current,big);
        if(small%2==0){
            return half+half;
        }else{
            return half+half+big;
        }
    }
    //towers of hannoi
    public static void towerOfHannoi(int n,char source,char Destination,char temp){
       if(n==0){
           return;
       }
       towerOfHannoi(n-1,source,temp,Destination);
       System.out.println("moving " +n+" the source "+ source+" to "+Destination);
       towerOfHannoi(n-1,temp,Destination,source);
    }
    //String Permutations
    public static List<String> permutation(String string){
        List<String> result=new ArrayList<>();
        if(string.length()==0)
            return result;
        permutationhelper(result,string,0);
        return result;
    }
    public static void permutationhelper(List<String> result,String string,int index){
        if(index==string.length()){
            result.add(string);
        }
        for(int i=index;i<string.length();i++){
            String str=swap(string,i,index);
            permutationhelper(result,str,index+1);
        }
    }
    public static String swap(String s,int a,int b){
        char[] c=s.toCharArray();
        char temp=c[a];
        c[a]=c[b];
        c[b]=temp;
        return new String(c);
    }
    //generate the paras according to the number
    //we are going to solve this problem in two approches
    //1.using back tracking
    public static List<String> genParas(int n){
        List<String> result=new ArrayList<>();
        if(n==0){
            return result;
        }
        genParasHelper(n,result,"",0,0);
        return result;
    }
    public static void genParasHelper(int n,List<String> result,String temp,int open,int close){
        if(temp.length()==n*2){
            result.add(temp);
            return;
        }
        if(open<n){
            genParasHelper(n,result,temp+"(",open+1,close);
        }
        if(close<open){
            genParasHelper(n,result,temp+")",open,close+1);
        }
    }
    //using stack
    public static List<String> genParas2(int n){
        List<String> result=new ArrayList<>();
        if(n==0){
            return result;
        }
        Stack<Character> stack=new Stack<>();
        genParas2Helper(stack,result,n,n);
        return result;
    }
    public static void genParas2Helper(Stack<Character> stack,List<String> result,int start,int end){
        if(start==0 && end==0){
            StringBuilder sb=new StringBuilder();
            for(Character c:stack){
                sb.append(c);
            }
            result.add(sb.toString());
            return;
        }
        if(start>0){
            stack.push('(');
            genParas2Helper(stack,result,start-1,end);
            stack.pop();
        }
        if(end>start){
            stack.push(')');
            genParas2Helper(stack,result,start,end-1);
            stack.pop();
        }
    }
    public static void main(String[] args) {
        //test case for Generating Paras
//        System.out.println(genParas(3));
        System.out.println(genParas2(3));
        //test case for permutations
//        System.out.println(permutation("abc"));
        //test case for towers of hannoi
//        towerOfHannoi(3,'A','C','B');
        //test case for recursive mutiply
//        System.out.println(mutiply(3,4));
        //test case for Power Set
//        System.out.println(PowerSet(new int[]{1,2,3}));
//        //test case for power Set 2
//        Set<Integer> test=new HashSet<>();
//        test.add(1);
//        test.add(2);
//        test.add(3);
//        System.out.println(PowerSet1(test));
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
