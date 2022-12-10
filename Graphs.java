import java.util.Queue;
import java.util.*;
public class Graphs {
    //Number of Islands
    //Given an m x n 2D binary grid grid which represents a map of '1's (land) and '0's (water),
    // return the number of islands.
    //An island is surrounded by water and is formed by connecting adjacent lands horizontally or vertically.
    // You may assume all four edges of the grid are all surrounded by water.
    public static int island(char[][] find) {
        int result = 0;
        if (find.length == 0 || find == null)
            return result;
        for (int i = 0; i < find.length; i++) {
            for (int j = 0; j < find[i].length; j++) {
                if (find[i][j] == '1') {
                    result += dfss(find, i, j);
                }
            }
        }
        return result;
    }

    public static int dfss(char[][] find, int i, int j) {
        if (i < 0 || i > find.length || j < 0 || j > find[i].length || find[i][j] == '0')
            return 0;
        find[i][j] = '0';
        dfss(find, i - 1, j);
        dfss(find, i + 1, j);
        dfss(find, i, j - 1);
        dfss(find, i, j + 1);
        return 1;
    }

    private static final int[][] directions={{1,0},{-1,0},{0,1},{0,-1}};
    public static int numIslands(char[][] grid){
        if(grid==null||grid.length==0)
            return 0;
        int count=0;
        int row=grid.length;
        int col=grid[0].length;
        for(int i=0;i<row;i++){
            for(int j=0;j<col;j++){
                if(grid[i][j]=='1'){
                    ++count;
                    bfs(grid,row,col,i,j);
                }
            }
        }
        return count;
    }
    public static void bfs(char[][] grid,int row,int col,int i,int j){
        Queue<Integer> queue=new LinkedList<>();
        queue.add(i*col+j);
        grid[i][j]='0';
        while(!queue.isEmpty()){
            int index=queue.poll();
            int rows=index/col;
            int cols=index%col;
            for(int[] dir:directions){
                int x=dir[0]+rows;
                int y=dir[1]+cols;
                if(x>-1&&x<row&&y>-1&&y<col&&grid[x][y]=='1'){
                    grid[x][y]='0';
                    queue.add(x*col+y);
                }
            }
        }
    }

    public static void main(String[] args) {
        char[][]  grid = {
                {'1','1','1','1','0'},
                {'1','1','0','1','0'},
                {'1','1','0','0','0'},
                {'0','0','0','0','0'}};
//         System.out.println(island(grid));
        System.out.println(numIslands(grid));
    }
}