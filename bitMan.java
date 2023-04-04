public class bitMan {
    //insertion
    public static int insertion(int N, int M, int i, int j) {
        // Calculate the mask to clear bits i through j in N
        int mask = ~(((1 << (j-i+1)) - 1) << i);

        // Clear the bits i through j in N using the mask
        N &= mask;

        // Shift M by i positions to align it with N
        M <<= i;

        // Insert M into N using the bitwise OR operator
        N |= M;

        return N;
    }
    //Binary to String
    public static void printBinaryRepresentation(double num) {
        if (num >= 1 || num <= 0) {
            System.out.println("ERROR: Number must be between 0 and 1");
            return;
        }

        StringBuilder binary = new StringBuilder();
        binary.append("0.");

        while (num > 0) {
            if (binary.length() >= 32) {
                System.out.println("ERROR: Number cannot be represented with at most 32 characters");
                return;
            }

            double doubleValue = num * 2;

            if (doubleValue >= 1) {
                binary.append("1");
                num = doubleValue - 1;
            } else {
                binary.append("0");
                num = doubleValue;
            }
        }

        System.out.println(binary.toString());
    }


    public static void main(String[] args) {
        double num = 0.72;
        printBinaryRepresentation(num);
//        int N = 0b10000000000;  // decimal 1024
//        int M = 0b10011;        // decimal 19
//        int i = 2;
//        int j = 6;
//
//        int result = insertion(N, M, i, j);
//        System.out.println("N: " + Integer.toBinaryString(N) + " (" + N + ")");
//        System.out.println("M: " + Integer.toBinaryString(M) + " (" + M + ")");
//        System.out.println("i: " + i);
//        System.out.println("j: " + j);
//        System.out.println("Result: " + Integer.toBinaryString(result) + " (" + result + ")");
    }
}
