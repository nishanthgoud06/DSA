import java.util.*;

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
    //Stack of Plates
    //for this problem we are going to use Listof stacks
    static class StackPlates{
        List<Stack<Integer>> stacks;
        int capacity;
        public StackPlates(int capacity){
            stacks=new ArrayList<>();
            this.capacity=capacity;
        }
        public void add(int val){
            if(stacks.isEmpty()||stacks.get(stacks.size()-1).size()==capacity){
                stacks.add(new Stack<>());
            }
            stacks.get(stacks.size()-1).add(val);
        }
        public int pop(){
            if(stacks.isEmpty()){
                System.out.println("there are no elements to pop");
                return -1;
            }
            int val=stacks.get(stacks.size()-1).pop();
            if(stacks.get(stacks.size()-1).size()==0){
                stacks.remove(stacks.size()-1);
            }
            return val;
        }
        public int popoAt(int index){
            if(stacks.isEmpty()){
                System.out.println();
            }
            Stack<Integer> temp=stacks.get(index);
            int val=temp.pop();
            if(temp.size()==0){
                stacks.remove(index);
            }
            return val;
        }
    }
    //Queue via stack
    static class QStack{
        Stack<Integer> stack1;
        Stack<Integer> stack2;
        public QStack(){
            stack1=new Stack<>();
            stack2=new Stack<>();
        }
        public void add(int val){
            if(!stack1.isEmpty()){
                stack2.push(stack1.pop());
            }
            stack1.push(val);
            while(!stack2.isEmpty()){
                stack1.push(stack2.pop());
            }
        }
        public int remove(){
            if(stack1.isEmpty()){
                return -1;
            }
            return stack1.pop();
        }
    }
    //Sort Stack
    static class sortStack{
        Stack<Integer> stack1;
        Stack<Integer> stack2;
        public sortStack(){
            stack1=new Stack<>();
            stack2=new Stack<>();
        }
        public void add(int val){
            if(stack1.isEmpty()){
                stack1.add(val);
                return;
            }
            while(!stack1.isEmpty() && stack1.peek()>val){
                stack2.add(stack1.pop());
            }
            stack1.add(val);
            while(!stack2.isEmpty()){
                stack1.add(stack2.pop());
            }
        }
        public int pop(){
            if(stack1.isEmpty())
                return -1;
            return stack1.pop();
        }
        public int peek(){
            if(stack1.isEmpty())
                return -1;
            return stack1.peek();
        }
        public boolean isEmpty(){
            return stack1.isEmpty();
        }
    }
    static class animalShelter{
        Queue<Integer> cats;
        Queue<Integer> Dogs;
        public animalShelter(){
            cats=new LinkedList<>();
            Dogs=new LinkedList<>();
        }
        public void enqueue(String type,int animal){
            if(type.equals("cats")){
                cats.add(animal);
                return;
            }
            if(type.equals("dogs")){
                Dogs.add(animal);
                return;
            }
        }
        public int DequeueAny(){
            Random rand=new Random();
            int num=rand.nextInt(2);
            if((num==0&&cats.size()>1)||(num==1&&Dogs.size()==0&&cats.size()>1)){
                return cats.poll();
            } else if ((num==1&&Dogs.size()>1)||(num==0&&cats.size()==0&&Dogs.size()>1)) {
                return Dogs.poll();
            }else{
                System.out.println("there are no more animals in the shelter to adapt");
                return 1;
            }
        }
        public int DequeuCats(){
            if(cats.size()>1){
                return cats.poll();
            }else{
                System.out.println("there are no more cats to adopt");
                return -1;
            }
        }
        public int DequeuDogs(){
            if(Dogs.size()>1){
                return Dogs.poll();
            }else{
                System.out.println("there are no more Dogs to adopt");
                return -1;
            }
        }
    }
    public static void main(String[] args) {
        //test case for Animal Shelter
        animalShelter test=new animalShelter();
        test.enqueue("cats",11);
        test.enqueue("cats",22);
        test.enqueue("cats",33);
        test.enqueue("dogs",44);
        test.enqueue("dogs",55);
        test.enqueue("dogs",66);
        System.out.println(test.DequeueAny());
        System.out.println(test.DequeuCats());
        System.out.println(test.DequeuDogs());
        //test case for Sort Stack
//        sortStack test=new sortStack();
//        test.add(3);
//        test.add(5);
//        test.add(1);
//        System.out.println(test.pop());
//        System.out.println(test.pop());
//        System.out.println(test.pop());
        //test case for Queue via Stacks
//        QStack test=new QStack();
//        test.add(1);
//        test.add(2);
//        test.add(3);
//        test.add(4);
//        test.add(5);
//        System.out.println(test.remove());
        //test case for stack of PLates
//        StackPlates test=new StackPlates(3);
//        test.add(1);
//        test.add(2);
//        test.add(3);
//        test.add(4);
//        test.add(5);
//        test.add(6);
//        test.add(7);
//        test.add(8);
//        test.add(9);
//        System.out.println(test.pop());
//        System.out.println(test.pop());
//        System.out.println(test.popoAt(0));
        //test case for Min Stack
//        minStack abc=new minStack();
//        abc.push(3);
//        abc.push(1);
//        abc.push(2);
//        abc.push(5);
//        abc.push(0);
//        System.out.println(abc.getMin());
//        abc.pop();
//        System.out.println(abc.getMin());
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
