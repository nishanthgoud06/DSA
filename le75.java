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
    public static void main(String[] args) {
        int[][] matrix={{1,2,2,3,5},{3,2,3,4,4},{2,4,5,3,1},{2,4,5,3,1},{6,7,1,4,5},{5,1,1,2,4}};
        System.out.println(paciAt(matrix));
//        System.out.println(rotten(new int[][]{{2,1,1},{1,1,0},{0,1,1}}));
//        System.out.println(happy(19));
    }
}
