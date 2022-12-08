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
    //now we are going to use the recursive version with dfs and perform the "or" function
    public static boolean dfs_target(BinarySeachTree node,int target){
        if(node==null)
            return false;
        if(node.val==target)
            return true;
        return dfs_target(node.left,target)||dfs_target(node.right,target);
    }
    //now we are going to implement the trees sum
    //my initial thought is to use dfs in recursive approach
    public static int treesum(BinarySeachTree node){
        if(node==null)
            return 0;
        return node.val+treesum(node.left)+treesum(node.right);
    }
    public static int treesumI(BinarySeachTree node){
        int result=0;
        Queue<BinarySeachTree> queue=new LinkedList<>();
        queue.offer(node);
        while(!queue.isEmpty()){
            BinarySeachTree val=queue.poll();
            result+=val.val;
            if(val.left!=null)
            queue.offer(val.left);
            if(val.right!=null)
                queue.offer(val.right);
        }
        return result;
    }
    //now we are going to find the min value in the tree
    public static int minV(BinarySeachTree node){
        if(node==null)
            return Integer.MAX_VALUE;
        return Math.min(node.val,Math.min(minV(node.left),minV(node.right)));
    }
    //now we are going to find the max sum path from the root to the child
    public static int maxSum(BinarySeachTree node){
        if(node==null)
            return Integer.MIN_VALUE;
        if(node.left==null && node.right==null)
            return node.val;
        int max=Math.max(maxSum(node.left),maxSum(node.right));
        return node.val+max;
    }
    //here we are trying to find the path sum where a value is given we need to find whether that sum can be formed by
    //following the path from root to leaf
    public static boolean pathSum(BinarySeachTree node,int target){
        if(node==null)
            return false;
        if(node.left==null&&node.right==null)
            return node.val==target;
        return pathSum(node.left,target- node.val)||pathSum(node.right,target- node.val);
    }
    //print the right side view of the binary tree
    //for the first approach we are going to use bfs
    public static List<Integer> rightView(BinarySeachTree node){
        List<Integer> result=new ArrayList<>();
        if(node ==null)
            return result;
        Queue<BinarySeachTree> temp=new LinkedList<>();
        temp.offer(node);
        while(!temp.isEmpty()){
            int size=temp.size();
            for(int i=0;i<size;i++){
                BinarySeachTree q= temp.poll();
                if(i==size-1){
                    result.add(q.val);
                }
                if(q.left!=null)
                    temp.offer(q.left);
                if(q.right!=null)
                    temp.offer(q.right);
            }
        }
        return result;
    }
    public static void main(String[] args) {
        BinarySeachTree n=new BinarySeachTree(1);
        n.left=new BinarySeachTree(2);
        n.left.right=new BinarySeachTree(5);
        n.right=new BinarySeachTree(3);
        n.right.right=new BinarySeachTree(4);
        System.out.println(rightView(n));
//        BinarySeachTree node=new BinarySeachTree(1);
//        node.left=new BinarySeachTree(2);
//        node.left.left=new BinarySeachTree(4);
//        node.left.right=new BinarySeachTree(5);
//        node.right=new BinarySeachTree(3);
//        node.right.right=new BinarySeachTree(6);
//        System.out.println(dfs(node));
//        System.out.println(dfsR(node,new ArrayList<>()));
//        System.out.println(bfs(node));
//        System.out.println(targetCheck(node,10));
//        System.out.println(dfs_target(node,5));
//        System.out.println(treesum(node));
//        System.out.println(treesumI(node));
//        System.out.println(minV(node));
//        System.out.println(maxSum(node));
//        System.out.println(pathSum(node,10));
    }
}
