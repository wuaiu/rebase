package leetcode.dfs;

public class Solution {
    public int numIslands(char[][] grid) {
        int rows = grid.length;
        if (rows == 0) {
            return 0;
        }
        int cols = grid[0].length;
        if (cols == 0) {
            return 0;
        }
        int num = 0;
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (grid[i][j] == '1') {
                    num++;
                    numIslandsDfs(grid, i, j);
                }
            }
        }
        return num;
    }

    public void numIslandsDfs(char[][] grid, int row, int col) {
        int rows = grid.length;
        int cols = grid[0].length;
        if (row < 0 || row >= rows || col < 0 || col >= cols) {
            return;
        }
        grid[row][col] = '0';
        numIslandsDfs(grid, row, col - 1);
        numIslandsDfs(grid, row, col + 1);
        numIslandsDfs(grid, row - 1, col);
        numIslandsDfs(grid, row + 1, col);
    }
}
