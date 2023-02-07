import java.lang.reflect.Array;
import java.util.*;

public class le75 {
    //happy number
    public static boolean happy(int num){
        if(num<=0)
            return false;
        int sumnum,remain;
        HashSet<Integer> hs=new HashSet<>();
        while(hs.add(num)){
            sumnum=0;
            while(num>0){
                remain=num%10;
                sumnum+=remain*remain;
                num=num/10;
            }
            if(sumnum==1)
                return true;
            else
                num=sumnum;
        }
        return false;
    }
    //994. Rotting Oranges
    public static int rotten(int[][] orange){
        if(orange.length==0||orange==null)
            return 0;
        int fresh=0;
        Queue<int[]> queue=new LinkedList<>();
        for(int i=0;i<orange.length;i++){
            for(int j=0;j< orange[0].length;j++){
                if(orange[i][j]==2){
                    queue.offer(new int[]{i,j});
                }else if(orange[i][j]==1){
                        fresh++;
                }
            }
        }
        int result=0;
        int[][] dir={{-1,0},{1,0},{0,-1},{0,1}};
        while(!queue.isEmpty()){
            int size= queue.size();
            ++result;
            for(int i=0;i<size;i++){
                int[] one=queue.poll();
                for(int[] j:dir){
                    int x=one[0]+j[0];
                    int y=one[1]+j[1];
                    if(x<0||x>=orange.length||y<0||y>=orange[0].length||orange[x][y]==2||orange[x][y]==0)
                        continue;
                    orange[x][y]=2;
                    fresh--;
                    queue.offer(new int[]{x,y});
                }
            }
        }
        return fresh==0?result-1:-1;
    }
//    417. Pacific Atlantic Water Flow
    public static List<List<Integer>> paciAt(int[][] heights){
        int m = heights.length, n = heights[0].length;
        boolean[][] pacific = new boolean[m][n];
        boolean[][] atlantic = new boolean[m][n];
        List<List<Integer>> res = new ArrayList<>();

        for (int i = 0; i < m; i++) {
            paciAtHelper(heights, i, 0, pacific, Integer.MIN_VALUE);
            paciAtHelper(heights, i, n-1, atlantic, Integer.MIN_VALUE);
        }


        for (int i = 0; i < n; i++) {
            paciAtHelper(heights, 0, i, pacific, Integer.MIN_VALUE);
            paciAtHelper(heights, m-1, i, atlantic, Integer.MIN_VALUE);
        }

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (pacific[i][j] && atlantic[i][j]) {
                    res.add(Arrays.asList(i, j));
                }
            }
        }

        return res;
    }

    public static void paciAtHelper(int[][] heights, int i, int j, boolean[][] ocean, int height){
        int[][] dirs = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
        int m = heights.length, n = heights[0].length;
        if (i < 0 || i >= m || j < 0 || j >= n || heights[i][j] < height || ocean[i][j]) {
            return;
        }
        ocean[i][j] = true;
        height = heights[i][j];
        for (int[] dir : dirs) {
            int x = i + dir[0];
            int y = j + dir[1];
            paciAtHelper(heights, x, y, ocean, height);
        }
    }
//    210. Course Schedule II
    public static int[] course(int numCourses,int[][] preReq){
        List<List<Integer>> list=new ArrayList<>(numCourses);
        for(int i=0;i<numCourses;i++){
            list.add(new ArrayList<>());
        }
        for(int[] i:preReq){
            list.get(i[1]).add(i[0]);
        }
        HashMap<Integer,Integer> hm=new HashMap<>();
        for(int i=0;i<numCourses;i++){
            hm.put(i,0);
        }
        List<Integer> res=new ArrayList<>();
        for(int i=0;i<numCourses;i++){
            if(!helperCourse(res,hm,list,i))
                return new int[0];
        }
        int[] result=new int[numCourses];
        for(int i=0;i<numCourses;i++){
            result[i]=res.get(numCourses-i-1);
        }
        return result;
    }
    public static boolean helperCourse(List<Integer> result,HashMap<Integer,Integer> hm,List<List<Integer>> list,int index){
        if(hm.get(index)==1)
            return false;
        if(hm.get(index)==2)
            return true;
        hm.put(index,1);
        for(int i:list.get(index)){
            if(!helperCourse(result,hm,list,i))
                return false;
        }
        hm.put(index,2);
        result.add(index);
        return true;
    }
    //we are going to implement the same program in the bfs approach
    public static int[] bfsCourse(int noOfCourses,int[][] courS){
        List<List<Integer>> list=new ArrayList<>(noOfCourses);
        for(int i=0;i<noOfCourses;i++){
            list.add(new ArrayList<>());
        }
        int[] col=new int[noOfCourses];
        for(int[] i:courS){
            col[i[0]]++;
            list.get(i[1]).add(i[0]);
        }
        return bfsCouHelper(list,col);
    }
    public static int[] bfsCouHelper(List<List<Integer>> temp,int[] tempo){
        Queue<Integer> queue=new LinkedList<>();
        int[] result=new int[tempo.length];
        int loc=0;
        for(int i=0;i< tempo.length;i++){
            if(tempo[i]==0){
                queue.offer(i);
            }
        }
        while(!queue.isEmpty()){
            int i=queue.poll();
            result[loc++]=i;
            for(int item:temp.get(i)){
                tempo[item]--;
                if(tempo[item]==0)
                    queue.offer(item);
            }
        }
        return loc==tempo.length?result:new int[0];
    }
    //house robbery
    public static int houseRobbery(int[] house){
        if(house.length==1){
            return house[0];
        }
        int[] dp=new int[house.length+1];
        dp[0]=0;
        dp[1]=house[0];
        for(int i=1;i<house.length;i++){
            dp[i+1]=Math.max(house[i]+dp[i-1],dp[i]);
        }
        return dp[house.length];
    }
    //coin change
    //the first approach which we have followed is bottom-up approach
    public static int coinChange(int[] change,int amount){
        if(amount==0)
            return 0;
        int[] dp=new int[amount+1];
        dp[0]=0;
        for(int i=1;i<=amount;i++){
            dp[i]=Integer.MAX_VALUE;
            for(int j=0;j<change.length;j++){
                if(change[j]<=i){
                    dp[i]=Math.min(dp[i],dp[i-change[j]]+1);
                }
            }
        }
        return dp[amount]==amount+1?-1:dp[amount];
    }
    //the second approach is going to be the top-down approach in which we are going to use
    // the hashmap to improve the time compleaxity
    public static int coinChange2(int[] change,int amount){
        HashMap<Integer,Integer> hm=new HashMap<>();
        return helperCoinChange(change,amount,hm);
    }
    public static int helperCoinChange(int[] change,int amount,HashMap<Integer,Integer> hm){
        if(amount==0)
            return 0;
        if(hm.containsKey(amount))
            return hm.get(amount);
        int min=Integer.MAX_VALUE;
        for(int i=0;i<change.length;i++){
            if(amount<change[i])
                continue;
            int val=helperCoinChange(change,amount-change[i],hm);
            if(val<min)
                min=val;
        }
            min=(min==Integer.MAX_VALUE?min:min+1);
            hm.put(amount,min);
            return min;
    }
    //416. Partition Equal Subset Sum
    public static boolean sunsetSum(int[] sum){
        if(sum.length==0)
            return true;
        int total=0;
        for(int i:sum){
            total+=i;
        }
        if(total%2!=0)
            return false;
        total=total/2;
        boolean[] result=new boolean[total+1];
        result[0]=true;
        for(int i=1;i<=sum.length;i++){
            for(int j=total;j>=sum[i-1];j--){
                result[j]=result[j]||result[j-sum[i-1]];
            }
        }
        return result[total];
    }
    //152. Maximum Product Subarray
    public static int maxSubArray(int[] arr){
        if(arr.length==0||arr==null)
            return 0;
        int max=arr[0],min=arr[0],result=arr[0];
        int temp_max=0;
        for(int i=1;i<arr.length;i++){
            max=Math.max(max*arr[i],Math.max(arr[i],min+arr[i]));
            min=Math.min(min*arr[i],Math.min(arr[i],max+arr[i]));
            temp_max=max;
            result=Math.max(temp_max,result);
        }
        return result;
    }
    //Longest Substring Without Repeating Characters
    //the first approach first we are going to use doesn't use any extra memory space
    public static int longSub(String s){
        if(s.length()==0)
            return 0;
        int[] arr=new int[128];
        int start_index=0;
        int result=0;
        for(int end_index=0;end_index<s.length();end_index++){
            start_index=Math.max(start_index,arr[s.charAt(end_index)]);
            result=Math.max(result,end_index-start_index+1);
            arr[s.charAt(end_index)]=end_index+1;
        }
        return result;
    }
    //to optimize time we are going to use the hashSet the time Complexity will improve
    public static int longSubHash(String s){
        if(s.length()==0)
            return 0;
        HashSet<Character> hashset=new HashSet<>();
        int start=0,end=0;
        int result=0;
        while(end<s.length()){
            if(!hashset.contains(s.charAt(end))){
                hashset.add(s.charAt(end));
                end++;
                result=Math.max(result,hashset.size());
            }else{
                hashset.remove(s.charAt(start));
                start++;
            }
        }
        return result;
    }
    //3sum
    public static List<List<Integer>> threeSum(int[] arr){
        List<List<Integer>> result=new ArrayList<>();
        if(arr.length==0)
            return result;
        Arrays.sort(arr);
        for(int i=0;i<arr.length-1;i++){
            if(i==0||(i>0&&arr[i]!=arr[i-1])){
                int j=i+1;
                int k=arr.length-1;
                while(j<k){
                    int sum=0-arr[i];
                    if(sum==arr[j]+arr[k]){
                        result.add(Arrays.asList(arr[i],arr[j],arr[k]));
                        while(j<k&&arr[j]==arr[j+1]) j=j+1;
                        while(j<k&&arr[k]==arr[k-1]) k=k-1;
                        j++;
                        k--;
                    }else if(sum>arr[j]+arr[k]){
                        j=j+1;
                    }else{
                        k=k-1;
                    }
                }
            }
        }
        return result;
    }
//    16. 3Sum Closest
    public static int threeSumCloset(int[] arr,int target){
        Arrays.sort(arr);
        int result=arr[0]+arr[1]+arr[arr.length-1];
        for(int i=0;i<arr.length-2;i++){
            int j=i+1;
            int k=arr.length-1;
            while(j<k){
                int sum=arr[i]+arr[j]+arr[k];
                if(sum>target){
                    k=k-1;
                }else{
                    j=j+1;
                }
                if(Math.abs(sum-target)<Math.abs(result-target)){
                    result=sum;
                }
            }
        }
        return result;
    }
    //min stack
    static class min_node{
        int val;
        int min;
        min_node next;
        public min_node(int val,int min,min_node next){
            this.val=val;
            this.min=min;
            this.next=next;
        }
    }
    static class min_stack{
        private static min_node min_node;
     public static void push(int val){
         if(min_node==null){
             min_node=new min_node(val,val,min_node);
         }else{
             min_node=new min_node(val,Math.min(min_node.min,val),min_node);
         }
     }
     public static  int top(){
        return min_node.val;
     }
     public static int min(){
        return min_node.min;
     }
    }
    //max stack
    static class max_node{
        int val;
        int max;
        max_node next;
        public max_node(int val,int max,max_node next){
            this.val=val;
            this.max=max;
            this.next=next;
        }
    }
    static class max_stack{
        private static max_node max_node;
        public static void push(int val){
            if(max_node==null){
                max_node=new max_node(val,val,max_node);
            }else{
                max_node=new max_node(val,Math.max(val,max_node.max),max_node);
            }
        }
        public static int top(){
            return max_node.val;
        }
        public static int max(){
            return max_node.max;
        }
    }
    public static void main(String[] args) {
//        min_stack min_test=new min_stack();
//        min_test.push(1);;
//        min_test.push(2);
//        min_test.push(-1);
//        min_test.push(10);
//        System.out.println(min_test.top());
//        System.out.println(min_test.min());
        max_stack max_stack=new max_stack();
        max_stack.push(1);
        max_stack.push(2);
        max_stack.push(-10);
        System.out.println(max_stack.top());
        System.out.println(max_stack.max());
//        System.out.println(threeSumCloset(new int[]{-1,2,1,-4},1));
//        System.out.println(threeSum(new int[]{-1,0,1,2,-1,-4}));
//        System.out.println(longSub("abcdab"));
//        System.out.println(longSubHash("abcdab"));
//        System.out.println(maxSubArray(new int[]{2,3,-2,4}));
//        System.out.println(sunsetSum(new int[]{1,2,3,5}));
//        System.out.println(coinChange(new int[]{1,2,5},11));
//        System.out.println(coinChange2(new int[]{1,2,5},11));
//        System.out.println(houseRobbery(new int[]{1,2,3,1}));
//        System.out.println(Arrays.toString(course(4,new int[][]{{1,0},{2,0},{3,1},{3,2}})));
//        System.out.println(Arrays.toString(bfsCourse(4,new int[][]{{1,0},{2,0},{3,1},{3,2}})));
//        int[][] matrix={{1,2,2,3,5},{3,2,3,4,4},{2,4,5,3,1},{2,4,5,3,1},{6,7,1,4,5},{5,1,1,2,4}};
//        System.out.println(paciAt(matrix));
//        System.out.println(rotten(new int[][]{{2,1,1},{1,1,0},{0,1,1}}));
//        System.out.println(happy(19));
    }
}
