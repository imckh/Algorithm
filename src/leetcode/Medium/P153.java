/*
153. 寻找旋转排序数组中的最小值
https://leetcode-cn.com/problems/find-minimum-in-rotated-sorted-array/

假设按照升序排序的数组在预先未知的某个点上进行了旋转。

( 例如，数组 [0,1,2,4,5,6,7] 可能变为 [4,5,6,7,0,1,2] )。

请找出其中最小的元素。

你可以假设数组中不存在重复元素。

示例 1:

输入: [3,4,5,1,2]
输出: 1
示例 2:

输入: [4,5,6,7,0,1,2]
输出: 0
*/
public class P153 {
    public static void main(String[] args) {
        //int[] nums = { 4, 5, 6, 7, 0, 1, 2 };
        int[] nums = {0,1,3,4,5,6,7};
        // int[] nums = {3,4,5,1,2};
        // int[] nums = {5,1};
        System.out.println(new Solution().findMin(nums));
    }

    static class Solution {
        public int findMin(int[] nums) {
            if (nums == null || nums.length == 0) {
                return -1;
            }

            // 如果最后一个数>=第一个数说明数组没有翻转，直接返回第一个数
            if (nums[nums.length - 1] >= nums[0])
                return nums[0];

            int l = 0, r = nums.length - 1, mid;

            while (l + 1 < r) {
                // mid = l + (r - l) / 2;
                mid = l + r >>> 1;
                // l r 是相互逼近的

                // 通过判断属于rotation的左边或者右边来决定 往左或者往右搜索
                if (nums[mid] >= nums[l]) {
                    // 至少说明mid的左侧是递增的
                    // 大于等于第一个数说明在左边，往右搜索
                    min = Math.min(min, nums[r]);
                    l = mid;
                } else {
                    // mid的右侧是递增的
                    r = mid;
                    min = Math.min(min, nums[mid]);
                }
                System.out.printf("l = %d, r = %d, mid = %d, min = %d\n", l, r, mid, min);
            }
            return Math.min(nums[l], nums[r]);
        }
    }
}