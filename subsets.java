import java.io.IOException;
public class subsets {
    //in this program we are going to present all possible subsets of a given array set we are even going
    // to include the empty set

    public static void subset(char[] set){
        int n=set.length;
        for(int i=0;i<(1<<n);i++){
            System.out.print("{");
            for(int j=0;j<n;j++) {

                if ((i & (1 << j)) > 0)
                    System.out.print(set[j]);
            }
                System.out.println("}");

        }
    }

    public static void main(String[] args) {
        char[] a={'a','b','c'};
        subset(a);
    }
}
