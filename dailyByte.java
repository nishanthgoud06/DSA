import java.sql.Array;
import java.util.*;

public class dailyByte {
    //here we are creating a new class to execte linked list
    static class Node{
        int val;
        Node next;
        public Node(int val){
            this.val=val;
            next=null;
        }
        public Node(int val,Node next){
            this.val=val;
            this.next=next;
        }
    }
    //now we are going to implement Merge LinkedList
    public Node MeLinkedList(Node n1, Node n2){
        Node result=new Node(0);
        Node head=result;
        while(n1!=null&&n2!=null){
            if(n1.val< n2.val){
                head.next=n1;
                n1=n1.next;
            }else{
                head.next=n2;
                n2=n2.next;
            }
        }
        head=head.next;
        if(n1!=null)
            head=n1.next;
        else
            head=n2.next;

        return result.next;
    }

    //This question is asked by Google. Given a string, reverse all of its characters and return the resulting string.
    //first i would like to execute the following problem using brute force
    public static void rev(String s){
        char[] a=s.toCharArray();
        int j=s.length()-1;
        for(int i=0;i<j;i++,j--){
            char temp=a[i];
            a[i]=a[j];
            a[j]=temp;
        }
        for(char i:a){
            System.out.print(i);
        }

    }
    //reversing individual letters in a string
    public static void in(String s){
        String[] str=s.split("\\s");
        String sbc="";
        for(String q:str){
            StringBuilder sb=new StringBuilder(q);
            sb.reverse();
            sbc+=sb.toString()+" ";
        }
        System.out.println(sbc);
    }
    //Valid palindrome or not
    public static boolean check(String s){
        //first we are going to use the functions of stringbulider
        StringBuilder sb=new StringBuilder(s);
        sb.reverse();
        String rev=sb.toString();
        if(s.equals(rev))
            return true;
        return false;
    }
    //Vaccum clearner Route
    public static boolean route(String s){
        char[] c=s.toCharArray();
        int cl=0,cr=0,cu=0,cd=0;
        for(int i=0;i<c.length;i++){
            if(c[i]=='l')
                cl++;
            else if(c[i]=='r')
                cr++;
            else if(c[i]=='u')
                cu++;
            else
                cd++;
        }
        if(cl-cr==0&&cu-cd==0)
            return true;
        return false;
    }
    //Correct Capitalization
    public static boolean correction(String s){
        String str=s;
        String sC=str.toUpperCase();
        String sL=str.toLowerCase();
        String cam=str.substring(0,1).toUpperCase()+s.substring(1);
        if(s.equals(sC)||s.equals(sL)||s.equals(cam))
            return true;
        return false;
    }
    //Binary String
    public static String Binary(String s1,String s2){
        StringBuilder sb=new StringBuilder();
       int i=s1.length()-1;
       int j=s2.length()-1;
       int carry=0;
       while(i>=0 ||j>=0){
           int sum=carry;
           if(i>=0)sum+=s1.charAt(i--)-'0';
           if(j>=0)sum+=s2.charAt(j--)-'0';
           sb.append(sum%2);
           carry=sum/2;
       }
       if(carry!=0)
           sb.append(carry);
       return sb.reverse().toString();
    }
//    Longest Common Prefix
    public static String Prefix(String[] arr){
        //the approach which we are going to implement is comparing word by word
        if(arr.length ==0)
            return "";
        if(arr.length==1)
            return arr[0];
        Arrays.sort(arr);
        int min=Math.min(arr[0].length(),arr[arr.length-1].length());
        int i=0;
        while(i<min&&arr[0].charAt(i)==arr[arr.length-1].charAt(i))
            i++;
        return arr[0].substring(0,i);
    }
    //the same program, but now we are iterating through the all string but comparing the character
    public static String Prefix1(String[] array){
        int min=findmin(array);
        String result="";
        char a;
        for(int i=0;i<min;i++){
             a=array[0].charAt(i);
             for(int j=1;j<array.length;j++){
                 if(a!=array[j].charAt(i))
                     return result;
             }
        result+=a;
        }
        return result;
    }
    public static int findmin(String[] array){
        int min=array[0].length();
        for(int i=1;i<array.length;i++){
            min=Math.min(min,array[i].length());
        }
        return min;
    }
    //finding the longest prefix using divide and conquer method
    public static String DaC(String[] array,int low,int high){
        if(low==high)
            return array[low];
        if(high>low){
            int mid=low+(high-low)/2;
            String str1=DaC(array,low,mid);
            String str2=DaC(array,mid+1,high);
            return (doit(str1,str2));
        }
        return null;
    }
    public static String doit(String s1,String s2){
        String result="";
        int a=s1.length();
        int b=s2.length();
        for(int i=0,j=0;i<=a-1&&j<=b-1;i++,j++){
            if(s1.charAt(i)!=s2.charAt(j))
                break;
            result+=s1.charAt(i);
        }
        return result;
    }
    //Remove a character from a string to make it a palindrome
    public static int palind(String str){
        int low=0,high=str.length()-1;
        while(low<high){
            if(str.charAt(low)==str.charAt(high)){
                low++;
                high--;
            }
            if(palcheck(str,low+1,high))
                return low;
            if(palcheck(str,low,high-1))
                return high;
            return -1;
        }
        return -2;
    }
    public static boolean palcheck(String str,int low,int high){
        while(low<high){
            if(str.charAt(low)!=str.charAt(high))
                return false;
        low++;
        high--;
        }
        return true;
    }
//    Two Sum
    public static boolean twoSum(int[] arr,int target){
        HashMap<Integer,Integer> hm=new HashMap<>();
        for(int i=0;i<arr.length;i++){
            if(hm.containsKey(target-arr[i]))
                return true;
            else
                hm.put(arr[i],i);
        }
        return false;
    }
    public static int jAS(String s1,String s2){
        HashSet<Character> hs=new HashSet<>();
        for(char c:s1.toCharArray())
            hs.add(c);
        int count=0;
        for(char c:s2.toCharArray()){
            if(hs.contains(c))
                count++;
        }
            return count;
    }
    //Valid Anagram
    //the idea that i could think to implement this function is i can sort all the characters in the String
    //other way is to add all the character in the hashset-this would hot work in all cases
    //we are now going to try using hashmap merge function
    public static boolean anagram(String s1,String s2){
        HashMap<Character,Integer> hashMap1=new HashMap<>();
        HashMap<Character,Integer> hashMap2=new HashMap<>();
        for(int i=0;i<s1.length();i++){
            hashMap1.merge(s1.charAt(i),1,Integer::sum);
            hashMap2.merge(s2.charAt(i),1,Integer::sum);
        }
        for(int i=0;i<s1.length();i++){
            if(hashMap1.get(s1.charAt(i))!=hashMap2.get(s2.charAt(i))){
                return false;
            }
        }
        return true;
    }
//    First Unique Character
//    public static int funique(String str){
//
//    }
    //this is same as the anagram
    static char[] characterCount=new char[256];
    public static void Funique(String s){
        for(int i=0;i<s.length();i++){
            characterCount[s.charAt(i)]++;
        }
        for(int i=0;i<256;i++){
            if(characterCount[i]==1){
                System.out.println(s.charAt(i-97));
                break;
            }
        }
    }
//    Spot the Difference
//i thought of doing like converting the trying to char Array as
// iterating and finding the char but the time complexity has become exponential
    public static char Spot(String s1,String s2){
        int count=(int)s2.charAt(s1.length());
        for(int i=0;i<s1.length();i++){
            count-=(int)s1.charAt(i);
            count+=(int)s2.charAt(i);
        }

        return (char)count;
    }
    //executing the same program using hashmap
    public static char Spot1(String s1,String s2){
        HashMap<Character,Integer> hm1=new HashMap<>();
        for(int i=0;i<s1.length();i++){
            char c=s1.charAt(i);
            int count=hm1.getOrDefault(c,0);
            hm1.put(c,count+1);
        }
        for(int i=0;i<s2.length();i++){
            char c=s2.charAt(i);
            if(hm1.containsKey(c)&&hm1.get(c)>0){
                 hm1.put(c,hm1.get(c)-1);
             }else{
                 return c;
             }
        }
        return '$';
    }
//uncommon words
public static String[] unCommonWord(String s1,String s2){
    Map<String,Integer> hashmap=new HashMap<>();
    for(String s:s1.split(" ")){
        hashmap.put(s,hashmap.getOrDefault(s,0)+1);
    }
    for(String s:s2.split(" ")){
        hashmap.put(s,hashmap.getOrDefault(s,0)+1);
    }
    List<String> result=new LinkedList<>();
    for(String s:hashmap.keySet()){
        if(hashmap.get(s)==1){
            result.add(s);
        }
    }
    return result.toArray(new String[result.size()]);
}

    public static void print(Node n){
        while(n!=null){
            System.out.println(n.val);
            n=n.next;
        }
    }
    public static int len(Node n){
        int count=0;
        while(n!=null){
            count++;
            n=n.next;
        }
        return count;
    }
    public static void removeFirst(Node n){
        n=n.next;
        print(n);
    }
    public static void removelast(Node n1){
        Node n=n1;
        while(n.next.next!=null){
            n=n.next;
        }
        n.next=null;
        print(n1);
    }
    public static void remove(Node n,int n1){
        Node node=n;
        Node prev=null;
        for(int i=0;i<n1-1;i++){
            prev=node;
            node=node.next;
        }
        prev.next=node.next;
        print(prev);
    }
    //Remove Nth to Last Node
    public static void remmoveN(Node n,int pos){
        Node c=new Node(0);
        c.next=n;
        Node fast=c;
        Node slow=c;
        for(int i=1;i<=pos+1;i++){
            fast=fast.next;
        }
        while(fast!=null){
            slow=slow.next;
            fast=fast.next;
        }
        slow.next=slow.next.next;
        print(n);
    }
//    Remove Value
    public static void removeValue(Node n,int val){
        while(n.next!=null&&n.val==val){
            n=n.next;
        }
        Node num=n;
        while(num!=null&&num.next!=null){
            if(num.next.val==val){
                num.next=num.next.next;
            }else{
                num=num.next;
            }
        }
        print(n);
    }
    //return the middle element
    public static void middleElement(Node n){
        Node slow=n;
        Node fast=n;
        while( fast!=null && fast.next!=null ){
            slow=slow.next;
            fast=fast.next.next;
        }
        System.out.println(slow.val);
    }
//    now we are going to check weather there is a cycle or not
    public static boolean check(Node n){
        Node slow=n;
        Node fast=n;
        while(fast!=null&&fast.next!=null){
            slow=slow.next;
            fast=fast.next.next;
            if(slow==fast){
                return true;
            }
        }
        return false;
    }
    //for this program we are going to return the index form which the cycle has started
    public static Node indexLL(Node n){
        Node fast=n;
        Node slow=n;
        while(fast!=null &&fast.next!=null){
            fast=fast.next.next;
            slow=slow.next;
            if(slow==fast){
                Node slow2=n;
                while(slow!=slow2){
                    slow=slow.next;
                    slow2=slow2.next;
                }
                return slow;
            }
        }
        return null;
    }
    //for this program we are going to reorder the list
    //sample input 1->2->3->4->5
    //sample output 1->5->2->4->3
    public static Node reverseOfNode(Node n){
        Node prev=null;
        Node current=n;
        while(current!=null){
            Node next=current.next;
            current.next=prev;
            prev=current;
            current=next;
        }
        return prev;
    }
    public static void mergeOfNode(Node first,Node second){
        while(first!=null){
            Node l1=first.next;
            Node l2=second.next;
            first.next=second;
            if(l1==null)
                break;
            second.next=l1;
            first=l1;
            second=l2;
        }
    }
    public static void reOrder(Node n){
        Node first=n;
        Node slow=n;
        Node fast=n;
        Node prev=null;
        while(fast!=null && fast.next!=null){
            prev=slow;
            slow=slow.next;
            fast=fast.next.next;
        }
        prev.next=null;
        Node list2=reverseOfNode(slow);
        mergeOfNode(first,list2);
    }
    //now we are going to create our own hashset
    //which will include the methods add,contains,remove
    //the hashset limitations are not mentioned as every one knows it
    class MyHashSet{
        static int arraylength=100;
        static int max_integer_value=1000000;
        static List<List<Integer>> Parent;
        public MyHashSet(){
            Parent=new ArrayList<>(arraylength);
            for(int i=0;i<arraylength;i++){
                Parent.add(null);
            }
        }
        public static void add(int x){
            int index=x%arraylength;
            List<Integer> child=Parent.get(index);
            if(child==null){
                List<Integer> list=new ArrayList<>();
                list.add(x);
                Parent.add(index,list);
            }else{
                if(!child.contains(x)){
                    child.add(x);
                }
            }
        }
        public static boolean contains(int x){
            int index=x%arraylength;
            List<Integer> child=Parent.get(index);
            return child.contains(x)&&child!=null;
        }
        public static void remove(int x){
            int index=x%arraylength;
            List<Integer> child=Parent.get(index);
            if(child.contains(x))
                child.remove(x);
        }
    }
    //validate the string which only has the paratensics/baracket
    public static boolean Validate(String s){
        Stack<Character> stack=new Stack<>();
        for(char c:s.toCharArray()){
            if(c=='(')
                stack.push(')');
            else if(c=='[')
                stack.push(']');
            else if(c=='{')
                stack.push('}');
            else if(stack.isEmpty()||stack.pop()==c){
                return false;
            }
        }
        return true;
    }
    //the below function is to add two linked list
    //we need to consider that the length of each list may or may not be equal
    public static Node adding(Node l1,Node l2){
        Node l3=new Node(0);
        Node dummy_head=l3;
        int carry=0;
        while(l1!=null&&l2!=null){
            int num1=(l1!=null)?l1.val:0;
            int num2=(l2!=null)?l2.val:0;
            int sum=num1+num2+0;
            carry=sum/10;
            int last=sum%10;
            Node num=new Node(last);
            dummy_head.next=num;
            dummy_head=dummy_head.next;
            if(l1!=null)
                l1=l1.next;
            if(l2!=null)
                l2=l2.next;
        }
        if(carry>0){
            Node n=new Node(carry);
            dummy_head.next=n;
            dummy_head=dummy_head.next;
        }
        return l3.next;
    }
//    here we are going to impliment the compare the keyStrokes
//    here we are gonna return the boolean value to see whether the given two strings are equals or not
    public static String Stacking(String s){
        Stack<Character> stack=new Stack();
        for(char c:s.toCharArray()){
            if(c=='#'){
                if(!stack.isEmpty()){
                    stack.pop();
                    continue;
                }
            }
            stack.push(c);
        }
        return stack.toString();
    }
    //now we are going to use the StringBuilder
    public static String StrBuilding(String s){
        StringBuilder str=new StringBuilder();
        for(char c:s.toCharArray()){
            if(c=='#'){
                if(!str.isEmpty()){
                    str.delete(str.length()-1,str.length());
                    continue;
                }
            }
            str.append(c);
        }
        return str.toString();
    }
    public static boolean Comparing(String s,String t){
        //for the first approach we are gonna use the stack data Structure
        return Stacking(s).equals(Stacking(t));
    }
    //for this program we have to remove adjacent charcters from the string
    //my first approach is using hashmap but by using that we are not geeting the sequence
    //now we are using stack approach
    public static void removeAdj(String s){
       StringBuilder str=new StringBuilder();
       for(char c:s.toCharArray()){
           int count=str.length();
           if(count>1&&str.charAt(count-1)==c){
               str.deleteCharAt(count-1);
           }else{
               str.append(c);
           }
       }
        System.out.println(str);
    }
    //now we are going to implement the same using two pointers
    public static void RemoAj(String s){
        int i=0;
        int length=s.length();
        char[] c=s.toCharArray();
        for(int j=0;j<length;j++,i++){
            c[i]=c[j];
            if(i>0&&c[i-1]==c[i]){
                i=i-2;
            }
        }
        System.out.println(new String(c,0,i));
    }
//    Next Greater Element I
    public static int[] neGr(int[] a,int[] b){
        HashMap<Integer,Integer> hm=new HashMap<>();
        Stack<Integer> stack=new Stack<>();
        for(int i:b){
            if(!stack.isEmpty()&&stack.peek()<i){
                hm.put(stack.pop(),i);
            }
            stack.push(i);
        }
        for(int i=0;i<a.length;i++){
            a[i]=hm.getOrDefault(i,-1);
        }
        return a;
    }
    //finding the permutations of a string
    public static String Swaping(String s,int i,int j){
        char[] c=s.toCharArray();
        char temp=c[i];
        c[i]=c[j];
        c[j]=temp;
        return String.valueOf(c);
    }
    public static void combination(String s,int i,int j){
        if(i==j){
            System.out.println(s);
        }
        else{
            for(int k=i;k<=j;k++){
                s=Swaping(s,i,k);
                combination(s,i+1,j);
            }
        }
    }
    public static boolean comp(String s,String q){
        return StrBuilding(s).equals(StrBuilding(q));
    }
    static class MovingAverage{
        int capacity;
        int sum;
        Queue<Double> c;
        public MovingAverage(int capacity){
            c=new LinkedList<>();
            this.capacity=capacity;
        }
        public void next(double val){
            sum+=val;
            c.add(val);
            if(c.size()>capacity){
                sum-=c.remove();
            }
            System.out.println(sum/c.size());
        }
    }
        static class classCounter{
        int limit;
        static Queue<Integer> coll;
        public classCounter(int limit){
            this.limit=limit;
        }
        public static void ping(int val){
            coll.add(val);
            while(coll.peek()<val-3000){
                coll.remove();
            }
            System.out.println(coll.size());
        }
        }
//        now we are to implement stack using queue
//            first we are going to use two queue
//            then we are going to use single queue
    static class sQueue{
        Queue<Integer> q1;
        Queue<Integer> q2;
        public sQueue(){
            q1=new LinkedList<>();
            q2=new LinkedList<>();
        }
        public void add(int val){
            q2.add(val);
            while(!q1.isEmpty()){
                q2.add(q1.peek());
                q1.remove();
            }
            Queue q=q2;
            q1=q2;
            q2=q;
        }
        public void remove(){
            if(!q1.isEmpty())
                q1.remove();
        }
}
    static class sQS{
        Queue<Integer> q;
        public sQS(){
            q=new LinkedList<>();
        }
        public void add(int val){
            int size=q.size();
            q.add(val);
            for(int i=0;i<size;i++){
                q.add(q.peek());
                q.remove();
            }
        }
        public int peek(){
            if(q.isEmpty())
                return -1;
            return q.peek();
        }
    }
    //find value in BST
    static class BST{
        int val;
      BST left;
      BST right;
      BST() {}
      BST(int val) { this.val = val; }
      BST(int val, BST left, BST right) {
          this.val = val;
          this.left = left;
          this.right = right;
      }
    }
//for this program we are going to Convert Binary Search Tree to Sorted Linked List
    public static void conBtoLl(BST node,List<Integer> results){
        if(node ==null){
            return;
        }
        conBtoLl(node.left,results);
        results.add(node.val);
        conBtoLl(node.right,results);
    }
//    Lowest Common Ancestor for this program we will give to values we need to find the least common ancestor of given two nodes
    public static BST leastC(BST node,int n1,int n2){
        if(node ==null)
            return null;
        if(node.val==n1 || node.val==n2)
            return node;
        BST leftside=leastC(node.left,n1,n2);
        BST rightside=leastC(node.right,n1,n2);
        if(leftside!=null&&rightside!=null)
            return node;
        else if(leftside!=null)
            return leftside;
        else
            return rightside;
    }
    //for this program we are going to check whether the given two trees are same or not;
    //one way is to use the recursive version where you pass the same loaction each time;
    //other way is the iterative version where we have used the queue
    public static boolean checkSimilarR(BST n1,BST n2){
        if(n1==null && n2==null){
            return true;
        }else if(n1==null ||n2==null)
            return false;
        if(n1.val==n2.val)
           return checkSimilarR(n1.left,n2.left)&&checkSimilarR(n1.right,n2.right);
        return false;
    }
    public static boolean checkSimilarI(BST n1,BST n2){
        Queue<BST> queue=new LinkedList<>();
        if(n1==null &&n2==null)
            return true;
        else if(n1==null ||n2==null)
            return false;
        if(n1!=null && n2!=null){
            queue.offer(n1);
            queue.offer(n2);
        }
        while(!queue.isEmpty()){
            BST a=queue.poll();
            BST b=queue.poll();
            if(a==null&&b==null)
                continue;
            if(a==null || b==null)
                return false;
            if(a.val!=b.val)
                return false;
            queue.offer(a.left);
            queue.offer(b.left);
            queue.offer(a.right);
            queue.offer(b.right);
        }
        return true;
    }
    //Given a binary search tree, return the minimum difference between any two nodes in the tree.
    static Integer prev=null;
    static int min=Integer.MAX_VALUE;
    public static int minDiff(BST node){
        if(node==null)
            return min;
        minDiff(node.left);
        if(prev!=null){
            min=Math.min(node.val-prev,min);
        }
        prev= node.val;
        minDiff(node.right);
        return min;
    }
    //Given a binary search tree, return its mode (you may assume the answer is unique). If the tree is empty,
    // return -1. Note: the mode is the most frequently occurring value in the tree.
    //for the first approach we are going to use the hashmap the time complexity is O(n) and the space complexity is O(n)
    public static void check(BST node,HashMap<Integer,Integer> hm,int[] max){
        if(node == null)
            return;
        check(node.left,hm,max);
        hm.put(node.val,hm.getOrDefault(node.val,0)+1);
        max[0]=Math.max(max[0],hm.get(node.val));
        check(node.right,hm,max);
    }
    public static List<Integer> modeH(BST node){
        int[] a=new int[1];
        HashMap<Integer,Integer> hm=new HashMap<>();
        check(node,hm,a);
        List<Integer> result=new ArrayList<>();
        for(Map.Entry<Integer,Integer> check:hm.entrySet()){
            if(a[0]==check.getValue()){
                result.add(check.getKey());
            }
        }
        return result;
    }
    //now we are going to the approach where the memory is linear
    Integer prev1=null;
    static int max=1;
    static int count=0;
    public static void implement(BST node,List<Integer> coll){
        if(node ==null)
            return;
        implement(node.left,coll);
        if(prev!=null){
            if(prev==node.val){
                count++;
            }else{
                count=1;
            }
        }
        if(count>max){
            max=count;
            coll.clear();
            coll.add(node.val);
        }else if(count==max){
            coll.add(node.val);
        }
        prev=node.val;
        implement(node.right,coll);
    }
    public static List<Integer> modeL(BST node){
        List<Integer> coll=new ArrayList<>();
        implement(node,coll);
        return coll;
    }

    //Max Value in Each Level
    public static List<Integer> maxValue(BST node){
        //now we are going to use bfs
        Queue<BST>  q=new LinkedList<>();
        List<Integer> result=new ArrayList<>();
        q.offer(node);
        while(!q.isEmpty()){
            int max=Integer.MIN_VALUE;
            int size=q.size();
            for(int i=0;i<size;i++){
                BST temp=q.poll();
                max=Math.max(max,temp.val);
                if(temp.left!=null)
                    q.offer(temp.left);
                if(temp.right!=null)
                    q.offer(temp.right);
            }
            result.add(max);
        }
        return result;
    }
    //Visible Values
    //for the first approach we are going to use BST
    public static List<Integer> printVisible(BST node){
        List<Integer> result=new ArrayList<>();
        if(node==null)
            return result;
        Queue<BST> q=new LinkedList<>();
        q.offer(node);
        while(!q.isEmpty()){
            int size=q.size();
            for(int i=0;i<size;i++) {
                BST temp = q.poll();
                if (i == 0)
                    result.add(temp.val);
                if (temp.left != null)
                    q.add(temp.left);
                if (temp.right != null)
                    q.add(temp.right);
            }
        }
            return result;
    }
    //now we are going to use the recursion method which can also be called as depth first search
    static int maxi=0;
    public static void p(BST n,int level){
        if(n==null)
            return;
        if(maxi<level){
            System.out.print(n.val+" ");
            maxi=level;
        }
        p(n.left,level+1);
        p(n.right,level+1);
    }
    public static void pr(BST n){
        maxi=0;
        p(n,1);
    }
    //bottoms up
    //naive approach
    //the approach we are going to use is dfs
    public static void doingBottomup(List<List<Integer>> result,BST node,int level){
        if(node==null)
            return;
        if(level>=result.size())
            result.add(0,new ArrayList<>());
        doingBottomup(result,node.left,level+1);
        doingBottomup(result,node.right,level+1);
        result.get(result.size()-level-1).add(node.val);
    }
    public static List<List<Integer>> bottomUp(BST node){
        List<List<Integer>> result=new ArrayList<>();
        doingBottomup(result,node,0);
        return result;
    }
    //now we are goinf to do the zigzag traversal
    public static void zigZag(BST n,List<List<Integer>> result,int level){
        if(n == null)
            return;
        Queue<BST> q=new LinkedList<>();
        q.offer(n);
        while(!q.isEmpty()){
            int size=q.size();
            List<Integer> temp=new ArrayList<>();
            for(int i=0;i<size;i++){
                BST ni=q.poll();
                if(level%2==0){
                    temp.add(ni.val);
                }else{
                    temp.add(0,ni.val);
                }
                if(ni.left!=null){
                    q.offer(ni.left);
                }
                if(ni.right!=null){
                    q.offer(ni.right);
                }
            }
            result.add(temp);
            level++;
        }
    }
    //Gather N-ary Tree Levels
    //Given an n-ary tree, return its level order traversal.
    //Note: an n-ary tree is a tree in which each node has no more than N children.
    static class treeNode{
        int val;
        List<treeNode> child;
        public treeNode(int val){
            this.val=val;
            child=new ArrayList<>();
        }
    }
    //we are going to perform bst for finding the node at each level
    public static List<List<Integer>> levelOrder(treeNode root) {
        List<List<Integer>> result=new ArrayList<>();
        if(root==null)
            return result;
        Queue<treeNode> queue=new LinkedList<>();
        queue.offer(root);
        while(!queue.isEmpty()){
            int size=queue.size();
            List<Integer> a=new ArrayList<>();
            for(int i=0;i<size;i++){
                treeNode temp=queue.poll();
                a.add(temp.val);
                for(treeNode ab:temp.child){
                    queue.add(ab);
                }
            }
            result.add(a);
        }
        return result;
    }
    //we need to print all possible way a root can reach to the leaf
    public static List<String> pri(BST node){
        List<String> result=new ArrayList<>();
        if(node==null)
            return result;
        dfs(node,"",result);
        return result;
    }
    public static void dfs(BST node,String s,List<String> temp){
        s+=node.val;
        if(node.left==null&&node.right==null)
            temp.add(s);
        if(node.left!=null)
            dfs(node.left,s+"->",temp);
        if(node.right!=null)
            dfs(node.right,s+"->",temp);
    }
    //another way to implement the above problem
    public static List<String> chain(BST node){
        List<String> result=new ArrayList<>();
        if(node ==null)
            return result;
        String temp=Integer.toString(node.val);
        if(node.left==null&&node.right==null)
            result.add(temp);
        if(node.left!=null)
            dfsing(node.left,temp,result);
        if(node.right!=null)
            dfsing(node.right,temp,result);
        return result;
    }
    public static void dfsing(BST node,String temp,List<String> result){
        temp+="->"+node.val;
        if(node.left==null&&node.right==null)
            result.add(temp);
        if(node.left!=null)
            dfsing(node.left,temp,result);
        if(node.right!=null)
            dfsing(node.right,temp,result);
    }
    //Root to Leaf Path Sum
    //Given a binary tree and a target, return whether or not there exists a root to leaf path
    // such that all values along the path sum to the target.
    public static String LeafPath(BST n,int target){
        if(n==null)
            return null;
        HashMap<Integer,String>hm=new HashMap<>();
        findingPath(n,"",0,hm);
        if(hm.containsKey(target))
            return hm.get(target);
        else
            return "can't find";
    }
    static void findingPath(BST n,String str,int value,HashMap<Integer,String> hm){
        value+=n.val;
        str+=n.val;
        if(n.left==null&&n.right==null)
            hm.put(value,str);
        if(n.left!=null)
            findingPath(n.left,str+"->",value,hm);
        if(n.right!=null)
            findingPath(n.right,str+"->",value,hm);

    }
    //checking whether a tree is valid binary tree or not
    public static boolean isValid(BST n){

        return ihelper(n,null,null);
    }
    public static boolean ihelper(BST node,Integer min,Integer max){
        if(node==null)
            return true;
        if(min!=null&&node.val<min||max!=null&&node.val>max)
            return false;
        return ihelper(node.left,0,node.val)||ihelper(node.right,node.val,0);
    }
    //construct a binary tree from preorder traversal array list
    static int count_of_the_list=0;
    public static BST constrcutTree(int[] arr){
        return doingtree(arr,Integer.MAX_VALUE);
    }
    public static BST doingtree(int[] arr,Integer max){
        if(count_of_the_list==arr.length||arr[count_of_the_list]>max)
            return null;
        BST node=new BST(arr[count_of_the_list++]);
        node.left=doingtree(arr,node.val);
        node.right=doingtree(arr,max);
        return node;
    }
    //Symmetrical Tree
    //Given a binary tree, return whether or not it forms a reflection across its center (i.e. a line drawn straight down starting from the root).
    //Note: a reflection is when an image, flipped across a specified line, forms the same image.
    public static boolean isSymmetrical(BST node){
        return node==null||checkingIS(node.left,node.right);
    }
    public static boolean checkingIS(BST node1,BST node2){
        if(node1==null||node2==null)
            return node1==node2;
        if(node1.val!=node2.val)
            return false;
        return checkingIS(node1.left,node2.right) &&checkingIS(node1.right,node2.left);
    }
    //for the second approach we are going to use the stack
    public static boolean checkSYS(BST node){
        if(node ==null)
            return true;
        Stack<BST> stack=new Stack<>();
        if(node.left!=null){
            if(node.right==null)
                return false;
            stack.push(node.left);
            stack.push(node.right);
        }else if(node.right!=null)
            return false;
        while(!stack.isEmpty()){
            if(stack.size()%2!=0)
                return false;
            BST right=stack.pop();
            BST left=stack.pop();
            if(left.val!=right.val)
                return false;
            if(left.left!=null){
                if(right.right==null)
                    return false;
                stack.push(left.left);
                stack.push(right.right);
            }else if(right.right!=null){
                return false;
            }
            if(left.right!=null){
                if(right.left==null)
                    return false;
                stack.push(left.right);
                stack.push(right.left);
            }else if(right.left!=null){
                return false;
            }
        }
        return true;
    }
    //Same Leaves
    //Given two binary trees, return whether or not both trees have the same leaf sequence.
    // Two trees have the same leaf sequence if both trees’ leaves read the same from left to right.
    public static List<Integer> doit(BST node,List<Integer> result){
        if(node==null)
            return result;
        if(node.left==null && node.right==null)
            result.add(node.val);
        doit(node.left,result);
        doit(node.right,result);
        return result;
    }
    public static boolean checkLeaves(BST root1,BST root2){
        if(root1==null&&root2==null)
            return true;
        List<Integer> result1=doit(root1,new ArrayList<>());
        List<Integer> result2=doit(root2,new ArrayList<>());
        return result1.equals(result2);
    }
    //for this program we are going to solve sum of left leaves of the bst
    //the first approach is going to be a dfs using recursion
    public static int sumOfLL(BST node){
        return sumofll(node,false);
    }
    public static int sumofll(BST node,boolean status){
        if(node == null)
            return 0;
        if(node.left==null&&node.right==null&&status==true){
            return node.val;
        }
        return sumofll(node.left,true)+sumofll(node.right,false);
    }
    //the second method is also using dfs with recursion
    public static int LLsum(BST node){
        int result=0;
        if(node==null)
            return result;
        Stack<BST> stack=new Stack<>();
        stack.push(node);
        while(!stack.isEmpty()){
            BST temp=stack.pop();
            if(temp.left!=null){
                if(temp.left.left==null&&temp.left.right==null){
                    result+=temp.left.val;
                }else{
                    stack.push(temp.left);
                }
            }
            if(temp.right!=null){
                if(temp.right.left!=null||temp.right.right!=null){
                    stack.push(temp.right);
                }
            }
        }
        return result;
    }
    //String Permutations
    // a string s consisting of only letters and digits, where we are allowed to transform any
    // letter to uppercase or lowercase, return a list containing all possible permutations of the string.
    public static boolean isprem(String s1,String s2){
        if(s1.length()==0)
            return true;
        if(s2.length()==0||s1.length()>s2.length())
            return false;
        int[] a=new int[26];
        int[] b=new int[26];
        int limit1=s1.length();
        int limit2=s2.length();
        for(int i=0;i<limit1;i++){
            a[s1.charAt(i)-'a']++;
            b[s2.charAt(i)-'a']++;
        }
        if(Arrays.equals(a,b))
            return true;
        for(int i=limit1;i<limit2;i++){
            b[s2.charAt(i-limit1)-'a']--;
            b[s2.charAt(i)-'a']++;
            if(Arrays.equals(a,b))
                return true;
        }
        return false;
    }
    public static boolean prechecko(String s1,String s2){
        if(s1.length()==0)
            return true;
        if(s2.length()==0||s1.length() >s2.length())
            return false;
        int[] array=new int[26];
        int limit1=s1.length();
        int limit2=s2.length();
        for(int i=0;i<s1.length();i++){
            array[s1.charAt(i)-'a']++;
            array[s2.charAt(i)-'a']--;
        }
        if(allZero(array))
            return true;
        for(int i=limit1;i<limit2;i++){
            array[s2.charAt(i)-'a']--;
            array[s2.charAt(i-limit1)-'a']++;
            if(allZero(array))
                return true;
        }
        return false;
    }
    public static boolean allZero(int[] array){
        for(int i=0;i<array.length;i++){
            if(array[i]!=0)
                return false;
        }
        return true;
    }
    //784. Letter Case Permutation
    //Given a string s, you can transform every letter individually to be lowercase or uppercase
    //to create another string.
    //Return a list of all possible strings we could create. Return the output in any order.
    //the first approach is using bfs
    public static List<String> letterCasePermutation(String s) {
        if(s.length()==0)
            return new LinkedList<>();
        Queue<String> queue=new LinkedList<>();
        queue.offer(s);
        for(int i=0;i<s.length();i++){
            int size=queue.size();
            if(Character.isDigit(s.charAt(i)))
                continue;
            for(int j=0;j<size;j++){
                String str=queue.poll();
                char[] c=str.toCharArray();
                c[i]=Character.toUpperCase(c[i]);
                queue.offer(String.valueOf(c));
                c[i]=Character.toLowerCase(c[i]);
                queue.offer(String.valueOf(c));
            }
        }
        return new LinkedList<>(queue);
    }
    //the second approach is using dfs
    public static List<String> dfsLettercase(String s){
        if(s.length()==0)
            return new LinkedList<>();
        List<String> str=new LinkedList<>();
        dfsLetterCaseHelper(s.toCharArray(),str,0);
        return str;
    }
    public static void dfsLetterCaseHelper(char[] c,List<String> str,int pos){
        if(pos==c.length){
            str.add(new String(c));
            return ;
        }
        if(c[pos]>='0'&&c[pos]<='9'){
            dfsLetterCaseHelper(c,str,pos+1);
            return;
        }
        c[pos]=Character.toLowerCase(c[pos]);
        dfsLetterCaseHelper(c,str,pos+1);
        c[pos]=Character.toUpperCase(c[pos]);
        dfsLetterCaseHelper(c,str,pos+1);
    }
    //Generate Text Messages
    //This question is asked by Google. Given a string of digits, return all possible text messages
    //those digits could send.
    public static List<String> letterCombinations(String digits) {
        List<String> result=new LinkedList<>();
        if(digits.length()==0||digits==null)
            return result;
        String[] ans=new String[]{
                "0",
                "1",
                "abc",
                "def",
                "ghi",
                "jkl",
                "mno",
                "pqrs",
                "tuv",
                "wxyz"
        };
        helperforcall(digits,result,"",0,ans);
        return result;
    }
    public static void helperforcall(String digits,List<String> result,String current,int index,String[] ans){
        if(index==digits.length()){
            result.add(current);
            return;
        }
        String s=ans[digits.charAt(index)-'0'];
        for(int i=0;i<s.length();i++){
            helperforcall(digits,result,current+s.charAt(i),index+1,ans);
        }
    }
    //Word Search
//    Given a 2D board that represents a word search puzzle and a string word,
//    return whether or the given word can be formed in the puzzle by only connecting cells
//    horizontally and vertically.
    public static boolean exist(char[][] board, String word) {
        if(word.length()==0||word==null)
            return true;
        for(int i=0;i<board.length;i++){
            for(int j=0;j<board[0].length;j++){
                if(board[i][j]==word.charAt(0)&&existHelper(board,word,i,j,0))
                    return true;
            }
        }
        return false;
    }
    public static boolean existHelper(char[][] board,String word,int row,int col,int count){
        if(count==word.length())
            return true;
        if(row<0||row>=board.length||col<0||col>=board[0].length||board[row][col]!=word.charAt(count))
            return false;
        char temp=board[row][col];
        board[row][col]=' ';
        boolean result=existHelper(board,word,row-1,col,count+1)||
                existHelper(board,word,row+1,col,count+1)||
                existHelper(board,word,row,col-1,count+1)||
                existHelper(board,word,row,col+1,count+1);
        board[row][col]=temp;
        return result;
    }
    public static int goldRush(int[][] grid){
        if(grid.length==0||grid==null)
            return 0;
        int[] max=new int[1];
        for(int i=0;i<grid.length;i++){
            for(int j=0;j<grid[0].length;j++){
                goldRushHelper(grid,max,i,j,0);
            }
        }
        return max[0];
    }
    public static void goldRushHelper(int[][] grid,int[] max,int i,int j,int current){
        if(i<0||i>=grid.length||j<0||j>=grid[0].length||grid[i][j]==0){
            max[0]=Math.max(max[0],current);
            return;
        }
        int temp=grid[i][j];
        grid[i][j]=0;
        goldRushHelper(grid,max,i-1,j,current+temp);
        goldRushHelper(grid,max,i+1,j,current+temp);
        goldRushHelper(grid,max,i,j-1,current+temp);
        goldRushHelper(grid,max,i,j+1,current+temp);
        grid[i][j]=temp;
    }
    //combination sum
    public static List<List<Integer>> combini(int[] combi,int target){
        List<List<Integer>> result=new ArrayList<>();
        combiniHelper(combi,target,new ArrayList<>(),0,result);
        return result;
    }
    public static void combiniHelper(int[] com,int target,List<Integer> current,int index,List<List<Integer>> result){
        if(target<0)
            return;
        if(target==0)
            result.add(new ArrayList<>(current));
        for(int i=index;i<com.length;i++){
            current.add(com[i]);
            combiniHelper(com,target-com[i],current,i,result);
            current.remove(current.size()-1);
        }
    }
//    Unique Combinations
//Given a list of positive numbers without duplicates and a target number,
// find all unique combinations of the numbers that sum to the target.
    public static List<String> uniqueCom(int limit){
        List<String> result=new ArrayList<>();
        if(limit==0)
            return result;
        uniqueComHelper(limit,result,0,0,"");
        return result;
    }
    public static void uniqueComHelper(int limit,List<String> result,int min,int max,String current){
        if(current.length()==limit*2){
            result.add(new String(current));
            return;
        }
        if(min<limit)
            uniqueComHelper(limit,result,min+1,max,current+"(");
        if(max<min)
            uniqueComHelper(limit,result,min,max+1,current+")");
    }
    //131. Palindrome Partitioning
    //Given a string s, partition s such that every substring of the partition is a palindrome.
    // Return all possible palindrome partitioning of s.
    public static boolean isPali(String s,int low,int high){
        while(low<high){
            if(s.charAt(low++)!=s.charAt(high--))
                return false;
        }
        return true;
    }
    public static List<List<String>> palidrome(String s){
        List<List<String>> result=new ArrayList<>();
        if(s.length()==0||s==null)
            return result;
        palindromeHelper(result,new ArrayList<>(),0,s);
        return  result;
    }
    public static void palindromeHelper(List<List<String>> result,List<String> temp,int index,String target){
        if(index==target.length()) {
            result.add(new ArrayList<>(temp));
            return;
        }
        for(int i=index;i<target.length();i++){
            if(isPali(target,index,i)){
                temp.add(target.substring(index,i+1));
                palindromeHelper(result,temp,i+1,target);
                temp.remove(temp.size()-1);
            }
        }
    }
    //Max Points
    public static int maxpoints(int[] col,int energy){
        int i=0;
        int j=col.length-1;
        int points=0;
        int maxpoints=0;
        Arrays.sort(col);
        while(i<=j){
            if(energy>=col[i]){
                energy-=col[i++];
                points++;
                maxpoints=Math.max(points,maxpoints);
            }else if(points>0){
                energy+=col[j--];
                points--;
            }else{
                return maxpoints;
            }
        }
        return maxpoints;
    }
//    You are given a group of stones, all of which have a positive weight.
//    At each turn, we select the heaviest two stones and smash them together.
//    When smashing these two stones together, one of two things can happen:
    public static int stones(int[] arr){
        if(arr.length==1)
            return arr[0];
        if(arr.length==0)
            return 0;
        Arrays.sort(arr);
        int i=0,j=arr.length-1,differ=0;
        while(j>i){
            arr[j-1]=arr[j]-arr[j-1];
            Arrays.sort(arr);
            j=j-1;
        }
        return arr[0];
    }
    //using priority queue
    public static int syonespri(int[] a){
        if(a.length==1)
            return a[0];
        if(a.length==0)
            return 0;
        PriorityQueue<Integer> pq=new PriorityQueue<>(Collections.reverseOrder());
        for(int i:a){
            pq.add(i);
        }
        while(pq.size()!=1){
            int p=pq.poll();
            int q=pq.poll();
            pq.add(p-q);
        }
        return pq.peek();
    }
//    860. Lemonade Change
//At a lemonade stand, each lemonade costs $5. Customers are standing in a queue to buy from you and order one at
// a time (in the order specified by bills). Each customer will only buy one lemonade and pay with either a $5, $10,
// or $20 bill. You must provide the correct change to each customer so that the net transaction is that the customer
// pays $5.
    public static boolean lemChange(int[] lem){
        int five=0,ten=0;
        for(int i:lem){
            if(i==5)
                five++;
            else if(i==10){
                five--;
                ten++;
            }else if(ten>0){
                ten--;
                five--;
            }else{
                five-=3;
            }
            if(five<0){
                return false;
            }
        }
        return true;
    }
//Lunchtime
public static int balancedMeals(String items) {
    // Initialize a counter for the number of balanced meals
    int count = 0;

    // Initialize variables to keep track of the number of F's and D's seen so far
    int fCount = 0;
    int dCount = 0;

    // Iterate through the items
    for (int i = 0; i < items.length(); i++) {
        // Update the count of F's and D's seen so far
        if (items.charAt(i) == 'F') {
            fCount++;
        } else {
            dCount++;
        }

        // If we have seen an equal number of F's and D's, increment the count of balanced meals
        if (fCount == dCount) {
            count++;
        }
    }

    // Return the count of balanced meals
    return count;
}
//1029. Two City Scheduling
//A company is planning to interview 2n people. Given the array costs where costs[i] = [aCosti, bCosti],
// the cost of flying the ith person to city a is aCosti, and the cost of flying the ith person to city b is bCosti.
//    Return the minimum cost to fly every person to a city such that exactly n people arrive in each city.
public static int twoCitySchedCost(int[][] costs) {
    int cost = 0, j = 0;
    int total = 0;
    int[] value = new int[costs.length];
    for (int[] i : costs) {
        total += i[0];
        value[j++] = i[1] - i[0];
    }
    Arrays.sort(value);
    for (int i = 0; i < costs.length / 2; i++) {
        total += value[i];
    }
    return total;
}
//
//approach 1
// public int minCostClimbingStair(int[] cost) {
//     for(int i=2;i<cost.length;i++){
//         cost[i]+=Math.min(cost[i-1],cost[i-2]);
//     }
//     return Math.min(cost[cost.length-1],cost[cost.length-2]);
// }
//approch 2
public int minCostClimbingStairs(int[] cost){
    int step1=0;
    int step2=0;
    for(int i=cost.length-1;i>=0;i--){
        int c=cost[i]+Math.min(step1,step2);
        step1=step2;
        step2=c;
    }
    return Math.min(step1,step2);
}
//322. Coin Change
    //Dynamic Programming approach
    public static  int coinChange(int coins[],int amount){
        int[] change=new int[amount+1];
        Arrays.fill(change,Integer.MAX_VALUE);
        change[0]=0;
        for(int i=1;i<=amount;i++){
            for(int j=0;j< coins.length;j++){
                if(coins[j]<=i){
                    change[i]=Math.min(change[i],1 + change[i-coins[j]]);
                }
            }
        }
        return change[amount]<Integer.MAX_VALUE?change[amount]:-1;
    }
    //recursive approach
    public static int coinChangeR(int[] coins,int amount){
        if(amount<0)
            return 0;
        return coinChangeRHelper(coins,amount,new int[amount]);
    }
    public static int coinChangeRHelper(int[] coins,int amount,int[] count){
        if(amount<0)
            return -1;
        if(amount==0)
            return 0;
        if(count[amount-1]!=0)
            return count[amount-1];
        int min=Integer.MAX_VALUE;
        for(int i:coins){
            int res=coinChangeRHelper(coins,amount-i,count);
            if(res>=0 && res< min)
                min=1+res;
        }
        count[amount-1] = (min==Integer.MAX_VALUE) ? -1 : min;
        return count[amount-1];
    }
//    91. Decode Ways
    public static int Decode(String s){
        if(s.length()==0||s==null)
            return 0;
        if(s.length()==1)
            return 1;
        int[] dp=new int[s.length()+1];
        dp[0]=1;
        dp[1]=s.charAt(0)=='0'?0:1;
        for(int i=2;i<=s.length();i++){
            int one=Integer.valueOf(s.substring(i-1,i));
            int two=Integer.valueOf(s.substring(i-2,i));
            if(one>=1){
                dp[i]+=dp[i-1];
            }
            if(two>=10&&two<=26){
                dp[i]+=dp[i-2];
            }
        }
        return dp[s.length()];
    }
//    62. Unique Paths
    public static int uniquePath(int m,int n){
        int[][] grid=new int[m][n];
        for(int i=0;i<m;i++){
            grid[i][0]=1;
        }
        for(int j=0;j<n;j++){
            grid[0][j]=1;
        }
        for(int i=1;i<m;i++){
            for(int j=1;j<n;j++){
                grid[i][j]=grid[i][j-1]+grid[i-1][j];
            }
        }
        return grid[m-1][n-1];
    }
//    403. Frog Jump
public static boolean canCross(int[] stones) {
    for(int i = 3; i < stones.length; i++) {
        if(stones[i] > stones[i - 1] * 2) {
            return false;
        }
    }

    Set<Integer> stoneSet = new HashSet<>();
    Set<List<Integer>> visited = new HashSet<>();
    for(int stone: stones) {
        stoneSet.add(stone);
    }

    int lastPosition = stones[stones.length - 1];
    Stack<Integer> positions = new Stack<>();
    Stack<Integer> jumps = new Stack<>();
    positions.add(0);
    jumps.add(0);

    while(!positions.isEmpty()) {
        int pos = positions.pop();
        int dis = jumps.pop();

        for(int i = dis - 1; i <= dis + 1; i++) {
            if(i <= 0) {
                continue;
            }

            int nextPos = pos + i;
            if(nextPos == lastPosition) {
                return true;
            }

            List<Integer> target = new ArrayList<>();
            target.add(nextPos);
            target.add(i);

            if(stoneSet.contains(nextPos) && !visited.contains(target)) {
                positions.add(nextPos);
                jumps.add(i);
                visited.add(target);
            }
        }
    }

    return false;
}
//1143. Longest Common Subsequence
    //the first approach is brutefroce which will not work for long length string
    public static int longCSBF(String s1,String s2){
        return longCSBFHelper(s1,s2,0,0);
    }
    public static int longCSBFHelper(String s1,String s2,int len1,int len2){
        if(len1==s1.length()||len2==s2.length())
            return 0;
        if(s1.charAt(len1)==s2.charAt(len2))
            return 1+longCSBFHelper(s1,s2,len1+1,len2+1);
        else
            return Math.max(longCSBFHelper(s1,s2,len1+1,len2),longCSBFHelper(s1,s2,len1,len2+1));
    }
    //approach 2
    //we use dynamic programming approach
    private static Integer[][] dp;
    public static int longCS(String s1,String s2){
        dp=new Integer[s1.length()][s2.length()];
        return longCSHelper(s1,s2,0,0);
    }
    public static int longCSHelper(String s1,String s2,int i,int j){
        if(i==s1.length()||j==s2.length())
            return 0;
        if(dp[i][j]!=null)
            return dp[i][j];
        if(s1.charAt(i)==s2.charAt(j))
            return dp[i][j]=1+longCSHelper(s1,s2,i+1,j+1);
        else
            return dp[i][j]=Math.max(longCSHelper(s1,s2,i+1,j),longCSHelper(s1,s2,i,j+1));
    }
    //thrid approach is bottom up dynamic programming
    public static int longestCommonSubsequence(String text1, String text2){
        int[][] dp=new int[text1.length()+1][text2.length()+1];
        for(int i=1;i<=text1.length();i++){
            for(int j=1;j<=text2.length();j++){
                if(text1.charAt(i-1)==text2.charAt(j-1)){
                    dp[i][j]=1+dp[i-1][j-1];
                }else{
                    dp[i][j]=Math.max(dp[i-1][j],dp[i][j-1]);
                }
            }
        }
        System.out.println(dp[1][1]);
        return dp[text1.length()][text2.length()];
    }
//    53. Maximum Subarray
    public static int maxSub(int[] arr){
        int[] dp=new int[arr.length];
        dp[0]=arr[0];
        int max=arr[0];
        for(int i=1;i<arr.length;i++){
            dp[i]=arr[i]+(dp[i-1]>=0?dp[i-1]:0);
            max=Math.max(dp[i],max);
        }
        return max;
    }
    //72. Edit Distance
    public static int minDistance(String word1, String word2) {
        int[][] dp = new int[word2.length()+1][word1.length()+1];
        for(int i = 0; i <= word2.length(); i++) {
            for(int j = 0; j <= word1.length(); j++) {
                if(i == 0 && j == 0) dp[i][j] = 0;
                else if(i == 0 && j != 0) {
                    dp[i][j] = j;
                } else if(i != 0 && j == 0) {
                    dp[i][j] = i;
                } else if(word2.charAt(i-1) != word1.charAt(j-1)) {
                    dp[i][j] = Math.min(dp[i][j-1], Math.min(dp[i-1][j], dp[i-1][j-1])) + 1;
                }else {
                    dp[i][j] = dp[i-1][j-1];
                }
            }
        }
        return dp[word2.length()][word1.length()];
    }
//    139. Word Break
public static boolean wordBreak(String s, List<String> dict) {
    boolean[] f = new boolean[s.length() + 1];
    f[0] = true;
    for(int i=1; i <= s.length(); i++){
        for(int j=0; j < i; j++){
            if(f[j] && dict.contains(s.substring(j, i))){
                f[i] = true;
                break;
            }
        }
    }

    return f[s.length()];
}
//    Longest Increasing Subsequence
    public static int longSub(int[] arr){
        if(arr.length==1)
            return 1;
        if(arr.length==0||arr==null)
            return 0;
        int m=0;
        int[] result=new int[arr.length];
        for(int i=0;i<arr.length;i++){
            result[i]=1;
            for(int j=0;j<i;j++){
                if(arr[i]>arr[j]){
                    int max=Math.max(result[j]+1,result[i]);
                    result[i]=max;
                }
            }
            m=Math.max(m,result[i]);
        }
        return m;
    }
    //painting houses
    public static int paintingHouses(int[][] houses){
        int result=0;
        int house=-1;
        for(int i=0;i<houses.length;i++){
            int min=Integer.MAX_VALUE;
            for(int j=0;j<houses[0].length;j++){
                if(min>houses[i][j]&&house!=j){
                    house=j;
                    min=houses[i][j];
                }
            }
            result+=min;
        }
        return result;
    }
    //another approach
    public static int paintingHouse2(int[][] house){
        if(house.length==0||house==null)
            return 0;
        for(int i=1;i<house.length;i++){
            house[i][0]+=Math.min(house[i-1][1],house[i-1][2]);
            house[i][1]+=Math.min(house[i-1][0],house[i-1][2]);
            house[i][2]+=Math.min(house[i-1][0],house[i-1][1]);
        }
        return Math.min(Math.min(house[house.length-1][0],house[house.length-1][1]),house[house.length-1][2]);
    }
    //Stairs
    public static int stairs(int steps){
        if(steps==0)
            return 0;
        if(steps==1)
            return 1;
        if(steps==2)
            return 2;
        int one=1;
        int two=2;
        int result=0;
        for(int i=2;i<steps;i++){
            result=one+two;
            one=two;
            two=result;
        }
        return result;
    }
//    Art Gallery Thief
    public static int artGallery(int[] weight,int[] values,int capacity){
        int[][] dp=new int[weight.length+1][capacity+1];
        for(int i=0;i<=weight.length;i++){
            dp[i][0]=0;
        }
        for(int i=0;i<=capacity;i++){
            dp[0][i]=0;
        }
        for(int i=1;i<=weight.length;i++){
            for(int j=1;j<=capacity;j++){
                if(weight[i-1]>j)
                    dp[i][j]=dp[i-1][j];
                else
                    dp[i][j]=Math.max(dp[i-1][j],dp[i-1][j-weight[i-1]]+values[i-1]);
            }
        }
        return dp[weight.length][capacity];
    }
    public static int knapSack(int limit,int[] weight,int[] values){
        int[][] result=new int[weight.length+1][limit+1];
        for(int i=1;i<=weight.length;i++){
            for(int j=1;j<=limit;j++){
                if(weight[i-1]>j){
                    result[i][j]=result[i-1][j];
                }else{
                    result[i][j]=Math.max(result[i-1][j],result[i-1][j-weight[i-1]]+values[i-1]);
                }
            }
        }
        return result[weight.length][limit];
    }
    //Buggy Software
    public static int isBuggy(int version){
        if(version==0)
            return 0;
        int low=1;
        int high=version;
        while(low<high){
            int mid=low+(high-low)/2;
            if(isBadVersion(mid)==true){
                high=mid-1;
            }else
                low=mid+1;
        }
        return low;
    }
    public static boolean isBadVersion(int num){
        int n=(int)Math.floor(Math.random());
        return num==n;
    }
    //Gym Lockers
    public static int gymlocker(int round){
        int count=0;
        if(round==0)
            return 0;
        int[] dp=new int[round+1];
        for(int i=1;i<=round;i++){
            dp[i]=1;
        }
        for(int pass=2;pass<=round;pass++){
            for(int i=pass;i<=round;i=i+pass){
                dp[i]=dp[i]==1?0:1;
            }
        }
        for(int i=1;i<=round;i++){
            if(dp[i]==1)
                count++;
        }
        return count;
    }
    //complement of a number
    public static int complementNumber(int num){
        if(num==0)
            return 1;
        int i=1;
        while(i<=num){
            i=i<<1;
        }
        return i-num-1;
    }
    //Create Trie
    static class Trie{
        static class trie{
            trie[] child;
            boolean isEnd;
            public trie(){
                child=new trie[26];
                isEnd=false;
            }
        }
        private static trie node;
        public Trie(){
            node=new trie();
        }
        public static void insert(String s){
            trie temp=node;
            for(int i=0;i<s.length();i++){
                if(temp.child[s.charAt(i)-'a']==null){
                    temp.child[s.charAt(i)-'a']=new trie();
                }
                temp=temp.child[s.charAt(i)];
            }
            temp.isEnd=true;
        }
        public static boolean search(String s){
            trie temp=node;
            for(int i=0;i<s.length();i++){
                if(temp.child[s.charAt(i-'a')]==null)
                    return false;
                else{
                    temp=temp.child[s.charAt(i-'a')];
                }
            }
            return temp.isEnd;
        }
        public static boolean startsWith(String s){
            trie temp=node;
            for(int i=0;i<s.length();i++){
                int j=s.charAt(i)-'a';
                if(temp.child[j]==null)
                    return false;
                temp=temp.child[j];
            }
            return true;
        }
    }
    //finding friend
    public static int findFriend(int[][] friends){
        if(friends.length==0)
            return 0;
        if(friends.length==1)
            return 1;
        boolean[] visited=new boolean[friends.length];
        int result=0;
        for(int i=0;i<friends.length;i++){
            if(!visited[i]){
                result++;
                findFriendHelper(i,visited,friends);
            }
        }
        return result;
    }
    public static void findFriendHelper(int i,boolean[] visited,int[][] friends){
        visited[i]=true;
        for(int j=0;j<friends.length;j++){
            if(!visited[j]&&friends[i][j]==1){
                findFriendHelper(j,visited,friends);
            }
        }
    }
    //Counting Primes
    public static int countingPrime(int num){
        if(num<=0)
            return 0;
        int result=0;
        for(int i=1;i<num;i++){
            if(i%2!=0)
                result++;
        }
        return result;
    }
//    Reverse Vowels
    public static String ReverseVowles(String s){
        if(s.length()==0)
            return "";
        HashSet<Character> hs=new HashSet<>();
        hs.add('a');
        hs.add('e');
        hs.add('i');
        hs.add('o');
        hs.add('u');
        char[] c=s.toCharArray();
        int low=0,high=c.length-1;
        while(low<high){
            while(low<high&&!hs.contains(c[low])){
                low++;
            }
            while(low<high&&!hs.contains(c[high])){
                high--;
            }
            char temp=c[low];
            c[low]=c[high];
            c[high]=temp;
            low++;
            high--;
        }
        return String.valueOf(c);
    }
    //Reap Children
    public static List<Integer> ReapChildren(List<Integer> pid,List<Integer> ppid,int kill){
        HashMap<Integer,List<Integer>> hm=new HashMap<>();
        for(int i=0;i<ppid.size();i++){
            List<Integer> child=hm.getOrDefault(ppid.get(i),new ArrayList<>());
            child.add(pid.get(i));
            hm.put(ppid.get(i),child);
        }
        List<Integer> result=new ArrayList<>();
        reapChildrenHelper(kill,hm,result);
        return result;
    }
    public static void reapChildrenHelper(int kill,HashMap<Integer,List<Integer>> hm,List<Integer> result){
        result.add(kill);
        for(int i:hm.get(kill)){
            result.add(i);
        }
    }
    //Character Scramble
    public static boolean charScramble(String passage,String text){
        if(passage.length()==0)
            return true;
        char[] c=new char[26];

        for(int i=0;i<text.length();i++){
            c[text.charAt(i)-'a']++;
        }
        for(int i=0;i<passage.length();i++){
            c[passage.charAt(i)-'a']--;
            if(c[passage.charAt(i)-'a']>0)
                return false;
        }
        return true;
    }
    //Subsequence
    public static boolean isSubSequence(String s,String t){
        if(s.length()>t.length())
            return false;
        char[] c=new char[26];
        for(int i=0;i<t.length();i++){
            c[t.charAt(i)-'a']++;
        }
        for(int i=0;i<s.length();i++){
            if(c[s.charAt(i)-'a']<=0)
                return false;
        }
        return true;
    }
    //Count Islands
    public static int countIsland(int[][] grid){
        if(grid.length==0||grid==null)
            return 0;
        int result=0;
        for(int i=0;i<grid.length;i++){
            for(int j=0;j<grid[0].length;j++){
                if(grid[i][j]==1){
                    countIslandHelper(i,j,grid);
                    result++;
                }
            }
        }
        return result;
    }
    public static void countIslandHelper(int i,int j,int[][] grid){
        if(i<0||i>=grid.length||j<0||j>=grid[0].length||grid[i][j]==0)
            return;
        grid[i][j]=0;
        countIslandHelper(i-1,j,grid);
        countIslandHelper(i+1,j,grid);
        countIslandHelper(i,j-1,grid);
        countIslandHelper(i,j+1,grid);
    }
    //compress chars
    //this is wrong
    public static int compress(char[] chars) {
        if(chars.length==0)
            return 0;
        int result=0;
        HashMap<Character,Integer> hm=new HashMap<>();
        for(char c:chars){
            hm.put(c,hm.getOrDefault(c,0)+1);
        }
        for(Map.Entry<Character,Integer> set:hm.entrySet()){
            if(set.getValue()>1)
                result++;
        }
        return result+hm.size();
    }
    public static int compressAns(char[] chars){
        int index=0,result=0;
        while(index<chars.length){
            char cur=chars[index];
            int count=0;
            while(index<chars.length&&chars[index]==cur){
                count++;
            }
            chars[result++]=cur;
            if(count!=1){
                for(char c:Integer.toString(count).toCharArray()){
                    chars[result++]=c;
                }
            }
        }
        return result;
    }
    //Defanging IPs
    public static String defanIP(String s){
        if(s.length()==0)
            return "";
        String result="";
        for(int i=0;i<s.length();i++){
            if(s.charAt(i)=='.'){
                result+="[.]";
            }else{
                result+=s.charAt(i);
            }
        }
        return result;
    }
    //Removing Vowels
    public static String removeVowels(String s){
        if(s.length()==0||s==null)
            return s;
        List<Character> vowels=new ArrayList<>();
        vowels.add('a');
        vowels.add('e');
        vowels.add('i');
        vowels.add('o');
        vowels.add('u');
        String result="";
        for(int i=0;i<s.length();i++){
            if(!vowels.contains(s.charAt(i))){
                result+=s.charAt(i);
            }
        }
        return result;
    }
    //Minimize Path
    //first we would solve this is brute force approach
    public static int minPath(int[][] path){
        if(path.length==0||path==null)
            return 0;
        for(int i=1;i<path.length;i++){
            path[i][0]+=path[i-1][0];
        }
        for(int i=1;i<path[0].length;i++){
            path[0][i]+=path[0][i-1];
        }
       int result=0;
        for(int i=1;i<path.length;i++){
            for(int j=1;j<path[0].length;j++){
                path[i][j]+=Math.min(path[i-1][j],path[i][j-1]);
            }
        }
        return path[path.length-1][path[0].length-1];
    }
    //Hamming Distance
    public static int hammingDistance(int x,int y){
        int result=0;
        int xor=x^y;
        while(xor!=0){
            result+=(xor&1);
            xor=xor>>1;
        }
        return result;
    }
//    Travel to Points
    public static int travelTopOint(int[][] points){
        if(points.length==0||points==null)
            return 0;
        int max=0;
        for(int i=0;i<points.length-1;i++){
            int x=Math.abs(points[i][0]-points[i+1][0]);
            int y=Math.abs(points[i][1]-points[i+1][1]);
            max+=Math.max(x,y);
        }
        return max;
    }
    public static int[] rearrangeElment(int[] a){
        if(a.length==0||a==null)
            System.out.println("no element");;

        int k=0;
        for(int i=0;i<a.length;i++){
            if(a[i]!=0){
                a[k++]=a[i];
            }
        }
        while(k!=a.length){
            a[k++]=0;
        }
        return a;
    }
    //Averages
    public static List<Double> averages(BST tree){
        List<Double> result=new ArrayList<>();
        Queue<BST> queue=new LinkedList<>();
        queue.offer(tree);
        while(!queue.isEmpty()){
            int size=queue.size();
            int val=0;
            for(int i=0;i<size;i++){
                BST temp=queue.poll();
                val+=temp.val;
                if(temp.left!=null)
                    queue.offer(temp.left);
                if(temp.right!=null)
                    queue.offer(temp.right);
            }
            result.add((double)val/size);
        }
        return result;
    }
    //Array Shuffle
    public static int[] shuffleArray(int[] a){
        int[] result=new int[a.length];
        if(a.length==0)
            return result;
        int k=0;
        for(int i=0;i<a.length;i++){
            if(a[i]%2==0){
                result[k]=a[i];
                k=k+2;
            }
        }
        k=1;
        for(int i=0;i<a.length;i++){
            if(a[i]%2!=0&&k<a.length){
                result[k]=a[i];
                k+=2;
            }
        }
        return result;
    }
    //Is Monotonic
    public static boolean monotonic(int[] a){
        boolean increasing=false;
        boolean decreasing=false;
        if(a[0]<a[a.length-1])
            increasing=true;
        else
            decreasing=true;
        for(int i=0;i<a.length-1;i++){
            if(increasing){
                if(a[i]>a[i+1]){
                    return false;
                }
            }
            if(decreasing){
                if(a[i]<a[i+1]){
                    return false;
                }
            }
        }
        return true;
    }
    //Diving Deep
    static class n_array{
        int val;
        List<n_array> children;
        public n_array(int val){
            this.val=val;
            this.children=new ArrayList<>();
        }
    }
    //i am thinking of using a dfss
    public static int DeepDive(n_array root){
        if(root==null)
            return 0;
        int max=0;
        for(n_array i:root.children){
            max=Math.max(DeepDive(i),max);
        }
        return max+1;
    }
    //Keyboard Row
    public static List<String> keyboardRow(List<String> input){
        List<String> result=new ArrayList<>();
        if(input.size()==0)
            return result;
        String[] words={"qwertyuiop","asdfghjkl","zxcvbnm"};
        for(String s:input){
            boolean singlerow=true;
            int rowIndex=-1;
            for(int i=0;i<words.length;i++){
                if(words[i].indexOf(s.charAt(0))!=-1){
                    rowIndex=i;
                    break;
                }
            }
            for(int j=1;j<words.length;j++){
                if(words[rowIndex].indexOf(s.charAt(j))==-1){
                    singlerow=false;
                    break;
                }
            }
            if(singlerow){
                result.add(s);
            }
        }
        return result;
    }
    //Flip Flopping Bits
    public static boolean flipFlop(int number){
        if(number<=2)
            return true;
        int prev=number&1;
        number=number>>1;
        while(number!=0){
            if((number&1)==prev)
                return false;
            prev=number&1;
            number=number>>1;
        }
        return true;
    }
    //Reverse Number
    public static int reverseNumber(int number){
        if(number==0)
            return number;
        int reverseNumber=0;
        while(number!=0){
            int temp=number%10;
            reverseNumber=reverseNumber*10+temp;
            number=number/10;
        }
        return reverseNumber;
    }
    //No Same Neighbors
    public static String noSame(String s){
        if(s.length()==0)
            return "";
        HashMap<Character,Integer> hm=new HashMap<>();
        PriorityQueue<Character> pq=new PriorityQueue<>((a,b)->hm.get(b)-hm.get(a));
        for(char c:s.toCharArray()){
            hm.put(c,hm.getOrDefault(c,0)+1);
        }
        pq.addAll(hm.keySet());
        StringBuilder result=new StringBuilder();
        while(pq.size()>1){
            char present=pq.remove();
            char next=pq.remove();
            result.append(present);
            result.append(next);
            hm.put(present,hm.get(present)-1);
            hm.put(next,hm.get(next)-1);
            if(hm.get(present)>0){
                pq.add(present);
            }
            if(hm.get(next)>0){
                pq.add(next);
            }
        }
        if(!pq.isEmpty()){
            char hope=pq.remove();
            if(hm.get(hope)>1)
                return "";
            result.append(hope);
        }
        return result.toString();
    }
    //Identical Elements
    public static boolean identicalEle(int[] arr,int k){
        if(arr.length<=1)
            return true;
        HashMap<Integer,List<Integer>> hm=new HashMap<>();
        for(int i=0;i<arr.length;i++){
            if(hm.containsKey(arr[i])){
                List<Integer> check=hm.get(arr[i]);
                int leftover=Math.abs(i-check.get(check.size()-1));
                if(leftover!=k)
                    return false;
                check.add(i);
                hm.put(arr[i],check);
            }else{
                List<Integer> temp=new ArrayList<>();
                temp.add(i);
                hm.put(arr[i],temp);
            }
        }
        return true;
    }
    //sum within bounds
    public static int SumBound(BST root,int low,int high){
        if (root == null) {
            return 0;
        }
        int sum = 0;
        if (root.val >= low && root.val <= high) {
            sum += root.val;
        }
        if (root.val > low) {
            sum += SumBound(root.left, low, high);
        }
        if (root.val < high) {
            sum += SumBound(root.right, low, high);
        }
        if (root.val == low) {
            sum += SumBound(root.right, low, high);
        }
        if (root.val == high) {
            sum += SumBound(root.left, low, high);
        }
        return sum;
    }
    //BirthDay
    public static int satisfied(int[] guest,int[] app){
        if(guest.length==0)
            return 0;
        if(app.length==0)
            return guest.length;
        HashSet<Integer> hs=new HashSet<>();
        for(int i:guest){
            hs.add(i);
        }
        int result=0;
        for(int i:app){
            if(hs.contains(i)){
                result++;
                hs.remove(i);
            }
        }
        return result;
    }
    //subTree
    public static boolean isSub(BST parent,BST child){
        if(parent==null)
            return parent==null&&child==null;
        if(parent.val==child.val){
            return isSubHelper(parent,child);
        }
        return isSub(parent.left,child)||isSub(parent.right,child);
    }
    public static boolean isSubHelper(BST parent,BST child){
        if(parent==null)
            return parent==null&&child==null;
        if(parent.val!=child.val)
            return false;
        return isSubHelper(parent.left,child.left)&&isSubHelper(parent.right,child.right);
    }
    //Birthday Cake
    public static int birthDay(int[] app,int[] cake){
        Arrays.sort(app);
        Arrays.sort(cake);
        int i=0;
        int j=0;
        int result=0;
        while(i<app.length&&j<cake.length){
            int person=app[i];
            int piece=cake[j];
            while(person>piece&&j<cake.length){
                j++;
                piece=cake[j];
            }
            if(piece>=person){
                result++;
                i++;
                j++;
            }
        }
        return result;
    }
    //Divisible Digits
    public static List<Integer> divisible(int num){
        List<Integer> result=new ArrayList<>();
        for(int i=0;i<num;i++){
            if(isSlef(i)){
                result.add(i);
            }
        }
        return result;
    }

    public static boolean isSlef(int num){
        int originalNum = num;
        while (num != 0) {
            int digit = num % 10;
            if (digit == 0 || originalNum % digit != 0) {
                return false;
            }
            num /= 10;
        }
        return true;
    }
    //Pond Size
    public static int pondSize(int[][] grid){
        if (grid == null || grid.length == 0)
            return 0;
        int max = 0;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j] == 0) {
                    max = Math.max(max, pondSizeHelper(i, j, grid));
                }
            }
        }
        return max;
    }
    public static int pondSizeHelper(int i,int j,int[][] grid){
        int size = 0;
        Stack<int[]> stack = new Stack<>();
        stack.push(new int[]{i, j});

        while (!stack.isEmpty()) {
            int[] curr = stack.pop();
            int x = curr[0];
            int y = curr[1];
            if (x >= 0 && x < grid.length && y >= 0 && y < grid[0].length && grid[x][y] == 0) {
                size++;
                grid[x][y] = 1;
                stack.push(new int[]{x - 1, y});
                stack.push(new int[]{x + 1, y});
                stack.push(new int[]{x, y - 1});
                stack.push(new int[]{x, y + 1});
            }
        }

        return size;
    }
    //Find the Number
    //here we are find the nth largest number in the array
    //for this we are not using sorting as it will take nlongn to sort the array
    //instead we are going to use max heap where the solution can be executed in log n time
    public static int findtheNumber(int[] numbers,int pos){
        if(numbers.length==0||numbers==null)
            return 0;
        PriorityQueue<Integer> pq=new PriorityQueue<>((a,b)->b-a);
        for(int i :numbers){
            pq.offer(i);
        }
        while(pos>1){
            pq.poll();
            pos=pos-1;
        }
        return pq.peek();
    }
    //the above approach we are usig loops twice instead lets implement this is 1 for loop
    public static int findtheNumbers(int[] number,int pos){
        if(number.length==0||number==null)
            return 0;
        PriorityQueue<Integer> pq=new PriorityQueue<>();
        for(int i:number){
                pq.offer(i);
            if(pq.size()>pos){
                pq.poll();
            }
        }
        return pq.peek();
    }
    //Swap Words
    public static String swapWors(String s){
        if(s.length()<=1)
            return s;
        String[] str=s.split(" ");
        int i=0,j=str.length-1;
        while(i<j){
            String temp=str[i];
            str[i]=str[j];
            str[j]=temp;
            i++;
            j--;
        }
        return String.join(" ",str);
    }
    //Products
    public static int[] products(int[] arr){
        if(arr.length<=1)
            return arr;
        int[] result=new int[arr.length];
        for(int i=0;i<arr.length;i++){
            int sum=1;
            for(int j=0;j<arr.length;j++){
                if(i==j)
                    continue;
                sum*=arr[j];
            }
            result[i]=sum;
        }
        return result;
    }
    //there is a better way to solve this problem
    public static int[] productMinMax(int[] arr){
        if(arr.length==0||arr==null)
            return new int[0];
        int[] prefix=new int[arr.length];
        int[] sufix=new int[arr.length];
        int[] result=new int[arr.length];
        prefix[0]=1;
        sufix[arr.length-1]=1;
        for(int i=1;i<arr.length;i++){
            prefix[i]=prefix[i-1]*arr[i-1];
        }
        for(int i=arr.length-2;i>=0;i--){
            sufix[i]=sufix[i+1]*arr[i+1];
        }
        for(int i=0;i<arr.length;i++){
            result[i]=prefix[i]*sufix[i];
        }
        return result;
    }
    //Tree Paths
    public static int treePath(BST node,int target){
        if(node==null){
            return 0;
        }
        int[] result=new int[1];
        return treepathHelper(node,target,  new HashMap<>(),0);
    }
    public static int treepathHelper(BST node, int k, Map<Integer, Integer> prefixSums, int currSum){
        if (node == null) {
            return 0;
        }

        currSum += node.val;
        int numPaths = prefixSums.getOrDefault(currSum - k, 0);
        if (currSum == k) {
            numPaths++;
        }

        prefixSums.put(currSum, prefixSums.getOrDefault(currSum, 0) + 1);

        numPaths += treepathHelper(node.left, k, prefixSums, currSum) + treepathHelper(node.right, k, prefixSums, currSum);

        prefixSums.put(currSum, prefixSums.get(currSum) - 1);
        if (prefixSums.get(currSum) == 0) {
            prefixSums.remove(currSum);
        }

        return numPaths;
    }
    //1129. Shortest Path with Alternating Colors
    public static int[] shortAltColor(int[][] red,int[][] blue,int limit){
        if((red.length==0&&blue.length==0)||limit==0)
            return new int[0];
        List<Integer>[] redList=new ArrayList[limit];
        List<Integer>[] blueList=new ArrayList[limit];
        for(int i=0;i<red.length;i++){
            redList[i]=new ArrayList<>();
        }
        for(int i=0;i<blue.length;i++){
            blueList[i]=new ArrayList<>();
        }
        for(int[] i:red){
            redList[i[0]].add(i[1]);
        }
        for(int[] i:blue){
            blueList[i[0]].add(i[1]);
        }
        int[][] dist=new int[limit][limit];
        for(int i=0;i<limit;i++){
            Arrays.fill(dist[i],-1);
        }
        dist[0][0]=0;
        dist[0][1]=0;
        Queue<int[]> queue=new LinkedList<>();
        queue.offer(new int[]{0,0});//red point of view
        queue.offer(new int[]{0,1});//blue point of view
        while(!queue.isEmpty()){
            int[] temp= queue.poll();
            int distance=temp[0];
            int color=temp[1];
            List<Integer>[] list=color==0?redList:blueList;
            for(int neighbour:list[distance]){
                if(dist[neighbour][1-color]==-1){
                    dist[neighbour][1-color]=dist[distance][color]+1;
                    queue.offer(new int[]{neighbour,1-color});
                }
            }
        }
        int[] result=new int[limit];
        for(int i=0;i<limit;i++){
            int min=-1;
            for(int j=0;j<2;j++){
                if(dist[i][j]!=-1){
                    if(min==-1||dist[i][j]<min){
                        result[i]=dist[i][j];
                    }
                }
            }
            result[i]=min;
        }
        return result;
    }
    //1466. Reorder Routes to Make All Paths Lead to the City Zero
    public static int reorder(int[][] roads,int total){
        HashSet<String> hs=new HashSet<>();
        HashMap<Integer,Set<Integer>> hm=new HashMap<>();
        for(int i=0;i<total;i++){
            hm.put(i,new HashSet<>());
        }
        for(int[] i:roads){
            hs.add(i[0]+";"+i[1]);
            hm.get(i[0]).add(i[1]);
            hm.get(i[1]).add(i[0]);
        }
        Queue<Integer> queue=new LinkedList<>();
        queue.offer(0);
        boolean[] visited=new boolean[total];
        visited[0]=true;
        int result=0;
        while(!queue.isEmpty()){
            int temp=queue.poll();
            for(int i:hm.get(temp)){
                if(visited[i])
                    continue;
                if(!hs.contains(i+";"+temp))
                    result++;
                queue.offer(i);
            }
        }
        return result;
    }
    //Bottom of the Barrel
    public static int bottomBarrel(BST node){
        if(node==null)
            return 0;
        int[] arr={0,-1};
        bottomBarrelHelper(node,0,arr);
        return arr[0];
    }
    public static void bottomBarrelHelper(BST node,int level,int[] arr){
        if(node==null)
            return;
        if(node.left!=null&&level>arr[1]){
            arr[0]=node.left.val;
            arr[1]=level;
        }
        bottomBarrelHelper(node.left,level+1,arr);
        bottomBarrelHelper(node.right,level+1,arr);
    }
    //String Repetition
    public static boolean StringRep(String s){
        int len=s.length();
        for(int i=len/2;i>=1;i--){
            if(len%i==0){
                int limit=len/i;
                String str=s.substring(0,i);
                StringBuilder sb=new StringBuilder();
                for(int j=0;j<limit;j++){
                    sb.append(str);
                }
                if(sb.toString().equals(s)){
                    return true;
                }
            }
        }
        return false;
    }
        public static List<String> repeatedSubstrings(String s) {
            List<String> res = new ArrayList<>();
            if (s.length() < 10) {
                return res;
            }

            Map<String, Integer> freq = new HashMap<>();
            for (int i = 0; i <= s.length() - 10; i++) {
                String substr = s.substring(i, i + 10);
                if(StringRep(substr)){
                    freq.put(substr, freq.getOrDefault(substr, 0) + 1);
                }
            }
            for (Map.Entry<String, Integer> entry : freq.entrySet()) {
                if (entry.getValue() > 1) {
                    res.add(entry.getKey());
                }
            }

            return res;
        }
//Setting Sail
    public static boolean settingSail(int[][] grid,int limit){
        if(grid.length==0||grid==null||limit==0)
            return false;
        //we are going to implememt this using dfs approach
        int current=0;
        for(int i=0;i<grid.length;i++){
            for(int j=0;j<grid[0].length;j++){
                if(grid[i][j]==1){
                    seetingSailHelper(grid,i,j,current);
                }
            }
        }
        return current< limit;
    }
    public static void seetingSailHelper(int[][] grid,int i,int j,int current){
        if (i < 0 || i >= grid.length || j < 0 || j >= grid[0].length || grid[i][j] == 0) {
            return;
        }
        grid[i][j]=0;
        current=current+1;
        seetingSailHelper(grid,i-1,j,current);
        seetingSailHelper(grid,i+1,j,current);
        seetingSailHelper(grid,i,j-1,current);
        seetingSailHelper(grid,i,j+1,current);
    }
    //Unique Characters
    public static int uniqueChar(String s,int sizeLimit){
        if(s.length()==0||s==null){
            return 0;
        }
        HashMap<Character,Integer> hm=new HashMap<>();
        int start=0;
        int end=0;
        int result=0;
        while(end<s.length()){
            char left_char=s.charAt(end);
            hm.put(left_char,hm.getOrDefault(left_char,0)+1);
            end++;
            while(hm.size()>sizeLimit){
                char right_char=s.charAt(start);
                hm.put(right_char,hm.get(right_char)-1);
                if(hm.get(right_char)==0)
                    hm.remove(right_char);
                start++;
            }
            result=Math.max(result,end-start);
        }
        return result;
    }
    //close points
    public static int[][] closePoints(int[][] points,int k){
       PriorityQueue<int[]> pq=new PriorityQueue<>((a,b)->Integer.compare(closepointshelper(a),closepointshelper(b)));
       for(int[] i:points){
           pq.offer(i);
       }
       while(pq.size()>k){
           pq.poll();
       }
       int[][] result=new int[k][2];
       while(!pq.isEmpty()){
           result[--k]=pq.poll();
       }
       return result;
    }
    public static int closepointshelper(int[] i){
        return ((i[0]*i[0])+(i[1]*i[1]));
    }
    //the same using list
    public static List<int[]> pointsClose(int[][] points,int k){
        if(points.length==0||points==null||k==0)
            return new ArrayList<>();
        PriorityQueue<int[]> pq=new PriorityQueue<>((a,b)->Integer.compare(closepointshelper(b),closepointshelper(a)));
        for(int[] i:points){
            pq.offer(i);
        }
        while(pq.size()>k){
            pq.poll();
        }
        List<int[]> result=new ArrayList<>();
        while(!pq.isEmpty()){
            result.add(pq.poll());
        }
        return result;
    }
    //List Sum
    static class ListNode{
        ListNode next;
        int val;
        public ListNode(int val){
            this.val=val;
            this.next=null;
        }
        public ListNode(int val,ListNode node){
            this.val=val;
            this.next=node;
        }
        public ListNode(){}
    }
    public static ListNode ListSum(ListNode list1,ListNode list2){
        ListNode result=new ListNode(0);
        ListNode Dummy=result;
        int carry=0;
        while(list1!=null&&list2!=null){
            int element1=list1!=null?list1.val:0;
            int element2=list2!=null?list2.val:0;
            int number=element2+element1+carry;
            int leftover=number%10;
            carry=number/10;
            ListNode next=new ListNode(leftover);
            Dummy.next=next;
            if(list1.next!=null){
                list1=list1.next;
            }
            if(list2.next!=null){
                list2=list2.next;
            }
            Dummy=Dummy.next;
        }
        if(carry>0){
            ListNode list=new ListNode(carry);
            Dummy.next=list;
            Dummy=Dummy.next;
        }
        return result.next;
    }
    //clean tree
    public static BST cleanTree(BST node){
        if(node==null)
            return new BST();
        node.left=cleanTree(node.left);
        node.right=cleanTree(node.right);
        if(node.left==null&&node.right==null){
            if(node.val==1){
                return node;
            }else{
                return null;
            }
        }
        if(node.left==null){
            return node.right;
        }
        if(node.right==null){
            return node.left;
        }

        return node;
    }
//Word Length
    //get the word length of the last character in the String
    //normally what i would do is to create an array of String use split and return the last array string length
    //but in this case i m not allowed to use any split() method
public static int lastWordLen(String s){
        if(s.length()==0||s==null)
            return 0;
        Queue<Character> queue=new LinkedList<>();
        int count=0;
        for(char c:s.toCharArray()){
            if(c==' '){
                count=0;
            }else{
                count++;
            }

        }
        return count;
}
//the chaptgpt solution is preety ,uch the same but it thinks my approch will not work for all test cases
    public static int lenOfTheLast(String s){
        int length=0;
        int lastLength=0;
        for(char c:s.toCharArray()){
            if(c==' '){
                if(length>0){
                    lastLength=length;
                }
                length=0;
            }else{
                length++;
            }
        }
        if(length>0){
            return length;
        }
        return lastLength;
    }
    //Third Largest
    public static int thirdLargest(int[] nums){
        if(nums.length==0||nums==null)
            return 0;
        HashSet<Integer> hashset=new HashSet<>();
        PriorityQueue<Integer> pq=new PriorityQueue<>((a,b)->Integer.compare(a,b));

        for(int i:nums){
            hashset.add(i);
        }
        for(int i:hashset){
            pq.add(i);
        }
        if(pq.size()<3){
            while(pq.size()!=1){
                pq.poll();
            }
        }
        while(pq.size()>3){
            pq.poll();
        }
        return pq.peek();
    }
    //now we are going to find whether a graph can be bipartite or not
    //for this problem we are going to assign colors in 3 different colors
    //0-no color
    //1-red color
    //2-blue-color
    //the first approch which we arec going to use is the DFS Approch
    public static boolean isBarpatite(int[][] graph){
        if(graph.length==0||graph==null){
            return false;
        }
        int[] visited=new int[graph.length];
        for(int i=0;i<graph.length;i++){
            if(visited[i]==0 && !isvalidBi(graph,visited,1,i)){
                return false;
            }
        }
        return true;
    }
    public static boolean isvalidBi(int[][] graph,int[] visited,int color,int index){
        if(visited[index]!=0){
            return visited[index]==color;
        }
        visited[index]=color;
        for(int i:graph[index]){
            if(!isvalidBi(graph,visited,-color,i)){
                return false;
            }
        }
        return true;
    }
    //approch using BFS
    public static boolean isBAp2(int[][] graph){
        if(graph.length==0||graph==null)
            return false;
        int[] visited=new int[graph.length];
        Queue<Integer> queue=new LinkedList<>();
        for(int i=0;i<graph.length;i++){
            if(visited[i]==0){
                visited[i]=1;
                queue.offer(i);
                while(!queue.isEmpty()){
                    Integer current=queue.poll();
                    for(int a:graph[current]){
                        if(visited[a]==visited[current]){
                          return false;
                        }else if(visited[a]==0){
                            queue.add(a);
                            visited[a]=-visited[current];
                        }
                    }
                }
            }
        }
        return true;
    }
    //apprcoh using union find
    static class Solution {
        int[] parent;
        int[] rank;

        public  boolean unionFinder(int[][] graph) {
            if (graph == null || graph.length == 0) {
                return false;
            }

            int n = graph.length;
            parent = new int[n];
            rank = new int[n];

            for (int i = 0; i < n; i++) {
                parent[i] = i;
                rank[i] = 0;
            }

            for (int i = 0; i < n; i++) {
                for (int j : graph[i]) {
                    if (find(i) == find(j)) {
                        return false;
                    }
                    union(i, j);
                }
            }

            return true;
        }
        public void union(int x, int y) {
            int rootX = find(x);
            int rootY = find(y);

            if (rootX != rootY) {
                if (rank[rootX] < rank[rootY]) {
                    parent[rootX] = rootY;
                } else if (rank[rootX] > rank[rootY]) {
                    parent[rootY] = rootX;
                } else {
                    parent[rootY] = rootX;
                    rank[rootX]++;
                }
            }
        }
        public int find(int point) {
            if (parent[point] == point) {
                return point;
            }
            parent[point] = find(parent[point]);
            return parent[point];
        }
    }
    //kth smallest element
    public static int smallestKele(BST node,int K){
        if(node==null)
            return -1;
        List<Integer> element=new ArrayList<>();
        smallestKeleHelper(node,element);
        Object[] solution=element.toArray();
        Arrays.sort(solution);
        return (int) solution[K-1];
    }
    public static void smallestKeleHelper(BST node,List<Integer> element){
        if(node==null)
            return;
        smallestKeleHelper(node.left,element);
        element.add(node.val);
        smallestKeleHelper(node.right,element);
    }
    //if it is the prefect Binary Search Tree
    public static int BSTSmallest(BST node,int number){
        if(node==null)
            return -1;
        Stack<BST> stack=new Stack<>();
        while(node!=null||!stack.isEmpty()){
            while(node!=null){
                stack.push(node);
                node=node.left;
            }
            node=stack.pop();
            if(--number==0)
                break;
            node=node.right;
        }
        return node.val;
    }
    //if its is not a Perfect BST then we can use the quick sort approch to decarse the time complexcity
    public static int smallBST(BST node, int k) {
        if (node == null || k <= 0)
            return -1;

        List<Integer> elements = new ArrayList<>();
        smallestKeleHelper(node, elements);

        if (k > elements.size())
            return -1;

        int[] arr = elements.stream().mapToInt(Integer::intValue).toArray();
        return quickSelect(arr, 0, arr.length - 1, k - 1);
    }


    public static int quickSelect(int[] arr, int low, int high, int k) {
        if (low == high)
            return arr[low];

        int pivotIndex = partition(arr, low, high);

        if (pivotIndex == k)
            return arr[pivotIndex];
        else if (pivotIndex < k)
            return quickSelect(arr, pivotIndex + 1, high, k);
        else
            return quickSelect(arr, low, pivotIndex - 1, k);
    }

    public static int partition(int[] arr, int low, int high) {
        int pivot = arr[high];
        int i = low - 1;

        for (int j = low; j < high; j++) {
            if (arr[j] < pivot) {
                i++;
                swap(arr, i, j);
            }
        }

        swap(arr, i + 1, high);
        return i + 1;
    }

    public static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
    //teansponse matrix
    public static int[][] transpose(int[][] matrix){
        int m=matrix.length;
        int n=matrix[0].length;
        int[][] result=new int[n][m];
        for(int i=0;i<n;i++){
            for(int j=i+1;j<m;j++){
                result[i][j]=matrix[j][i];
            }
        }
        return result;
    }
    //Infection
    //using dfs approach
    public static int getTotalMinutes(int[][] grid){
        int result=2;
        for(int i=0;i<grid.length;i++){
            for(int j=0;j<grid[0].length;j++){
                if(grid[i][j]==2){
                    getTotalMinutesHelper(grid,i,j,2);
                }
            }
        }
        for(int i=0;i<grid.length;i++){
            for(int j=0;j<grid[0].length;j++){
                if(grid[i][j]==1)
                    return -1;
                else
                    result=Math.max(result,grid[i][j]);
            }
        }
        return result-2;
    }
    public static void getTotalMinutesHelper(int[][] grid,int i,int j,int status){
        if(i<0||j<0||i>=grid.length||j>=grid[0].length||grid[i][j]==0)
            return;
        if(grid[i][j]>1&&grid[i][j]<status)
            return;
        grid[i][j]=status;
        status++;
        getTotalMinutesHelper(grid,i-1,j,status);
        getTotalMinutesHelper(grid,i+1,j,status);
        getTotalMinutesHelper(grid,i,j-1,status);
        getTotalMinutesHelper(grid,i,j+1,status);
    }
    //bfs
    public static int rottern(int[][] grid){
        if(grid.length==0||grid==null)
            return -1;
        Queue<int[]> queue=new LinkedList<>();
        int count=0;
        for(int i=0;i<grid.length;i++){
            for(int j=0;j<grid[0].length;j++){
                if(grid[i][j]==1){
                    count++;
                }else if(grid[i][j]==2){
                    queue.offer(new int[]{i,j});
                }
            }
        }
        int[][] dir={{-1,0},{1,0},{0,-1},{0,1}};
        int result=0;
        while(!queue.isEmpty()){
            result++;
            for(int i=0;i< queue.size();i++){
                int[] current= queue.poll();
                for(int[] d:dir){
                    int x=current[0]+d[0];
                    int y=current[1]+d[1];
                    if(x<0||y<0||x>=grid.length||y>=grid[0].length||grid[x][y]==0||grid[x][y]==2)
                        continue;
                    grid[x][y]=2;
                    count--;
                    queue.offer(new int[]{x,y});
                }
            }
        }
        return result-1;
    }
    //Link-Up
    static class Listi{
        int data;
        Listi next;
        public Listi(int data){
            this.data=data;
        }
        public Listi(){}
    }
    public static Listi linkUp(Listi node){
        if(node==null){
            return new Listi(0);
        }
        Listi odd=new Listi();
        Listi even=new Listi();
        Listi oddHead = odd;
        Listi evenHead=even;
        while(node!=null){
            int val=node.data;
            if(val%2==0){
                even.next=new Listi(val);
                even=even.next;
            }else{
                odd.next=new Listi(val);
                odd=odd.next;
            }
            node=node.next;
        }

        odd.next = evenHead.next;

        return oddHead.next;
    }
    //Elections
    //this versions should be used when the requirement is to not use extra memory
    // if extra space you can use hashmap  with less time complexcity
    public static int elections(int[] arr){
        if(arr.length==0||arr==null)
            return -1;
        int candidate=0;
        int count=0;
        for(int i:arr){
            if(count==0){
                candidate=i;
                count++;
            }else{
                count--;
            }
        }
        int percentage=0;
        for(int i:arr){
            if(i==candidate){
                percentage++;
            }
        }
        return percentage>arr.length/2?candidate:-1;
    }
    public static void printing(Listi node){
        while(node!=null){
            System.out.println(node.data);
            node=node.next;
        }
    }
    //Locked Room
    public static boolean lockedRoom(int[][] rooms){
        if(rooms.length==0||rooms==null)
            return true;
        Queue<Integer> queue=new LinkedList<>();
        queue.offer(0);
        boolean[] visited=new boolean[rooms.length];
        while(!queue.isEmpty()){
            int size=queue.size();
            for(int i=0;i<size;i++){
                int current=queue.poll();
                if(visited[current]){
                    continue;
                }
                visited[current]=true;
                for(int room:rooms[current]){
                    queue.offer(room);
                }
            }
        }
        for(int i=0;i<visited.length;i++){
            if(!visited[i])
                return false;
        }
        return true;
    }
    //using the dfs approch
    public static boolean lockedRoomDfs(int[][] rooms){
        if(rooms.length==0||rooms==null)
            return true;
        boolean[] visited=new boolean[rooms.length];
        List<Integer> current=new ArrayList<>();
        current.add(0);
        lockedRoomDfsHelper(rooms,visited,current);
        for(int i=0;i<visited.length;i++){
            if(!visited[i])
                return false;
        }
        return true;
    }
    public static void lockedRoomDfsHelper(int[][] rooms, boolean[] visited, List<Integer> current) {
        if (current.isEmpty()) {
            return;
        }

        for (int i = 0; i < current.size(); i++) {
            int room = current.get(i);

            if (!visited[room]) {
                visited[room] = true;
                for (int nextRoom : rooms[room]) {
                    current.add(nextRoom);
                }
            }
        }
    }
    //Partners
    public static int Partbers(int[] nums){
        if(nums.length==0||nums==null)
            return 0;
        HashMap<Integer,Integer> hm=new HashMap<>();
        for(int i:nums){
            hm.put(i,hm.getOrDefault(i,0)+1);
        }
        int result=0;
        for(int i:hm.values()){
            if(i>1){
                result+=nc2(i,2);
            }
        }
        return result;
    }
    public static int nc2(int n,int r){

        int up=fact(n);
        int down=fact(n-r)*fact(r);
        return up/down;
    }
    public static int fact(int n){
        if(n==1||n==0)
            return 1;
        return n*fact(n-1);
    }
    public static boolean TreePair(BST node,int target){
        if(target==0)
            return true;
        HashSet<Integer> hashset=new HashSet<>();
        return TreePairHelper(node,target,hashset);
    }
    public static boolean TreePairHelper(BST node,int target,HashSet<Integer> hashset){
        if(node == null)
            return false;
        int compliment=target-node.val;
        if(hashset.contains(compliment)){
            return true;
        }
        hashset.add(node.val);
        return TreePairHelper(node.left,target,hashset)||TreePairHelper(node.right,target,hashset);
    }
    //unique Characters
    public static int uniChar(String s){
        if(s.length()==0||s==null)
            return 0;
        int start=0;
        int end=0;
        int result=0;
        int len=s.length();
        HashSet<Character> hashset=new HashSet<>();
        while(end<len){
            if(hashset.contains(s.charAt(end))){
                hashset.remove(s.charAt(start));
                start++;
            }else{
                int c=end-start+1;
                hashset.add(s.charAt(end));
                result=Math.max(c,result);
                end++;
            }
        }
        return result;
    }
    //Longest conservative Path
    public static int longConPath(BST node){
        if(node==null)
            return 0;
        Queue<BST> queue=new LinkedList<>();
        queue.offer(node);
        int result=0;
        while(!queue.isEmpty()){
            int size=queue.size();
            for(int i=0;i<size;i++){
                BST temp=queue.poll();
                if(temp.left!=null)
                    queue.offer(temp.left);
                if(temp.right!=null)
                    queue.offer(temp.right);
            }
            result++;
        }
        return result;
    }
    //google interview question
    //Writing email
    public static int writeEmail(String[] emails){
        if(emails.length==0||emails==null)
            return 0;
        HashMap<String,Integer> hashmap=new HashMap<>();
        for(String email:emails){
            StringBuilder sb=new StringBuilder();
            boolean ignore=false;
            for(char c:email.toCharArray()){
               if(c=='+'){
                   ignore=true;
               }else if(c=='@'){
                   sb.append(email.substring(email.indexOf('@')));
               }else if(!ignore && c!='.'){
                   sb.append(c);
               }
            }
            hashmap.put(sb.toString(),hashmap.getOrDefault(sb.toString(),0)+1);
        }
        return hashmap.size();
    }
    //rotate the array in 90 degrees
    public static int[][] rotateArray(int[][] arr){
        if(arr.length==0||arr==null)
            return arr;
        for(int i=0;i<arr.length/2;i++){
            for(int j=i;j<arr.length-1-i;j++){
                int temp=arr[i][j];
                arr[i][j]=arr[arr.length-1-j][i];
                arr[arr.length-1-j][i]=arr[arr.length-1-i][arr.length-1-j];
                arr[arr.length-1-i][arr.length-1-j]=arr[j][arr.length-1-i];
                arr[j][arr.length-1-i]=temp;
            }
        }
        return arr;
    }
    public static void prinninty(int[][] arr){
        for(int[] i:arr){
            for(int j:i){
                System.out.println(j);
            }
        }
    }
    //Shortest Distance
    public static int[] shortDistance(String s,char c){
        if(s.length()==0||s==null)
            return new int[0];
        List<Integer> temp=new ArrayList<>();
        int[] result=new int[s.length()];
        Arrays.fill(result,-1);
        for(int i=0;i<s.length();i++){
            if(s.charAt(i)==c){
                result[i]=0;
                temp.add(i);
            }
        }
        int[] position=new int[temp.size()];
        for(int i=0;i<temp.size();i++){
            position[i]=temp.get(i);
        }
        for(int i=0;i<s.length();i++){
            if(result[i]!=0){
                int min=Integer.MAX_VALUE;
                for(int j=0;j<position.length;j++){
                    int distance=Math.abs(position[j]-i);
                    if(min>distance)
                        min=distance;
                }
                result[i]=min;
            }
        }
        return result;
    }
    public static int[] shortestDistance(String s, char c) {
        if (s == null || s.length() == 0)
            return new int[0];

        int[] result = new int[s.length()];
        Arrays.fill(result, Integer.MAX_VALUE);
        int prevIndex = -1;

        // Forward pass
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == c) {
                prevIndex = i;
            }
            if (prevIndex != -1) {
                result[i] = i - prevIndex;
            }
        }

        prevIndex = -1;
        // Backward pass
        for (int i = s.length() - 1; i >= 0; i--) {
            if (s.charAt(i) == c) {
                prevIndex = i;
            }
            if (prevIndex != -1) {
                if (result[i] == 0 || prevIndex - i < result[i]) {
                    result[i] = prevIndex - i;
                }
            }
        }

        return result;
    }
    //Counting Words
    public static List<String> countWord(String[] words,int k){

        if(words.length==0){
            return new ArrayList<>();
        }
        HashMap<String,Integer> hashmap=new HashMap<>();
        for(String s:words){
            hashmap.put(s,hashmap.getOrDefault(s,0)+1);
        }
        List<String> result=new ArrayList<>(hashmap.keySet());
        Collections.sort(result,(a,b)->{
            int freq=hashmap.get(a)-hashmap.get(b);
            if(freq==0)
                return a.compareTo(b);
            return freq;
        });
        return result.subList(0,k);
    }
    //another approch
    public static List<String> countWords(String[] words,int k){
        if(words.length==0)
            return new ArrayList<>();
        HashMap<String,Integer> hashmap=new HashMap<>();
        for(String word:words){
            hashmap.put(word,hashmap.getOrDefault(word,0)+1);
        }
        PriorityQueue<String> pq=new PriorityQueue<>(new compartor(hashmap));
        for(String s:hashmap.keySet()){
            pq.offer(s);
        }
        List<String> result=new ArrayList<>();
        while(result.size()<k){
            String s=pq.poll();
            result.add(s);
        }
        return result;
    }
    static class compartor implements Comparator<String>{
        HashMap<String,Integer> hashmap;
        public compartor(HashMap<String,Integer> hashmap){
            this.hashmap=hashmap;
        }
        @Override
        public int compare(String a,String b){
            int fre=hashmap.get(a)-hashmap.get(b);
            if(fre==0)
                return a.compareTo(b);
            return fre;
        }
    }
    //Cut String
    public static List<Integer> cutString(String s){
        if(s.length()==0)
            return new ArrayList<>();
        List<Integer> result=new ArrayList<>();
        for(int i=0;i<s.length();i++){
            int current='a'-s.charAt(i);
            int length=0;
            for(int j=i+1;j<s.length();j++){
                int next='a'-s.charAt(j);
                if(current==next){
                    length=j-i;
                    i=j;
                }
            }
            result.add(length+1);
        }
        return result;
    }
    //the above approch is not working in all cases
    public static List<Integer> Cutstring(String s){
        if(s.length()==0||s==null)
            return new ArrayList<>();
        HashMap<Character,Integer> hashmap=new HashMap<>();
        for(int i=0;i<s.length();i++){
            hashmap.put(s.charAt(i),i);
        }
        int prev=-1;
        int max=0;
        List<Integer> result=new ArrayList<>();
        for(int i=0;i<s.length();i++){
            max=Math.max(hashmap.get(s.charAt(i)),max);
            if(max==i){
                result.add(max-prev);
                prev=max;
            }
        }
        return result;
    }
    //a good pair
    public static int[] goodPair(int[] nums,int target){
        HashMap<Integer,Integer> hashmap=new HashMap<>();
        for(int i=0;i<nums.length;i++){
            int compilment=Math.abs(nums[i]-target);
            if(hashmap.containsKey(compilment)){
                return new int[]{i+1,hashmap.get(compilment)+1};
            }
            hashmap.put(nums[i],i);
        }
        return null;
    }
    //indexOf
    public static int indexof(String s1,String s2){
        if(s2.length()>s1.length())
            return -1;
        int len=s2.length();
        int end=0;
        while(end<=s1.length()-s2.length()){
            int start=0;
            while(start<len && s1.charAt(start+end)==s2.charAt(start)){
                start++;
            }
            if(start==len){
                return end;
            }else{
                end+=start+1;
            }
        }
        return -1;
    }
    //Spiral matrix
    public static List<Integer> spiralMatrix(int[][] arr){
        int top=0;
        int bottom=arr.length-1;
        int left=0;
        int right=arr[0].length-1;
        List<Integer> result=new ArrayList<>();
        int len=arr.length*arr[0].length;
        while(result.size()<len){
            for(int i=left;i<=right&& result.size()<len;i++){
                result.add(arr[top][i]);
            }
            top++;
            for(int i=top;i<=bottom && result.size()<len;i++){
                result.add(arr[i][right]);
            }
            right--;
            for(int i=right;i>=left && result.size()<len;i--){
                result.add(arr[bottom][i]);
            }
            bottom--;
            for(int i=bottom;i>=top&& result.size()<len;i--){
                result.add(arr[i][left]);
            }
            left++;
        }
        return result;
    }
    //Crack the code
    public static boolean crackCode(String s1,String s2){
        if(s1.length()==0 && s2.length()==0){
            return true;
        }
        if(s1.length()==0 || s2.length()==0)
            return false;
        HashMap<Character,String> hashmap=new HashMap<>();
        String[] words=s1.split(" ");
        char[] charArray=s2.toCharArray();
        if(words.length!=charArray.length)
            return false;
        for(int i=0;i<words.length;i++){
            String current=words[i];
            char c=charArray[i];
            if(!hashmap.containsKey(c)){
                hashmap.put(c,current);
            }else if(!hashmap.get(c).equals(current)){
                return false;
            }
        }
        return hashmap.keySet().size() ==s2.chars().distinct().count();
    }
    //Max Sum
    public static int maxSum(BST node){
        if(node==null)
            return 0;
       int[] result=maxSumHelper(node);
       return result[1];
    }
    public static int[] maxSumHelper(BST node){
        if(node==null){
            int[] empty={0,Integer.MIN_VALUE};
            return empty;
        }
        int[] leftSum=maxSumHelper(node.left);
        int[] rightSum=maxSumHelper(node.right);
        int left=Math.max(leftSum[0],0);
        int right=Math.max(rightSum[0],0);
        int currentSum=left+right+node.val;
        int maxSum=Math.max(Math.max(leftSum[1],rightSum[1]),currentSum);
        int[] result={currentSum,maxSum};
        return result;
    }
    //Migic number
    public static boolean magic(int n){
        if(n==1)
            return true;
        int num=n;
        while(num!=1 && num!=4){
            int sum=0;
            while(num>0){
                int a=num%10;
                sum+=a*a;
                num=num/10;
            }
            num=sum;
        }
        return num==1;
    }
    //Grouped Words
    public static List<List<String>> groupedWords(String[] words){
        HashMap<Integer,List<String>> hashmap=new HashMap<>();
        for(String s:words){
            int index=0;
            for(char c:s.toCharArray()){
                index+='a'-c;
            }
                List<String> temp=hashmap.getOrDefault(index,new ArrayList<>());
                temp.add(s);
        }
        List<List<String>> result=new ArrayList<>();
        for(Map.Entry<Integer,List<String>> entry: hashmap.entrySet()){
            result.add(entry.getValue());
        }
        List<List<String>> result1=new ArrayList<>();
        HashMap<String,List<String>> hashmap1=new HashMap<>();
        for(String word:words){
            String s=getSignature(word);
            List<String> group=hashmap1.getOrDefault(s,new ArrayList<>());
            group.add(word);
            hashmap1.put(s,group);
        }
        return new ArrayList<>(hashmap1.values());
    }
    public static String getSignature(String s){
        if(s.length()==0){
            return new String("zero");
        }
        int[] arr=new int[26];
        for(char c:s.toCharArray()){
            arr[c-'a']++;
        }
        StringBuilder sb=new StringBuilder();
        for(int i=0;i<26;i++){
            if(arr[i]>0){
                sb.append((char)('a'+i));
                sb.append(arr[i]);
            }
        }
        return sb.toString();
    }
    //Combine Time
    public static int[][] CombineTime(int[][] times){
        if(times.length==0||times==null)
            return new int[0][];
        Arrays.sort(times,(a,b)->a[0]-b[0]);
        List<int[]> result=new ArrayList<>();
        int[] temp=times[0];
        for(int[] current:times){
            if(current[0]<=temp[1]){
                temp[1]=Math.max(current[1],temp[1]);
            }else if(current[0]>temp[1]){
                result.add(current);
                temp=current;
            }
        }
        result.add(temp);
        return result.toArray(new int[result.size()][]);
    }
    //Largest iSland
    public static int largestIsland(int[][] grid){
        if(grid.length==0||grid==null)
            return 0;
        int result=0;
        for(int i=0;i<grid.length;i++){
            for(int j=0;j<grid[0].length;j++){
                if(grid[i][j]==1){
                    result=Math.max(largestIslandHelper(grid,i,j),result);
                }
            }
        }
        return result;
    }
    public static int largestIslandHelper(int[][] grid,int i,int j){
        if(i<0||i>=grid.length||j<0||j>=grid[0].length||grid[i][j]==0){
            return 0;
        }
        grid[i][j]=0;
        return 1+largestIslandHelper(grid,i-1,j)+largestIslandHelper(grid,i+1,j)+largestIslandHelper(grid,i,j-1)+largestIslandHelper(grid,i,j+1);
    }
    //To The Third Power
    public static boolean isThirdPower(int n){
        if(n==0)
            return true;
        return n%3==0;
    }
    
//    Minimum Removal
    public static List<String> miniRemoval(String s){
        int removeopen=0,removeclose=0;
        for(char c:s.toCharArray())
        {
            if(c=='('){
                removeopen++;
            }else if(c==')'){
                if(removeopen>0){
                    removeopen--;
                }else{
                    removeclose++;
                }
            }
        }
        HashSet<String> hashset=new HashSet<>();
        miniRemovalhelper(s,0,removeopen,removeclose,hashset,0,new StringBuilder());
        return new ArrayList<>(hashset);
    }
    public static void miniRemovalhelper(String s,int index,int opencount,int closecount,HashSet<String> hashset,int count,StringBuilder result){
        if(opencount==0&&closecount==0&&count==0&&index==s.length()){
            hashset.add(result.toString());
            return;
        }
        if(opencount<0 || closecount<0|| count<0 ||index>=s.length())
            return;
        if(s.charAt(index)=='('){
            miniRemovalhelper(s,index+1,opencount-1,closecount,hashset,count,result);
            miniRemovalhelper(s,index+1,opencount,closecount,hashset,count+1,result.append('('));
        }else if(s.charAt(index)==')'){
            miniRemovalhelper(s,index+1,opencount,closecount-1,hashset,count,result);
            miniRemovalhelper(s,index+1,opencount,closecount,hashset,count-1,result.append(')'));
        }else{
            miniRemovalhelper(s,index+1,opencount,closecount,hashset,count,result);
        }
        result.setLength(result.length()-1);
    }
//    High Frequency Trading
    public int maxProfit(int[] prices) {
        int profit=0;
        int min=Integer.MAX_VALUE;
        for(int i=0;i<prices.length;i++){
            if(min>prices[i]){
                min=prices[i];
            }else{
                profit=Math.max(profit,prices[i]-min);
            }
        }
        return profit;
    }
    //Largest Pool
    public static int largestPool(int[] pool){
        if(pool.length==0)
            return 0;
        int left=0;
        int right=pool.length-1;
        int max=Integer.MIN_VALUE;
        while(left<right){
            max=Math.max(max,(Math.min(pool[left],pool[right]))*(right-left));
            if(pool[left]<pool[right])
                left++;
            else
                right--;
        }
        return max;
    }
    //Sore Thumb
    public static int[] soreThumb(int[] nums) {
        if (nums.length == 0) {
            return new int[0];
        }

        HashSet<Integer> soreThumbs = new HashSet<>();

        for (int i = 1; i < nums.length - 1; i++) {
            if (nums[i] > nums[i - 1] && nums[i] > nums[i + 1]) {
                soreThumbs.add(i);
            }
        }

        // Check for sore thumbs at the first and last index
        if (nums[0] > nums[1]) {
            soreThumbs.add(0);
        }
        if (nums[nums.length - 1] > nums[nums.length - 2]) {
            soreThumbs.add(nums.length - 1);
        }

        return soreThumbs.stream()
                .mapToInt(Integer::intValue)
                .toArray();
    }
    //Trim Tree
    public static List<BST> trimTree(BST node,int[] dead){
        List<BST> result=new ArrayList<>();
        List<Integer> deadList=new ArrayList<>();
        for (int i:dead){
            deadList.add(i);
        }
        if(!deadList.contains(node.val)){
            result.add(node);
        }
        trimTreeHelper(node,deadList,result);
        return result;
    }
    public static BST trimTreeHelper(BST node,List<Integer> deadList,List<BST> result){
        if(node==null)
            return null;
        node.left=trimTreeHelper(node.left,deadList,result);
        node.right=trimTreeHelper(node.right,deadList,result);
        if(deadList.contains(node.val)){
            if(node.left!=null && !deadList.contains(node.left.val)){
                result.add(node.left);
            }
            if(node.right!=null && !deadList.contains(node.right.val)){
                result.add(node.right);
            }
        }
        return node;
    }
    //trim the bst with the highest and the lowest value
    public static BST trimBST(BST node ,int[] values){
        if(node==null)
            return null;
        if(node.val<values[0]){
            return trimBST(node.left,values);
        }
        if(node.val>values[1]){
            return trimBST(node.right,values);
        }
        node.left=trimBST(node.left,values);
        node.right=trimBST(node.right,values);
        return node;
    }
    public static void preOrder(BST node){
        if(node==null)
            return;
        preOrder(node.left);
        System.out.println(node.val);
        preOrder(node.right);
    }
    //Triplets
    public static List<List<Integer>> triplets(int[] arr){
        if(arr.length<=2){
            return new ArrayList<>();
        }
        Arrays.sort(arr);
        List<List<Integer>> result=new ArrayList<>();

        for(int i=0;i<arr.length-2;i++){
            if(i==0||(i>0&&arr[i]!=arr[i-1])){
                int j=i+1;
                int k=arr.length-1;
                int sum=0-arr[i];
                while(j<k){
                    if(sum==arr[j]+arr[k]){
                        result.add(Arrays.asList(arr[i],arr[j],arr[k]));
                        while(j<k && arr[j]==arr[j+1]){
                            j=j+1;
                        }
                        while(j<k && arr[k]==arr[k-1]){
                            k=k-1;
                        }
                        j++;
                        k--;
                    }else if(sum<arr[j]+arr[k]){
                        k--;
                    }else{
                        j++;
                    }
                }
            }
        }
        return result;
    }
    //Expensive Inventory
    public static int expensiveInventory(int[] values,int[] label,int wanted,int limit){
        if(values.length==0||label.length==0||wanted==0||limit==0)
            return 0;
        HashMap<Integer,Integer> hashmap=new HashMap<>();
        for(int i=0;i<label.length;i++){
            if(hashmap.containsKey(label[i])){
                int max=hashmap.get(label[i]);
                if(max<values[i]){
                    hashmap.put(label[i], values[i]);
                }
            }else{
                hashmap.put(label[i],values[i]);
            }
        }
        List<Map.Entry<Integer,Integer>> sortedValues=new ArrayList<>(hashmap.entrySet());
        Collections.sort(sortedValues, (a, b) -> b.getValue().compareTo(a.getValue()));
    HashMap<Integer,Integer> labelCount= new HashMap<Integer,Integer>();

        int current=0;
        int result=0;
        for(Map.Entry<Integer,Integer> map:sortedValues){
            if(labelCount.getOrDefault(map.getKey(),0)<limit && current<wanted){
                result+=map.getValue();
                labelCount.put(map.getKey(), labelCount.getOrDefault(map.getKey(),0)+1);
                current++;
            }
        }
    return result;
    }
    public static String getColumnTitle(int n) {
        StringBuilder columnTitle = new StringBuilder();

        while (n > 0) {
            int remainder = (n - 1) % 26;
            char ch = (char) ('A' + remainder);
            columnTitle.insert(0, ch);
            n = (n - 1) / 26;
        }

        return columnTitle.toString();
    }
    //Minimum Rotations
//    public static int MinimumRotations(int[] top,int[] bottom){
//        int[] count=new int[top.length];
//        for(int i:top){
//            count[i-1]++;
//        }
//        for(int i:bottom){
//            count[i-1]++;
//        }
//        boolean solution=false;
//        int value=-1;
//        for(int i=0;i<count.length;i++){
//            if(count[i]==6){
//                solution=true;
//                value=i;
//            }
//        }
//        List<Integer> topPos=new ArrayList<>();
//        List<Integer> bottomPosition=new ArrayList<>();
//        for(int i=0;i<top.length;i++){
//            if(top[i]==value){
//                topPos.add(i);
//            }
//            if(bottom[i]==value){
//                bottomPosition.add(i);
//            }
//        }
//        boolean result=false;
//        if(solution){
//            if(topPos.size()>bottomPosition.size()){
//                for(int i:topPos){
//                    swaping(top,bottom,i);
//                    result=checking(top,value);
//                }
//                if(result)
//                    return top.length-topPos.size();
//            }else{
//                for(int i:bottomPosition){
//                    swaping(bottom,top,i);
//                    result=checking(bottom,value);
//                }
//                if(result)
//                    return top.length-bottomPosition.size();
//            }
//        }
//        return -1;
//    }
//    public static void swaping(int[] a,int[] b,int loc){
//        int temp=a[loc];
//        a[loc]=b[loc];
//        b[loc]=temp;
//    }
//    public static boolean checking(int[] arr,int value){
//        for(int i:arr){
//            if(i!=value && i!=0)
//                return false;
//        }
//        return true;
//    }
    public static int MinimumRotations(int[] top,int[] bottom){
        HashMap<Integer,Integer> topRow=getOccurance(top);
        HashMap<Integer,Integer> bottomRow=getOccurance(bottom);
        int resultTop = checkPossibility(top, topRow, bottomRow);
        if (resultTop != -1) {
            return resultTop;
        }

        // Check if it is possible to make all values in the bottom row the same
        int resultBottom = checkPossibility(bottom, topRow, bottomRow);
        if (resultBottom != -1) {
            return resultBottom;
        }

        return -1;
    }
    public static HashMap<Integer,Integer> getOccurance(int[] arr){
        HashMap<Integer,Integer> hashmap=new HashMap<>();
        for(int i:arr){
            hashmap.put(i,hashmap.getOrDefault(i,0)+1);
        }
        return hashmap;
    }
    private static int checkPossibility(int[] row, Map<Integer, Integer> countTop, Map<Integer, Integer> countBottom) {
        int minRotations = Integer.MAX_VALUE;
        for (int i = 1; i <= 6; i++) {
            if (countTop.containsKey(i) && countTop.get(i) + countBottom.getOrDefault(i, 0) >= row.length) {
                int rotations = row.length - Math.max(countTop.get(i), countBottom.getOrDefault(i, 0));
                minRotations = Math.min(minRotations, rotations);
            }
        }
        return minRotations == Integer.MAX_VALUE ? -1 : minRotations;
    }
    //Tree Width
    public static int widthTree(BST node){
        if(node==null)
            return 0;
        int left=widthTree(node.left);
        int right=widthTree(node.right);
        int max=Math.max(left,right);
        return 1+max;
    }
    //    Language Encryption
    public static boolean lexi(String[] arr){
        if(arr.length==0)
            return true;
        for(int i=0;i<arr.length-1;i++){
            if(arr[i].compareTo(arr[i+1])>0)
                return false;
        }
        return true;
    }
    public static boolean languageEncryption(String alphabet,String[] arr){
        if(arr.length==0)
            return true;
        HashMap<Character,Integer> hashset=new HashMap<>();
        int j=0;
        for(char c:alphabet.toCharArray()){
            hashset.put(c,j++);
        }
        for(int i=1;i<arr.length;i++){
            String prev=arr[i-1];
            String current=arr[i];
            int len=Math.min(prev.length(),current.length());
            for(int k=0;k<len;k++){
                if(hashset.get(prev.charAt(k))>hashset.get(current.charAt(k))){
                    return false;
                }
            }
            if(prev.length()>current.length())
                return false;
        }
        return true;
    }
    //find the result
    public static int[] resultFind(int[] arr,int target){
        int start=0,end=0;
        int sum=0;
        while(end<arr.length){
            sum+=arr[end];
            if(sum>target){
                sum-=arr[start];
                start++;
            }
            if(sum==target)
                return new int[]{start,end};
        }
        return new int[0];
    }
    //    K Distinct Character
    public static int distinctK(String s,int limit){
        if(s.length()==0 || limit==0)
            return 0;
        int start=0,end=0;
        HashMap<Character,Integer> hashmap=new HashMap<>();
        int length=0;
        while(end<s.length()){
            char c=s.charAt(end);
            hashmap.put(c,hashmap.getOrDefault(c,0)+1);
            while(hashmap.size()>limit){
                char c1=s.charAt(start);
                hashmap.put(c1, hashmap.get(c1)-1);
                if(hashmap.get(c1)==0)
                    hashmap.remove(c1);
                start++;
            }
            length=Math.max(length,end-start+1);
            end++;
        }
        return length;
    }
    //Sum to Target
    public static List<List<Integer>> sumtoTarget(int[] nums,int target){
        List<List<Integer>> result=new ArrayList<>();
        if(target==0)
            return result;
        sumtoTargethelper(nums,target,new ArrayList<>(),result,0);
        return result;
    }
    public static void sumtoTargethelper(int[] nums,int target,List<Integer> temp,List<List<Integer>> result,int index){
        if(target==0){
            result.add(new ArrayList<>(temp));
        }
        if(target<0){
            return;
        }
        for(int i=index;i<nums.length;i++){
            temp.add(nums[i]);
            sumtoTargethelper(nums,target-nums[i],temp,result,i+1);
            temp.remove(temp.size()-1);
        }
    }
    //work Schedule
    public static boolean workSch(int[][] times){
        if(times.length==0)
            return true;
        int[] prev=times[0];
        for(int i=1;i<times.length;i++){
            int[] current=times[i];
            if(current[0]<=prev[1])
                return false;
            prev=current;
        }
        return true;
    }
    //Calling All Nodes
    public static List<Integer> callingAnodes(BST node){
        if(node==null)
            return new ArrayList<>();
        List<Integer> result=new ArrayList<>();
        Stack<BST> stack=new Stack<>();
        stack.push(node);
        while(!stack.isEmpty()){
            int size=stack.size();
            for(int i=0;i<size;i++){
                BST current=stack.pop();
                result.add(current.val);
                if(current.left!=null){
                    stack.push(current.left);
                }
                if(current.right!=null){
                    stack.push(current.right);
                }
            }
        }
        Collections.reverse(result);
        return result;
    }
//    Linked List Palindrome
    //i am thinking of two approches to solve this problem
    //approch 1
    static class Listing{
        int data;
        Listing next;
        static int size=0;
        public Listing(int data){
            this.data=data;
            next=null;
            size++;
        }
        public static int size(){
            return size;
        }

}
public static Listing reversit(Listing node){
        Listing prev=null;
        while(node!=null){
            Listing next=node.next;
            node.next=prev;
            prev=node;
            node=next;
        }
        return prev;
    }
    public static boolean isPali(Listing node) {
        if (node == null)
            return true;

        Listing slow = node;
        Listing fast = node;

        // Find the middle node of the linked list
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }

        // If the list has odd number of nodes, skip the middle node
        if (fast != null) {
            slow = slow.next;
        }

        // Reverse the second half of the linked list
        Listing reverse = reversit(slow);

        // Compare the first half with the reversed second half
        while (reverse != null) {
            if (node.data != reverse.data) {
                return false;
            }
            node = node.next;
            reverse = reverse.next;
        }
        return true;
    }

    //approch two
    public static boolean isPali2(Listing node) {
        if (node == null)
            return true;

        int size = node.size();
        int middle = size / 2;
        int current = 0;
        Listing temp = node;

        while (current < middle) {
            temp = temp.next;
            current++;
        }

        if (size % 2 == 1) {
            temp = temp.next; // Skip the middle node for odd-sized lists
        }

        Listing tempCopy = temp; // Create a copy of the second half
        Listing reverse = reversit(tempCopy);

        while (reverse != null) {
            if (node.data != reverse.data) {
                return false;
            }
            node = node.next;
            reverse = reverse.next;
        }
        return true;
    }
    //Find mid of the rotated array
    public static int findMinofRotated(int[] nums) {
        int low=0;
        int high=nums.length-1;
        while(low<high){
            int mid=low+(high-low)/2;
            if(nums[mid]>nums[high]){
                low=mid+1;
            }else{
                high=mid;
            }
        }
        return nums[low];
    }
    //STring addition
    public static String StringAdd(String num1,String num2){
        int num1l=num1.length()-1;
        int num2l=num2.length()-1;
        int carry=0;
        StringBuilder sb=new StringBuilder();
        while(num1l>=0||num2l>=0){
            int a=(num1l>=0)?num1.charAt(num1l)-'0':0;
            int b=(num2l>=0)?num2.charAt(num2l)-'0':0;
            int total=a+b+carry;
            sb.append(total%10);
            carry=total/10;
            num1l--;
            num2l--;
        }
        if(carry!=0){
            sb.append(carry);
        }
        return sb.reverse().toString();
    }
    public static Listing listInterection(Listing node1,Listing node2){
        Listing a=node1;
        Listing b=node2;
        while(a!=b){
            if(a==null){
                a=node2;
            }else{
                a=a.next;
            }
            if(b==null){
                b=node1;
            }else{
                b=b.next;
            }
        }
        return a;
    }

    //Same Value Trees
    public static int sameValue(BST tree){
        if(tree == null)
            return 0;
        return sameValueHelper(tree)[0];
    }
    public static int[] sameValueHelper(BST node){
        if(node ==null)
            return new int[]{0,-1};
        int[] leftResult=sameValueHelper(node.left);
        int[] rightResult=sameValueHelper(node.right);
        int result=leftResult[0]+rightResult[0];
        if((leftResult[1]==-1||node.val==leftResult[1])&&(rightResult[1]==-1||node.val==rightResult[1])){
            result+=1;
        }
        return new int[]{result,node.val};
    }
    //Continuous Sums
    public static int continousSum(int[] nums,int target){
        int sum=0;
        int count=0;
        HashMap<Integer,Integer> hashmap=new HashMap<>();
        hashmap.put(0,1);
        for(int i=0;i<nums.length;i++){
            sum+=nums[i];
            if(hashmap.containsKey(sum-target)){
                count+=hashmap.get(sum-target);
            }
            hashmap.put(sum,hashmap.getOrDefault(sum,0)+1);
        }
        return count;
    }
    //Harmonious Values
    public static int harmoValues(int[] nums){
        if(nums.length==0||nums==null)
            return 0;
        int result=0;
        int right=0;
        int left=0;
        Arrays.sort(nums);
        while(right<nums.length){
            int diff=nums[right]-nums[left];
            if(diff==1){
                result=Math.max(result,right-left+1);
                right++;
            }else if(diff>1){
                left++;
            }else{
                right++;
            }
        }
        return result;
    }
//    String Validity
    public static boolean stringvalidity(String s){
        if(s.length()==1 || s.length()%2!=0)
            return false;
        if(s.length()==0)
            return true;
        int open=0,close=0,mixed=0;
        for(char c:s.toCharArray()){
            if(c=='('){
                open++;
            }else if(c==')'){
                close++;
            }else if(c=='*'){
                mixed++;
            }else{
                return false;
            }
        }
        open=open+close;
        return Math.abs(open-mixed)==0;
    }
//    Common Values
    public static int[] commonValues(int[] num1,int[] num2){
        if(num1.length==0&&num2.length==0)
            return new int[0];
        int pointer1=0,pointer2=0;
        int[] result=new int[num1.length];
        int i=0;
        while(pointer1<num1.length&&pointer2<num2.length){
            if(num1[pointer1]==num2[pointer2]){
                result[i]=num1[pointer1];
                i++;
                pointer1++;
                pointer2++;
            }else if(num1[pointer1]-num2[pointer2]>0){
                pointer2++;
            }else{
                pointer1++;
            }
        }
        return result;
    }
    //Microsoft Paint
    public static int[][] microsoftPaint(int[][] image,int row,int col,int color){
        if(image[row][col]==color)
            return image;
        if(image.length==0||image==null)
            return image;
        microsoftPainthelper(image,row,col,color,image[row][col]);
        return image;
    }
    //Most Popular
    public static String mostpopular(String s,String[] popular){
        if(popular.length==0)
            return new String("");
        String[] words=s.split("[^a-zA-Z]+");
        HashMap<String,Integer> hashmap=new HashMap<>();
        for(String str:words){
            String word=str.toLowerCase();
            hashmap.put(word,hashmap.getOrDefault(word,0)+1);
        }
        List<Map.Entry<String,Integer>> hashmaps=new ArrayList<>(hashmap.entrySet());
        Collections.sort(hashmaps,new Comparator<Map.Entry<String,Integer>>(){
                public int compare(Map.Entry<String,Integer> map1,Map.Entry<String,Integer> map2){
                    return map1.getValue().compareTo(map2.getValue());
        }
        });
    for(String st:popular){
        if(hashmaps.contains(st)){
            hashmaps.remove(st);
        }
    }
    return hashmaps.get(hashmaps.size()-1).getKey();
    }
    public static void microsoftPainthelper(int[][] image,int row,int col,int color,int old){
        if(row>=0&&row<image.length&&col>=0&&col<image[0].length&&image[row][col]==old)
        {
            image[row][col]=color;
            microsoftPainthelper(image, row - 1, col, color, old);
            microsoftPainthelper(image, row + 1, col, color, old);
            microsoftPainthelper(image, row, col - 1, color, old);
            microsoftPainthelper(image, row, col + 1, color, old);
        }
    }
    public static void printpaint(int[][] arr){
        for(int[] i: arr){
            System.out.println(Arrays.toString(i));
        }
    }
    public static int finalDigit(int num){
        if(num ==0)
            return 0;
        int current=num;
        while(current%10!=current){
            int temp =current;
            int sum=0;
            while(temp!=0){
                int t=temp%10;
                sum+=t;
                temp=temp/10;
            }
            current=sum;
        }
        return current;
    }
    //Missing Numbers
    public static int missingNumber(int[] nums){
        int total=0;
        for(int i:nums){
            total+=i;
        }
        int[] actual=new int[nums.length];
        int sum=0;
        for(int i=0;i<nums.length;i++){
            actual[i]=i+1;
            sum+=i+1;
        }
        int index=nums.length-(sum-total);
        return index==nums.length?-1:actual[index];
    }
    //Theft
    public static int theft(int[] nums){
        if(nums.length==0||nums==null)
            return 0;
        int[] dp=new int[nums.length+1];
        dp[0]=0;
        dp[1]=nums[0];
        for(int i=1;i<nums.length;i++){
            dp[i+1]=Math.max(dp[i],dp[i-1]+nums[i]);
        }
        return dp[nums.length];
    }
    //atios
    public static int atios(String s){
        if(s.length()==0)
            return 0;
        int length=s.length();
        int result=0;
        int sign=0;
        int start=0;
        while(s.charAt(start)==' ' && start<length){
            start++;
        }
        if(s.charAt(start)=='-'&&start<length){
            sign=(s.charAt(start)=='-')?-1:1;
            start++;
        }
        while(start<length){
            int element=s.charAt(start)-'0';
            if(result>Integer.MAX_VALUE/10||(result==Integer.MAX_VALUE && element>Integer.MAX_VALUE%10))
                return (sign == -1) ? Integer.MIN_VALUE : Integer.MAX_VALUE;
            result=result*10+element;
            start=start+1;
        }
        return result;
    }
    public String countAndSay(int n) {
        if(n==1){
            return "1";
        }
        String prev=countAndSay(n-1);
        int count=1;
        StringBuilder sb=new StringBuilder();
        for(int i=0;i<prev.length();i++){
            char current=prev.charAt(i);
            if(i<prev.length()-1 && prev.charAt(i+1)==current){
                count++;
            }else{
                sb.append(count).append(current);
                count=1;
            }
        }
        return sb.toString();
    }
    public static List<List<Integer>> premu(int[] nums){
        List<List<Integer>> result=new ArrayList<>();
        if(nums.length==0)
            return result;
        List<Integer> temp=new ArrayList<>();
        for(int i:nums){
            temp.add(i);
        }
        premuHelper(result,temp,0);
        return result;
    }
    public static void premuHelper(List<List<Integer>> result,List<Integer> temp,int index){
        if(index==temp.size()){
            result.add(new ArrayList<>(temp));
            return;
        }
        for(int i=index;i<temp.size();i++){
            Collections.swap(temp,index,i);
            premuHelper(result,temp,index+1);
            Collections.swap(temp,index,i);
        }
    }
//    53. Maximum Subarray
    //bruteforce
    public static int bfmaxSubArray(int[] nums){
        if(nums.length==0)
            return 0;
        int result=nums[0];
        for(int i=0;i<nums.length;i++){
            int total=0;
            for(int j=i;j<nums.length;j++){
                total+=nums[j];
                result=Math.max(result,total);
            }
        }
        return result;
    }
    public static int maxSubArray(int[] nums){
        if(nums.length==0)
            return 0;
        int[] dp=new int[nums.length];
        dp[0]=nums[0];
        int result=nums[0];
        for(int i=1;i<nums.length;i++){
            dp[i]=Math.max(nums[i],nums[i]+dp[i-1]);
            result=Math.max(result,dp[i]);
        }
        return result;
    }
    //Jump Game
    public static boolean jumGame(int[] nums){
        if(nums==null || nums.length==0|| nums.length==1)
            return true;
        boolean[] dp=new boolean[nums.length];
        dp[0]=true;
        for(int i=0;i<nums.length;i++){
            if(dp[i]){
                for(int j=1;j<=nums[i] && i+j<nums.length;j++){
                    dp[i+j]=true;
                }
            }
        }
        return dp[nums.length-1];
    }
    public static boolean jupGame2(int[] nums){
        if(nums==null||nums.length==0)
            return true;
        int target=nums.length-1;
        for(int i=nums.length-1;i>=0;i--){
            if(nums[i]+i>=target){
                target=i;
            }
        }
        return target==0;
    }
    //decode ways
    //bruteforce way to solve the problem
    public static int decodeWay(String s){
        return decodewayHelper(s,0);
    }
    public static int decodewayHelper(String s,int index){
        if(index==s.length()){
            return 1;
        }
        if(s.charAt(index)=='0')
            return 0;
        int count=0;
        count+=decodewayHelper(s,index+1);
        if(index<s.length()-1){
            int twodigit=Integer.parseInt(s.substring(index,index+2));
            if(twodigit>=10&&twodigit<=26){
                count+=decodewayHelper(s,index+2);
            }
        }
        return count;
    }
    //dynamic programming way
    public static int decodenoway(String s){
        if(s==null||s.length()==0)
            return 0;
        int[] dp=new int[s.length()+1];
        dp[0]=1;
        dp[1]=s.charAt(0)=='0'?0:1;
        for(int i=2;i<s.length();i++){
            int one=Integer.valueOf(s.substring(i-1,i));
            int two=Integer.parseInt(s.substring(i-2,i));
            if(one>=1){
                dp[i]+=dp[i-1];
            }
            if(two>=10&& two<=26){
                dp[i]+=dp[i-2];
            }
        }
        return dp[s.length()];
    }
//    Construct Binary Tree from Preorder and Inorder Traversal
class constructSolution {
    private int index=0;

    public BST buildTree(int[] preorder, int[] inorder) {
        if(preorder==null||preorder.length==0||inorder==null||inorder.length==0){
            return null;
        }
        HashMap<Integer,Integer> hashmap=new HashMap<>();
        for(int i=0;i<inorder.length;i++){
            hashmap.put(inorder[i],i);
        }
        return helper(preorder,0,preorder.length-1,hashmap);
    }
    public BST helper(int[] preorder,int left,int right,HashMap<Integer,Integer> hashmap){
        if(left>right){
            return null;
        }
        BST node=new BST(preorder[index]);
        int position=hashmap.get(preorder[index]);
        index++;
        node.left=helper(preorder,left,position-1,hashmap);
        node.right=helper(preorder,position+1,right,hashmap);
        return node;
    }
}
//we are going to cinstruct a BST from sorted array using iteration method;
    static class dhoni{
        int left;
        int right;
        BST node;
        public dhoni(int left,int right,BST node){
            this.left=left;
            this.right=right;
            this.node=node;
        }
}
    public static int getMiddle(int low,int high){
        return low+(high-low)/2;
    }
    public static BST sortedBST(int[] nums){
        Stack<dhoni> stack=new Stack<>();
        BST node=new BST(nums[getMiddle(0,nums.length-1)]);
        dhoni csk=new dhoni(0,nums.length-1,node);
        stack.push(csk);
        while(!stack.isEmpty()){
            dhoni temp=stack.pop();
            int temp_left=temp.left;
            int temp_rigth=temp.right;
            BST nodes=temp.node;
            int mid=getMiddle(temp_left,temp_rigth);
            if(temp_left<=mid-1){
                nodes.left=new BST(nums[getMiddle(temp_left,mid-1)]);
                stack.push(new dhoni(temp_left,mid-1,nodes.left));
            }
            if(mid+1<=temp.right){
                nodes.right=new BST(nums[getMiddle(mid+1,temp_rigth)]);
                stack.push(new dhoni(mid+1,temp_rigth,nodes.right));
            }
        }
        return node;
    }
    public static void preOrderBST(BST node, List<Integer> result) {
        if (node == null) {
            return;
        }
        result.add(node.val);
        preOrderBST(node.left, result);
        preOrderBST(node.right, result);
    }
    // Populating Next Right Pointers in Each Node
    static class BST_1{
        BST_1 left,right,next;
        int val;
        public BST_1(BST_1 left,BST_1 right,int val){
            this.left=left;
            this.right=right;
            this.val=val;
            next=null;
        }
    }
    public static BST_1 popRightPointer(BST_1 node){
        if(node==null){
            return null;
        }
        Queue<BST_1> queue=new LinkedList<>();
        queue.offer(node);
        while(!queue.isEmpty()){
            int size=queue.size();
            BST_1 prev=null;
            for(int i=0;i<size;i++){
                BST_1 current=queue.poll();
                if(prev!=null){
                    prev.next=current;
                }
                if(current.left!=null){
                    queue.offer(current.left);
                }
                if(current.right!=null){
                    queue.offer(current.right);
                }
                prev=current;
            }
        }
        return node;
    }
    //using recurion
    public static BST_1 connect(BST_1 node){
        if(node==null){
            return null;
        }
        connectHelper(node);
        return node;
    }
    public static void connectHelper(BST_1 node){
        if(node.left==null||node.right==null)
            return;
        node.left.next=node.right;
        if(node.next!=null){
            node.right.next=node.next.left;
        }
        connectHelper(node.left);
        connectHelper(node.right);
    }
    //problem related to Binary Serach Tree
    //target sum from root to leaf
    //using recursion
    public static boolean taregtBST(BST node,int target){
        if(node == null){
            return false;
        }
        return targetBSTHelper(node,target);
    }
    public static boolean targetBSTHelper(BST node,int target){
        if(node==null)
            return false;
        if(target==node.val&&node.left==null&&node.right==null){
            return true;
        }
        return targetBSTHelper(node.left,target-node.val)||targetBSTHelper(node.right,target-node.val);
    }
    //solving the same problem using iteration
    public static boolean targetBST(BST node,int target){
        if(node==null)
            return false;
        Stack<BST> stack=new Stack<>();
        Stack<Integer> numbers=new Stack<>();
        numbers.push(node.val);
        stack.push(node);
        while(!stack.isEmpty()){
            BST temp=stack.pop();
            int current_val=numbers.pop();
            if(temp.val==current_val &&temp.left==null&&temp.right==null){
                return true;
            }
            if(temp.left!=null){
                numbers.push(target-temp.val);
                stack.push(temp.left);
            }
            if(temp.right!=null){
                numbers.push(target-temp.val);
                stack.push(temp.right);
            }
        }
        return false;
    }
    //Binary Tree Maximum Path Sum
//    static int tree_max_path_sum=0;
    public static int maxPathSum(BST node){
        if(node==null)
            return 0;
        int[] result=new int[1];
        maxPathSumHelper(node,result);
        return result[0];
    }
    public static int maxPathSumHelper(BST node,int[] result){
        if(node==null){
            return 0;
        }
        int left=Math.max(0,maxPathSumHelper(node.left,result));
        int right=Math.max(0,maxPathSumHelper(node.right,result));
        int current=left+right+node.val;
        result[0]=Math.max(result[0],current);
        return node.val+Math.max(left,right);
    }
    //flatteren HashMap
    public static HashMap<String,String> faltHashMap(HashMap<String,Object> hashmap){
        HashMap<String,String>result=new HashMap<>();
        if(hashmap==null||hashmap.size()==0){
            return result;
        }
        flatHashMapHelper("",hashmap,result);
        return result;
    }
    public static void flatHashMapHelper(String prefix,HashMap<String,Object> hashmap,HashMap<String,String> result){
        for(Map.Entry<String,Object> entry:hashmap.entrySet()){
            String key=entry.getKey();
            Object value=entry.getValue();
            if(value instanceof String){
                String current=prefix.isEmpty()?key:(prefix+"."+key);
                result.put(current,(String)value);
            } else if (value instanceof Object) {
                if(prefix.isEmpty()){
                    flatHashMapHelper(key,(HashMap<String, Object>) value,result);
                }else{
                    flatHashMapHelper(prefix+"."+key,(HashMap<String, Object>) value,result);
                }
            }
        }
    }
    //next permutation
    public static int[] nextPermutation(int[] nums) {
        int n = nums.length;

        // Step 1: Find the first pair of adjacent elements where the left element is smaller than the right element
        int i = n - 2;
        while (i >= 0 && nums[i] >= nums[i + 1]) {
            i--;
        }

        // Step 2: If no such pair is found, reverse the entire sequence
        if (i < 0) {
            reverse(nums, 0, n - 1);
            return nums;
        }

        // Step 3: Find the smallest element to the right of index "i" that is larger than the element at index "i"
        int j = n - 1;
        while (j > i && nums[j] <= nums[i]) {
            j--;
        }

        // Step 4: Swap the elements at indices "i" and "j"
        swap(nums, i, j);

        // Step 5: Reverse the subsequence starting from index "i+1" to the end of the sequence
        reverse(nums, i + 1, n - 1);
    return nums;
    }

    private static void reverse(int[] nums, int start, int end) {
        while (start < end) {
            swapi(nums, start, end);
            start++;
            end--;
        }
    }

    private static void swapi(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }
    //all permutation
    public static List<int[]> combiint(int[] nums){
        List<int[]> result=new ArrayList<>();
        if(nums==null||nums.length==0)
            return result;
        combiintHelper(nums,0,result);
        return result;
    }
    public static void combiintHelper(int[] nums,int i,List<int[]> result){
        if(i<nums.length){
            result.add(Arrays.copyOf(nums, nums.length));
        }
        for(int j=i;j<nums.length;j++){
            swapp(nums,i,j);
            combiintHelper(nums,i+1,result);
            swapp(nums,i,j);
        }
    }
    public static void swapp(int[] nums,int i,int j){
        int temp=nums[i];
        nums[i]=nums[j];
        nums[j]=temp;
    }
    //different ways of merging mutiple array into single array
    //approch -1
    public static int[] mergearr1(int[][] nums){
        if(nums==null||nums.length==0)
            return new int[0];
        PriorityQueue<Integer> pq=new PriorityQueue<>((a,b)->a-b);
        for(int i=0;i<nums.length;i++){
            for(int j=0;j<nums[i].length;j++){
                pq.add(nums[i][j]);
            }
        }
        return pq.stream().mapToInt(Integer::intValue).toArray();
    }
    //approch-2
    public static int[] mergearr2(int[][] nums){
        if(nums==null||nums.length==0){
            return new int[0];
        }
        return mergearr2Helper(nums,0,nums.length-1);
    }
    public static int[] mergearr2Helper(int[][] nums,int low,int high){
        if(low==high){
            return nums[low];
        }
        int mid=low+(high-low)/2;
        int[] left=mergearr2Helper(nums,low,mid);
        int[] right=mergearr2Helper(nums,mid+1,high);
        return mergeit(left,right);
    }
    public static int[] mergeit(int[] left,int[] right){
        int[] result=new int[left.length+right.length];
        int i=0,j=0,k=0;
        while(i<left.length&&j<right.length){
            if(left[i]<=right[j]){
                result[k]=left[i];
                k++;
                i++;
            }else if(right[j]<left[i]){
                result[k]=right[j];
                j++;
                k++;
            }
        }
        while(i<left.length){
            result[k++]=left[i];
            i=i+1;
        }
        while(j<right.length){
            result[k++]=right[j];
            j=j+1;
        }
        return result;
    }
    //di the same for ListNode type
    public static ListNode mergeList(ListNode[] listNodes){
        if(listNodes==null||listNodes.length==0)
            return new ListNode();
        PriorityQueue<ListNode> pq=new PriorityQueue<>((a,b)->a.val-b.val);
        for(ListNode i:listNodes){
            pq.offer(i);
        }
        ListNode result=new ListNode(0);
        ListNode current=result;
        while(!pq.isEmpty()){
            ListNode temp=pq.poll();
            current.next=temp;
            current=current.next;
            if(temp.next!=null){
                pq.offer(temp.next);
            }
        }
        return result.next;
    }
    public static void printit(ListNode list){
        while(list!=null){
            System.out.println(list.val);
            list=list.next;
        }
    }
    //approch two
    public static ListNode mergeListArray(ListNode[] list){
        if(list==null||list.length==0){
            return new ListNode();
        }
        return mergeListArrayhelper(list,0,list.length-1);
    }
    public static ListNode mergeListArrayhelper(ListNode[] list,int low,int high){
        if(low==high)
            return list[low];
        int mid=low+(high-low)/2;
        ListNode left=mergeListArrayhelper(list,low,mid);
        ListNode right=mergeListArrayhelper(list,mid+1,high);
        return mergeLists(left,right);
    }
    public static ListNode mergeLists(ListNode left,ListNode right){
        ListNode result=new ListNode(0);
        ListNode dummy=result;
        while(left!=null&&right!=null){
            if(left.val<= right.val){
                dummy.next=left;
                left=left.next;
            }else{
                dummy.next=right;
                right=right.next;
            }
            dummy=dummy.next;
        }
        while(left!=null){
            dummy.next=left;
            dummy=dummy.next;
            left=left.next;
        }
        while(right!=null){
            dummy.next=right;
            dummy=dummy.next;
            right=right.next;
        }
        return result.next;
    }
    //dedetcing whether a cycle is present in the undireted graph or not
    //if present we nned to print all the cycles in the graph
    static class graph {
        static int num;
        static List<List<Integer>> vertices;

        public graph(int num) {
            vertices = new ArrayList<>(num);
            this.num = num;
            for (int i = 0; i < num; i++) {
                vertices.add(new ArrayList<>());
            }
        }

        public void addedge(int i, int j) {
            vertices.get(i).add(j);
            vertices.get(j).add(i);
        }

        public static void hasCycle(int current, boolean[] visited,List<List<Integer>> temp, List<Integer> result) {
            visited[current] = true;
            result.add(current);
            for (int i : vertices.get(current)) {
                if (!visited[i]) {
                    hasCycle(i, visited, temp, result);
                } else if (result.size()>2 &&i==result.get(0)) {
                    temp.add(new ArrayList<>(result));
                }
            }
        }
            public static List<List<Integer>> findCycle () {
                List<List<Integer>> result = new ArrayList<>();
                boolean[] visited = new boolean[num];
                for (int i = 0; i < num; i++) {
                    List<Integer> temp = new ArrayList<>();
                        hasCycle(i,visited,result,temp);
                }
                return result;
            }
        }
    public static int hIndex(int[] citations) {
        // Step 1: Sort the citations array in non-increasing order
        Arrays.sort(citations);

        int n = citations.length;
        int h = 0;

        // Step 2: Traverse the sorted array to find the h-index
        for (int i = n - 1; i >= 0; i--) {
            int papersWithAtLeastHCitations = n - i;
            if (citations[i] >= papersWithAtLeastHCitations) {
                h = Math.max(h, papersWithAtLeastHCitations);
            } else {
                // No need to check further, as the remaining papers have even fewer citations
                break;
            }
        }

        // Step 3: Return the maximum value of h as the h-index
        return h;
    }
    //Product of Array Except Self
    public static int[] productArray(int[] nums){
        if(nums==null|| nums.length==0)
            return new int[0];
        int[] result=new int[nums.length];
        int left=1,right=1;
        for(int i=0;i<nums.length;i++){
            result[i]=left;
            left=left*nums[i];
        }
        for(int j=nums.length-1;j>=0;j--){
            result[j]=result[j]*right;
            right=right*nums[j];
        }
        return result;
    }
    //Gas Station
    public static int gasStation(int[] gas,int[] cost){
        if(gas.length!=cost.length)
            return -1;
        int total=0;
        int total_gas=0;
        int total_cost=0;
        int start=0;
        for(int i=0;i<gas.length;i++){
            total=total+gas[i]-cost[i];
            total_gas+=gas[i];
            total_cost+=cost[i];
            if(total<0){
                total=0;
                start=i+1;
            }
        }
        if(total_gas<total_cost)
            return -1;
        return start;
    }
//    Candy
    public static int candy(int[] nums){
        if(nums==null||nums.length==0)
            return 0;
        int[] dp=new int[nums.length];
        Arrays.fill(dp,1);
        for(int i=1;i<nums.length;i++){
            if(nums[i]>nums[i-1]){
                dp[i]=dp[i-1]+1;
            }
        }
        for(int i=nums.length-2;i>0;i++){
            if(nums[i]>nums[i+1]){
                dp[i]=Math.max(dp[i],dp[i+1]+1);
            }
        }
        int result=0;
        for(int i:dp){
            result+=i;
        }
        return result;
    }
    //Trapping Rain Water
    public static int trapRain(int[] nums){
        if(nums==null||nums.length==0)
            return 0;
        int result=0;
        for(int i=1;i<nums.length-1;i++){
            int total=0;
            int left=i-1;
            int left_Max=0;
            int right=i+1;
            int right_Max=0;
            while(left>=0){
                left_Max=Math.max(left_Max,nums[left]);
                left=left-1;
            }
            while(right<=nums.length-1){
                right_Max=Math.max(right_Max,nums[right]);
                right=right+1;
            }
            total=Math.min(left_Max,right_Max);
            if(total>nums[i]){
                result+=total-nums[i];
            }
        }
        return result;
    }
//    Least Number of Unique Integers after K Removals
    public static int uniqueInt(int[] nums,int remove){
        HashMap<Integer,Integer> hashmap=new HashMap<>();
        for(int i:nums){
            hashmap.put(i,hashmap.getOrDefault(i,0)+1);
        }
        List<Integer> result=new ArrayList<>(hashmap.values());
        Collections.sort(result);
        int i=0;
        while(remove>=result.get(i)){
            remove=remove-result.get(i++);
            if(i==result.size())
                break;
        }
        return result.size()-i;
    }
    //Reverse Words in a String
    public static String reverseWords(String s) {
        if(s==null || s.length()==0)
            return s;
        String result="";
        String[] arr=s.trim().split("\\s+");
        System.out.println(arr.length);
        for(int i=arr.length-1;i>=0;i--){
            if(i==0){
                result+=arr[i];
            }else{
                result+=arr[i]+" ";
            }
        }
        return result;
    }
    //Zigzag Conversion
    public static String zigZag(String s,int steps){
        if(s==null||s.length()==0||steps==0){
            return s;
        }
        StringBuilder[] step=new StringBuilder[steps];
        for(int i=0;i<steps;i++){
            step[i]=new StringBuilder();
        }
        char[] c=s.toCharArray();
        int index=0;
        int length=c.length;
        while(index<length){
            for(int i=0;i<steps && index<length;i++){
                step[i].append(c[index++]);
            }
            for(int i=steps-2;i>0 && index<length;i--){
                step[i].append(c[index++]);
            }
        }
        StringBuilder result=new StringBuilder();
        for(int i=0;i<steps;i++){
            result.append(step[i]);
        }
        return result.toString();
    }
    //Substring with Concatenation of All Words
    public static List<Integer> subStringConcat(String target,String[] words){
        List<Integer> result=new ArrayList<>();
        if(target==null||target.length()==0||words==null || words.length==0)
            return result;
        HashMap<String,Integer> hashmap=new HashMap<>();
        for(String w:words){
            hashmap.put(w,hashmap.getOrDefault(w,0)+1);
        }
        int wordLength=words[0].length();
        int wordSize=words.length;
        int totalSize=wordLength*wordSize;
        for(int i=0;i<target.length()-totalSize;i++){
            HashMap<String,Integer> current=new HashMap<>();
            int j=0;
            while(j<totalSize){
                String word=target.substring(i+j,i+j+wordLength);
                if(!hashmap.containsKey(word) || current.getOrDefault(word,0)>=hashmap.get(word)){
                    break;
                }
                current.put(word,current.getOrDefault(word,0)+1);
            }
            if(j==totalSize){
                result.add(i);
            }
        }
        return result;
    }
    // Minimum Number of Arrows to Burst Balloons
    public static int burstBallon(int[][] points){
        if(points==null||points.length==0)
            return 0;
        int arrow=1;
        int currentMax=points[0][1];
        for(int i=1;i<points.length;i++){
            if(points[i][0]>currentMax){
                arrow++;
                currentMax=points[i][1];
            }
        }
        return arrow;
    }
    //Split Array into Consecutive Subsequences
    public boolean isPossible(int[] nums) {
        Map<Integer, Integer> existingSequences = new HashMap<>();
        Map<Integer, Integer> createNewSequence = new HashMap<>();

        for (int i : nums) {
            createNewSequence.put(i, createNewSequence.getOrDefault(i, 0) + 1);
        }

        for (int i : nums) {
            if (createNewSequence.get(i) == 0) {
                continue;
            }

            if (existingSequences.getOrDefault(i, 0) > 0) {
                existingSequences.put(i, existingSequences.get(i) - 1);
                existingSequences.put(i + 1, existingSequences.getOrDefault(i + 1, 0) + 1);
                createNewSequence.put(i, createNewSequence.get(i) - 1);
            } else if (createNewSequence.getOrDefault(i, 0) > 0 &&
                    createNewSequence.getOrDefault(i + 1, 0) > 0 &&
                    createNewSequence.getOrDefault(i + 2, 0) > 0) {
                createNewSequence.put(i, createNewSequence.get(i) - 1);
                createNewSequence.put(i + 1, createNewSequence.get(i + 1) - 1);
                createNewSequence.put(i + 2, createNewSequence.get(i + 2) - 1);
                existingSequences.put(i + 3, existingSequences.getOrDefault(i + 3, 0) + 1);
            } else {
                return false;
            }
        }

        return true;
    }
//    Simplify Path
    public String simplifyPath(String path) {
        path=path.trim();
        String[] str=path.split("/");
        Deque<String> result = new ArrayDeque<>();
        for(String s:str){
            if(s.equals("")||s.equals("."))
                continue;
            if(s.equals("..")){
                if(!result.isEmpty()){
                    result.removeLast();
                }
            }else{
                result.add(s);
            }
        }
        if(result.isEmpty()){
            return "/";
        }
        StringBuilder sb=new StringBuilder();
        while(!result.isEmpty()){
            sb.append("/");
            sb.append(result.removeFirst());
        }
        return sb.toString();
    }
    //150. Evaluate Reverse Polish Notation
    public static int evalRPN(String[] tokens) {
        Stack<Integer> stack=new Stack<>();
        for(String str:tokens){
            if(str.equals("+")){
                int val1=stack.pop();
                int val2=stack.pop();
                stack.push(val2+val1);
            }else if(str.equals("-")){
                int val1=stack.pop();
                int val2=stack.pop();
                stack.push(val2-val1);
            }else if(str.equals("/")){
                int val1=stack.pop();
                int val2=stack.pop();
                stack.push(val2/val1);
            }else if(str.equals("*")){
                int val1=stack.pop();
                int val2=stack.pop();
                stack.push(val1*val2);
            }else{
                stack.push(Integer.parseInt(str));
            }
        }
        return stack.peek();
    }
    public static ListNode reverseKGroup(ListNode head, int k) {
        if(head==null || k==0 ||k==1)
            return head;
        Stack<ListNode> stack=new Stack<>();
        ListNode result=new ListNode(0);
        result.next=head;
        ListNode dummy=result;
        ListNode current=head;
        int count=0;
        while(current!=null){
            count++;
            stack.push(current);
            current=current.next;
            if(count==k){
                while(!stack.isEmpty()){
                    dummy.next=stack.pop();
                    dummy=dummy.next;
                }
                dummy.next=current;
                count=0;
            }
        }
        if(count<k){
            Stack<ListNode> stack1=new Stack<>();
            while(!stack.isEmpty()){
                stack1.push(stack.pop());
            }
            while(!stack1.isEmpty()){
                dummy.next=stack1.pop();
                dummy=dummy.next;
            }
        }
        return result.next;
    }
    public static void printList(ListNode list){
        while(list!=null){
            System.out.println(list.val);
            list=list.next;
        }
    }
    //above problem with better time complexcity
    public static ListNode reverseListNode(ListNode head,int k){
        if(head==null || k==0)
            return head;
        ListNode result=new ListNode();
        result.next=head;
        ListNode dummy=result;
        int count=0;
        while(dummy.next!=null){
            count++;
            dummy=dummy.next;
        }
        dummy=result;
        while (dummy!=null){
            if(count<k){
                break;
            }
            int val=k-1;
            ListNode next=dummy.next;
            ListNode first=dummy.next;
            ListNode second=first.next;
            while(val-->0){
                ListNode temp=second.next;
                second.next=first;
                first=second;
                second=temp;
            }
            count-=k;
            dummy.next=first;
            next.next=second;
            dummy=next;
        }
        return result.next;
    }
    //61. Rotate List
    public static ListNode rotateList(ListNode node,int k){
        if(node==null || k==0)
            return node;
        int count=0;
        ListNode temp=node;
        while(temp!=null){
            count++;
            temp=temp.next;
        }
        ListNode fast=node;
        ListNode slow=node;
        k=k%count;
        if(k==0)
            return node;
        for(int i=0;i<k;i++){
            fast=fast.next;
        }
        while(fast.next!=null){
            fast=fast.next;
            slow=slow.next;
        }
        ListNode newNode=slow.next;
        slow.next=null;
        fast.next=node;
        return newNode;
    }

    //leetcode hard basic calculator


    //486. Predict the Winner
    static class pair{
        int first;
        int second;
        public pair() {}
    }
    public static boolean predictWinner(int[] arr){
        if(arr==null ||arr.length==1)
            return true;
        int n=arr.length;
        pair[][] dp=new pair[n][n];
        for(int i=0;i<n;i++){
            dp[i][i]=new pair();
            dp[i][i].first=arr[i];
            dp[i][i].second=0;
        }
        for(int length=2;length<=n;length++){
            for(int i=0;i<=n-length;i++){
                int j=i+length-1;
                dp[i][j]=new pair();
                if(arr[i]+dp[i+1][j].second>arr[j]+dp[i][j-1].second){
                    dp[i][j].first=arr[i]+dp[i+1][j].second;
                    dp[i][j].second=dp[i+1][j].first;
                }else{
                    dp[i][j].first=arr[j]+dp[i][j-1].second;
                    dp[i][j].second=dp[i][j-1].first;
                }
            }
        }
        return dp[0][n-1].first>dp[0][n-1].second;
    }
    //hacker rank DeQueue medium  Question
    //The first line of input contains two integers  n and m
    // representing the total number of integers and the size of the subarray, respectively.
    // The next line contains  space separated integers.
    public static int DequeueProblem(int limit,int[] nums){
        if(nums==null || nums.length<limit)
            return 0;
        HashMap<Integer,Integer> hashmap=new HashMap<>();
        int uniqueCount=0;
        int result=0;
        for(int i=0;i<nums.length;i++){
            hashmap.put(nums[i],hashmap.getOrDefault(nums[i],0)+1);
            if(hashmap.get(nums[i])==1){
                uniqueCount++;
            }
            if(i>=limit){
                int current=nums[i-limit];
                hashmap.put(current,hashmap.get(current)-1);
                if(hashmap.get(current)==0)
                    uniqueCount--;
            }
            if(i>=limit-1){
                result=Math.max(result,uniqueCount);
            }
        }
    return result;
    }
    //using DeQueue
    public static int usingDequeue(int limit,int[] nums){
        if(nums==null || nums.length<limit)
            return 0;
        Deque<Integer> dequeue=new LinkedList<>();
        HashSet<Integer> hashset=new HashSet<>();
        int result=0;
        for(int i=0;i<nums.length;i++){
            int element=nums[i];
            if(dequeue.size()==limit){
                int removeFirst=dequeue.removeFirst();
                if(!dequeue.contains(removeFirst)){
                    hashset.remove(removeFirst);
                }
            }
            dequeue.offer(element);
            hashset.add(element);
            if(dequeue.size()==limit){
                result=Math.max(result,hashset.size());
            }
        }
        return result;
    }
    //add Two Character
    public static int TwoCharacter(String s){
        if(s.length()<=1)
            return 0;
        int result=0;
        for(int i=0;i<s.length();i++){
            for(int j=i+1;j<s.length();j++){
                String current=formIt(s,s.charAt(i),s.charAt(j));
                if(isValidString(current)){
                    result=Math.max(result,current.length());
                }
            }
        }
        return result;
    }
    public static String formIt(String s,char a,char b){
        StringBuilder sb=new StringBuilder();
        for(char c:s.toCharArray()){
            if(c==a||c==b)
                sb.append(c);
        }
        return sb.toString();
    }
    public static boolean isValidString(String current){
        for(int i=1;i<current.length();i++){
            if(current.charAt(i)==current.charAt(i-1)){
                return false;
            }
        }
        return true;
    }
    //Weighted Uniform String
    public static List<String> weightUniform(String s,List<Integer> query){
        List<String> result=new ArrayList<>();
        if(s==null || s.length()==0)
            return result;
        HashSet<Integer> hashset=new HashSet<>();
        char prev=' ';
        int count=0;
        for(char c:s.toCharArray()){
            if(c!=prev){
                count=c-'a'+1;
                prev=c;
            }else{
                count+=c-'a'+1;
            }
            hashset.add(count);
        }
        for(int i:query){
            if(hashset.contains(i)){
                result.add("Yes");
            }else{
                result.add("No");
            }
        }
        return result;
    }
    //medium calculator
    public static int calci(String s){
        if(s==null ||s.length()==0)
            return 0;
        Stack<Integer> stack=new Stack<>();
        int result=0;
        int operator='+';
        for(int i=0;i<s.length();i++){
            char current=s.charAt(i);
            if(Character.isDigit(current)){
                result=result*10+('0'-current);
            }
            if(!Character.isDigit(current) && !Character.isSpaceChar(current) || i==s.length()-1){
                if(operator=='-'){
                    stack.push(-result);
                }
                if(operator=='+'){
                    stack.push(result);
                }
                if(operator=='*'){
                    stack.push(stack.pop()*result);
                }
                if(operator=='*'){
                    stack.push(stack.pop()/result);
                }
                operator=current;
                result=0;
            }
        }
        int total=0;
        while(!stack.isEmpty()){
            total+=stack.pop();
        }
        return total;
    }
    //Count Prime
    //my initall thought was you can simply write a helper function where your are
    // going ton check wheather a number is prime or not
    //time complexcity of the above approch is o(n * m)
    //space complexcity is o(1)
    public static int countPrime(int n){
        if(n<=1){
            return 0;
        }
        int count=0;
        for(int i=2;i<n;i++){
            if(isPrime(i)){
                count++;
            }
        }
        return count;
    }
    public static boolean isPrime(int n){
        for(int i=2;i<=Math.sqrt(n);i++){
            if(n%i==0)
                return false;
        }
        return true;
    }
    //if we follow the above approch the time complexity is more
    //by using dynamic programming we can change it to o(n+n)=o(2n)=o(n)
    public static int isPrmie2(int n){
        if(n<=1)
            return 0;
        boolean[] arr=getDpPrime(n);
        int count=0;
        for(int i=2;i<n;i++){
            if(arr[i])
                count++;
        }
        return count;
    }
    public static boolean[] getDpPrime(int n){
        boolean[] arr=new boolean[n];
        Arrays.fill(arr,true);
        arr[0]=false;
        arr[1]=false;
        for(int i=2;i*i<n;i++){
            if(arr[i]){
                for(int j=i*i;j<n;j=j+i){
                    arr[j]=false;
                }
            }
        }
        return arr;
    }
    //longest substring with k most distinct characters
    public static int longestKchar(String s,int limit){
        if(s.length()==0)
            return 0;
        HashMap<Character,Integer> hashmap=new HashMap<>();
        int end=0,start=0,result=0;
        while(end<s.length()){
            hashmap.put(s.charAt(end),hashmap.getOrDefault(s.charAt(end),0)+1);
            while(hashmap.size()>limit){
                char left=s.charAt(start);
                hashmap.put(left,hashmap.get(left)-1);
                if(hashmap.get(left)==0)
                    hashmap.remove(left);
                start++;
            }
            result=Math.max(result,end-start+1);
            end++;
        }
        return result;
    }
    //Longest Substring with At Least K Repeating Characters
    public static int longestSub(String s,int limit){
        if(s.length()==0)
            return 0;
        HashMap<Character,Integer> hashmap=new HashMap<>();
        for(char c:s.toCharArray()){
            hashmap.put(c,hashmap.getOrDefault(c,0)+1);
        }
        boolean valid=true;
        for(Map.Entry<Character,Integer> entry:hashmap.entrySet()){
            if(entry.getValue()<limit){
                valid=false;
            }
        }
        if(valid){
            return s.length();
        }
        int end=0,start=0,result=0;
        while(end<s.length()){
            if(hashmap.get(s.charAt(end))<limit){
                result=Math.max(result,longestSub(s.substring(start,end),limit));
                start=end+1;
            }
            end++;
        }
        result=Math.max(result,longestSub(s.substring(start),limit));
        return result;
    }
    //329. Longest Increasing Path in a Matrix
    public static int longestPathMatrix(int[][] matrix){
        if(matrix==null || matrix.length==0)
            return 0;
        int n=matrix.length;
        int m=matrix[0].length;
        int[][] visited=new int[n][m];
        int result=0;
        for(int i=0;i<n;i++){
            for(int j=0;j<m;j++){
                result=Math.max(result,helperlongestPathMatrix(i,j,visited,matrix,Integer.MIN_VALUE));
            }
        }
        return result;
    }
    static int[][] dir={{0,-1},{0,1},{-1,0},{1,0}};
    public static int helperlongestPathMatrix(int i,int j,int[][] visited,int[][] matrix,int prev){
        if(i<0 || i>=matrix.length || j<0 || j>=matrix[0].length ||matrix[i][j]<=prev){
            return 0;
        }
        if(visited[i][j]!=0)
            return visited[i][j];
        int result=0;
        for(int[] d:dir){
            result=Math.max(result,helperlongestPathMatrix(i+d[0],j+d[1],visited,matrix,matrix[i][j]));
        }
        visited[i][j]=result+1;
        return visited[i][j];
    }
    //239. Sliding Window Maximum
    public static int[] slidingWindow(int[] nums,int limit){
        if(nums==null || nums.length==0){
            return new int[0];
        }
        int[] result=new int[nums.length-limit+1];
        Deque<Integer> queue=new ArrayDeque<>();
        int n=nums.length;
        for(int i=0;i<nums.length;i++){
           while(!queue.isEmpty() && queue.peekFirst()<i-limit+1){
               queue.pollFirst();
           }
           while(!queue.isEmpty() && nums[queue.peekLast()]<nums[i]){
               queue.pollLast();
           }
           queue.offerLast(i);
           if(i>=limit-1){
               result[i-limit+1]=nums[queue.peekFirst()];
           }
        }
        return result;
    }
    //approch 2
    public static int[] slidingWindow2(int[] nums,int limit){
        if(nums==null || nums.length==0)
            return new int[0];
        PriorityQueue<Integer> pq=new PriorityQueue<>((a,b)->b-a);
        int[] result=new int[nums.length-limit+1];
        for(int i=0;i<nums.length;i++){
            pq.offer(nums[i]);
            if(i>=limit-1){
                result[i-limit+1]=pq.peek();
                pq.remove(nums[i-limit+1]);
            }
        }
        return result;
    }
//    Separate the Numbers
    public static void sepearteNumber(String s){
        if(s.length()==0)
            return;
        for(int i=1;i<s.length()/2;i++){
            Long first=Long.parseLong(s.substring(0,i));
            Long current=first;
            String remain=s.substring(i);
            boolean valid=true;
            while(!remain.isEmpty()){
                Long next=current+1;
                String nextStr=Long.toString(next);
                if(remain.startsWith(nextStr)){
                    current=next;
                    remain=remain.substring(nextStr.length());
                }else{
                    valid=false;
                    break;
                }
            }
            if(valid && remain.isEmpty()){
                System.out.println("YES "+first);
                return;
            }
        }
        System.out.println("NO");
    }
    public static double powMath(long x,int n){
        if(n==0)
            return 1;
        if(n<0){
            x=1/x;
            n=-(n);
        }
        return n%2==0?powMath(x*x,n/2):x*powMath(x*x,n/2);
    }
    //approch 2
    public static double powMath2(long x,int n){
        if(n==0)
            return 1;
        if(x<0){
            x=1/x;
            n=(-n);
        }
        double result=1;
        while(n>0){
            if(n%2==1){
                result*=x;
            }
            x*=2;
            n=n/2;
        }
        return result;
    }
    //implementing Djistras Algorithm
    //finding the Shortest Path from each index from start index 0
    public static Map<Integer,Integer> dj(int[][] grid){
        HashMap<Integer,Integer> result=new HashMap<>();
        if(grid==null||grid.length==0)
            return result;
        int length=grid.length;
        boolean[] visited=new boolean[length];
        int[] min=new int[length];
        Arrays.fill(min,Integer.MAX_VALUE);
        min[0]=0;
        for(int i=0;i<length-1;i++){
            int minIndex=findMinIndex(visited,min);
            System.out.println("minIndex: " + minIndex);
            visited[minIndex]=true;
            for(int j=0;j<length;j++){
                if(!visited[j] && grid[minIndex][j]!=0 && min[minIndex]!=Integer.MAX_VALUE){
                    int newDistance=min[minIndex]+grid[minIndex][j];
                        if(newDistance<min[j]){
                        min[j]=newDistance;
                        }
                    }
            }
        }
        for(int i=0;i<min.length;i++){
            result.put(i,min[i]);
        }
        return result;
    }
    public static int findMinIndex(boolean[] visited,int[] min){
        int minIndex=-1;
        for(int i=0;i<min.length;i++){
            if(!visited[i] &&(minIndex==-1 ||min[i]<min[minIndex])){
                minIndex=i;
            }
        }
        return minIndex;
    }
    //hacker rank problem for The Full Counting Sort
    public static String fullCountingString(List<List<String>> arr){
        if(arr==null ||arr.size()==0)
            return "";
        StringBuilder[] sb=new StringBuilder[100];
        for(int i=0;i<arr.size();i++){
            if(i<arr.size()/2){
                arr.get(i).set(1,"-");
            }
            int index=Integer.parseInt(arr.get(i).get(0));
            if(sb[index]==null){
                sb[index]=new StringBuilder();
            }
            sb[index].append(arr.get(i).get(1)+" ");
        }
        StringBuilder result=new StringBuilder();
        for(int i=0;i<100;i++){
            if(sb[i]!=null){
                result.append(sb[i]);
            }
        }
        return result.toString();
    }
    //word Break 2
    public static List<String> wordBreak2(String s,List<String> wordDict){
        List<String> result=new ArrayList<>();
        if(s==null || s.length()==0)
            return result;
        helperWordBreak2(result,new StringBuilder(),0,s,wordDict);
        return result;
    }
    public static void helperWordBreak2(List<String> result,StringBuilder sb,int index,String s,List<String> wordDict){
        if(index==s.length()){
            result.add(sb.toString());
            return;
        }
        for(String word:wordDict){
            if(index+word.length()<=s.length() && s.startsWith(word,index)){
                int length=sb.length();
                if(length>0){
                    sb.append(" ");
                }
                sb.append(word);
                helperWordBreak2(result,sb,index+word.length(),s,wordDict);
                sb.setLength(length);
            }
        }
    }
    //approch 2
     public static List<String> wordDict2(String s,List<String> wordDict){
        List<String> result=new ArrayList<>();
        if(s==null || s.length()==0)
            return result;
        helperwordDict2(result,s,wordDict,0,new StringBuilder());
        return result;
     }
     public static void helperwordDict2(List<String> result,String s,List<String> wordDict,int index,StringBuilder sb){
        if(index==s.length()){
            result.add(sb.toString().trim());
            return;
        }
        for(int i=index;i<s.length();i++){
            String temp=s.substring(index,i+1);
            if(wordDict.contains(temp)){
                int length=sb.length();
                sb.append(temp+" ");
                helperwordDict2(result,s,wordDict,index+temp.length(),sb);
                sb.setLength(length);
            }
        }
     }
     //2452. Words Within Two Edits of Dictionary
    public static List<String> wordWith2(String[] words,String[] dict){
        List<String> result=new ArrayList<>();
        for(String s1:words){
            for(String s2:dict){
                if(wordWith2Helper(s1,s2)){
                    result.add(s1);
                    break;
                }
            }
        }
        return result;
    }
    public static boolean wordWith2Helper(String s1,String s2){
        int count=0;
        for(int i=0;i<4;i++){
            if(s1.charAt(i)!=s2.charAt(i)){
                count++;
                if(count>2){
                    return false;
                }
            }
        }
        return true;
    }
    //433. Minimum Genetic Mutation
    public static int minGenMut(String startGene,String end,List<String> Bank){
        if(Bank.size()==0)
            return -1;
        char[] mutations = {'A', 'C', 'G', 'T'};
        Queue<String> queue=new LinkedList<>();
        HashSet<String> bank=new HashSet<>(Bank);
        queue.offer(startGene);
        int result=0;
        while(!queue.isEmpty()){
            int size=queue.size();
            for(int i=0;i<size;i++){
                String current=queue.poll();
                if(current.equals(end)){
                    return result;
                }
                char[] currentArray=current.toCharArray();
                for(int j=0;j<currentArray.length;j++){
                    char originalChar=currentArray[j];
                    for(char mute:mutations){
                        if(originalChar!=mute){
                            currentArray[j]=mute;
                            String temp=new String(currentArray);
                            if(bank.contains(temp)){
                                queue.offer(temp);
                                bank.remove(temp);
                            }
                        }
                    }
                    currentArray[j]=originalChar;
                }
            }
            result++;
        }
        return -1;
    }
    //countStaircaseRows
    public static int countStaircaseRows(int n){
        int result=0;
        int current=1;
        while(n>=current){
            n-=current;
            current++;
            result++;
        }
        return result;
    }
    //sherlockAndAnagrams HackerRank
    public static int sherlockAndAnagrams(String s){
        if(s==null || s.length()==0)
            return 0;
        int result=0;
        for(int length=1;length<s.length();length++){
            for(int i=0;i<=s.length()-length;i++){
                String temp=s.substring(i,i+length);
                char[] c1=temp.toCharArray();
                Arrays.sort(c1);
                for(int j=i+1;j<=s.length()-length;j++){
                    String temp2=s.substring(j,j+length);
                    char[] c2=temp2.toCharArray();
                    Arrays.sort(c2);
                    if(Arrays.equals(c1,c2)){
                        result++;
                    }
                }
            }
        }
        return result;
    }
    //127. Word Ladder
    public static int wordLadder(String startString,String endString,List<String> wordList){
        if(!wordList.contains(endString)){
            return 0;
        }
        HashSet<String> hashset=new HashSet<>(wordList);
        Queue<String> queue=new LinkedList<>();
        queue.offer(startString);
        int result=0;
        while(!queue.isEmpty()){
            int size=queue.size();
            for(int i=0;i<size;i++){
                String temp=queue.poll();
                char[] arr=temp.toCharArray();
                for(int k=0;k<arr.length;k++){
                    char arrChar=arr[k];
                    for(char j='a';j<='z';j++){
                        if(arrChar==j){
                            continue;
                        }
                        arr[k]=j;
                        String tempStr=String.valueOf(arr);
                        if(tempStr.equals(endString)){
                            return result+1;
                        }
                        if(hashset.contains(tempStr)){
                            hashset.remove(tempStr);
                            queue.offer(tempStr);
                        }
                    }
                    arr[k]=arrChar;
                }
                result++;
            }
        }
        return 0;
    }
    //68. Text Justification
    public static List<String> textJustification(List<String> list,int maxWidth){
        List<String> result=new ArrayList<>();
        int currentWidth=0;
        List<String> currentLine=new ArrayList<>();
        for(String str:list){
            if(currentWidth+currentLine.size()+str.length()>maxWidth){
                String justifiedLine = (textJustificationHelper(currentLine,currentWidth,maxWidth));
                result.add(justifiedLine);
                currentLine.clear();
                currentWidth=0;
            }
            currentLine.add(str);
            currentWidth+=str.length();
        }
        if(!currentLine.isEmpty()){
            String lastLine = String.join(" ", currentLine);
            lastLine += getSpacing(maxWidth - lastLine.length());
            result.add(lastLine);
        }
        return result;
    }

    public static String textJustificationHelper(List<String> currentLine,int currentWidth,int maxWidth){
        int totalSpace=maxWidth-currentWidth;
        int gaps= currentLine.size()-1;
        int baseSpace=gaps==0?totalSpace:totalSpace/gaps;
        int extraSpace=gaps==0?0:totalSpace%gaps;
        StringBuilder sb=new StringBuilder();
        for(int i=0;i<currentLine.size();i++){
            sb.append(currentLine.get(i));
            if(i<currentLine.size()-1){
                sb.append(getSpacing(baseSpace));
                if(extraSpace>0){
                    sb.append(" ");
                    extraSpace--;
                }
            }
        }
        return sb.toString();
    }
    public static String getSpacing(int n){
        StringBuilder sb=new StringBuilder();
        while(n!=0){
            sb.append(" ");
            n--;
        }
        return sb.toString();
    }
    //Modify Image
    public static int[][] modifyImage(int[][] array){
        if(array==null||array.length==0)
            return new int[0][0];
        int[][] result=new int[array.length][array[0].length];
        //-1-1 -10 -11
        //0-1 00 01
        //1-1 10 11
        int[][] dir={{-1,-1},{-1,0},{-1,1},{0,-1},{0,1},{1,-1},{1,0},{1,1}};
        for(int i=0;i<array.length;i++){
            for(int j=0;j<array[0].length;j++){
                int count=0;
                int temp=array[i][j];
                for(int[] d:dir){
                    int x=i+d[0];
                    int y=j+d[1];
                    if(x>=0&&x<array.length&&y>=0&&y<array[0].length){
                        temp+=array[x][y];
                        count++;
                    }
                }
                result[i][j]=temp/(count+1);
            }
        }
        return result;
    }
    //Bear and Steady Gene
    public static int bearAndStready(String S){
        int[] arr=new int[4];
        for(char c:S.toCharArray()){
            if(c=='A')
                arr[0]++;
            else if(c=='C')
                arr[1]++;
            else if(c=='G')
                arr[2]++;
            else if(c=='T')
                arr[3]++;
        }
        int left=0,right=0,result=S.length(),limit=S.length()/4;
        while(right<S.length()){
            int index=getIndexOfBS(S.charAt(right));
            arr[index]--;
            while(left<S.length() && arr[0]<=limit&& arr[1]<=limit&& arr[2]<=limit&& arr[3]<=limit){
                result=Math.min(result,right-left+1);
                int leftIndex=getIndexOfBS(S.charAt(left++));
                arr[leftIndex]++;
            }
            right++;
        }
        return result;
    }
    public static int getIndexOfBS(char c){
        if(c=='A'){
            return 0;
        }else if(c=='C')
            return 1;
        else if(c=='G')
            return 2;
        else if(c=='T')
            return 3;
        return -1;
    }
    //84. Largest Rectangle in Histogram
    //brute force approch
    public static int largestRect(int[] arr){
        if(arr==null||arr.length==0)
            return 0;
        if(arr.length==1)
            return arr[0];
        Arrays.sort(arr);
        int max=Integer.MIN_VALUE;
        for(int i=0;i<arr.length;i++){
            int remainingBars = arr.length - i;
            int val = arr[i] * remainingBars; // Calculate the sum directly
            max = Math.max(max, val);
        }
        return max;
    }
    //the above approch time complexcity is o(nlogn) for sorting and n for iteratuing through the array
    //we can achive o(n) with the help of Stack
    public static int largestRect2(int[] arr){
        if(arr.length==0)
            return 0;
        Stack<Integer> stack=new Stack<>();
        int max=0;
        for(int i=0;i<arr.length;i++){
            while(!stack.isEmpty() && arr[i]<arr[stack.peek()]){
                int top=arr[stack.pop()];
                int width=stack.isEmpty()?i:i-stack.peek()-1;
                max=Math.max(max,top*width);
                System.out.println(max);
            }
            stack.push(i);
        }
        while(!stack.isEmpty()){
            int top=arr[stack.pop()];
            int width=stack.isEmpty()?arr.length:arr.length-stack.peek()-1;
            max=Math.max(max,top*width);
        }
        return max;
    }
//    435. Non-overlapping Intervals
    public static int nonOver(int[][] nums){
        if(nums==null ||nums.length==0)
            return 0;
        int result=1;
        Arrays.sort(nums,(a,b)->Integer.compare(a[1],b[1]));
        int end=nums[0][1];
        for(int[] i:nums){
            if(i[0]>=end){
                result++;
                end=i[1];
            }
        }
        return nums.length-result;
    }
//    Job Sequencing Problem
    static class Job{
        int id;
        int Deadline;
        int profit;
        public Job(){}
        public Job(int id,int Deadline,int profit){
            this.id=id;
            this.Deadline=Deadline;
            this.profit=profit;
        }
}
    public static int[] jobSeq(Job[] jobs){
        Arrays.sort(jobs,new Comparator<Job>(){
            public int compare(Job job1,Job job2){
                return job2.profit-job1.profit;
            }
        });
        int length=jobs.length;
        int[] values=new int[length+1];
        int profit=0;
        int count=0;
        for(Job job:jobs){
            for(int i=job.Deadline;i>=1;i--){
                if(values[i]==0){
                    values[i]=1;
                    profit+= job.profit;
                    count++;
                    break;
                }
            }
        }
        return new int[]{profit,count};
    }
    //finding the median of the two sorted arrays
    public static double medianTwoSorted(int[] nums1,int[] nums2){
        int n=nums1.length;
        int m=nums2.length;
        int low=0;
        int high=n;
        while(low<=high){
            int partx=low+(high-low)/2;
            int party=(n+m+1)/2-partx;
            int maxLeftX=partx==0?Integer.MIN_VALUE:nums1[partx-1];
            int minRightx=partx==n?Integer.MAX_VALUE:nums1[partx];
            int maxLefty=party==0?Integer.MIN_VALUE:nums2[party-1];
            int minRighty=party==n?Integer.MAX_VALUE:nums2[party];
            if(maxLeftX<=minRighty && maxLefty<=minRightx){
                if((n+m)%2==0){
                    return (double)Math.max(maxLeftX,maxLefty)+Math.min(minRightx,minRighty);
                }else
                    return Math.max(maxLefty,maxLeftX);
            }else if(maxLeftX>minRighty){
                high=partx-1;
            }else{
                low=partx+1;
            }
        }
    throw new IllegalArgumentException();
    }
    //Shopping Spree
    public static int[] shoppingSpree(int[] prices){
        int[] result=new int[prices.length];
        Stack<Integer> stack=new Stack<>();
        for(int i=0;i<prices.length;i++){
            while(stack.isEmpty() && prices[i]<=prices[stack.peek()]){
                int index=stack.pop();
                result[index]=prices[index]-prices[i];
            }
            stack.push(i);
        }
        return result;
    }
    //finding the maximum width in the Binary Serach Tree
    public static int maxWidthBst(BST node){
        if(node==null)
            return 0;
        Queue<Integer> index=new LinkedList<>();
        Queue<BST> queue=new LinkedList<>();
        index.offer(1);
        queue.offer(node);
        int result=0;
        while(!queue.isEmpty()){
            int size=queue.size();
            int leftIndex=index.peek();
            for(int i=0;i<size;i++){
                int currentIndex=index.poll();
                BST temp=queue.poll();
                int width=currentIndex-leftIndex+1;
                result=Math.max(result,width);
                if(temp.left!=null){
                    queue.offer(temp.left);
                    index.offer(width*2);
                }
                if(temp.right!=null){
                    queue.offer(temp.right);
                    index.offer(width*2+1);
                }
            }
        }
        return result;
    }
//    All Root to Leaf Paths In Binary Tree.
    public static List<String> pathRootLeaf(BST root){
        List<String> result=new ArrayList<>();
        if(root==null)
            return result;
        pathRootLeafHelper(root,result,new StringBuilder());
        return result;
    }
    public static void pathRootLeafHelper(BST root,List<String> result,StringBuilder sb){
        if(root==null)
            return;
        sb.append(root.val);
        if(root.left==null && root.right==null){
            result.add(sb.toString());
        }else{
            sb.append(" ");
            pathRootLeafHelper(root.left,result,sb);
            pathRootLeafHelper(root.right,result,sb);
        }
        sb.setLength(sb.length()-1);
        if(root.left!=null && root.right!=null){
            sb.setLength(sb.length()-1);
        }
    }
//    String to Integer (atoi)
    public static int Atois(String s){
        if(s==null||s.length()==0)
            return 0;
        int sign=1;
        int result=0;
        int start=0;
        int length=s.length();
        //we are at the starting point where all the initial spaes are ignored
        while(start<length &&s.charAt(start)==' '){
            start++;
        }

        //we are finding whetahre the inital sign is negative or positive
        if(start<length){
            if(s.charAt(start)=='-'){
                sign=-1;
                start++;
            }
        }
        //acutual conversion starts here
        while(start<length){
            char c=s.charAt(start);
            if(c>'9'||c<'0')
                break;
            int element=c-'0';
            result=result*10+element;
            start++;
        }
        return sign*result;
    }
//    Settling Debts
    public static int settlingDebts(int[][] transactions){
        HashMap<Integer,Integer> hashmap=new HashMap<>();
        for(int[] T:transactions){
            int from=T[0];
            int to=T[1];
            int amount=T[2];
            hashmap.put(from,hashmap.getOrDefault(from,0)-amount);
            hashmap.put(to,hashmap.getOrDefault(to,0)+amount);
        }
        int[] report=new int[hashmap.size()];
        int index=0;
        for(int i:hashmap.values()){
            report[index]=i;
        }
        return settlingDebtsHelper(report);
    }
    //find the missing kth element
    public static int missKthElement(int[] nums,int k){
        int low=0;
        int high=nums.length-1;
        while(low<=high){
            int mid=low+(high-low)/2;
            int missing=nums[mid]-(mid+1);
            if(missing<k){
                low=mid+1;
            }else{
                high=mid-1;
            }
        }
        return low+k;
    }
    public static int settlingDebtsHelper(int[] report){
        int left=0;
        int right= report.length-1;
        int result=0;
        while(left<right){
            int left_amount=report[left];
            int right_amount=report[right];
            if(left_amount>right_amount){
                report[left]+=report[right];
                right--;
            }else{
                report[right]+=report[left];
                left++;
            }
            result++;
        }
        return result;
    }
//    Greedy Florist
    public static int GreddyFlorist(int[] prices,int customers){
        int result=0;
        int index=prices.length-1;
        int infilate=0;
        while(index>=0){
            for(int i=0;i<customers && index>=0;i++){
                result+=(infilate+1)*prices[index];
            }
            infilate++;
        }
        return result;
    }
    //chain size
    public static int chainSize(String[] words){
        Arrays.sort(words,(a,b)->a.length()-b.length());
        HashMap<String,Integer> hashmap=new HashMap<>();
        int result=0;
        for(String word:words){
            int count=1;
            StringBuilder sb=new StringBuilder(word);
            for(int i=0;i<word.length();i++){
                sb.deleteCharAt(i);
                String key=sb.toString();
                if(hashmap.containsKey(key)){
                    count=hashmap.get(key)+1;
                }
                result=Math.max(result,count);
                sb.insert(i,word.charAt(i));
            }
            hashmap.put(word,count);
        }
        return result;
    }
    public static int farFromLand(int[][] grid){
        if(grid.length==0)
            return 0;
        Queue<int[]> queue=new LinkedList<>();
        for(int i=0;i<grid.length;i++){
            for(int j=0;j<grid[0].length;j++){
                if(grid[i][j]==1){
                    queue.offer(new int[]{i,j,0});
                }
            }
        }
        if(!queue.isEmpty() && queue.size()==grid.length+grid[0].length){
            return 0;
        }
        int result=0;
        int[][] dir={{-1,0},{1,0},{0,1},{0,-1}};
        boolean[][] visited=new boolean[grid.length][grid[0].length];
        while(!queue.isEmpty()){
            int[] temp=queue.poll();
            int x=temp[0];
            int y=temp[1];
            int count=temp[2];
            for(int[] d:dir){
                int new_x=d[0]+x;
                int new_y=d[1]+y;
                if(new_x>=0 && new_x<grid.length && new_y>=0 && new_y<grid[0].length && !visited[new_x][new_y]){
                    visited[new_x][new_y]=true;
                    queue.offer(new int[]{new_x,new_y,count+1});
                    result=Math.max(result,count+1);
                }
            }
        }
        return result;
    }
    //1008. Construct Binary Search Tree from Preorder Traversal
    static class construct{
        int index=0;
        public BST bstToPre(int[] preorder){
            if(preorder.length==0)
                return new BST();
            return helper(preorder,Integer.MAX_VALUE);
        }
        public BST helper(int[] preorder,int max){
            if(index== preorder.length || preorder[index]>max)
                return null;
            BST result=new BST(preorder[index++]);
            result.left=helper(preorder,result.val);
            result.right=helper(preorder,max);
            return result;
        }
    }
    //Children Sum Property
    public static boolean childSum(BST node) {
        if (node == null)
            return false;
        Queue<BST> queue = new LinkedList<>();
        queue.offer(node);
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                BST temp = queue.poll();
                if (temp.left != null && temp.right != null) {
                    queue.offer(temp.left);
                    queue.offer(temp.right);
                    if(temp.left.val+temp.right.val!=temp.val)
                        return false;
                }else if(temp.left!=null){
                    queue.offer(temp.left);
                    if(temp.left.val!=temp.val)
                        return false;
                }else if(temp.right!=null){
                    queue.offer(temp.right);
                    if(temp.right.val!=temp.val){
                        return false;
                    }
                }
            }
        }
        return true;
    }
    //Even Vowel Substring
    public static int evenVowel(String s){
        if(s==null || s.length()==0)
            return 0;
        HashSet<Character> hashset=new HashSet<>();
        hashset.add('a');
        hashset.add('e');
        hashset.add('i');
        hashset.add('o');
        hashset.add('u');
        int[] array=new int[5];
        int end=0;
        int start=0;
        int result=0;
        while(end<s.length()){
            char ch=s.charAt(end);
            if(hashset.contains(ch)){
                array[vowelIndex(ch)]++;
                while(isEvenCount(array) && start<s.length()){
                    result=Math.max(result,Math.abs(end-start)+1);
                    char startChar=s.charAt(start);
                    if(hashset.contains(startChar)){
                        array[vowelIndex(startChar)]--;
                    }
                    start++;
                }
            }
            end++;
        }
        return result;
    }
    public static int vowelIndex(char c){
        return "aeiou".indexOf(c);
    }
    public static boolean isEvenCount(int[] array){
        for(int i:array){
            if(i%2!=0){
                return false;
            }
        }
        return true;
    }
    //was able to solve the test case where atleat 2 vowel are present refered gfg to learn the best optimal test case
    public static int evenVowel2(String s){
        if(s.length()==0)
            return 0;
        HashMap<Character,Integer> hashmap=new HashMap<>();
        HashMap<String,Integer> hashmap2=new HashMap<>();
        hashmap.put('a',0);
        hashmap.put('e',1);
        hashmap.put('i',2);
        hashmap.put('o',3);
        hashmap.put('u',4);
        String str="00000";
        hashmap2.put(str,-1);
        int end=0;
        int result=0;
        while(end<s.length()){
            char chracter=s.charAt(end);
            boolean isVowel=hashmap.containsKey(chracter);
            if(isVowel){
                int index=hashmap.get(chracter);
                if(str.charAt(hashmap.get(chracter))=='0'){
                    str=str.substring(0,index)+"1"+str.substring(index+1);
                }else{
                    str=str.substring(0,index)+"0"+str.substring(index+1);
                }
            }
            boolean index=hashmap2.containsKey(str);
            if(!index){
                hashmap2.put(str,end);
            }else{
                result=Math.max(result,end-hashmap2.get(str));
            }
            end++;
        }
        return result;
    }
    //Update Products
    public static int[] updateProducts(int[] nums){
        if(nums.length==0)
            return nums;
        HashMap<Integer,Integer> hashmap=new HashMap<>();
        for(int i:nums){
            hashmap.put(i,hashmap.getOrDefault(i,0)+1);
        }
        List<Map.Entry<Integer,Integer>> collect=new ArrayList<>(hashmap.entrySet());
        collect.sort((a,b)->b.getValue()-a.getValue());
        int left=0,right=1;
        int[] result=new int[nums.length];
        for(Map.Entry<Integer,Integer> entry:collect){
            int element=entry.getKey();
            int frequency=entry.getValue();
            while(frequency>0){
                if(right<nums.length){
                    result[right]=element;
                    right+=2;
                }else{
                    result[left]=element;
                    left+=2;
                }
                frequency--;
            }
        }
        return result;
    }
    //count Luck
    //Ron and Hermione are deep in the Forbidden Forest collecting potion ingredients,
    // and they've managed to lose their way. The path out of the forest is blocked, so
    // they must make their way to a portkey that will transport them back to Hogwarts.
    public static boolean countLuck(List<String> matrix,int decision){
        if(matrix.size()==0)
            return true;
        int row=matrix.size();
        int col=matrix.get(0).length();
        char[][] grid=new char[row][col];
        int startRow=0;
        int startCol=0;
        int targetRow=0;
        int targetCol=0;
        for(int i=0;i<row;i++){
            for(int j=0;j<col;j++){
                grid[i][j]=matrix.get(i).charAt(j);
                if(grid[i][j]=='M'){
                    startRow=i;
                    startCol=j;
                    grid[i][j]='X';
                }else if(grid[i][j]=='*'){
                    targetRow=i;
                    targetCol=j;
                }
            }
        }
        Queue<int[]> queue=new LinkedList<>();
        queue.offer(new int[]{startRow,startCol,0});
        int result=0;
        int[][] directions={{0,-1},{0,1},{-1,0},{1,0}};
        while(!queue.isEmpty()){
            int[] current=queue.poll();
            int currentRow=current[0];
            int currentCol=current[1];
            int currentCount=current[2];
            int tempCount=0;
//            System.out.println("Current row " + currentRow);
//            System.out.println("Current col " + currentCol);
            if(currentRow==targetRow && currentCol==targetCol)
                break;
            for(int[] d:directions){
                int newX=currentRow+d[0];
                int newY=currentCol+d[1];
                if(newX>=0 && newX<row &&newY>=0 && newY<col && grid[newX][newY]!='X'){
                    tempCount++;
                    queue.offer(new int[]{newX,newY,currentCount+1});
                    grid[newX][newY]='X';
                }
            }
//            System.out.println(tempCount);
            if(tempCount>1){
                result++;
            }
        }
        System.out.println(result);
        return result==decision;
    }
    //Clockwork
    public static int clockWork(String[] array){
        if(array.length==0)
            return 0;
        List<Integer> list=new ArrayList<>();
        for(String s:array){
            String[] str=s.split("\\:");
            int hour=Integer.parseInt(str[0]);
            int min=Integer.parseInt(str[1]);
            int total=hour*60+min;
            list.add(total);
        }
        list.sort((a,b)->Integer.compare(a,b));
        int result=Integer.MAX_VALUE;
        for(int i=1;i<list.size();i++){
            result=Math.min(result,list.get(i)-list.get(i-1));
        }
        int edgeCase=24*60-(list.get(list.size()-1)-list.get(0));
        result=Math.min(result,edgeCase);
        return result;
    }
//    1373. Maximum Sum BST in Binary Tree
    //approch 1
    static class validBST{
        final BST node;
        int max;
        int min;
        int sum;
        boolean isValid=true;
        public validBST(BST node){
         this.node=node;
         this.sum=node.val;
         this.min=node.val;
         this.max=node.val;
        }
    }
    public static int maxSumBST(BST node){
        if(node==null)
            return 0;
        int[] result = { Integer.MIN_VALUE };
        helperMaxSumBST(node,result);
        return result[0];
    }
    public static validBST helperMaxSumBST(BST node,int[] result){
        if(node==null)
            return null;
        validBST left=helperMaxSumBST(node.left,result);
        validBST right=helperMaxSumBST(node.right,result);
        validBST temp=new validBST(node);
        if(left!=null){
            temp.sum+=left.sum;
            temp.min=Math.min(node.val,left.min);
            temp.max=Math.max(node.val,left.max);
            temp.isValid&=left.isValid&(left.max< node.val);
        }
        if(right!=null){
            temp.sum+=right.sum;
            temp.min=Math.min(temp.min,right.min);
            temp.max=Math.max(temp.max, right.max);
            temp.isValid&=right.isValid&(right.min>node.val);
        }
        if(temp.isValid){
            result[0]=Math.max(result[0],temp.sum);
        }
        return temp;
    }
    public static void main(String[] args) {
        //test case for valid BST highest Sum
        BST test=new BST(1);
        test.left=new BST(4);
        test.left.left=new BST(2);
        test.left.right=new BST(4);
        test.right=new BST(3);
        test.right.left=new BST(2);
        test.right.right=new BST(5);
        test.right.right.left=new BST(4);
        test.right.right.right=new BST(6);
        System.out.println(maxSumBST(test));
            //test case for Clock work
//        System.out.println(clockWork(new String[]{"01:00", "01:10"}));
//        System.out.println(clockWork(new String[]{"00:00", "12:23", "05:50", "23:12"}));
//        //test case four Count Luck
//        List<String> test=new ArrayList<>();
//        test.add(".X.X......X");
//        test.add(".X*.X.XXX.X");
//        test.add(".XX.X.XM...");
//        test.add("......XXXX.");
//        List<String> test1=new ArrayList<>();
//        test1.add(".X.X......X");
//        test1.add(".X*.X.XXX.X");
//        test1.add(".XX.X.XM...");
//        test1.add("......XXXX.");
//        List<String> test2=new ArrayList<>();
//        test2.add("*..");
//        test2.add("X.X");
//        test2.add("..M");
//        System.out.println(countLuck(test,3));
//        System.out.println(countLuck(test1,4));
//        System.out.println(countLuck(test2,1));
        //test case for Update Products
//        System.out.println(Arrays.toString(updateProducts(new int[]{1,1,2,2,3,3})));
        //test case for Even Vowel SubString
//        System.out.println(evenVowel2("bbb"));
//        System.out.println(evenVowel2("aeiouaeioua"));
        //test case for Even Vowel Substring
//        System.out.println(evenVowel("bbbb"));
//        BST test=new BST(4);
//        test.left=new BST(1);
//        test.right=new BST(3);
//        BST test2=new BST(7);
//        test2.left=new BST(3);
//        test2.right=new BST(4);
//        test2.left.left=new BST(3);
//        test2.right.left=new BST(2);
//        test2.right.right=new BST(3);
//        System.out.println(childSum(test));
//        System.out.println(childSum(test2));
//        int[] preorder={8,5,1,7,10,12};
//        construct test=new construct();
//        preOrder(test.bstToPre(preorder));
        //test case Far From Land
//        System.out.println(farFromLand(new int[][]{{0, 0, 0}, {0, 1, 0}, {0, 0, 0}}));
        //test case for Highest Chain Size
//        System.out.println(chainSize(new String[]{"a","abc"}));
        //test case Something's Missing
//        System.out.println(missKthElement(new int[]{1,9,13,22},4));
        //test case Settling Debts
//        System.out.println(settlingDebts(new int[][]{{}}));
        //test case for c++Atios
//        System.out.println(Atois("4193 with words"));
        //test case for finding the width
        //[1,3,2,5,null,null,9,6,null,7]
//        BST test=new BST(1);
//        test.left=new BST(3);
//        test.right=new BST(2);
//        test.left.left=new BST(5);
//        test.right.right=new BST(9);
//        test.left.left.left=new BST(6);
//        test.right.right.left=new BST(7);
////        System.out.println(maxWidthBst(test));
//        System.out.println(pathRootLeaf(test));
//        System.out.println(medianTwoSorted(new int[]{1,3,8,9,15,},new int[]{7,11,18,19,21,25}));
        //test case for Job Sequence
//        Job test=new Job(1,4,20);
//        Job test1=new Job(2,1,10);
//        Job test2=new Job(3,1,40);
//        Job test3=new Job(4,1,30);
//        System.out.println(Arrays.toString(jobSeq(new Job[]{test,test1,test2,test3})));
        //test case for 435. Non-overlapping Intervals
//        System.out.println(nonOver(new int[][]{{1,2},{2,3},{3,4},{1,3}}));
        //test case for Rectangle in Histogram
//        System.out.println(largestRect(new int[]{2,1,5,6,5,3}));
//        System.out.println(largestRect2(new int[]{2,1}));
        //test case for Bear and Steady Gene
//        System.out.println(bearAndStready("GAAATAAA"));
        //test case Modify Image
//        printpaint(modifyImage(new int[][]{{1,3,2},{5, 8, 3},{4, 2, 2}}));
        //test case fro text judification
//             System.out.println(textJustification(Arrays.asList(new String[]{"This", "is", "an", "example", "of", "text", "justification."}),16));
        //test case word Ladder
//         System.out.println(wordLadder("hit","cog",Arrays.asList(new String[]{"hot","dot","dog","lot","log","cog"})));
        //test case for sherlockAndAnagrams
//         System.out.println(sherlockAndAnagrams("ifailuhkqq"));
        //test case for Minimum Genetic Mutation
//         System.out.println(minGenMut("AACCGGTT","AAACGGTA",Arrays.asList(new String[]{"AACCGGTA","AACCGCTA","AAACGGTA"})));
        //test case Words Within Two Edits of Dictionary
//         System.out.println(wordWith2(new String[]{"word","note","ants","wood"},new String[]{"wood","joke","moat"}));
        //test case for Word Break 2
//         System.out.println(wordBreak2("catsanddog",Arrays.asList(new String[]{"cat","cats","and","sand","dog"})));
//         System.out.println(wordDict2("catsanddog",Arrays.asList(new String[]{"cat","cats","and","sand","dog"})));
        //test case for The Full Counting Sort
//         List<List<String>> values = new ArrayList<>();
//         values.add(Arrays.asList("0", "ab"));
//         values.add(Arrays.asList("6", "cd"));
//         values.add(Arrays.asList("0", "ef"));
//         values.add(Arrays.asList("6", "gh"));
//         values.add(Arrays.asList("4", "ij"));
//         values.add(Arrays.asList("0", "ab"));
//         values.add(Arrays.asList("6", "cd"));
//         values.add(Arrays.asList("0", "ef"));
//         values.add(Arrays.asList("6", "gh"));
//         values.add(Arrays.asList("0", "ij"));
//         values.add(Arrays.asList("4", "that"));
//         values.add(Arrays.asList("3", "be"));
//         values.add(Arrays.asList("0", "to"));
//         values.add(Arrays.asList("1", "be"));
//         values.add(Arrays.asList("5", "question"));
//         values.add(Arrays.asList("1", "or"));
//         values.add(Arrays.asList("2", "not"));
//         values.add(Arrays.asList("4", "is"));
//         values.add(Arrays.asList("2", "to"));
//         values.add(Arrays.asList("4", "the"));
//         System.out.println(fullCountingString(values));
        //test case for Dj
//         int[][] grid = {
//                 {0, 2, 0, 4, 0},
//                 {2, 0, 3, 0, 0},
//                 {0, 3, 0, 1, 0},
//                 {4, 0, 1, 0, 3},
//                 {0, 0, 0, 3, 0}
//         };
//
//         Map<Integer, Integer> result = dj(grid);
//
//         System.out.println("Vertex \t Minimum Distance");
//         for (Map.Entry<Integer, Integer> entry : result.entrySet()) {
//             System.out.println(entry.getKey() + "\t" + entry.getValue());
//         }
        //test case for implementing Math pow
//        System.out.println(powMath(2,3));
//        System.out.println(powMath2(2,3));
        //test case for Separate the Numbers
//        sepearteNumber("99910001001");
//        sepearteNumber("7891011");
//        sepearteNumber("9899100");
//        sepearteNumber("999100010001");
        //test case for Sliding Window Maximum
//        System.out.println(Arrays.toString(slidingWindow(new int[]{1,3,-1,-3,5,3,6,7},3)));
//        System.out.println(Arrays.toString(slidingWindow2(new int[]{1,3,-1,-3,5,3,6,7},3)));
        //test case for Longest Path Matrix
//        System.out.println(longestPathMatrix(new int[][]{{9,9,4},{6,6,8},{2,1,1}}));
        //test case for Longest Substring
//        System.out.println(longestSub("ababbc",2));
        //test case for longest substring with k most distinct characters
//        System.out.println(longestKchar("eceba",2));
        //test case for counting prime
//        System.out.println(isPrmie2(50));
//        System.out.println(countPrime(50));
        //test case for Weighted Uniform String
//        List<Integer> test=new ArrayList<>();
//        test.add(1);
//        test.add(7);
//        test.add(5);
//        test.add(4);
//        test.add(5);
//        System.out.println(weightUniform("abbcccdddd",test));
        //test case for add two character
//        System.out.println(TwoCharacter("beabeefeab"));
        //test case for dequeue different methods
//        System.out.println("Test case-1");
//        System.out.println(DequeueProblem(3,new int[]{5,3,5,2,3,2}));
//        System.out.println("Test case-2");
//        System.out.println(usingDequeue(3,new int[]{5,3,5,2,3,2}));
        //test case for predict winner
//        System.out.println(predictWinner(new int[]{1,5,233,7}));
        //test case for Rotate List
//        ListNode test=new ListNode(1);
//        test.next=new ListNode(2);
//        test.next.next=new ListNode(3);
//        test.next.next.next=new ListNode(4);
//        test.next.next.next.next=new ListNode(5);
//        printList(rotateList(test,2));
        //test case for  Reverse Nodes in k-Group
//        ListNode test=new ListNode(1);
//        test.next=new ListNode(2);
//        test.next.next=new ListNode(3);
//        test.next.next.next=new ListNode(4);
//        test.next.next.next.next=new ListNode(5);
//        printList(reverseListNode(test,2));
//        printList(reverseKGroup(test,2));
        //test case for basic Calculator
//        calci test=new calci("(1+(4+5+2)-3)+(6+8)");
//        System.out.println(test.getResult());
        //test case for  Minimum Number of Arrows to Burst Balloons
//        System.out.println(burstBallon(new int[][]{{1,2},{2,3},{3,4},{4,5}}));
        //test case for Substring with Concatenation of All Words
//        System.out.println(subStringConcat("barfoothefoobarman",new String[]{"foo","bar"}));
        //test case for zigzag
//        System.out.println(zigZag("PAYPALISHIRING",4).equals("PINALSIGYAHRPI"));
        //test case for
//        System.out.println(reverseWords("a good   example"));
        //test case for Least Number of Unique Integers after K Removals
//        System.out.println(uniqueInt(new int[]{4,3,1,1,3,3,2},3));
        //test case for Trapping Rain Water
//        System.out.println(trapRain(new int[]{0,1,0,2,1,0,1,3,2,1,2,1}));
        //test case for Product of Array Except Self
//        System.out.println(Arrays.toString(productArray(new int[]{-1,1,0,-3,3})));
        //test case for finding a cycle is present or not
//        graph test=new graph(6);
//        test.addedge(0,1);
//        test.addedge(0,2);
//        test.addedge(1,2);
//        test.addedge(2,3);
//        test.addedge(3,4);
//        test.addedge(3,5);
//        test.addedge(4,5);
//        System.out.println(test.findCycle());
        //test cae for List array
//        ListNode one=new ListNode(1);
//        one.next=new ListNode(4);
//        one.next.next=new ListNode(5);
//        ListNode two=new ListNode(1);
//        two.next=new ListNode(3);
//        two.next.next=new ListNode(4);
//        ListNode three=new ListNode(2);
//        three.next=new ListNode(6);
//        ListNode[] arr={one,two,three};
//
//        printit(mergeListArray(arr));
//        int[][] test={{1,4,5},{1,3,4},{2,6}};
//        System.out.println(Arrays.toString(mergearr2(test)));
        //test case for all permutations
//        List<int[]> result=combiint(new int[]{1,2,3});
//        for(int[] i:result){
//            System.out.println(Arrays.toString(i));
//        }
        //test case for next permutation
//        System.out.println(Arrays.toString(nextPermutation(new int[]{6,2,3,5,4,1,0})));
//        System.out.println(Arrays.toString(nextPermutation(new int[]{1,2,3})));
        //test case for Falttern HashMap
//        HashMap<String, Object> dict = new HashMap<>();
//        dict.put("Key1", "1");
//
//        HashMap<String, Object> nestedDict = new HashMap<>();
//        nestedDict.put("a", "2");
//        nestedDict.put("b", "3");
//
//        HashMap<String, Object> innerDict = new HashMap<>();
//        innerDict.put("d", "3");
//
//        HashMap<String, Object> emptyDict = new HashMap<>();
//        emptyDict.put("", "1");
//
//        innerDict.put("e", emptyDict);
//        nestedDict.put("c", innerDict);
//        dict.put("Key2", nestedDict);
//        HashMap<String, String> flattened = faltHashMap(dict);
//        for (Map.Entry<String, String> entry : flattened.entrySet()) {
//            System.out.println(entry.getKey() + " : " + entry.getValue());
//        }
        //test case for max Sum
//        BST test=new BST(-10);
//        test.left=new BST(9);
//        test.right=new BST(20);
//        test.right.left=new BST(15);
//        test.right.right=new BST(7);
//        System.out.println(maxPathSum(test));
//        //test case for targetSum of Binary Search Tree
//        BST test=new BST(5);
//        test.left=new BST(4);
//        test.left.left=new BST(11);
//        test.left.left.left=new BST(7);
//        test.left.left.right=new BST(2);
//        test.right=new BST(8);
//        test.right.left=new BST(13);
//        test.right.right=new BST(4);
////        System.out.println(taregtBST(test,22));
//        System.out.println(taregtBST(test,22));
        //test case for contruct BST using sorted array
//        List<Integer> result=new ArrayList<>();
//        BST node=sortedBST(new int[]{-10,-3,0,5,9});
//        preOrderBST(node,result);
//        System.out.println(result);
        //test case for decode way of String
//        System.out.println(decodeWay("123"));
        //test case for Jump Game
//        System.out.println(jumGame(new int[]{3,2,1,0,4}));
        //test case for Max SubArray
//        System.out.println(bfmaxSubArray(new int[]{-2,1,-3,4,-1,2,1,-5,4}));
//        System.out.println(maxSubArray(new int[]{-2,1,-3,4,-1,2,1,-5,4}));
        //test case for atios
//        System.out.println(atios("  42"));
        //test case for Theif
//        System.out.println(theft(new int[]{1,2,3,4}));
        //test case for Missing Number
//        System.out.println(missingNumber(new int[]{3, 2, 4, 1, 5}));
        //test case for Final Digit
//        System.out.println(finalDigit(8353));
        //test case for Most Popular
//        System.out.println(mostpopular("The daily, the byte Daily.",new String[]{"the"}));
        //test case for microsoftPaint
//        printpaint(microsoftPaint(new int[][]{{0,1,1},{0,1,0},{1,1,1}},1,1,3));
        //test case for Common Values
//        System.out.println(Arrays.toString(commonValues(new int[]{1,2,2,3},new int[]{0,2,2,5})));
        //test case for String Validity
//        System.out.println(stringvalidity("((*"));
        //test case for Harmonious Values
//        System.out.println(harmoValues(new int[]{3, 2, 2, 2, 4, 3, 3}));
        //test case for Continous Sums
//        System.out.println(continousSum(new int[]{1,1,4},5));
        //test case Same Value
//        BST tree=new BST(2);
//        tree.left=new BST(5);
//        tree.right=new BST(7);
//        tree.left.left=new BST(3);
//        tree.left.right=new BST(3);
//        tree.right.right=new BST(7);
//        System.out.println(sameValue(tree));

        //test case Linked List Intersection
//        Listing node1=new Listing(1);
//        node1.next=new Listing(2);
//        node1.next.next=new Listing(3);
//        node1.next.next.next=new Listing(4);
//        node1.next.next.next.next=new Listing(5);
//        Listing node2=new Listing(0);
//        node2.next=new Listing(1);
//        node2.next.next=new Listing(2);
//        node2.next.next.next=new Listing(3);
//        node2.next.next.next.next=new Listing(4);
//        node2.next.next.next.next.next=new Listing(5);
//        System.out.println(listInterection(node1,node2));
        //test case for adding number as string
//        System.out.println(StringAdd("95","7"));
        //test case for reverse a palindrom or not for a linkedList
//        Listing test=new Listing(1);
//        test.next=new Listing(2);
////        test.next.next=new Listing(1);
//        System.out.println("for odd case pali-1"+isPali(test));
//        System.out.println("for odd case pali-2"+isPali2(test));
//        Listing test1=new Listing(1);
//        test1.next=new Listing(7);
//        System.out.println("for even case pali-1"+isPali(test1));
//        System.out.println("for even case pali-2"+isPali2(test1));
//        Listing test2=new Listing(1);
//        test2.next=new Listing(2);
//        test2.next.next=new Listing(2);
//        test2.next.next.next=new Listing(1);
//        System.out.println("for even case pali-1"+ isPali(test2));
//        System.out.println("for even case pali-2"+ isPali2(test2));
//        //test case for Calling All nodes
//        BST test=new BST(1);
//        test.left=new BST(2);
//        test.
//        System.out.println(callingNodes());
        //test case for Work Schdule
//        System.out.println(workSch(new int[][]{{1,3},{5,10},{11,15}}));
        //test case for sum to target
//        System.out.println(sumtoTarget(new int[]{8, 2, 2, 4, 5, 6, 3},9));
        //test case for K distinct elements
//        System.out.println(distinctK("abccccd",3));
        //test casec for Language Encryption
//        System.out.println(languageEncryption("worldabcefghijkmnpqstuvxyz",new String[]{"word","world","row"}));
        //test case for the width of the tree
//        BST test=new BST(1);
//        test.left=new BST(3);
//        test.right=new BST(9);
//        test.left.left=new BST(8);
//        test.left.right=new BST(7);
//        System.out.println(widthTree(test));
        //test case for sum to target
//        System.out.println(sumtoTarget(new int[]{8, 2, 2, 4, 5, 6, 3},9));
        //test case for K distinct elements
//        System.out.println(distinctK("abccccd",3));
        //test casec for Language Encryption
//        System.out.println(languageEncryption("worldabcefghijkmnpqstuvxyz",new String[]{"word","world","row"}));
        //test case for the width of the tree
//        BST test=new BST(1);
//        test.left=new BST(3);
//        test.right=new BST(9);
//        test.left.left=new BST(8);
//        test.left.right=new BST(7);
//        System.out.println(widthTree(test));
        //test case for Minimum Rotations
//        System.out.println(MinimumRotations(new int[]{2,1,2,4,2,2},new int[]{5,2,6,2,3,2}));
        //test case for Spreadsheet Column
//        System.out.println(getColumnTitle(28));
        //test case for Expensive Inventory
//        System.out.println(expensiveInventory(new int[]{5,4,3,2,1},new int[]{1,1,2,2,3},2,1));
        //test case for Triplets
//        System.out.println(triplets(new int[]{0, -1, 1, 1, 2, -2}));
        //test case for the trimming the bst according to the highest and the lowest values
//        BST test=new BST(5);
//        test.left=new BST(2);
//        test.right=new BST(7);
//        test.left.left=new BST(1);
//        test.left.right=new BST(3);
//        test.right.left=new BST(6);
//        test.right.right=new BST(9);
//        BST result=trimBST(test,new int[]{2,7});
//        preOrder(result);
        //test case for Trim Tree
//        BST test=new BST(3);
//        test.left=new BST(1);
//        test.right=new BST(7);
//        test.left.left=new BST(2);
//        test.left.right=new BST(8);
//        test.right.left=new BST(4);
//        test.right.right=new BST(6);
//        List<BST> result=trimTree(test,new int[]{7,8});
//        for(BST i:result){
//            System.out.println(i.val);
//        }
        //test caes for sore thumb
//        System.out.println(Arrays.toString(soreThumb(new int[]{5, 2, 4, 6, 3})));
        //test case for the largest pool
//        System.out.println(largestPool(new int[]{1,4,4,8,2}));
        //test case for Minimum Removal
//        System.out.println(miniRemoval("(()()()"));
        //test case for the Third Power
//        System.out.println(isThirdPower(55));
        //test case for the Largest Island which are connected
//        System.out.println(largestIsland(new int[][]{{1,1,0},{1,1,0},{0,0,1}}));
        //test case for Combine Times
//        System.out.println(Arrays.deepToString(CombineTime(new int[][]{{1,3},{1,8}})));
        //test case for Grouped Words
//        System.out.println(groupedWords(new String[]{"car", "arc", "bee", "eeb", "tea"}));
        //test case for the magical
//        System.out.println(magic(22));
        //test case for Max Sum
//        BST node=new BST(1);
//        node.left=new BST(4);
//        node.right=new BST(9);
//        System.out.println(maxSum(node));
        //test case for Crack the Code
//        System.out.println(crackCode("the daily Byte","abc"));
        //test case for Spiral Matrix;
//        System.out.println(spiralMatrix(new int[][]{{1,2,3},{4,5,6},{7,8,9}}));
        //test case fior Index Of
//        System.out.println(indexof("abc","c"));
        //test case for the good pair
//        System.out.println(Arrays.toString(goodPair(new int[]{1,2,5,7,9},10)));
        //test case for cutString
//        System.out.println(Cutstring("abacdddecn"));
        //test casec for Cut String
//        System.out.println(cutString("abacdddecn"));
//        //test case for Counting word
//        System.out.println(countWord(new String[]{"coding", "is", "fun", "code", "coding", "fun"},2));
//        //implementation 2
//        System.out.println(countWords(new String[]{"coding", "is", "fun", "code", "coding", "fun"},2));
        //test case for Shortest Distance
//        System.out.println(Arrays.toString(shortDistance("dailybyte",'y')));
        //test case for Rotate the array by 90 degrees
//        int[][] temp={{10,11,12},{13,14,15},{16,17,18}};
//        prinninty(temp);
//        prinninty(rotateArray(temp));
        //test case for Writing emails
//        System.out.println(writeEmail(new String[]{"test.email+kevin@dailybyte.com",
//                "test.e.mail+john.smith@dailybyte.com",
//                "testemail+david@daily.byte.com"}));
        //test case for Longest Conservate Path
//        BST test=new BST(1);
//        test.right=new BST(2);
//        test.right.right=new BST(3);
//        System.out.println(longConPath(test));
        //test case for highest Unique Charcter in the STring
//        System.out.println(uniChar("abcdssa"));
        //test case for Target Pair
//        BST test=new BST(1);
//        test.left=new BST(2);
//        test.right=new BST(3);
//        System.out.println(TreePair(test,6));
        //test case for Partner
//        System.out.println(Partbers(new int[]{5, 5, 3, 1, 1, 3, 3}));
        //test case for Visited Rooms
//        System.out.println(lockedRoomDfs(new int[][]{{1},{2},{}}));
        //tets case for election testing
//        System.out.println(elections(new int[]{1,3,2,3,1,2,3,3,3}));
        //test case for ink up
//        Listi test=new Listi(1);
//        test.next=new Listi(2);
//        test.next.next=new Listi(3);
//        test.next.next.next=new Listi(4);
//        test.next.next.next.next=new Listi(5);
//        printing(test);
//        Listi result=linkUp(test);
//        printing(result);
        //test case for the infection
//        System.out.println(getTotalMinutes(new int[][]{{1,1,1},{1,1,0},{0,1,2}}));
//        System.out.println(rottern(new int[][]{{1,1,1},{1,1,0},{0,1,2}}));
        //test case for the transpose matrix
//        System.out.println(transpose(new int[][]{{1,2,3},{4,5,6},{7,8,9}}).toString());
        //test case for non-prefect binary tree
//        BST test=new BST(1);
//        test.left=new BST(3);
//        test.left.left=new BST(5);
//        test.right=new BST(2);
//        test.right.left=new BST(4);
//        System.out.println(smallBST(test,2));
        //test case for Kth Smallest element
//        BST test=new BST(3);
//        test.left=new BST(1);
//        test.right=new BST(4);
//        test.left.right=new BST(2);
//        System.out.println(smallestKele(test,2));
        //test case for Given an undirected graph determine whether it is bipartite.
//        System.out.println(isBarpatite(new int[][]{{1,3},{0,2},{1,3},{0,2}}));
//        System.out.println(isBAp2(new int[][] {{1,3},{0,2},{1,3},{0,2}}));
//        //test case for the union finder approch
//        Solution test=new Solution();
//        System.out.println(test.unionFinder(new int[][]{{1,3},{0,2},{1,3},{0,2}}));
//        System.out.println(thirdLargest(new int[]{9, 5}));
//        System.out.println(lastWordLen("Hello, World"));
//        System.out.println(lenOfTheLast("Hello, World"));
//        BST node=new BST(1);
//        node.left=new BST(1);
//        node.right=new BST(0);
//        System.out.println(cleanTree(node));
//        System.out.println(pointsClose(new int[][]{{1,1},{-2,-2}},1));
//        System.out.println(closePoints(new int[][]{{1,1},{-2,-2}},1));
//        System.out.println(uniqueChar("abcabbbbbbbbbbb",3));
//        System.out.println(settingSail(new int[][]{{0,0,1},{1,0,0},{0,0,1}},5));
//        System.out.println(repeatedSubstrings("ABABABABABABBBBBBBBBBB"));
//        BST node=new BST(8);
//        node.left=new BST(1);
//        node.right=new BST(4);
//        node.right.left=new BST(2);
//        System.out.println(bottomBarrel(node));
//        System.out.println(Arrays.toString(shortAltColor(new int[][]{{0,1},{1,2}},new int[0][0],3)));
//        BST node=new BST(2);
//        node.left=new BST(-4);
//        node.right=new BST(9);
//        node.left.left=new BST(2);
//        System.out.println(treePath(node,11));
//        System.out.println(Arrays.toString(productMinMax(new int[]{1,2,3})));
//        System.out.println(Arrays.toString(products(new int[]{1,2,3})));
//        System.out.println(swapWors("The Daily Byte"));
//        System.out.println(findtheNumbers(new int[]{9, 2, 1, 7, 3, 2},5));
//        System.out.println(pondSize(new int[][]{{1,0,1},{0,0,0},{1,0,1}}));
//        System.out.println(divisible(17));
//        System.out.println(birthDay(new int[]{3,4,5},new int[]{2}));
//        System.out.println(satisfied(new int[]{3, 4, 5},new int[]{2}));
//        BST node=new BST(1);
//        node.left=new BST(7);
//        node.right=new BST(5);
//        node.left.left=new BST(4);
//        node.right.left=new BST(3);
//        node.right.right=new BST(9);
//        System.out.println(SumBound(node,3,5));
//        System.out.println(identicalEle(new int[]{1,2,1,2,1},1));
//        System.out.println(noSame("aab"));
//        System.out.println(reverseNumber(-37));
//        System.out.println(flipFlop(8));
//        System.out.println(keyboardRow(Arrays.asList("uto","xzy","byte")));
//        n_array root=new n_array(4);
//        n_array root1=new n_array(3);
//        n_array root2=new n_array(9);
//        n_array root3=new n_array(2);
//        n_array root4=new n_array(7);
//        n_array root5=new n_array(2);
//        root.children.add(root1);
//        root.children.add(root2);
//        root.children.add(root3);
//        root1.children.add(root4);
//        root3.children.add(root5);
//        System.out.println(DeepDive(root));
//        System.out.println(monotonic(new int[]{8, 4, 6}));
//        System.out.println(Arrays.toString(shuffleArray(new int[]{1,2,3,4})));
//        BST root=new BST(1);
//        root.left=new BST(6);
//        root.right=new BST(8);
//        root.left.left=new BST(1);
//        root.left.right=new BST(5);
//        System.out.println(averages(root));
//        System.out.println(Arrays.toString(rearrangeElment(new int[]{3, 7, 0, 5, 0, 2})));
//        System.out.println(travelTopOint(new int[][]{{0, 1}, {2, 3}, {4, 0}}));
//        System.out.println(hammingDistance(2,4));
//        System.out.println(minPath(new int[][]{{1,1,3},{2,3,1},{4,6,1}}));
//        System.out.println(removeVowels("byte"));
//        System.out.println(defanIP("127.0.0.1"));
//        System.out.println(compress(new char[]{'a', 'b', 'c'}));
//        System.out.println(countIsland(new int[][]{{11000}}));
//        System.out.println(isSubSequence("xyz","axbyc"));
//        System.out.println(charScramble("bat","cat"));
//        System.out.println(ReapChildren(new ArrayList<>(Arrays.asList(2,4,3,7)),new ArrayList<>(Arrays.asList(0,2,2,3)),3));
//        System.out.println(ReverseVowles("computer"));
//        System.out.println(countingPrime(7));
//        System.out.println(findFriend(new int[][]{{1,1,0},{1,1,0},{0,0,1}}));
//        System.out.println(complementNumber(27));
//        System.out.println(gymlocker(9));
//        System.out.println(artGallery(new int[]{4,1,3},new int[]{4,2,7},10));
//        int[][] test1={{17,2,17},{16,16,5},{14,3,19}};
//        System.out.println(paintingHouses(test1));
//        System.out.println(paintingHouse2(test1));
//        System.out.println(longSub(new int[]{1,9,7,4,7,13}));
//        System.out.println(maxSub(new int[]{-2,1,-3,4,-1,2,1,-5,4}));
//        System.out.println(longCS("abcde","ace"));
//        System.out.println(canCross(new int[]{0,1,3,5,6,8,12,17}));
//        System.out.println(uniquePath(3,7));
//        System.out.println(Decode("12"));
//        System.out.println(coinChange(new int[]{1,2,5},11));
//        System.out.println(maxpoints(new int[]{100,200,300,400},200));
//        System.out.println(palidrome("abba"));
//        System.out.println(uniqueCom(3));
//        System.out.println(combini(new int[]{2,3,6,7},7));
//        int[][] grid={{0,6,0},{5,8,7},{0,9,0}};
//        System.out.println(goldRush(grid));
//        char[][] board={{'c','a','t','f'},{'b','g','e','s'},{'i','t','a','e'}};
//        System.out.println(exist(board,"uydiutiu6erurku"));
//        System.out.println(letterCombinations("23"));
//        String s="a1b2";
//        System.out.println(dfsLettercase(s));
//        System.out.println(letterCasePermutation(s));
//        String s1="ab";
//        String s2="eidbaooo";
//        System.out.println(isprem(s1,s2));
//        System.out.println(prechecko(s1,s2));
//        BST node=new BST(1);
//        node.left=new BST(2);
//        node.right=new BST(2);
//        node.left.left=new BST(3);
//        node.left.right=new BST(4);
//        node.right.left=new BST(4);
//        node.right.right=new BST(3);
//        System.out.println(sumOfLL(node));
//        System.out.println(LLsum(node));
//        BST node1=new BST(1);
//        node1.left=new BST(2);
//        node1.right=new BST(2);
//        node1.left.left=null;
//        node1.left.right=new BST(3);
//        node1.right.left=null;
//        node1.right.right=new BST(3);
//        System.out.println(checkSYS(node));
//        System.out.println(checkSYS(node1));
//        int[] arr={8,5,1,7,10,12};
//        System.out.println(constrcutTree(arr).toString());
//        BST node=new BST(104);
//        node.left=new BST(39);
//        node.right=new BST(31);
//        node.left.left=new BST(32);
//        node.left.right=new BST(1);
//        node.right.left=new BST(9);
//        node.right.right=new BST(10);
//        BST node=new BST(2);
//        node.left=new BST(1);
//        node.right=new BST(3);
//        System.out.println(isValid(node));
//        System.out.println(LeafPath(node,175));
//        System.out.println(chain(node));
//        BST node=new BST(1);
//        node.left=new BST(2);
//        node.right=new BST(3);
//        node.left.right=new BST(5);
//        System.out.println(pri(node));
//        treeNode n=new treeNode(1);
//        treeNode n1=new treeNode(3);
//        treeNode n2=new treeNode(2);
//        treeNode n3=new treeNode(4);
//        treeNode n4=new treeNode(5);
//        treeNode n5=new treeNode(6);
//
//        n.child.add(n1);
//        n.child.add(n2);
//        n.child.add(n3);
//        System.out.println(levelOrder(n));
//        BST n=new BST(1);
//        n.left=new BST(5);
//        n.left.left=new BST(5);
//        n.left.right=new BST(3);
//        n.right=new BST(6);
//        n.right.right=new BST(7);
//        List<List<Integer>> a=new ArrayList<>();
//        zigZag(n,a,0);
//        System.out.println(a);
//        System.out.println(bottomUp(n));
//        pr(n);
//        System.out.println(maxValue(n));
//        BST n=new BST(2);
//        n.left=new BST(1);
//        n.right=new BST(3);
//        n.left.right=new BST(2);
//        System.out.println(modeL(n));
//        System.out.println(modeH(n));
//        BST n=new BST(4);
//        n.left=new BST(2);
//        n.left.left=new BST(1);
//        n.left.right=new BST(3);
//        n.right=new BST(6);
//        n.right.left=new BST(5);
//        n.right.right=new BST(7);
//        System.out.println(minDiff(n));
//        List<Integer> result=new LinkedList<>();
//        conBtoLl(n,result);
//        System.out.println(result);
//        System.out.println(leastC(n,5,2).val);
//        sQS s=new sQS();
//        s.add(1);
//        s.add(2);
//        s.add(3);
//        System.out.println(s.peek());
//        MovingAverage mv=n
//        ew MovingAverage(3);
    }
}
