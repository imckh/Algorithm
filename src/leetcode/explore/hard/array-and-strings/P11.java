/*
11. 盛最多水的容器

https://leetcode-cn.com/problems/container-with-most-water/
https://leetcode-cn.com/explore/interview/card/top-interview-questions-hard/55/array-and-strings/126/


给定 n 个非负整数 a1，a2，...，an，每个数代表坐标中的一个点 (i, ai) 。在坐标内画 n 条垂直线，垂直线 i 的两个端点分别为 (i, ai) 和 (i, 0)。找出其中的两条线，使得它们与 x 轴共同构成的容器可以容纳最多的水。

说明：你不能倾斜容器，且 n 的值至少为 2。

https://aliyun-lc-upload.oss-cn-hangzhou.aliyuncs.com/aliyun-lc-upload/uploads/2018/07/25/question_11.jpg

图中垂直线代表输入数组 [1,8,6,2,5,4,8,3,7]。在此情况下，容器能够容纳水（表示为蓝色部分）的最大值为 49。

示例:

输入: [1,8,6,2,5,4,8,3,7]
输出: 49

*/
public class P11 {
    public static void main(String[] args) {
        String s = "PAYPALISHIRING";

        int[] arr = {1,8,6,2,5,4,8,3,7, 1,8,6,2,5,4,8,3,7};
        System.out.println(new Solution().maxAreaViolence(arr));
        System.out.println(new Solution().maxAreaDoublePointer(arr));
    }
}
class Solution {
    public int maxArea(int[] height) {
        return maxAreaDoublePointer(height);
    }

    public int maxAreaViolence(int[] height) {
        int maxArea = 0;
        for (int i = 0; i < height.length; i++) {
            for (int j = i + 1; j < height.length; j++) {
                maxArea = Math.max(
                    maxArea, 
                    Math.min(height[i], height[j]) * (j - i)// 暴力找i和j之间的面积(注意要找最短的位置)
                    );
            }
        }
        return maxArea;
    }

    /**
     这种方法背后的思路在于，两线段之间形成的区域总是会受到其中较短那条长度的限制。
     此外，两线段距离越远，得到的面积就越大。

    我们在由线段长度构成的数组中使用两个指针，一个放在开始，一个置于末尾。 
    此外，我们会使用变量 maxarea 来持续存储到目前为止所获得的最大面积。 
    在每一步中，我们会找出指针所指向的两条线段形成的区域，
    更新 maxarea，并将指向较短线段的指针向较长线段那端移动一步
     */
    public int maxAreaDoublePointer(int[] height) {
        int maxArea = 0;
        int l = 0;
        int r = height.length - 1;

        while (l <= r) {
            maxArea = Math.max(
                maxArea, 
                Math.min(height[l], height[r]) * (r - l)
            );
            /*
            考虑最外围构成的面积, 为了使面积最大, 是要考虑更长的两条线段之间的区域.
            如果将较长的线段指针向内侧移动, 由于面积受限于短的线段, 面积不会有任何增加. 
            但是这里我们是想让上限变得更高
            所以如果移动短的线段的指针, 虽然宽度减小, 但是却可能有助于面积增加, 
            因为移动较短的线段指针会得到相对较长的线段, 可以克服由宽度减小而引起的面积减小
            */
            if (height[l] > height[r]) {
                r--;
            } else {
                l++;
            }
        }

        return maxArea;
    }
}