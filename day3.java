public class day3 {
    //peak of the array itreative
    public static void peak_i(int[] a){
        int[] result=new int[a.length];
        int j=0;
        if(a.length==0||a.length==1)
            for(int i:a){
                System.out.println(i);
            }
        for(int i=1;i<a.length-2;i++){
            if(a[i-1]<a[i]&&a[i]>a[i+1]){
                result[j]=a[i];
                j++;
            }
        }
        for(int i=0;i<result.length;i++){
            System.out.println(result[i]);
        }
    }
    //peak in a array using recursion
    public static int peak(int[] a,int size,int low,int high){
        int mid;

        mid=low+(high-low)/2;
        if((mid==0||a[mid-1]<a[mid])&&(mid==a.length-1||mid<size-1))
            return mid;
        else if(mid>0&&a[mid-1]>a[mid])
            return peak(a,size,low,mid-1);
        else
            return peak(a,size,mid+1,high);


    }
    //with recursion
    public static boolean binary_r(int[] arr,int start,int end,int target){

        while(start<=end){
            int mid=start+(end-start)/2;
            if(arr[mid]==target)
                return true;
            else if(arr[mid]>target)
                return binary_r(arr,start,mid-1,target);
            else
                return binary_r(arr,mid+1,end,target);
        }
        return false;
    }
    //without recursion
    public static boolean binary(int[] a,int target){
        int start=0;
        int end=a.length-1;
        int mid=(start+end)/2;
        while(start<end){
            if(a[mid]==target)
                return true;
            else if(a[mid]>target)
                end=mid-1;
            else
                start=mid+1;
            mid=(start+end)/2;
        }
        return false;
    }
    //linear serach using recursion where we are comparing from the last
    public static int linear(int[] a,int target,int size){
        if(size<1)
            return -1;
        if(a[size-1]==target)
            return size-1;


        return linear(a,target,size-1);
    }
    public static boolean sort(int[] arr,int size){
        //base case
        if(arr.length==0||arr.length==1)
            return true;
        //processing code
        if(arr[size-2]>arr[size-1])
            return false;
        return sort(arr,size-1);

        //recuresion relation
    }
    public static int add(int[] a,int size){
        if(size==0)
            return 0;
        return a[size-1]+add(a,size-1);
    }
    public static void main(String args[]) {
        int x=10;
        int y=25;
        int z=x+y;
        int[] a={68,34,23,76,89};
        //System.out.println(sort(a,5));
        // for(int i=0;i<a.length-1;i++){
        //     if(a[i]>a[i+1]){
        //         System.out.println("not greater"+a[i]);
        //     }
        // }
        int[] ab={1,2,3,4,6};
        //System.out.println(add(ab,3));
        //System.out.println(linear(ab,1,3));
        //System.out.println(binary_r(ab,0,4,4));
        int[] c={1,4,7,5,2};

        System.out.println(peak(c,5,0,4));
    }
}
