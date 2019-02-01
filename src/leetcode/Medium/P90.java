import java.util.ArrayList;
import java.util.List;

/*
90. 子集 II

https://leetcode-cn.com/problems/subsets-ii/

给定一个可能包含重复元素的整数数组 nums，返回该数组所有可能的子集（幂集）。

说明：解集不能包含重复的子集。

示例:

输入: [1,2,2]
输出:
[
  [2],
  [1],
  [1,2,2],
  [2,2],
  [1,2],
  []
]
*/
import java.util.*;

public class P90 {
    public static void main(String[] args) {
        // int[] nums = {1,2,3};
        int[] nums = { 2, 2, 2, 3, 2 };

        for (List<Integer> list : new Solution().subsetsWithDup(nums)) {
            System.out.println(list);
        }
        System.out.println("==================");
        for (List<Integer> list : new Solution_no_recursive().subsetsWithDup(nums)) {
            System.out.println(list);
        }
    }

    static class Solution {
        public List<List<Integer>> subsetsWithDup(int[] nums) {
            List<List<Integer>> results = new ArrayList<List<Integer>>();
            if (nums == null)
                return results;

            if (nums.length == 0) {
                results.add(new ArrayList<Integer>());
                return results;
            }
            Arrays.sort(nums);
            dfs(nums, 0, new ArrayList<Integer>(), results);
            return results;
        }

        public void dfs(int[] nums, int startIndex, List<Integer> subset, List<List<Integer>> results) {
            results.add(new ArrayList<>(subset));
            for (int i = startIndex; i < nums.length; i++) {
                if (i != startIndex && nums[i] == nums[i - 1]) {
                    // 重复数字
                    // 已经排好序了 直接前后比较即可
                    continue;
                }

                subset.add(nums[i]);
                dfs(nums, i + 1, subset, results);
                subset.remove(subset.size() - 1);
            }
        }
    }

    static class Solution_no_recursive {
        public List<List<Integer>> subsetsWithDup(int[] nums) {
            List<List<Integer>> subsets = new ArrayList<List<Integer>>();
            ArrayList<Integer> indexes = new ArrayList<Integer>(); // 记录subsets中每个集合结尾元素的下标

            Arrays.sort(nums);
            subsets.add(new ArrayList<Integer>());
            indexes.add(-1);

            for (int i = 0; i < nums.length; ++i) {
                System.out.println(i);
                int size = subsets.size();
                for (int s = 0; s < size; ++s) {
                    if (i > 0 && nums[i] == nums[i - 1] && indexes.get(s) != i - 1) {
                        continue; // 去重，如果有重复数字出现，只有前上一个数字选了才能选当前数字
                    }
                    subsets.add(new ArrayList<Integer>(subsets.get(s)));
                    subsets.get(subsets.size() - 1).add(nums[i]);
                    indexes.add(i);
                    //System.out.println(indexes);
                }
            }

            return subsets;
        }
    }
}