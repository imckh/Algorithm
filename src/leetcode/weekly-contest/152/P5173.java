/*
5173. 质数排列

请你帮忙给从 1 到 n 的数设计排列方案，使得所有的「质数」都应该被放在「质数的索引」上；你需要返回可能的方案总数。

让我们一起来回顾一下「质数」：质数一定是大于 1 的，并且不能用两个小于它的正整数的乘积来表示。

由于答案可能会很大，所以请你返回答案 模 mod 10^9 + 7 之后的结果即可。

 

示例 1：

输入：n = 5
输出：12
解释：举个例子，[1,2,5,4,3] 是一个有效的排列，但 [5,2,3,4,1] 不是，因为在第二种情况里质数 5 被错误地放在索引为 1 的位置上。
示例 2：

输入：n = 100
输出：682289015
 

提示：

1 <= n <= 100
*/

import java.util.Arrays;

public class P5173 {
    public static void main(String[] args) {

        


        System.out.println(new Solution().numPrimeArrangements(100));

        System.out.println(127512000 % ((int)Math.pow(10, 9) + 7));
        System.out.println(127512000 % 1000000007);
    }

    static class Solution {
        int[] ps = {2,3,5,7,11,13,17,19,23,29,
            31,37,41,43,47,53,59,61,67,71,
            73,79,83,89,97};
        int P = (int)Math.pow(10, 9) + 7;
        public int numPrimeArrangements(int n) {
            int pnum = 0;
            for (int i = 0; i < ps.length; i++) {
                if (ps[i] <= n) {
                    pnum ++;
                }
            }
            
            int npNum = n - pnum;
            long result = 1; // int类型会溢出 必须用long

            while (pnum > 0) {
                result = (result * pnum) % P;
                pnum--;
            }
            while (npNum > 0) {
                result = (result * npNum) % P;
                npNum--;
            }
            return (int)result;
        }
    }
}