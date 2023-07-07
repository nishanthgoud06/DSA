package meta;

import java.util.Arrays;

public class Cafeteria {
    public long getMaxAdditionalDinersCount(long N, long K, int M, long[] S) {
        // Write your code here
        long[] nums = new long[(int) N];
        Arrays.fill(nums, -1);

        for (int i = 0; i < M; i++) {
            nums[(int) (S[i] - 1)] = 0;
        }

        int count = 0;
        for (int i = 0; i < (int) N; i++) {
            boolean isValid = true;
            int left = Math.max(i - (int) K, 0);
            int right = Math.min(i + (int) K, (int) (N - 1));

            for (int j = left; j <= right; j++) {
                if (nums[j] == 0) {
                    isValid = false;
                    break;
                }
            }

            if (isValid) {
                count++;
                nums[i] = 0;
            }
        }

        return count;
    }

    public static void main(String[] args) {
        Cafeteria test=new Cafeteria();
        System.out.println(test.getMaxAdditionalDinersCount(15,1,2,new long[]{2,6}));
    }
}
