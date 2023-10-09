import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class Pgraphs {
    //785. Is Graph Bipartite?
    //the first approch which we are going to follow is dfs
    public static boolean isBipartite(int[][] graphs){
        if(graphs.length==0){
            return true;
        }
        int[] color=new int[graphs.length];
        for(int i=0;i<graphs.length;i++){
            if(color[i]==0 && !isBipartiteHelper(graphs,color,i,1)){
                return false;
            }
        }
        return true;
    }
    public static boolean isBipartiteHelper(int[][] graphs,int[] colors,int currentNode,int color){
        if(colors[currentNode]!=0){
            return colors[currentNode]==color;
        }
        colors[currentNode]=color;
        for(int i:graphs[currentNode]){
            if(!isBipartiteHelper(graphs,colors,i,-color)){
                return false;
            }
        }
        return true;
    }
    //BFS
    public static boolean isBipartite2(int[][] graphs){
        if(graphs.length==0)
            return true;
        int[] color=new int[graphs.length];
        Queue<Integer> stack=new LinkedList<>();
        for(int i=0;i<graphs.length;i++){
            if(color[i]==0){
                stack.offer(i);
                color[i]=1;
                while(!stack.isEmpty()){
                    int currentNode=stack.poll();
                    for(int j:graphs[currentNode]){
                        if(color[j]==color[currentNode]){
                            return false;
                        }else if(color[j]==0){
                            stack.offer(j);
                            color[j]=-color[currentNode];
                        }
                    }
                }
            }
        }
        return true;
    }
    public static void main(String[] args) {
        //test case for isBipartite-dfs
        int[][] test1={{1,2,3},{0,2},{0,1,3},{0,2}};
        int[][] test2={{1,3},{0,2},{1,3},{0,2}};
        System.out.println(isBipartite(test1));
        System.out.println(isBipartite(test2));
        //test case for isBipartite-bfs
        System.out.println(isBipartite2(test1));
        System.out.println(isBipartite2(test2));
    }
}
