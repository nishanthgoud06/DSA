import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.*;
public class breathFirstSearch {
//1091. Shortest Path in Binary Matrix
    //first i thought of using dfs but it is failing in somecases
    private static int[][] dir={{1,0},{-1,0},{0,1},{0,-1},{1,1},{-1,-1},{1,-1},{-1,1}};
    public static int path(int[][] grid){
        int m=grid.length;
        int n=grid[0].length;
        boolean[][] visited=new boolean[m][n];
        visited[0][0]=true;
        Queue<int[]> queue=new LinkedList<>();
        queue.offer(new int[]{0,0});
        int ans=0;
        while(!queue.isEmpty()){
            int size= queue.size();
            for(int i=0;i<size;i++){
                int[] pop= queue.poll();
                if(pop[0]==m-1&&pop[1]==n-1)
                    return ans+1;
                for(int j=0;j<8;j++){
                    int nextx=dir[j][0]+pop[0];
                    int nexty=dir[j][1]+pop[1];
                    if(nextx>=0&&nextx<m&&nexty>=0&&nexty<n&&!visited[nextx][nexty]&&grid[nextx][nexty]==0){
                        queue.add(new int[]{nextx,nexty});
                        visited[nextx][nexty]=true;
                    }
                }
            }
            ans++;
        }
        return -1;
    }
//130. Surrounded Regions
public static void solve(char[][] board) {
    if (board.length == 0 || board[0].length == 0) return;
    if (board.length < 3 || board[0].length < 3) return;
    int m = board.length;
    int n = board[0].length;
    for (int i = 0; i < m; i++) {
        if (board[i][0] == 'O') helper(board, i, 0);
        if (board[i][n - 1] == 'O') helper(board, i, n - 1);
    }
    for (int j = 1; j < n - 1; j++) {
        if (board[0][j] == 'O') helper(board, 0, j);
        if (board[m - 1][j] == 'O') helper(board, m - 1, j);
    }
    for (int i = 0; i < m; i++) {
        for (int j = 0; j < n; j++) {
            if (board[i][j] == 'O') board[i][j] = 'X';
            if (board[i][j] == '*') board[i][j] = 'O';
        }
    }
}
    private static void helper(char[][] board, int r, int c) {
        if (r < 0 || c < 0 || r > board.length - 1 || c > board[0].length - 1 || board[r][c] != 'O') return;
        board[r][c] = '*';
        helper(board, r + 1, c);
        helper(board, r - 1, c);
        helper(board, r, c + 1);
        helper(board, r, c - 1);
    }
//    797. All Paths From Source to Target
public static List<List<Integer>> allPathsSourceTarget(int[][] graph) {
    List<List<Integer>> result=new ArrayList<>();
    List<Integer> temp=new ArrayList<>();
    temp.add(0);
    dfs(result,temp,0,graph);
    return result;
}
    public static void dfs(List<List<Integer>> result,List<Integer> temp,int current,int[][] graph){
        if(current==graph.length-1){
            result.add(new ArrayList<>(temp));
        }
        for(int i:graph[current]){
            temp.add(i);
            dfs(result,temp,i,graph);
            temp.remove(temp.size()-1);
        }
    }
    public static void main(String[] args) {
        int[][] test={{0,0,0},{1,0,0},{1,1,0}};
        System.out.println(path(test));
    }
}
