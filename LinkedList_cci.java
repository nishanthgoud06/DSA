import java.util.HashSet;

public class LinkedList_cci {
    static class node{
        int val;
        node next;
        public node(){
        }
        public node(int val){
            this.val=val;
            next=null;
        }
        public node(int val,node next){
            this.val=val;
            this.next=next;
        }
    }
    //Remove dups form an unsorted LinkedList
    public static void removeDupi(node n){
      HashSet<Integer> hashset=new HashSet<>();
      node current=n;
      node previous=null;
      while(current!=null){
          if(hashset.contains(current.val)){
              previous.next=current.next;
          }else{
              hashset.add(current.val);
              previous=current;
          }
          current=current.next;
      }
    }
    //follow up without using Hashset
    public static void removeDuplicates(node n){
        if (n == null || n.next == null)
            return ;
        n = mergeSort(n);
        node current = n;
        while (current != null && current.next != null) { // Add this check
            if (current.val == current.next.val) {
                current.next = current.next.next;
            } else {
                current = current.next;
            }
        }
    }
    public static node getMiddle(node n){
        if (n == null)
            return n;
        node slow = n, fast = n.next;
        while (fast != null && fast.next != null) {
            fast = fast.next.next;
            slow = slow.next;
        }
        return slow;
    }
    public static node mergeSort(node n) {
        if (n == null || n.next == null)
            return n;
        node mid = getMiddle(n);
        node left = n;
        node right = mid.next;
        mid.next = null;
        node sortedLeft = mergeSort(left);
        node sortedRight = mergeSort(right);
        return merge(sortedLeft, sortedRight);
    }
    public static node merge(node left,node right){
        node dummy = new node(0);
        node tail = dummy;
        while (left != null && right != null) {
            if (left.val < right.val) {
                tail.next = left;
                left = left.next;
            } else {
                tail.next = right;
                right = right.next;
            }
            tail = tail.next;
        }
        if (left != null) {
            tail.next = left;
        } else {
            tail.next = right;
        }
        return dummy.next;
    }
    public static void printList(node n) {
        while (n!= null) {
            System.out.print(n.val + " ");
            n = n.next;
        }
        System.out.println();
    }
    //return Kth to the last
    public static int klast(node n,int k){
        if(n==null)
            return 0;
        node slow=n,fast=n;
        int count=0;
        while(fast!=null&&fast.next!=null){
            slow=slow.next;
            count=k;
            while(count>0){
                fast=fast.next;
                count=count-1;
            }
        }
        return slow.val;
    }
    //delete the middle node
    public static void deleteMiddle(node n){
        if(n==null)
            return;
        node slow=n;
        node summy=new node(0);
        summy.next=slow;
        node fast=n;
        node prev_slow = null;
        while(fast!=null&&fast.next!=null){
            prev_slow = summy;
            summy=summy.next;
            fast=fast.next.next;
        }
        // Remove the middle node
        prev_slow.next = summy.next;
    }
    public static node partition(node n,int pivot){
        if(n==null)
            return n;
        node head1=null;
        node tail1=null;
        node head2=null;
        node tail2=null;
        node temp=n;
        while(temp!=null){
            if(temp.val<pivot){
                if(head1==null){
                    head1=temp;
                    tail1=temp;
                }else{
                    tail1.next=temp;
                    tail1=temp;
                }
            }else{
                if(head2==null){
                    head2=temp;
                    tail2=temp;
                }else{
                    tail2.next=temp;
                    tail2=temp;
                }
            }
            temp=temp.next;
        }
        if (head1 == null)
            return head2;
        tail1.next = head2;
        if (tail2 != null)
            tail2.next = null;
        return head1;
    }
    public static void main(String[] args) {
        //test case for partation
        node n=new node(3);
        n.next=new node(5);
        n.next.next=new node(8);
//        n.next.next.next=new node(5);
//        n.next.next.next.next=new node(10);
//        n.next.next.next.next.next=new node(2);
//        n.next.next.next.next.next.next=new node(1);

        printList(partition(n,5));
        //test case for Return Kth to Last
//        node n = new node(1);
//        n.next = new node(2);
//        n.next.next = new node(3);
//        n.next.next.next = new node(4);
//        n.next.next.next.next = new node(5);
//        deleteMiddle(n);
//        printList(n);
//        System.out.println(klast(n,2));
//        node n=new node(1);
//        n.next=new node(3);
//        n.next.next=new node(5);
//        n.next.next.next=new node(2);
//        n.next.next.next.next=new node(2);
//        n.next.next.next.next.next=new node(7);
//        n.next.next.next.next.next.next=new node(6);
//        n.next.next.next.next.next.next.next=new node(6);
//        n.next.next.next.next.next.next.next.next=new node(6);
//        n.next.next.next.next.next.next.next.next.next=new node(8);
////        print(n);
////        removeDupi(n);
////        print(n);
//        removeDuplicates(n);
//        print(n);
    }
}
