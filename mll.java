public class mll {
    //in this program we are going to implement merge sort using linked list
    Node head=null;
    static class Node{
        int val;
        Node next;
        public Node(int val){
            this.val=val;
        }
    }
    public Node merging(Node left,Node right){
        Node result=null;
        if(left==null)
            return right;
        if(right==null)
            return left;
        if(left.val<right.val) {
            result = left;
            result.next = merging(left.next, right);
        }else{
            result=right;
            result.next=merging(left,right.next);
        }
        return result;
    }
    public static Node getMiddle(Node n){
        if(n==null)
            return n;
        Node slow=n;
        Node fast=n;
        while(fast.next!=null&&fast.next.next !=null){
            slow=slow.next;
            fast=fast.next.next;
        }
        return slow;
    }
    public  Node merge(Node n){
        if(n==null||n.next==null)
            return n;
        Node middle=getMiddle(n);
        Node middleNext=middle.next;
        middle.next=null;
        Node left=merge(n);
        Node right=merge(middleNext);
        Node sortedList=merging(left,right);
        return sortedList;
    }


    public  void insert(int val){
        Node n=new Node(val);
        n.next=head;
        head=n;
    }
    public static void print(Node n){
        while(n!=null){
            System.out.println(n.val);
            n=n.next;
        }
    }
    public static void main(String[] args) {
        mll linkedlist=new mll();
        linkedlist.insert(15);
        linkedlist.insert(42);
        linkedlist.insert(3);
        linkedlist.insert(22);
        linkedlist.insert(14);
        System.out.println("before");
        print(linkedlist.head);
        System.out.println("after");
       linkedlist.head=linkedlist.merge(linkedlist.head);
        print(linkedlist.head);
    }
}
