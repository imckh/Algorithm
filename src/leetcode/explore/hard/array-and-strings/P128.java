import java.util.*;

/*
128. 最长连续序列

https://leetcode-cn.com/problems/longest-consecutive-sequence/
https://leetcode-cn.com/explore/interview/card/top-interview-questions-hard/55/array-and-strings/129/


给定一个未排序的整数数组，找出最长连续序列的长度。

要求算法的时间复杂度为 O(n)。

示例:

输入: [100, 4, 200, 1, 3, 2]
输出: 4
解释: 最长连续序列是 [1, 2, 3, 4]。它的长度为 4。
*/
public class P128 {
    public static void main(String[] args) {
        int[] nums = new int[]{1,2,0,1,1,1,1,1,1};
        System.out.println(new Solution().longestConsecutive(nums));
        System.out.println(new Solution_1().longestConsecutive(nums));
    }

    static class Solution_1 {
        // n
        public int longestConsecutive(int[] nums) {
            if (nums == null || nums.length == 0) {
                return 0;
            }
            if (nums.length == 1) {
                return 1;
            }

            // 去重
            Set<Integer> set = new HashSet<>(nums.length);
            for (int i = 0; i < nums.length; i++) {
                set.add(nums[i]);
            }

            int max = 1;
            // 在每个数两边找，判断是否连续
            for (int e : nums) {
                int count = 1;
                int l = e - 1;
                int r = e + 1;
                while (set.contains(l)) {
                    count++;
                    set.remove(l); // 会把查看过的remove掉, 所以整体上每个元素查看两遍, 时间复杂度O(N)
                    l--;
                }
                while(set.contains(r)) {
                    count++;
                    set.remove(r);
                    r++;
                }
                max = Math.max(max, count);
            }
            return max;
        }
    }

    static class Solution {
        // nlogn
        public int longestConsecutive(int[] nums) {
            if (nums == null || nums.length == 0) {
                return 0;
            }
            if (nums.length == 1) {
                return 1;
            }

            Arrays.sort(nums);// 先排序
            //System.out.println(Arrays.toString(nums));
            int result = 1;
            int max = 1;
            for (int i = 1; i < nums.length; i++) {
                if (nums[i] == nums[i - 1]) {
                    // 跳过重复的
                    // 比如 [0, 1, 1, 1, 1, 1, 1, 1, 2]
                    continue;
                } else if (nums[i] - 1 == nums[i - 1]) {
                    //System.out.println(nums[i]);
                    // 找到前后差是1的最长序列
                    max++;
                } else {
                    result = Math.max(result, max);
                    max = 1;
                }
            }
            // 从 nums[i] - 1 == nums[i - 1]终止后, 需要更新一次result
            result = Math.max(result, max);
            return result;
        }
    }
}