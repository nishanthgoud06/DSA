import java.lang.reflect.Array;
import java.util.*;

public class gfgBasic {
//    //this class includes all the basic level form gfg practise section
//    //Cyclically rotate an array by one
//    public static void rotateCy(int[] a){
//        int temp=a[a.length-1];
//        for(int i=a.length-1;i>=1;i--){
//            a[i]=a[i-1];
//        }
//        a[0]=temp;
//    }
//    //First and last occurrences of x
//    //Given a sorted array arr containing n elements with possibly duplicate elements,
//    // the task is to find indexes of first and last occurrences of an element x in the given array
//    public static ArrayList<Integer> flO(int[] a,int target){
//        int apperance=0;
//        int  first=-1;
//        int last =-1;
//        for(int i=0;i<a.length;i++){
//            if(a[i]==target && apperance==0){
//                first=i;
//                apperance=1;
//            }
//            if(a[i]==target){
//                last=i;
//            }
//        }
//        ArrayList<Integer> arrayList=new ArrayList<>(2);
//        arrayList.add(first);
//        arrayList.add(last);
//        return arrayList;
//    }
//    //for this program we are implementing stacks using array
//    //stacks perform last in first out.
//    static class g{
//        int top=-1;
//        int[] arr;
//        public g(){
//            arr=new int[100];
//        }
//        public  void push(int i){
//            arr[++top]=i;
//        }
//        public int pop(){
//            if(top==-1)
//                return -1;
//            else
//                return arr[top--];
//        }
//    }
//    static class a{
//        int front,rear=0;
//        int[] arr;
//        public a(){
//            arr=new int[100];
//        }
//        void push(int i){
//            arr[rear++]=i;
//        }
//        int pop(){
//            if(rear-front>0){
//                return arr[front++];
//            }
//            return -1;
//        }
//    }
//    public static void sortit(int[][] arr){
//        int a= arr.length;
//        int b=arr[0].length;
//        ArrayList<Integer> ab=new ArrayList<>();
//        int k=0;
//        for(int i=0;i<a;i++){
//            for(int j=0;j<b;j++){
//                ab.add(arr[i][j]);
//            }
//        }
//        Collections.sort(ab);
//        for(int i=0;i<a;i++){
//            for(int j=0;j<b;j++){
//                arr[i][j]=ab.get(k++);
//            }
//        }
//    }
//    public static void printing(int[][] a){
//        for(int i=0;i<a.length;i++){
//            for(int j=0;j<a[0].length;j++){
//                System.out.println(a[i][j]);
//            }
//        }
//    }
//    //Bit Difference
//    public static int bitDiff(int a,int b){
//        int c=a^b;
//        return Integer.bitCount(c);
//    }
//    //to check whether the enter Node is a circular linked list or not
//    static class Node{
//        int val;
//        Node next;
//        Node(int val){
//            this.val=val;
//            next=null;
//        }
//    }
//    //to check whether a binary search tree is balanced or not
//    //to check wetaher the node is Circular or not
//    public static boolean checlCir(Node test){
//        Node temp=test;
//        while(temp.next!=null){
//            temp=temp.next;
//        }
//        if(temp.next==test)
//            return true;
//        return false;
//    }
//    public static int middle(int a,int b,int c){
//        int result=Math.max(a,Math.max(b,c));
//        if(result==a)
//            result=Math.max(b,c);
//        else if(result==b)
//            result=Math.max(a,c);
//        else
//            result=Math.max(a,b);
//        return result;
//    }
//    public static int median(int[] a){
//        Arrays.sort(a);
//        int mid= (int) Math.floor(a.length/2);
//        return ((mid&1)==1)?a[mid]:(a[mid]+a[mid-1])/2;
//    }
//    //Find the Duplicate Number
//    //Given an array of integers nums containing n + 1 integers where each integer is in the range [1, n] inclusive.
//    //
//    //There is only one repeated number in nums, return this repeated number.
//    //
//    //You must solve the problem without modifying the array nums and uses only constant extra space.
//    public static  int Dupli(int[] a){
//        int slow=a[0];
//        int fast=a[0];
//        do{
//            slow=a[slow];
//            fast=a[a[fast]];
//        }while(slow!=fast);
//        fast=a[0];
//        while(slow!=fast){
//            slow=a[slow];
//            fast=a[fast];
//        }
//        return slow;
//    }
//to check whether a given tree is balanced or not
    static class node{
        int val;
        node left,right;
        public node(int val){
            this.val=val;
            this.left=null;
            this.right=null;
        }
}
    static boolean result=true;
    public static int perform(node n){
        if(n==null)
            return 0;
        int left=perform(n.left);
        int right=perform(n.right);
        if(Math.abs(left-right)>1)
           result=false;
        return 1+Math.max(left,right);
    }
    public static boolean isBal(node n){

        perform(n);
        return result;
    }
    //to check whether the two node are same or not
    public static boolean similar(node n1,node n2){
        Queue<node> queue=new LinkedList<>();
        if(n1==null && n2==null)
            return true;
        if(n1==null||n2==null)
            return false;
        if(n1!=null&& n2!=null){
            queue.offer(n1);
            queue.offer(n2);
        }
        while(!queue.isEmpty()){
            node first= queue.poll();
            node second= queue.poll();
            if (first == null && second == null)
                continue;
            if (first == null || second == null)
                return false;
            if (first.val != second.val)
                return false;
            queue.offer(first.left);
            queue.offer(second.left);
            queue.offer(first.right);
            queue.offer(second.right);
        }
        return true;
    }
    //now we are going to check whether a subtree is part of a tree;
    public static boolean isPart(node n1,node n2){
        if(n1==null)
            return false;
        if(n1.val==n2.val){
            if(ispartornot(n1,n2))
                return true;
        }
        return isPart(n1.left,n2)||isPart(n1.right,n2);
    }
    public static boolean ispartornot(node n1,node n2){
        if(n1==null || n2==null)
            return n1==null&&n2==null;
        return n1.val==n2.val&&ispartornot(n1.left,n2.left)&&ispartornot(n1.right,n2.right);
    }
    //here we are going to return the total no of good values
    public static int good(node n){
        return values(n,-999);
    }
    public static int values(node n,int max){
        if(n==null)
            return 0;
        int result= n.val>max?1:0;
        result+=values(n.left,Math.max(n.val,max));
        result+=values(n.right,Math.max(n.val,max));
        return result;
    }
    //here we are going to implement Quick sort the fasted sorting algorithm of all the sorting methods
    //the time complexcity of the algorithm is O{nlogn)
    public static void swap(int[] arr,int i,int j){
        //we are going to swap using bit manupilation
        int temp=arr[i];
        arr[i]=arr[j];
        arr[j]=temp;
    }
    public static void QuickSort(int[] nums){
        QuickSortHelper(nums,0,nums.length-1);
    }
    public static void QuickSortHelper(int[] nums,int low,int high){
        if(low<high){
            int pivot=findingpivot(nums,low,high);
            QuickSortHelper(nums,low,pivot-1);
            QuickSortHelper(nums,pivot+1,high);
        }
    }
    public static int findingpivot(int[] nums,int low,int high){
        int i=low-1;
        int pivot=nums[high];
        int j=low;
        while(j<=high-1){
            if(nums[j]<pivot){
                i++;
                swap(nums,i,j);
            }
            j++;
        }
        swap(nums,i+1,high);
        return i+1;
    }
    public static void main(String[] args) {
        int[] test={6,7,1,2,5,4};
        System.out.println("Before "+Arrays.toString(test));
        QuickSort(test);
        System.out.println("After "+Arrays.toString(test));
//            node n=new node(3);
//            n.left=new node(1);
//            n.left.left=new node(3);
//            n.right=new node(4);
//            n.right.left=new node(1);
//            n.right.right=new node(5);
//        System.out.println(good(n));
//        node n=new node(3);
//        n.left=new node(9);
//        n.right=new node(20);
//        n.right.right=new node(1);
//        n.right.right.right=new node(5);
//        System.out.println(isBal(n));
//        System.out.println(Dupli(new int[]{1,2,3,4,5,1}));
//        System.out.println(median(new int[] {56,67,30,79}));
//        System.out.println(middle(23,45,65));
//        Node head=null;
//       Node a=new Node(1);
//       Node b=new Node(2);
//       Node c=new Node(3);
//       Node d=new Node(4);
//       Node e=new Node(5);
//       gfgBasic testing=new gfgBasic();
//       head=a;
//       a.next=b;
//       b.next=c;
//       c.next=d;
//       d.next=e;
//       e.next=a;
//        System.out.println(bitDiff(10,20));
//        int[][] a={{10,20,30,40},{15,25,35,45},{27,29,37,48},{32,33,39,50}};
//        printing(a);
//        System.out.println("\\\\\\\\\\\\\\");
//        sortit(a);
//        printing(a);
//        gfgBasic outer=new gfgBasic();
//        gfgBasic.a inner=outer.new a();
//        inner.push(2);
//        inner.push(3);
//        System.out.println(inner.pop());
//        inner.push(4);
//        System.out.println(inner.pop());
//        gfgBasic outer=new gfgBasic();
//        gfgBasic.g inner=outer.new g();
//        inner.push(2);
//        inner.push(3);
//        System.out.println(inner.pop());
//        inner.push(4);
//        System.out.println(inner.pop());

//        int[] a={9,8,7,6,4,2,1,3};
//        System.out.println(Arrays.toString(a));
//        rotateCy(a);
//        System.out.println(Arrays.toString(a));
//        System.out.println(flO(new int[]{ 1, 3, 5, 5, 5, 5, 67, 123, 125 },5));
    }
}
