import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.*;

public class Recursion {
    //47. Permutations II
//    Given a collection of numbers, nums, that might contain duplicates, return all possible unique permutations in any order.
    public static List<List<Integer>> permuteUnique(int[] nums) {
        List<List<Integer>> result=new ArrayList<>();
        if(nums.length==0||nums==null)
            return result;
        boolean[] checkList=new boolean[nums.length];
        Arrays.sort(nums);
        helper(result,new ArrayList<>(),nums,checkList);
        return result;
    }
    public static void helper(List<List<Integer>> result,List<Integer> Temp,int[] nums,boolean[] checkList){
        if(Temp.size()==nums.length){
            result.add(new ArrayList<>(Temp));
        }
        for(int i=0;i<nums.length;i++){
            if(checkList[i])
                continue;
            if(i>0&&nums[i]==nums[i-1]&&!checkList[i-1])
                continue;
            checkList[i]=true;
            Temp.add(nums[i]);
            helper(result,Temp,nums,checkList);
            checkList[i]=false;
            Temp.remove(Temp.size()-1);
        }
    }
//17. Letter Combinations of a Phone Number
    public static List<String> phoneNum(String digits){
        List<String> result=new ArrayList<>();
        if(digits==null||digits.length()==0)
            return result;
        String[] phone={
                "0",
                "1",
                "abc",
                "def",
                "ghi",
                "jkl",
                "mno",
                "pqrs",
                "tuv",
                "wxyz"
        };
        phoneNumHelper(result,digits,phone,0,"");
        return result;
    }
    public static void phoneNumHelper(List<String> result,String digits,String[] phone,int index,String temp){
        if(index==digits.length()){
            result.add(temp);
            return;
        }
        String s=phone[digits.charAt(index)-'0'];
        for(int i=0;i<s.length();i++){
            phoneNumHelper(result,digits,phone,index+1,temp+s.charAt(i));
        }
    }
    //22. Generate Parentheses
    public static List<String> genPar(int limit){
        List<String> result=new ArrayList<>();
        if(limit==0)
            return result;
        genParHelper(result,"",limit,0,0);
        return result;
    }
    public static void genParHelper(List<String> result,String temp,int limit,int left,int right){
        if(temp.length()==limit*2){
            result.add(new String(temp));
            return;
        }
        if(temp.length()>limit*2)
            return;
        if(left<limit)
            genParHelper(result,temp+"(",limit,left+1,right);
        if(right<left)
            genParHelper(result,temp+")",limit,left,right+1);
    }
    //76 Word Search
    public static boolean wordS(String word,char[][] grid){
        if(word.length()==0||word==null)
            return true;
        for(int i=0;i<grid.length;i++){
            for(int j=0;j<grid[0].length;j++){
                if(grid[i][j]==word.charAt(0)&& wordHelper(word,grid,i,j,0))
                    return true;
            }
        }
        return false;
    }
    public static boolean wordHelper(String word,char[][] grid,int i,int j,int count){
        if(count==word.length())
            return true;
        if(i<0||i>=grid.length||j<0||j>=grid[0].length||word.charAt(count)!=grid[i][j]||count>word.length())
            return false;
        char c=grid[i][j];
        grid[i][j]=' ';
        boolean result= wordHelper(word,grid,i-1,j,count+1)||wordHelper(word,grid,i+1,j,count+1)
               ||wordHelper(word,grid,i,j-1,count+1)||wordHelper(word,grid,i,j+1,count+1);
        grid[i][j]=c;
        return result;
    }
    public static void main(String[] args) {
        System.out.println(wordS("abcced",new char[][]{{'a','b','c','e'},{'s','f','c','s'},{'a','d','e','e'}}));
//        System.out.println(genPar(3));
//        System.out.println(phoneNum("23"));
//        System.out.println(permuteUnique(new int[]{1,2,3}));
    }
}
