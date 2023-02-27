import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;

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
    public static void main(String[] args) {
//test case for twoSum
        System.out.println(Arrays.toString(isTwoSum(new int[]{2,7,11,15},9)));
        //test case for method valid anagram
//        System.out.println(isValidAnagram("anagram","nagaram"));
//        System.out.println(validAnagram("rat","car"));
        //test case for conatins Duplicates
//        System.out.println(ContainsDuplicate(new int[]{1,2,3,4,5,5}));
//        System.out.println("case 1 "+containsDuplicates(new int[]{1,2,3,4,5}));
//        System.out.println("case 2 " +containsDuplicates(new int[]{4,2,4,7,5,3,1}));
    }
}
