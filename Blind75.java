import java.io.CharConversionException;
import java.lang.reflect.Array;
import java.util.*;

public class Blind75 {
    //in this class i am going to imcluse all the neetcode blind 75 Coding interview Question with the Solution.
    //i am going to implement according to the roadmap given by the author of neetcode
    //the first topic which we are going to implement is arrays
    //1.Contains Duplicate
    public static boolean ContainsDuplicate(int[] nums){
        //this a easy level problem
        //there are two approach to solve this problem
        //if the requirement doesn't include having limited space we can implement using hashset
        HashSet<Integer> hashset=new HashSet<>();
        for(int i:nums){
            if(!hashset.contains(i)){
                hashset.add(i);
            }else
                return true;
        }
        return false;
    }
    //in the below method we are going to use linear memory space but the time complexity in this case will be o(nlogn)
    //as we are going to sort the given array
    public static boolean containsDuplicates(int[] nums){
        Arrays.sort(nums);
        for(int i=0;i<nums.length-2;i++){
            if(nums[i]==nums[i+1])
                return true;
        }
        return false;
    }
    //the second problem is about anagram
    //brute force appriach is to sort both os the strings and compare them to each other
    public static boolean validAnagram(String s1,String s2){
        if(s1.length()!=s2.length())
            return false;
        char[] c1=s1.toCharArray();
        Arrays.sort(c1);
        char[] c2=s2.toCharArray();
        Arrays.sort(c2);
        for(int i=0;i<c1.length;i++){
            if(c1[i]!=c2[i])
                return false;
        }
        return true;
    }
    //second approach would be to implement using dynamic programming
    public static boolean isValidAnagram(String s1,String s2){
        if(s1.length()!=s2.length())
            return false;
        int[] character=new int[26];
        for(int i=0;i<s1.length();i++){
            character[s1.charAt(i)-'a']++;
        }
        for(int i=0;i<s2.length();i++){
            character[s2.charAt(i)-'a']--;
        }
        for(int i=0;i<26;i++){
            if(character[i]!=0){
                return false;
            }
        }
        return true;
    }
    //two sum
    //the brute force approach is to use two for loops where if you find the combinstion you return else return -1,-1
    public static int[] twoSum(int[] num,int target){
        int[] result=new int[2];
        for(int i=0;i<num.length;i++){
            for(int j=i+1;i<num.length;i++){
                if(num[i]+num[j]==target){
                    result[0]=i;
                    result[1]=j;
                }
            }
        }
        return result;
    }
    //the second approach would be to use hashmap the space complexity would be linear in this case
    public static int[] isTwoSum(int[] nums,int target){
        HashMap<Integer,Integer> hashmap=new HashMap<>();
        for(int i=0;i<nums.length;i++){
            int compliment=Math.abs(target-nums[i]);
            if(hashmap.containsKey(compliment)){
                return new int[]{hashmap.get(compliment),i};
            }
            hashmap.put(nums[i],i);
        }
        return new int[]{-1,-1};
    }
    //now we are in the medium level questions in the array
    //Group Anagram
    //I am thinking to implement this problem using backtracking
    public static List<List<String>> groupAnagram(String[] words){
        List<List<String>> result=new ArrayList<>();
        if(words.length==0)
            return result;
        HashMap<String,List<String>> hashmap=new HashMap<>();
        for(String s:words){
            char[] c=s.toCharArray();
            Arrays.sort(c);
            String change=String.valueOf(c);
            if(!hashmap.containsKey(change)){
                hashmap.put(change,new ArrayList<>());
            }
            List<String> temp=hashmap.get(change);
            temp.add(s);
            hashmap.put(change,temp);
        }
        result.addAll(hashmap.values());
        return result;
    }
    //the approch which doesn't use hashmap
    public static List<List<String>> isGroupedAnagram(String[] words){
        List<List<String>> result=new ArrayList<>();
        if(words.length==0)
            return result;
        boolean[] visited=new boolean[words.length];
        int[] count = new int[26]; // move the initialization outside the loop
        for(int i=0;i<words.length;i++){
            if(!visited[i]){
                List<String> temp=new ArrayList<>();
                temp.add(words[i]);
                visited[i]=true;
                Arrays.fill(count, 0); // reset the count array for each new word
                for(int j=i+1;j< words.length;j++){
                    if(!visited[j]&&isAnagram(words[i],words[j])){
                        visited[j]=true;
                        temp.add(words[j]);
                    }
                }
                result.add(new ArrayList<>(temp));
            }
        }
        return result;
    }
    public static boolean isAnagram(String word1,String word2){
        int[] count=new int[26];
        for(int i=0;i<word1.length();i++){
            count[word1.charAt(i)-'a']++;
        }
        for(int i=0;i<word2.length();i++){
            count[word2.charAt(i)-'a']--;
        }
        for(int i=0;i<26;i++){
            if(count[i]!=0)
                return false;
        }
        return true;
    }
    //347. Top K Frequent Elements
    public static int[] topk(int[] nums,int k){
        //for the first approach i am thinking for using priority Queue which could be implemented in O(n) and the space complexicity would
        //be O(n)
        HashMap<Integer,Integer> hm=new HashMap<>();
        for(int i:nums){
            hm.put(i, hm.getOrDefault(i,0)+1);
        }
        List<Integer> result=new ArrayList<>();
        for(Map.Entry<Integer,Integer> map:hm.entrySet()){
            if(map.getValue()>=k){
                result.add(map.getKey());
            }
        }
        int[] myArray = new int[result.size()];
        for (int i = 0; i < result.size(); i++) {
            myArray[i] = result.get(i);
        }
        return myArray;
    }
    //238. Product of Array Except Self
    //time complexcity o(n2)
    public static int[] productOfArray(int[] nums){

        int[] res=new int[nums.length];
        for(int i=0;i<nums.length;i++){
            int result=1;
            for(int j=0;j<nums.length;j++){
                if(i==j)
                    continue;
                result=result*nums[j];
            }
            res[i]=result;
        }
        return res;
    }
    //time complexcity of o(n) space complexcity of 0(n)
    public static int[] productOfArray1(int[] nums){
        int n=nums.length;
        int[] left=new int[n];
        int[] right=new int[n];
        int[] result=new int[n];
        left[0]=1;
        for(int i=1;i<n;i++){
            left[i]=nums[i-1]*left[i-1];
        }
        right[n-1]=1;
        for(int i=n-2;i>=0;i--){
            right[i]=right[i+1]*nums[i+1];
        }
        for(int i=0;i<n;i++){
            result[i]=left[i]*right[i];
        }
        return result;
    }
    //space complexcity o(n)
    public static int[] productOfArray2(int[] nums){
        int[] result=new int[nums.length];
        int pos=1;
        for(int i=0;i<nums.length;i++){
            result[i]=pos;
            pos=pos*nums[i];
        }
        int right=1;
        for(int j=nums.length-1;j>=0;j--){
            result[j]=result[j]*right;
            right=right*nums[j];
        }
        return result;
    }
    //128. Longest Consecutive Sequence
    public static int longestConSeq(int[] nums){
        if(nums.length==0||nums==null)
            return 0;
        int result=1;
        HashSet<Integer> hashset=new HashSet<>();
        for(int i:nums){
            hashset.add(i);
        }
        for(int i:nums){
            if(!hashset.contains(i-1)){
                int current=i;
                int current_length=1;
                while(hashset.contains(current+1)){
                    current_length++;
                    current++;
                }
                result=Math.max(current_length,current);
            }
        }
        return result;
    }
    //Two Pointers
    //Valid palindrome
    public static boolean isvalidPalindrom(String s){
        if(s.length()==0||s==null)
            return true;
        StringBuilder sb=new StringBuilder();
        for(char c:s.toCharArray()){
            if(Character.isLetterOrDigit(c)){
                sb.append(Character.toLowerCase(c));
            }
        }
        String str=sb.toString();
        int i=0,j=str.length()-1;
        while (i<j){
            if(str.charAt(i)!=str.charAt(j)){
                return false;
            }
            i++;
            j--;
        }
        return true;
    }
    //15. 3Sum
    //so this problem is similar to binary search
    public static List<List<Integer>> Sum3(int[] nums){
        List<List<Integer>> result=new ArrayList<>();
        if(nums.length==0||nums==null)
            return result;
        Arrays.sort(nums);
        for(int i=0;i<nums.length-1;i++){
            if(i==0||(i>0&&nums[i]!=nums[i-1])){
                int j=i+1;
                int k=nums.length-1;
                while(j<k){
                    int sum=0-nums[i];
                    if(sum==nums[j]+nums[k]){
                        result.add(Arrays.asList(nums[i],nums[j],nums[k]));
                        while(j<k&&nums[j]==nums[j+1])j=j+1;
                        while(j<k&&nums[k]==nums[k-1])k=k-1;
                        j++;
                        k--;
                    }else if(nums[j]+nums[k]>sum){
                        k=k-1;
                    }else{
                        j=j+1;
                    }
                }
            }
        }
        return result;
    }
    //11. Container With Most Water
    public static int containerWater(int[] nums){
        if(nums.length==0||nums==null){
            return 0;
        }
        int low=0;
        int high=nums.length-1;
        int max=0;
        while(low<high){
           max=Math.max(max,Math.min(nums[low],nums[high])*(high-low));
           if(nums[low]<nums[high])
               low++;
           else
               high--;
        }
        return max;
    }
    //Sliding window
    //Best Time to Buy And Sell Stock
    public static int BestStock(int[] prices){
        if(prices.length==0||prices==null)
            return 0;
        int result=0;
        int min=Integer.MAX_VALUE;
        for(int i=0;i< prices.length;i++){
            if(prices[i]<min){
                min=prices[i];
            }else{
                result=Math.max(result,prices[i]-min);
            }
        }
        return result;
    }
    //Longest Substring Without Repeating Characters
    //for approch one we are going to use array to store the number of times each charcter is repeated
    public static int subWithoutRepeat(String s){
        if(s.length()==0)
            return 0;
        int[] arr=new int[128];
        int start=0;
        int result=0;
        for(int end=0;end<s.length();end++){
            start=Math.max(start,arr[s.charAt(end)]);
            result=Math.max(result,end-start+1);
            arr[s.charAt(end)]=end+1;
        }
        return result;
    }
    //now we are going to implement using hashset
    public static int SubWithoutRepeat(String s){
        if(s.length()==0)
            return 0;
        int end=0;
        int start=0;
        int result=0;
        HashSet<Character> hashSet=new HashSet<>();
        while(end!=s.length()){
            if(!hashSet.contains(s.charAt(end))){
                hashSet.add(s.charAt(end));
                result=Math.max(result, hashSet.size());
                end++;
            }else{
                start++;
                hashSet.remove(s.charAt(start));
            }
        }
        return result;
    }
    //424. Longest Repeating Character Replacement
    public static int longrepeatRep(String s,int limit){
        if(s.length()==0)
            return 0;
        int start=0;
        int result=0;
        int[] arr=new int[26];
        int max=0;
        for(int end=0;end<s.length();end++){
            arr[s.charAt(end)-'A']++;
            int current_char_count=arr[s.charAt(end)-'A'];
            max=Math.max(current_char_count,max);
            while(end-start-max+1>limit){
                start++;
                arr[s.charAt(end)-'A']--;
            }
            result=Math.max(result,end-start+1);
        }
        return result;
    }
    //minimum window SubString
    //the brute force approch
    public static String minWindow(String s,String t){
        String result="";
        int min_length=Integer.MAX_VALUE;
        for(int i=0;i<s.length();i++){
            for(int j=i;j<s.length();j++){
                String current=s.substring(i,j+1);
                boolean check=stringContainsAllCharacters(current,t);
                if(stringContainsAllCharacters(current,t)&&current.length()<min_length){
                    min_length=current.length();
                    result=current;
                }
            }
        }
        return result;
    }
    public static boolean stringContainsAllCharacters(String current,String target){
        HashMap<Character,Integer> hashmap=new HashMap<>();
        for(char c:target.toCharArray()){
            hashmap.put(c,hashmap.getOrDefault(c,0)+1);
        }

        for(char c:current.toCharArray()){
            if(hashmap.containsKey(c)){
                hashmap.put(c,hashmap.get(c)-1);
                if(hashmap.get(c)==0){
                    hashmap.remove(c);
                }
            }
        }
        return hashmap.isEmpty();
    }
    //the above approcj will not would not work for string with more length
    //Below we are using array with the time complexicity of o(n)
    public static String minwinSubString(String original,String target){
        int[] arr=new int[128];
        for(char c:target.toCharArray()){
            arr[c]++;
        }
        int size=target.length();
        int left=0,right=0;
        int minLength=Integer.MAX_VALUE;
        int minleft=0;
        while(right<original.length()){
            char c=original.charAt(right++);
            if(arr[c]>0){
                size--;
            }
            arr[c]--;
            while(size==0){
                if(right-left<minLength){
                    minLength=right-left;
                    minleft=left;
                }
                char l=original.charAt(left++);
                if(arr[l]==0){
                    size++;
                }
                arr[l]++;
            }
        }
        return minLength==Integer.MAX_VALUE?"":original.substring(minleft,minleft+minLength);
    }
    //stack problems
    //there is only one stack problem in the blind 75
    //valid parantesces
    public static boolean isvalidPara(String s){
        if(s.length()==0){
            return true;
        }
        Stack<Character> stack=new Stack<>();
        for(char c:s.toCharArray()){
            if(c=='(')
                stack.push(')');
            else if(c=='{')
                stack.push('}');
            else if(c=='[')
                stack.push(']');
            else if(stack.isEmpty()||stack.pop()!=c)
                return false;
        }
        return stack.isEmpty();
    }
    //now we are working on Binary Search
    //153. Find Minimum in Rotated Sorted Array
    public static int minInRotate(int[] nums){
        int low=0,high=nums.length-1;
        while(low<high){
            int mid=low+(high-low)/2;
            if(nums[mid]>nums[high])
                low=mid+1;
            else if(nums[mid]<nums[high])
                high=mid;
        }
        return nums[high];
    }
    //33. Search in Rotated Sorted Array
    public static int indexSearch(int[] nums,int target){
        //here we are searching the element in the nums array which is possiblt rotated before passing into the method
        int low=0;
        int high=nums.length-1;
        while(low<high){
            int mid=low+(high-low)/2;
            if(nums[mid]>nums[high])
                low=mid+1;
            else if(nums[mid]<nums[high])
                high=mid;
        }
        int first_low=0;
        int first_high=low-1;
        int second_low=low;
        int second_high=nums.length-1;
        if(nums[first_low]<=target && nums[first_high]>=target){
            low=first_low;
            high=first_high;
        }else{
            low=second_low;
            high=second_high;
        }
        while(low<=high){
            int mid=low+(high-low)/2;
            if(nums[mid]==target)
                return mid;
            else if(nums[mid]>target)
                high=mid-1;
            else
                low=mid+1;
        }
        return -1;
    }
    //Linked List
    static class Node{
        int val;
        Node next;
        public Node(int val){
            this.val=val;
        }
        public Node(int val,Node next){
            this.val=val;
            this.next=next;
        }
        public Node(){

        }
    }
    //Reverse a linked list
    public static Node reverseLink(Node node){
        if(node==null)
            return new Node();
        Node prev=null;
        while(node!=null){
            Node current=node.next;
            node.next=prev;
            prev=node;
            node=current;
        }
        return prev;
    }
    public static void printLinkedList(Node n){
        while(n!=null){
            System.out.println(n.val);
            n=n.next;
        }
    }
    //merge two sorted list
    public static Node mergeList(Node list1,Node list2){
        if(list1==null)
            return list2;
        if(list2==null)
            return list1;
        if(list1.val<list2.val){
            list1.next=mergeList(list1.next,list2);
            return list1;
        }else{
            list2.next=mergeList(list1,list2.next);
            return list2;
        }
    }
    //reorder the list
    public static void reOrder(Node node){
        if(node==null||node.next==null)
            return;
        Node first=node;
        Node first_end=null;
        Node second=node;
        Node second_end=node;
        while(second_end!=null&&second_end.next!=null){
            first_end=second;
            second=second.next;
            second_end=second_end.next.next;
        }
        first_end.next=null;
        Node sec=reverseLink(second);
        mergeOfList(first,sec);
    }
    public static void mergeOfList(Node list1,Node list2){
        while(list1!=null){
            Node list1_next=list1.next;
            Node list2_next=list2.next;
            list1.next=list2;
            if(list1_next==null)
                break;
            list2.next=list1_next;
            list1=list1.next;
            list2=list2.next;
        }
    }
    //19. Remove Nth Node From End of List
    public static Node removeNth(Node node,int number){
        Node dummy=new Node(0,node);
        int size=0;
        Node test=dummy;
        while (test!=null){
            size++;
            test=test.next;
        }
        Node fast=dummy;
        for(int i=0;i<size-number-1;i++){
            fast=fast.next;
        }
        fast.next=fast.next.next;
        return dummy.next;
    }
    //141. Linked List Cycle
    public static boolean isCycle(Node node){
        if(node==null)
            return false;
        Node fast=node;
        Node slow=node;
        while(fast!=null&&fast.next!=null){
            fast=fast.next.next;
            slow=slow.next;
            if(slow==fast){
                return true;
            }
        }
        return false;
    }
    //Linked List cycle -II
    //we need to return the node from which the cycle has started
    public static Node isCycle2(Node node){
        if(node==null)
            return null;
        Node fast=node;
        Node slow=node;
        while(fast!=null&&fast.next!=null){
            fast=fast.next.next;
            slow=slow.next;
            if(fast==slow){
                Node slow1=node;
                while(slow1!=slow){
                    slow1=slow1.next;
                    slow=slow.next;
                }
                return slow1;
            }
        }
        return null;
    }
    //merge k Sorted List
    //brute force approch
    public static Node mergek(Node[] lists){
        if(lists.length==0)
            return null;
        if(lists.length==1)
            return lists[0];
        Node dummy=new Node(Integer.MIN_VALUE);
        for(Node node:lists){
            mergeList(dummy,node);
        }
        return dummy.next;
    }
    //optimized version
    public static Node mergeK2(Node[] lists){
        PriorityQueue<Node> pq=new PriorityQueue<>((a,b)->a.val-b.val);
        for(Node list:lists){
            pq.add(list);
        }
        Node dummy=new Node(0);
        Node tail=dummy;
        while(!pq.isEmpty()){
            Node node=pq.poll();
            tail.next=node;
            tail=node;
            if(node.next!=null){
                pq.add(node.next);
            }
        }
        return dummy.next;
    }
    static class Tree{
        int val;
        Tree left;
        Tree right;
        public Tree(int val){
            this.val=val;
            this.left=null;
            this.right=null;
        }
        public Tree(){}
        public Tree(int val,Tree left,Tree right){
            this.val=val;
            this.left=left;
            this.right=right;
        }
    }
    //now we are going ton implemente all the blind75 Tree problems
    //inversted Binary tree
    public static Tree inverstedTree(Tree tree){
        if(tree==null)
            return null;
        final Tree left=tree.left;
        final Tree right=tree.right;
       tree.left=inverstedTree(right);
       tree.right=inverstedTree(left);
       return tree;
    }
    //Depth of a binary tree
    public static int depth(Tree tree){
        if(tree==null)
            return 0;
        Queue<Tree> queue=new LinkedList<>();
        queue.offer(tree);
        int result=0;
        while(!queue.isEmpty()){
            int size=queue.size();
            for(int i=0;i<size;i++){
                Tree temp=queue.poll();
                if(temp.left!=null)
                    queue.offer(temp.left);
                if(temp.right!=null)
                    queue.offer(temp.right);
            }
            result++;
        }
        return result;
    }
    public static int depth2(Tree tree){
        if(tree==null)
            return 0;
        return Math.max(depth2(tree.left),depth2(tree.right))+1;
    }
        public static void preOrder(Tree node){
            if(node==null)
                return;
            preOrder(node.left);
            System.out.println(node.val);
            preOrder(node.right);
        }
        //same tree
    public static boolean isSame(Tree tree1,Tree tree2){
        if(tree1==null||tree2==null)
            return tree1==tree2;
        if(tree1==null&&tree2==null)
            return true;
        if(tree1.val!=tree2.val)
            return false;
        return isSame(tree1.left,tree2.left)&&isSame(tree1.right,tree2.right);
    }
    //a method to check whether one is the subtree of other
    public static boolean isSub(Tree tree1,Tree tree2){
        if(tree1==null)
            return tree1==tree2;
        if(tree1.val== tree2.val){
            if(isSubhelper(tree1,tree2))
                return true;
        }
        return isSub(tree1.left,tree2)||isSub(tree1.right,tree2);
    }
    public static boolean isSubhelper(Tree tree1,Tree tree2){
        if(tree1==null&&tree2==null)
            return true;
        if(tree1==null||tree2==null)
            return tree1==tree2;
        if(tree1.val!=tree2.val)
            return false;
        return isSubhelper(tree1.left,tree2.left)&&isSubhelper(tree1.right,tree2.right);
    }
    //235. Lowest Common Ancestor of a Binary Search Tree
    public static Integer LCM(Tree tree, int left, int right) {
        if (tree == null) {
            return null;
        }
        if (tree.val < left && tree.val < right) {
            return LCM(tree.right, left, right);
        } else if (tree.val > left && tree.val > right) {
            return LCM(tree.left, left, right);
        } else {
            return tree.val;
        }
    }
    //102. Binary Tree Level Order Traversal
    public static List<List<Integer>> levelOrder(Tree tree){
        List<List<Integer>> result=new ArrayList<>();
        if(tree==null)
            return result;
        Queue<Tree> queue=new LinkedList<>();
        queue.offer(tree);
        while(!queue.isEmpty()){
            int size=queue.size();
            List<Integer> temp=new ArrayList<>();
            for(int i=0;i<size;i++){
                Tree t=queue.poll();
                temp.add(t.val);
                if(t.left!=null){
                    queue.offer(t.left);
                }
                if(t.right!=null){
                    queue.offer(t.right);
                }
            }
            result.add(temp);
        }
        return result;
    }
    //validate a binary search tree
    public static boolean isValidBST(Tree tree){
        if(tree==null)
            return true;
        return isValidBSTHelper(tree,null,null);
    }
    public static boolean isValidBSTHelper(Tree tree,Integer min,Integer max){
        if(tree==null)
            return true;
        if((min!=null&&tree.val<=min)||(max!=null&&tree.val>=max))
            return false;
        return isValidBSTHelper(tree.left,min,tree.val)&&isValidBSTHelper(tree.right,tree.val,max);
    }
    //Kth smallest element
    public static int elementK(Tree node,int k){
        if(node==null)
            return -1;
        Stack<Tree> stack=new Stack<>();
        while(node!=null||!stack.isEmpty()){
            while(node!=null){
                stack.push(node);
                node=node.left;
            }
            node=stack.pop();
            if(--k==0)
                break;
            node=node.right;
        }
        return node.val;
    }
    //105. Construct Binary Tree from Preorder and Inorder Traversal
    //this problem is so simple but because of the question i was think of all sort of approchss to implement
    private int preIndex=0;
    public Tree preinOrderTrav(int[] preorder,int[] inorder){
        HashMap<Integer,Integer> hm=new HashMap<>();
        for(int i=0;i<inorder.length;i++){
            hm.put(inorder[i],i );
        }
        return preinOrderTravHelper(preorder,hm,0,preorder.length-1);
    }
    public Tree preinOrderTravHelper(int[] preorder,HashMap<Integer,Integer> hm,int start,int end){
        if(start>end)
            return null;
        int rootVal=preorder[preIndex];
        Tree tree=new Tree(rootVal);
        int rootIndex=hm.get(rootVal);
        tree.left=preinOrderTravHelper(preorder,hm,start,rootIndex-1);
        tree.right=preinOrderTravHelper(preorder,hm,rootIndex+1,end);
        return tree;
    }
    //Reorder Routes to Make All Paths Lead to the City Zero
    public static int roadConstruction(int target,int[][] connections,int num_of_roads){
        HashSet<String> hashset=new HashSet<>();
        HashMap<Integer,Set<Integer>> hashmap=new HashMap<>();
        for(int i=0;i<num_of_roads;i++){
            hashmap.put(i,new HashSet<>());
        }
        for(int[] i:connections){
            hashset.add(i[0]+","+i[1]);
            hashmap.get(i[1]).add(i[0]);
            hashmap.get(i[0]).add(i[1]);
        }
        boolean[] visited=new boolean[num_of_roads];
        Queue<Integer> queue=new LinkedList<>();
        queue.offer(target);
        int result=0;
        while(!queue.isEmpty()){
            int temp= queue.poll();
            for(int i:hashmap.get(temp)){
                if(visited[i])
                    continue;
                visited[i]=true;
                if(!hashset.contains(i+","+temp))
                    result++;
                queue.offer(i);
            }
        }
        return result;
    }
    //this is a hard problem
    //124. Binary Tree Maximum Path Sum
    public static int BinaryMaxPathSum(Tree node){
        if(node==null)
            return 0;
        int[] result=new int[1];
        BinaryMaxPathSumhelper(node,result);
        return result[0];
    }
    public static int BinaryMaxPathSumhelper(Tree node,int[] result){
        if(node==null)
            return 0;
        int left=Math.max(0,BinaryMaxPathSumhelper(node.left,result));
        int right=Math.max(0,BinaryMaxPathSumhelper(node.right,result));
        int val=node.val+left+right;
        result[0]=Math.max(result[0],val);
        return node.val+Math.max(left,right);
    }
    //this is the second hard problem in the class of blind75 trees
    public static String serialize(Tree node){
        if(node==null)
            return "";
        Queue<Tree> queue=new LinkedList<>();
        queue.offer(node);
        StringBuilder sb=new StringBuilder();
        while(queue.isEmpty()){
            Tree temp=queue.poll();
            if(temp==null){
                sb.append("#,");
            }else{
                sb.append(temp.val);
                queue.offer(temp.left);
                queue.offer(temp.right);
            }
        }
        return sb.toString();
    }
    public static Tree deserialize(String s){
        if(s==null||s.length()==0)
            return null;
        Queue<Tree> queue=new LinkedList<>();
        String[] stringarray=s.split(",");
        Tree temp=new Tree(Integer.parseInt(stringarray[0]));
        queue.offer(temp);
        int count=1;
        while(!queue.isEmpty()&&count<stringarray.length){
            Tree tempo=queue.poll();
            if(!stringarray[count].equals("#")){
                Tree temp1=new Tree(Integer.parseInt(stringarray[count]));
                tempo.left=temp1;
                queue.offer(temp1);
            }
            count++;
            if(!stringarray[count].equals("#")){
                Tree temp2=new Tree(Integer.parseInt(stringarray[count]));
                tempo.right=temp2;
                queue.offer(temp2);
            }
            count++;
        }
        return temp;
    }
    // now we are implementing
    //here we are designing trie
    static class trie{
        static List<String> collection;
        public trie(){
            collection=new ArrayList<>();
        }
        public static void insert(String s){
            collection.add(s);
        }
        public static boolean search(String s){
            return collection.contains(s);
        }
        public static boolean startsWith(String s){
            for(String str:collection){
                if(str.substring(0,s.length()).equals(s))
                    return true;
            }
            return false;
        }
    }
    //the same we need to implemennt using arrays
    static class trsNode{
        static class trs{
            trs[] collection;
            boolean isend;
            public trs(){
                collection=new trs[26];
                isend=false;
            }
        }
        private trs node;
        public trsNode(){
            node=new trs();
        }
        public void insert(String s){
            trs temp=node;
            for(int i=0;i<s.length();i++){
                int index=s.charAt(i)-'a';
                if(temp.collection[index]==null){
                    temp.collection[index]=new trs();
                }
                temp=temp.collection[index];
            }
            temp.isend=true;
        }
        public boolean contains(String s){
            trs temp=node;
            for(int i=0;i<s.length();i++){
                int index=s.charAt(i)-'a';
                if(temp.collection[index]==null)
                    return false;
                temp=temp.collection[index];
            }
            return temp.isend;
        }
        public boolean startsWith(String s){
            trs temp=node;
            for(int i=0;i<s.length();i++){
                int index=s.charAt(i)-'a';
                if(temp.collection[index]==null)
                    return false;
                temp=temp.collection[index];
            }
            return true;
        }
    }


    public static void main(String[] args) {
        //test case for 208. Implement Trie (Prefix Tree)
//        trie test=new trie();
//        test.insert("apple");
//        System.out.println(test.search("apple"));
//        System.out.println(test.search("app"));
//        System.out.println(test.startsWith("app"));
//        test.insert("app");
//        System.out.println(test.search("app"));
        //performing the same test but now er are testing it on array design
//        trsNode test1=new trsNode();
//        test1.insert("apple");
//        System.out.println(test1.contains("apple"));
//        System.out.println(test1.contains("app"));
//        System.out.println(test1.startsWith("app"));
//        test1.insert("app");
//        System.out.println(test1.contains("app"));
        //test case for 297. Serialize and Deserialize Binary Tree
//        Tree tree=new Tree(-10);
//        tree.left=new Tree(9);
//        tree.right=new Tree(20);
//        tree.right.left=new Tree(15);
//        tree.right.right=new Tree(7);
////        preOrder(tree);
//        System.out.println("testing");
//        System.out.println(serialize(tree));
        //test case for Binary Tree Maximum Path Sum
//        Tree tree=new Tree(-10);
//        tree.left=new Tree(9);
//        tree.right=new Tree(20);
//        tree.right.left=new Tree(15);
//        tree.right.right=new Tree(7);
//        System.out.println(BinaryMaxPathSum(tree));
        //test case for Reorder Routes to Make All Paths Lead to the City Zero
//        System.out.println(roadConstruction(0,new int[][]{{0,1},{1,3},{2,3},{4,0},{4,5}},6));
        //test case
        //test case for Kth element
//        Tree test=new Tree(3);
//        test.left=new Tree(1);
//        test.right=new Tree(4);
//        test.left.right=new Tree(2);
//        System.out.println(elementK(test,1));
        //test case for LCM
//        Tree tree=new Tree(6);
//        tree.left=new Tree(2);
//        tree.left.left=new Tree(0);
//        tree.left.right=new Tree(4);
//        tree.right=new Tree(8);
//        tree.right.left=new Tree(7);
//        tree.right.right=new Tree(9);
//        tree.left.right.left=new Tree(3);
//        tree.left.right.right=new Tree(5);
//        System.out.println(LCM(tree,0,4));
//        Tree test=new Tree(2);
//        test.left=new Tree(1);
//        test.right=new Tree(3);
//        /test case for valis BST
//        System.out.println(isValidBST(tree));
//        System.out.println(isValidBST(test));
        //test case for level order elements
//        System.out.println(levelOrder(tree));
//        //test case for subtree or not
//        Tree tree1=new Tree(3);
//        tree1.left=new Tree(4);
//        tree1.right=new Tree(5);
//        tree1.left.left=new Tree(1);
//        tree1.left.right=new Tree(2);
//        Tree tree2=new Tree(4);
//        tree2.left=new Tree(1);
//        tree2.right=new Tree(2);
//        System.out.println(isSub(tree1,tree2));
        //test case for whether both trees are same or not
//        Tree tree1=new Tree(1);
//        tree1.left=new Tree(2);
//        tree1.right=new Tree(3);
//        Tree tree2=new Tree(1);
//        tree2.left=new Tree(2);
////        tree2.right=new Tree(3);
//        System.out.println(isSame(tree1,tree2));
        //test case for inverted Tree
//        Tree tree=new Tree(4);
//        tree.left=new Tree(2);
//        tree.right=new Tree(7);
//        tree.left.left=new Tree(1);
//        tree.left.right=new Tree(3);
//        tree.right.left=new Tree(6);
//        tree.right.right=new Tree(9);
//        preOrder(tree);
//        inverstedTree(tree);
//        preOrder(tree);
        //test case for the depth of the tree
//        System.out.println(depth(tree));
//        System.out.println(depth2(tree));
        //test case for merging K sorted List
//        Node node1=new Node(1);
//        node1.next=new Node(4);
//        node1.next.next=new Node(5);
//        Node node2=new Node(1);
//        node2.next=new Node(3);
//        node2.next.next=new Node(4);
//        Node node3=new Node(2);
//        node3.next=new Node(6);
//        Node[] test1={node1,node2,node3};
//        printLinkedList(mergek(test1));
//        printLinkedList(mergeK2(test1));
        //test case to check a cycle is present in a linked list or not
//        Node node=new Node(3);
//        node.next=new Node(2);
//        node.next.next=new Node(0);
//        node.next.next.next=new Node(4);
//        node.next.next.next.next=node.next;
//        System.out.println(isCycle(node));
//        System.out.println(isCycle2(node).val);
        //test case for remove Nth node from the linked list
//        Node node=new Node(1);
//        node.next=new Node(2);
//        node.next.next=new Node(3);
//        node.next.next.next=new Node(4);
//        node.next.next.next.next=new Node(5);
//        printLinkedList(removeNth(node,2));
        //test case for ListNode reorder
//        Node node=new Node(1);
//        node.next=new Node(2);
//        node.next.next=new Node(3);
//        node.next.next.next=new Node(4);
//        //before
//        printLinkedList(node);
//        //after
//        reOrder(node);
//        System.out.println("/////////////");
//        printLinkedList(node);
//        //test case for merging two sorted linkedlist
//        Node node=new Node(1);
//        node.next=new Node(2);
//        node.next.next=new Node(3);
//        Node node1=new Node(1);
//        node1.next=new Node(3);
//        node1.next.next=new Node(4);
//        printLinkedList(mergeList(node,node1));
        //test case for reverse a linkedlist
//        Node node=new Node(1);
//        node.next=new Node(2);
//        node.next.next=new Node(3);
//        node.next.next.next=new Node(4);
//        node.next.next.next.next=new Node(5);
//        //before
//        printLinkedList(node);
//        //after
//        printLinkedList(reverseLink(node));
        //test case for finding the index of the target in rotated array
//        System.out.println(indexSearch(new int[]{4,5,6,7,0,1,2},0));
        //test case for finding min in the rotated array
//        System.out.println(minInRotate(new int[]{3,4,5,1,2}));
        //test case for valid paranteces
//        System.out.println(isvalidPara("{}"));
        //test case for the minimumwindow subString
//        System.out.println(minWindow("ABOBECODEBANC","ABC"));
//        System.out.println(minwinSubString("ABOBECODEBANC","ABC"));
        //test case for longest subString with repeating charcater untill a certain limit
//        System.out.println(longrepeatRep("ABAB",2));
        //test case for longest substring without repeating character
//        System.out.println(subWithoutRepeat("abcabcbb"));
//        System.out.println(subWithoutRepeat("abcabcbb"));
        //test case for when to sell the stock for highest profits
//        System.out.println(BestStock(new int[]{7,1,5,3,6,4}));
        //test case for container with most water
//        System.out.println(containerWater(new int[]{1,8,6,2,5,4,8,3,7}));
        //test case for Three sum
//        System.out.println(Sum3(new int[]{-1,0,1,2,-1,-4}));
        //test case for valid palindrome
//        System.out.println(isvalidPalindrom("A man, a plan, a canal: Panama"));
//        System.out.println(isvalidPalindrom("race a car"));
        //test case most consercative sequence
//        System.out.println(longestConSeq(new int[]{0,3,7,2,5,8,4,6,0,1}));
        //test caes for the product of the array
//        System.out.println(Arrays.toString(productOfArray(new int[]{1,2,3,4})));
//        System.out.println(Arrays.toString(productOfArray1(new int[]{1,2,3,4})));
//        System.out.println(Arrays.toString(productOfArray2(new int[]{1,2,3,4})));
        //test case for the top K frequent
//        System.out.println(Arrays.toString(topk(new int[]{1,1,1,2,2,3},2)));
        //test case for group anagram
//        System.out.println(groupAnagram(new String[]{"eat","tea","tan","ate","nat","bat"}));
//        System.out.println(isGroupedAnagram(new String[]{"eat","tea","tan","ate","nat","bat"}));
//test case for twoSum
//        System.out.println(Arrays.toString(isTwoSum(new int[]{2,7,11,15},9)));
        //test case for method valid anagram
//        System.out.println(isValidAnagram("anagram","nagaram"));
//        System.out.println(validAnagram("rat","car"));
        //test case for conatins Duplicates
//        System.out.println(ContainsDuplicate(new int[]{1,2,3,4,5,5}));
//        System.out.println("case 1 "+containsDuplicates(new int[]{1,2,3,4,5}));
//        System.out.println("case 2 " +containsDuplicates(new int[]{4,2,4,7,5,3,1}));
    }
}
