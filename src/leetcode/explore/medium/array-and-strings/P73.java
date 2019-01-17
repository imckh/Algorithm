import java.util.Arrays;

/*
73. 矩阵置零

https://leetcode-cn.com/problems/set-matrix-zeroes/
https://leetcode-cn.com/explore/interview/card/top-interview-questions-medium/29/array-and-strings/76/

给定一个 m x n 的矩阵，如果一个元素为 0，则将其所在行和列的所有元素都设为 0。请使用原地算法。

示例 1:

输入: 
[
  [1,1,1],
  [1,0,1],
  [1,1,1]
]
输出: 
[
  [1,0,1],
  [0,0,0],
  [1,0,1]
]
示例 2:

输入: 
[
  [0,1,2,0],
  [3,4,5,2],
  [1,3,1,5]
]
输出: 
[
  [0,0,0,0],
  [0,4,5,0],
  [0,3,1,0]
]
进阶:

一个直接的解决方案是使用  O(mn) 的额外空间，但这并不是一个好的解决方案。
一个简单的改进方案是使用 O(m + n) 的额外空间，但这仍然不是最好的解决方案。
你能想出一个常数空间的解决方案吗？
*/

public class P73 {
    public static void main(String[] args) {
        int[][] matrix = new int[][] { { 1, 1, 1 }, { 1, 0, 1 }, { 1, 1, 1 } };
        int[][] matrix2 = new int[][] { { 0, 1, 2, 0 }, { 3, 4, 5, 2 }, { 1, 3, 0, 5 } };
        print(matrix);
        new Solution().setZeroes(matrix);
        print(matrix);
        System.out.println();
        print(matrix2);
        new Solution().setZeroes(matrix2);
        print(matrix2);
    }

    static void print(int[][] matrix) {
        for (int i = 0; i < matrix.length; i++) {
            System.out.print("[");
            for (int j = 0; j < matrix[0].length; j++) {
                System.out.print(matrix[i][j] + ",");
            }
            System.out.println("]");
        }
    }

    static class Solution {
        public void setZeroes(int[][] matrix) {
            // 需要存储每一行, 或者每一列是否需要置零
            boolean[] x = new boolean[matrix.length];
            boolean[] y = new boolean[matrix[0].length];

            for (int i = 0; i < matrix.length; i++) {
                for (int j = 0; j < matrix[0].length; j++) {
                    if (matrix[i][j] == 0) {
                        // i行j列需要置零, 避免重复置零
                        if (!x[i])
                            x[i] = true;
                        if (!y[j])
                            y[j] = true;
                    }
                }
            }

            // System.out.println(Arrays.toString(x));
            // System.out.println(Arrays.toString(y));

            for (int i = 0; i < matrix.length; i++) {
                for (int j = 0; j < matrix[0].length; j++) {
                    if (x[i] || y[j]) {
                        matrix[i][j] = 0;
                    }
                }
            }
        }
    }

    static class Solution2 {
        // 使用O(1)的空间, 需要使用原始数组来存储数据
        public void setZeroes(int[][] matrix) {
            if (matrix == null || matrix.length == 0)
                return;

            int rows = matrix.length;
            int cols = matrix[0].length;

            // 若首行首列需要置零, 先保存下来
            boolean empty_row0 = false;
            boolean empty_col0 = false;
            for (int i = 0; i < cols; i++) {
                if (matrix[0][i] == 0) {
                    empty_row0 = true;
                    break;
                }
            }

            for (int i = 0; i < rows; i++) {
                if (matrix[i][0] == 0) {
                    empty_col0 = true;
                    break;
                }
            }

            // 使用首行首列来记录数组中需要置零的行和列
            for (int i = 1; i < rows; i++) {
                for (int j = 1; j < cols; j++) {
                    if (matrix[i][j] == 0) {
                        matrix[0][j] = 0;
                        matrix[i][0] = 0;
                    }
                }
            }

            // 根据保存的首行首列记录数组中需要置零的行和列
            for (int i = 1; i < rows; i++) {
                for (int j = 1; j < cols; j++) {
                    if (matrix[0][j] == 0 || matrix[i][0] == 0)
                        matrix[i][j] = 0;
                }
            }

            // 将首行首列置零
            if (empty_row0) {
                for (int i = 0; i < cols; i++) {
                    matrix[0][i] = 0;
                }
            }

            if (empty_col0) {
                for (int i = 0; i < rows; i++) {
                    matrix[i][0] = 0;
                }
            }
        }
    }

}