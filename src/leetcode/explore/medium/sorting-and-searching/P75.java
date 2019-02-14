import java.util.Arrays;

/*
75. 颜色分类

https://leetcode-cn.com/problems/sort-colors/
https://leetcode-cn.com/explore/interview/card/top-interview-questions-medium/50/sorting-and-searching/96/

给定一个包含红色、白色和蓝色，一共 n 个元素的数组，原地对它们进行排序，使得相同颜色的元素相邻，并按照红色、白色、蓝色顺序排列。

此题中，我们使用整数 0、 1 和 2 分别表示红色、白色和蓝色。

注意:
不能使用代码库中的排序函数来解决这道题。

示例:

输入: [2,0,2,1,1,0]
输出: [0,0,1,1,2,2]
进阶：

一个直观的解决方案是使用计数排序的两趟扫描算法。
首先，迭代计算出0、1 和 2 元素的个数，然后按照0、1、2的排序，重写当前数组。
你能想出一个仅使用常数空间的一趟扫描算法吗？
*/
public class P75 {
    public static void main(String[] args) {
        int[] colors = { 2, 0, 2, 1, 1, 0 };
        //new Solution().sortColors(colors);
        new Solution2().sortColors(colors);
        System.out.println(Arrays.toString(colors));
    }

    static class Solution {
        // 统计个数   这个并不是原地排序!!!
        public void sortColors(int[] nums) {
            if (nums == null || nums.length == 0) {
                return;
            }
            int zero = 0, one = 0, two = 0;

            for (int i = 0; i < nums.length; i++) {
                switch (nums[i]) {
                case 0:
                    zero++;
                    break;
                case 1:
                    one++;
                    break;
                case 2:
                    two++;
                    break;
                default:
                    break;
                }
            }
            int index = 0;
            for (int i = 0; i < zero; i++, index++) {
                nums[index] = 0;
            }
            for (int i = 0; i < one; i++, index++) {
                nums[index] = 1;
            }
            for (int i = 0; i < two; i++, index++) {
                nums[index] = 2;
            }
        }
    }

    static class Solution2 {
        // 0往数组头上移动
        // 2往数组尾部移动
        // 剩下的1自然就在中间了
        public void sortColors(int[] nums) {
            int red = 0;
            int blue = nums.length - 1;
            for (int i = 0; i <= blue; i++) {
                if (nums[i] == 0) {
                    int temp = nums[i];
                    nums[i] = nums[red];
                    nums[red] = temp;

                    red++;
                } else if (nums[i] == 2) {
                    int temp = nums[i];
                    nums[i] = nums[blue];
                    nums[blue] = temp;
                    // 此处的i--是为了防止换过来的还是2而被忽略
                    i--;
                    blue--;
                }
            }
        }
    }
}