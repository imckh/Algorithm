import java.util.*;

/*
41. 缺失的第一个正数

https://leetcode-cn.com/problems/first-missing-positive/
https://leetcode-cn.com/explore/interview/card/top-interview-questions-hard/55/array-and-strings/128/

给定一个未排序的整数数组，找出其中没有出现的最小的正整数。

示例 1:

输入: [1,2,0]
输出: 3
示例 2:

输入: [3,4,-1,1]
输出: 2
示例 3:

输入: [7,8,9,11,12]
输出: 1
说明:

你的算法的时间复杂度应为O(n)，并且只能使用常数级别的空间。
*/
public class P41 {
    public static void main(String[] args) {
        // int[] nums = new int[]{2,9,-1,1};
        int[] nums = new int[]{7,8,9,11,12,6,5,4,3,1};
        //int[] nums = new int[]{1,2,3,4,5,6,7,9};
        System.out.println(new Solution().firstMissingPositive(nums));
    }

    static class Solution {
        /*
        [7, 8, 9, 11, 12 ,6 ,5 ,4 ,3 ,1]

        [5, 8, 9, 11, 12, 6, 7, 4, 3, 1] 7在数组长度范围内, 与位置7上的5交换
        [12, 8, 9, 11, 5, 6, 7, 4, 3, 1] 完成的5在数组长度范围内, 继续用交换完成的5进行交换12, 12不符合交换原则
        [12, 4, 9, 11, 5, 6, 7, 8, 3, 1] 8在数组长度范围内, 与4交换
        [12, 11, 9, 4, 5, 6, 7, 8, 3, 1] 交换完的4在数组长度范围内, 继续与11交换, 11不符合交换原则
        [12, 11, 3, 4, 5, 6, 7, 8, 9, 1] 9在数组长度范围内, 与3交换, 3本来就在3, 4本来就在4, 一直到1
        [1, 11, 3, 4, 5, 6, 7, 8, 9, 12] 1与12交换, 完成预处理
        */
        public int firstMissingPositive(int[] nums) {
            int len = nums.length;
            // 遍历数组，调整数组中元素所处位置，保证数组中的大于0且小于等于数组长度的数i，都位于i-1的位置，即nums[i-1]=i
            for (int i = 0; i < len; i++) {
                /**
                 * while循环保证i位置上的数满足nums[i] == i+1或者nums[i] == 0
                 * nums[i] == 0，即i+1缺失
                 * 
                 * 我们需要交换的数必须要>0并且要<=len(nums)
                 * */
                while (nums[i] != i + 1 && nums[i] > 0 && nums[i] <= len) {
                    if (nums[nums[i] - 1] == nums[i]) {
                        nums[i] = 0;
                        break;
                    }
                    int temp = nums[nums[i] - 1];
                    nums[nums[i] - 1] = nums[i];
                    nums[i] = temp;
                    System.out.println(Arrays.toString(nums));
                }
            }

            //遍历数组，第一个满足nums[i-1] != i的数，即为缺失的第一个正数 
            int index = 0;
            for (; index < len && nums[index] == index + 1; index++);
            return index + 1;
        }
    }
}