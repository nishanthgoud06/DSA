public class peakFinder {
    //one dimensional peak finding
    public static int peak1(int[] a){
        int low=0;
        int high=a.length-1;
        int mid=0;
        while(low<=high){
            mid=low+high>>1;
            if((mid==0||a[mid]>a[mid-1])&&(mid==a.length-1||a[mid]>a[mid+1])){
                break;
            }else if(a[mid-1]>a[mid]){
                high=mid-1;
            }else if(a[mid+1]>a[mid]){
                low=mid+1;
            }
        }
        return mid;
    }
    public static int indexFinding(int[][] a,int row,int mid,int max){
        int max_index=0;
        for(int i=0;i<row;i++){
            if(max_index<a[i][mid]){
                max=a[i][mid];
                max_index=i;
            }
        }
        return max_index;
    }
    public static  int maxNumber(int[][] a,int row,int mid,int max){
        int max_number=0;
        for(int i=0;i<row;i++){
            if(max_number<a[i][mid]){
                max_number=a[i][mid];
            }
        }
        return max_number;
    }
    public static int element(int[][] a,int row,int col,int mid){
        int max=0;
        int max_index=indexFinding(a,row,col,mid);
        max=maxNumber(a,row,col,mid);
        if(mid==0||mid==col-1)
            return max;
        if(max>=a[max_index][mid-1]&&max>=a[max_index][mid+1]){
            return max;
        }
        if (max < a[max_index][mid - 1])
            return element(a, row, col, (int) Math.ceil(mid-mid/2));

        // If max is less than its left
        // if (max < arr[max_index][mid+1])
        return element(a, row, col,(int)Math.ceil(mid+mid/2) );
    }
    public static void peak2(int[][] a){
        //the first approach is brute force
        int row=a.length;
        int col=a[0].length;
        int mid=(int)Math.ceil(col/2);
        System.out.println(element(a,row,col,mid));
    }
    public static void main(String[] args) {
        //int[] a={1,2,3};
        //System.out.println("the peak element in the given array "+a[peak1(a)]);
        int[][] ab={{1,2,3},{4,5,6},{7,8,9}};
        peak2(ab);
    }
}
