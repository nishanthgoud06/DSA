import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;

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
    public static void main(String[] args) {
        
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
