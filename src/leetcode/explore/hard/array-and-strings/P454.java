/*
454. 四数相加 II

https://leetcode-cn.com/problems/4sum-ii/
https://leetcode-cn.com/explore/interview/card/top-interview-questions-hard/55/array-and-strings/125/

给定四个包含整数的数组列表 A , B , C , D ,计算有多少个元组 (i, j, k, l) ，使得 A[i] + B[j] + C[k] + D[l] = 0。

为了使问题简单化，所有的 A, B, C, D 具有相同的长度 N，且 0 ≤ N ≤ 500 。所有整数的范围在 -228 到 228 - 1 之间，最终结果不会超过 231 - 1 。

例如:

输入:
A = [ 1, 2]
B = [-2,-1]
C = [-1, 2]
D = [ 0, 2]

输出:
2

解释:
两个元组如下:
1. (0, 0, 0, 1) -> A[0] + B[0] + C[0] + D[1] = 1 + (-2) + (-1) + 2 = 0
2. (1, 1, 0, 0) -> A[1] + B[1] + C[0] + D[0] = 2 + (-1) + (-1) + 0 = 0
*/
import java.util.*;
public class P454 {
    public static void main(String[] args) {
        int[] A = new int[]{ 1, 2};
        int[] B = new int[]{-2,-1};
        int[] C = new int[]{-1, 2};
        int[] D = new int[]{ 0, 2};
        System.out.println(new Solution().fourSumCount(A, B, C, D));
    }

    static class Solution {
        public int fourSumCount(int[] A, int[] B, int[] C, int[] D) {
            // 先保存a+b的所有取值，然后遍历所有c+d的组合，分成两部分算起来更快
            int n = A.length;
            if (n == 0) return 0;

            Map<Integer, Integer> ab = new HashMap<>();
            for (int a : A) {
                for (int b : B) {
                    ab.put(a + b, ab.getOrDefault(a + b, 0) + 1);
                }
            }

            int res = 0;
            for (int c : C) {
                for (int d : D) {
                    int part2 = c + d;
                    int part1 = -part2;
                    res += ab.getOrDefault(part1, 0);
                }
            }

            return res;
        }
    }
}