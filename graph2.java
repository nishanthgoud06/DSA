import java.util.*;
public class graph2 {
    public static int[] djistras(List<List<List<Integer>>> adj,int V,int S){
        if(adj.size()==0)
            return new int[0];
        boolean[] visited=new boolean[V];
        int[] distance=new int[V];
        Arrays.fill(distance,Integer.MAX_VALUE);
        distance[S]=0;
        for(int i=0;i<V;i++){
            int currentVertex=findMinVertex(distance,visited);
            visited[currentVertex]=true;
            for(int j=0;j<V;j++){
                if(!visited[j]){
                    List<List<Integer>> travel=adj.get(currentVertex);
                    for(List<Integer> t:travel){
                        int to=t.get(0);
                        int weight=t.get(1);
                        if(distance[currentVertex]+weight<distance[to]){
                            distance[to]=distance[currentVertex]+weight;
                        }
                    }
                }
            }
        }
        return distance;
    }
    public static int findMinVertex(int[] distance,boolean[] visited){
        int result=-1;
        for(int i=0;i<distance.length;i++){
            if(!visited[i] &&(result==-1 || distance[i]<distance[result])){
                result=i;
            }
        }
        return result;
    }

    public static void main(String[] args) {
        List<List<List<Integer>>> test1=new ArrayList<>();
        Scanner scanner=new Scanner(System.in);
        System.out.println("enter the number of vertex");
        int V= scanner.nextInt();
        for(int i=0;i<V;i++){
            test1.add(new ArrayList<>());
        }
        System.out.println("enter the total no of edges");
        int edges=scanner.nextInt();
        for(int i=0;i<edges;i++){
            System.out.println("enter the source,target,weight");
            int source=scanner.nextInt();
            int target=scanner.nextInt();
            int weight=scanner.nextInt();
            test1.get(source).add(Arrays.asList(target,weight));
        }
        System.out.println("enter the source vertex");
        int source= scanner.nextInt();
        System.out.println(Arrays.toString(djistras(test1,V,source)));

    }
}
