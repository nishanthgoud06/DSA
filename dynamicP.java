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
    public static void main(String[] args) {
        List<String> test=new ArrayList<>();
        test.add("university");
        test.add("of");
        test.add("north");
        test.add("texas");
        System.out.println(wordBreak("universityofnorthtexas",test));
//        System.out.println(decode("1234432133"));
//        System.out.println(jump2(new int[]{2,3,1,1,2,4,2,0,1,1}));
//        System.out.println(jumpGame2(new int[]{3,2,1,0,4}));
//        System.out.println(jumpGame(new int[]{3,2,1,0,4}));
//        System.out.println(hourseR2Dp(new int[]{1,2,3,1}));
//        System.out.println(houseR1DP(new int[]{1,2}));
//        System.out.println(houseR1(new int[]{1,2}));
    }
}
