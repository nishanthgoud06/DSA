import java.util.*;

public class dynamicProgramming {
    //fibonacci series
    public static int fiBIntRec(int n){
        //in the recusion version of execution the time complexity is o(n**2) which is not ideal as if we try to exectute large numbers the time required will be increased as well
        if(n==0)
            return 0;
        if(n==1)
            return 1;
        return fiBIntRec(n-1)+fiBIntRec(n-2);
    }
    public static int fibInt(int n){
        //here we have used dynamic programming to calculate the fib numbe
        // the time complexity of the dynamic programming is o(n) as we are stroring the values in the array so when ever the number is called it will take less time to excute
        int[] a=new int[n+2];
        a[0]=0;
        a[1]=1;
        int i;
        for(i=2;i<=n;i++){
             a[i]=a[i-1]+a[i-2];
        }
        return a[n];
    }
    //grid traveller
    //point to be noted when were you see their is somekind of pattern that need to be followed then you mostly use recuison
    public static int GridTraveller(int n1,int n2){
        //recursion method of implementation
        if(n1==0 ||n2==0)
            return 0;
        if(n1==1 &&n2==1)
            return 1;
        return GridTraveller(n1-1,n2)+GridTraveller(n1,n2-1);
    }
    public static int DGridTraveller(int n1,int n2){
        //dynamic version
        int[][] count=new int[n1][n2];
        for(int i=0;i<n1;i++){
            count[i][0]=1;
        }
        for(int j=0;j<n2;j++){
            count[0][j]=1;
        }
        for(int i=1;i<n1;i++){
            for(int j=1;j<n2;j++){
                count[i][j]=count[i][j-1]+count[i-1][j];
            }
        }
        return count[n1-1][n2-1];
    }
    public static int grid(int a,int b,int[][] mat){
        //this sort of programming is i can relate to more
        if(a==1||b==1)
            return 1;
        if(mat[a][b]==0){
            mat[a][b]=grid(a,b-1,mat)+grid(a-1,b,mat);
        }
        return mat[a][b];
    }
    //CANSUM
    public static Boolean canSum(int target,int[] array){
        if(target==0)
            return true;
        if(target<0)
            return false;
        for(int i=0;i<array.length;i++){
            int diff=target-array[i];
            if(canSum(diff,array)==true){
                return true;
            }
        }
        return false;
    }
    //using dynamic programming
    public static Boolean Dcansum(int target,int[] values){
        HashSet<Integer> hs=new HashSet<>();
        if(target ==0)
            return true;
        if(target<0)
            return false;
        for(int i=0;i<values.length;i++){
            if(hs.contains(values[i]))
                return true;
            int temp=target-values[i];
            return Dcansum(temp,values);
        }
        return false;
    }
    public static void doit(List<List<Integer>> result,List<Integer> temp,int[] elements,int target,int index){
        if(target<0)
            return;
        if(target==0){
            result.add(new ArrayList<>(temp));
        }
        for(int i=index;i<elements.length;i++){
            int a=target-elements[i];
            temp.add(elements[i]);
            doit(result,temp,elements,a,i);
            temp.remove(temp.size()-1);
        }
    }
    public static List<List<Integer>> combineSum(int[] elements, int target){
        //here we have used back tracking so that we can find all posiible combination of number
        // present in the element that could add up to the target
        Arrays.sort(elements);
        List<List<Integer>> result=new ArrayList<>();
        doit(result,new ArrayList<Integer>(),elements,target,0);
        return result;
    }
    //here we are checking weather we can construct a target string from the list of string
    public static Boolean canCon(String[] items,String target){
        if(target.isEmpty()){
            return true;
        }
        for(String i:items){
            if(target.indexOf(i)==0){
                String suffix=target.substring(i.length());
                if(canCon(items,suffix)==true){
                    return true;
                }
            }
        }
        return false;
    }
    public static Boolean DcanCon(String[] items,String target,HashMap<String,Boolean> hashMap){
        //for reducing the time complexity we have used hashmap so that we can skip doing the same opertaion again.
        if(target.isEmpty()){
            return true;
        }
        if(hashMap.containsKey(target))
            return hashMap.get(target);
        for(String i:items){
            if(target.indexOf(i)==0){
                String suffix=target.substring(i.length());
                if(DcanCon(items,suffix,hashMap)==true){
                    hashMap.put(target,true);
                    return true;
                }
            }
        }
        return false;
        //for the bruteforce approach of the problem canConstruct
        //m=target.length
        //n=elements.length
//        brute force                   memorized
//         O(n.pow(m) * m);                 O(n *m**2) time
//        O(m**2) space                     O(m**2) space
    }
    public static int Countcon(String[] items,String target,HashMap<String,Integer> hm){
        if(hm.containsKey(target))
            return hm.get(target);
        if(target.isEmpty())
            return 1;
        int count=0;
        for(String s:items){
            if(target.indexOf(s)==0){
                int ini=Countcon(items,target.substring(s.length()),hm);
                count+=ini;
            }
        }
        hm.put(target,count);
        return count;
    }
    public static int fibi(int n){
        int[] arrray=new int[n +1];
        arrray[0]=0;
        arrray[1]=1;
        for(int i=2;i<=n;i++){
            arrray[i]=arrray[i-1]+arrray[i-2];
        }
        return arrray[n];
    }
    public static int gridTraveller(int m,int n){
        if(m==1||n==1)
            return 1;
        return gridTraveller(m-1,n)+gridTraveller(m,n-1);
    }
    //now we are going to code all the above using tabulation technique
    //the tecnique is simple you just have to craete a empty array with the length
    // of the target and populate with constant value you need to start from the index
    // and add the values from the index and change the value from that that position
    public static boolean incsum(int target, int[] items){
        boolean[] value=new boolean[target+1];
        Arrays.fill(value,false);
        value[0]=true;
        for(int i=0;i<=target;i++){
            if(value[i]){
                for(int j:items){
                    if(i+j< value.length){
                        value[i+j]=true;
                    }
                }
            }
        }
        return value[target];
    }
    //for the next program we are actually trying to find the nu ber of values required to find the target value
//    public static List<List<Integer>> Hosum(int target,int[] values){
//        List<List> elements=new ArrayList<>(target+1);
//        for(List i:elements){
//            i.add(null);
//        }
//        elements.add(0,new ArrayList<Integer>());
//        for(int i=0;i<target;i++){
//            if(elements.get(i)==null){
//                for(int j:values){
//                    if(i+j<target){
//                        elements.set(i + j, Collections.singletonList(j));
//                    }
//                }
//            }
//        }
//        return elements[target];
//    }
    //for this below program we will be using backtracking to print all possible combinations of target value.
    //for the below program if can repeat the combination
    public static List<List<Integer>>  combination(int[] values,int target){
        Arrays.sort(values);
        List<List<Integer>> result=new ArrayList<>();
        find(result,new ArrayList<Integer>(),target,values,0);
        return result;
    }
    public static void find(List<List<Integer>> result,List<Integer> temp,int target,int[] values,int index){
            if(target<0)
                return;
            if(target==0)
                result.add(new ArrayList<>(temp));
            for(int i=index;i<values.length;i++){
                temp.add(values[i]);
                find(result,temp,target-values[i],values,i);
                temp.remove(temp.size()-1);
            }
    }
    //below is the same program as combination but here we are not allowed to repeat the combinatio
    //which means the values inside the list should only be used once
     static List<List<Integer>> combi(int[] values,int target){
        Arrays.sort(values);
        List<List<Integer>> results=new ArrayList<>();
        combiE(results,new ArrayList<Integer>(),values,target,0);
        return results;
    }
    private static void combiE(List<List<Integer>> results,List<Integer> temp,int[] values,int target,int index){
        if(target<0)
            return;
        if(target==0)
            results.add(new ArrayList<>(temp));
        for(int i=index;i<values.length;i++){
            if(i>index&&(values[i]==values[i-1]))
                continue;
            temp.add(values[i]);
            combiE(results,temp,values,target-values[i],i+1);
            temp.remove(temp.size()-1);
        }
    }
    //below is the program where we find all possible combination of target value form the given set of
    // candidates using dynamic programming following tabulation strategy
    public static List<Integer> tcansum(int[] candidates, int target){
        if(target==0)
            return null;
        ArrayList<Integer>[] result=new ArrayList[target+1];
        result[0]=new ArrayList<Integer>();
        for(int i=0;i<candidates.length;i++){
            ArrayList<Integer> ab=new ArrayList<>();
            int a=candidates[i];
            ab.add(a);
            result[a]=ab;
        }
        for(int i=1;i<=target;i++){
            if(result[i]==null)
                continue;
            for(int j=0;j<candidates.length;j++){
                int n=candidates[j];
                //System.out.println(n);
                int target_cube=i+n;

                if(target_cube >target)
                    continue;
               // System.out.println(target_cube);
                if(result[target_cube]==null)
                    result[target_cube]= new ArrayList<Integer>();
                ArrayList<Integer> src=result[i];
                ArrayList<Integer>desc=result[target_cube];
                desc.clear();
                desc.addAll(src);
                desc.add(n);
                //System.out.println(desc.toString());
                if(target_cube==target){
                    return result[target];
                }

            }
        }
        if (result[target] == null) return null;


        return result[target];

    }
    //the below program i have found in google
    static int[] howSum(int targetSum, int[] numbers) {

        // **** sanity checks ****
        if (targetSum == 0) return null;


        ArrayList<Integer>[] table = new ArrayList[targetSum + 1];

        table[0] = new ArrayList<Integer>();

        for (int i = 0; i < numbers.length; i++) {
            ArrayList<Integer> al = new ArrayList<Integer>();
            int nums = numbers[i];
            al.add(nums);
            table[nums] = al;
        }

        // **** iterate through the table ****
        for (int i = 1; i <= targetSum; i++) {

            // ???? ????
            // System.out.println("<<< i: " + i);

            // **** skip this entry (if needed) ****
            if (table[i] == null)
                continue;

            // **** loop through the numbers array ****
            for (int j = 0; j < numbers.length; j++) {

                // **** for ease of use ****
                int num = numbers[j];

                // ???? ????
                // System.out.println("<<< num: " + num);

                // **** compute target index ****
                int ndx = i + num;

                // **** skip this index (out of range) ****
                if (ndx > targetSum)
                    continue;

                // ???? ????
                 //System.out.println("<<< ndx: " + ndx);

                // **** initialize list (if needed) ****
                if (table[ndx] == null)
                    table[ndx] = new ArrayList<Integer>();

                // **** copy all elements from table[i] to table[ndx] ****
                ArrayList<Integer> src = table[i];
                ArrayList<Integer> dst = table[ndx];
                dst.clear();
                dst.addAll(src);

                // **** add current element to table[ndx] ****
                dst.add(num);

                // ???? ????
                 //System.out.println("<<< dst: " + dst.toString());

                // **** check if done ****
                if (ndx == targetSum) {

                    // **** convert List<Integer> to int[] ****
                    int[] arr = dst.stream().mapToInt( x -> x).toArray();

                    // **** return int[] ****
                    return arr;
                }
            }

            // ???? ????
            // System.out.println("<<< table: " + Arrays.toString(table));
        }

        // **** check if no ansswer was found ****
        if (table[targetSum] == null) return null;

        // **** get last list in the table ****
        ArrayList<Integer> lst = table[targetSum];

        // **** convert List<Integer> to int[] ****
        int[] arr = lst.stream().mapToInt( x -> x).toArray();

        // **** return int[] ****
        return arr;
    }
    public static boolean canCons(String[] candidate,String target){
        boolean[] result=new boolean[target.length()+1];
        Arrays.fill(result,false);
        result[0]=true;
        for(int i=0;i<target.length();i++){
            if(result[i]){
                for(String s:candidate){
                    if(target.substring(i,i+s.length())==s){
                        result[i+s.length()]=true;
                    }
                }
            }
        }
        return result[target.length()-1];
    }
    //here we are implementing tabulation to find the whether a string can be found with the
    // elements in the candiadtes set if possible it should return true
    //here first trying to fill our boolean array values then we iterate through the
    // candidate key then we select the one element in the candiadte key and finds lits length and
    // we get the substring of the target value if the target value of the length of the element matches we mark the length of the candidate element length to true
    public static boolean caanCons(String[] arr,String Target){
        if(Target.length()==0)
            return true;
        boolean[] set=new boolean[Target.length()+1];
        set[0]=true;
        for(int i=0;i<set.length;i++){
            if(set[i]==false)
                continue;
                for(int j=0;j<arr.length;j++){
                    String s=arr[j];
                    if(i+s.length()>= set.length)
                        continue;
                    String sub=Target.substring(i,i+s.length());
                    if(sub.equals(s)){
                        set[i+s.length()]=true;
                    }
                }
                if(set[Target.length()])
                    return true;

        }
        return set[Target.length()];
    }
    //Palindrome Partitioning
    //Given a string s, partition s such that every substring of the partition is a palindrome.
    // Return all possible palindrome partitioning of s.
    //A palindrome string is a string that reads the same backward as forward.
    public static boolean p(String s,int low,int high){
        while(low<high){
            if(s.charAt(low++)!=s.charAt(high--))
                return false;
        }
        return true;
    }
    public static void DP(List<List<String>> result,List<String> temp,String target,int index){
        if(index==target.length())
            result.add(new ArrayList<>(temp));
        for(int i=index;i<target.length();i++){
            if(p(target,index,i)){
                temp.add(target.substring(index,i+1));
                DP(result,temp,target,i+1);
                temp.remove(temp.size()-1);
            }
        }
    }
    public static List<List<String>> paliPart(String s){
        List<List<String>> result=new ArrayList<>();
        DP(result,new ArrayList<String>() ,s,0);
        return result;
    }
    //we need to implement the subset sum problem
    //where Given a set of non-negative integers, and a value sum,
    //determine if there is a subset of the given set with sum equal to given sum.
    //the first approach which we are going to use is the recursive
    public static boolean sumSub(int[] candidates,int len,int target){
        if(target==0)
            return true;
        if(len==0)
            return false;
        if(candidates[len-1]>target)
            sumSub(candidates,len-1,target);
        return sumSub(candidates,len-1,target)||sumSub(candidates,len-1,target-candidates[len-1]);
    }
    //Partition Equal Subset Sum
    public static boolean subSe(int[] nums,int target,int index,int temp){
        if(temp*2==target)
            return true;
        if(temp>target/2 || index>=nums.length)
            return false;
        return subSe(nums,target,index+1,temp)||subSe(nums,target,index+1,temp+nums[index]);
    }
    public static boolean che(int[] nums){
        int total=0;
        for(int i:nums){
            total+=i;
        }
        if(total%2!=0)
            return false;
        return subSe(nums,total,0,0);
    }
    //using Dyanmic Programming for this we are using datatype hashmap to store the
    // value and the boolean value with that combination
    public static boolean DsumSe(int[] array,int total,int index,int sum,HashMap<String,Boolean> hm){
        String s=index+" "+sum;
        if(hm.containsKey(s))
            return hm.get(s);
        if(sum*2==total)
            return true;
        if(sum>total/2 ||index>=array.length)
            return false;
        Boolean b=DsumSe(array,total,index+1,sum,hm)||DsumSe(array,total,index+1,sum+array[index],hm);
        hm.put(s,b);
        return b;
    }
    public static Boolean cD(int[] array){
        int total=0;
        for(int i: array){
            total+=i;
        }
        if(total%2!=0)
            return false;
        return DsumSe(array,total,0,0,new HashMap<String,Boolean>());
    }
    public static void main(String[] args) {
        System.out.println(cD(new int[]{1,2,3,5}));
//        System.out.println(sumSub(new int[]{3, 34, 4, 12, 5, 2},6,8));
//        System.out.println(che(new int[]{1,5,11,5}));
//        System.out.println(paliPart("aab"));
//        System.out.println(caanCons(new String[]{"ab","abc","cd","def","abcd"},"abcdef"));
//        System.out.println(Arrays.toString(howSum(7,new int[]{5,3,4})));
//        System.out.println(tcansum(new int[]{5,3,4},7));
//        System.out.println(combination(new int[]{10,1,2,7,6,1,5},8));
//        System.out.println(combi(new int[]{10,1,2,7,6,1,5},8));
//        System.out.println(fibInt(9));
//        System.out.println(fiBIntRec(9));
//        System.out.println(DGridTraveller(0,3));
//        int[][] a=new int[16][16];
//        System.out.println(grid(3,3,a));
//        System.out.println(canSum(8,new int[]{12}));
//        System.out.println(Dcansum(8,new int[]{12}));
//        System.out.println(combineSum(new int[] {2,3,7},7));
//        System.out.println(canCon(new String[]{"ab","abc","cd","def","abcd"},new String("abcdef")));
////        HashMap<String , Boolean> hm=new HashMap<>();
//        System.out.println(DcanCon(new String[]{"ab","abc","cd","def","abcd"},new String("abcdef"),hm));
//        HashMap<String,Integer> hm1=new HashMap<>();
//        System.out.println(Countcon(new String[]{"purp","p","ur","le","purpl"},new String("purple"),hm1));
//        System.out.println(hm1);
//        System.out.println(fibi(8));
//        System.out.println(gridTraveller(3,3));
        //System.out.println(incsum(7,new int[] {2,4}));
    }
}
