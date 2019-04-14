import java.util.Arrays;

/*
5025. 最长等差数列  显示英文描述

题目难度 Medium
给定一个整数数组 A，返回 A 中最长等差子序列的长度。

回想一下，A 的子序列是列表 A[i_1], A[i_2], ..., A[i_k]
其中 0 <= i_1 < i_2 < ... < i_k <= A.length - 1。
并且如果 B[i+1] - B[i]( 0 <= i < B.length - 1) 的值都相同，那么序列 B 是等差的。



示例 1：

输入：[3,6,9,12]
输出：4
解释：
整个数组是公差为 3 的等差数列。
示例 2：

输入：[9,4,7,2,10]
输出：3
解释：
最长的等差子序列是 [4,7,10]。
示例 3：

输入：[20,1,15,3,10,5,8]
输出：4
解释：
最长的等差子序列是 [20,15,10,5]。


提示：

2 <= A.length <= 2000
0 <= A[i] <= 10000
 */
public class P5025 {
    public static void main(String[] args) {
        Solution solution = new Solution();
        int[] arr = new int[]{0,8,45,88,48,68,28,55,17,24};
        System.out.println(solution.longestArithSeqLength(arr));
        System.out.println(solution.calApLength(arr));
    }

    static class Solution {
        public int longestArithSeqLength(int[] A) {
            if (A == null || A.length == 0) return 0;
            if (A.length == 1) return 1;

            //Arrays.sort(A);
            int max = A[0], min = A[0];
            for (int i = 0; i < A.length; i++) {
                max = Math.max(max, A[i]);
                min = Math.min(min, A[i]);
            }

            System.out.println(Arrays.toString(A));
            //int maxTolerance = A[A.length - 1] - A[0]; // A数组中的最大公差
            int maxTolerance = max - min; // A数组中的最大公差
            if (maxTolerance == 0) {
                return A.length;
            }

            int[][] dp = new int[A.length][maxTolerance + 1]; // dp[i][t]表示从0到i范围公差为t的等宽数列长度
            // tolerance=A[i]-A[j],j<i
            // dp[i][tolerance]=dp[j][tolerance]=1
            for (int i = 0; i <dp.length ; i++) {
                for (int j = 0; j < maxTolerance + 1; j++) {
                    dp[i][j]=1;
                }
            }

            int result = 1, tolerance;

            for (int i = 1; i < A.length; i++) {
                for (int j = i - 1; j >= 0; j--) {
                    tolerance = A[i] - A[j];
                    dp[i][tolerance] = dp[j][tolerance] + 1;
                    result = Math.max(result, dp[i][tolerance]);
                }
            }
            //print(dp);
            return result;
        }

        public static int calApLength(int[] intAr){
            if (intAr==null){
                return 0;//返回空的情况
            }
            Arrays.sort(intAr);
            int maxlen=intAr[intAr.length-1]-intAr[0];
            int ans=1;
            if (maxlen==0){
                return intAr.length;
            }
            //dp[i][diff]的意思是intAr[0]到intAr[i],等差为diff的等差长度
            // 有diff=intAr[i]-intAr[j],j<i
            // dp[i][diff]=dp[j][diff]=1

            int[][] dp=new int[intAr.length][maxlen+1];
            for (int i = 0; i <dp.length ; i++) {
                for (int j = 0; j < maxlen+1; j++) {
                    dp[i][j]=1;
                }
            }
            for (int i = 1; i <intAr.length ; i++) {
                for (int j = i-1; j >=0; j--) {
                    int diff=intAr[i]-intAr[j];//算出i和j之间的等差
                    dp[i][diff]=dp[j][diff]+1;//当前
                    ans=Math.max(ans,dp[i][diff]);
                }
            }
            return ans;
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
    }
}
