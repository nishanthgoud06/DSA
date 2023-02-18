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
//    1091. Shortest Path in Binary Matrix
    public static int shortPath(int[][] grid){
        if(grid.length==0||grid==null)
            return -1;
        if(grid[0][0]==1||grid[grid.length-1][grid[0].length-1]==1)
            return -1;
        int[][] dir={{-1,-1},{-1,0},{-1,1},{0,-1},{0,0},{0,1},{1,-1},{1,0},{1,1}};
        boolean[][] visited=new boolean[grid.length][grid[0].length];
        Queue<int[]> queue=new LinkedList<>();
        queue.offer(new int[]{0,0});
        visited[0][0]=true;
        int result=0;
        while(!queue.isEmpty()){
            int size= queue.size();
            for(int i=0;i<size;i++){
                int[] temp= queue.poll();
                if(temp[0]==grid.length-1&&temp[1]==grid[0].length-1)
                    return result+1;
                for(int j=0;j<8;j++){
                    int x=temp[0]+dir[j][0];
                    int y=temp[1]+dir[j][1];
                    if(x>=0&&x<grid.length&&y>=0&&y<grid[0].length&&grid[x][y]==0&&!visited[x][y]){
                        visited[x][y]=true;
                        queue.offer(new int[]{x,y});
                    }
                }
            }
            result++;
        }
        return -1;
    }
    //542. 01 Matrix
    public static int[][] zeroonematrix(int[][] matrix){
        if(matrix.length==0||matrix==null)
            return null;
        Queue<int[]> queue=new LinkedList<>();
        for(int i=0;i<matrix.length;i++){
            for(int j=0;j<matrix[0].length;j++){
                if(matrix[i][j]==0){
                    queue.offer(new int[] {i,j});
                }else{
                    matrix[i][j]=Integer.MAX_VALUE;
                }
            }
        }
        int[][] dir={{-1,0},{1,0},{0,-1},{0,1}};
        while(!queue.isEmpty()){
            int size= queue.size();
            for(int i=0;i<size;i++){
                int[] temp=queue.poll();
                for(int[] d:dir){
                    int x=temp[0]+d[0];
                    int y=temp[1]+d[1];
                    if(x<0||x>=matrix.length||y<0||y>=matrix[0].length||matrix[x][y]<=matrix[temp[0]][temp[1]]+1)
                        continue;
                    matrix[x][y]=1+matrix[temp[0]][temp[1]];
                    queue.offer(new int[]{x,y});
                }
            }
        }
        return matrix;
    }
    //934. Shortest Bridge
    public static int shortbridge(int[][] grid){
        if(grid.length==0||grid==null){
            return 0;
        }
        Queue<int[]> queue=new LinkedList<>();
        boolean found=false;
        int m=grid.length;
        int n=grid[0].length;
        int result=0;
        boolean[][] visited=new boolean[m][n];
        int[][] dir={{-1,0},{1,0},{0,-1},{0,1}};
        for(int i=0;i<m&&!found;i++){
            for(int j=0;j<n&&!found;j++){
                if(grid[i][j]==1) {
                    shortbridgeHelper(i, j, queue, visited, grid);
                    found = true;
                }
            }
        }

        while(!queue.isEmpty()){
            int size= queue.size();
            for(int i=0;i<size;i++){
                int[] temp=queue.poll();
                for(int[] d:dir){
                    int x=temp[0]+d[0];
                    int y=temp[1]+d[1];
                    if(x>=0&&x<m&&y>=0&&y<n&&!visited[x][y]){
                        if(grid[x][y]==1)
                            return result;
                        visited[x][y]=true;
                        queue.offer(new int[]{x,y});
                    }
                }
            }
            result++;
        }
        return -1;
    }
    public static void shortbridgeHelper(int i,int j,Queue<int[]> queue,boolean[][] visited,int[][] grid){
        if(i<0||i>= visited.length||j<0||j>= visited[0].length||visited[i][j]||grid[i][j]==0)
            return;
        queue.offer(new int[]{i,j});
        visited[i][j]=true;
        shortbridgeHelper(i-1,j,queue,visited,grid);
        shortbridgeHelper(i+1,j,queue,visited,grid);
        shortbridgeHelper(i,j-1,queue,visited,grid);
        shortbridgeHelper(i,j+1,queue,visited,grid);
    }
    //1926. Nearest Exit from Entrance in Maze
    public static int maze(char[][] grid,int[] start){
        if(grid.length==0||grid==null)
            return 0;
        Queue<int[]> queue=new LinkedList<>();
        grid[start[0]][start[1]]='+';
        queue.offer(new int[]{start[0],start[1]});
        int[][] dir={{0,-1},{0,1},{1,0},{-1,0}};
        int result=0;
        while(!queue.isEmpty()){
            int size=queue.size();
            result++;
            for(int i=0;i<size;i++){
                int[] temp=queue.poll();
                for(int[] d:dir){
                    int x=d[0]+temp[0];
                    int y=d[1]+temp[1];
                    if(x<0||x>=grid.length||y<0||y>=grid[0].length)
                        continue;
                    if(grid[x][y]=='+')
                        continue;
                    if(x==0||x==grid.length-1||y==0||y==grid[i].length-1)
                        return result;
                    grid[x][y]='+';
                    queue.offer(new int[]{x,y});
                }
            }
        }
        return -1;
    }
    //797. All Paths From Source to Target
    public static List<List<Integer>> sourceToPath(int[][] graph){
        List<List<Integer>> result=new ArrayList<>();
        if(graph.length==0||graph==null)
            return result;
        List<Integer> temp=new ArrayList<>();
        temp.add(0);
        sourceToPathHelper(result,graph,temp,0);
        return result;
    }
    public static void sourceToPathHelper(List<List<Integer>> result,int[][] graph,List<Integer> temp,int index){
        if(index==graph.length-1){
            result.add(new ArrayList<>(temp));
            return;
        }
        for(int i:graph[index]){
            temp.add(i);
            sourceToPathHelper(result,graph,temp,i);
            temp.remove(temp.size()-1);
        }
    }
    //841. Keys and Rooms
    public static boolean KeysRoom(List<List<Integer>> rooms){
        HashSet<Integer> hs=new HashSet<>();
        hs.add(0);
        KeysRoomHelper(rooms,hs,0);
        return hs.size()==rooms.size();
    }
    public static void KeysRoomHelper(List<List<Integer>> rooms,HashSet<Integer> hs,int index){
        for(int i:rooms.get(index)){
            if(!hs.contains(i)){
                hs.add(i);
                KeysRoomHelper(rooms,hs,i);
            }
        }
    }
    //547. Number of Provinces
    public static int provinces(int[][] provience){
        if(provience.length==0||provience==null)
            return 0;
        int result=0;
        boolean[] visited=new boolean[provience.length];
        for(int i=0;i<provience.length;i++){
            if(!visited[i]){
                provi_helper(provience,i,visited);
                result++;
            }
        }
        return result;
    }
    public static void provi_helper(int[][] provience,int index,boolean[] visited){
        for(int i=0;i<provience.length;i++){
            if(!visited[i]&&provience[index][i]==1){
                visited[i]=true;
                provi_helper(provience,i,visited);
            }
        }
    }
//    1319. Number of Operations to Make Network Connected
    public static int numOfNetwork(int n, int[][] connections){
        if(connections.length<n-1)
            return -1;
        int[] parent=new int[n];
        for(int i=0;i<n;i++){
            parent[i]=i;
        }
        int components = n;
        for(int[] c:connections){
            int a=c[0];
            int b=c[1];
            int pa=findParent(parent,a);
            int pb=findParent(parent,b);
            if(pa!=pb){
                parent[pa]=pb;
                components--;
            }
        }
        return components-1;
    }
    public static int findParent(int[] parent,int p){
        if(parent[p]!=p){
            return findParent(parent,parent[p]);
        }
        return parent[p];
    }
    //1376. Time Needed to Inform All Employees
    public static int Timeneeded(int[] manager,int total,int[] informTime,int headId){
        int result=0;
        for(int i=0;i<total;i++){
            result=Math.max(result,TimeneededHelper(manager,informTime,i));
        }
        return result;
    }
    public static int TimeneededHelper(int[] manager,int[] informTime,int i){
        if(manager[i]!=-1){
            informTime[i]+=TimeneededHelper(manager,informTime,manager[i]);
            manager[i]=-1;
        }
        return informTime[i];
    }
//    802. Find Eventual Safe States
    public static List<Integer> findSafe(int[][] states){
        List<Integer> result=new ArrayList<>();
        if(states.length==0||states==null)
            return result;
        int[] visited=new int[states.length];
        for(int i=0;i< states.length;i++){
            if(findSafeHelper(states,i,visited))
                result.add(i);
        }
        return result;
    }
    public static boolean findSafeHelper(int[][] states,int position,int[] visited){
        if(visited[position]!=0)
            return visited[position]==1;
        visited[position]=2;
        for(int i:states[position]){
            if(!findSafeHelper(states,i,visited))
                return false;
        }
        visited[position]=1;
        return true;
    }
    public static void main(String[] args) {
        System.out.println(findSafe(new int[][]{{1,2},{2,3},{5},{0},{5},{},{}}));
//        System.out.println(Timeneeded(new int[]{2,2,-1,2,2,2},6,new int[]{0,0,1,0,0,0},2));
//        System.out.println(numOfNetwork(4,new int[][]{{0,1},{0,2},{1,2}}));
//        System.out.println(provinces(new int[][]{{1,0,0},{0,1,0},{0,0,1}}));
//        System.out.println(sourceToPath(new int[][]{{1,2},{3},{3},{0}}));
//        System.out.println(maze(new char[][]{{'+','+','.','+'},{'.','.','.','+'},{'+','+','+','.'}},new int[]{1,2}));
//        System.out.println(shortbridge(new int[][]{{0,1,0},{0,0,0},{0,0,1}}));
//        System.out.println(Arrays.toString(zeroonematrix(new int[][]{{0,0,0},{0,1,0},{0,0,0}})));
//        System.out.println(shortPath(new int[][]{{0,1},{1,0}}));
//        System.out.println(paciAtla(new int[][]{{1,2,2,3,5},{3,2,3,4,4},{2,4,5,3,1},{6,7,1,4,5},{5,1,1,2,4}}));
//        System.out.println(farLand(new int[][]{{1,0,1},{0,0,0},{1,0,1}}));
//        System.out.println(noofIsland(new int[][] {{1,1,1,1,0},{1,1,0,1,0},{1,1,0,0,0},{0,0,0,0,0}}));
//        System.out.println(noOfIsland(new int[][] {{1,1,1,1,0},{1,1,0,1,0},{1,1,0,0,0},{0,0,0,0,0}}));
//        for(int[] i:floodFill(new int[][]{{1,1,1},{1,1,0},{1,0,1}},1,1,2)){
//            System.out.println(Arrays.toString(i));
//        }
    }
}
