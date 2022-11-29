import java.util.*;

public class BinarySeachTree {
    //in this course we are going to practise all problems related to Binary search tree
    //a binary tree needs to satisfy the below three requirements
    //1.the is one root node
    //2.at most 2 children per node
    //3.exactly one path between root node and the other nodes in the tree
    int val;
    BinarySeachTree left,right;
    public BinarySeachTree(int val){
        this.val=val;
        this.left=null;
        this.right=null;
    }
    public BinarySeachTree(){

    }
//lets first implement dfs
    //this is the iterative approach
    public static List<Integer> dfs(BinarySeachTree node){
        //to implement dfs we are going to use stacks
        List<Integer> result=new ArrayList<>();
        Stack<BinarySeachTree> stack=new Stack<>();
        stack.push(node);
        while(!stack.isEmpty()){
            BinarySeachTree a= stack.pop();
            if(a==null)
                continue;
            result.add(a.val);
            stack.push(a.right);
            stack.push(a.left);
        }
    return result;
    }
    //now we are going to work on the recursive approach
    public static List<Integer> dfsR(BinarySeachTree node,List<Integer> col){
        if(node==null)
            return col;
        col.add(node.val);
        dfsR(node.left,col);
        dfsR(node.right,col);
        return col;
    }
    //now we are going to implement bfs
    //i am thinking implementation can be done using queue
    //the first approach is the itreative approach
    public static List<Integer> bfs(BinarySeachTree node){
        Queue<BinarySeachTree> queue=new LinkedList<>();
        List<Integer> result=new ArrayList<>();
        queue.offer(node);
        while(!queue.isEmpty()){
            BinarySeachTree element=queue.poll();
            result.add(element.val);
            if(element.left!=null)
                queue.offer(element.left);
            if(element.right!=null)
                queue.offer(element.right);
        }
        return result;
    }
    //now we are going to find weather the tree included a target or not based on which we are going to return the boolean value
    public static boolean targetCheck(BinarySeachTree node,int target){
        //the first approach is the level-seach which is nothing but bfs
        Queue<BinarySeachTree> queue=new LinkedList<>();
        queue.offer(node);
        while(!queue.isEmpty()){
            BinarySeachTree bst=queue.poll();
            if(target==bst.val)
                return true;
            if(bst.left!=null)
                queue.offer(bst.left);
            if(bst.right!=null)
                queue.offer(bst.right);
        }
        return false;
    }
    //cant implement using recursion as recursion uses stack but to implement bfs we need to follow level-by-level
//    public static List<Integer> bfsR(BinarySeachTree node){
//        List<Integer> result=new LinkedList<>();
//        if(node==null)
//            return result;
//        result.add(node.val);
//        bfsR(node.left);
//
//
//    }
    public static void main(String[] args) {
        BinarySeachTree node=new BinarySeachTree(1);
        node.left=new BinarySeachTree(2);
        node .left.left=new BinarySeachTree(4);
        node.left.right=new BinarySeachTree(5);
        node.right=new BinarySeachTree(3);
        node.right.right=new BinarySeachTree(6);
//        System.out.println(dfs(node));
//        System.out.println(dfsR(node,new ArrayList<>()));
//        System.out.println(bfs(node));
        System.out.println(targetCheck(node,10));
    }
}
