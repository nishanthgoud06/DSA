import java.util.*;

public class TreesAndGraph {
    static class Graph{
        int val;
        List<Integer>[] adjList;
        public Graph(int val){
            this.val=val;
            adjList=new List[val];
            for(int i=0;i<val;i++){
                adjList[i]=new ArrayList<>();
            }
        }
        public void add(int u,int v){
            adjList[u].add(v);
        }
    }
    //Route Between Nodes
    public static boolean RBNodes(int i,int j,Graph graph){
        boolean[] visited=new boolean[graph.val];
        visited[i]=true;
        Queue<Integer> queue=new LinkedList<>();
        queue.offer(i);
        while(!queue.isEmpty()){
            int temp=queue.poll();
            for(int ele: graph.adjList[temp]){
                if(ele==j){
                    return true;
                }
                if(!visited[ele]){
                    visited[ele]=true;
                    queue.offer(ele);
                }
            }
        }
        return false;
    }
    //Minimal Tree
    static class Tree{
        int val;
        Tree left,right;
        public Tree(int val){

            this.val=val;
            this.left=null;
            this.right=null;
        }
        public Tree(int val,Tree left,Tree right){
            this.val=val;
            this.left=left;
            this.right=right;
        }
        public Tree(){}
    }
    public static void Inorder(Tree node){
        if(node==null){
            return;
        }
        Inorder(node.left);
        System.out.println(node.val);
        Inorder(node.right);
    }
    //building the tree
    public static void insert(Tree tree,int[] arr){
        insertHelper(tree,0,arr.length-1,arr);
    }
    public static void insertHelper(Tree tree, int low, int high, int[] arr){
        if(low > high)
            return;
        int mid = low + (high - low) / 2;
        tree.val = arr[mid];
        tree.left = new Tree();
        tree.right = new Tree();
        insertHelper(tree.left, low, mid - 1, arr);
        insertHelper(tree.right, mid + 1, high, arr);
    }

    public static int height(Tree test){
        Queue<Tree> queue=new LinkedList<>();
        queue.offer(test);
        int result=0;
        while(!queue.isEmpty()){
            int size=queue.size();
            for(int i=0;i<size;i++){
                Tree temp=queue.poll();
                if(temp.left!=null){
                    queue.offer(temp.left);
                }
                if(temp.right!=null){
                    queue.offer(temp.right);
                }
            }
            result++;
        }
        return result;
    }
    //List of Depths
    public static List<List<Integer>> listDepth(Tree node){
        List<List<Integer>> result=new ArrayList<>();
        if(node==null)
            return result;
        Queue<Tree> queue=new LinkedList<>();
        queue.offer(node);
        while(!queue.isEmpty()){
            int size=queue.size();
            List<Integer> temp=new ArrayList<>();
            for(int i=0;i<size;i++){
                Tree n=queue.poll();
                temp.add(n.val);
                if(n.left!=null){
                    queue.offer(n.left);
                }
                if(n.right!=null){
                    queue.offer(n.right);
                }
            }
            result.add(temp);
        }
        return result;
    }
    //Check Balanced
    public static boolean isBalanced(Tree node){
        if(node==null)
            return true;
       int left=isBalancedHelper(node.left);
       int right=isBalancedHelper(node.right);
       if(Math.abs(left-right)>1)
           return false;
       return isBalanced(node.left)&&isBalanced(node.right);
    }
    public static int isBalancedHelper(Tree node){
        if(node==null)
            return 0;
        return Math.max(isBalancedHelper(node.left),isBalancedHelper(node.right))+1;
    }
    //Validate BST
    public static boolean isValidBST(Tree root) {
        if(root==null)
            return true;
        return validCheck(root,null,null);
    }
    public static boolean validCheck(Tree root,Integer min,Integer max){
        if(root==null)
            return true;
        if(min!=null&&root.val<=min||max!=null&&root.val>=max)
            return false;
        return validCheck(root.left,min,root.val)&&validCheck(root.right,root.val,max);
    }
    //Succesor
    public static int Successor(Tree node,int val){
        Tree current=node;
        Tree Successor=null;
        while(current!=null){
            if(current.val>val){
                Successor=current;
                current=current.left;
            }else{
                current=current.right;
            }
        }
        if(Successor==null)
            return -1;
        return Successor.val;
    }
    //Predecessor
    public static int Predecessor(Tree node,int val){
        Tree Pred=null;
        Tree current=node;
        while(current!=null){
            if(current.val<val){
                Pred=current;
                current=current.right;
            }else{
                current=current.left;
            }
        }
        if(Pred==null)
            return -1;
        return Pred.val;
    }
    public static Tree getSuc(Tree node){
        while(node.left!=null){
            node=node.left;
        }
        return node;
    }
    //delete a node
    public static void DeleteNode(Tree node, int val) {
        if (node == null)
            return;
        node = deleteHelper(node, val);
    }

    public static Tree deleteHelper(Tree node, int val) {
        if (node == null)
            return null;
        if (node.val > val) {
            node.left = deleteHelper(node.left, val);
        } else if (node.val < val) {
            node.right = deleteHelper(node.right, val);
        } else {
            if (node.left == null)
                return node.right;
            else if (node.right == null)
                return node.left;
            else {
                Tree suc = getSuc(node.right);
                node.val = suc.val;
                node.right = deleteHelper(node.right, suc.val);
            }
        }
        return node;
    }
//    Build Order
    public static List<String> Build(String[] Projects,String[][] dependency){
        List<String> result=new ArrayList<>();
        HashMap<String,List<String>> hashmap=new HashMap<>();
        for(String p:Projects){
            hashmap.put(p,new ArrayList<>());
        }
        for(String[] d:dependency){
            hashmap.get(d[0]).add(d[1]);
        }
        Set<String> visited=new HashSet<>();
        for(String i:Projects){
            buildHelper(i,result,hashmap,visited);
        }
        if(result.size()!=Projects.length)
            System.out.println("there is a loop in the project");
        Collections.reverse(result);
        return result;
    }
    public static void buildHelper(String s,List<String> result,HashMap<String,List<String>> hashmap,Set<String> visited){
        if(visited.contains(s))
            return;
        visited.add(s);
        for(String str:hashmap.get(s)){
            buildHelper(str,result,hashmap,visited);
        }
        result.add(s);
    }
    //First Common Ancestors
    public static Tree Fcs(Tree tree,Tree left,Tree right){
        if(tree==null)
            return null;
        System.out.println("Visiting node " + tree.val);
        if(tree.val==left.val||tree.val==right.val)
            return tree;
        Tree left_node=Fcs(tree.left,left,right);
        Tree right_node=Fcs(tree.right,left,right);
        if(left_node!=null&&right_node!=null){
            return tree;
        }else if(left_node!=null)
            return left_node;
        else
            return right_node;
    }
//Build Order
    public static List<String> BuildOrder(List<String> Projects,String[][] dependency){
        List<String> result=new ArrayList<>();
        if(Projects.isEmpty())
            return result;
        HashSet<String> visited=new HashSet<>();
        HashMap<String,List<String>> hashMap=new HashMap<>();
        for(int i=0;i<Projects.toArray().length;i++){
            hashMap.put(Projects.get(i),new ArrayList<>());
        }
        for(String[] d:dependency){
            hashMap.get(d[0]).add(d[1]);
        }
        for(String i:Projects){
            buildOrderHelper(i,hashMap,result,visited);
        }

        Collections.reverse(result);
        return result;
    }
    public static void buildOrderHelper(String Project,HashMap<String,List<String>> dependency,List<String> result,HashSet<String> Visited){
        if(Visited.contains(Project))
            return;
        Visited.add(Project);
        for(String i:dependency.get(Project)){
            buildOrderHelper(i,dependency,result,Visited);
        }
        result.add(Project);
    }
    //BST Sequence
    public static List<List<Integer>> BSTsequence(Tree t){
        List<List<Integer>> result=new ArrayList<>();
        if(t==null){
            result.add(new ArrayList<>());
            return result;
        }
        List<Integer> prefix=new ArrayList<>();
        prefix.add(t.val);
        List<List<Integer>> left=BSTsequence(t.left);
        List<List<Integer>> right=BSTsequence(t.right);
        for(List<Integer> l:left){
            for(List<Integer> r:right){
                List<List<Integer>> temp=new ArrayList<>();
                BSTSequenceHelper(l,r,prefix,temp);
                result.addAll(temp);
            }
        }
        return result;
    }
    public static void BSTSequenceHelper(List<Integer> left,List<Integer> right,List<Integer> prefix,List<List<Integer>> result){
        if(left.isEmpty()||right.isEmpty()){
            List<Integer> results=new ArrayList<>(prefix);
            results.addAll(left);
            results.addAll(right);
            result.add(results);
            return;
        }
        int firstHead=left.remove(0);
        prefix.add(firstHead);
        BSTSequenceHelper(left,right,prefix,result);
        prefix.remove(prefix.size()-1);
        left.add(0,firstHead);
        int secondHead=right.remove(0);
        prefix.add(secondHead);
        BSTSequenceHelper(left,right,prefix,result);
        prefix.remove(prefix.size()-1);
        right.add(0,secondHead);
    }
    //Check SubTree
    public static boolean isSub(Tree Big,Tree small){
        if(Big==null)
            return false;
        if(small==null)
            return true;
        if(isSubHelper(Big,small))
            return true;
        return isSub(Big.left,small)||isSub(Big.right,small);
    }
    public static boolean isSubHelper(Tree big,Tree small){
        if(big==null&&small==null)
            return true;
        if(big==null||small==null)
            return false;
        if(big.val!= small.val)
            return false;
        return isSubHelper(big.left,small.left)&&isSubHelper(big.right,small.right);
    }
    static class TreeNode{
        int val;
        TreeNode left,right;
        List<Integer> count=new ArrayList<>();
        public TreeNode(int val){
            this.val=val;
        }
        TreeNode temp;
        public void insert(int val){
            count.add(val);
            TreeNode t=temp;
            if(t==null){
                t=new TreeNode(val);
            }
            if(val<t.val){
                t.left=new TreeNode(val);
            }else{
                t.right=new TreeNode(val);
            }
        }
        public boolean find(int val){
            TreeNode t=temp;
            if(t==null)
                return false;
            while(t!=null){
                if(t.val==val)
                    return true;
                else if(t.val<val){
                    t=t.left;
                }else{
                    t=t.right;
                }
            }
            return  false;
        }
        public void delete(int val){
            TreeNode t=temp;
            if(t==null)
                return;
            deleteHelper(val,t);
        }
        public TreeNode suc(TreeNode t){
            if(t==null)
                return null;
            if(t.left!=null){
                t=t.left;
            }
            return t;
        }
        public TreeNode deleteHelper(int val,TreeNode t){
            if(val<t.val){
                deleteHelper(val,t.left);
            }else if(val>t.val){
                deleteHelper(val,t.right);
            }else{
                if(t.left==null&& t.right==null){
                    t=null;
                } else if (t.left==null) {
                    t=t.right;
                }else if(t.right==null){
                    t=t.left;
                }else{
                    TreeNode a=suc(t.right);
                    t.val=a.val;
                    t.right=deleteHelper(a.val, t.right);
                }
            }
            return t;
        }
        public TreeNode getRandomNode(){
            Random r=new Random();
            int val=r.nextInt(count.size());
            TreeNode t=temp;
            return getRandomNodeHelper(t,val);
        }
        public TreeNode getRandomNodeHelper(TreeNode t, int randomIndex){
            if(t == null){
                return null;
            }
            int leftCount = t.left == null ? 0 : t.left.count.size();
            if(randomIndex == leftCount){
                return t;
            } else if(randomIndex < leftCount){
                return getRandomNodeHelper(t.left, randomIndex);
            } else {
                return getRandomNodeHelper(t.right, randomIndex - leftCount - 1);
            }
        }
    }
    //Paths to Sum
    public static List<List<Integer>> PathSum(Tree t,int val){
        List<List<Integer>> result=new ArrayList<>();
        if(t==null||val==0)
            return result;
       pathSumHelper(t,val,result,new ArrayList<Integer>());
       return result;
    }
    public static void pathSumHelper(Tree t,int val,List<List<Integer>> result,List<Integer> temp){
        if (t == null) {
            return;
        }
        temp.add(t.val);
        if (t.left == null && t.right == null && val == t.val) {
            result.add(new ArrayList<>(temp));
        } else {
            pathSumHelper(t.left, val - t.val, result, temp);
            pathSumHelper(t.right, val - t.val, result, temp);
        }
        temp.remove(temp.size() - 1);
    }
    public static void main(String[] args) {
        //test case for path sum
        Tree test=new Tree(5);
        test.left=new Tree(4);
        test.right=new Tree(8);
        test.left.left=new Tree(11);
        test.left.left.left=new Tree(7);
        test.left.left.right=new Tree(2);
        test.right.left=new Tree(13);
        test.right.right=new Tree(4);
        test.right.right.left=new Tree(5);
        test.right.right.right=new Tree(1);
        List<List<Integer>> expected1 = new ArrayList<>();
        expected1.add(Arrays.asList(5, 4, 11, 2));
        expected1.add(Arrays.asList(5, 8, 4, 5));
        expected1.add(Arrays.asList(5, 8, 4, 1));
        List<List<Integer>> result1 = PathSum(test,22);
        System.out.println(result1);
        System.out.println("Test case 1: " + result1.equals(expected1));
        //test case for Random Node
        //test case for is SubTree
//        Tree test=new Tree(1);
//        test.left=new Tree(2);
//        test.right=new Tree(3);
//        test.left.left=new Tree(4);
//        test.left.right=new Tree(5);
//        Tree test1=new Tree(2);
//        test1.left=new Tree(4);
//        test1.right=new Tree(5);
//        test1.right=new Tree(3);
//        System.out.println(isSub(test,test1));
        //test case for BST Sequences
//        Tree test=new Tree(2);
//        test.left=new Tree(1);
//        test.right=new Tree(3);
//        System.out.println(BSTsequence(test));
        //test case for First Common Ancestors
//        Tree test=new Tree(2);
//        test.left=new Tree(3);
//        test.right=new Tree(5);
//        test.left.left=new Tree(7);
//        test.left.right=new Tree(8);
//        test.right.left=new Tree(9);
//        test.right.right=new Tree(10);
//        System.out.println(Fcs(test,new Tree(9),new Tree(10)).val);
        //test case for project Builder
//        System.out.println(Build(new String[]{"a","b","c","d","e","f"},new String[][]{{"a","d"},{"f","d"},{"b","d"},{"f","a"},{"d","c"}}));
        //test case for Successor
//        Tree test=new Tree(5);
//        test.left=new Tree(2);
//        test.left.left=new Tree(1);
//        test.left.right=new Tree(3);
//        test.right=new Tree(7);
//        test.right.left=new Tree(6);
//        test.right.right=new Tree(9);
//        System.out.println("Succesor"+Successor(test,3));
//        System.out.println("Pred"+Predecessor(test,3));
        //test case for deleting a node form the Bst
//        DeleteNode(test,5);
//        Inorder(test);
        //testcase for minimal Tree
//        int[] test={1,2,3,6,7,8,9};
//        Tree testing=new Tree();
//        insert(testing,test);
//        System.out.println(listDepth(testing));
//        System.out.println(isBalanced(testing));
//        Inorder(testing);
//        System.out.println(height(testing));
        //test case for Route Between Nodes
//        Graph test=new Graph(4);
//        test.add(0,1);
//        test.add(1,2);
//        test.add(2,3);
//        System.out.println(RBNodes(0,3,test));
    }
}
