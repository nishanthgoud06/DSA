import java.util.Arrays;
import java.util.Random;

public class hard_cci {
    //Add Without using the Plus Symbol
    public static int Add(int a,int b){
        if(b==0)
            return a;
        int sum=a^b;
        int carry=a&b;
        carry=carry<<1;
        return Add(sum,carry);
    }
    //Shuffle
    public static int rand(int low,int high){
        return low+(int)(Math.random()*(high+low+1));
    }
    public static int[] shuffle(int[] deck){
        for(int i=0;i<deck.length;i++){
            int j=rand(0,i);
            int temp=deck[i];
            deck[i]=deck[j];
            deck[j]=temp;
        }
        return deck;
    }
    //subset of a size with equal propaibility of elements getting picked
    public static int[] RandomSet(int[] arr,int m){
        int[] result = Arrays.copyOf(arr, m);
//        System.out.println(Arrays.toString(result));
        Random rand = new Random();

        for (int i = m; i < arr.length; i++) {
            int j = rand.nextInt(i + 1);
            if (j < m) {
                result[j] = arr[i];
            }
        }

        return result;
    }
    //another approch of solving this problem where we will be using the boolean visited array to kepp track
    public static int[] RandomSet2(int[] arr,int limit){
        int[] result=new int[limit];
        Random random=new Random();
        boolean[] visited=new boolean[arr.length];
        int current=0;
        int index;
        while(current!=limit){
            index=random.nextInt(arr.length+1);
            while(visited[index]){
                index=random.nextInt(arr.length+1);
            }
            visited[index]=true;
            result[current]=arr[index];
            current++;
        }
        return result;
    }
    //the question is randomely asked it can't be fin in cracking coding interview
    public static int maxAttend(int[][] arr){
        int current=0;
        int maxCount=0;
        int maxTime=0;
        Arrays.sort(arr,(a,b)-> a[0]-b[0]);
        for(int[] a:arr){
            if(a[0]>maxTime){
                current=1;
                maxTime=a[1];
            }else{
                current++;
                maxTime=Math.min(maxTime,a[1]);
                maxCount=Math.max(current,maxCount);
            }
        }
        return maxCount;
    }
    //Missing number
    static class BitInteger {
        int[] bits;

        public BitInteger(int value) {
            bits = new int[BitInteger.INTEGER_SIZE];
            for (int i = 0; i < BitInteger.INTEGER_SIZE; i++) {
                bits[i] = (value >> i) & 1;
            }
        }

        public int getBit(int bit) {
            return bits[bit];
        }

        public static final int INTEGER_SIZE = 32;
    }
    public static int findMissingNumber(BitInteger[] arr) {
        int numBits = BitInteger.INTEGER_SIZE - 1; // assuming 32-bit integers
        int missing = 0;

        // count number of 1's in each bit position for array and expected sequence
        for (int i = 0; i < numBits; i++) {
            int bitCount = 0;
            for (BitInteger num : arr) {
                if (num.getBit(i) == 1) {
                    bitCount++;
                }
            }
            if (bitCount % 2 == 1) { // bit is set in missing number
                missing += Math.pow(2, i);
            }
        }

        return missing;
    }
    //simple missing number
    //normally we would add the actual sum and Subtract with the given array sum
    public static int MissingNumber(int[] arr){
        if(arr.length==0)
            return 0;
        int sum=0;
        int act=0;
        for(int i=0;i<arr.length;i++){
            sum+=arr[i];
            act+=i;
        }
        return act-sum;
    }
    //now we are going to do the same if all the values we stored in as Binary Format
    //need to create a binarInteger array class
    static class IntegerBit{
        static int size=31;
        int[] bytes;
        public IntegerBit(int value){
            bytes=new int[size];
            for(int i=0;i<size;i++){
                bytes[i]=(value>>i)&1;
            }
        }
        public IntegerBit(){
            bytes=new int[size];
            for(int i=0;i<size;i++){
                bytes[i]=0;
            }
        }
        public int get(int i){
            return bytes[i];
        }
    }
    public static IntegerBit[] generateArray(int size,int missingNumber){
        IntegerBit[] result=new IntegerBit[size];
        for(int i=0;i<size;i++){
            if(missingNumber==i){
                result[i]=new IntegerBit();
            }else{
                result[i]=new IntegerBit(i);
            }
        }
        return result;
    }
    public static int FindMIssingNumber(IntegerBit[] arr){
        int missing=0;
        for(int i=0;i<IntegerBit.size-1;i++){
            int oneCount=0;
            for(IntegerBit current:arr){
                if(current.get(i)==1){
                    oneCount++;
                }
            }
            if(oneCount%2==1){
                missing+=Math.pow(2,i);
            }
        }
        return missing;
    }

    // TODO: 4/25/23 the solution is incomplete need to add the condition in checking missing method
    //  the method need to have different condition based on the size of test case if even the number of 0 is equal to o+1 if odd 1==0


    public static void main(String[] args) {
        //test case for missing number
        IntegerBit[] test=generateArray(7,5);
        int[] arr={0,1,2,3,4,6,7};
        IntegerBit[] test2=new IntegerBit[arr.length];
        for(int i=0;i<arr.length;i++){
            test2[i]=new IntegerBit(arr[i]);
        }
        System.out.println(FindMIssingNumber(test2));
        System.out.println("the missing number is "+FindMIssingNumber(test));
        // test case
//        int n = 7;
//        int[] arr = {0, 1, 2, 3, 5, 6, 7}; // missing number is 5
//        BitInteger[] bitArr = new BitInteger[arr.length];
//        for (int i = 0; i < arr.length; i++) {
//            bitArr[i] = new BitInteger(arr[i]);
//        }
//        int missing = findMissingNumber(bitArr);
//        System.out.println("Missing number: " + missing);
        //test case for nMaxinum atten expected during the event
//        System.out.println(maxAttend(new int[][]{{1,4},{2,5},{7,9},{3,4}}));
        //test case for Random Set
//        System.out.println(Arrays.toString(RandomSet(new int[]{1,2,3,4,5,6,7,8,9,10},4)));
//        System.out.println(Arrays.toString(RandomSet2(new int[]{1,2,3,4,5,6,7,8,9,10},4)));
        //test case for shuffle
//        System.out.println(Arrays.toString(shuffle(new int[]{1,2,3})));
        //test case for ASdd without + symbol
//        System.out.println(Add(5,7));
    }
}
