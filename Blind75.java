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
    public static void main(String[] args) {
        //test case most consercative sequence
        System.out.println(longestConSeq(new int[]{0,3,7,2,5,8,4,6,0,1}));
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
