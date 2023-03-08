import java.lang.reflect.Array;
import java.util.Arrays;

public class crackCode {
    //this class includes all the problem of how to crack coding interview
    //chapter-1 Arrays
    //1.isUnique
    public static boolean isUnique(String s){
        if(s.length()==0)
            return true;
        int[] count=new int[26];
        for(int i=0;i<s.length();i++){
            count[s.charAt(i)-'a']++;
        }
        for(int i=0;i<26;i++){
            if(i>1){
                return false;
            }
        }
        return true;
    }
    ////check parameter
    public static boolean ispermutation(String s1,String s2){
        if(s1.length()!=s2.length()){
            return false;
        }
        int[] count=new int[26];
        for(int i=0;i<s1.length();i++){
            count[s1.charAt(i)-'a']++;
        }
        for(int i=0;i<s2.length();i++){
            count[s2.charAt(i)-'a']--;
        }
        for(int i=0;i<26;i++){
            if(i>1){
                return false;
            }
        }
        return true;
    }
    //urlify
    public static String urlify(String s){
        StringBuilder sb=new StringBuilder();
        for(int i=0;i<s.length();i++){
            if(s.charAt(i)==' '){
                sb.append("%20");
            }else{
                sb.append(s.charAt(i));
            }
        }
        return sb.toString();
    }
    //palindrome permuattion
    public static boolean isPali(String s1,String s2){
        if(s1.length()!=s2.length())
            return false;
        int i=0,j=s1.length()-1;
        while(i<j){
            if(s1.charAt(i)!=s2.charAt(j))
                return false;
            i++;
            j--;
        }
        return true;
    }
    //one Away
    public static boolean Oneway(String s1,String s2){
        if(s1.length()-s2.length()>1)
            return false;
        int[] c=new int[26];
        int limit=1;
        for(int i=0;i<s1.length();i++){
            c[s1.charAt(i)-'a']++;
        }
        for(int i=0;i<s2.length();i++){
            c[s2.charAt(i)-'a']--;
        }
        for(int i=0;i<s1.length();i++){
            if(c[s1.charAt(i)-'a']!=0){
                if(limit==1){
                    limit--;
                }else{
                    return false;
                }
            }
        }
        return true;
    }
    //String compression
    public static String stringCompersion(String s1){
        if(s1.length()==0)
            return "";
        StringBuilder sb=new StringBuilder();
        int count=0;
        for(int i=0;i<s1.length();i++){
            count++;
            if(count+1==s1.length()||s1.charAt(i)!=s1.charAt(i+1)){
                sb.append(s1.charAt(i)+count);
                count=0;
            }
        }
        return sb.length()<s1.length()?sb.toString():s1;
    }
    //Zero Matrix
    public static int[][] zeroMatrix(int[][] matrix){
        boolean[] row=new boolean[matrix.length];
        boolean[] col=new boolean[matrix[0].length];
        for(int i=0;i<matrix.length;i++){
            for(int j=0;j<matrix[0].length;j++){
                if(matrix[i][j]==0){
                    row[i]=true;
                    col[i]=true;
                }
            }
        }
        for(int i=0;i<row.length;i++){
            if(row[i]){
                changeRow(i,matrix);
            }
        }
        for(int i=0;i<col.length;i++){
            if(col[i]){
                changeCol(i,matrix);
            }
        }
        return matrix;
    }
    public static void changeRow(int i,int[][] matrix){
        for(int j=0;j<matrix[0].length;j++){
            matrix[i][j]=0;
        }
    }
    public static void changeCol(int i,int[][] matrix){
        for(int j=0;j<matrix.length;j++){
            matrix[j][i]=0;
        }
    }
    //String Rotation
    public static boolean stringRotation(String s1,String s2){
        char[] c=s2.toCharArray();
        s2= String.valueOf(c);
        return s1.equals(s2);
    }
}
