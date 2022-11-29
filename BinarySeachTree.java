import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

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
    public static void main(String[] args) {
        BinarySeachTree node=new BinarySeachTree(1);
        node.left=new BinarySeachTree(2);
        node .left.left=new BinarySeachTree(4);
        node.left.right=new BinarySeachTree(5);
        node.right=new BinarySeachTree(3);
        node.right.right=new BinarySeachTree(6);
        System.out.println(dfs(node));
    }
}
