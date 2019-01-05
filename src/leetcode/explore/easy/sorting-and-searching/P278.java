/*
278. 第一个错误的版本

https://leetcode-cn.com/problems/first-bad-version/

https://leetcode-cn.com/explore/interview/card/top-interview-questions-easy/8/sorting-and-searching/53/

你是产品经理，目前正在带领一个团队开发新的产品。不幸的是，你的产品的最新版本没有通过质量检测。由于每个版本都是基于之前的版本开发的，所以错误的版本之后的所有版本都是错的。

假设你有 n 个版本 [1, 2, ..., n]，你想找出导致之后所有版本出错的第一个错误的版本。

你可以通过调用 bool isBadVersion(version) 接口来判断版本号 version 是否在单元测试中出错。实现一个函数来查找第一个错误的版本。你应该尽量减少对调用 API 的次数。

示例:

给定 n = 5，并且 version = 4 是第一个错误的版本。

调用 isBadVersion(3) -> false
调用 isBadVersion(5) -> true
调用 isBadVersion(4) -> true

所以，4 是第一个错误的版本。 
*/
public class P108 {
    public static void main(String[] args) {
        int[] q = new int[]{-10,-3,0,5,9};
        TreeNode t = new Solution().sortedArrayToBST(q);
        prettyPrintTree(t);
    }

    /* The isBadVersion API is defined in the parent class VersionControl.
      boolean isBadVersion(int version); */
    public class Solution extends VersionControl {
        public int firstBadVersion(int n) {
            // 左右不同, 二分
            int start = 1, end = n;
            while (start + 1 < end) {
                int mid = start + (end - start) / 2;
                if (isBadVersion(mid)) {
                    end = mid;
                } else {
                    start = mid;
                }
            }

            if (isBadVersion(start)) {
                return start;
            }
            return end;
        }
    }
}