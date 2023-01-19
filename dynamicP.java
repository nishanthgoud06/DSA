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
    public static void main(String[] args) {
        System.out.println(houseR1DP(new int[]{1,2}));
        System.out.println(houseR1(new int[]{1,2}));
    }
}
