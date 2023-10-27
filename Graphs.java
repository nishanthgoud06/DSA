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
    static class Tree{
        int val;
        Tree left,right;
        public Tree(int val){
            this.val=val;
        }
    }
    static class Goggle{
        StringBuilder sb;
        public Goggle(){
            sb=new StringBuilder();
        }
        public String serializer(Tree node){
            serializerHelper(node);
            return sb.toString();
        }
        public void serializerHelper(Tree node){
            if(node==null){
                sb.append("#"+",");
                return;
            }else{
                sb.append(node.val+",");
                serializer(node.left);
                serializer(node.right);
            }
        }
        public Tree deserializer(String s){
            String[] c=s.split("\\,");
            return deserializerHelper(c);
        }
        public Tree deserializerHelper(String[] c){
            Tree result=new Tree(Integer.parseInt(c[0]));
            Queue<Tree> queue=new LinkedList<>();
            queue.offer(result);
            int count=1;
            while(!queue.isEmpty() && count<c.length){
                Tree current=queue.poll();
                if(!c[count].equals("#")){
                    Tree left=new Tree(Integer.parseInt(c[count]));
                    current.left=left;
                    queue.offer(left);
                }
                count++;
                if(!c[count].equals("#")){
                    Tree right=new Tree(Integer.parseInt(c[count]));
                    current.right=right;
                    queue.offer(right);
                }
                count++;
            }
            return result;
        }
    }
    public static void inorder(Tree node){
        if(node==null)
            return;
        inorder(node.left);
        System.out.println(node.val);
        inorder(node.right);
    }
    //rearrange the array
    public static int findMissingValue(int[] nums){
        if(nums.length==0)
            return -1;
        int length=nums.length;
        for(int i=0;i<length;i++){
            while(1<=nums[i] && nums[i]<=length && nums[i]!=nums[nums[i]-1]){
                int temp = nums[i];
                nums[i] = nums[temp - 1];
                nums[temp - 1] = temp;
            }
        }
        for(int i=0;i<=length;i++){
            if(nums[i]!=i+1){
                return i+1;
            }
        }
        return -1;
    }
    //Bi-Parttite
    //DFS approch
    public static boolean bipartite(int[][] graph){
        if(graph.length==0)
            return true;
        int[] color=new int[graph.length];
        for(int i=0;i< graph.length;i++){
            if(color[i]==0 && !helperBipartite(graph,i,color,1)){
                return false;
            }
        }
        return true;
    }
    public static boolean helperBipartite(int[][] graph,int vertex,int[] colors,int color){
        if(colors[vertex]!=0){
            return colors[vertex]==color;
        }
        colors[vertex]=color;
        for(int i:graph[vertex]){
            if(!helperBipartite(graph,i,colors,-color)){
                return false;
            }
        }
        return true;
    }
    //BFS approch
    public static boolean bipartiteBFS(int[][] graph){
        if(graph.length==0)
            return true;
        int[] color=new int[graph.length];
        Queue<Integer> queue=new LinkedList<>();
        for(int i=0;i<graph.length;i++){
            if(color[i]==0){
                color[i]=1;
                queue.offer(i);
                while(!queue.isEmpty()){
                    int currentVertex= queue.poll();
                    for(int vertex:graph[currentVertex]){
                        if(color[vertex]!=0){
                            if(color[vertex]==color[currentVertex])
                                return false;
                        }else if(color[vertex]==0){
                            color[vertex]=-color[currentVertex];
                            queue.offer(vertex);
                        }
                    }
                }
            }
        }
        return true;
    }
    //design google question
    static class Xor{
        List<Integer> list;
        Xor prev;
        Xor next;
        public Xor(){
            list=new ArrayList<>();
        }
        public void add(int element){
            list.add(element);
        }
        public int get(int index){
            return list.get(index);
        }
    }
    //Required Vertices
    public static List<Integer> reuiredVertices(int[][] edges,int N){
        List<Integer> result=new ArrayList<>();
        if(edges.length==0 && N==0)
            return result;
        HashMap<Integer,List<Integer>> hashmap=new HashMap<>();
        for(int i=0;i<N;i++){
            hashmap.put(i,new ArrayList<>());
        }
        int[] count=new int[N];
        for(int[] edge:edges){
            int from=edge[0];
            int to=edge[1];
            hashmap.get(from).add(to);
            count[to]++;
        }
        for(int i=0;i<N;i++){
            if(count[i]==0){
                result.add(i);
//                queue.offer(i);
            }
        }
        return result;
    }
    //encode and decode
    //111 -3
    //11-2
    //1-1
    public static int encodeAndDecode(String number){
        if(number.length()==1)
            return 1;
        if(number.charAt(0)=='0'){
            return 0;
        }
        int[] dp=new int[number.length()+1];
        dp[0]=1;
        dp[1]=1;
        for(int i=2;i<=number.length();i++){
            int one=number.charAt(i-1)-'0';
            int two=number.charAt(i-2)-'0';
            two*=10+one;
            if(one>=1){
                dp[i]+=dp[i-1];
            }
            if(two>=10 && two<=26){
                dp[i]+=dp[i-2];
            }
        }
        return dp[number.length()];
    }
    //Magic Tree Nodes
    public static List<Integer> magicTree(Tree node){
        List<Integer> result=new ArrayList<>();
        if(node==null)
            return result;
        magicTreeHelper(node,result,-1);
        return result;
    }
    public static void magicTreeHelper(Tree node,List<Integer> result,int max){
        if(node==null){
            return;
        }
        if(max<node.val){
            result.add(node.val);
        }
        if(node.val>max){
            max=node.val;
        }
        magicTreeHelper(node.left,result,max);
        magicTreeHelper(node.right,result,max);
    }
    //using bfs Approch
    public static List<Integer> magicTreeBFS(Tree node){
        if(node==null)
            return new ArrayList<>();
        List<Integer> result=new ArrayList<>();
        Queue<Tree> queue=new LinkedList<>();
        Queue<Integer> maxValue=new LinkedList<>();
        queue.offer(node);
        maxValue.offer(-1);
        int max=-1;
        while(!queue.isEmpty()){
            Tree current=queue.poll();
            int currentMax=maxValue.poll();
            if(current.val > currentMax){
                currentMax= current.val;;
                result.add(current.val);
            }
            if(current.left!=null){
                queue.offer(current.left);
                maxValue.offer(currentMax);
            }
            if(current.right!=null){
                queue.offer(current.right);
                maxValue.offer(currentMax);
            }
        }
        return result;
    }
//    universal value
    public static int universalValue(Tree node){
        if(node==null)
            return 0;
        int[] count=new int[1];
        universalValueHelper(node,count);
        return count[0];
    }
    public static boolean universalValueHelper(Tree node,int[] count){
        if(node==null){
            return true;
        }
        boolean left=universalValueHelper(node.left,count);
        boolean right=universalValueHelper(node.right,count);
        if(left&&right){
            if((node.left==null||node.left.val==node.val)&&(node.right==null || node.right.val==node.val)){
                count[0]++;
                return true;
            }
        }
        return false;
    }
    public static int[] sequenceEquation(int[] nums){
        if(nums.length==0)
            return new int[0];
        int[] result=new int[nums.length];
        HashMap<Integer,Integer> hashmap=new HashMap<>();
        for(int i=0;i<nums.length;i++){
            hashmap.put(i+1,nums[i]);
        }
        for(int i=1;i<=nums.length;i++){
            result[i-1]=sequenceEqHelper(i,hashmap,2);
        }
        return result;
    }
    public static int sequenceEqHelper(int target,HashMap<Integer,Integer> hashmap,int rounds){
        if(rounds==0){
            return target;
        }
        for(Map.Entry<Integer,Integer> entry:hashmap.entrySet()){
            if(entry.getValue()==target){
                return sequenceEqHelper(entry.getKey(),hashmap,rounds-1);
            }
        }
        return -1;
    }
    //1328. Break a Palindrome
    public static String breakpalindrome(String s){
        return "";
    }
    public static void main(String[] args) {
        //Sequence Equation
        System.out.println(Arrays.toString(sequenceEquation(new int[]{5,2,1,3,4})));
        //test case for universal Value
//        Tree node=new Tree(0);
//        node.left=new Tree(1);
//        node.right=new Tree(0);
//        node.right.left=new Tree(1);
//        node.right.right=new Tree(0);
//        node.right.left.left=new Tree(1);
//        node.right.left.right=new Tree(1);
//        System.out.println(universalValue(node));
        //test case for Magic Tree
//        Tree node=new Tree(5);
//        node.left=new Tree(4);
//        node.right=new Tree(9);
//        node.left.left=new Tree(8);
//        node.left.right=new Tree(7);
//        System.out.println(magicTreeBFS(node));
        //test case for encode and decode
//        System.out.println(encodeAndDecode("001"));
        //Required Vertices
//        System.out.println(reuiredVertices(new int[][]{{3,1},{1,2},{0,2}},4));
        //Xor Design
//        Xor test=new Xor();
//        test.add(1);
//        test.add(2);
//        test.add(3);
//        test.add(4);
//        System.out.println(test.get(3));
        //Bi-partite
//        System.out.println(bipartiteBFS(new int[][]{{1,3},{0,2},{1,3},{0,2}}));
//        System.out.println(bipartiteBFS(new int[][]{{1,2,3},{0,2},{0,1,3},{0,2}}));
        //test case for Finding missing value in the array in linear time and in constant space
//        System.out.println(findMissingValue(new int[]{3,4,-1,1}));
        //test case for Google Problem
//        Goggle test=new Goggle();
//        Tree testing=new Tree(5);
//        testing.left=new Tree(2);
//        testing.right=new Tree(7);
//        testing.left.left=new Tree(1);
//        testing.left.right=new Tree(3);
//        testing.right.left=new Tree(6);
//        testing.right.right=new Tree(8);
//        String serializer=test.serializer(testing);
//        System.out.println(serializer);
//        Tree deserializer=test.deserializer(serializer);
//        inorder(deserializer);
        //test case for Topological Sort
//        List<List<Integer>> list=new ArrayList<>();
//        for(int i=0;i<4;i++){
//            list.add(new ArrayList<>());
//        }
//        list.get(1).add(0);
//        list.get(2).add(0);
//        list.get(3).add(0);
//        System.out.println(Arrays.toString(topoSort(list,4)));
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