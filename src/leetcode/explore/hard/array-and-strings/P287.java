import java.util.*;

/*
287. 寻找重复数

https://leetcode-cn.com/problems/find-the-duplicate-number/

https://leetcode-cn.com/explore/interview/card/top-interview-questions-hard/55/array-and-strings/130/

给定一个包含 n + 1 个整数的数组 nums，其数字都在 1 到 n 之间（包括 1 和 n），可知至少存在一个重复的整数。假设只有一个重复的整数，找出这个重复的数。

示例 1:

输入: [1,3,4,2,2]
输出: 2
示例 2:

输入: [3,1,3,4,2]
输出: 3
说明：

不能更改原数组（假设数组是只读的）。
只能使用额外的 O(1) 的空间。
时间复杂度小于 O(n2) 。
数组中只有一个重复的数字，但它可能不止重复出现一次。

*/
public class P287 {
    public static void main(String[] args) {
        //int[] nums = new int[]{3,1,4,2,5,6,7,3};
        int[] nums = new int[]{1,3,4,2,2};

        int n = new Solution().findDuplicate(nums);
        System.out.println(n);
        int n2 = new Solution_2().findDuplicate(nums);
        System.out.println(n2);
        int n3 = new Solution_3().findDuplicate(nums);
        System.out.println(n3);
    }

    static class Solution {
        // 时间 nlogn
        // 空间 1
        // 更改了原数组
        public int findDuplicate(int[] nums) {
            // nums.length >= 2

            Arrays.sort(nums);

            for (int i = 1; i < nums.length; i++) {
                if (nums[i] == nums[i - 1]) {
                    return nums[i];
                }
            }

            return nums[0];
        }
    }

    static class Solution_2 {
        // nlogn
        // 使用额外的 O(n) 的空间
        public int findDuplicate(int[] nums) {
            // nums.length >= 2
            // 把 10个数放进9个位置, 必定有多出来的
            int[] n = new int[nums.length - 1];

            for (int i = 0; i < nums.length; i++) {
                n[nums[i]]++;
                if (n[nums[i]] > 1) {
                    return nums[i];
                }
            }

            return nums[0];
        }
    }
    static class Solution_3 {
        // 时间n, 空间1
        // 快慢指针
        // 利用坐标和数值之间相互转换，而由于重复数字的存在，那么一定会形成环，
        // 用快慢指针可以找到环并确定环的起始位置
        public int findDuplicate(int[] nums) {
            int slow = 0, fast = 0, t = 0;

            // 知道链表环的入口即为需要求解的重复数据，先用快慢指针找到环内某一节点
            while (true) {
                slow = nums[slow];
                fast = nums[nums[fast]];
                //System.out.printf("slow = %d, fast = %d\n", slow, fast);
                if (slow == fast) break;
            }
            //System.out.println("break");
            // 再从该节点和原点同时出发，经数学推导可知，它们首次相遇的节点即为链表环的入口
            while (true) {
                slow = nums[slow];
                t = nums[t];
                if (slow == t) break;
            }
            return slow;
        }
    }
}