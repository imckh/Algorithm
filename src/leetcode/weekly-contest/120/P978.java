/*
978. 最长湍流子数组 
https://leetcode-cn.com/contest/weekly-contest-120/problems/longest-turbulent-subarray/
当 A 的子数组 A[i], A[i+1], ..., A[j] 满足下列条件时，我们称其为湍流子数组：

若 i <= k < j，当 k 为奇数时， A[k] > A[k+1]，且当 k 为偶数时，A[k] < A[k+1]；
或 若 i <= k < j，当 k 为偶数时，A[k] > A[k+1] ，且当 k 为奇数时， A[k] < A[k+1]。
也就是说，如果比较符号在子数组中的每个相邻元素对之间翻转，则该子数组是湍流子数组。

返回 A 的最大湍流子数组的长度。

 

示例 1：

输入：[9,4,2,10,7,8,8,1,9]
输出：5
解释：(A[1] > A[2] < A[3] > A[4] < A[5])
示例 2：

输入：[4,8,12,16]
输出：2
示例 3：

输入：[100]
输出：1
 

提示：

1 <= A.length <= 40000
0 <= A[i] <= 10^9
*/
import java.util.*;
public class P978 {
    public static void main(String[] args) {
        //int[] A = new int[] {1,1,0,1,0,1,0,0,1};
        int[] A = new int[] {100};
        System.out.println(new Solution().maxTurbulenceSize(A));
    }

    static class Solution {
        public int maxTurbulenceSize(int[] A) {
            if (A == null || A.length == 0) return 0;
            if (A.length == 1) {
                return 1;
            }
            int result = 0;
            int[] p = new int[A.length]; // 以p[i]为结尾的 最长湍流子数组
            p[0] = 0;
            p[1] = A[1] == A[0] ? 0 : 1;
            /*
            p[i] = p[i-1] 加上
            */
            for (int i = 2; i < A.length; i++) {
                if ((A[i-2] > A[i-1] && A[i-1] < A[i])
                    || (A[i-2] < A[i-1] && A[i-1] > A[i])
                ) { // i-2, i-1, i这三个都符合条件， 说明加上这一个i也符合
                    // 上一个状态加1
                    p[i] = p[i-1] + 1;
                } else if (A[i-1] != A[i]) {
                    // 只有该状态和上一个状态符合
                    p[i] = 1;
                } else {
                    // 不符合
                    p[i] = 0;
                }
                //System.out.println(Arrays.toString(p));
                result = Math.max(p[i], result);
            }
            // 因为是从0开始的， 所以结果加1
            return result + 1;
        }
    }
}