import java.util.*;

public class cc_RandD {
    //Triple Step
    public static int tripleSteps(int n) {
        int[] dp = new int[n+1];
        dp[0] = 1;
        dp[1] = 1;
        dp[2] = 2;
        for (int i = 3; i <= n; i++) {
            dp[i] = dp[i-1] + dp[i-2] + dp[i-3];
        }
        return dp[n];
    }
    //robort in a grid
    public static int robogrid(int i,int j){
        int[][] grid=new int[i+1][j+1];
        for(int a=0;a<=i;a++){
            grid[a][0]=1;
        }
        for(int a=0;a<=j;a++){
            grid[0][a]=1;
        }
        for(int a=1;a<=i;a++){
            for(int b=1;b<=j;b++){
                grid[a][b]=1+Math.max(grid[a-1][b],grid[a][b-1]);
            }
        }
        return grid[i][j];
    }
    //magic index
    //test1:if the elements in the array are distinct
    public static int magicIndex1(int[] arr){
        return magicIndex1H(arr,0,arr.length-1);
    }
    public static int magicIndex1H(int[] arr,int low,int high){
        while(low<high){
            int mid=low+(high-low)/2;
            if(arr[mid]==mid){
                return mid;
            }else if(arr[mid]<mid){
                magicIndex1H(arr,low,mid-1);
            }else{
                magicIndex1H(arr,mid+1,high);
            }
        }
        return -1;
    }
    //test case-2:if the elements need not to be distinct
    public static int magicIndex(int[] arr) {
        return magicIndexHelper(arr, 0, arr.length - 1);
    }

    private static int magicIndexHelper(int[] arr, int low, int high) {
        if (low > high) {
            return -1;
        }
        int mid = low + (high - low) / 2;
        if (arr[mid] == mid) {
            return mid;
        }
        int leftIndex = Math.min(mid - 1, arr[mid]);
        int leftResult = magicIndexHelper(arr, low, leftIndex);
        if (leftResult != -1) {
            return leftResult;
        }
        int rightIndex = Math.max(mid + 1, arr[mid]);
        int rightResult = magicIndexHelper(arr, rightIndex, high);
        return rightResult;
    }
    //Power set
    //Back Tracking
    public static List<List<Integer>> PowerSet(int[] num){
        List<List<Integer>> result=new ArrayList<>();
        PowerSetH(num,result,new ArrayList<>(),0);
        return result;
    }
    public static void PowerSetH(int[] nums,List<List<Integer>> result,List<Integer> temp,int index){
        result.add(new ArrayList<>(temp));
        for(int i=index;i<nums.length;i++){
            temp.add(nums[i]);
            PowerSetH(nums,result,temp,i+1);
            temp.remove(temp.size()-1);
        }
    }
    //Power Set problem-2
    public static Set<Set<Integer>> PowerSet1(Set<Integer> nums){
        Set<Set<Integer>> result=new HashSet<>();
        result.add(new HashSet<>());
        if(nums.size()==0)
            return result;
        for(int i:nums){
            Set<Set<Integer>> tempSet=new HashSet<>();
            for(Set<Integer> set:result){
                Set<Integer> temp=new HashSet<>(set);
                temp.add(i);
                tempSet.add(temp);
            }
            result.addAll(tempSet);
        }
        return result;
    }
    //recusrsive mutiply
    public static int mutiply(int a,int b){
        if(a==0||b==0){
            return 0;
        }
        if(a==1)
            return b;
        return mutiply(a-1,b)+b;
    }
    //book approch
    public static int muliplytwo(int a,int b){
        int small=a<b?a:b;
        int big=b>a?b:a;
        return mutiplyhelper(small,big);
    }
    public static int mutiplyhelper(int small,int big){
        if(small==0)
            return 0;
        if(small==1)
            return big;
        int current=small>>1;
        int half=mutiplyhelper(current,big);
        if(small%2==0){
            return half+half;
        }else{
            return half+half+big;
        }
    }
    //towers of hannoi
    public static void towerOfHannoi(int n,char source,char Destination,char temp){
       if(n==0){
           return;
       }
       towerOfHannoi(n-1,source,temp,Destination);
       System.out.println("moving " +n+" the source "+ source+" to "+Destination);
       towerOfHannoi(n-1,temp,Destination,source);
    }
    //String Permutations
    public static List<String> permutation(String string){
        List<String> result=new ArrayList<>();
        if(string.length()==0)
            return result;
        permutationhelper(result,string,0);
        return result;
    }
    public static void permutationhelper(List<String> result,String string,int index){
        if(index==string.length()){
            result.add(string);
        }
        for(int i=index;i<string.length();i++){
            String str=swap(string,i,index);
            permutationhelper(result,str,index+1);
        }
    }
    public static String swap(String s,int a,int b){
        char[] c=s.toCharArray();
        char temp=c[a];
        c[a]=c[b];
        c[b]=temp;
        return new String(c);
    }
    //generate the paras according to the number
    //we are going to solve this problem in two approches
    //1.using back tracking
    public static List<String> genParas(int n){
        List<String> result=new ArrayList<>();
        if(n==0){
            return result;
        }
        genParasHelper(n,result,"",0,0);
        return result;
    }
    public static void genParasHelper(int n,List<String> result,String temp,int open,int close){
        if(temp.length()==n*2){
            result.add(temp);
            return;
        }
        if(open<n){
            genParasHelper(n,result,temp+"(",open+1,close);
        }
        if(close<open){
            genParasHelper(n,result,temp+")",open,close+1);
        }
    }
    //using stack
    public static List<String> genParas2(int n){
        List<String> result=new ArrayList<>();
        if(n==0){
            return result;
        }
        Stack<Character> stack=new Stack<>();
        genParas2Helper(stack,result,n,n);
        return result;
    }
    public static void genParas2Helper(Stack<Character> stack,List<String> result,int start,int end){
        if(start==0 && end==0){
            StringBuilder sb=new StringBuilder();
            for(Character c:stack){
                sb.append(c);
            }
            result.add(sb.toString());
            return;
        }
        if(start>0){
            stack.push('(');
            genParas2Helper(stack,result,start-1,end);
            stack.pop();
        }
        if(end>start){
            stack.push(')');
            genParas2Helper(stack,result,start,end-1);
            stack.pop();
        }
    }
    //PAINT FILL
    public static int[][] paintFill(int[][] grid,int row,int col,int color){
        int old=grid[row][col];
        paintFillHelper(grid,row,col,color,old);
        return grid;
    }
    public static void paintFillHelper(int[][] grid,int row,int col,int ncolor,int ocolor){
        if(row<0||row>=grid.length||col<0||col>=grid[0].length||grid[row][col]==ncolor)
            return;
        if(grid[row][col]==ocolor){
            grid[row][col]=ncolor;
            paintFillHelper(grid,row-1,col,ncolor,ocolor);
            paintFillHelper(grid,row+1,col,ncolor,ocolor);
            paintFillHelper(grid,row,col-1,ncolor,ocolor);
            paintFillHelper(grid,row,col+1,ncolor,ocolor);
        }
    }
    //Coin Change
    public static int CoinChange(int[] change,int target){
        int[] result=new int[target+1];
        for(int i=0;i<=target;i++){
            result[i]=Integer.MAX_VALUE - 1;
        }
        result[0]=0;
        for(int i=1;i<=target;i++){
            for(int j=0;j< change.length;j++){
                if(change[j]<=i){
                    result[i]=Math.min(result[i],1+result[i-change[j]]);
                }
            }
        }
        return result[target];
    }
    //coins
    //given change we need to find the number of ways to represent the target number
    public static int changeCoin(int[] coins,int target){
        int[] result=new int[target+1];
        result[0]=1;
        for(int i=0;i<coins.length;i++){
            for(int j=coins[i];j<=target;j++){
                result[j]+=result[j-coins[i]];
            }
        }
        return result[target];
    }
    //N -Queens Problem
    public static void buildQueen(int n){
        if(n==0)
            return;
        int[] castle=new int[n];
        queenHelper(castle,n,0);
    }
    public static void queenHelper(int[] castle,int n,int row){
        if(row==n){
            printCastle(castle);
            return;
        }
        for(int i=0;i<n;i++){
            castle[row]=i;
            if(isValidQueen(castle,row)){
                queenHelper(castle,n,row+1);
            }
        }
    }
    public static boolean isValidQueen(int[] castle,int row){
        for(int i=0;i<row;i++){
            if(castle[row]==castle[i]||Math.abs(castle[row]-castle[i])==Math.abs(row-i)){
                return false;
            }
        }
        return true;
    }
    public static void printCastle(int[] castle){
        for(int i=0;i<castle.length;i++){
            for(int j=0;j<castle.length;j++){
                if(castle[i]==j){
                    System.out.print("Q");
                }else{
                    System.out.print(".");
                }
            }
            System.out.println();
        }
        System.out.println();
    }
    //Stack the boxes where the rotation of the box is allowed
    public static int StackBoxes(Box[] boxes){
        Box[] newBoxes=new Box[boxes.length*3];
        generateBoxes(boxes,newBoxes);
        Arrays.sort(newBoxes);
        int[] t=new int[newBoxes.length];
        int[] result=new int[newBoxes.length];
        for(int i=0;i< newBoxes.length;i++){
            t[i]=newBoxes[i].height;
            result[i]=i;
        }
        for(int i=1;i< newBoxes.length;i++){
            for(int j=0;j< newBoxes.length;j++){
                if(newBoxes[i].length<newBoxes[j].length&&newBoxes[i].width<newBoxes[j].width){
                    if(t[j]+newBoxes[i].height>t[i]){
                        t[i]=t[j]+newBoxes[i].height;
                        result[i]=j;
                    }
                }
            }
        }
        int max=Integer.MIN_VALUE;
        for(int i=0;i< newBoxes.length;i++){
            if(max<t[i]){
                max=t[i];
            }
        }
        return max;
    }

    public static void generateBoxes(Box[] boxes,Box[] newBoxes){
        int index=0;
        for(int i=0;i< boxes.length;i++){
            newBoxes[index++]=Box.Dimensions(boxes[i].height,boxes[i].length,boxes[i].width);
            newBoxes[index++]=Box.Dimensions(boxes[i].length,boxes[i].height,boxes[i].width);
            newBoxes[index++]=Box.Dimensions(boxes[i].width,boxes[i].length,boxes[i].height);
        }
    }
    static class Box implements Comparable<Box>{
        int height;
        int width;
        int length;
        public Box(int height,int width,int length){
            this.height=height;
            this.width=width;
            this.length=length;
        }
        public Box(){

        }
        static Box Dimensions(int height,int side1,int side2){
            Box box=new Box();
            box.height=height;
            if(side1>=side2){
                box.length=side1;
                box.width=side2;
            }else{
                box.length=side2;
                box.width=side1;
            }
            return box;
        }
    @Override
        public int compareTo(Box B){
            if(this.length*this.width>=B.length*B.width){
                return -1;
            }else{
                return 1;
            }
        }

    }
    //tallest stack
    public static int tallestStack(int[][] boxes) {
        int n = boxes.length;
        Arrays.sort(boxes, (a, b) -> Integer.compare(a[1], b[1])); // Sort based on height

        int[] dp = new int[n];
        int max_height = 0;
        for (int i = 0; i < n; i++) {
            int max_height_i = 0;
            for (int j = 0; j < i; j++) {
                if (boxes[j][0] < boxes[i][0] && boxes[j][1] < boxes[i][1] && boxes[j][2] < boxes[i][2]) {
                    max_height_i = Math.max(max_height_i, dp[j]);
                }
            }
            dp[i] = max_height_i + boxes[i][1];
            max_height = Math.max(max_height, dp[i]);
        }
        return max_height;
    }
    public static void main(String[] args) {
        //test case for tallest Stack
        System.out.println(tallestStack(new int[][]{{2,1,3},{1,2,3},{3,2,1},{2,3,1}}));
        //test case for Stack Boxes
//        Box[] test={new Box(1,2,4),new Box(3,2,5)};
//        System.out.println(StackBoxes(test));
        //test case for n queen
//        buildQueen(4);
        //test case for Change Coin
//        System.out.println(changeCoin(new int[]{1,5},100));
        //test case for coin Change
//        System.out.println(CoinChange(new int[]{1,5,6,8},11));
        //test case for Paint Fill
//        System.out.println(Arrays.toString(new int[][]{{}}));
        //test case for Generating Paras
//        System.out.println(genParas(3));
//        System.out.println(genParas2(3));
        //test case for permutations
//        System.out.println(permutation("abc"));
        //test case for towers of hannoi
//        towerOfHannoi(3,'A','C','B');
        //test case for recursive mutiply
//        System.out.println(mutiply(3,4));
        //test case for Power Set
//        System.out.println(PowerSet(new int[]{1,2,3}));
//        //test case for power Set 2
//        Set<Integer> test=new HashSet<>();
//        test.add(1);
//        test.add(2);
//        test.add(3);
//        System.out.println(PowerSet1(test));
        //test case for magic Index
//        int[] test1={-40,-20,-1,1,2,3,5,7,9,12,13};
//        int[] test2={-10,-5,2,2,2,3,4,7,9,12,13};
//        System.out.println(magicIndex1(test1));
        //test case for Robot in the grid
//        System.out.println(robogrid(2,4));
        //test case for Triple Steps
//        System.out.println(tripleSteps(3));
    }
}
