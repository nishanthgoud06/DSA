package meta;

public class DirectorofPhotography {
    public int getArtisticPhotographCount(int N, String C, int X, int Y) {
        // Write your code here
        int count = 0;

        for (int i = 0; i < N; i++) {
            if (C.charAt(i) == 'P') {
                for (int j = i + 1; j < N; j++) {
                    if (C.charAt(j) == 'A') {
                        for (int k = j + 1; k < N; k++) {
                            if (C.charAt(k) == 'B') {
                                int distancePA = Math.abs(j - i);
                                int distanceAB = Math.abs(k - j);

                                if (distancePA >= X && distancePA <= Y && distanceAB >= X && distanceAB <= Y) {
                                    count++;
                                }
                            }

                        }

                    }
                }
            }

        }

        return count;
    }

    public static void main(String[] args) {
        DirectorofPhotography test=new DirectorofPhotography();
        System.out.println(test.getArtisticPhotographCount(5,"APABA",1,2));
    }
}
