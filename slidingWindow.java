import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class slidingWindow {
    //normal sliding window problem
    public static int MaxSubArray(int[] a,int limit){
        int max=0;
        int cur=0;
        for(int i=0;i<a.length;i++){
            cur+=a[i];
            if(i>=limit-1){
                max=Math.max(cur,max);
                cur-=a[i-(limit-1)];
            }
        }
        return max;
    }
    public static int smallestSubArray(int[] a,int target){
        int current=0;
        int min=Integer.MAX_VALUE;
        int window_start=0;
        for(int window_end=0;window_end<a.length;window_end++){
            current+=a[window_end];
            while(current>=target) {
                min = Math.min(min, window_end - window_start+1);
                current -= a[window_start];
                window_start++;
            }
        }
        return min;
    }
    public static int findLength(String str, int k) {
        int windowStart = 0, maxLength = 0;
        Map<Character, Integer> charFrequencyMap = new HashMap<>();
        for (int windowEnd = 0; windowEnd < str.length(); windowEnd++) {
            char rightChar = str.charAt(windowEnd);
            charFrequencyMap.put(rightChar, charFrequencyMap.getOrDefault(rightChar, 0) + 1);
            while (charFrequencyMap.size() > k) {
                char leftChar = str.charAt(windowStart);
                charFrequencyMap.put(leftChar, charFrequencyMap.get(leftChar) - 1);
                if (charFrequencyMap.get(leftChar) == 0) {
                    charFrequencyMap.remove(leftChar);
                }
                windowStart++;
            }
            maxLength = Math.max(maxLength, windowEnd - windowStart + 1);
        }
        return maxLength;
    }
    public static int longestSubArrayKDistinct(String s,int limit){
        HashMap<Character,Integer> hm=new HashMap<>();
        int window_start=0;
        int max=0;
        for(int window_end=0;window_end<s.length();window_end++){
            char c=s.charAt(window_end);
            hm.put(c,hm.getOrDefault(c,0)+1);
            while(hm.size()>limit){
                char c1=s.charAt(window_start);
                hm.put(c1,hm.get(c1)-1);
                if(hm.get(c1)==0){
                    hm.remove(c1);
                }
                window_start++;
            }
            max=Math.max(max,window_end-window_start+1);
        }
        return max;
    }
    //713. Subarray Product Less Than K
    public static int subArrayProduct(int[] arr,int limit){
        int window_start=0;
        int product=arr[0];
        int window_end=1;
        int result=0;
        if(product<limit)
            result++;
        while(window_end<arr.length){
            product=product*arr[window_end];
            if(product<limit){
                result=result+window_end-window_start+1;
            }else{
                while(product>=limit){
                    product=product/arr[window_start];
                    window_start++;
                }
                result=result+window_end-window_start+1;
            }
            window_end++;
        }
        return result;
    }
//    209. Minimum Size Subarray Sum
//    Given an array of positive integers nums and a positive integer target, return the minimal length of a subarray
//    whose sum is greater than or equal to target. If there is no such subarray, return 0 instead.
//    Example 1:
//    Input: target = 7, nums = [2,3,1,2,4,3]
    public static int minSub(int target,int[] nums){
        if(nums.length==0 || nums==null)
            return 0;
        int window_start=0,window_end=0,value=0,min=Integer.MAX_VALUE;
        while(window_end<nums.length){
            value+=nums[window_end];
            while(value>=target){
                min=Math.min(min,window_end-window_start+1);
                value-=nums[window_start];
                window_start++;
            }
            window_end++;
        }
        return min;
    }
//    438. Find All Anagrams in a String
public static List<Integer> findAnagrams(String s, String p) {
    List<Integer> result=new ArrayList<>();
    if(p.length()>s.length())
        return result;
    HashMap<Character,Integer> hm=new HashMap<>();
    for(char c:p.toCharArray()){
        hm.put(c,hm.getOrDefault(c,0)+1);
    }
    int count=hm.size();
    int window_start=0;
    int window_end=0;
    while(window_end<s.length()){
        char c=s.charAt(window_end);
        if(hm.containsKey(c)){
            hm.put(c,hm.get(c)-1);
            if(hm.get(c)==0)
                count--;
        }
        window_end++;
        while(count==0){
            char c1=s.charAt(window_start);
            if(hm.containsKey(c1)){
                hm.put(c1,hm.get(c1)+1);
                if(hm.get(c1)>0)
                    count++;
            }
            if(window_end-window_start==p.length())
                result.add(window_start);
            window_start++;
        }
    }
    return result;
}
    public static void main(String[] args) {
        System.out.println(minSub(7,new int[]{2,3,1,2,4,3}));
//        System.out.println(subArrayProduct(new int[]{10,5,2,6},100));
//        System.out.println(findLength("AAAHHBBBBBBIBBBC",2));
//        System.out.println(longestSubArrayKDistinct("AAAHHBBBBBBBBBBC",2));
//        System.out.println(smallestSubArray(new int[]{4,2,1,7,8,1,2,8,1,0},8));
//        System.out.println(MaxSubArray(new int[]{4,2,1,7,8,1,2,8,1,0},3));
    }
}
