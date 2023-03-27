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
    //sum Lists
    public static node sumList(node list1,node list2){
        if(list1==null)
            return list2;
        if(list2==null)
            return list1;
        int carry=0;
        int sum=0;
        node result=new node(0);
        node dummy=result;
        while(list1!=null&&list2!=null){
            int val1= list1.val;
            int val2=list2.val;
            int total=val1+val2;
            sum=total%10;
            dummy.next=new node(sum+carry);
            dummy=dummy.next;
            carry=total/10;
            list1=list1.next;
            list2=list2.next;
        }
        if(carry!=0){
            node temp=new node(carry);
            dummy.next=temp;
        }
        return result.next;
    }
    //palindrome
    public static boolean isPalindrome(node n){
        if(n==null||n.next==null)
            return true;
        node slow=n;
        node fast=n;
        while(fast!=null&&fast.next!=null){
            slow=slow.next;
            fast=fast.next.next;
        }
        node prev=null;
        node current=slow.next;
        while(current!=null){
            node next=current.next;
            current.next=prev;
            prev=current;
            current=next;
        }
        node part1=n;
        node part2=prev;
        while(part2!=null){
            if(part1.val!= part2.val){
                return false;
            }
            part1=part1.next;
            part2=part2.next;
        }
        return true;
    }
    //Intersectionm
    public static node intersection(node list1,node list2){
        if(list1==null||list2==null)
            return null;
        int count=0;
        node l1=list1;
        node l2=list2;
        int count1=lengthNode(l1);
        int count2=lengthNode(l2);
        int diff=Math.abs(count1-count2);
        System.out.println(diff);
        if(count1>count2)
            l1=changeNode(l1,diff);
        else
            l2=changeNode(l2,diff);
        while(l1!=null&&l2!=null){
            if(l1.val==l2.val)
                return l2;
            l1=l1.next;
            l2=l2.next;
        }
        return null;
    }
    public static node changeNode(node n,int limit){
        while(limit>0){
            n=n.next;
            limit=limit-1;
        }
        return n;
    }
    public static int lengthNode(node n){
        int count=0;
        while(n!=null){
            count++;
            n=n.next;
        }
        return count;
    }
    //Loop detection
    public static node loopDetect(node n){
        if(n ==null)
            return n;
        node slow=n;
        node fast=n;
        while(fast!=null&&fast.next!=null){
            slow=slow.next;
            fast=fast.next.next;
            if(slow.val== fast.val){
                break;
            }
        }
        node slow2=n;
        while(slow2.next.val!=slow.val){
            slow2=slow2.next;
        }
        return slow2;
    }
    public static void main(String[] args) {
        //test case for loop detection
        node n1=new node(1);
        node n2=new node(2);
        node n3=new node(3);
        node n4=new node(4);
        node n5=new node(5);
        n1.next=n2;
        n2.next=n3;
        n3.next=n4;
        n4.next=n5;
        n5.next=n3;
        System.out.println(loopDetect(n1).val);
        //test case for intersection
//        node n1=new node(1);
//        n1.next=new node(2);
//        n1.next.next=new node(3);
//        n1.next.next.next=new node(4);
//        node n2=new node(5);
//        n2.next=new node(6);
//        n2.next.next=new node(7);
//        n2.next.next.next=new node(3);
//        n2.next.next.next.next=new node(4);
//        System.out.println(intersection(n1,n2));
        //test case for palindrome
//        node n=new node(1);
//        n.next=new node(2);
//        n.next.next=new node(3);
//        n.next.next.next=new node(3);
//        n.next.next.next.next=new node(2);
//        n.next.next.next.next.next=new node(1);
//        System.out.println(isPalindrome(n));
        //test case for Sum Lists
//        node node1=new node(7);
//        node1.next=new node(1);
//        node1.next.next=new node(6);
//        node node2=new node(5);
//        node2.next=new node(9);
//        node2.next.next=new node(2);
//        printList(sumList(node1,node2));
        //test case for partation
//        node n=new node(3);
//        n.next=new node(5);
//        n.next.next=new node(8);
//        n.next.next.next=new node(5);
//        n.next.next.next.next=new node(10);
//        n.next.next.next.next.next=new node(2);
//        n.next.next.next.next.next.next=new node(1);
//
//        printList(partition(n,5));
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
