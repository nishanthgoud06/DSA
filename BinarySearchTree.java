public class BinarySearchTree {
//    static class Node{
//        int val;
//        Node left,right;
//        public Node(int val){
//            this.val=val;
//            this.left=null;
//            this.right=null;
//        }
//    }
//    Node root;
//    public BinarySearchTree(){
//        root=null;
//    }
//    public void inserting(int val){
//        root=insert(root,val);
//    }
//    public  Node insert(Node root,int val){
//        if(root==null){
//            root=new Node(val);
//            return root;
//        }else if(root.val>val){
//            root.left=insert(root.left,val);
//        }else if(root.val<val){
//            root.right=insert(root.right,val);
//        }
//        return root;
//    }
//    public Node search(Node root,int val){
//        if(root==null||root.val==val)
//            return root;
//        else if(root.val<val)
//            return search(root.left,val);
//        else
//            return search(root.right,val);
//    }
//    public void inOrderRef(){
//         inOrder(root);
//    }
//    public void inOrder(Node root){
//        if(root!=null){
//            inOrder(root.left);
//            System.out.println(root.val);
//            inOrder(root.right);
//        }
//    }
//
//    public static void main(String[] args) {
//    BinarySearchTree bst=new BinarySearchTree();
//    bst.inserting(50);
//    bst.inserting(30);
//    bst.inserting(20);
//    bst.inserting(40);
//    bst.inserting(70);
//    bst.inserting(60);
//    bst.inserting(80);
//    bst.inOrderRef();
//    }
    static class node{
        int data;
        node left,right;
        public node(int data){
            this.data=data;
            this.left=null;
            this.right=null;
        }
}
node root;
    public BinarySearchTree(){
        root=null;
    }
    public void insert(int val){
        root=inserting(root,val);
    }
    public node inserting(node root,int val){
        if(root==null) {
            root = new node(val);
            return root;
        }else if(val<root.data){
            root.left=inserting(root.left,val);
        }else if(val> root.data){
            root.right=inserting(root.right,val);
        }
        return root;
    }
    public void inorder(){
        inordering(root);
    }
    public static void inordering(node root){
        if(root!=null){
            inordering(root.left);
            System.out.println(root.data);
            inordering(root.right);
        }
    }
    public  void preorder(){
        preordering(root);
    }
    public static void preordering(node root){
        if(root!=null){
            System.out.println(root.data);
            preordering(root.left);
            preordering(root.right);
        }
    }
    public void postorder(){
        postordering(root);
    }
    public static void postordering(node root){
        if(root!=null){
            postordering(root.left);
            postordering(root.right);
            System.out.println(root.data);
        }
    }
    public void delete(int val){
        deleteing(root,val);
    }
    node deleteing(node n,int val){
        if(n==null)
            return n;
        if(n.data>val)
            n.left=deleteing(n.left,val);
        else if(n.data<val)
            n.right=deleteing(n.right,val);
        else{
            if(n.left==null)
                return n.right;
            else if(n.right==null)
                return n.left;
            n.data=minValue(n.right);
            n.right=deleteing(n.right,n.data);
        }
        return n;
    }
    int minValue(node root){
        int node_data=root.data;
        while(root.left!=null){
            node_data=root.left.data;
            root=root.left;
        }
        return root.data;
    }
    public static void main(String[] args) {
        BinarySearchTree bst=new BinarySearchTree();
        bst.insert(50);
        bst.insert(30);
        bst.insert(20);
        bst.insert(40);
        bst.insert(70);
        bst.insert(60);
        bst.insert(80);
        bst.inorder();
        bst.delete(80);
        System.out.println("after");
        bst.inorder();
    }
}
