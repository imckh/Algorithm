/*
5053. 地图分析

你现在手里有一份大小为 N x N 的『地图』（网格） grid，上面的每个『区域』（单元格）都用 0 和 1 标记好了。其中 0 代表海洋，1 代表陆地，你知道距离陆地区域最远的海洋区域是是哪一个吗？请返回该海洋区域到离它最近的陆地区域的距离。

我们这里说的距离是『曼哈顿距离』（ Manhattan Distance）：(x0, y0) 和 (x1, y1) 这两个区域之间的距离是 |x0 - x1| + |y0 - y1| 。

如果我们的地图上只有陆地或者海洋，请返回 -1。

 

示例 1：

https://assets.leetcode-cn.com/aliyun-lc-upload/uploads/2019/08/17/1336_ex1.jpeg

输入：[[1,0,1],[0,0,0],[1,0,1]]
输出：2
解释： 
海洋区域 (1, 1) 和所有陆地区域之间的距离都达到最大，最大距离为 2。


示例 2：

https://assets.leetcode-cn.com/aliyun-lc-upload/uploads/2019/08/17/1336_ex2.jpeg

输入：[[1,0,0],[0,0,0],[0,0,0]]
输出：4
解释： 
海洋区域 (2, 2) 和所有陆地区域之间的距离都达到最大，最大距离为 4。
 

提示：

1 <= grid.length == grid[0].length <= 100
grid[i][j] 不是 0 就是 1
*/
import java.util.*;

public class P5053 {
    public static void main(String[] args) {
        int[][] grid = {{1,0,1},{0,0,0},{1,0,1}};
        //int[][] grid = {{1,0,0},{0,0,0},{0,0,0}};
        System.out.println(new Solution().maxDistance(grid));
    }

    static class Solution {
        int[][] distanceSum = null;
        public int maxDistance(int[][] grid) {
            distanceSum = new int[grid.length][grid[0].length];
            int maxI = 0;
            int maxJ = 0;
            int max = 0;

            for (int i = 0; i < grid.length; i++) {
                for (int j = 0; j < grid[0].length; j++) {
                    if (grid[i][j] == 0) {
                        // 找到其与所有陆地间的距离和
                        distanceSum[i][j] += distance(grid, i, j);
                        if (max < distanceSum[i][j]) {
                            max = distanceSum[i][j];
                            maxI = i;
                            maxJ = j;
                        }
                    }
                }
            }
            print(distanceSum);
            return distanceMax(grid, maxI, maxJ);
        }

        public void print(int[][] a) {
            for (int i = 0; i < a.length; i++) {
                for (int j = 0; j < a[0].length; j++) {
                    System.out.print(a[i][j] + " ");
                }
                System.out.println();
            }
        }

        public int distance(int[][] grid, int y, int x) {
            int sum = 0;
            for (int i = 0; i < grid.length; i++) {
                for (int j = 0; j < grid[0].length; j++) {
                    if (grid[i][j] == 1) {
                        // 找到其与所有陆地间的距离和
                        sum += Math.abs(i - y) + Math.abs(j - x);
                    }
                }
            }
            return sum;
        }

        public int distanceMax(int[][] grid, int y, int x) {
            int max = 0;
            int dis = 0;
            for (int i = 0; i < grid.length; i++) {
                for (int j = 0; j < grid[0].length; j++) {
                    if (grid[i][j] == 1) {
                        // 找到其与所有陆地间的距离和
                        dis = Math.abs(i - y) + Math.abs(j - x);
                        if (max < dis) {
                            max = dis;
                        }
                    }
                }
            }
            return max;
        }
    }
}