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
    public static void main(String[] args) {
        
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
