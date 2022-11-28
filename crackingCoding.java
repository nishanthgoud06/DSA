import java.util.HashMap;

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

    public static void main(String[] args) {
        System.out.println(urlify("Mr John Smith "));
//        System.out.println(Unique("abca"));
        System.out.println(permu("abcd","dcat"));
    }
}