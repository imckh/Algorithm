/*
54. 螺旋矩阵

https://leetcode-cn.com/problems/spiral-matrix/
https://leetcode-cn.com/explore/interview/card/top-interview-questions-hard/55/array-and-strings/124/

给定一个包含 m x n 个元素的矩阵（m 行, n 列），请按照顺时针螺旋顺序，返回矩阵中的所有元素。

示例 1:

输入:
[
 [ 1, 2, 3 ],
 [ 4, 5, 6 ],
 [ 7, 8, 9 ]
]
输出: [1,2,3,6,9,8,7,4,5]
示例 2:

输入:
[
  [1, 2, 3, 4],
  [5, 6, 7, 8],
  [9,10,11,12]
]
输出: [1,2,3,4,8,12,11,10,9,5,6,7]
*/
import java.util.*;
public class P54 {
    public static void main(String[] args) {
        int[][] nums = new int[][] { 
            { 1, 2, 3, 4 }, 
            { 5, 6, 7, 8 }, 
            { 9, 10, 11, 12 } 
        };
        System.out.println(new Solution().spiralOrder(nums));
    }

    static class Solution {
        public List<Integer> spiralOrder(int[][] matrix) {
            List<Integer> results = new ArrayList<>();
            if (matrix == null || matrix.length == 0) {
                return results;
            }
            int[] dx = { 0, 1, 0, -1 };
            int[] dy = { 1, 0, -1, 0 };
            int x = 0;
            int y = 0;
            int dir = 0;
            for (int i = 0; i < matrix.length * matrix[0].length; i++) {
                results.add(matrix[x][y]);
                matrix[x][y] = 0;
                int next_x = x + dx[dir];
                int next_y = y + dy[dir];

                if (next_x < 0 || next_x >= matrix.length 
                    || next_y < 0 || next_y >= matrix[0].length
                        || matrix[next_x][next_y] == 0) {
                    dir = (dir + 1) % 4;
                    next_x = x + dx[dir];
                    next_y = y + dy[dir];
                }
                x = next_x;
                y = next_y;
            }
            return results;
        }
    }
}