package leetcode.dynamic_programming;

import java.util.ArrayList;
import java.util.List;

public class Solution {
    public List<String> readBinaryWatch(int turnedOn) {
        List<String> ans = new ArrayList<>();
        int nums[] = new int[]{1, 2, 4, 8, 1, 2, 4, 8, 16, 32};
        readBinaryWatchHelp(ans, nums, 0, 0, 0, 0, turnedOn);
        return ans;
    }

    public void readBinaryWatchHelp(List<String> ans, int[] nums, int hour, int minute, int index, int count, int sum) {
        if (hour > 11 || minute > 59) {
            return;
        }
        if (count == sum) {
            StringBuilder sb = new StringBuilder();
            sb.append(hour);
            sb.append(":");
            if (minute < 10) {
                sb.append('0');
            }
            sb.append(minute);
            ans.add(sb.toString());
        }
        if (index >= nums.length) {
            return;
        }
        readBinaryWatchHelp(ans, nums, hour, minute, index + 1, count, sum);
        if (index <= 3) {
            hour += nums[index];
        } else {
            minute += nums[index];
        }
        readBinaryWatchHelp(ans, nums, hour, minute, index + 1, count + 1, sum);
        if (index <= 3) {
            hour -= nums[index];
        } else {
            minute -= nums[index];
        }
        return;
    }

    public int maximalSquare(char[][] matrix) {
        int rows = matrix.length;
        if (rows == 0) {
            return 0;
        }
        int cols = matrix[0].length;
        int dp[][] = new int[rows][cols];
        int ans = 0;
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (matrix[i][j] == '1') {
                    if (i == 0 || j == 0) {
                        dp[i][j] = 1;
                    } else {
                        dp[i][j] = Math.min(dp[i - 1][j], Math.min(dp[i][j - 1], dp[i - 1][j - 1])) + 1;
                    }
                    ans = Math.max(ans, dp[i][j]);
                } else {
                    dp[i][j] = 0;
                }
            }
        }
        return ans * ans;
    }

    public int rob2(int[] nums) {
        int n = nums.length;
        if (n == 0) {
            return 0;
        }
        if (n == 1) {
            return nums[0];
        }
        int buyOne[] = new int[n];
        int notBuyOne[] = new int[n];
        buyOne[0] = nums[0];
        buyOne[1] = nums[0];
        notBuyOne[0] = 0;
        notBuyOne[1] = nums[1];
        for (int i = 2; i < n - 1; ++i) {
            buyOne[i] = Math.max(buyOne[i - 2] + nums[i], buyOne[i - 1]);
        }
        for (int i = 2; i < n; ++i) {
            notBuyOne[i] = Math.max(notBuyOne[i - 2] + nums[i], notBuyOne[i - 1]);
        }
        return Math.max(buyOne[n - 2], notBuyOne[n - 1]);
    }

    public int rob(int[] nums) {
        int n = nums.length;
        if (n == 0) {
            return 0;
        }
        if (n == 1) {
            return nums[0];
        }
        int buy[] = new int[n];
        int notBuy[] = new int[n];
        buy[0] = nums[0];
        buy[1] = Math.max(nums[0], nums[1]);
        notBuy[1] = nums[0];
        for (int i = 2; i < n; ++i) {
            buy[i] = Math.max(buy[i - 2], notBuy[i - 1]) + nums[i];
            notBuy[i] = Math.max(buy[i - 1], notBuy[i - 1]);
        }
        return Math.max(buy[n - 1], notBuy[n - 1]);
    }
}
