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

//this is the extension of trie data structure implementation
    //Design Add And Search Words Data Structure
    static class tries{
        class tri{
            tri[] children;
            boolean isEnd;
            public tri(){
                children=new tri[26];
                isEnd=false;
            }
        }
        tri node;
        public tries(){
            node=new tri();
        }
        public void insert(String word){
            tri temp=node;
            for(int i=0;i<word.length();i++){
                int loc=word.charAt(i)-'a';
                if(temp.children[loc]==null)
                    temp.children[loc]=new tri();
                temp=temp.children[loc];
            }
            temp.isEnd=true;
        }
        public boolean search(String word){
            tri temp=node;
            return searchHelper(word,0,temp);
        }
        public boolean searchHelper(String word,int index,tri temp){
            if(index==word.length())
                return temp.isEnd;
                if(word.charAt(index)=='.'){
                    for(int j=0;j<26;j++){
                        if(temp.children[j]!=null&&searchHelper(word,index+1,temp.children[j]))
                            return true;
                    }
                    return false;
                }else{
                    int loc=word.charAt(index)-'a';
                    if(temp.children[loc]==null)
                        return false;
                    return searchHelper(word,index+1,temp.children[loc]);
                }
            }
}
//this is not present in blind 75 but the more harder version is aviable in blind 75 but i htough we can first learn
    //how word search is implemented in the fisrt place
    //word search
    public static boolean wordSearch(char[][] collections,String search){
        if(search.length()==0)
            return true;
        for(int i=0;i<collections.length;i++){
            for(int j=0;j<collections[0].length;j++){
                if(search.charAt(0)==collections[i][j]&&wordSearchHelper(collections,search,0,0,0))
                    return true;
            }
        }
        return false;
    }
    public static boolean wordSearchHelper(char[][] collections,String search,int Index,int i,int j){
        if(Index==search.length())
            return true;
        if(i<0||j<0||i>=collections.length||j>=collections[0].length||collections[i][j]!=search.charAt(Index)||Index>search.length())
            return false;
        char temp=collections[i][j];
        collections[i][j]=' ';
        boolean result=wordSearchHelper(collections,search,Index+1,i+1,j)||
                        wordSearchHelper(collections,search,Index+1,i-1,j)||
                        wordSearchHelper(collections,search,Index+1,i,j+1)||
                        wordSearchHelper(collections,search,Index+1,i,j-1);
        collections[i][j]=temp;
        return result;
    }
    //this is a hard blind75 trees problem
    static class hard{
        hardNode node;
        public hard(){
            node=new hardNode();
        }
        public void insert(String s){
            hardNode temp=node;
            for(int i=0;i<s.length();i++){
                int loc=s.charAt(i)-'a';
                if(temp.children[loc]==null)
                    temp.children[loc]=new hardNode();
                temp=temp.children[loc];
            }
            temp.string=s;
        }
        class hardNode{
            hardNode[] children;
            String string;
            public hardNode(){
                children=new hardNode[26];
                string=null;
            }
        }
    }
    public static List<String> wordSearch2(char[][] board,String[] words){
        List<String> result=new ArrayList<>();
        if(words.length==0)
            return result;
        hard node=new hard();
        for(String word:words){
            node.insert(word);
        }
        for(int i=0;i< board.length;i++){
            for(int j=0;j<board[0].length;j++){
                wordSearch2Helper(board,node.node,i,j,result);
            }
        }
        return result;
    }
    public static void wordSearch2Helper(char[][] board, hard.hardNode temp, int i, int j, List<String> result){
        char index=board[i][j];
        if(index=='#'||temp.children[index-'a']==null)
            return;
        temp=temp.children[index-'a'];
        if(temp.string!=null){
            result.add(temp.string);
            temp.string=null;
        }
        board[i][j]='#';
        if(i>0) wordSearch2Helper(board,temp,i-1,j,result);
        if(j>0) wordSearch2Helper(board,temp,i,j-1,result);
        if(i<board.length-1)wordSearch2Helper(board,temp,i+1,j,result);
        if(j<board[0].length-1)wordSearch2Helper(board,temp,i,j+1,result);
        board[i][j]=index;
    }
    //Backtracking
    //combination Sum
    public static List<List<Integer>> combi(int[] numbers,int target){
        List<List<Integer>> result=new ArrayList<>();
        if(numbers.length==0)
            return result;
        combiHelper(numbers,target,result,0,new ArrayList<>());
        return result;
    }
    public static void combiHelper(int[] numbers,int target,List<List<Integer>> result,int index,List<Integer> temp){
        if(target==0){
            result.add(new ArrayList<>(temp));
            return;
        }
        if(target<0){
            return;
        }
        for(int i=index;i<numbers.length;i++){
            temp.add(numbers[i]);
            combiHelper(numbers,target-numbers[i],result,i,temp);
            temp.remove(temp.size()-1);
        }
    }
    //word Search
    public static boolean wordSearch2(char[][] letters,String word){
        if(word.length()==0)
            return true;
        for(int i=0;i< letters.length;i++){
            for(int j=0;j<letters[0].length;j++){
                if(word.charAt(0)==letters[i][j]&&wordSearchHelper2(letters,word,i,j,0))
                    return true;
            }
        }
        return false;
    }
    public static boolean wordSearchHelper2(char[][] letters,String word,int i,int j,int index){
        if(index==word.length())
            return true;
        if(i<0||j<0||i>=letters.length||j>=letters[0].length||word.charAt(index)!=letters[i][j]||index>word.length())
            return false;
        boolean result=wordSearchHelper2(letters,word,i+1,j,index+1)||
                wordSearchHelper2(letters,word,i-1,j,index+1)||
                wordSearchHelper2(letters,word,i,j+1,index+1)||
                wordSearchHelper2(letters,word,i,j-1,index+1);
        return result;
    }
    //hard problem heaps/priorityQueue
    static class MedianFinder{
        PriorityQueue<Integer> pq1;
        PriorityQueue<Integer> pq2;
        Boolean isEven=true;
        public MedianFinder(){
            pq1=new PriorityQueue<>(Collections.reverseOrder());
            pq2=new PriorityQueue<>();
        }
        public void addNum(int num){
            if(isEven){
                pq2.offer(num);
                pq1.offer(pq2.poll());
            }else{
                pq1.offer(num);
                pq2.offer(pq1.poll());
            }
            isEven=!(isEven);
        }
        public double median(){
            if(isEven)
                return (pq1.peek()+pq2.peek())/2.0;
            else
                return pq1.peek();
        }
    }
    //Graphs Blind75
    //no of island
    //i would solve this problem in both the approches
    //the first approch is the dfs approch
    public static int noOfisland(char[][] grid){
        if(grid.length==0||grid==null)
            return 0;
        int result=0;
        for(int i=0;i<grid.length;i++){
            for(int j=0;j<grid[0].length;j++){
                if(grid[i][j]=='1'){
                    noOfislandHelper(i,j,grid);
                    result++;
                }
            }
        }
        return result;
    }
    public static void noOfislandHelper(int i,int j,char[][] grid){
        if(i<0||i>=grid.length||j<0||j>=grid[0].length||grid[i][j]=='0')
            return;
        grid[i][j]='0';
        noOfislandHelper(i-1,j,grid);
        noOfislandHelper(i+1,j,grid);
        noOfislandHelper(i,j-1,grid);
        noOfislandHelper(i,j+1,grid);
    }
    //implemenyting the same problem using bfs  approch
    static int[][] dir={{1,0},{-1,0},{0,1},{0,-1}};
    public static int islandNumber(char[][] grid){
        if(grid.length==0||grid==null)
            return 0;
        Queue<int[]> queue=new LinkedList<>();
        int result=0;
        for(int i=0;i<grid.length;i++){
            for(int j=0;j<grid[0].length;j++){
                if(grid[i][j]=='1'){
                    queue.offer(new int[]{i,j});
                    islandHelper(queue,grid);
                    result++;
                }
            }
        }
        return result;
    }
    public static void islandHelper(Queue<int[]> temp,char[][] grid){
        while(!temp.isEmpty()){
            int[] cur=temp.poll();
            int x=cur[0];
            int y=cur[1];
            for(int[] d:dir){
                int a=x+d[0];
                int b=y+d[1];
                if(a>=0&&a<grid.length&&b>=0&&b<grid[0].length&&grid[a][b]=='1'){
                    grid[a][b]='0';
                    temp.offer(new int[]{a,b});
                }
            }
        }
    }
    //clone graph
    static class graphNode{
        int val;
        List<graphNode> neighbours;
        public graphNode(){
            val=0;
            neighbours=new ArrayList<>();
        }
        public graphNode(int val){
            this.val=val;
            neighbours=new ArrayList<>();
        }
        public graphNode(int val,List<graphNode> neighbours){
            this.val=val;
            this.neighbours=neighbours;
        }
    }
    private static HashMap<Integer,graphNode> hashmap=new HashMap<>();
    public static graphNode cloneGraph(graphNode test){
        if(test==null)
            return null;
        if(hashmap.containsKey(test.val))
            return hashmap.get(test.val);
        graphNode n=new graphNode(test.val,new ArrayList<>());
        hashmap.put(test.val,n);
        for(graphNode no:test.neighbours){
            n.neighbours.add(cloneGraph(no));
        }
        return n;
    }
    public static boolean areEqual(graphNode n, graphNode n1, Set<graphNode> visited) {
        if (n == null && n1 == null) {
            return true;
        }
        if (n == null || n1 == null) {
            return false;
        }
        if (n.val != n1.val) {
            return false;
        }
        if (visited.contains(n)) {
            return true;
        }
        visited.add(n);
        int size = n.neighbours.size();
        if (size != n1.neighbours.size()) {
            return false;
        }
        for (int i = 0; i < size; i++) {
            if (!areEqual(n.neighbours.get(i), n1.neighbours.get(i), visited)) {
                return false;
            }
        }
        return true;
    }
//417. Pacific Atlantic Water Flow
    //this is an interseting problem
    public static List<List<Integer>> pacificAtlantic(int[][] grid){
        List<List<Integer>> result=new ArrayList<>();
        if(grid.length==0||grid==null)
            return result;
        int len1=grid.length;
        int len2=grid[0].length;
        boolean[][] paciVisited=new boolean[len1][len2];
        boolean[][] atlaVisited=new boolean[len1][len2];
        for(int i=0;i<len1;i++){
            paciAtlaHelper(i,0,paciVisited,Integer.MIN_VALUE,grid);
            paciAtlaHelper(i,len1-1,atlaVisited,Integer.MIN_VALUE,grid);
        }
        for(int j=0;j<len2;j++){
            paciAtlaHelper(0,j,paciVisited,Integer.MIN_VALUE,grid);
            paciAtlaHelper(len2-1,j,atlaVisited,Integer.MAX_VALUE,grid);
        }
        for(int i=0;i<len1;i++){
            for(int j=0;j<len2;j++){
                if(paciVisited[i][j]&&atlaVisited[i][j]){
                    List<Integer> temp=new ArrayList<>();
                    temp.add(i);
                    temp.add(j);
                    result.add(temp);
                }
            }
        }
        return result;
    }
    public static void paciAtlaHelper(int i,int j,boolean[][] visited,int max,int[][] grid){
        if(i<0||i>=grid.length||j<0||j>=grid[0].length||visited[i][j]||grid[i][j]<max)
            return;
        visited[i][j]=true;
        paciAtlaHelper(i+1,j,visited,grid[i][j],grid);
        paciAtlaHelper(i-1,j,visited,grid[i][j],grid);
        paciAtlaHelper(i,j+1,visited,grid[i][j],grid);
        paciAtlaHelper(i,j-1,visited,grid[i][j],grid);
    }
    //Course Schedule
    public static boolean courseScheduler(int numOfCourses,int[][] prereq){
        if(numOfCourses==0)
            return true;
        List<List<Integer>> list=new ArrayList<>();
        for(int i=0;i<numOfCourses;i++){
            list.add(new ArrayList<>());
        }
        int[] depth=new int[numOfCourses];
        for(int[] p:prereq){
            int course=p[0];
            int pr=p[1];
            list.get(pr).add(course);
            depth[course]++;
        }
        Queue<Integer> queue=new LinkedList<>();
        for(int i=0;i<numOfCourses;i++){
            if(depth[i]==0)
                queue.offer(i);
        }
        int result=0;
        while(!queue.isEmpty()){
            int course= queue.poll();
            result++;
            for(int i:list.get(course)){
                if(--depth[i]==0){
                    queue.offer(i);
                }
            }
        }
        return result==numOfCourses;
    }
    //Dynamic Programming
    //70. Climbing Stairs

    public static int climbStairs(int n){
        int[] result=new int[n];
        result[0]=1;
        result[1]=2;
        for(int i=2;i<n;i++){
            result[i]=result[i-1]+result[i-2];
        }
        return result[n-1];
    }
    //if in case we want to implement this without using arrays
    public static int starisClimb(int n){
        if(n==0)
            return 0;
        else if(n==1)
            return 1;
        else if(n==2)
            return 2;
        int result=0;
        int first=1;
        int second=2;
        for(int i=2;i<n;i++){
            result=first+second;
            first=second;
            second=result;
        }
        return result;
    }
    //198. House Robber
    public static int houseRobber(int[] house){
        if(house.length==2)
            return Math.max(house[0],house[1]);
        int[] dp=new int[house.length+1];
        dp[0]=0;
        dp[1]=house[0];
        for(int i=1;i<house.length;i++){
            dp[i+1]=Math.max(dp[i],house[i]+dp[i-1]);
        }
        return dp[house.length];
    }
    //house robber -2
    public static int houseRobber2(int[] nums){
        if(nums.length==1)
            return nums[0];
        if(nums.length==2)
            return Math.max(nums[0],nums[1]);
        return Math.max(houseRobber2Helper(0,nums.length-1,nums),houseRobber2Helper(1,nums.length,nums));
    }
    public static int houseRobber2Helper(int left,int right,int[] nums){
        int[] dp=new int[right];
        dp[left]=nums[left];
        dp[left+1]=Math.max(dp[left],dp[left+1]);
        for(int i=left+2;i<right;i++){
            dp[i]=Math.max(dp[i-1],dp[i-2]+nums[i]);
        }
        return dp[right-1];
    }
    //longest palindrome SubString
    //instead of solving in dynamic approch we are going to solve using sliding window approch
    public static String longPali(String s){
        if(s.length()==0||s==null)
            return "";
        int start=0,end=0;
        for(int i=0;i<s.length();i++){
            int low=helperLongpali(i,i,s);
            int high=helperLongpali(i,i+1,s);
            int max=Math.max(low,high);
            if(max>end-start){
                start=i-((max-1)/2);
                end=i+(max/2);
            }
        }
        return s.substring(start,end+1);
    }
    public static int helperLongpali(int start,int end,String s){
        if(start>end)
            return 0;
        while(start>=0&&end<s.length()&&s.charAt(start)==s.charAt(end)){
            start--;
            end++;
        }
        return end-start-1;
    }
    //now we are going to implement the same problem using dynamic programming
    public static String longestPali(String s){
        if(s == null || s.length() == 0) {
            return "";
        }
        int len = s.length();
        boolean[][] visited = new boolean[len][len];
        for(int i = 0; i < len; i++) {
            visited[i][i] = true;
        }
        int maxLength = 1;
        int start = 0;
        for(int i = 2; i <= len; i++) {
            for(int j = 0; j < len - i + 1; j++) {
                int k = j + i - 1;
                if(s.charAt(j) == s.charAt(k) && (i == 2 || visited[j+1][k-1])) {
                    visited[j][k] = true;
                    if(i > maxLength) {
                        maxLength = i;
                        start = j;
                    }
                }
            }
        }
        return s.substring(start, start + maxLength);
    }
    //647. Palindromic Substrings
    //this is same same the above approch
    //we are going to use dynamic programming to solve the problem
    public static int paliSubString(String s){
        if(s.length()==0)
            return 0;
        if(s.length()==1)
            return 1;
        int count=0;
        int length=s.length();
        boolean[][] dp=new boolean[length][length];
        for (int i=0;i<length;i++){
            dp[i][i]=true;
            count++;
        }
        for(int len=2;len<=length;len++){
            for(int i=0;i<length-len+1;i++){
                int j=len-i-1;
                System.out.println(j);
                if(s.charAt(i)==s.charAt(j)){
                    if(len==2||dp[i+1][j-1]){
                        count++;
                        dp[i][j]=true;
                    }
                }
            }
        }
        return count;
    }
    //decode ways
    public static int decodeWays(String s){
        if(s.length()==0)
            return 0;
        int[] dp=new int[s.length()+1];
        dp[0]=1;
        dp[1]=s.charAt(0)=='0'?0:1;
        for(int i=2;i<=s.length();i++){
            int len1=Integer.valueOf(s.substring(i-1,i));
            int len2=Integer.valueOf(s.substring(i-2,i));
            if(len1>0)
                dp[i]+=dp[i-1];
            if(len2>=10&&len2<=26)
                dp[i]+=dp[i-2];
        }
        return dp[s.length()];
    }
    //coin change
    //using dynamic programming
    public static int coinChange(int[] coins,int target){
        int[] dp=new int[target+1];
        Arrays.fill(dp,Integer.MAX_VALUE);
        dp[0]=0;
        for(int i=1;i<=target;i++){
            for(int j=0;j<coins.length;j++){
                if(i>=coins[j]){
                    dp[i]=Math.min(dp[i],dp[i-coins[j]]+1);
                }
            }
        }
        return dp[target];
    }
    //implementing using top down approch
    public static int changeCoin(int[] coins,int target){
        if(target==0)
            return 0;
        HashMap<Integer,Integer> hm=new HashMap<>();
        changeCoinHelper(coins,target,hm);
        return hm.get(target)!=Integer.MAX_VALUE?hm.get(target):-1;
    }
    public static int changeCoinHelper(int[] coins,int target,HashMap<Integer,Integer> hm){
        if(target==0)
            return 0;
        if(hm.containsKey(target))
            return hm.get(target);
        int min=Integer.MAX_VALUE;
        for(int i=0;i<coins.length;i++){
            if(coins[i]>target)
                continue;
            int val=changeCoinHelper(coins,target-coins[i],hm);
            if(val<min){
                min=val;
            }
        }
        min=(min==Integer.MAX_VALUE?min:min+1);
        hm.put(target,min);
        return min;
    }
    //max product subArray
    public static int maxProduct(int[] array){
        if(array.length==0)
            return 0;
        if(array.length==1)
            return array[0];
        int[] dp=new int[array.length+1];
        int max=array[0];
        int min=array[0];
        int result=array[0];
        for(int i=1;i<array.length;i++){
            int tempmax=Math.max(max*array[i],Math.max(min*array[i],array[i]));
            min=Math.min(min*array[i],Math.min(max*array[i],array[i]));
            max=tempmax;
            result=Math.max(result,max);
        }
        return result;
    }
    //word break
    public static boolean wordBreak(List<String> dict,String target){
        if(target.length()==0)
            return true;
        boolean[] dp=new boolean[target.length()+1];
        dp[0]=true;
        for(int i=1;i<=target.length();i++){
            for(int j=0;j< i;j++){
                if(dict.contains(target.substring(j,i))&&dp[j]){
                    dp[i]=true;
                    break;
                }
            }
        }
        return dp[target.length()];
    }
    //300. Longest Increasing Subsequence
    public static int longSubSequence(int[] nums){
        if(nums.length==0)
            return 0;
        else if(nums.length==1)
            return 1;
        int[] dp=new int[nums.length];
        Arrays.fill(dp,1);
        int result=0;
        for(int i=1;i<nums.length;i++){
            for(int j=0;j<i;j++){
                if(nums[j]<nums[i]){
                    int max=Math.max(dp[j]+1,dp[i]);
                    dp[i]=max;
                }
            }
            result=Math.max(result,dp[i]);
        }
        return result;
    }
    public static void main(String[] args) {
        //longest subSequence
        System.out.println(longSubSequence(new int[]{10,9,2,5,3,7,101,18}));
        //word container
//        System.out.println(wordBreak(Arrays.asList(new String[]{"leet", "code"}),"leetcode"));
        //Max product
//        System.out.println(maxProduct(new int[]{2,3,-2,4,-2,0,20}));
        //coins change
//        System.out.println(coinChange(new int[]{1,3,5},11));
//        System.out.println(changeCoin(new int[]{1,3,5},11));
        //test case for decode ways
//        System.out.println(decodeWays("226"));
        //test case for the palidromic subStrinf
//        System.out.println(paliSubString("aaa"));
        //test case for longest String palindrome
//        System.out.println(longPali("babad"));
//        System.out.println(longestPali("babad"));
        //test case for house robber 2
//        System.out.println(houseRobber2(new int[]{1,2,3,1}));
        //test case for house Robber
//        System.out.println(houseRobber(new int[]{1,2,3,1}));
        //test case for Staris Climber
//        System.out.println(climbStairs(20));
//        System.out.println(starisClimb(20));
        //test case for Course Scheduler
//        System.out.println(courseScheduler(4,new int[][]{{1,0},{0,1}}));
        //test case for Atlantic and Pacific Ocean
//        System.out.println(pacificAtlantic(new int[][]{{1,2,2,3,5},{3,2,3,4,4},{2,4,5,3,1},{2,4,5,3,1},{5,1,1,2,4}}));
        //test case for 417. Pacific Atlantic Water Flow
        //test case for cloning a graph
//        graphNode test1=new graphNode(1);
//        graphNode test2=new graphNode(2);
//        graphNode test3=new graphNode(3);
//        graphNode test4=new graphNode(4);
//        test1.neighbours.add(test2);
//        test1.neighbours.add(test4);
//        test2.neighbours.add(test3);
//        test2.neighbours.add(test1);
//        test3.neighbours.add(test2);
//        test3.neighbours.add(test4);
//        test4.neighbours.add(test1);
//        test4.neighbours.add(test3);
//        System.out.println(areEqual(test1,cloneGraph(test1),new HashSet<>()));
        //test case for noOfIsland
//        System.out.println(noOfisland(new char[][]{{'1','1','1','1','0'},{'1','1','0','1','0'},
//                {'1','1','0','0','0'},{'0','0','0','0','0'}}));
        //test case 2 for no of Island
//        System.out.println(islandNumber(new char[][]{{'1','1','1','1','0'},{'1','1','0','1','0'},
//                {'1','1','0','0','0'},{'0','0','0','0','0'}}));
        //test case for PriorityQueue
//        MedianFinder test=new MedianFinder();
//        test.addNum(1);
//        test.addNum(2);
//        System.out.println(test.median());
//        test.addNum(3);
//        System.out.println(test.median());
        //test case for Word Search
//        System.out.println(wordSearch2(new char[][]{{'a','b','c','e'},{'s','f','c','s'},{'a','d','e','e'}},"abcced"));
        //test case for combination Sum
//        System.out.println(combi(new int[]{2,3,6,7},7));
        //test case for word search 2
//        System.out.println(wordSearch2(new char[][]{{'o','a','a','n'},{'e','t','a','e'},{'i','h','k','r'},{'i','f','l','v'}},new String[]{"oath","pea","eat","rain"}));
        //test case for word search version 1
//        System.out.println(wordSearch(new char[][]{{'a','b','c','e'},{'s','f','c','s'},{'a','d','e','e'}},"abcced"));
        //test case for 211. Design Add and Search Words Data Structure
//        tries test=new tries();
//        test.insert("bad");
//        test.insert("dad");
//        test.insert("mad");
//        System.out.println(test.search("pad"));
//        System.out.println(test.search("bad"));
//        System.out.println(test.search(".ad"));
//        System.out.println(test.search("b.."));

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
