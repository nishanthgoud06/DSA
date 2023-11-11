import java.sql.Array;
import java.util.*;

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
    public static void main(String[] args) {
        //test case for max number in a certain range
//        System.out.println(maxValue(new int[]{10, 5, 2, 7, 8, 7},3));
        System.out.println(maxValue2(new int[]{10, 5, 2, 7, 8, 7},3));
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
