public class lList {
    //in this class file we are going to include all of the problems related to linked List
    static class node {
        int val;
        node next;
        public node(int val) {
            this.val = val;
            this.next = null;
        }
        public node() {}
        public node(int val, node n) {
            this.val = val;
            this.next = n;
        }
    }
        //    148. Sort List
        public static node sort(node root){
            if(root==null){
                return null;
            }
            node slow=root;
            node fast=root;
            node temp=root;
            while(fast!=null&&fast.next!=null){
                temp=slow;
                slow=slow.next;
                fast=fast.next.next;
            }
            temp.next=null;
            node left=root;
            node right=slow;
            return merge(left,right);
        }
        public static node merge(node left,node right){
        node temp=new node(0);
        node current=temp;
        while(left!=null&&right!=null){
            if(left.val<right.val){
                current.next=left;
                left=left.next;
            }else{
                current.next=right;
                right=right.next;
            }
            current=current.next;
        }
        if(left!=null){
            current.next=left;
            left=left.next;
        }
        if(right!=null){
            current.next=right;
            right=right.next;
        }
        return temp.next;
    }
//19. Remove Nth Node From End of List
    public static node removeN(node root,int n){
        if(root==null)
            return null;
        node temp=new node(0);
        node left=temp;
        node right=temp;
        for(int i=1;i<=n+1;i++){
            right=right.next;
        }
        while(right!=null){
            left=left.next;
            right=right.next;
        }
        left.next=left.next.next;
        return root;
    }
//    234. Palindrome Linked List
    public static boolean paliList(node head){
        node slow=head,fast=head;
        while(fast!=null&&fast.next!=null){
            slow=slow.next;
            fast=fast.next.next;
        }
        node prev=slow;
        slow=slow.next;
        prev.next=null;
        while(slow!=null){
            node next=slow.next;
            slow.next=prev;
            prev=slow;
            slow=next;
        }
        node left=head;
        node right=prev;
        while(right!=null){
            if(left.val!=right.val)
                return false;
            left=left.next;
            right=right.next;
        }
        return true;
    }
//    328. Odd Even Linked List
    public static node newLink(node n){
        if(n==null)
            return null;
        node odd=n;
        node even=n.next;
        node evenhead=even;
        while(even!=null&&even.next!=null){
            odd.next=even.next;
            odd=odd.next;
            even.next=odd.next;
            even=even.next;
        }
        odd.next=evenhead;
        return n;
    }

    public static void main(String[] args) {
        node n=new node(8);
        node n1=new node(5,n);
        node n2=new node(1,n1);
        node n3=new node(8,n2);
        node n4=new node(-1);
//        System.out.println(sort(n));
        System.out.println(paliList(n));
    }
}
