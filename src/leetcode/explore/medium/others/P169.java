/*
169. 求众数

https://leetcode-cn.com/explore/interview/card/top-interview-questions-medium/54/others/121/
https://leetcode-cn.com/problems/majority-element/

给定一个大小为 n 的数组，找到其中的众数。众数是指在数组中出现次数大于 ⌊ n/2 ⌋ 的元素。

你可以假设数组是非空的，并且给定的数组总是存在众数。

示例 1:

输入: [3,2,3]
输出: 3
示例 2:

输入: [2,2,1,1,1,2,2]
输出: 2
*/
import java.util.*;
public class P169 {
    public static void main(String[] args) {
        System.out.println(new Solution().majorityElement(new int[]{3,2,3}));
        System.out.println(new Solution().majorityElement(new int[]{2,2,1,1,1,2,2}));
        System.out.println(new Solution_1().majorityElement(new int[]{3,2,3}));
        System.out.println(new Solution_1().majorityElement(new int[]{2,2,1,1,1,2,2}));
    }

    static class Solution {
        public int majorityElement(int[] nums) {
            if (nums.length == 1) {
                return nums[0];
            }
            // 使用map记录出现次数
            Map<Integer, Integer> map = new HashMap<>();

            for (int i = 0; i < nums.length; i++) {
                if (map.containsKey(nums[i])) {
                    int n = map.get(nums[i]);
                    map.put(nums[i], n + 1);
                } else {
                    map.put(nums[i], 1);
                }
                if (map.get(nums[i]) > nums.length / 2) {
                    return nums[i];
                }
            }
            return 0;
        }
    }

    static class Solution_1 {
        public int majorityElement(int[] nums) {
            int result = nums[0], count = 0;
            // 如果nums[x]是众数
            // 经过下面的count++或者--, nums[x]的count肯定是大于0的
            for (int i = 0; i < nums.length; i++) {
                if (count == 0) {
                    // 如果count减为0, 则说明上一个result肯定不是众数, 替换为下一个
                    result = nums[i];
                    count++;
                } else if (result == nums[i]) {
                    // 遇到result, count++
                    count++;
                } else {
                    // 没有遇到result, count--
                    count--;
                }
            }
            return result;
        }
    }
}