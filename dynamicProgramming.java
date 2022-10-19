import java.util.HashMap;
import java.util.HashSet;

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
    public static void main(String[] args) {
//        System.out.println(fibInt(9));
//        System.out.println(fiBIntRec(9));
//        System.out.println(DGridTraveller(0,3));
//        int[][] a=new int[16][16];
//        System.out.println(grid(3,3,a));
        System.out.println(canSum(8,new int[]{12}));
        System.out.println(Dcansum(8,new int[]{12}));
    }
}
