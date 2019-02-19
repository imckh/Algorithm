/*
240. 搜索二维矩阵 II

https://leetcode-cn.com/problems/search-a-2d-matrix-ii/
https://leetcode-cn.com/explore/interview/card/top-interview-questions-medium/50/sorting-and-searching/103/

编写一个高效的算法来搜索 m x n 矩阵 matrix 中的一个目标值 target。该矩阵具有以下特性：

每行的元素从左到右升序排列。
每列的元素从上到下升序排列。
示例:

现有矩阵 matrix 如下：

[
  [1,   4,  7, 11, 15],
  [2,   5,  8, 12, 19],
  [3,   6,  9, 16, 22],
  [10, 13, 14, 17, 24],
  [18, 21, 23, 26, 30]
]
给定 target = 5，返回 true。

给定 target = 20，返回 false。
*/
public class P240 {
    public static void main(String[] args) {
        int[][] matrix = {
            {1,   4,  7, 11, 15},
            {2,   5,  8, 12, 19},
            {3,   6,  9, 16, 22},
            {10, 13, 14, 17, 24},
            {18, 21, 23, 26, 30}
        };
        // int[][] matrix = {
        //     {1}
        // };
        int target = 5;
        System.out.println(new Solution().searchMatrix(matrix, target));
    }
    static class Solution {
        public boolean searchMatrix(int[][] matrix, int target) {
            if (matrix == null || matrix.length == 0) {
                return false;
            }

            // 根据数组的规律, 从左下角开始搜索
            // target大于当前数, 向右
            // target小于当前数, 向左
            // log(m+n) m = matrix.length, n matrix[0].length
            int x = 0, y = matrix.length - 1;
            while (x < matrix[0].length && y >= 0) { // 边界以内
                if (matrix[y][x] == target) {
                    return true;
                } else if (matrix[y][x] > target) {
                    y--;
                } else if (matrix[y][x] < target) {
                    x++;
                }
            }

            return false;
        }
    }
}