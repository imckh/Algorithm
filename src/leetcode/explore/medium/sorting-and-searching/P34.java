/*
34. 在排序数组中查找元素的第一个和最后一个位置

https://leetcode-cn.com/problems/find-first-and-last-position-of-element-in-sorted-array/
https://leetcode-cn.com/explore/interview/card/top-interview-questions-medium/50/sorting-and-searching/100/

给定一个按照升序排列的整数数组 nums，和一个目标值 target。找出给定目标值在数组中的开始位置和结束位置。

你的算法时间复杂度必须是 O(log n) 级别。

如果数组中不存在目标值，返回 [-1, -1]。

示例 1:

输入: nums = [5,7,7,8,8,10], target = 8
输出: [3,4]
示例 2:

输入: nums = [5,7,7,8,8,10], target = 6
输出: [-1,-1]
*/

public class P34 {
    public static void main(String[] args) {
        int[] nums = {5,5};
        int target = 4;
        int[] r = new Solution().searchRange(nums, target);
        System.out.printf("[%d, %d]\n", r[0], r[1]);
    }

    static class Solution {
        // 二分查找后向左右两边扩展
        public int[] searchRange(int[] nums, int target) {
            int[] result = new int[]{-1, -1};
            if (nums == null || nums.length == 0) {
                return result;
            }

            int l = 0, r = nums.length - 1;
            int index = -1;
            while (l <= r) {
                int mid = l + (r - l) / 2;
                System.out.println("mid = " + mid);
                if (nums[mid] > target) {
                    r = mid - 1;
                } else if (nums[mid] < target) {
                    l = mid + 1;
                } else {
                    index = mid;
                    break;
                }
            }
            
            if (index == -1) {
                return result;
            }
            result[0] = index;
            result[1] = index;
            while (result[0] > 0 && nums[result[0] - 1] == nums[index]) {
                result[0]--;
            }
            while (result[1] < nums.length-1 && nums[result[1] + 1] == nums[index]) {
                result[1]++;
            }
            return result;
        }
    }
}