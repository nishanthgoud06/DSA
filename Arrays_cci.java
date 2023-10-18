import java.sql.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Arrays_cci {
    //1.1 implement anAlgorithm to determine if a string has all unique character.
    // what i you cannot use additional data Structure
    public static boolean isUnique(String s){
        //but the time complexity is O(n2)
        char[] c=s.toCharArray();
        for(int i=0;i<c.length;i++){
            for(int j=i+1;j<c.length;j++){
                if(c[i]==c[j])
                    return false;
            }
        }
        return true;
    }
    public static boolean isUniques(String s){
        char[] c=s.toCharArray();
        Arrays.sort(c);
        for(int i=0;i<c.length-1;i++){
            if(c[i]==c[i+1])
                return false;
        }
        return true;
    }
    //check permutations
//    public static boolean permutations(String s1,String s2){
//        if(s2.length()==0)
//            return false;
//        return checkPermutations(s1,s2);
//    }
//Lexicographical Values
    public static List<Integer> lexiIntsort(int k){
        List<String> temp=new ArrayList<>();
        for(int i=1;i<=k;i++){
            temp.add(String.valueOf(i));
        }
        Collections.sort(temp);
        List<Integer> result=new ArrayList<>();
        for(String s:temp){
            result.add(Integer.valueOf(s));
        }
        return result;
    }
    //approch 2
    public static List<Integer> lexiIntSort(int k){
        List<Integer> result=new ArrayList<>();
        lexiIntSortHelper(result,1,k);
        return result;
    }
    public static void lexiIntSortHelper(List<Integer> result,int start,int end){
        if(start>end){
            return;
        }
        result.add(start);
        lexiIntSortHelper(result,start*10,end);
        if(start%10<9){
            lexiIntSortHelper(result,start+1,end);
        }
    }
//Merge String
    public static String mergeStrings(String s1,String s2){
        //first approch
//        if(s1.length()==0 && s2.length()==0)
//            return new String();
//        else if(s1.length()>=s2.length()){
//            return s1.concat(s2);
//        }else{
//            return s2.concat(s1);
//        }
        //second approch
        char[] result=new char[s1.length()+s2.length()];
        int i=0;
        if(s1.length()>=s2.length()){
            for(int j=0;j<s1.length();j++){
                result[i++]=s1.charAt(j);
            }
            for(int j=0;j<s2.length();j++){
                result[i++]=s2.charAt(j);
            }
        }else{
            for(int j=0;j<s2.length();j++){
                result[i++]=s2.charAt(j);
            }
            for(int j=0;j<s1.length();j++){
                result[i++]=s1.charAt(j);
            }

        }
        return String.valueOf(result);
    }
//    One Unique Character Substrings
    public static int unique(String s){
        if(s==null || s.length()==0)
            return 0;
        int[] array=new int[26];
        int count=0;
        for(char c:s.toCharArray()){
            array[c-'a']++;
        }
        for(int i:array){
            count+=i;
        }
        int i=0;
        while(i<s.length()-1){
            while(s.charAt(i)==s.charAt(i+1)){
                count++;
                i++;
            }
            i++;
        }
        return count;
    }
//    Roll the Dice
public static int countDiceCombinations(int N, int max, int target) {
    int[][] dp = new int[N + 1][target + 1];

    // Initialize base case: one way to achieve sum 0 with 0 dice.
    dp[0][0] = 1;

    // Fill in the dp array.
    for (int i = 1; i <= N; i++) {
        for (int j = 1; j <= target; j++) {
            for (int k = 1; k <= max && k <= j; k++) {
                dp[i][j] += dp[i - 1][j - k];
            }
        }
    }

    return dp[N][target];
}
    //K Divisibility
    public static List<List<Integer>> kDivide(int[] nums,int target){
        if(target==0)
            return new ArrayList<>();
        List<List<Integer>> result=new ArrayList<>();
        KdivideHelper(result,nums,target,new ArrayList<>(),0);
        return result;
    }
    public static void KdivideHelper(List<List<Integer>> result,int[] nums,int target,List<Integer> temp,int index){
        if(target==0){
            result.add(new ArrayList<>(temp));
            return;
        }
        if(target<0){
            return;
        }
        for(int i=index;i<nums.length;i++){
            temp.add(nums[i]);
            KdivideHelper(result,nums,target-nums[i],temp,index+1);
            temp.remove(temp.size()-1);
        }
    }
    //N=2,max=6,target=4
//    case-1:N1=1,N2=3
//    case-2:N1=3,N2=1
//    case-3:N1=2,N2=2
    public static int countSubarraysDivisibleByK(int[] nums, int k) {
        int[] dp=new int[k];
        dp[0]=1;
        int prefix=0;
        int result=0;
        for(int num:nums){
            prefix+=num;
            prefix=(prefix%k+k)%k;
            result+=dp[prefix];
            dp[prefix]++;
        }
        return result;
    }
    public static void main(String[] args) {
        int[] nums = {1, 3, 1, 2, 5};
        int k = 7;
        System.out.println(countSubarraysDivisibleByK(nums, k));
//        System.out.println(kDivide(new int[]{1, 3, 1, 2, 5},7));
//        System.out.println(countDiceCombinations(1,6,5));
//        System.out.println(unique("aabca"));
//        System.out.println(mergeStrings("a","def"));
//        System.out.println(lexiIntsort(11));
//        System.out.println(lexiIntSort(11));
//        System.out.println(isUnique("cabc"));
    }
}
