/*
5071. 找出所有行中最小公共元素

给你一个矩阵 mat，其中每一行的元素都已经按 递增 顺序排好了。请你帮忙找出在所有这些行中 最小的公共元素。

如果矩阵中没有这样的公共元素，就请返回 -1。

 

示例：

输入：mat = [[1,2,3,4,5],[2,4,5,8,10],[3,5,7,9,11],[1,3,5,7,9]]
输出：5
 

提示：

1 <= mat.length, mat[i].length <= 500
1 <= mat[i][j] <= 10^4
mat[i] 已按递增顺序排列。
*/
class Solution {
    public int smallestCommonElement(int[][] mat) {
        int min = Integer.MAX_VALUE;
        boolean hasPub = false;
        for (int i = 0; i < mat[0].length; i++) { // 遍历第一行
            int n = mat[0][i];
            //System.out.println(n + "  ==============");
            int findIndex = -1;
            for (int j = 1; j < mat.length; j++) {
                //System.out.println(Arrays.binarySearch(mat[j], n));
                findIndex = Arrays.binarySearch(mat[j], n);
                //findIndex = binarySearch(mat[j], n);
                if (findIndex < 0) break;
            }
            if (findIndex >= 0) {
                min = Math.min(min, n);
                hasPub = true;
            }
        }
        if (!hasPub) return -1;
        return min;
    }
}