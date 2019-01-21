/*
334. 递增的三元子序列

https://leetcode-cn.com/problems/increasing-triplet-subsequence/
https://leetcode-cn.com/explore/interview/card/top-interview-questions-medium/29/array-and-strings/80/


给定一个未排序的数组，判断这个数组中是否存在长度为 3 的递增子序列。

数学表达式如下:

如果存在这样的 i, j, k,  且满足 0 ≤ i < j < k ≤ n-1，
使得 arr[i] < arr[j] < arr[k] ，返回 true ; 否则返回 false 。
说明: 要求算法的时间复杂度为 O(n)，空间复杂度为 O(1) 。

示例 1:

输入: [1,2,3,4,5]
输出: true
示例 2:

输入: [5,4,3,2,1]
输出: false
*/
public class P334 {
    public static void main(String[] args) {
        int[] nums;
        nums = new int[]{1,1,-2,6};
        System.out.println(new Solution().increasingTriplet(nums));
        nums = new int[]{5,4,3,2,1};
        System.out.println(new Solution().increasingTriplet(nums));
        nums = new int[]{9,1,10,2,11};
        System.out.println(new Solution().increasingTriplet(nums));
    }

    static class Solution {
        public boolean increasingTriplet(int[] nums) {
            if (nums == null || nums.length < 3) {
                return false;
            }
            int min = Integer.MAX_VALUE;        // 遍历到当前位置最小的元素
            int secondMin = Integer.MAX_VALUE;  // 从min的位置开始一直到当前位置的第二小元素

            for (int num : nums) {
                if (num < min) min = num;
                else if (num > min && num < secondMin) secondMin = num;
                else if (num > secondMin) return true; 
                // 确定这两个元素后, 再在后续的元素中找有没有比second_min大的元素, 如果有, 就表示存在递增的三元子序列
            }
            return false;
        }
    }
}