/*
162. 寻找峰值

https://leetcode-cn.com/problems/find-peak-element/
https://leetcode-cn.com/explore/interview/card/top-interview-questions-medium/50/sorting-and-searching/99/

峰值元素是指其值大于左右相邻值的元素。

给定一个输入数组 nums，其中 nums[i] ≠ nums[i+1]，找到峰值元素并返回其索引。

数组可能包含多个峰值，在这种情况下，返回任何一个峰值所在位置即可。

你可以假设 nums[-1] = nums[n] = -∞。

示例 1:

输入: nums = [1,2,3,1]
输出: 2
解释: 3 是峰值元素，你的函数应该返回其索引 2。
示例 2:

输入: nums = [1,2,1,3,5,6,4]
输出: 1 或 5 
解释: 你的函数可以返回索引 1，其峰值元素为 2；
     或者返回索引 5， 其峰值元素为 6。
*/
public class P162 {
    public static void main(String[] args) {
        //int[] nums = { 1, 2, 1, 3, 5, 6, 4 };
        int[] nums = { 1, 1,1,1,3};
        System.out.println(new Solution().findPeakElement(nums));
        System.out.println(new Solution().findPeakElement2(nums));
        System.out.println(new Solution2().findPeak(nums));
    }

    /**
    提示了用log的时间复杂度，所以考虑用二分法Binary Search。

    规律一：如果nums[i] > nums[i+1]，则在i之前一定存在峰值元素
    规律二：如果nums[i] < nums[i+1]，则在i+1之后一定存在峰值元素

    正确性证明：
        1. A[0] < A[1] && A[n-2] > A[n-1] 所以一定存在一个peek元素。
        2. 二分时，选择大的一边, 则留下的部分仍然满足1 的条件，即最两边的元素都小于相邻的元素。所以仍然必然存在peek。
        3. 二分至区间足够小，长度为1, 则中间元素就是peek。
     */
    static class Solution {
        // nums[i] ≠ nums[i+1]
        public int findPeakElement(int[] nums) {
            int start = 0, end = nums.length - 1;

            while (start + 1 < end) {
                int mid = (start + end) / 2;
                if (nums[mid] < nums[mid - 1]) {
                    end = mid;
                } else if (nums[mid] < nums[mid + 1]) {
                    start = mid;
                } else {
                    end = mid;
                }
            }

            if (nums[start] < nums[end]) {
                return end;
            } else {
                return start;
            }
        }

        public int findPeakElement2(int[] nums) {
            int l = 0, r = nums.length-1;
            while(l <= r){
                int mid = (r - l) / 2 + l;
                if((mid <= l || nums[mid] > nums[mid-1]) &&
                        (mid >= r || nums[mid] > nums[mid+1]))
                    return mid;
                if(mid <= l || nums[mid] > nums[mid-1])
                    l = mid + 1;
                else
                    r = mid - 1;
            }
            return -1;
        }
    }
}