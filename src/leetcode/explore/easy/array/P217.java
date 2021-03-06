import java.util.*;

/*
217. 存在重复元素

https://leetcode-cn.com/problems/contains-duplicate/
https://leetcode-cn.com/explore/interview/card/top-interview-questions-easy/1/array/24/

给定一个整数数组，判断是否存在重复元素。

如果任何值在数组中出现至少两次，函数返回 true。如果数组中每个元素都不相同，则返回 false。

示例 1:

输入: [1,2,3,1]
输出: true
示例 2:

输入: [1,2,3,4]
输出: false
示例 3:

输入: [1,1,1,3,3,4,3,2,4,2]
输出: true
*/
public class P217 {
    public static void main(String[] args) {
        int[] arr = {1, 2, 2, 3, 3};
        System.out.println(new Solution().containsDuplicate(arr));
    }

    static class Solution {
        // 还可以用排序的方法, 在判断相邻两个数, 但是时间复杂度最少 O(NlogN)

        // 时间复杂度o(n)
        public boolean containsDuplicate(int[] nums) {
            if (nums == null || nums.length <= 1) return false;
            Set<Integer> set = new HashSet<>();
            for (int i = 0; i < nums.length; i++) {
                if (set.contains(nums[i])) {
                    return true;
                } else {
                    set.add(nums[i]);
                }
            }

            return false;
        }
    }
}