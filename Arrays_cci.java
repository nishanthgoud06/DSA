import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.SecureRandom;
import java.sql.Array;
import java.util.*;
import java.io.*;

public class Arrays_cci {
    //1.1 implement anAlgorithm to determine if a string has all unique character.
    // what i you cannot use additional data Structure
    public static boolean isUnique(String s){
        //but the time complexity is O(n2)
        char[] c=s.toCharArray();
        for(int i=0;i<c.length;i++){
            for(int j=i+1;j<c.length;j++){
                if(c[i]==c[j])
                    return false;
            }
        }
        return true;
    }
    public static boolean isUniques(String s){
        char[] c=s.toCharArray();
        Arrays.sort(c);
        for(int i=0;i<c.length-1;i++){
            if(c[i]==c[i+1])
                return false;
        }
        return true;
    }
    //check permutations
//    public static boolean permutations(String s1,String s2){
//        if(s2.length()==0)
//            return false;
//        return checkPermutations(s1,s2);
//    }
//Lexicographical Values
    public static List<Integer> lexiIntsort(int k){
        List<String> temp=new ArrayList<>();
        for(int i=1;i<=k;i++){
            temp.add(String.valueOf(i));
        }
        Collections.sort(temp);
        List<Integer> result=new ArrayList<>();
        for(String s:temp){
            result.add(Integer.valueOf(s));
        }
        return result;
    }
    //approch 2
    public static List<Integer> lexiIntSort(int k){
        List<Integer> result=new ArrayList<>();
        lexiIntSortHelper(result,1,k);
        return result;
    }
    public static void lexiIntSortHelper(List<Integer> result,int start,int end){
        if(start>end){
            return;
        }
        result.add(start);
        lexiIntSortHelper(result,start*10,end);
        if(start%10<9){
            lexiIntSortHelper(result,start+1,end);
        }
    }
//Merge String
    public static String mergeStrings(String s1,String s2){
        //first approch
//        if(s1.length()==0 && s2.length()==0)
//            return new String();
//        else if(s1.length()>=s2.length()){
//            return s1.concat(s2);
//        }else{
//            return s2.concat(s1);
//        }
        //second approch
        char[] result=new char[s1.length()+s2.length()];
        int i=0;
        if(s1.length()>=s2.length()){
            for(int j=0;j<s1.length();j++){
                result[i++]=s1.charAt(j);
            }
            for(int j=0;j<s2.length();j++){
                result[i++]=s2.charAt(j);
            }
        }else{
            for(int j=0;j<s2.length();j++){
                result[i++]=s2.charAt(j);
            }
            for(int j=0;j<s1.length();j++){
                result[i++]=s1.charAt(j);
            }

        }
        return String.valueOf(result);
    }
//    One Unique Character Substrings
    public static int unique(String s){
        if(s==null || s.length()==0)
            return 0;
        int[] array=new int[26];
        int count=0;
        for(char c:s.toCharArray()){
            array[c-'a']++;
        }
        for(int i:array){
            count+=i;
        }
        int i=0;
        while(i<s.length()-1){
            while(s.charAt(i)==s.charAt(i+1)){
                count++;
                i++;
            }
            i++;
        }
        return count;
    }
//    Roll the Dice
public static int countDiceCombinations(int N, int max, int target) {
    int[][] dp = new int[N + 1][target + 1];

    // Initialize base case: one way to achieve sum 0 with 0 dice.
    dp[0][0] = 1;

    // Fill in the dp array.
    for (int i = 1; i <= N; i++) {
        for (int j = 1; j <= target; j++) {
            for (int k = 1; k <= max && k <= j; k++) {
                dp[i][j] += dp[i - 1][j - k];
            }
        }
    }

    return dp[N][target];
}
    //K Divisibility
    public static List<List<Integer>> kDivide(int[] nums,int target){
        if(target==0)
            return new ArrayList<>();
        List<List<Integer>> result=new ArrayList<>();
        KdivideHelper(result,nums,target,new ArrayList<>(),0);
        return result;
    }
    public static void KdivideHelper(List<List<Integer>> result,int[] nums,int target,List<Integer> temp,int index){
        if(target==0){
            result.add(new ArrayList<>(temp));
            return;
        }
        if(target<0){
            return;
        }
        for(int i=index;i<nums.length;i++){
            temp.add(nums[i]);
            KdivideHelper(result,nums,target-nums[i],temp,index+1);
            temp.remove(temp.size()-1);
        }
    }
    //N=2,max=6,target=4
//    case-1:N1=1,N2=3
//    case-2:N1=3,N2=1
//    case-3:N1=2,N2=2
    public static int countSubarraysDivisibleByK(int[] nums, int k) {
        int[] dp=new int[k];
        dp[0]=1;
        int prefix=0;
        int result=0;
        for(int num:nums){
            prefix+=num;
            prefix=(prefix%k+k)%k;
            result+=dp[prefix];
            dp[prefix]++;
        }
        return result;
    }
    //Same Vowel Count
    public static boolean samevowel(String s){
        if(s.length()==0)
            return true;
        if(s.length()%2!=0)
            return false;
        int length=s.length();
        int firstEnd=length/2;
        String firstHalf=s.substring(0,firstEnd);
        String secondHalf=s.substring(firstEnd,length);
        return sameVowelHelper1(firstHalf)==sameVowelHelper1(secondHalf);
    }
//    1590. Make Sum Divisible by P
    public static int makeSumDiv(int[] nums,int k){
        if(nums.length==0)
            return -1;
        int[] dp=new int[nums.length+1];
        for(int i=1;i<=nums.length;i++){
            dp[i]=dp[i-1]+nums[i-1];
        }
        int total=dp[nums.length];
        for(int i=1;i<nums.length;i++){
            for(int start=0;start+i<=nums.length;start++){
                int end=start+i;
                int subSum=dp[end]-dp[start];
                int rem=total-subSum;
                if(rem%k==0){
                    return i;
                }
            }
        }
        return 0;
    }
//    Soup or Salad
    public static int soupSalad(int[] people,int[] sides){
        if(people.length==0){
            return 0;
        }
        HashMap<Integer,Integer> hashmap=new HashMap<>();
        for(int i:people){
            hashmap.put(i,hashmap.getOrDefault(i,0)+1);
        }
        int currentSide=0;
        int satisfied=0;
            while(currentSide<sides.length && hashmap.containsKey(sides[currentSide])){
                hashmap.put(sides[currentSide],hashmap.get(sides[currentSide])-1);
                if(hashmap.get(sides[currentSide])==0){
                    hashmap.remove(sides[currentSide]);
                }
                satisfied++;
                currentSide++;
            }
            return people.length-satisfied;
        }
       //Tree Reconstruction
//    static class Tree{
//        int val;
//        Tree left,right;
//        public Tree(int val){
//            this.val=val;
//        }
//       }
//    public static Tree treeCon(int[] preorder,int[] inorder){
//        HashMap<Integer,Integer> hashMap=new HashMap<>();
//        for(int i=0;i< inorder.length;i++){
//            hashMap.put(inorder[i],i);
//        }
//        return treeConHelper(preorder,hashMap,0,preorder.length);
//    }
//    public static void printTreeInorder(Tree node){
//        if(node==null)
//            return;
//        printTreeInorder(node.left);
//        System.out.println(node.val);
//        printTreeInorder(node.right);
//    }
//    public static Tree treeConHelper(int[] preorder,HashMap<Integer,Integer> hashmap,int left,int right){
//        if(left>right){
//            return null;
//        }
//        Tree result=new Tree(preorder[left]);
//        result.left=treeConHelper(preorder,hashmap,left+1,hashmap.get(result.val));
//        result.right=treeConHelper(preorder,hashmap,hashmap.get(result.val),right);
//        return result;
//    }
    public static int sameVowelHelper1(String s){
        HashSet<Character> hashset=new HashSet<>();
        hashset.add('a');
        hashset.add('e');
        hashset.add('i');
        hashset.add('o');
        hashset.add('u');
        int count=0;
        for(char c:s.toCharArray()){
            if(hashset.contains(c)){
                count++;
            }
        }
        return count;
    }
    //Coffee Shop Customers
    public static int coffeeShopCustomer(int[] customer,int[] mode,int duriation){
        if(customer.length==0)
            return 0;
        int max=0;
        int end=0;
        while(end<mode.length){
            if(mode[end]==1){
                int count=0;
                int current=end;
                int value=0;
                while(count!=duriation && current<customer.length){
                    value+=customer[current];
                    current++;
                    count++;
                }
                max=Math.max(max,value);
            }
            end++;
        }
        return max;
    }
    //0
    //abcba
    //k=2
    //the longest substring with k
    public static String longSubString(String s,int k){
        if(k==0)
            return "";
        HashSet<Character> hashSet=new HashSet<>();
        int result=0;
        int startIndex=0;
        int start=0,end=0;
        while(end<s.length()){
            char endChar=s.charAt(end);
            hashSet.add(endChar);
            if(hashSet.size()>k){
                while(hashSet.size()>k){
                    char startChar=s.charAt(start);
                    hashSet.remove(startChar);
                    start++;
                }
            }
            int current=end-start+1;
            if(result<current){
                result=current;
                startIndex=start;
            }
            end++;
        }
        return s.substring(startIndex,startIndex+result);
    }
//    This problem was asked by Amazon.
//
//    There exists a staircase with N steps, and you can climb up either 1 or 2 steps at a time. Given N, write a function that returns the number of unique ways you can climb the staircase. The order of the steps matters.
//
//    For example, if N is 4, then there are 5 unique ways:
//
//            1, 1, 1, 1
//            2, 1, 1
//            1, 2, 1
//            1, 1, 2
//            2, 2
public static List<List<Integer>> stairCase(int target,List<Integer> ways){
        List<List<Integer>> result=new ArrayList<>();
        if(target==0)
            return result;
        stairCasehelper(target,ways,result,new ArrayList<>());
        return result;
}
public static void stairCasehelper(int target,List<Integer> ways,List<List<Integer>> result,List<Integer> temp){
        if(target==0)
            result.add(new ArrayList<>(temp));
        if(target<0)
            return;
        for(int i:ways){
            temp.add(i);
            stairCasehelper(target-i,ways,result,temp);
            temp.remove(temp.size()-1);
        }
}
//type-1;
    public static int stairCase1(int target,int[] nums){
        int[] dp=new int[target+1];
        dp[0]=1;
        for(int i=1;i<=target;i++){
            for(int j:nums){
                if(i-j>=0){
                    dp[i]+=dp[i-j];
                }
            }
        }
        return dp[target];
    }
    //Implement an autocomplete system
    //BruteForce
    public static List<String> autoComplete(String[] words,String pre){
        if(words.length==0||pre.length()==0)
            return new ArrayList<>();
        int length=pre.length();
        List<String> result=new ArrayList<>();
        for(String s:words){
            String str=s.substring(0,length);
            if(str.equals(pre)){
                result.add(s);
            }
        }
        return result;
    }
    //Director of Photography
//    public static int stackStab(int N,int[] nums){
//        int[] dp=new int[N+1];
//        dp[0]=0;
//        int i=0;
//        while(i<N){
//            if(dp[i]<nums[i]){
//                dp[i+1]=nums[i];
//            }
//        }
//    }
    //daily coding problem
    public static List<String> possibleString(List<String> all,String target){
        if(target.length()==0 && all.size()==0)
            return new ArrayList<>();
        List<String> result=new ArrayList<>();
        int end=0,start=0;
        while(end<target.length()){
            String current=target.substring(start,end+1);
            if(all.contains(current)){
                result.add(current);
                start=end+1;
            }
            end++;
        }
        return start==target.length()?result:new ArrayList<>();
    }
    //the maximum values of each subarray of length k
    public static List<Integer> maxValue(int[] nums,int k){
        List<Integer> result=new ArrayList<>();
        for(int i=0;i<=nums.length-k;i++){
            int max=0;
            for(int j=i;j<i+k;j++){
                if(max<nums[j]){
                    max=nums[j];
                }
            }
            result.add(max);
        }
        return result;
    }
    //optimmized version
    public static List<Integer> maxValue2(int[] nums,int k){
        List<Integer> result=new ArrayList<>();
        if(nums.length==0 || k==0)
            return result;
        int i=0;
        Deque<Integer> deque=new ArrayDeque<>();
        while(i<k){
            if(!deque.isEmpty() && nums[deque.peekFirst()]<nums[i]){
                deque.pollFirst();
            }
            deque.offerLast(i);
            i++;
        }
        System.out.println(deque.peekFirst());
        while(i< nums.length){
            result.add(nums[deque.peekFirst()]);
            while(!deque.isEmpty() && deque.peekFirst()<=i-k){
                deque.pollFirst();
            }
            while(!deque.isEmpty() && nums[deque.peekFirst()]<=nums[i]){
                deque.pollFirst();
            }
            deque.offerLast(i);
            i++;
        }
        result.add(deque.peekFirst());
        return result;
    }
    static class TreeNode{
        int val;
        TreeNode left,right,parent;
        boolean isLocked;
        public TreeNode(int val,TreeNode left,TreeNode right,TreeNode parent){
            this.val=val;
            this.left=left;
            this.right=right;
            this.parent=parent;
            this.isLocked=false;
        }
        public boolean currentState(){
            return isLocked;
        }
        
    }
    public static int TappedWater(int[] nums){
        if(nums.length==0||nums==null)
            return 0;
        int leftMax=0;
        int rightMax=0;
        int left=0;
        int right=nums.length-1;
        int result=0;
        while(left<right){
            leftMax=Math.max(leftMax,nums[left]);
            rightMax=Math.max(rightMax,nums[right]);
            if(nums[left]<nums[right]){
                result+=Math.max(0,leftMax-nums[left]);
                left++;
            }else {
                result+=Math.max(0,rightMax-nums[right]);
                right--;
            }
        }
        return result;
    }
    //2462. Total Cost to Hire K Workers
    public static int totalCost(int[] nums,int k,int neighbour){
        PriorityQueue<Integer> left=new PriorityQueue<>((a,b)->a-b);
        PriorityQueue<Integer> right=new PriorityQueue<>((a,b)->a-b);
        int i=0,j=nums.length-1;
        int count=0,result=0;
        while(count<k){
            while(i<=j && left.size()<neighbour){
                left.offer(nums[i++]);
            }
            while(j>=i && right.size()<neighbour){
                right.offer(nums[j--]);
            }
            int lefty=left.size()>0?left.peek():Integer.MAX_VALUE;
            int righty=right.size()>0?right.peek():Integer.MAX_VALUE;
            if(lefty<=righty){
                result+=lefty;
                left.poll();
            }else{
                result+=righty;
                right.poll();
            }
            count++;
        }
        return result;
    }
    //Compute the running median of a sequence of numbers
    static class median{
        private PriorityQueue<Integer> minHeap;
        private PriorityQueue<Integer> maxHeap;
        public median(){
            minHeap=new PriorityQueue<>((a,b)->a-b);
            maxHeap=new PriorityQueue<>((a,b)->b-a);
        }
        public void addElement(int a){
            if(maxHeap.size()==0 || maxHeap.peek()>=a){
                maxHeap.add(a);
            }else {
                minHeap.add(a);
            }
            if(maxHeap.size()>minHeap.size()+1){
                minHeap.add(maxHeap.poll());
            }else if(minHeap.size()>maxHeap.size()){
                maxHeap.add(minHeap.poll());
            }
        }

        public float getMedian(){
            if(minHeap.size()==maxHeap.size())
                return (minHeap.peek()+maxHeap.peek())/2;
            else
                return maxHeap.peek();
        }
    }
    //Sort Colors
    public static char[] sortColors(char[] arr){
        if(arr.length==0)
            return arr;
        int low=0,high=arr.length-1,mid=0;
        while(mid<=high){
            if(arr[mid]=='R'){
                char temp=arr[low];
                arr[low]=arr[mid];
                arr[mid]=temp;
                low++;
                mid++;
            }else if(arr[mid]=='G'){
                mid++;
            }else{
                char temp=arr[high];
                arr[high]=arr[mid];
                arr[mid]=temp;
                high--;
            }
        }
        return arr;
    }
    static class Node{
        int val;
        Node left,right;
        public Node(int val){
            this.val=val;
        }
        public Node(int val,Node left,Node right){
            this.val=val;
            this.left=left;
            this.right=right;
        }
    }
//    Given the root to a binary search tree, find the second largest node in the tree.
    public static int secondLargestBST(Node node){
        if(node==null)
            return 0;
        int[] result=new int[2];
        Arrays.fill(result,Integer.MIN_VALUE);
        secondLargestBSTHelper(node,result);
        return result[1];
    }
    public static void secondLargestBSTHelper(Node node,int[] arr){
        if(node==null)
            return ;
        secondLargestBSTHelper(node.right,arr);
        if(node.val>arr[0]){
            arr[1]=arr[0];
            arr[0]=node.val;
        }else if(node.val<arr[0] && node.val>arr[1]){
            arr[1]=node.val;
        }
        secondLargestBSTHelper(node.left,arr);
    }
    //findinh the second largest element i the array
    public static int secondLargest(int[] nums){
        if(nums.length<2)
            return Integer.MIN_VALUE;
        int result=-1,largest=0;
        for(int i=1;i<nums.length;i++){
            if(nums[i]>nums[largest]){
                result=largest;
                largest=i;
            }else if(nums[i]<nums[largest]){
                if(result==-1 || nums[i]>nums[result]){
                    result=i;
                }
            }
        }
        return nums[result];
    }
//    Best Time to Buy and Sell Stock with Transaction Fee
    public static int timeAndBuy(int[] nums,int fee){
        if(nums.length==0)
            return 0;
        int[][] dp=new int[nums.length][2];
        dp[0][0]=0;
        dp[0][1]=-nums[0]-fee;
        for (int i = 1; i < nums.length; i++) {
            dp[i][0]=Math.max(dp[i-1][0],dp[i-1][1]+nums[i]);
            dp[i][1]=Math.max(dp[i-1][1],dp[i-1][0]-nums[i]-fee);
        }
        return dp[nums.length-1][0];
    }
    //901. Online Stock Span
    static class Stonk{
        Stack<int[]> stack;
        public Stonk(){
            stack=new Stack<>();
        }
        public int next(int value){
            int current=1;
            while(!stack.isEmpty() && value>=stack.peek()[0]){
                current+=stack.pop()[1];
            }
            stack.push(new int[]{value,current});
            return current;
        }
    }
    //Palidrome need to return the earliest lexicographically
    public static String palindrome(String str){
        StringBuilder sb1=new StringBuilder(str);
        StringBuilder sb2=new StringBuilder();
        int length=str.length()-1;
        while(length>=0){
            sb2.append(sb1.charAt(length));
            if(isPali(sb2.toString()+sb1.toString())){
                return sb2.toString()+sb1.toString();
            }
            length--;
        }
        return "";
    }
    public static boolean isPali(String s){
        int low=0,high=s.length()-1;
        while(low<high){
            if(s.charAt(low)!=s.charAt(high)){
                return false;
            }
            low++;
            high--;
        }
        return true;
    }
    public static void gridGame(int[][] live,int rounds,int size){
        if(rounds==0)
            return;
        char[][] grid=new char[size][size];
        Arrays.fill(grid,'.');
        for(int[] i:live){
            int row=i[0];
            int col=i[1];
            grid[row][col]='*';
        }

    }
    public static int noOfNeighbours(char[][] grid,int i,int j){
        int[][] dir={{0,1},{0,-1},{1,0},{-1,0}};
        return -1;
    }
    public static void printGrid(char[][] grid){
        for(char[] i:grid){
            System.out.println(Arrays.asList(i));
        }
    }
    //DailyByte Medium
    public static int RotatedArray(int[] nums,int target){
        if(nums.length==0)
            return -1;
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
        int temp=low;
        low=0;
        high=nums.length-1;
//        System.out.println(temp);
        if(nums[low]>=target && nums[temp-1]<=target){
            high=temp-1;
        }else{
            low=temp;
        }
        while(low<=high){
            int mid=low+(high-low)/2;
            if(nums[mid]==target){
                return mid;
            }else if(nums[mid]>target){
                high=mid-1;
            }else{
                low=mid+1;
            }
        }
        return -1;
    }
    //Url shorten
    static class UrlShort{
        HashMap<String,String> hashmap;
        int length;
        public UrlShort(int length){
            this.length=length;
            hashmap=new HashMap<>();
        }
        public String Encode(String url){
            if(hashmap.containsKey(url)){
                return hashmap.get(url);
            }
            String result=String.valueOf(this.generateKey(url));
            hashmap.put(url,result);
            return result;
        }
        //we can actually decrease the Decode serach time to O(1) by using another hashmap which stores the value and Key in
        //where tiny url is the key and the original url as value but we need more storage for that
        public String Decode(String url){
            for(Map.Entry<String,String> map:hashmap.entrySet()){
                if(map.getValue()==url){
                    return map.getKey();
                }
            }
            return null;
        }
        private String generateKey(String url){
            String alphanumeric="abcdefghijklmnopqurstvwxyz0123456789";
            StringBuilder result=new StringBuilder(length);
            Random random=new Random();
            int current=0;
            while (current<length){
                int index=random.nextInt(alphanumeric.length());
                result.append(alphanumeric.charAt(index));
                current++;
            }
            return result.toString();
        }
    }
//    queue using two stacks
    static class SQueue{
        Stack<Integer> stack1;
        Stack<Integer> stack2;
        public SQueue(){
            stack1=new Stack<>();
            stack2=new Stack<>();
        }
        public void push(int value){
            while(!stack1.isEmpty()){
                stack2.push(stack1.pop());
            }
            stack1.push(value);
            while(!stack2.isEmpty()){
                stack1.push(stack2.pop());
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
    }
    //Generate Random Number
    public static int[] shuffleDeck(int[] nums){
        for(int i=nums.length-1;i>0;i--){
            int j=shuffleIt(i+1);
            int temp=nums[i];
            nums[i]=nums[j];
            nums[j]=temp;
        }
        return nums;
    }
    public static int shuffleIt(int i){
        SecureRandom random=new SecureRandom();
        return random.nextInt(i)+1;
    }
    //Flight Schduler
    public static List<String> flightSchedule(String[][] travels, String start) {
        HashMap<String, List<String>> hashmap = new HashMap<>();
        for (String[] s : travels) {
            String from = s[0];
            String to = s[1];
            if (hashmap.containsKey(from)) {
                hashmap.get(from).add(to);
                Collections.sort(hashmap.get(from)); // Sort destinations lexicographically
            } else {
                hashmap.put(from, new ArrayList<>());
                hashmap.get(from).add(to);
            }
        }

        List<String> result = new ArrayList<>();
        if (flightScheduleHelper(result, hashmap, start)) {
            return result;
        } else {
            return null;
        }
    }

    public static boolean flightScheduleHelper(List<String> result, HashMap<String, List<String>> hashmap, String start) {
        if (result.contains(start)) {
            return false;
        }
        result.add(start);
        if (hashmap.containsKey(start)) {
            for (String str : hashmap.get(start)) {
                if (!flightScheduleHelper(result, hashmap, str)) {
                    return false;
                }
            }
        }

        // Ensure all flights are used in the itinerary
        return result.size() == hashmap.size() + 1;
    }
    //357. Count Numbers with Unique Digits
    //brute force approch
    public static int counUni(int n){
        if(n==0)
            return 1;
        int result=0;
        for(int i=0;i<=Math.pow(10,n);i++){
            if(counUniHelper(i))
                result++;
        }
        return result;
    }
    public static boolean counUniHelper(int n){
        int[] length=new int[10];
        while(n>0){
            int current=n%10;
            if(length[current]==1)
                return false;
            length[current]=1;
            n=n/10;
        }
        return true;
    }
    //using Dynamic Programming
    public static int counUni2(int n){
        if(n==0)
            return 1;
        int[] dp=new int[n+1];
        dp[0]=1;
        dp[1]=10;
        for(int i=2;i<=n;i++){
            int total=9;
            int limit=9;
            for(int j=1;j<i;j++){
                total*=limit;
                limit--;
            }
            dp[i]=dp[i-1]+total;
        }
        return dp[n];
    }
    //minimum number of columns that can be removed to ensure that
    //each row is ordered from top to bottom lexicographically
    public static int toBeRemoved(char[][] grid){
        if(grid.length==0||grid==null)
            return 0;
        int result=0;
        int row=grid.length;
        int col=grid[0].length;
        for(int i=0;i<col;i++){
            int prev=Integer.MAX_VALUE;
            int cole=0;
            while(cole<row){
                if(prev<'z'-grid[cole][i]){
                    result++;
                    break;
                }
                prev='z'-(int)grid[cole][i];
                cole++;
            }
        }
        return result;
    }
    //find the length of the longest increasing subsequence in the array
    public static int longSub(int[] nums){
        if(nums==null || nums.length==0)
            return 0;
        int length=nums.length;
        int[] dp=new int[length];
        Arrays.fill(dp,1);
        for(int i=0;i<length;i++){
            for(int j=i+1;j<length;j++){
                if(nums[j]>nums[i]){
                    dp[j]=Math.max(1+dp[i],dp[j]);
                }
            }
        }
        int result=0;
        for(int i:dp){
            result=Math.max(result,i);
        }
        return result;
    }
    //Apple medium problem
    //brute force
    public static int solve(int n,int target){
        if( n==0)
            return 0;
        int result=0;
        int[][] dp=new int[n][n];
        int value=1;
        for(int i=0;i<n;i++){
            dp[0][i]=value;
            value++;
        }
        for(int i=1;i<n;i++){
            for(int j=0;j<n;j++){
                dp[i][j]=dp[0][j]+dp[i-1][j];
                if(dp[i][j]==target){
                    result++;
                }
            }
        }
        for(int[] i:dp)
            System.out.println(Arrays.toString(i));
        return result;
    }
    public static int[][] mergeTime(int[][] nums){
        if(nums==null || nums.length==0)
            return new int[0][];
        Arrays.sort(nums,(a,b)->a[0]-b[0]);
        int firstStart=nums[0][0];
        int firstEnd=nums[0][1];
        List<int[]> result=new ArrayList<>();
        int j=1;
        while(j<nums.length){
            int currentStart=nums[j][0];
            int currentEnd=nums[j][1];
            if(currentStart<=firstEnd){
//                result.add(new int[]{Math.min(currentStart,firstStart),Math.max(firstEnd,currentEnd)});
                firstStart=Math.min(currentStart,firstStart);
                firstEnd=Math.max(currentEnd,firstEnd);
            }else{
                result.add(new int[]{firstStart,firstEnd});
                firstStart=currentStart;
                firstEnd=currentEnd;
            }
            j++;
        }
        result.add(new int[]{firstStart,firstEnd});
        int[][] object=new int[result.size()][2];
        int k=0;
        for(int[] i:result){
            object[k++]=i;
        }
        return object;
    }
    //most frequently-occurring letter along that path.
    public static int mostFreq(int[][] nums,int n){
        if(nums==null || nums.length==0)
            return -1;
        int[] result=new int[1];
        HashMap<Integer,List<Integer>> hashmap=new HashMap<>();
        for(int[] i:nums){
            int from=i[0];
            int to=i[1];
            if(hashmap.containsKey(from)){
                hashmap.get(from).add(to);
            }else{
                hashmap.put(from,new ArrayList<>());
                hashmap.get(from).add(to);
            }
        }
        boolean[] visited=new boolean[n];
        for(int i=0;i<n;i++){
            mostFreqHelper(result,hashmap,i,visited,0);
        }
        return result[0]<n?result[0]:-1;
    }
    public static void mostFreqHelper(int[] result,HashMap<Integer,List<Integer>> hashmap,int current,boolean[] visited,int value){
        if(visited[current])
            return;
        result[0]=Math.max(result[0],value);
        visited[current]=true;
        if(hashmap.containsKey(current)){
            for(int i:hashmap.get(current)){
                mostFreqHelper(result,hashmap,i,visited,value+1);
            }
        }

        visited[current]=false;
    }
    public static int generateRandom(int n){
        return (int)(Math.random()*n)+1;
    }
    public static int perfectNumber(int n){
        int result=0;
        for(int i=1;;i++){
            int current=0;
            for(int j=i;j>0;j=j/10){
                current+=j%10;
            }
            if(current==10)
                result++;
            if(result==n)
                return i;
        }
    }
    public static int maxProduct3(int[] nums){
        if(nums==null || nums.length<=2)
            return Integer.MIN_VALUE;
        int min1=Integer.MAX_VALUE;
        int min2=Integer.MAX_VALUE;
        int max1=Integer.MIN_VALUE;
        int max2=Integer.MIN_VALUE;
        int max3=Integer.MIN_VALUE;
        for(int i:nums){
            if(i>max1){
                max3=max2;
                max2=max1;
                max1=i;
            }else if(i>max2){
                max3=max2;
                max2=i;
            }else if(i>max3){
                max3=max2;
            }

            if(i<min1){
                min2=min1;
                min1=i;
            }else if(i<min2){
                min2=i;
            }
        }
        return Math.max(max1*max2*max3,min1*min2*max1);
    }
    //maximum sum of any contiguous subarray of the array.
    public static int maxConSub(int[] nums){
        if(nums==null || nums.length==0)
            return 0;
        int end=0,start=0,result=0,current=0;
        while(end<nums.length){
            current+=nums[end];
            if(current<0){
                while(current<0){
                    current-=nums[start];
                    start++;
                }
            }
            result=Math.max(result,current);
            end++;
        }
        return result;
    }
    //the number of ways of starting at the top-left corner and getting to the bottom-right corner.
    public static int matrixWays(int n,int m){
        int[][] grid=new int[n+1][m+1];
        for(int i=0;i<=n;i++){
            grid[i][0]=1;
        }
        for(int i=0;i<=m;i++){
            grid[0][i]=1;
        }
        for(int i=1;i<=m;i++){
            for(int j=1;j<=n;j++){
                grid[i][j]=grid[i-1][j]+grid[i][j-1];
            }
        }
        return grid[n-1][m-1];
    }
    //determine whether the array could become non-decreasing by modifying at most 1 element.
    public static boolean nonDe(int[] nums){
        if(nums.length<=1)
            return true;
        boolean used =false;
        for(int i=1;i<nums.length;i++){
            if(!(nums[i]>nums[i-1])){
                if(used)
                    return false;
                used=true;
            }
        }
        return true;
    }
    static class LinkedList{
        int val;
        LinkedList next;
        public LinkedList(int val){
            this.val=val;
        }
        public LinkedList(int val,LinkedList next){
            this.val=val;
            this.next=next;
        }
        public LinkedList(){}
    }
    //merge LinkedList
    public static LinkedList mergeIt(LinkedList[] arr){
        if(arr==null || arr.length==0)
            return new LinkedList();
        int i=0,j=arr.length-1;
        return mergeitH1(arr,i,j);
    }
    public static LinkedList mergeitH1(LinkedList[] arr,int i,int j){
        if (i == j) {
            return arr[i];
        }

        int mid = i + (j - i) / 2;
        LinkedList m1 = mergeitH1(arr, i, mid);
        LinkedList m2 = mergeitH1(arr, mid + 1, j);
        return mergeitH2(m1, m2);
    }
    public static LinkedList mergeitH2(LinkedList m1,LinkedList m2){
        LinkedList result=new LinkedList();
        LinkedList dummy=result;
        while(m1!=null && m2!=null){
            if(m1.val<=m2.val){
                dummy.next=new LinkedList(m1.val);
                dummy=dummy.next;
                m1=m1.next;
            }else{
                dummy.next=new LinkedList(m2.val);
                dummy=dummy.next;
                m2=m2.next;
            }
        }
        while(m1!=null){
            dummy.next=new LinkedList(m1.val);
            dummy=dummy.next;
            m1=m1.next;
        }
        while(m2!=null){
            dummy.next=new LinkedList(m2.val);
            dummy=dummy.next;
            m2=m2.next;
        }
        return result.next;
    }
    //print the spiral
    public static void printSpiral(int[][] nums){
        if(nums==null || nums.length==1)
            return;
        int startrow=0,endRow=nums.length-1,startcol=0,endCol=nums[0].length-1;
        int current=0;
        int total=(endRow+1)*(endCol+1);
        while(current<total){
            for(int i=startcol;i<=endCol;i++){
                System.out.println(nums[startrow][i]);
                current++;
            }
            startrow++;
            for(int i=startrow;i<=endRow;i++){
                System.out.println(nums[i][endCol]);
                current++;
            }
            endCol--;
            for(int i=endCol;i>=startcol;i--){
                System.out.println(nums[endRow][i]);
                current++;
            }
            endRow--;
            for(int i=endRow;i>=startrow;i--){
                System.out.println(nums[i][startcol]);
                current++;
            }
            startcol++;
        }
    }
    //longest palindromic contiguous substring
    //Brute Force Approch
    public static String conSub(String s){
        if(s.length()==0)
            return s;
        int max=0;
        String result = "";
        for(int i=0;i<s.length();i++){
            for(int j=i+1;j<=s.length();j++){
                if(isPali(s.substring(i,j)) && s.substring(i,j).length()>max){
                    result=s.substring(i,j);
                    max=s.substring(i,j).length();
                }
            }
        }
        return result;
    }
    //we are using Dynamic Programming to improve the time complexcity by o(n)
    public static String conSub1(String s){
        if(s.length()==0)
            return s;
        boolean[][] dp=new boolean[s.length()][s.length()];
        for(int i=0;i<s.length();i++){
            dp[i][i]=true;
        }
        int startIndex=0;
        int maxLength=1;
        for(int len=2;len<s.length();len++){
            for(int i=0;i<s.length()-len+1;i++){
                int j=len+i-1;
                if(s.charAt(i)==s.charAt(j)){
                    if(len==2||dp[i+1][j-1]){
                        startIndex=i;
                        dp[i][j]=true;
                        maxLength=Math.max(maxLength,len);
                    }
                }
            }
        }
        return s.substring(startIndex,startIndex+maxLength);
    }
    //Knight tour
    public static int[][] knightTour(int n) {
        int[][] result = new int[n][n];
        boolean[][] visited = new boolean[n][n];
        knightTourHelper(result, visited, 0, 0, 1, n);
        return result;
    }

    public static void knightTourHelper(int[][] result, boolean[][] visited, int i, int j, int strike, int n) {
        if (i >= 0 && j >= 0 && i < n && j < n && !visited[i][j]) {
            visited[i][j] = true;
            result[i][j] = strike;

            // All possible moves for a knight
            int[] rowMoves = { -2, -1, 1, 2, 2, 1, -1, -2 };
            int[] colMoves = { 1, 2, 2, 1, -1, -2, -2, -1 };

            for (int k = 0; k < 8; k++) {
                int nextI = i + rowMoves[k];
                int nextJ = j + colMoves[k];

                if (nextI >= 0 && nextI < n && nextJ >= 0 && nextJ < n && !visited[nextI][nextJ]) {
                    knightTourHelper(result, visited, nextI, nextJ, strike + 1, n);
                }
            }

            // Backtracking: Undo the changes made during this move
            visited[i][j] = false;
        }
    }
    //bishops attack
    public static int bishopsAttack(int[][] positions){
        if(positions==null || positions.length==0)
            return 0;
        int result=0;
        for(int i=0;i<positions.length;i++){
            for(int j=i+1;j< positions.length;j++){
                int startRow=positions[i][0];
                int startCol=positions[i][1];
                int endRow=positions[j][0];
                int endCol=positions[j][1];
                if(Math.abs(startRow-endRow)==Math.abs(startCol-endCol))
                    result++;
            }
        }
        return result;
    }
//    deepest node
    public static Node deepNode(Node n){
        if(n==null)
            return n;
        Queue<Node> queue=new java.util.LinkedList<>();
        queue.offer(n);
        List<Node> result=new ArrayList<>();
        while(queue.isEmpty()){
            int size=queue.size();
            for(int i=0;i<size;i++){
                Node current=queue.poll();
                result.add(current);
                if(current.left!=null)
                    queue.offer(current.left);
                if(current.right!=null)
                    queue.offer(current.right);
            }
        }
        return result.get(result.size()-1);
    }
    //subset of S that adds up to k
    public static int[] subSetAdd(int[] nums,int k){
        if(nums==null || nums.length==0)
            return nums;
        List<Integer> temp=new ArrayList<>();
        if(!helper(temp,nums,k,0,0))
            return new int[0];
        int[] result=new int[temp.size()];
        int j=0;
        for(int i:temp){
            result[j++]=i;
        }
        return result;
    }
    public static boolean helper(List<Integer> temp,int[] nums,int target,int index,int total){
        if(target==total)
            return true;
        if(total>target)
            return false;
        for(int i=index;i<nums.length;i++){
            temp.add(nums[i]);
            if(helper(temp,nums,target,i+1,total+nums[i]))
                return true;
            temp.remove(temp.size()-1);
        }
        return false;
    }
    //Partition to K Equal Sum Subsets
    public static boolean kEqualSub(int[] nums,int k){
        int total=0;
        for(int i:nums)
            total+=i;
        if(nums.length<k || total%k!=0)
            return false;
        return kEqualSubHelper(nums,k,0,0,total/k);
    }
    public static boolean kEqualSubHelper(int[] nums,int k,int index,int sum,int target){
        if(k<=0)
            return true;
        for(int i=index;i<nums.length;i++){
            if(sum+nums[i]<=target && nums[i]!=0){
                int temp=nums[i];
                nums[i]=0;
                if(sum+temp==target){
                    if(kEqualSubHelper(nums,k-1,0,0,target))
                        return true;
                }else{
                    if(kEqualSubHelper(nums,k,i+1,sum+temp,target))
                        return true;
                }
                nums[i]=target;
            }
        }
        return false;
    }
//    all possible letters the number could represent
    public static List<String> numCombi(String s){
        HashMap<Character,String> hashmap=new HashMap<>();
        hashmap.put('2',"abc");
        hashmap.put('3',"def");
        hashmap.put('4',"ghi");
        hashmap.put('5',"jkl");
        hashmap.put('6',"mno");
        hashmap.put('7',"pqrs");
        hashmap.put('8',"tuv");
        hashmap.put('9',"wxyz");
        List<String> result=new ArrayList<>();
        numCombiHelper(result,hashmap,s,0,"");
        return result;
    }
    public static void numCombiHelper(List<String> result,HashMap<Character,String> hashmap,String number,int index,String current){
        if(index==number.length()){
            result.add(new String(current));
            return;
        }
        for(char c:hashmap.get(number.charAt(index)).toCharArray()){
            numCombiHelper(result,hashmap,number,index+1,current+c);
        }
    }
//    the maximum profit you could have made from buying and selling that stock once
    public static int maxProfit(int[] nums){
        if(nums==null || nums.length==0)
            return 0;
        int low=Integer.MAX_VALUE;
        int profit=0;
        for(int i=0;i<nums.length;i++){
            low=Math.min(low,nums[i]);
            profit=Math.max(profit,nums[i]-low);
        }
        return profit;
    }
    //Microsoft Easy
    static class ReadF{
        private int current;
        private String Document;

        public ReadF(String Path) throws IOException {
            Document=Files.readString(Paths.get(Path));
            current=0;
        }

        public String read7(){
            if(current>=Document.length()){
                return null;
            }
            String result=Document.substring(current,Math.min(current+7,Document.length()));
            current+=7;
            return result;
        }
    }
    //the word can be found in the matrix by going left-to-right, or up-to-down.
    public static boolean findWord(char[][] grid,String word){
        if(word.length()==0)
            return true;
        for(int i=0;i<grid.length;i++){
            for(int j=0;j<grid[0].length;j++){
                if(grid[i][j]==word.charAt(0)){
                    if(findWordH1(grid,i,j,word) || findWordH2(grid,i,j,word))
                        return true;
                }
            }
        }
        return false;
    }
    public static boolean findWordH1(char[][] grid,int i,int j,String word){
        int k=j;
        int current=0;
        while(k<grid[0].length && current<word.length()){
//            System.out.println(grid[k][j]);
            if(grid[i][k++]!=word.charAt(current++))
                return false;
        }
        return current==word.length();
    }
    public static boolean findWordH2(char[][] grid,int i,int j,String word){
        int k=i;
        int current=0;
        while(k<grid.length && current<word.length()){
            System.out.println(grid[k][j]);
            if(grid[k][j]!=word.charAt(current))
                return false;
            k+=1;
            current+=1;
        }
        return current==word.length();
    }
//    Suppose an arithmetic expression is given as a binary tree. Each leaf is an integer and each internal node is one of '+', '−', '∗', or '/'.
    public static int arithDo(Node node){
        if(node==null)
            return 0;
        Queue<Integer> operator=new java.util.LinkedList<>();
        Stack<Character> operation=new Stack<>();
        List<Character> list=new ArrayList<>();
        list.add('+');
        list.add('-');
        list.add('/');
        list.add('*');
        Queue<Node> queue=new java.util.LinkedList<>();
        queue.offer(node);
        while(!queue.isEmpty()){
            int size=queue.size();
            for(int i=0;i<size;i++){
                Node current=queue.poll();
                if(Character.isDigit(current.val)){
//                    System.out.println(current.val-'0');
                    operator.offer(current.val-'0');
                } else if(list.contains((char)current.val)){
                    operation.add((char) current.val);
                }
                if(operator.size()>=2 && operation.size()>=1){
                    int num1 = operator.poll();
                    int num2 = operator.poll();
                    char op = operation.pop();
//                    System.out.println(num1+" "+num2+" "+op);
                    operator.offer(arithDoHelper(num1, num2, op));
                }
                if(current.left!=null){
                    queue.offer(current.left);
                }
                if(current.right!=null)
                    queue.offer(current.right);
            }
        }
        return operator.size()>0?operator.poll():0;
    }
    public static int arithDoHelper(int num1,int num2,char c){
            if(c=='+')
                return num1+num2;
            else if(c=='-')
                return num1-num2;
            else if(c=='/')
                return num2!=0?num1/num2:0;
            else if(c=='*')
                return num1*num2;
            return 0;
    }
//implement Stack with max function
    static class iStack{
        Queue<Integer> queue1;
        Queue<Integer> queue2;
        PriorityQueue<Integer> pq;
        public iStack(){
            queue1=new java.util.LinkedList<>();
            queue2=new java.util.LinkedList<>();
            pq=new PriorityQueue<>((a,b)->b-a);
        }
        public void push(int val){
            pq.add(val);
            if(!queue1.isEmpty()){
                while(!queue1.isEmpty())
                    queue2.offer(queue1.poll());
            }
            queue1.offer(val);
            while(!queue2.isEmpty())
                    queue1.offer(queue2.poll());
        }
        public int pop(){
            if(queue1.isEmpty())
                return Integer.MIN_VALUE;
            int element=queue1.poll();
            pq.remove(element);
            return element;
        }
        public int max(){
            if(pq.size()>0)
                return pq.peek();
            return Integer.MIN_VALUE;
        }
    }
    public static int MatricIslandCount(int[][] grid){
        if(grid.length==0)
            return 0;
        int result=0;
        for(int i=0;i<grid.length;i++){
            for(int j=0;j<grid[0].length;j++){
                if(grid[i][j]==1){
                    result++;
                    MatricIslandCountHelper(grid,i,j);
                }
            }
        }
        return result;
    }
    public static void MatricIslandCountHelper(int[][] grid,int i,int j){
        if(i<0 || j<0 || i>=grid.length || j>=grid[0].length || grid[i][j]==0)
            return;
        grid[i][j]=0;
        MatricIslandCountHelper(grid,i-1,j);
        MatricIslandCountHelper(grid,i+1,j);
        MatricIslandCountHelper(grid,i,j-1);
        MatricIslandCountHelper(grid,i,j+1);
    }
    //Invert a binary tree.
    public static void PreO(Node node){
        if(node==null)
            return;
        PreO(node.left);
        System.out.println(node.val);
        PreO(node.right);
    }
    public static Node Invert(Node node){
        if(node==null)
            return null;
        final Node left=node.left;
        final Node right=node.right;
        node.left=Invert(right);
        node.right=Invert(left);
        return node;
    }
    public static void main(String[] args) {
        Node node=new Node(1);
        node.left=new Node(2);
        node.right=new Node(3);
        node.left.left=new Node(4);
        node.left.right=new Node(5);
        node.right.left=new Node(6);
        PreO(node);
        PreO(Invert(node));
//        System.out.println(MatricIslandCount(new int[][]{{1,0,0,0,0},{0,0,1,1,0},{0,1,1,0,0},{0,0,0,0,0},{1,1,0,0,1},{1,1,0,0,1}}));
//        Node root = new Node('*');
//        root.left = new Node('+');
//        root.right = new Node('+');
//        root.left.left = new Node('3');
//        root.left.right = new Node('2');
//        root.right.left = new Node('4');
//        root.right.right = new Node('5');
//        int result = arithDo(root);
//        System.out.println("Result of the Arithmetic Expression: " + result);
//        char[][] test={{'F','A','C','I'},{'O','B','Q','P'},{'A','N','O','B'},{'M','A','S','S'}};
//        System.out.println(findWord(test,"FOAM"));
//        System.out.println(findWord(test,"MASS"));
//        try{
//            ReadF test=new ReadF("src/documents.txt");
//            System.out.println(test.read7());
//            System.out.println(test.read7());
//            System.out.println(test.read7());
//        }catch(Exception e){
//            System.out.println(e);
//        }
//        System.out.println(maxProfit(new int[]{9, 11, 8, 5, 7, 10}));
//        System.out.println(numCombi("23"));
//        System.out.println(kEqualSub(new int[]{2,2,2,2,3,4,5},4));
//        System.out.println(kEqualSub(new int[]{1,1,1,1,2,2,2,2},4));
//        System.out.println(Arrays.toString(subSetAdd(new int[]{12, 1, 61, 5, 9, 2},24)));
//        System.out.println(bishopsAttack(new int[][]{{0,0},{1,2},{2,2},{4,0}}));
//        int[][] result=knightTour(3);
//        for(int i=0;i<result.length;i++){
//            for(int j=0;j<result.length;j++){
//                System.out.print(result[i][j]+" ");
//            }
//            System.out.println();
//        };
        //        System.out.println(conSub1("aabcdcb"))
//        System.out.println(conSub1("bananas"));
//        int[][] test={{1,2,3,4,5},{6,7,8,9,10},{11,12,13,14,15},{16,17,18,19,20}};
//        printSpiral(test);
//        LinkedList temp1=new LinkedList(1);
//        temp1.next=new LinkedList(2);
//        temp1.next.next=new LinkedList(3);
//        temp1.next.next.next=new LinkedList(4);
//        LinkedList temp2=new LinkedList(6);
//        temp2.next=new LinkedList(7);
//        temp2.next.next=new LinkedList(8);
//        temp2.next.next.next=new LinkedList(9);
//        LinkedList temp3=new LinkedList(10);
//        temp3.next=new LinkedList(11);
//        temp3.next.next=new LinkedList(12);
//        temp3.next.next.next=new LinkedList(13);
//        LinkedList result=mergeIt(new LinkedList[]{temp1,temp2,temp3});
//        while(result!=null){
//            System.out.println(result.val);
//            result=result.next;
//        }
//        System.out.println(nonDe(new int[]{10, 5, 7}));
//        System.out.println(matrixWays(5,5));
//        System.out.println(maxConSub(new int[]{34, -50, 42, 14, -5, 86}));
//        System.out.println(maxConSub(new int[]{-5, -1, -8, -9}));
//        System.out.println(maxProduct3(new int[]{-10, -10, 5, 2}));
//        System.out.println(perfectNumber(2));
//        System.out.println(generateRandom(5));
        //test case for most frequently-occurring letter along that path.
//        int[][] test={{0,1},{0,2},{2,3},{3,4}};
//        System.out.println(mostFreq(test,5));
        //test case for overlapping intervals
//        int[][] test={{1,3},{5,8},{4,10},{20,25}};
//        int[][] result=mergeTime(test);
//        for(int[] i:result){
//            System.out.println(Arrays.toString(i));
//        }
//        System.out.println(solve(6,12));
        //test case for longest incresing subsequence
//        System.out.println(longSub(new int[]{0, 8, 4, 12, 2, 10, 6, 14, 1, 9, 5, 13, 3, 11, 7, 15}));
        //test case for counting the removal of colums for lexologocal
//        char[][] test1={{'c','b','a'},{'d','a','f'},{'g','h','i'},{'z','z','z'}};
//        char[][] test2={{'a','b','c','d','e','f'}};
//        char[][] test3={{'z','y','x'},{'w','v','u'},{'t','s','r'}};
//        System.out.println(toBeRemoved(test1));
//        System.out.println(toBeRemoved(test2));
//        System.out.println(toBeRemoved(test3));
        //test case for OCunt Number with Unique Digits
//        System.out.println(counUni(9));
//        System.out.println(counUni2(9));
        //test case for flight Schedule
//        String[][] str={{"SFO","HKO"},{"YYZ","SFO"},{"YUL","YYZ"},{"HKO","ORD"}};
//        System.out.println(flightSchedule(str,"YUL"));
//        String[][] str1={{"A","B"},{"A","C"},{"B","C"},{"C","A"}};
//        System.out.println(flightSchedule(str1,"A"));
        //test case for shuffling the deck
//        int[] deck=new int[51];
//        for(int i=0;i<51;i++){
//            deck[i]=i+1;
//        }
//        System.out.println(Arrays.toString(deck));
//        System.out.println(Arrays.toString(shuffleDeck(deck)));
        //test case for Queue using Stack
//        SQueue test=new SQueue();
//        test.push(1);
//        test.push(2);
//        test.push(3);
//        test.push(4);
//        System.out.println(test.pop());
//        System.out.println(test.peek());
        //test case for urlShortner
//        UrlShort test=new UrlShort(6);
//        String key=test.Encode("https://www.youtube.com/watch?v=48lyxeVORsU&ab_channel=JohnCoogan");
//        System.out.println(key);
//        String result=test.Decode(key);
//        System.out.println(result);
        //test case for finding a target element in the rotated array
//        System.out.println(RotatedArray(new int[]{13, 18, 25, 2, 8, 10},8));
        //test case for Palidrome Problem
//        System.out.println(palindrome("race"));
//        System.out.println(palindrome("google"));
        //test case for Online Stock Span
//        Stonk test=new Stonk();
//        System.out.println(test.next(100));
//        System.out.println(test.next(80));
//        System.out.println(test.next(60));
//        System.out.println(test.next(70));
//        System.out.println(test.next(60));
//        System.out.println(test.next(75));
//        System.out.println(test.next(85));
        //Profit Loss Calculator
//        System.out.println(timeAndBuy(new int[]{1,3,2,8,4,9},2));
        //test case for 2nd Largest element in the array
//        System.out.println(secondLargest(new int[]{7,6,5,10,11,12,8,1}));
        //test case for fidning the second largest element in the array
//        Node test=new Node(5);
//        test.left=new Node(2);
//        test.right=new Node(7);
//        test.left.left=new Node(1);
//        test.left.right=new Node(4);
//        test.right.left=new Node(6);
//        test.right.right=new Node(9);
//        System.out.println(secondLargestBST(test));
        //test case for Sorting Char Array
//        System.out.println(Arrays.toString(sortColors(new char[]{'G', 'B', 'R', 'R', 'B', 'R', 'G'})));
        //test case for runnibg median
//        median test=new median();
//        test.addElement(1);
//        test.addElement(3);
//        test.addElement(2);
//        System.out.println(test.getMedian());
        //test case for Total Cost
//        System.out.println(totalCost(new int[]{17,12,10,2,7,2,11,20,8},3,4));
        //test casec for TappedWater
//        System.out.println(TappedWater(new int[]{2,1,2}));
//        System.out.println(TappedWater(new int[]{3,0,1,3,0,5}));
        //test case for max number in a certain range
//        System.out.println(maxValue(new int[]{10, 5, 2, 7, 8, 7},3));
//        System.out.println(maxValue2(new int[]{10, 5, 2, 7, 8, 7},3));
        //test case for find the possible STring combination
//        System.out.println(possibleString(Arrays.asList(new String[]{"quick","brown","the","fox"}),"thequickbrownfox"));
//        System.out.println(possibleString(Arrays.asList(new String[]{"bed","bath","bedbath","and","beyond"}),"bedbathandbeyond"));
        //test case for autocomplete system
//        System.out.println(autoComplete(new String[]{"dog", "deer", "deal"},"de"));
        //test case for stair case
//        System.out.println(stairCase(4,Arrays.asList(1,2)));
//        System.out.println(stairCase1(4,new int[]{1,2}));
        //the longest substring with k
//        System.out.println(longSubString("abcba",2));
        //test case for Coffee shop customer
//        System.out.println(coffeeShopCustomer(new int[]{5,2,2,2},new int[]{1,0,1,1},2));
        //test case for constructing a tree based on the inorder and preorder array
//        printTreeInorder(treeCon(new int[]{1,2,3},new int[]{2,1,3}));
        //test case for Soup and Salad
//        System.out.println(soupSalad(new int[]{1,1,1},new int[]{1,1,1}));
//        System.out.println(makeSumDiv(new int[]{6,3,5,2},9));
        //test case for Same Vowel
//        System.out.println(samevowel("computer"));
//        int[] nums = {1, 3, 1, 2, 5};
//        int k = 7;
//        System.out.println(countSubarraysDivisibleByK(nums, k));
//        System.out.println(kDivide(new int[]{1, 3, 1, 2, 5},7));
//        System.out.println(countDiceCombinations(1,6,5));
//        System.out.println(unique("aabca"));
//        System.out.println(mergeStrings("a","def"));
//        System.out.println(lexiIntsort(11));
//        System.out.println(lexiIntSort(11));
//        System.out.println(isUnique("cabc"));
    }
}
