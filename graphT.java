import java.util.*;

public class graphT {
    //floodfill
    public static int[][] floodFill(int[][] flood,int sr,int sc,int color){
        floodFillHelper(flood,sr,sc,color,flood[sr][sc]);
        return flood;
    }
    public static void floodFillHelper(int[][] flood,int sr,int sc,int color,int old){
        if(sr<0||sr>=flood.length||sc<0||sc>=flood[0].length||flood[sr][sc]!=old)
            return;
        flood[sr][sc]=color;
        floodFillHelper(flood,sr-1,sc,color,old);
        floodFillHelper(flood,sr+1,sc,color,old);
        floodFillHelper(flood,sr,sc-1,color,old);
        floodFillHelper(flood,sr-1,sc+1,color,old);
    }
    //no of island
    //the first approach which we are going to implement is using dfs
    public static int noOfIsland(int[][] island){
        if(island.length==0||island==null)
            return 0;
        int result=0;
        for(int i=0;i<island.length;i++){
            for(int j=0;j<island.length;j++){
                result+=noOfIslandHelper(island,i,j);
            }
        }
        return result;
    }
    public static int noOfIslandHelper(int[][] island,int i,int j){
        if(i<0||i>=island.length||j<0||j>=island[0].length||island[i][j]==0)
            return 0;
        island[i][j]=0;
        noOfIslandHelper(island,i-1,j);
        noOfIslandHelper(island,i+1,j);
        noOfIslandHelper(island,i,j-1);
        noOfIslandHelper(island,i,j+1);
        return 1;
    }
    //now we are goinf to implement the same program using bfs approch
    private static int[][] dir={{-1,0},{1,0},{0,-1},{0,1}};
    public static int noofIsland(int[][] island){
        if(island.length==0)
            return 0;
        int result=0;
        for(int i=0;i<island.length;i++){
            for(int j=0;j<island[0].length;j++){
                if(island[i][j]==1){
                    result++;
                    System.out.println(i+""+j);
                    noiHelper(island,i,j);
                }
            }
        }
        return result;
    }
    public static void noiHelper(int[][] island,int i,int j){
        Queue<Integer> queue=new LinkedList<>();
        island[i][j]=0;
        queue.offer(i*island[0].length+j);
        while(!queue.isEmpty()){
                int loc= queue.poll();
                int pos1=loc/island[0].length;
                int pos2=loc%island[0].length;
                for(int [] d:dir){
                    int row=pos1+d[0];
                    int col=pos2+d[1];
                    if(row>=0&&row<island.length&&col>=0&&col<island[0].length&&island[row][col]==1){
                        island[row][col]=0;
                        queue.offer(row*island[0].length+col);
                    }
                }
        }
    }
//    695. Max Area of Island
    public static int maxArea(int[][] island){
        if(island.length==0)
            return 0;
        int result=0;
        for(int i=0;i<island.length;i++){
            for(int j=0;j<island[0].length;j++){
                result=Math.max(result,maxAreaHelper(island,i,j));
            }
        }
        return result;
    }
    public static int maxAreaHelper(int[][] island,int i, int j){
        if(i>=0&&i<island.length&&j>=0&&j<island[0].length&&island[i][j]==1){
            return 1+maxAreaHelper(island,i-1,j)+maxAreaHelper(island, i+1, j)+maxAreaHelper(island,i,j-1)+maxAreaHelper(island, i, j+1);
        }
        return 0;
    }
//    1254. Number of Closed Islands
    public static int closedIsland(int[][] island){
        if(island.length==0||island==null)
            return 0;
        int result=0;
        for(int i=0;i<island.length;i++){
            for(int j=0;j<island[0].length;j++){
                if(island[i][j]==0){
                    if(closedIslandhelper(i,j,island))
                        result++;
                }
            }
        }
        return result;
    }
    public static boolean closedIslandhelper(int i,int j,int[][] island){
        if(i<0||i>island.length||j<0||j>=island[0].length)
            return false;
        if(island[i][j]==1)
            return true;
        island[i][j]=1;
        boolean up=closedIslandhelper(i-1,j,island);
        boolean down=closedIslandhelper(i+1,j,island);
        boolean left=closedIslandhelper(i,j-1,island);
        boolean right=closedIslandhelper(i,j+1,island);
        return up&&down&&left&&right;
    }
    //we are implementing the same program but now we are counting the total number of individual closed island
    public static int noOfClosedIsland(int[][] grid){
        if(grid.length==0||grid==null)
            return 0;
        boolean[][] visited=new boolean[grid.length][grid[0].length];
        int result=0;
        for(int i=1;i<grid.length;i++){
            for(int j=1;j<grid.length;j++){
                if(grid[i][j]==0){
                    result+=closedIsHelp(grid,i,j,visited);
                }
            }
        }
        return result;
    }
    public static int closedIsHelp(int[][] grid,int i,int j,boolean[][] visited){
        if(i<0||i>= grid.length||j<0||j>=grid[0].length||visited[i][j]||grid[i][j]==1)
            return 0;
        visited[i][j]=true;
        grid[i][j]=1;
        int result=1;
        if(!visited[i-1][j]&&grid[i-1][j]==0) result+=closedIsHelp(grid,i-1,j,visited);
        if(!visited[i+1][j]&&grid[i+1][j]==0) result+=closedIsHelp(grid,i+1,j,visited);
        if(!visited[i][j-1]&&grid[i][j-1]==0) result+=closedIsHelp(grid,i,j-1,visited);
        if(!visited[i][j+1]&&grid[i][j+1]==0) result+=closedIsHelp(grid,i,j+1,visited);
        return result;
    }
    //1162. As Far from Land as Possible
    public static int farLand(int[][] grid){
        if(grid.length==0||grid==null)
            return 0;
        Queue<Integer> queue=new LinkedList<>();
        for(int i=0;i<grid.length;i++){
            for(int j=0;j<grid[0].length;j++){
                if(grid[i][j]==1){
                    queue.offer(i*grid[0].length+j);
                }
            }
        }
        if(queue.size()==0||queue.size()==grid.length*grid[0].length)
            return -1;
        int result=0;
        int[][] dir={{-1,0},{1,0},{0,-1},{0,1}};
        while(!queue.isEmpty()){
            int index=queue.poll();
            int x=index/grid[0].length;
            int y=index%grid[0].length;
            for(int[] d:dir){
                int i=x+d[0];
                int j=y+d[1];
                if(i>=0&&i<grid.length&&j>=0&&j<grid[0].length&&grid[i][j]==0){
                    grid[i][j]=1+grid[x][y];
                    result=Math.max(result,grid[i][j]);
                    queue.offer(i*grid[0].length+j);
                }
            }
        }
        return result-1;
    }
    //417. Pacific Atlantic Water Flow
    public static List<List<Integer>> paciAtla(int[][] grid){
        List<List<Integer>> result=new ArrayList<>();
        if(grid.length==0||grid==null)
            return result;
        int m=grid.length;
        int n=grid[0].length;
        boolean[][] pacific=new boolean[m][n];
        boolean[][] atlantic=new boolean[m][n];
        for(int i=0;i<m;i++){
            paciAtlaHelper(grid,i,0,pacific,Integer.MIN_VALUE);
            paciAtlaHelper(grid,i,n-1,atlantic,Integer.MIN_VALUE);
        }
        for(int i=0;i<n;i++){
            paciAtlaHelper(grid,0,i,pacific,Integer.MIN_VALUE);
            paciAtlaHelper(grid,m-1,i,atlantic,Integer.MIN_VALUE);
        }
        for(int i=0;i<m;i++){
            for(int j=0;j<n;j++){
                if(pacific[i][j]&&atlantic[i][j]){
                   result.add(Arrays.asList(i,j));
                }
            }
        }
        return result;
    }
    public static void paciAtlaHelper(int[][] grid,int i,int j,boolean[][] ocean,int max){
        if(i<0||i>=grid.length||j<0||j>=grid[0].length||grid[i][j]<max||ocean[i][j])
            return;
        ocean[i][j]=true;
        paciAtlaHelper(grid,i-1,j,ocean,grid[i][j]);
        paciAtlaHelper(grid,i+1,j,ocean,grid[i][j]);
        paciAtlaHelper(grid,i,j-1,ocean,grid[i][j]);
        paciAtlaHelper(grid,i,j+1,ocean,grid[i][j]);
    }
    public static void main(String[] args) {
        System.out.println(paciAtla(new int[][]{{1,2,2,3,5},{3,2,3,4,4},{2,4,5,3,1},{6,7,1,4,5},{5,1,1,2,4}}));
//        System.out.println(farLand(new int[][]{{1,0,1},{0,0,0},{1,0,1}}));
//        System.out.println(noofIsland(new int[][] {{1,1,1,1,0},{1,1,0,1,0},{1,1,0,0,0},{0,0,0,0,0}}));
//        System.out.println(noOfIsland(new int[][] {{1,1,1,1,0},{1,1,0,1,0},{1,1,0,0,0},{0,0,0,0,0}}));
//        for(int[] i:floodFill(new int[][]{{1,1,1},{1,1,0},{1,0,1}},1,1,2)){
//            System.out.println(Arrays.toString(i));
//        }
    }
}
