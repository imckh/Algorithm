import java.util.*;

/*
300. 最长上升子序列

https://leetcode-cn.com/problems/longest-increasing-subsequence/
https://leetcode-cn.com/explore/interview/card/top-interview-questions-medium/51/dynamic-programming/107/

给定一个无序的整数数组，找到其中最长上升子序列的长度。

示例:

输入: [10,9,2,5,3,7,101,18]
输出: 4 
解释: 最长的上升子序列是 [2,3,7,101]，它的长度是 4。
说明:

可能会有多种最长上升子序列的组合，你只需要输出对应的长度即可。
你算法的时间复杂度应该为 O(n2) 。
进阶: 你能将算法的时间复杂度降低到 O(n log n) 吗?
*/
public class P300 {
    public static void main(String[] args) {
        int[] nums = {4,10,4,3,8,9};
        nums = new int[]{10,9,2,5,3,7,101,18};
        System.out.println(new Solution_nlogn().lengthOfLIS(nums));
        System.out.println(new Solution_n2().lengthOfLIS(nums));
        System.out.println(new Solution_TreeSet().lengthOfLIS(nums));
    }

    static class Solution_n2 {
        /*
        Dp[i] 表示以第i个数字为结尾的最长上升子序列的长度。
        对于每个数字，枚举前面所有小于自己的数字 j，Dp[i] = max{Dp[j]} + 1. 如果没有比自己小的，Dp[i] = 1;
        */
        public int lengthOfLIS(int[] nums) {
            int []f = new int[nums.length];
            int max = 0;
            for (int i = 0; i < nums.length; i++) {
                f[i] = 1;
                for (int j = 0; j < i; j++) {
                    if (nums[j] < nums[i]) {
                        f[i] = f[i] > f[j] + 1 ? f[i] : f[j] + 1;
                    }
                    // System.out.println(Arrays.toString(f));
                }
                if (f[i] > max) {
                    max = f[i];
                }
            }
            return max;
        }
    }

    static class Solution_nlogn {
        public int lengthOfLIS(int[] nums) {
            if (nums == null || nums.length == 0) {
                return 0;
            }
            if (nums.length == 1) {
                return 1;
            }

            int maxL = 0;
            int[] dp = new int[nums.length]; // 所有长度为i+1的递增子序列中, 最小的那个序列尾数.
            // 由定义知dp数组必然是一个递增数组, 可以用 maxL 来表示最长递增子序列的长度. 
            // 对数组进行迭代, 依次判断每个数num将其插入dp数组相应的位置:
            // 1. num > dp[maxL], 表示num比所有已知递增序列的尾数都大, 将num添加入dp数组尾部, 并将最长递增序列长度maxL加1
            // 2. dp[i-1] < num <= dp[i], 只更新相应的dp[i]
            for (int num : nums) {
                int lo = 0, hi = maxL;
                while (lo < hi) {
                    int mid = lo + (hi - lo) / 2;
                    if (dp[mid] < num) {
                        lo = mid + 1; 
                    } else {
                        hi = mid;
                    }
                }
                dp[lo] = num;
                if (lo == maxL) {
                    maxL++;
                }
                System.out.println(Arrays.toString(dp));
            }
            return maxL;
        }
    }

    static class Solution_TreeSet {
        public int lengthOfLIS(int[] nums) {
            TreeSet<Integer> set = new TreeSet<Integer>();
            
            for (int i : nums) {
                Integer ceil = set.ceiling(i);
                // TreeSet.ceiling(x)方法可以直接找出set中大于等于x的最小数字，如果不存在则返回null
                if (ceil != null) {
                    // 1. 如果这个数字存在，则删除这个数字，然后把x插入set中，相当于代替该数字。
                    set.remove(ceil);
                }
                // 2. 如果这个数字不存在，说明x大于set中任何数字，直接把x插入set中。
                set.add(i);
                //System.out.println(set.toString());
            }
            // 最后返回set的大小即可。
            return set.size();
        }
    }
}