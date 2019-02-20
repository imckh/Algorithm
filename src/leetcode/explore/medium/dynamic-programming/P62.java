/*
62. 不同路径

https://leetcode-cn.com/problems/unique-paths/
https://leetcode-cn.com/explore/interview/card/top-interview-questions-medium/51/dynamic-programming/105/

一个机器人位于一个 m x n 网格的左上角 （起始点在下图中标记为“Start” ）。

机器人每次只能向下或者向右移动一步。机器人试图达到网格的右下角（在下图中标记为“Finish”）。

问总共有多少条不同的路径？

https://assets.leetcode-cn.com/aliyun-lc-upload/uploads/2018/10/22/robot_maze.png

例如，上图是一个7 x 3 的网格。有多少可能的路径？

说明：m 和 n 的值均不超过 100。

示例 1:

输入: m = 3, n = 2
输出: 3
解释:
从左上角开始，总共有 3 条路径可以到达右下角。
1. 向右 -> 向右 -> 向下
2. 向右 -> 向下 -> 向右
3. 向下 -> 向右 -> 向右
示例 2:

输入: m = 7, n = 3
输出: 28
*/

import java.util.*;
public class P62 {
    public static void main(String[] args) {
        System.out.println(new Solution().uniquePaths(3, 7));
        System.out.println(new Solution().uniquePaths_optimized(3, 7));
        System.out.println(new Solution_math_Permutations().uniquePaths(3, 7));
    }
    // 动态规划
    static class Solution {
        public int uniquePaths(int m, int n) {
            if (m <= 0 || n <= 0) {
                return 0;
            }
            if (m == 1 || n == 1) {
                return 1;
            }
            /*
            初始状态
                1   1   1   1   1
                1
                1
                1
            
            当前这个状态只和左边和上边的格子有关系
                1   1   1   1   1
                1   2   3   4   5
                1
                1
            
            依次求解
                1   1   1   1   1
                1   2   3   4   5
                1   3   6
                1
            
            于是我们可以得到状态转移方程：

            ways[i][j] = ways[i-1][j] + ways[i][j-1];
            */
            int[][] ways = new int[m][n];
            for (int i = 0; i < m; i++) {
                for (int j = 0; j < n; j++) {
                    if (i == 0 || j == 0) {
                        ways[i][j] = 1;
                    } else {
                        ways[i][j] = ways[i-1][j] + ways[i][j-1];
                    }
                }
            }
            return ways[m-1][n-1];
        }

        public int uniquePaths_optimized(int m, int n) {
            if (m <= 0 || n <= 0) {
                return 0;
            }
            if (m == 1 || n == 1) {
                return 1;
            }
            /*
            上个方法是一行一行求解的,
            实际上只需要记录遍历到(i, j)这个位置的时候当前行有几种路径
            如果遍历到(i, m-1)的时候,替换掉这一行对应列的路径即可
            状态转移方程编程: res[j] = res[j] + res[j-1]
            */
            int[] res = new int[n];
            res[0] = 1;
            for (int i = 0; i < m; i++) {
                for (int j = 1; j < n; j++) {
                    res[j] += res[j - 1];
                    //System.out.println("i=" + i + " " + "j=" + j + ":" + Arrays.toString(res));
                }
            }
            return res[n-1];
        }
    }
    // 排列组合
    static class Solution_math_Permutations {
        public int uniquePaths(int m, int n) {
            // 我们要知道有多少种路径，那么只要知道m+n-2个step中，向下的n-1个step有多少种组合(向右一样)即可
            // 以模拟的[4, 7]的例子,每一条路径：
            // 向右的肯定有6步;
            // 向左的肯定有3步; 问题即为:c(9,3) = (9 * 8 * 7) / (1 * 2 * 3) = 84
            // 组合数公式：C(m,n) = m! / (n! * (m - n)!)
            return (int)combination(m + n - 2, n-1);
        }

        /**
         * 计算阶乘数，即n! = n * (n-1) * ... * 2 * 1 
         * 
         * @param n
         * @return
         */
        private static long factorial(int n) {
            return (n > 1) ? n * factorial(n - 1) : 1;
        }

        /**
         * 计算排列数，即A(n, m) = n!/(n-m)! 
         * 
         * @param n
         * @param m
         * @return
         */
        public static long arrangement(int n, int m) {
            return (n >= m) ? factorial(n) / factorial(n - m) : 0;
        }

        /**
         * 计算组合数，即C(n, m) = n!/((n-m)! * m!) 
         * 
         * @param n
         * @param m
         * @return
         */
        public static long combination(int n, int m) {
            return (n >= m) ? factorial(n) / factorial(n - m) / factorial(m) : 0;
        }
    }
}