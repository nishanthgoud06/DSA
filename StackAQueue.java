import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class StackAQueue {
    public static class stack3{
        int[] top;
        int[] array;
        int current_size;
        public stack3(int size){
            top=new int[]{-1,-1,-1};
            array=new int[3*size];
            current_size=size;
        }
        public void add(int Stack_number,int number){
            if(top[Stack_number]==current_size-1){
                System.out.println("the stack you have selected is full");
                return;
            }

            top[Stack_number]++;
            array[Stack_number*current_size+top[Stack_number]]=number;
        }
        public void pop(int Stack_number){
            if(top[Stack_number]==-1){
                System.out.println("the stack you have selected is Empty");
            }
            top[Stack_number]--;
        }
        public int peek(int Stack_number){
            if(top[Stack_number]==-1)
                System.out.println("the stack you wan to view is empty");
            return array[Stack_number*current_size+top[Stack_number]];
        }
    }
    //Stack Min
    static class minStack{
        Stack<Integer> minStack;
        Stack<Integer> stack;
        public minStack(){
            minStack=new Stack<>();
            stack=new Stack<>();
        }
        public void push(int val){
            stack.push(val);
            if(minStack.isEmpty()||val<minStack.peek()){
                minStack.push(val);
            }
        }
        public void pop(){
            int min= stack.pop();
            if(min==minStack.peek()){
                minStack.pop();
            }
        }
        public int getMin(){
            return minStack.peek();
        }
        public int top(){
            return stack.peek();
        }
    }
    public static void main(String[] args) {
        //test case for Min Stack
        minStack abc=new minStack();
        abc.push(3);
        abc.push(1);
        abc.push(2);
        abc.push(5);
        abc.push(0);
        System.out.println(abc.getMin());
        abc.pop();
        System.out.println(abc.getMin());
        //test case for Three in One
//        stack3 test=new stack3(3);
//        test.add(0,1);
//        test.add(0,2);
//        test.add(0,3);
//        test.add(1,1);
//        test.add(1,2);
//        test.add(1,3);
//        test.add(2,1);
//        test.add(2,2);
//        test.add(2,3);
//        test.pop(2);
//        System.out.println(test.peek(2));

    }
}
