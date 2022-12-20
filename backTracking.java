import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class backTracking {
    //Subsets
    public static List<List<Integer>> subsets(int[] a){
        List<List<Integer>> result=new ArrayList<>();
        if(a.length==0||a==null)
            return result;
        subsetsHelper(a,result,new ArrayList<>(),0);
        return result;
    }
    public static void subsetsHelper(int[] a,List<List<Integer>> result,List<Integer> temp,int index){
        result.add(new ArrayList<>(temp));
        for(int i=index;i<a.length;i++){
            temp.add(a[i]);
            subsetsHelper(a,result,temp,i+1);
            temp.remove(temp.size()-1);
        }
    }
    //subsets 2
    public static List<List<Integer>> subsetstwo(int[] a){
        List<List<Integer>> result=new ArrayList<>();
        if(a.length==0||a==null)
            return result;
        Arrays.sort(a);
        subsetstwoHelper(result,a,0,new ArrayList<>());
        return result;
    }
    public static void subsetstwoHelper(List<List<Integer>> result,int[] a,int index,List<Integer> temp){
        result.add(new ArrayList<>(temp));
        for(int i=index;i<a.length;i++){
            if(i>index && a[i]==a[i-1])
                continue;
            temp.add(a[i]);
            subsetstwoHelper(result,a,i+1,temp);
            temp.remove(temp.size()-1);
        }
    }
    //permutations
    public static List<List<Integer>> permu(int[] a){
        List<List<Integer>> result=new ArrayList<>();
        if(a.length==0||a==null)
            return result;
        permuHelper(result,a,new ArrayList<>());
        return result;
    }
    public static void permuHelper(List<List<Integer>> result,int[] a,List<Integer> temp){
        if(temp.size()==a.length)
            result.add(new ArrayList<>(temp));
        for(int i=0;i<a.length;i++){
            if(temp.contains(a[i]))
                continue;
            temp.add(a[i]);
            permuHelper(result,a,temp);
            temp.remove(temp.size()-1);
        }
    }
    //permutations-ii
    public static List<List<Integer>> ptwo(int[] numbers){
        List<List<Integer>> result=new ArrayList<>();
        if(numbers.length==0||numbers==null)
            return result;
        Arrays.sort(numbers);
        ptwoHelper(result,numbers,new ArrayList<>(),new boolean[numbers.length]);
        return result;
    }
    public static void ptwoHelper(List<List<Integer>> result,int[] numbers,List<Integer> temp,boolean[] b){
        if(temp.size()==numbers.length)
            result.add(new ArrayList<>(temp));
        for(int i=0;i< numbers.length;i++){
            if(b[i]||i>0&&numbers[i]==numbers[i-1]&&!b[i-1])
                continue;
            b[i]=true;
            temp.add(numbers[i]);
            ptwoHelper(result,numbers,temp,b);
            b[i]=false;
            temp.remove(temp.size()-1);
        }
    }
    //combination sum
    public static List<List<Integer>> combi(int[] collection,int target){
        List<List<Integer>> result=new ArrayList<>();
        if(target==0)
            return result;
        combiHelper(collection,target,result,new ArrayList<>(),0);
        return result;
    }
    public static void combiHelper(int[] collection,int target,List<List<Integer>> result,List<Integer> temp,int index){
        if(target<0)
            return;
        if(target==0){
            result.add(new ArrayList<>(temp));
            return;
        }
        for(int i=index;i<collection.length;i++){
            temp.add(collection[i]);
            combiHelper(collection,target-collection[i],result,temp,i);
            temp.remove(temp.size()-1);
        }
    }
    //combinatation ii
    public static List<List<Integer>> combitwo(int[] numbers,int target){
        List<List<Integer>> result=new ArrayList<>();
        if(numbers.length==0||target==0)
            return result;
        Arrays.sort(numbers);
        combitwoHelper(numbers,target,0,new ArrayList<>(),result);
        return result;
    }
    public static void combitwoHelper(int[] numbers,int target,int index,List<Integer> temp,List<List<Integer>> result){
        if(target==0){
            result.add(new ArrayList<>(temp));
            return;
        }
        if(target<0)
            return;
        for(int i=index;i< numbers.length;i++){
            if(i>index&&numbers[i]==numbers[i-1])
                continue;
            temp.add(numbers[i]);
            combitwoHelper(numbers,target-numbers[i],i+1,temp,result);
            temp.remove(temp.size()-1);
        }
    }
    //palindrome partitioning
    public static boolean ispali(String s,int low,int high){
        while(low<high){
            if(s.charAt(low++)!=s.charAt(high--))
                return false;
        }
        return true;
    }
    public static List<List<String>> palipart(String s){
        List<List<String>> result=new LinkedList<>();
        if(s==null||s.length()==0)
            return result;
        palipartHelper(result,s,new ArrayList<>(),0);
        return result;
    }
    public static void palipartHelper(List<List<String>> result,String s,List<String> temp,int index){
        if(index==s.length())
            result.add(new ArrayList<>(temp));
        for(int i=index;i<s.length();i++){
            if(ispali(s,index,i)){
                temp.add(s.substring(index,i+1));
                palipartHelper(result,s,temp,i+1);
                temp.remove(temp.size()-1);
            }
        }
    }
    public static void main(String[] args) {
        System.out.println(palipart("aab"));
//        System.out.println(combitwo(new int[]{10,1,2,7,6,1,5},8));
//        System.out.println(combi(new int[]{2,3,6,7},7));
//        System.out.println(ptwo(new int[]{1,1,2}));
//        System.out.println(permu(new int[]{1,2,3}));
//      System.out.println(subsetstwo(new int[]{1,2,2}));
//      System.out.println(subsets(new int[]{1,2,3}));
    }
}
