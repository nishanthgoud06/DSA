import java.util.HashMap;
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
    public static void main(String[] args) {
        System.out.println(findLength("AAAHHBBBBBBIBBBC",2));
        System.out.println(longestSubArrayKDistinct("AAAHHBBBBBBBBBBC",2));
//        System.out.println(smallestSubArray(new int[]{4,2,1,7,8,1,2,8,1,0},8));
//        System.out.println(MaxSubArray(new int[]{4,2,1,7,8,1,2,8,1,0},3));
    }
}
