import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class trees {
    int val;
    trees left,right;
    public trees(int val){
        this.val=val;
        this.left=null;
        this.right=null;
    }
    //invert a binary tree
    //the first approach is going to be using dfs which uses recursion which is nothing but usage of stack
    public static trees conD(trees node){
        if(node==null)
            return null;
        final trees left=node.left;
        final trees right=node.right;
        node.left=conD(right);
        node.right=conD(left);
        return node;
    }
    public static void conB(trees node){
        if(node==null)
            return;
        Queue<trees> queue=new LinkedList<>();
        queue.offer(node);
        while(!queue.isEmpty()){
            trees temp=queue.poll();
            final trees left=temp.left;
            final trees right=temp.right;
            temp.right=left;
            temp.left=right;
            if(temp.left!=null){
                queue.offer(temp.left);
            }
            if(temp.right!=null)
                queue.offer(temp.right);
        }
    }
    public static void inOrder(trees node){
        if(node ==null)
            return;
        inOrder(node.left);
        System.out.print(node.val);
        inOrder(node.right);
    }
    //the level of the bst
    //the first approach is by using dfs
    public static int level(trees node){
        if(node==null)
            return 0;
        return 1+Math.max(level(node.left),level(node.right));
    }
    //the second approach is by using bfs
    public static int levelB(trees node){
        int depth=0;
        if(node==null)
           return depth;
        Queue<trees> q=new LinkedList<>();
        q.offer(node);
        while(!q.isEmpty()){
            trees temp=q.peek();
            int size=q.size();
            depth++;
            for(int i=0;i<size;i++){
                q.poll();
                if(temp.left!=null)
                    q.offer(temp.left);
                if(temp.right!=null)
                    q.offer(temp.right);
            }
        }
        return depth;
    }
    //the dfs iterative
    public static int depthI(trees node){
        if(node==null)
            return 0;
        Stack<trees> item=new Stack<>();
        Stack<Integer> value=new Stack<>();
        item.push(node);
        value.push(1);
        int max=0;
        while(!item.isEmpty()){
            trees temp=item.pop();
            int tempval=value.pop();
            max=Math.max(max,tempval);
            if(temp.left!=null){
                item.push(temp.left);
                value.push(tempval+1);
            }
            if(temp.right!=null){
                item.push(temp.right);
                value.push(tempval+1);
            }

        }
        return max;
    }
    //the diameter of the trees
    //need to return the highest length between two node through out the tree
    static int max=0;
    public static int diametercount(trees node){
        if(node==null)
            return 0;
        int left=diametercount(node.left);
        int right=diametercount(node.right);
        max=Math.max(max,left+right);
        return 1+Math.max(left,right);
    }
    public static int diameter(trees node){
        if(node==null)
            return 0;
        diametercount(node);
        return max;
    }
    public static void main(String[] args) {
        trees n=new trees(4);
        n.left=new trees(3);
        n.left.left=new trees(1);
        n.left.right=new trees(2);
        n.right=new trees(7);
        n.right.right=new trees(6);
        System.out.println(level(n));
        System.out.println(levelB(n));
        System.out.println(depthI(n));
//        inOrder(n);
//        System.out.println();
//        trees result1=conD(n);
//        inOrder(result1);
//        System.out.println();
//        conB(n);
//        inOrder(n);
    }
}
