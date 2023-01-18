import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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

    public static void main(String[] args) {
        System.out.println(permuteUnique(new int[]{1,2,3}));
    }
}
