import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;

public class gfgBasic {
    //this class includes all the basic level form gfg practise section
    //Cyclically rotate an array by one
    public static void rotateCy(int[] a){
        int temp=a[a.length-1];
        for(int i=a.length-1;i>=1;i--){
            a[i]=a[i-1];
        }
        a[0]=temp;
    }
    //First and last occurrences of x
    //Given a sorted array arr containing n elements with possibly duplicate elements,
    // the task is to find indexes of first and last occurrences of an element x in the given array
    public static ArrayList<Integer> flO(int[] a,int target){
        int apperance=0;
        int  first=-1;
        int last =-1;
        for(int i=0;i<a.length;i++){
            if(a[i]==target && apperance==0){
                first=i;
                apperance=1;
            }
            if(a[i]==target){
                last=i;
            }
        }
        ArrayList<Integer> arrayList=new ArrayList<>(2);
        arrayList.add(first);
        arrayList.add(last);
        return arrayList;
    }
    //for this program we are implementing stacks using array
    //stacks perform last in first out.
    class g{
        int top=-1;
        int[] arr;
        public g(){
            arr=new int[100];
        }
        public  void push(int i){
            arr[++top]=i;
        }
        public int pop(){
            if(top==-1)
                return -1;
            else
                return arr[top--];
        }
    }
    class a{
        int front,rear=0;
        int[] arr;
        public a(){
            arr=new int[100];
        }
        void push(int i){
            arr[rear++]=i;
        }
        int pop(){
            if(rear-front>0){
                return arr[front++];
            }
            return -1;
        }
    }
    public static void sortit(int[][] arr){
        int a= arr.length;
        int b=arr[0].length;
        ArrayList<Integer> ab=new ArrayList<>();
        int k=0;
        for(int i=0;i<a;i++){
            for(int j=0;j<b;j++){
                ab.add(arr[i][j]);
            }
        }
        Collections.sort(ab);
        for(int i=0;i<a;i++){
            for(int j=0;j<b;j++){
                arr[i][j]=ab.get(k++);
            }
        }
    }
    public static void printing(int[][] a){
        for(int i=0;i<a.length;i++){
            for(int j=0;j<a[0].length;j++){
                System.out.println(a[i][j]);
            }
        }
    }
    //Bit Difference
    public static int bitDiff(int a,int b){
        int c=a^b;
        return Integer.bitCount(c);
    }
    //to check whether the enter Node is a circular linked list or not
    static class Node{
        int val;
        Node next;
        Node(int val){
            this.val=val;
            next=null;
        }
    }
    //to check wetaher the node is Circular or not
    public static boolean checlCir(Node test){
        Node temp=test;
        while(temp.next!=null){
            temp=temp.next;
        }
        if(temp.next==test)
            return true;
        return false;
    }
    public static int middle(int a,int b,int c){
        int result=Math.max(a,Math.max(b,c));
        if(result==a)
            result=Math.max(b,c);
        else if(result==b)
            result=Math.max(a,c);
        else
            result=Math.max(a,b);
        return result;
    }
    public static int median(int[] a){
        Arrays.sort(a);
        int mid= (int) Math.floor(a.length/2);
        return ((mid&1)==1)?a[mid]:(a[mid]+a[mid-1])/2;
    }
    public static void main(String[] args) {
        System.out.println(median(new int[] {56,67,30,79}));
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
