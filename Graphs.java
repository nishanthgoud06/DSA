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
    //copy graph here we are copying the graph
    static class graph{
        int val;
        List<graph> neighbours;
        public graph(){
            this.val=0;
            neighbours=new ArrayList<graph>();
        }
        public graph(int val){
            this.val=val;
            neighbours=new ArrayList<graph>();
        }
        public graph(int val,ArrayList<graph> neighbours){
            this.val=val;
            this.neighbours=neighbours;
        }
    }
    //for the first approach we are going to use hashmap
    static HashMap<Integer,graph> hm=new HashMap<>();
    public static graph copy(graph node){
        if(node==null)
            return null;
        if(hm.containsKey(node.val))
            return hm.get(node.val);
        graph g=new graph(node.val,new ArrayList<graph>());
        hm.put(node.val,g);
        for(graph n:node.neighbours){
            g.neighbours.add(copy(n));
        }
        return g;
    }
    //the second approach we are going to use the helper function to use the dfs
    public static graph copied(graph node){
        if(node==null)
            return null;
        graph[] memory=new graph[100];
        Arrays.fill(memory,null);
        graph copy=new graph(node.val);
        helper(node,memory,copy);
        return copy;
    }
    public static void helper(graph node,graph[] memory,graph copy){
        memory[copy.val]=copy;
        for(graph g:node.neighbours){
            if(memory[g.val]==null){
                graph gh=new graph(g.val);
                copy.neighbours.add(gh);
                helper(g,memory,copy);
            }else{
                copy.neighbours.add(memory[g.val]);
            }
        }
    }
    //695. Max Area of Island
    //You are given an m x n binary matrix grid. An island is a group of 1's (representing land) connected
    // 4-directionally (horizontal or vertical.) You may assume all four edges of the grid are surrounded by water.
    //The area of an island is the number of cells with a value 1 in the island.
    //Return the maximum area of an island in grid. If there is no island, return 0.
    public static int countArea(int[][] island,int i,int j){
        if(i>=0&&i<island.length&&j>=0&&j<island[0].length&&island[i][j]==1){
            island[i][j]=0;
            return 1+countArea(island,i+1,j)+countArea(island,i-1,j)+countArea(island,i,j+1)+countArea(island,i,j-1);
        }
        return 0;
    }
    public static int maxArea(int[][] island){
        int max=0;
        if(island==null||island.length==0)
            return 0;
        for(int i=0;i<island.length;i++){
            for(int j=0;j<island[0].length;j++){
                if(island[i][j]==1){
                    max=Math.max(max,countArea(island,i,j));
                }
            }
        }
        return max;
    }
    //here we are going to do gfg graph problem
    //detect whether the dirted graph has a cycle or not
    static class Test{
        int size;
        List<List<Integer>> graph;
        public Test(int size){
            graph=new ArrayList<>(size);
            for(int i=0;i<size;i++){
                graph.add(new ArrayList<>());
            }
        }
        public void addEdge(int parent,int child){
            graph.get(parent).add(child);
        }
    }
    public static boolean hasCycleDir(Test graph,int size){
        if(graph.graph.size()==0)
            return false;
        boolean[] visited=new boolean[size];
        boolean[] stack=new boolean[size];
        for(int i=0;i<size;i++){
            if(hasCycleDirHelper(graph,visited,stack,i))
                return true;
        }
        return false;
    }
    public static boolean hasCycleDirHelper(Test graph,boolean[] visited,boolean[] stack,int vertex){
        if(stack[vertex])
            return true;
        if(visited[vertex])
            return false;
        stack[vertex]=true;
        visited[vertex]=true;
        for(int i:graph.graph.get(vertex)){
            if(hasCycleDirHelper(graph,visited,stack,i)){
                return true;
            }
        }
        stack[vertex]=false;
        return false;
    }
    //detect a cycle in undirected graph
    //approch-1 using dfs
    public static boolean hasCycleunDDfs(List<List<Integer>> graph,int size){
        if(graph.size()==0)
            return false;
        boolean[] visited=new boolean[size];
        for(int i=0;i<size;i++){
            if(!visited[i] && helperhasCycleunDDfs(graph,visited,i,-1)){
                return true;
            }
        }
        return false;
    }
    public static boolean helperhasCycleunDDfs(List<List<Integer>> graph,boolean[] visited,int current,int parent){
        visited[current]=true;
        for(int i:graph.get(current)){
            if(!visited[i]){
                if(helperhasCycleunDDfs(graph,visited,i,current)){
                    return true;
                }
            }else if(i!=parent){
                return true;
            }
        }
        return false;
    }
    //Topological sort
    public static int[] topoSort(List<List<Integer>> graph,int size){
        if(size==0)
            return new int[0];
        Set<Integer> hashSet=new HashSet<>();
        Stack<Integer> stack=new Stack<>();
        for(int i=0;i<size;i++){
            if(!hashSet.contains(i)){
                helperTopoSort(hashSet,stack,graph,i);
            }
        }
        int i=0;
        int[] result=new int[size];
        while(!stack.isEmpty()){
            result[i++]=stack.pop();
        }
        return result;
    }
    public static void helperTopoSort(Set<Integer> hashset,Stack<Integer> stack,List<List<Integer>> graph,int i){
        if(hashset.contains(i)){
            return;
        }
        hashset.add(i);
        for(int j:graph.get(i)){
            helperTopoSort(hashset,stack,graph,j);
        }
        stack.push(i);
    }
    public static void main(String[] args) {
        //test case for Topological Sort
        List<List<Integer>> list=new ArrayList<>();
        for(int i=0;i<4;i++){
            list.add(new ArrayList<>());
        }
        list.get(1).add(0);
        list.get(2).add(0);
        list.get(3).add(0);
        System.out.println(Arrays.toString(topoSort(list,4)));
        //test case for undirted cycle detection
        //test case 1
//        List<List<Integer>> test1=new ArrayList<>();
//        for(int i=0;i<4;i++){
//            test1.add(new ArrayList<>());
//        }
//        test1.get(0).add(1);
//        test1.get(1).add(0);
//        test1.get(1).add(2);
//        test1.get(2).add(1);
//        test1.get(0).add(2);
//        test1.get(2).add(0);
//        test1.get(2).add(3);
//        test1.get(3).add(2);
//        test1.get(0).add(1);
//        test1.get(1).add(0);
//        test1.get(2).add(1);
//        test1.get(1).add(2);
//        test1.get(1).add(3);
//        test1.get(3).add(1);
//        System.out.println(hasCycleunDDfs(test1,4));
        //test case 2
//        Test test=new Test(4);
//        test.addEdge(0,1);
//        test.addEdge(1,2);
//        test.addEdge(2,3);

//        System.out.println(hasCycleDir(test,4));
//        int[][] grid = {{0,0,1,0,0,0,0,1,0,0,0,0,0},
//                        {0,0,0,0,0,0,0,1,1,1,0,0,0},
//                        {0,1,1,0,1,0,0,0,0,0,0,0,0},
//                        {0,1,0,0,1,1,0,0,1,0,1,0,0},
//                        {0,1,0,0,1,1,0,0,1,1,1,0,0},
//                        {0,0,0,0,0,0,0,0,0,0,1,0,0},
//                        {0,0,0,0,0,0,0,1,1,1,0,0,0},
//                        {0,0,0,0,0,0,0,1,1,0,0,0,0}};
//        System.out.println(maxArea(grid));
//        char[][]  grid = {
//                {'1','1','1','1','0'},
//                {'1','1','0','1','0'},
//                {'1','1','0','0','0'},
//                {'0','0','0','0','0'}};
////         System.out.println(island(grid));
//        System.out.println(numIslands(grid));
    }
}