public class testingtree {
    public static class node{
        int val;
        node left,right;
        public node(int val) {
            this.val = val;
            this.left = null;
            this.right = null;
        }
    }
    node root;
    public testingtree(){
        root=null;
    }
    public void inorder(){
        inordering(root);
    }
    public void inordering(node root){
        if(root!=null){
            inordering(root.left);
            System.out.println(root.val);
            inordering(root.right);
        }
    }
    public void preorder(){
        preordering(root);
    }
    public void preordering(node root){
        if(root!=null){
            System.out.println(root.val);
            preordering(root.left);
            preordering(root.right);
        }
    }
    public void postordering(node root){
        if(root!=null){
            postordering(root.left);
            postordering(root.right);
            System.out.println(root.val);
        }
    }
    public void postorder(){
        postordering(root);
    }
    public static void main(String[] args) {
        testingtree tree=new testingtree();
        tree.root=new node(1);
        tree.root.left=new node(2);
        tree.root.right=new node(3);
        tree.root.left.left=new node(4);
        tree.root.left.right=new node(5);
        System.out.println("pre-order");
        tree.preorder();
        System.out.println("inorder");
        tree.inorder();
        System.out.println("postorder");
        tree.postorder();
    }
}
