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
    public static void main(String[] args) {
        System.out.println(sunsetSum(new int[]{1,2,3,5}));
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
