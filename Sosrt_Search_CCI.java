import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class Sosrt_Search_CCI {
//    10.1 Sorted Merge
public static void merge_Sort(int[] a,int[] b,int indexA,int indexB){
    int size=indexB+indexA+1;
    while(indexB>=0){
        if(b[indexB]>a[indexA]){
            a[size]=b[indexB];
            indexB--;
        }else{
            a[size]=a[indexA];
            indexA--;
        }
        size--;
    }
}
//grouped anagram
    public static List<List<String>> groupedAnagram(String[] elements){
    List<List<String>> result=new ArrayList<>();
        HashMap<String,List<String>> hm=new HashMap<>();
    if(elements.length==0)
        return result;
    for(String s:elements){
        if(hm.containsKey(sortString(s))){
            hm.get(sortString(s)).add(s);
        }else{
            hm.put(sortString(s),new ArrayList<>());
            hm.get(sortString(s)).add(s);
        }
    }
    for(List<String> i:hm.values()){
        result.add(i);
    }
    return result;
    }
    public static String sortString(String s){
    char[] c=s.toCharArray();
        Arrays.sort(c);
        return String.valueOf(c);
    }
    //search in a rotated array
    public static int rotateArray(int[] arr,int target) {
        int low = 0;
        int high = arr.length - 1;

        while (low <= high) {
            int mid = (low + high) / 2;

            if (arr[mid] == target) {
                return mid;
            }

            if (arr[low] <= arr[mid]) { // left half is sorted
                if (target >= arr[low] && target < arr[mid]) {
                    high = mid - 1; // search left half
                } else {
                    low = mid + 1; // search right half
                }
            } else { // right half is sorted
                if (target > arr[mid] && target <= arr[high]) {
                    low = mid + 1; // search right half
                } else {
                    high = mid - 1; // search left half
                }
            }
        }

        return -1;
    }
    //rotate an array
    public static int[] rotateArr(int[] arr,int k){
    reverse(arr,0,arr.length-1);
    reverse(arr,0,k-1);
    reverse(arr,k,arr.length-1);
    return arr;
    }
    public static void reverse(int[] a,int i,int j){
    while(i<j){
        int temp=a[i];
        a[i]=a[j];
        a[j]=temp;
        i++;
        j--;
    }
    }
    //search in Listy
    public static class Listy{
    int[] arr;
    int index;
    int size;
    public Listy(int size){
        this.size=size;
        arr=new int[size];
        index=0;
    }
    public int elementAt(int index){
        if(arr.length-1<index)
            return -1;
        else
            return arr[index];
    }
    public void add(int val){
        if(index>=arr.length){
            int[] narr=new int[arr.length*2];
            for(int i=0;i<arr.length;i++){
                narr[i]=arr[i];
            }
            arr=narr;
        }
        arr[index]=val;
        index++;
    }
    }
    public static int sortedSearcg(Listy arr,int target){
    int high=1;
    while(arr.elementAt(high)<target&&arr.elementAt(high)!=-1){
        high=high*2;
    }
    return sortedSearchHelper(arr,target,high/2,high);
    }
    public static int sortedSearchHelper(Listy arr,int target,int low,int high){
    int mid=0;
    while(low<high){
            mid=low+(high-low)/2;
            int middle=arr.elementAt(mid);
            if(middle>target){
                high=mid-1;
            }else if(middle<target){
                low=mid+1;
            }else{
                return mid;
            }
        }
    return -1;
    }
    //Spare Search
    public static boolean SpareSearch(String[] elements,String target){
    if(elements.length==0||target.length()==0)
        return true;
    return SpareSearchHelper(elements,target,0,elements.length-1);
    }
    public static boolean SpareSearchHelper(String[] elements,String target,int low,int high){
        while(low<high){
            int mid=low+(high-low)/2;
            if(elements[mid].isEmpty()){
                int left=mid-1;
                int right=mid+1;
                while(true){
                    if(left<low||right>high){
                        return false;
                    } else if (left>=low && !elements[left].isEmpty()) {
                        mid=left;
                        break;
                    }else if(right<=high&&!elements[right].isEmpty()){
                        mid=right;
                        break;
                    }
                    left--;
                    right++;
                }
            }
           if(elements[mid].equals(target))
               return true;
           else if(elements[mid].compareTo(target)<0){
               low=mid+1;
           }else{
               high=mid-1;
           }
        }
        return false;
    }
    //searching in a sorted array
    public static boolean findMat(int[][] element, int target) {
        if (element.length == 0) {
            return false;
        }
        int row = element.length;
        int col = element[0].length;
        int low = 0;
        int high = row * col - 1;
        while (low <= high) {
            int mid = low + (high - low) / 2;
            int i = mid / col;
            int j = mid % col;
            if (element[i][j] == target) {
                return true;
            } else if (element[i][j] < target) {
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }
        return false;
    }
    public static boolean findMat1(int[][] array, int target) {
        int row = 0;
        int col = array[0].length - 1;
        while (row < array.length && col >= 0) {
            if (array[row][col] == target) {
                return true;
            } else if (array[row][col] > target) {
                col--;
            } else {
                row++;
            }
        }
        return false;
    }
    //Rank the Stream
    RankNode root=null;
    void track(int num){
        if(root==null){
            root=new RankNode(num);
        }else{
            root.add(num);
        }
    }
    int getRankOftheNumber(int R){
        return root.getRank(R);
    }
     class RankNode{
    int data;
    int leftCount;
    RankNode left,right;
    public RankNode(int d){
        data=d;
    }

    public void add(int val){
        if(val<= data){
            if(left!=null){
                left.add(val);
            }else{
                left=new RankNode(val);
            }
            leftCount++;
        }else{
            if(right!=null){
                right.add(val);
            }else{
                right=new RankNode(val);
            }
        }
    }
    public int getRank(int d){
        if(d==data)
            return leftCount;
        else if(d<data){
            if(left==null)
                return -1;
            else
                return left.getRank(d);
        }else{
            int right_rank=right==null?-1:right.getRank(d);
            if(right_rank==-1)
                return -1;
            else
                return leftCount+1+right_rank;
        }
    }
    }
    //Peaks and Valley
    public static void print(int[] arr){
        for(int i:arr){
            System.out.print(i+" ");
        }
    }
    //approch 1
    public static void PeakAndValley(int[] arr){
        Arrays.sort(arr);
        for(int i=1;i<arr.length;i=i+2){
            swap(arr,i,i-1);
        }
        print(arr);
    }
    public static void swap(int[] a,int i,int j){
        int temp=a[i];
        a[i]=a[j];
        a[j]=temp;
    }
    //approch 2
    public static void PeakAndValley2(int[] arr){
        for(int i=1;i<arr.length;i+=2){
            int max=pandvHelper(arr,i-1,i,i+1);
            if(i!=max){
                swap(arr,max,i);
            }
        }
        print(arr);
    }
    public static int pandvHelper(int[] arr,int a,int b,int c){
        a=(a>=0 && a<arr.length)?a:Integer.MIN_VALUE;
        b=(b>=0 && b<arr.length)?b:Integer.MIN_VALUE;
        c=(c>=0 && c<arr.length)?c:Integer.MIN_VALUE;
        int max=Math.max(a,Math.max(b,c));
        if(max==a){
            return a;
        }else if(max==b)
            return b;
        else{
            return c;
        }
    }
    public static void main(String[] args) {
        //test case for Peaks and Valley
        PeakAndValley(new int[]{9,1,0,4,8,7});
        PeakAndValley2(new int[]{9,1,0,4,8,7});
    //test caes for Rank of Stream
//        Sosrt_Search_CCI test=new Sosrt_Search_CCI();
//        test.track(5);
//        test.track(1);
//        test.track(4);
//        test.track(4);
//        test.track(5);
//        test.track(9);
//        test.track(7);
//        test.track(13);
//        test.track(3);
//        System.out.println(test.getRankOftheNumber(4));
    //test case for Sorted Matrix Search
//        int[][] test={{15,20,40,85},{20,35,80,95},{30,55,95,105},{40,80,100,120}};
//        System.out.println(findMat(test,55));
//        System.out.println(findMat1(test,55));
    //test case for Spare Search
//        String[] test={"at","","","","ball","","","car","","","dad","",""};
//        System.out.println(SpareSearch(test,"balls"));
    //test case for Sorted Search,No Size
//        Listy test=new Listy(9);
//        test.add(1);
//        test.add(2);
//        test.add(3);
//        test.add(4);
//        test.add(5);
//        test.add(6);
//        test.add(7);
//        test.add(8);
//        test.add(9);
//        System.out.println(sortedSearcg(test,7));
    //test case for rotate array
//        System.out.println(Arrays.toString(rotateArr(new int[]{1,2,3,4,5,6,7},3)));
    //test case for rotated Array
//        System.out.println(rotateArray(new int[]{15,16,19,20,25,1,3,4,5,7,10,14},5));
    //test case for grouped anagarm
//        String[] s={"eat", "tea", "tan", "ate", "nat", "bat"};
//        System.out.println(groupedAnagram(s));
    //test case for merge sorted array
//        int[] a=new int[20];
//        int[] b=new int[10];
//        a[0]=1;
//        b[0]=2;
//        for(int i=1;i<10;i++){
//            a[i]=1+i;
//            b[i]=2+i;
//        }
//        merge_Sort(a,b,9,9);
//        for(int i:a){
//            System.out.println(i);
//        }
    }
}
