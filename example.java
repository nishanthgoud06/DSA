import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
public class example {
    class Node{
        Node left;
        Node right;
        int val;
        Node next;
        public Node(int val){
            this.val=val;
            this.left=null;
            this.right=null;
            this.next=null;
        }
        public Node(int _val, Node _left, Node _right, Node _next) {
            val = _val;
            left = _left;
            right = _right;
            next = _next;
        }
    }
    //117. Populating Next Right Pointers in Each Node II
    public static Node connect(Node root){
        if(root==null)
            return null;
        Node prev=null;
        Node current=root;
        Node head=null;
        while(current!=null){
            while(current!=null){
                if(current.left!=null){
                    if(prev!=null){
                        prev=current.left;
                    }else{
                        head=current.left;
                    }
                    prev=current.left;
                }
                if(current.right!=null){
                    if(prev!=null){
                        prev.next=current.right;
                    }else{
                        head=current.right;
                    }
                    prev=current.right;
                }
                current=current.next;
            }
            current=head;
            head=null;
            prev=null;
        }
        return root;
    }
    static int o=1;
    //here we are trying to find the permutation of a string
    private static void permute(String str, int l, int r)
    {
        if (l == r)
            System.out.println(str+" "+o++);
        else
        {
            for (int i = l; i <= r; i++)
            {
                str = swap(str,l,i);
                permute(str, l+1, r);
            }
        }
    }
    public static String swap(String s,int i,int j){
        char[] a=s.toCharArray();
        char first=a[i];
        char temp=a[j];
        a[i]=temp;
        a[j]=first;
        return String.valueOf(a);
    }
    //we can cod ein a different way to find the Permutation of a string
    public static void permutation(String s1,String s2){
        if(s1.length()==0)
            System.out.println(s2);
        else{
            for(int i=0;i<s1.length();i++){
                String rem=s1.substring(0,i)+s1.substring(i+1);
                permutation(rem,s2+s1.charAt(i));
            }
        }
    }
    //a program to print the two factorial
    public static int Powerof2(int n){
        if(n==0)
            return 1;
        else if(n==1){
            System.out.println(1);
            return 1;
        }
        else{
            int a=Powerof2(n/2);
            int sum=a*2;
            System.out.println(sum);
            return sum;
        }
    }
    static boolean isperfect(int a){
        int num=(int)Math.sqrt(a);
        if(num*num-a!=0)
            return false;
        return true;
    }
    public static void perfectSquare(int number){
        if(isperfect(number)){
            System.out.println(number +"is a perfect square");
        }
        int upper=-1;
        int lower=-1;
        int increasingnumber=number+1;
        int decreasingnumber=number-1;
        while(true){
            if(isperfect(increasingnumber)){
                upper=increasingnumber;
                break;
            }else{
                increasingnumber++;
            }
        }
        while(true){
            if(isperfect(decreasingnumber)){
                lower=decreasingnumber;
                break;
            }else{
                decreasingnumber--;
            }
        }
        int large=increasingnumber-number;
        int small=number-decreasingnumber;
        if(small>large){
            System.out.println("the closest is higher that the given number "+ increasingnumber+" difference "+large);
        }else{
            System.out.println("the closest is lower then the given number "+ decreasingnumber+" difference "+small);
        }
    }
    //now we are going to implement more optimized version
    public static void perSquare(int number){
        int check=(int)Math.sqrt(number);
        if(check*check==number)
            System.out.println("the given number is a perfect square "+ number);
        int upper=(check+1)*(check+1);
        int lower=check*check;
        int small=number-lower;
        int big=upper-number;
        if(small>big){
            System.out.println("the closest perfect square is higher than the given number "+ upper);
        }else{
            System.out.println("the closest perfect square is lower than the given number "+lower);
        }
    }
    //now we going to desing hashmap;
    //right now i am thinking of implementing this the in the same approach as hashset
    //but i a bit confused on how to handle conlission in hashmap hopefully by 30 min i can figure out
    //how to design it
//2131. Longest Palindrome by Concatenating Two Letter Words?
    public static int LongPali(String[] words){
        int result=0,unpair=0;
        HashMap<String,Integer> hm=new HashMap<>();
        for(String word:words){
            if(!hm.containsKey(word))
                hm.put(word,0);
            if(word.charAt(0)==word.charAt(1)){
                if(hm.get(word)>0){
                    unpair--;
                    result+=4;
                }else{
                    unpair++;
                    hm.put(word,hm.get(word)+1);
                }
            }else{
                String rev=Character.toString(word.charAt(1))+Character.toString(word.charAt(0));
                if(hm.containsKey(rev)){
                    result+=4;
                    hm.put(rev,hm.get(rev)-1);
                }else{
                    hm.put(word,hm.get(word)+1);
                }
            }
        }
        if(unpair>0)
            result+=2;
        return result;
    }
//    43. Multiply Strings
    public static String mutilplyStr(String s1,String s2){
        int num1=s1.length();
        int num2=s2.length();
        int[] arr=new int[num1+num2];
        for(int i=num1-1;i>=0;i--){
            for(int j=num2-1;j>=0;j--){
                int mul=(s1.charAt(i)-'0')*(s2.charAt(j)-'0');
                int pos1=i+j;
                int pos2=i+j+1;
                int sum=mul+arr[pos2];
                arr[pos1]+=sum/10;
                arr[pos2]=sum%10;
            }
        }
        String result="";
        for(int i:arr){
            result+= String.valueOf(i);
        }
        return result;
    }
    //3Sum
    //the first approach first we are going to implement is using Two Pointers
    public static List<List<Integer>> Sum_3(int[] numbers){
        List<List<Integer>> result=new ArrayList<>();
        if(numbers.length==0)
            return result;
        Arrays.sort(numbers);
        for(int i=0;i< numbers.length-2;i++){
            if(i==0||(i>0&&numbers[i]!=numbers[i-1])){
                int low=i+1;
                int high=numbers.length-1;
                int sum=0-numbers[i];
                while(low<high){
                    if(sum==numbers[low]+numbers[high]){
                        result.add(new ArrayList<>(Arrays.asList(numbers[i],numbers[low],numbers[high])));
                        while(low<high&&numbers[low]==numbers[low+1])low=low+1;
                        while(low<high&&numbers[high]==numbers[high-1])high=high-1;
                        low=low+1;
                        high=high-1;
                    }else if(numbers[low]+numbers[high]>sum){
                        high=high-1;
                    }else{
                        low=low+1;
                    }
                }
            }
        }
        return result;
    }
    public static List<List<Integer>> sum_3_HashMap(int[] nums){
        Set<List<Integer>> res  = new HashSet<>();
         if(nums.length==0) return new ArrayList<>(res);
         Arrays.sort(nums);
         for(int i=0; i<nums.length-2;i++){
             int j =i+1;
            int  k = nums.length-1;
             while(j<k){
                 int sum = nums[i]+nums[j]+nums[k];
                 if(sum==0)res.add(Arrays.asList(nums[i],nums[j++],nums[k--]));
                 else if (sum >0) k--;
                 else if (sum<0) j++;
             }

         }
         return new ArrayList<>(res);
        }
    //mulitply strings
    public static String mulStr(String s1,String s2){
        int len1=s1.length();
        int len2=s2.length();
        int[] arr=new int[len1+len2];
        for(int i=len1-1;i>=0;i--){
            for(int j=len2-1;j>=0;j--){
                int mul=(s1.charAt(i)-'0')*(s2.charAt(j)-'0');
                int pos1=i+j;
                int pos2=i+j+1;
                int sum=mul+arr[pos2];
                arr[pos1]+=sum/10;
                arr[pos2]=sum%10;
            }
        }
        String result="";
        for(int i:arr){
            result+=i;
        }
        return result;
    }
    //5. Longest Palindromic Substring
    public static String longPali(String s){
        if(s.length()<1||s==null)
            return "";
        int start=0;
        int end=0;
        for(int i=0;i<s.length();i++){
            int low=longPaliHelper(s,i,i);
            int high=longPaliHelper(s,i,i+1);
            int max=Math.max(low,high);
            if(max>end-start){
                start=i-((max-1)/2);
                end=i+max/2;
            }
        }
        return s.substring(start,end+1);
    }
    public static int longPaliHelper(String s,int left,int right){
        if(s==null||left>right){
            return 0;
        }
        while(left>=0&&right<s.length()&&s.charAt(left)==s.charAt(right)){
            left--;
            right++;
        }
        return right-left-1;
    }
    //1706. Where Will the Ball Fall
    public static int[] ballFall(int[][] wall){
        if(wall.length==0||wall==null)
            return null;
        int row=wall.length;
        int col=wall[0].length;
        int[] result=new int[col];
        Arrays.fill(result,-1);
        for(int i=0;i<col;i++){
            int x=0,y=i;
            while(true){
                if(x>=row){
                    if(y<col&&y>=0)
                        result[i]=1;
                    break;
                }else{
                    if(y+1<col&&wall[x][y]==1&&wall[x][y+1]==1){
                        x++;
                        y++;
                    }else if(y-1>=0&&wall[x][y]==-1&&wall[x][y-1]==-1){
                        x++;
                        y--;
                    }else{
                        break;
                    }
                }
            }
        }
        return result;
    }
    //design of indexOf
    public static int Indexof(String s1,String s2){
        if(s2.length()>s1.length()){
            return -1;
        }
        int i=0;
        while(i<=s1.length()-s2.length()){
            int j=0;
            while(j<s2.length() && s1.charAt(i+j)==s2.charAt(j)){
                j++;
            }
            if(j==s2.length()){
                return i;
            }else{
                i+=j+1;
            }
        }
        return -1;
    }
//    Reduce Array Size to The Half
    public static int reduceArray(int[] nums){
        HashMap<Integer,Integer> hashmap=new HashMap<>();
        for(int i:nums){
            hashmap.put(i,hashmap.getOrDefault(i,0)+1);
        }
        int result=0;
        int currentSize=0;
        PriorityQueue<Integer> pq=new PriorityQueue<>((a,b)->hashmap.get(b)-hashmap.get(a));
        for(int i:hashmap.keySet()){
            pq.offer(i);
        }
        while(!pq.isEmpty()){
            int value=pq.poll();
            currentSize+=value;
            result++;
            if(currentSize>=nums.length/2){
                break;
            }
        }
        return result;
    }
//    the next bigger element, also known as the inorder successor.
    static class PNode{
        int data;
        PNode left,right,parent;

    public PNode(int data, PNode left, PNode right, PNode parent) {
        this.data = data;
        this.left = left;
        this.right = right;
        this.parent = parent;
    }

    public PNode(int data) {
        this.data = data;
    }
}
    public static int nextBigElement(PNode node,int target){
        if(node!=null){
            if(node.data==target){
                if(node.parent!=null && node.parent.data>target){
                    return node.parent.data;
                }else{
                    if(node.right!=null){
                        PNode current=node.right;
                        while(current.left!=null){
                            current=current.left;
                        }
                        return current.data;
                    }
                }
            }else if(node.data>target){
                return nextBigElement(node.left,target);
            }else if(node.data<target){
                return nextBigElement(node.right,target);
            }
        }
        return -1;
    }
//    where each node also has a “random” pointer that points to anywhere in the linked list, deep clone the list.
    static class LNode{
      int data;
      LNode next,random;

    public LNode(int data, LNode next, LNode random) {
        this.data = data;
        this.next = next;
        this.random = random;
    }

    public LNode(int data) {
        this.data = data;
    }

    public  LNode(int data, LNode next){
          this.data=data;
          this.next=next;
      }
}
    public static LNode hardCopy(LNode node){
        HashMap<LNode,LNode> hashmap=new HashMap<>();
        LNode temp=node;
        LNode result=new LNode(-1);
        LNode dummy=result;
        while(temp!=null){
            dummy.next=new LNode(temp.data);
            dummy=dummy.next;
            hashmap.put(dummy,temp);
            temp=temp.next;
        }
        dummy=result;
        dummy=dummy.next;
        temp=node;
        while(temp!=null){
            if(temp.random!=null && hashmap.containsKey(temp.random)){
                dummy.random=hashmap.get(temp.random);
            }
            temp=temp.next;
            dummy=dummy.next;
        }
        return result.next;
    }
    public static void main(String[] args) {
        PNode root = new PNode(10);
        root.left = new PNode(5);
        root.right = new PNode(30);
        root.right.left = new PNode(22);
        root.right.right = new PNode(35);
        root.right.right.left = new PNode(33);

        // Set parent pointers
        root.left.parent = root;
        root.right.parent = root;
        root.right.left.parent = root.right;
        root.right.right.parent = root.right;
        root.right.right.left.parent=root.right.right;
        System.out.println(nextBigElement(root,30));
//        System.out.println(reduceArray(new int[]{3,3,3,3,5,5,5,2,2,7}));
//        System.out.println(Indexof("abcdef","e"));
//        int[][] test={{1,1,1,-1,-1},{1,1,1,-1,-1},{-1,-1,-1,1,1},{1,1,1,1,-1},{-1,-1,-1,-1,-1}};
//        System.out.println(Arrays.toString(ballFall(test)));
//        System.out.println(longPali("racecar"));
//        System.out.println(mulStr("5","12"));
//        System.out.println(Sum_3(new int[]{-1,0,1,2,-1,-4}));
//        System.out.println(sum_3_HashMap(new int[]{-1,0,1,2,-1,-4}));
//        System.out.println(mutilplyStr("12","5"));
//        System.out.println(LongPali(new String[]{"aa","bb","ab","ba"}));
////        Powerof2(25);
//        permutation("abc","");
//        permute("abc",0,2);
//        //1.extract numbers from text
//        String text="one1two2THREE3Four4";
//        String dilimitter="\\d";
//        Pattern pattern=Pattern.compile(dilimitter,Pattern.CASE_INSENSITIVE);
//        String[] result=pattern.split(text);
//        for(String s:result){
//            System.out.println(s);
//        }
    }
}
