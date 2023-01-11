public class slidingWindow {
    //normal sliding window problem
    public static int MaxSubArray(int[] a,int limit){
        int max=0;
        int cur=0;
        for(int i=0;i<a.length;i++){
            cur+=a[i];
            if(i>=limit-1){
                max=Math.max(cur,max);
                cur-=a[i-(limit-1)];
            }
        }
        return max;
    }

    public static void main(String[] args) {
        System.out.println(MaxSubArray(new int[]{4,2,1,7,8,1,2,8,1,0},3));
    }
}
