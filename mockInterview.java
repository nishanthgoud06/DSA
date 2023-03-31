public class mockInterview {

    //check the plagarsim of two sentences
    public static boolean plag(String s1, String s2) {
        s1 = s1.toLowerCase();
        s2 = s2.toLowerCase();
        int distance = getPlag(s1, s2);
        int tH = (int) Math.ceil(Math.max(s1.length(), s2.length()) * 0.3);
        return distance <= tH;
    }

    public static int getPlag(String s1, String s2) {
        int m = s1.length();
        int n = s2.length();
        int[][] arr = new int[m + 1][n + 1]; // initialize array with m+1 rows and n+1 columns
        for (int i = 0; i <= m; i++) { // iterate over rows
            arr[i][0] = i;
        }
        for (int i = 0; i <= n; i++) { // iterate over columns
            arr[0][i] = i;
        }
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                if (s1.charAt(i - 1) == s2.charAt(j - 1)) {
                    arr[i][j] = arr[i - 1][j - 1];
                } else {
                    arr[i][j] = Math.min(arr[i - 1][j] + 1, Math.min(arr[i][j - 1] + 1, arr[i - 1][j - 1] + 1));
                }
            }
        }
        return arr[m][n];
    }


    public static void main(String[] args) {
        //test case for checking the plag of two sentences
        String s1="plag";
        String s2="plaging";
        System.out.println(plag(s1,s2));
    }
}
