/*
48. 旋转图像

https://leetcode-cn.com/explore/interview/card/top-interview-questions-easy/1/array/31/
https://leetcode-cn.com/problems/rotate-image/

给定一个 n × n 的二维矩阵表示一个图像。

将图像顺时针旋转 90 度。

说明：

你必须在原地旋转图像，这意味着你需要直接修改输入的二维矩阵。请不要使用另一个矩阵来旋转图像。

示例 1:

给定 matrix = 
[
  [1,2,3],
  [4,5,6],
  [7,8,9]
],

原地旋转输入矩阵，使其变为:
[
  [7,4,1],
  [8,5,2],
  [9,6,3]
]
示例 2:

给定 matrix =
[
  [ 5, 1, 9,11],
  [ 2, 4, 8,10],
  [13, 3, 6, 7],
  [15,14,12,16]
], 

原地旋转输入矩阵，使其变为:
[
  [15,13, 2, 5],
  [14, 3, 4, 1],
  [12, 6, 8, 9],
  [16, 7,10,11]
]
*/

import java.util.*;
public class P48 {
    public static void main(String[] args) {
        // int[][] board = {
        //     {1,2,3},
        //     {4,5,6},
        //     {7,8,9}
        // };
        int[][] board = {
            { 5, 1, 9,11},
            { 2, 4, 8,10},
            {13, 3, 6, 7},
            {15,14,12,16}
        };
        print2DArray(board);
        new Solution().rotate(board);
        System.out.println("----------------------");
        print2DArray(board);
    }

    static class Solution {
        /*
        n 数组长度, row, col
        [0,0], [i,j], .  ....       n
        .                           [j,n-i]
        .                           .
        .                           .
        .                           .
        .                           .
        [n-j,i], ...                .
        n, .....          [n-i,n-j],[n,n]

        其中, 带i, j的是四个需要旋转的位置
        而且第1行需要旋转 0 -- n-1个元素
            第2行需要旋转 1 -- n-2个元素
            ...
            以此类推
            只需要到第n/2行即可, 因为下边的行都已经进行过旋转了 
        */
        public void rotate(int[][] matrix) {
            if (matrix == null || matrix.length <= 1) return;
            int len = matrix.length;
            int arrLen = len - 1;

            for (int i = 0; i < len / 2; i++) { // row 只需要到二维数组的一半
                for (int j = i; j < len - i - 1; j++) { // col 
                    int t = matrix[i][j];
                    
                    matrix[i][j] = matrix[arrLen - j][i];
                    matrix[arrLen - j][i] = matrix[arrLen - i][arrLen - j];
                    matrix[arrLen - i][arrLen - j] = matrix[j][arrLen - i];
                    matrix[j][arrLen - i] = t;
                    
                    //System.out.println("==================");
                    //System.out.println(i + " " + j + " " + t);
                    //print2DArray(matrix);
                }
            }
        }
    }

    public static void print2DArray(int[][] arr) {
        for (int i = 0; i < arr.length; i++) {
            System.out.println(Arrays.toString(arr[i]));
        }
    }
}