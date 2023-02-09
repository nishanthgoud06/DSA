import java.util.Arrays;

public class graphT {
    //floodfill
    public static int[][] floodFill(int[][] image, int sr, int sc, int color){
        if(image.length==0)
            return new int[0][0];
        helper(sr,sc,color,image);
        return image;
    }
    public static void helper(int i,int j,int color,int[][] image){
        if(i>=0&&i< image.length&&j>=0&&j<image[0].length&&image[i][j]!=0&&image[i][j]!=color){
            image[i][j]=color;
            helper(i-1,j,color,image);
            helper(i+1,j,color,image);
            helper(i,j-1,color,image);
            helper(i,j+1,color,image);
        }
    }

    public static void main(String[] args) {
        for(int[] i:floodFill(new int[][]{{1,1,1},{1,1,0},{1,0,1}},1,1,2)){
            System.out.println(Arrays.toString(i));
        }
    }
}
