/*
5141. 最大的以 1 为边界的正方形

给你一个由若干 0 和 1 组成的二维网格 grid，请你找出边界全部由 1 组成的最大 正方形 子网格，并返回该子网格中的元素数量。如果不存在，则返回 0。

 

示例 1：

输入：grid = [[1,1,1],[1,0,1],[1,1,1]]
输出：9
示例 2：

输入：grid = [[1,1,0,0]]
输出：1
 

提示：

1 <= grid.length <= 100
1 <= grid[0].length <= 100
grid[i][j] 为 0 或 1
*/
import java.util.*;

public class P5141 {
    public static void main(String[] args) {
        int[][] arr = new int[][]{
            {1,1,1},{1,0,1},{1,1,1}
        };
        System.out.println(new Solution().largest1BorderedSquare(arr));
    }

    static class Solution {
        // 时间复杂度 n^3 肯定超时
        public int largest1BorderedSquare(int[][] grid) {
            int max = 0;
            for (int i = 0; i < grid.length; i++) {
                for (int j = 0; j < grid[0].length; j++) {
                    if (grid[i][j] == 1) {
                        // 对每个是1的正方左上角找最大值
                        // 找以i,j为左上角的面积最大值
                        System.out.println(i + "  " + j);
                        max = Math.max(maxArea(i, j, grid), max);

                    }
                }
            }

            return max;
        }

        // 从i, j开始遍历到边界
        // 找到最大的正方形
        public int maxArea(int i, int j, int[][] grid) {
            int area = 1;
            int maxLength = Math.min(grid.length - i, grid[0].length-j);
            System.out.println("maxLength = " + maxLength);
            for (int l = 1; l < maxLength; l++) {
                boolean up = false;
                for (int k = i; k < i + l; k++) { // 上
                    if (grid[k][j] == 1) {
                        up = true;
                    } else {
                        break;
                    }
                }
                boolean left = false;
                for (int k = j; k < j + l; k++) { // 左
                    if (grid[i][k] == 1) {
                        left = true;
                    } else {
                        break;
                    }
                }
                for (int k = 0; k < l; k++) { // 下
                    if (grid[k][j] == 1) {
                        x1 = true;
                    } else {
                        break;
                    }
                }
                for (int k = 0; k < l; k++) {
                    if (grid[k][j] == 1) {
                        x1 = true;
                    } else {
                        break;
                    }
                }

                if (grid[i+l][j] == 1
                    && grid[i][j+l] == 1
                    && grid[i+l][j+l] == 1) {
                    area = (l + 1) * (l + 1);
                    System.out.println(area);
                } else {
                    return area;
                }
            }

            return area;
        }
    }
}