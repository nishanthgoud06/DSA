import java.util.*;

public class crackingCoding {
//    Is Unique: Implement an algorithm to determine if a string has all unique characters. What if you
//    cannot use additional data structures?
    public static boolean Unique(String s){
        boolean[] ch=new boolean[123];
        for(int i=0;i<123;i++){
            ch[i]=false;
        }
        for(char c:s.toCharArray()){
            if(!ch[c]){
                ch[c]=true;
            }else{
                return false;
            }
        }
        return true;
    }
//    Check Permutation: Given two strings, write a method to decide if one is a permutation of the
//    other.
    public static boolean permu(String s1,String s2){
        if(s1.length()!=s2.length())
            return false;
        int[] l=new int[256];
        int[] k=new int[256];
        for(int i=0;i<s1.length();i++){
            l[s1.charAt(i)]++;
            k[s2.charAt(i)]++;
        }
        for(int i=0;i<256;i++){
            if(l[i]!=k[i])
                return false;
        }
        return true;
    }
//    URLify: Write a method to replace all spaces in a string with '%20: You may assume that the string
//    has sufficient space at the end to hold the additional characters, and that you are given the "true"
//    length of the string. (Note: If implementing in Java, please use a character array so that you can
//    perform this operation in place.)
    public static String urlify(String s){
        String str = "Mr John Smith   ";
        // Trim the given string
        str = str.trim();
        // Replace All space (unicode is \\s) to %20
        str = str.replaceAll("\\s", "%20");
        // Display the result
        return  str;
    }
//    Palindrome Permutation: Given a string, write a function to check if it is a permutation of a palindrome.
//    A palindrome is a word or phrase that is the same forwards and backwards. A permutation
//    is a rea rrangement of letters. The palindrome does not need to be limited to just dictionary words.
public static List<List<Integer>> threeSum(int[] nums) {
    //using two pointers
    //time comp-o(n*2)
    //space com-o(1)
    List<List<Integer>> result=new ArrayList<>();
    if(nums.length==0)
        return result;
    Arrays.sort(nums);
    for(int i=0;i<nums.length-2;i++){
        if(i==0||(i>0 && nums[i] != nums[i-1])){
            int low=i+1;
            int high=nums.length-1;
            int sum=0-nums[i];
            while(low<high){
                if(nums[low]+nums[high]==sum){
                    result.add(Arrays.asList(nums[i],nums[low],nums[high]));
                    while(low<high &&nums[low]==nums[low+1])low=low+1;
                    while(low<high&&nums[high]==nums[high-1])high=high-1;
                    low++;
                    high--;
                }else if(nums[low]+nums[high]>sum){
                    high--;
                }else{
                    low++;
                }
            }
        }
    }
    return result;
    //using extra space
    // time com-o(n*2)
    //space com-o(n)
//        Set<List<Integer>> res  = new HashSet<>();
//         if(nums.length==0) return new ArrayList<>(res);
//         Arrays.sort(nums);
//         for(int i=0; i<nums.length-2;i++){
//             int j =i+1;
//            int  k = nums.length-1;
//             while(j<k){
//                 int sum = nums[i]+nums[j]+nums[k];
//                 if(sum==0)res.add(Arrays.asList(nums[i],nums[j++],nums[k--]));
//                 else if (sum >0) k--;
//                 else if (sum<0) j++;
//             }

//         }
//         return new ArrayList<>(res);
}
    public static List<Integer> findAnagrams(String s, String p) {
        Map<Character, Integer> map = new HashMap<>();
        List<Integer> result = new ArrayList<>();
        if(s==null || s.length()<p.length()) {
            return result;
        }

        for(char ch : p.toCharArray()) {
            map.put(ch, map.getOrDefault(ch,0)+1);
        }

        int counter = map.size();

        int end =0;
        int start=0;

        while(end<s.length()) {
            char ch = s.charAt(end);

            if(map.containsKey(ch)) {
                map.put(ch, map.get(ch)-1);

                if(map.get(ch)==0) {
                    counter--;
                }
            }

            while(counter==0) {
                char startChar = s.charAt(start);

                if(map.containsKey(startChar)) {
                    map.put(startChar, map.get(startChar)+1);

                    if(map.get(startChar)>0) {
                        counter++;
                    }
                }

                if(end-start+1==p.length()) {
                    result.add(start);
                }

                start++;
            }
            end++;
        }

        return result;

    }
    public static void main(String[] args) {
        System.out.println(urlify("Mr John Smith "));
//        System.out.println(Unique("abca"));
        System.out.println(permu("abcd","dcat"));
    }
}
