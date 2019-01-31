import java.util.*;

/*
46. 全排列

https://leetcode-cn.com/problems/permutations/
https://leetcode-cn.com/explore/interview/card/top-interview-questions-medium/49/backtracking/93/

给定一个没有重复数字的序列，返回其所有可能的全排列。

示例:

输入: [1,2,3]
输出:
[
  [1,2,3],
  [1,3,2],
  [2,1,3],
  [2,3,1],
  [3,1,2],
  [3,2,1]
]
 */

public class P46 {
    public static void main(String[] args) {
        int[] nums = {1, 2, 3};
        System.out.println(new Solution().permute(nums));
    }

    static class Solution {
        public List<List<Integer>> permute(int[] nums) {
            // 使用深度优先搜索算法
            // 使用 visited 数组记录某个数是否被放到 permutation 里了
            List<List<Integer>> results = new ArrayList<>();
            if (nums == null) {
                return results;
            }
            dfs(nums, new boolean[nums.length], new ArrayList<>(), results);
            return results;
        }

        /**
         * 深度优先遍历
         * 
         * @param nums 数组
         * @param visited 到现在为止的数组是否被放进了permutation中
         * @param permutation 当前序列
         * @param results 最终结果
         */
        private void dfs(int[] nums, boolean[] visited, List<Integer> permutation, List<List<Integer>> results) {
            if (nums.length == permutation.size()) {
                // 递归的终点: permutation的长度与原始数组相同
                results.add(new ArrayList<>(permutation));
                return;
            }
            
            for (int i = 0; i < nums.length; i++) {
                if (visited[i]) {
                    // 当前元素已经访问过了
                    continue;
                }

                // 没有访问过的, 依次加入permutation序列, 并进行下一次递归调用
                permutation.add(nums[i]);
                visited[i] = true;
                dfs(nums, visited, permutation, results);
                // 需要注意为了for循环的下一次做状态的恢复
                visited[i] = false;
                permutation.remove(permutation.size() - 1);
            }
        }
    }
}