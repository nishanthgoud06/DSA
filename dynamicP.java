import java.sql.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.*;

public class dynamicP {
    //house robbery
    //recursive approach
    public static int houseR1(int[] house){
        if(house.length==0)
            return 0;
        return houseRHelper(house,house.length-1);
    }
    public static int houseRHelper(int[] house,int limit){
        if(limit<0)
            return 0;
        return Math.max(houseRHelper(house,limit-2)+house[limit],houseRHelper(house,limit-1));
    }
    //dynamic programming
    public static int houseR1DP(int[] house){
        if(house.length==0)
            return 0;
        int[] dp=new int[house.length+1];
        dp[0]=0;
        dp[1]=house[0];
        for(int i=1;i<house.length;i++){
            int val=house[i];
            dp[i+1]=Math.max(dp[i-1]+val,dp[i]);
        }
        return dp[house.length];
    }
    //house robbery-2
    public static int hourseR2Dp(int[] house){
        if(house.length==0)
            return 0;
        if(house.length==1)
            return Math.max(house[0],house[1]);
        return Math.max(house2Helper(house,0,house.length-1),house2Helper(house,1,house.length));
    }
    public static int house2Helper(int[] house,int left,int right){
        int[] dp=new int[right];
        dp[left]=house[left];
        dp[left+1]=Math.max(house[left],house[left+1]);
        for(int i=left+2;i<right;i++){
            dp[i]=Math.max(dp[i-2]+house[i],dp[i-1]);
        }
        return dp[dp.length-1];
    }
    //55 jump game
    //the fisrt approach is useing iteration
    public static boolean jumpGame(int[] steps){
        if(steps.length==0)
            return true;
        int pos=0;
        for(int i=0;i<steps.length;i++){
            if(i>pos)
                return false;
            pos=Math.max(pos,steps[i]+i);
        }
        return true;
    }
    //solving the problrm using dynamic programming
    public static boolean jumpGame2(int[] steps){
        if(steps.length==0)
            return true;
        boolean[] dp=new boolean[steps.length];
        dp[0]=true;
        for(int i=1;i<steps.length;i++){
            for(int j=0;j<i;j++){
                if(dp[i-1]&&steps[j]+j>=i) {
                    dp[i] = true;
                    break;
                }
            }
        }
        return dp[steps.length-1];
    }
    //jump 2
    public static int jump2(int[] jump){
        if(jump.length==0)
            return 0;
        int[] dp=new int[jump.length+1];
        Arrays.fill(dp,Integer.MAX_VALUE-1);
        dp[0]=0;
        for(int i=1;i<jump.length;i++){
            for(int j=0;j<i;j++){
                if(jump[j]+j>=i){
                    if(dp[i]>dp[j]+1)
                        dp[i]=dp[j]+1;
                }
            }
        }
        return dp[jump.length-1];
    }
    //91. Decode Ways
    public static int decode(String s){
        if(s.length()==0)
            return 0;
        int[] dp=new int[s.length()+1];
        dp[0]=1;
        dp[1]=s.charAt(0)=='0'?0:1;
        for(int i=2;i<=s.length();i++){
            int num1=Integer.valueOf(s.substring(i-1,i));
            int num2=Integer.valueOf(s.substring(i-2,i));
            if(num1>0)
                dp[i]+=dp[i-1];
            if(num2>=10&&num2<=26)
                dp[i]+=dp[i-2];
        }
        return dp[s.length()];
    }
    //139. Word Break
    public static boolean wordBreak(String s,List<String> dict){
        if(s.length()==0)
            return true;
        boolean[] dp=new boolean[s.length()+1];
        dp[0]=true;
        for(int i=1;i<=s.length();i++){
            for(int j=0;j<i;j++){
                if(dp[j]&&dict.contains(s.substring(j,i))){
                    dp[i]=true;
                    break;
                }
            }
        }
        return dp[s.length()];
    }
    //300. Longest Increasing Subsequence
    public static int longestSub(int[] nums){
        if(nums.length==0||nums==null)
            return 0;
        int result=0;
        int[] dp=new int[nums.length];
        for(int i=0;i<nums.length;i++){
            dp[i]=1;
            for(int j=0;j<i;j++){
                if(nums[i]>nums[j]&&dp[i]<dp[j]+1){
                    dp[i]=dp[j]+1;
                }
            }
            result=Math.max(result,dp[i]);
        }
        return result;
    }
//    673. Number of Longest Increasing Subsequence
    public static int LonGISubSequence(int[] nums){
        if(nums.length==0||nums==null)
            return 0;
        int[] dp=new int[nums.length];
        int[] count=new int[nums.length];
        Arrays.fill(dp,1);
        Arrays.fill(count,1);
        int max=1;
        for(int i=0;i<nums.length;i++){
            for(int j=0;j<i;j++){
                if(nums[i]>nums[j]){
                    if(dp[i]<dp[j]+1){
                        dp[i]=dp[j]+1;
                        count[i]=count[j];
                    }else if(dp[i]==dp[j]+1){
                        count[i]+=count[j];
                    }
                }
                max=Math.max(max,dp[i]);
            }
        }
        int result=0;
        for(int i=0;i<nums.length;i++){
            if(dp[i]==max){
                result+=count[i];
            }
        }
        return result;
    }
  //  1143. Longest Common Subsequence
    //top-down approach
    public static int longestComSub(String s1,String s2){
        int[][] dp=new int[s1.length()+1][s2.length()+1];
        for(int i=0;i<=s1.length();i++){
            dp[i][0]=0;
        }
        for(int i=0;i<=s2.length();i++){
            dp[0][i]=0;
        }
        for(int i=1;i<=s1.length();i++){
            for(int j=1;j<=s2.length();j++){
                if(s1.charAt(i-1)==s2.charAt(j-1))
                    dp[i][j]=1+dp[i-1][j-1];
                else
                    dp[i][j]=Math.max(dp[i-1][j],dp[i][j-1]);
            }
        }
        return dp[s1.length()][s2.length()];
    }
    //bottom-up
    public static int longestComSub2(String s1,String s2){
        int[][] dp=new int[s1.length()][s2.length()];
        return longestComHelper(s1,s2,0,0,dp);
    }
    public static int longestComHelper(String s1,String s2,int i,int j,int[][] dp){
        if(i==s1.length()||j==s2.length())
            return 0;
        if(s1.charAt(i)==s2.charAt(j))
            return dp[i][j]=1+longestComHelper(s1,s2,i+1,j+1,dp);
        else
            return dp[i][j]=Math.max(longestComHelper(s1,s2,i+1,j,dp),longestComHelper(s1,s2,i,j+1,dp));
    }
    //583. Delete Operation for Two Strings
    public static int DeleteStr(String s1,String s2){
        int[][] dp=new int[s1.length()+1][s2.length()+1];
        for(int i=0;i<=s1.length();i++){
            dp[i][0]=i;
        }
        for(int j=0;j<=s2.length();j++){
            dp[0][j]=j;
        }
        for(int i=1;i<=s1.length();i++){
            for(int j=1;j<=s2.length();j++){
                if(s1.charAt(i-1)==s2.charAt(j-1))
                    dp[i][j]=dp[i-1][j-1];
                else
                    dp[i][j]=1+Math.min(dp[i-1][j],dp[i][j-1]);
            }
        }
        return dp[s1.length()][s2.length()];
    }
    public static int DeleteStr2(String s1,String s2){
        int[][] dp=new int[s1.length()+1][s2.length()+1];
        for(int i=0;i<=s1.length();i++){
            Arrays.fill(dp[i],-1);
        }
        return DeleteStr2Helper(s1,s2,s1.length(),s2.length(),dp);
    }
    public static int DeleteStr2Helper(String s1,String s2,int i,int j,int[][] dp){
        if(i==0)
            return dp[i][j]=j;
        if(j==0)
            return dp[i][j]=i;
        if(dp[i][j]!=-1)
            return dp[i][j];
        if(s1.charAt(i-1)==s2.charAt(j-1))
            return dp[i][j]=DeleteStr2Helper(s1,s2,i-1,j-1,dp);
        else
            return dp[i][j]=1+Math.min(DeleteStr2Helper(s1,s2,i-1,j,dp),DeleteStr2Helper(s1,s2,i,j-1,dp));
    }
//coin change
//now we are going to write the bottom-up approach of this problem
    public static int coinChange(int[] coins,int amount){
        int[] dp=new int[amount+1];
        dp[0]=0;
        for(int i=1;i<=amount;i++){
            dp[i]=dp.length;
            for(int j=0;j<coins.length;j++){
                if(i>=coins[j])
                    dp[i]=Math.min(dp[i],dp[i-coins[j]]+1);
            }
        }
        return dp[amount];
    }
    //now we are going to write the top-down approach of this problem
    public static int coinChangeBU(int[] coins,int amount){
        HashMap<Integer,Integer> hm=new HashMap<>();
        return coinChangeBUHelper(coins,amount,hm);
    }
    public static int coinChangeBUHelper(int[] coins,int amount,HashMap<Integer,Integer> hm){
        if(amount==0)
            return 0;
        int min=Integer.MAX_VALUE;
        for(int i=0;i<coins.length;i++){
            if(coins[i]>amount)
                continue;
            int val=coinChangeBUHelper(coins,amount-coins[i],hm);
            if(val<min)
                min=val;

        }
        min=min==Integer.MAX_VALUE?min:min+1;
        hm.put(amount,min);
        return min;
    }
    //Gym Locker
    //in this problem statement we are going to solve a problem at each level if the door is closed we open and vice versa.
    public static int GymLocker(int round){
        if(round==0)
            return 0;
        if(round==1||round==2)
            return 1;
        int[] dp=new int[round+1];
        for(int i=1;i<=round;i++){
            dp[i]=1;
        }
        for(int i=2;i<=round;i++){
            for(int j=i;j<=round;j+=i){
                dp[i]=dp[i]==1?0:1;
            }
        }
        int result=0;
        for(int i=1;i<=round;i++){
            if(dp[i]==1)
                result++;
        }
        return result;
    }
    //can Sum using memorization
    public static List<Integer> canSum(int[] nums,int target){
        HashMap<Integer,List<Integer>> hashmap=new HashMap<>();
        return canSumHelper(hashmap,nums,target,new ArrayList<>());
    }
    public static List<Integer> canSumHelper(HashMap<Integer,List<Integer>> hashmap,int[] nums,int target,List<Integer> temp){
        if(target<0)
            return null;
        if(target==0)
            return new ArrayList<>(temp);
        if(hashmap.containsKey(target))
            return hashmap.get(target);
        for(int i=0;i<nums.length;i++){
            temp.add(nums[i]);
            List<Integer> result=canSumHelper(hashmap,nums,target-nums[i],temp);
            temp.remove(temp.size()-1);
            if(result!=null){
                hashmap.put(target,new ArrayList<>(result));
                return result;
            }
        }
        hashmap.put(target,null);
        return null;
    }
    //best Sum
    public static List<Integer> bestSum(int[] nums,int target){
        if(nums.length==0)
            return new ArrayList<>();
        HashMap<Integer,List<Integer>> hashMap=new HashMap<>();
        return bestSumHelper(nums,target,hashMap);
    }
    public static List<Integer> bestSumHelper(int[] nums, int target, HashMap<Integer, List<Integer>> hashmap) {
        if (target == 0) {
            return new ArrayList<>();
        }
        if (target < 0) {
            return null;
        }
        if (hashmap.containsKey(target)) {
            return hashmap.get(target);
        }
        List<Integer> result = null;
        for (int num : nums) {
            List<Integer> current = bestSumHelper(nums, target - num, hashmap);
            if (current != null) {
                List<Integer> temp = new ArrayList<>(current);
                temp.add(num);
                if (result == null || result.size() > temp.size()) {
                    result = temp;
                }
            }
        }
        hashmap.put(target, result);
        return result;
    }
    //i am going to do the bestSUm and canSum in tabulation method
    public static List<Integer> canSum2(int target,int[] nums){
        if(target==0)
            return new ArrayList<>();
        List<Integer>[] solution=new ArrayList[target+1];
        solution[0]=new ArrayList<>();
        for(int i=0;i<=target;i++){
            if(solution[i]!=null){
                for(int j:nums){
                    int next=i+j;
                    if(next<=target){
                        solution[next]=new ArrayList<>(solution[i]);
                        solution[next].add(j);
                    }
                }
            }
        }
        return solution[target];
    }
    public static List<Integer> bestSum2(int target,int[] nums){
        if(target==0)
            return new ArrayList<>();
        List<Integer>[] array=new ArrayList[target+1];
        array[0]=new ArrayList<>();
        for(int i=0;i<=target;i++){
            if(array[i]!=null){
                for(int j:nums){
                    int next=i+j;
                    if(next<=target && (array[next]==null || array[next].size()>array[i].size()+1)){
                        array[next]=new ArrayList<>(array[i]);
                        array[next].add(j);
                    }
                }
            }
        }
        return array[target];
    }
    //canConstruct
    public static boolean canConstruct(String[] array,String target){
        if(target.length()==0)
            return true;
        return canConHelper(array,target,new StringBuilder());
    }
    public static boolean canConHelper(String[] arr,String target,StringBuilder sb){
        if(target.equals(sb.toString()))
            return true;
        if(sb.toString().length()>target.length())
            return false;
        for(String s:arr){
            int length=sb.toString().length();
            sb.append(s);
            if(canConHelper(arr,target,sb))
                return true;
            sb.setLength(length);
        }
        return false;
    }
    //using tabulation
    public static boolean canCon1(String[] words,String target){
        if(target.length()==0)
            return true;
        HashSet<String> hashset=new HashSet<>();
        return canCon1Helper(words,target,hashset,new StringBuilder());
    }
    public static boolean canCon1Helper(String[] words,String target,HashSet<String> hashset,StringBuilder sb){
        if(sb.toString().equals(target))
            return true;
        if(sb.toString().length()>target.length())
            return false;
        if(hashset.contains(target))
            return false;
        for(String s:words){
            int length=sb.toString().length();
            sb.append(s);
            hashset.add(sb.toString());
            if(canCon1Helper(words,target,hashset,sb)){
                return true;
            }
            sb.setLength(length);
        }
        return false;
    }
    //using tabulation
    public static boolean canCon2(String[] words, String target) {
        if (target.length() == 0)
            return true;
        HashMap<String, Boolean> hashmap = new HashMap<>();
        return helpercanCon2(words, target, hashmap);
    }

    public static boolean helpercanCon2(String[] words, String target, HashMap<String, Boolean> hashmap) {
        if (target.length() == 0)
            return true;
        if (hashmap.containsKey(target))
            return hashmap.get(target);
        for (String s : words) {
            if (target.startsWith(s)) {
                boolean check = helpercanCon2(words, target.substring(s.length()), hashmap);
                if (check) {
                    hashmap.put(target, true);
                    return true;
                }
            }
        }
        hashmap.put(target, false);
        return false;
    }
    //using memorization
    public static boolean canCon3(String[] words,String target){
        if(target.length()==0)
            return true;
        boolean[] result=new boolean[target.length()+1];
        result[0]=true;
        for(int i=0;i<target.length();i++){
            if(result[i]){
                for(String s:words){
                    if(target.startsWith(s) && i+s.length()<=target.length()){
                        int next=i+s.length();
                        result[next]=true;
                        break;
                    }
                }
            }
        }
        return result[target.length()];
    }

    //count Construct
    public static int counCon(String[] words,String target){
        int[] result=new int[1];
        counConHelper(words,target,result);
        return result[0];
    }
    public static void counConHelper(String[] words,String target,int[] result){
        if(target.length()==0)
            result[0]=result[0]+1;
        for(String s:words){
            if(target.startsWith(s)){
                counConHelper(words,target.substring(s.length()),result);
            }
        }
    }
    //using memorization
    public static int countCon2(String[] words,String target){
        HashMap<String,Integer> hashmap=new HashMap<>();
        return countCon2Helper(words,target,hashmap);
    }
    public static int countCon2Helper(String[] words,String target,HashMap<String,Integer> hashmap){
        if(target.length()==0)
            return 1;
        if(hashmap.containsKey(target))
            return hashmap.get(target);
        int totalCount=0;
        for(String s:words){
            if(target.startsWith(s)){
                int value=countCon2Helper(words,target.substring(s.length()),hashmap);
                totalCount+=value;
            }
        }
        hashmap.put(target,totalCount);
        return totalCount;
    }
    //using tabluation
    public static int countCon3(String[] words,String target){
        int[] result=new int[target.length()+1];
        boolean[] reach=new boolean[target.length()+1];
        reach[0]=true;
        result[0]=1;
        for(int i=0;i<target.length();i++){
            if(reach[i]){
                for(String s:words){
                    if(target.startsWith(s,i) && i+s.length()<=target.length()){
                        reach[i+s.length()]=true;
                        result[i+s.length()]+=result[i];
                        // break;
                    }
                }
            }
        }
        return result[target.length()];
    }
    //allconstrct
    //bruteForce
    public static List<List<String>> allCon(String[] words,String target){
        if(target.length()==0)
            return new ArrayList<>();
        List<List<String>> result=new ArrayList<>();
        allConHelper(words,target,result,new ArrayList<>());
        return result;
    }
    public static void allConHelper(String[] words,String target,List<List<String>> result,List<String> temp){
        if(target.length()==0)
            result.add(new ArrayList<>(temp));
        for(String s:words){
            if(target.startsWith(s)){
                temp.add(s);
                allConHelper(words,target.substring(s.length()),result,temp);
                temp.remove(temp.size()-1);
            }
        }
    }
    public static void main(String[] args) {
//        System.out.println(bestSum2(100,new int[]{2,3,4,25}));
        System.out.println(bestSum(new int[]{2,3,4,25},100));
//        System.out.println(canSum(new int[]{2,3,5},8));
//        System.out.println(GymLocker(9));
//        System.out.println(coinChangeBU(new int[]{1,5,6,8},11));
//        System.out.println(coinChange(new int[]{1,5,6,8},11));
//        System.out.println(DeleteStr("abc","a"));
//        System.out.println(DeleteStr2("abc","a"));
//        System.out.println(longestComSub2("abcdes","abc"));
//        System.out.println(longestComSub("abcdes","abc"));
//        System.out.println(LonGISubSequence(new int[]{1,3,5,4,7}));
//        System.out.println(longestSub(new int[]{10,9,2,5,3,7,101,18}));
//        List<String> test=new ArrayList<>();
//        test.add("university");
//        test.add("of");
//        test.add("north");
//        test.add("texas");
//        System.out.println(wordBreak("universityofnorthtexas",test));
//        System.out.println(decode("1234432133"));
//        System.out.println(jump2(new int[]{2,3,1,1,2,4,2,0,1,1}));
//        System.out.println(jumpGame2(new int[]{3,2,1,0,4}));
//        System.out.println(jumpGame(new int[]{3,2,1,0,4}));
//        System.out.println(hourseR2Dp(new int[]{1,2,3,1}));
//        System.out.println(houseR1DP(new int[]{1,2}));
//        System.out.println(houseR1(new int[]{1,2}));
    }
}
