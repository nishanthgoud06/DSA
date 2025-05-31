package structure;

import java.util.Arrays;

// 1769. Minimum Number of Operations to Move All Balls to Each Box
public class ballBox {
    static String boxes="001011";

    public static void main(String[] args) {
        System.out.println(Arrays.toString(findBoxValue(boxes)));
    }

    private static int[] findBoxValue(String boxes)
    {
        int[] result = new int[boxes.length()];
        for(int i=0;i<boxes.length();i++){
            for(int j=0;j<boxes.length();j++){
                if(boxes.charAt(j)!='0'){
                    result[i]+=Math.abs(i-j);
                }
            }
        }
        return result;
    }
}
