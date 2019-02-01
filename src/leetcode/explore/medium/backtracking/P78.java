import java.util.*;

/*
78. 子集

https://leetcode-cn.com/problems/subsets/
https://leetcode-cn.com/explore/interview/card/top-interview-questions-medium/49/backtracking/94/

给定一组不含重复元素的整数数组 nums，返回该数组所有可能的子集（幂集）。

说明：解集不能包含重复的子集。

示例:

输入: nums = [1,2,3]
输出:
[
  [3],
  [1],
  [2],
  [1,2,3],
  [1,3],
  [2,3],
  [1,2],
  []
]
*/
public class P78 {
    public static void main(String[] args) {
        //int[] nums = {1,2,3};
        int[] nums = {2,3,1};

        for (List<Integer> list : new Solution_DFS().subsets(nums)) {
            System.out.println(list);
        }
        System.out.println("==================");
        for (List<Integer> list : new Solution_BFS().subsets_1(nums)) {
            System.out.println(list);
        }
    }

    static class Solution_DFS {
        public List<List<Integer>> subsets(int[] nums) {
            List<List<Integer>> result = new ArrayList<>();
            if (nums == null) {
                return result;
            }
            
            if (nums.length == 0) {
                result.add(new ArrayList<Integer>());
                return result;
            }

            dfs(nums, 0, new ArrayList<>(), result);
            return result;
        }
        // 以 subset 开头的, 加上 nums 中以 index 开始的的数的组合
        public void dfs(int[] nums, int index,  List<Integer> subset, List<List<Integer>> result) {
            if (index == nums.length) {
                result.add(new ArrayList<Integer>(subset));
                return;
            } 
            
            // 选了 nums[index]
            subset.add(nums[index]);
            dfs(nums, index + 1, subset, result);

            // 没选 nums[index]
            subset.remove(subset.size() - 1);
            dfs(nums, index + 1, subset, result);
        }
    }

    static class Solution_BFS {
        public List<List<Integer>> subsets_1(int[] S) {
            List<List<Integer>> result = new ArrayList<>();
            Arrays.sort(S);
            result.add(new ArrayList<Integer>());
            for (int item : S) {
                List<List<Integer>> newResult = new ArrayList<>(result);
                for (List<Integer> list : result) {
                    List<Integer> newList = new ArrayList<>(list);
                    newList.add(item);
                    newResult.add(newList);
                }
                result = newResult;
            }
            return result;
        }

        public List<List<Integer>> subsets(int[] nums) {
            List<List<Integer>> result = new LinkedList<>();
            if (nums == null) {
                return result;
            }
            
            if (nums.length == 0) {
                result.add(new ArrayList<Integer>());
                return result;
            }

            // BFS
            /*
            第一层只有[]
            第二层将 单独一个的加进队列
            以此类推


            [] 
            [1] [2] [3]
            [1, 2] [1, 3] [2, 3]
            [1, 2, 3]
            */
            Queue<List<Integer>> queue = new LinkedList<>();
            queue.offer(new LinkedList<Integer>());

            while (!queue.isEmpty()) {
                List<Integer> subset = queue.poll();
                result.add(subset);

                for (int i = 0; i < nums.length; i++) {
                    if (subset.size() == 0 
                        || subset.get(subset.size() - 1) < nums[i]) {
                        List<Integer> nextSubset = new LinkedList<>(subset);
                        nextSubset.add(nums[i]);
                        System.out.println("added nextSubset:" + nextSubset);
                        queue.offer(nextSubset);
                    }
                }
            }
            return result;
        }
    }
}