/*
200. 岛屿的个数

https://leetcode-cn.com/problems/number-of-islands/
https://leetcode-cn.com/explore/learn/card/queue-stack/217/queue-and-bfs/872/
https://leetcode-cn.com/explore/interview/card/top-interview-questions-medium/32/trees-and-graphs/90/

给定一个由 '1'（陆地）和 '0'（水）组成的的二维网格，计算岛屿的数量。一个岛被水包围，并且它是通过水平方向或垂直方向上相邻的陆地连接而成的。你可以假设网格的四个边均被水包围。

示例 1:

输入:
11110
11010
11000
00000

输出: 1
示例 2:

输入:
11000
11000
00100
00011

输出: 3
*/
import java.util.*;
public class P200 {
    public static void main(String[] args) {
        char[][] grid = new char[][]{
            {'1','1','1','1','0'},
            {'1','1','0','1','0'},
            {'1','1','0','0','0'},
            {'0','0','0','1','1'}};
        System.out.println(new Solution().numIslands(grid));
        System.out.println(new Solution().numIslands_union_find(grid));
    }
    // https://www.coursera.org/learn/algorithms-part1/lecture/OLXM8/union-find-applications
    static class UnionFind {
        private int[] father;
        private int count;
        
        UnionFind(int n) {
            father = new int[n];
            for (int i = 0; i < n; i++) {
                father[i] = i;
            }
            count = n;
        }
        
        int find(int n) {
            if (father[n] == n) {
                return n;
            }
            return father[n] = find(father[n]);
        }
        
        void connect(int a, int b)  {
            int rootA = find(a);
            int rootB = find(b);
            if (rootA != rootB) {
                father[rootA] = rootB;
                count--;
            }
        }
        int check() {
            return count;
        }
    }

    static class Solution {
        // 更好的方法是用《算法》中的union-find
        public int numIslands_union_find(char[][] grid) { 
            if (grid.length == 0 || grid[0].length == 0) {
                return 0;
            }
            int n = grid.length;
            int m = grid[0].length;

            UnionFind u = new UnionFind(n * m);
            int waterCount = 0;
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < m; j++) {
                    if (grid[i][j] == '1') {
                        if (j + 1 < m && grid[i][j + 1] == '1') {
                            u.connect(i * m + j, i * m + j + 1);
                        }
                        if (i + 1 < n && grid[i + 1][j] == '1') {
                            u.connect(i * m + j, i * m + j + m);
                        }
                    } else {
                        waterCount++;
                    }
                }
            }
            return u.check() - waterCount;
        }

        // n^2
        public int numIslands(char[][] grid) {
            if (grid == null || grid.length == 0 || grid[0].length == 0) {
                return 0;
            }
            int ans = 0;
            boolean[][] v = new boolean[grid.length][grid[0].length]; //visited

            for (int i = 0; i < grid.length; i++) {
                for (int j = 0; j < grid[0].length; j++) {
                    if (grid[i][j] == '1' && !v[i][j]) {
                        ans++;
                        bfs(grid, v, i, j); // 寻找一个区域
                    }
                }
            }

            return ans;
        }

        private void bfs(char[][] grid, boolean[][] v, int sx, int sy) {
            int[] dx = {1, 0, 0, -1}; // 上下左右
            int[] dy = {0, 1, -1, 0};

            Queue<Integer> qx = new LinkedList<>();
            Queue<Integer> qy = new LinkedList<>();
            qx.offer(sx);
            qy.offer(sy);
            v[sx][sy] = true;

            while (!qx.isEmpty()) {
                int cx = qx.poll();
                int cy = qy.poll();

                for (int i = 0; i < 4; i++) {
                    int nx = cx + dx[i];
                    int ny = cy + dy[i];
                    // 分别得到sx, sy的上下左右
                    if (0 <= nx && nx < grid.length
                        && 0 <= ny && ny < grid[0].length
                        && !v[nx][ny]
                        && (grid[nx][ny] == '1')
                    ) { // 确保边界的情况下, 找1的区域, visited置为true
                        qx.offer(nx);
                        qy.offer(ny);
                        v[nx][ny] = true;
                    }
                }
            }
        }
    }
}