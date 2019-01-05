
/*
384. 打乱数组

https://leetcode-cn.com/problems/shuffle-an-array/
https://leetcode-cn.com/explore/interview/card/top-interview-questions-easy/24/design/58/


打乱一个没有重复元素的数组。

示例:

// 以数字集合 1, 2 和 3 初始化数组。
int[] nums = {1,2,3};
Solution solution = new Solution(nums);

// 打乱数组 [1,2,3] 并返回结果。任何 [1,2,3]的排列返回的概率应该相同。
solution.shuffle();

// 重设数组到它的初始状态[1,2,3]。
solution.reset();

// 随机返回数组[1,2,3]打乱后的结果。
solution.shuffle();
*/
import java.util.*;

public class P384 {
    public static void main(String[] args) {
        int[] nums = { 1, 2, 3, 4, 5 };
        Solution obj = new Solution(nums);
        int[] param_0 = obj.shuffle();
        System.out.println(Arrays.toString(param_0));
        int[] param_1 = obj.reset();
        System.out.println(Arrays.toString(param_1));
        int[] param_2 = obj.shuffle();
        System.out.println(Arrays.toString(param_2));
    }

    // 《算法》中对此题有详细的描述
    static class Solution {
        private int[] original;
        private int[] values;
        private Random random;

        public Solution(int[] nums) {
            original = nums;
            values = Arrays.copyOf(nums, nums.length);
            random = new Random();
        }

        /** Resets the array to its original configuration and return it. */
        public int[] reset() {
            values = Arrays.copyOf(original, original.length);
            return values;
        }

        /** Returns a random shuffling of the array. */
        public int[] shuffle() {
            int rightEnd = values.length - 1;
            int r;
            while (rightEnd > 0) {
                r = random.nextInt(rightEnd + 1);
                if (r != rightEnd)
                    swap(values, r, rightEnd);
                rightEnd--;
            }
            return values;
        }

        private void swap(int[] array, int i, int j) {
            int temp = array[i];
            array[i] = array[j];
            array[j] = temp;
        }
    }

    /**
     * Your Solution object will be instantiated and called as such: Solution obj =
     * new Solution(nums); int[] param_1 = obj.reset(); int[] param_2 =
     * obj.shuffle();
     */
}