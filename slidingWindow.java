import java.util.*;

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
//    Given a string s and an integer k, return the length of the longest substring in s you can create that contains a single capital letter. You may apply k operations to s where a single operation consists of selecting one capital letter and modifying it to be another capital letter.
//            Note: s will only contain uppercase alphabetical characters.
    public static int charUpdate(String str,int limit){
        if(str.length()==0 || str==null)
            return 0;
        int[] ch=new int[26];
        int end=0,start=0,result=0,max=0;
        while(end<str.length()){
            char endChar=str.charAt(end);
            ch[endChar-'A']++;
            max=Math.max(ch[endChar-'A'],max);
            if(end-start+1-max>limit){
                char startChar=str.charAt(start);
                ch[startChar]--;
                start++;
            }
            result=Math.max(result,end-start+1);
            end++;
        }
        return result;
    }
//    Find all substrings of a string that are a permutation of another string
    public static List<String> subStr(String str1,String str2){
        List<String> result=new ArrayList<>();
        if(str2.length()==0)
            return result;
        int[] ch=new int[26];
        for(char c:str2.toCharArray()){
            ch[c-'a']++;
        }
        for(int i=0;i<str1.length()-str2.length()+1;i++){
            int[] ch2=new int[26];
            String str= str1.substring(i,i+str2.length());
            for(char c:str.toCharArray()){
                ch2[c-'a']++;
            }
            int count=0;
            for(char c:str2.toCharArray()){
                if(ch2[c-'a']==ch[c-'a']){
                    count++;
                }
            }
            if(count==str2.length()){
                result.add(str);
            }
        }
        return result;
    }
//    Find the longest substring of a string containing distinct characters
    public static int uniqueSub(String str){
        if(str.length()==0)
            return 0;
        HashSet<Character> hashset=new HashSet<>();
        int end=0,start=0,result=0;
        while(end<str.length()){
            char endChar=str.charAt(end);
            while(hashset.contains(endChar)){
                char startChar=str.charAt(start);
                hashset.remove(startChar);
                start++;
            }
            hashset.add(endChar);
            result=Math.max(end-start+1,result);
            end++;
        }
        return result;
    }
//    Find maximum length sequence of continuous ones
    public static int maximumLength(int[] nums){
        if(nums.length==0)
            return 0;
        int start=0,end=0,prevIndex=-1,max=0,count=0,result=0;
        while(end<nums.length){
            if(nums[end]==0){
                prevIndex=end;
                count++;
            }
            if(count==2){
                while(nums[start]!=0){
                    start++;
                }
                count=1;
            }
            if(end-start+1>max){
                max=end-start+1;
                result=prevIndex;
            }
            end++;
        }
    return result;
    }
    public static List<Integer> seqOne(int[] nums){
        List<Integer> result=new ArrayList<>();
        if(nums.length==0)
            return result;
        int count=0;
        for(int i:nums){
            if(i==0){
                count++;
            }
        }
        for(int i=0;i<count;i++){
            result.add(helperseqOne(i,nums));
        }
        return result;
    }
    public static int helperseqOne(int limit,int[] nums){
        int end=0,start=0,max=0,result=0,count=0;
        while(end<nums.length){
            if(nums[end]==0){
                count++;
            }
            if(count>limit){
                while(nums[start]!=0){
                    start++;
                }
                start++;
                count--;
            }
            if(end-start+1>max){
                max=end-start+1;
            }
            end++;
        }
        return max;
    }
//    Find minimum sum subarray of size `k`
    public static int minSumSubArray(int[] nums,int limit){
        return 0;
    }
    //Find a subarray having the given sum in an integer array
    public static List<Integer> subArrayTarget(int[] nums,int target){
        List<Integer> list = new ArrayList<>();
        int current = 0;
        int high = 0;
        for (int low = 0; low < nums.length; low++) {
            while (high < nums.length && current + nums[high] <= target) {
                current += nums[high];
                high++;
            }
            if (current == target) {
                for (int i = low; i < high; i++) {
                    list.add(nums[i]);
                }
                return list;
            }
            current -= nums[low];
        }
        return list;
    }
//    Find the smallest subarray length whose sum of elements is greater than `k`
    public static int smallestSubtraget(int[] nums,int target){
        if(nums.length==0)
            return 0;
        int start=0,end=0,result=Integer.MAX_VALUE,current=0;
        while(end<nums.length){
            current+=nums[end];
            while(current>target){
                result=Math.min(result,end-start+1);
                current-=nums[start];
                start++;
            }
            end++;
        }
        return result;
    }
    //Find the count of distinct elements in every subarray of size `k`
    public static List<Integer> distElement(int[] nums,int limit){
        if(nums.length==0)
            return null;
        int start=0,end=0;
        HashMap<Integer,Integer> hashmap=new HashMap<>();
        List<Integer> result=new ArrayList<>();
        while(end<nums.length){
            hashmap.put(nums[end],hashmap.getOrDefault(nums[end],0)+1);
            if((end-start+1)%limit==0){
                result.add(hashmap.size());
                int starting=nums[start];
                hashmap.put(starting,hashmap.get(starting)-1);
                if(hashmap.get(starting)==0)
                    hashmap.remove(starting);
                start++;
            }
            end++;
        }
        return result;
    }
    //Least Unique Elements
    public static int leastUnique(int[] nums, int limit) {
        if (nums.length == 0 || limit == 0)
            return 0;

        HashMap<Integer, Integer> hashmap = new HashMap<>();
        int uniqueValue = Integer.MAX_VALUE; // Initialize to 0 because no elements are removed initially

        for(int i:nums)
            hashmap.put(i,hashmap.getOrDefault(i,0)+1);
//        System.out.println(hashmap);
        for (int start = 0; start <= nums.length-limit; start++) {
            int current = start;
            int count = 0;
            int end=start;
            while (current < nums.length && count < limit) {
                if (hashmap.containsKey(nums[current])) {
                    hashmap.put(nums[current], hashmap.get(nums[current]) - 1);
                    if (hashmap.get(nums[current]) == 0) {
                        hashmap.remove(nums[current]);
                    }
                    count++;
                }
                current++;
            }
            // After removing `limit` elements, update `uniqueValue`
            System.out.println(hashmap);
            System.out.println("//");
            uniqueValue = Math.min(uniqueValue, hashmap.size());
            int urrent=end;
            while(urrent<end+limit){
                hashmap.put(nums[urrent],hashmap.getOrDefault(nums[urrent],0)+1);
                urrent++;
            }
            System.out.println(hashmap);
            System.out.println("///////");
        }
        return uniqueValue;
    }
//    Find duplicates within a range `k` in an array
    public static List<String> findDuplicate(int[] nums,int range){
        if(nums.length==0 || range==0)
            return null;
        List<String> result=new ArrayList<>();
        HashMap<Integer,Integer> hashMap=new HashMap<>();
        for(int i=0;i<nums.length;i++){
            int value=nums[i];
            hashMap.put(value, hashMap.getOrDefault(value,0)+1);
            if(hashMap.get(value)>1){
                result.add(new String("found the duplicate key in the range of "+(i+1) +" and "+(i-range)+" the duplicate value is "+ value));
            }
        }
        return result;
    }
//    Print all subarrays of an array having distinct elements
    public static List<List<Integer>> printAllSub(int[] nums){
        List<List<Integer>> result = new ArrayList<>();
        if (nums.length == 0)
            return result;

        int start = 0;
        int end = 0;
        List<Integer> temp = new ArrayList<>();

        while (end < nums.length) {
            if (!temp.contains(nums[end])) {
                temp.add(nums[end]);
                end++;
            } else {
                result.add(new ArrayList<>(temp));
                temp.remove(0);
                start++;
            }
        }
        return result;
    }
//    Find minimum sum subarray of size `k`
    public static int[] minSum(int[] nums,int k){
        if(nums.length==0)
            return nums;
        int start=0,end=0,min=Integer.MAX_VALUE,current=0,startIndex=0;
        while(end<nums.length){
            current+=nums[end];
            if((end-start+1)%k==0){
                if(current<min){
                    min=current;
                    startIndex=start;
                }
                current-=nums[start];
                start++;
            }
            end++;
        }
        return Arrays.copyOfRange(nums,startIndex,startIndex+k);
    }
    public static void main(String[] args) {
        System.out.println(Arrays.toString(minSum(new int[]{10, 4, 2, 5, 6, 3, 8, 1},3)));
//        System.out.println(printAllSub(new int[]{5, 2, 3, 5, 4, 3}));
//        List<String> test=findDuplicate(new int[]{5, 6, 8, 2, 4, 6, 9},4);
//        for(String s:test)
//            System.out.println(s);
//        System.out.println(leastUnique(new int[]{1,4,3,3},2));
//        System.out.println(leastUnique(new int[]{3, 1, 2, 2, 3, 4, 4, 5},3));
//        System.out.println(distElement(new int[]{2, 1, 2, 3, 2, 1, 4, 5},5));
//        System.out.println(smallestSubtraget(new int[]{1, 2, 3, 4, 5, 6, 7, 8},21));
//        System.out.println(subArrayTarget(new int[]{2, 6, 0, 9, 7, 3, 1, 4, 1, 10},15));
//        System.out.println(seqOne(new int[]{1, 1, 0, 1, 1, 0, 1, 1, 1, 1, 0, 0}));
//        System.out.println(maximumLength(new int[]{0, 0, 1, 0, 1, 1, 1, 0, 1, 1}));
//        System.out.println(uniqueSub("findlongestsubstring"));
//        System.out.println(subStr("xyyzxzyzxxyz","xyz"));
//        System.out.println(charUpdate("AABBB",2));
//        System.out.println(minSub(7,new int[]{2,3,1,2,4,3}));
//        System.out.println(subArrayProduct(new int[]{10,5,2,6},100));
//        System.out.println(findLength("A",2));
//        System.out.println(longestSubArrayKDistinct("AAAHHBBBBBBBBBBC",2));
//        System.out.println(smallestSubArray(new int[]{4,2,1,7,8,1,2,8,1,0},8));
//        System.out.println(MaxSubArray(new int[]{4,2,1,7,8,1,2,8,1,0},3));
    }
}
