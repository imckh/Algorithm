/*
268. 缺失数字

https://leetcode-cn.com/problems/missing-number/
https://leetcode-cn.com/explore/interview/card/top-interview-questions-easy/26/others/69/

给定一个包含 0, 1, 2, ..., n 中 n 个数的序列，找出 0 .. n 中没有出现在序列中的那个数。

示例 1:

输入: [3,0,1]
输出: 2
示例 2:

输入: [9,6,4,2,3,5,7,0,1]
输出: 8
说明:
你的算法应具有线性时间复杂度。你能否仅使用额外常数空间来实现?
*/
public class P268 {
    public static void main(String[] args) {
        int[] arr = new int[]{9,6,4,2,3,5,7,0,1};
        arr = new int[]{1,2,3};
        System.out.println(new Solution().missingNumber(arr));
    }

    static class Solution {
        public int missingNumber(int[] nums) {
            // 0 + 1 + 2 + ... n = (0 + n) * length / 2
            // 减去实际的和就知道缺哪个了
            int shouldSum = nums.length * (nums.length + 1) / 2;
            int sum = 0;
            for (int i = 0; i < nums.length; i++) {
                sum += nums[i];
            }
            return shouldSum - sum;
        }
    }
}