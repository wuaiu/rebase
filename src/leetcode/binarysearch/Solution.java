package leetcode.binarysearch;

public class Solution {
    public int kthSmallest(int[][] matrix, int k) {
        int n = matrix.length;
        if (n == 0) {
            return -1;
        }
        int left = matrix[0][0];
        int right = matrix[n - 1][n - 1];
        while (left <= right) {
            int mid = left + (right - left) / 2;
            int cur = kthSmallestNumb(matrix, mid);
            if (cur >= k) {
                right = mid - 1;
            }else{
                left = mid + 1;
            }
        }
        return left;
    }
    public int kthSmallestNumb(int[][] matrix, int target) {
        int rows = matrix.length;
        int cols = matrix[0].length;
        int numb = 0;
        int col = cols - 1;
        int row = 0;
        while (row < rows && col >= 0) {
            int cur = matrix[row][col];
            if (cur <= target) {
                numb += col + 1;
                row = row + 1;
            }else{
                col = col - 1;
            }
        }
        return numb;
    }
}
