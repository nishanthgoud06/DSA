import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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

    public static void main(String[] args) {
        System.out.println(DeleteStr("abc","a"));
        System.out.println(DeleteStr2("abc","a"));
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
