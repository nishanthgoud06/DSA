package meta;

import java.util.*;

public class Kaitenzushi {
    public int getMaximumEatenDishCount(int N, int[] D, int K) {
        // Write your code here
        Set<Integer> set=new HashSet<>();
        int count=0;
        for(int i=0;i<D.length;i++){
            if(!set.contains(D[i])){
                count++;
            }
            set.add(D[i]);
            if(set.size()>K){
                set.remove(D[i-K]);
            }
        }
        return count;
    }

    public static void main(String[] args) {
        Kaitenzushi test=new Kaitenzushi();
        System.out.println(test.getMaximumEatenDishCount(6,new int[]{1, 2, 3, 3, 2, 1},1));

    }
}
