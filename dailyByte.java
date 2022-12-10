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
    public static void main(String[] args) {
        BST node=new BST(104);
        node.left=new BST(39);
        node.right=new BST(31);
        node.left.left=new BST(32);
        node.left.right=new BST(1);
        node.right.left=new BST(9);
        node.right.right=new BST(10);
        System.out.println(LeafPath(node,175));
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
//        mv.next(3);
//        mv.next(5);
//        mv.next(7);
//        mv.next(6);
//        combination("abc",0,2);
//        removeAdj("foobar");
//        RemoAj("foobar");
//        System.out.println(Comparing("ab#c","ac"));
//        System.out.println(comp("ab#c","ac"));
//        Node n1=new Node(3);
//        Node n2=new Node(7);
//        print(adding(n1,n2));
//        Node n=new Node(1);
//        Node n1=new Node(2);
//        Node n2=new Node(3);
//        Node n3=new Node(4);
//        Node n4=new Node(5);
////        Node n5=new Node(6);
////        Node n6=new Node(7);
////        Node n7=new Node(8);
//        n.next=n1;
//        n1.next=n2;
//        n2.next=n3;
//        n3.next=n4;
////        n4.next=n5;
////        n5.next=n6;
////        n6.next=n7;
//        print(n);
//        System.out.println("--------------------");
//        reOrder(n);
//        print(n);
//        middleElement(n);
//        indexLL(n);
//        middleElement(n);
//        removeValue(n,2);
//        remmoveN(n,2);
//        System.out.println(Spot1("coding"," ingcod"));
//        System.out.println(Spot("coding"," ingcod"));
//        Funique("abcabd");
//        System.out.println(funique("developer")-'a');
//        System.out.println(anagram("listen","silent"));
//        System.out.println(jAS("abc","ac"));
//        System.out.println(twoSum(new int[]{1, 3, 8, 2},11));
//        String s1="foobof";
//        int a=palind(s1);
//        if(a==-1)
//            System.out.println("its not a palindrome");
//        else if(a==-2)
//            System.out.println("its a palindrome");
//        else
//            System.out.println("its a palindrome by removing a character which is at "+ a);
//        System.out.println(DaC(new String[] {"spot", "spotty", "spotted"},0,2));
//        System.out.println(Prefix1(new String[] {"spot", "spotty", "spotted"}));
//        System.out.println(Prefix(new String[] {"spot", "spotty", "spotted"}));
//        System.out.println(Binary("11","11"));
//        System.out.println(correction("coding"));
//        String s="ruulldrd";
//        System.out.println(route(s));
//        String s="North Texas";
//        System.out.println(s);
//        rev(s);
//        System.out.println();
//        in(s);
//        String s="levelsr";
//        System.out.println(check(s));
    }
}
