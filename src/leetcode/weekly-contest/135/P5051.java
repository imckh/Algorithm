import java.util.*;

/**
5051. 有效的回旋镖  显示英文描述  

回旋镖定义为一组三个点，这些点各不相同且不在一条直线上。

给出平面上三个点组成的列表，判断这些点是否可以构成回旋镖。

 

示例 1：

输入：[[1,1],[2,3],[3,2]]
输出：true
示例 2：

输入：[[1,1],[2,2],[3,3]]
输出：false
 

提示：

points.length == 3
points[i].length == 2
0 <= points[i][j] <= 100

 */
public class P5051 {
    public static void main(String[] args) {
        int[][] points = new int[][]{{1,2},{2,4},{3,6}};

        System.out.println(new Solution().isBoomerang(points));
    }

    static class Solution {
        public boolean isBoomerang(int[][] points) {
            int[] A = points[0];
            int A1 = A[0], A2 = A[1];
            int[] B = points[1];
            int B1 = B[0], B2 = B[1];
            int[] C = points[2];
            int C1 = C[0], C2 = C[1];
            System.out.println(Arrays.toString(A));
            System.out.println(Arrays.toString(B));
            System.out.println(Arrays.toString(C));

            return ((A1*B2 - A2*B1) + (B1*C2 - B2*C1) + (C1*A2 - C2*A1)) != 0;
        }
    }
}