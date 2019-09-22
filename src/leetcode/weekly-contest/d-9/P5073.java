/*
5073. 进击的骑士

一个坐标可以从 -infinity 延伸到 +infinity 的 无限大的 棋盘上，你的 骑士 驻扎在坐标为 [0, 0] 的方格里。

骑士的走法和中国象棋中的马相似，走 “日” 字：即先向左（或右）走 1 格，再向上（或下）走 2 格；或先向左（或右）走 2 格，再向上（或下）走 1 格。

每次移动，他都可以按图示八个方向之一前进。

https://assets.leetcode.com/uploads/2018/10/12/knight.png

现在，骑士需要前去征服坐标为 [x, y] 的部落，请你为他规划路线。

最后返回所需的最小移动次数即可。本题确保答案是一定存在的。

 

示例 1：

输入：x = 2, y = 1
输出：1
解释：[0, 0] → [2, 1]
示例 2：

输入：x = 5, y = 5
输出：4
解释：[0, 0] → [2, 1] → [4, 2] → [3, 4] → [5, 5]
 

提示：

|x| + |y| <= 300
*/
class Solution {
    public int minKnightMoves(int x, int y) {
        if (x == 0 && y == 0) return 0;
        x = Math.abs(x)+2;
        y = Math.abs(y)+2;
        int[][] dp = new int[x + 4][y + 4];

        for (int i = 0; i < dp.length; i++) {
            for (int j = 0; j < dp[0].length; j++) {
                dp[i][j] = Integer.MAX_VALUE;
            }
        }
        int cx = 2, cy = 2; // current
        int i = 0;
        dp[cx][cy] = 0;
        // print(dp);
        // System.out.println(x + " " + y);
        while (helper(dp, ++i, x, y)) {
            // print(dp);
            // System.out.println("=============================");
        }

        return dp[x][y];
    }

    // 从0，0开始， 走n步可以到达的位置
    public boolean helper(int[][] dp, int n, int x, int y) {
        boolean find = false;
        for (int i = 1; i < dp.length-2; i++) {
            for (int j = 1; j < dp[0].length-2; j++) {
                if (dp[i][j] == n - 1) {
                    find = true;
                    dp[i + 1][j + 2] = Math.min(dp[i + 1][j + 2], dp[i][j] + 1);
                    dp[i + 2][j + 1] = Math.min(dp[i + 2][j + 1], dp[i][j] + 1);
                    if (i - 1 >= 0) dp[i - 1][j + 2] = Math.min(dp[i - 1][j + 2], dp[i][j] + 1);
                    if (j - 1 >= 0) dp[i + 2][j - 1] = Math.min(dp[i + 2][j - 1], dp[i][j] + 1);

                    if (j - 2 >= 0 && i - 1 >= 0) dp[i - 1][j - 2] = Math.min(dp[i - 1][j - 2], dp[i][j] + 1);
                    if (i - 2 >= 0 && j - 1 >= 0) dp[i - 2][j - 1] = Math.min(dp[i - 2][j - 1], dp[i][j] + 1);
                    if (j - 2 >= 0) dp[i + 1][j - 2] = Math.min(dp[i + 1][j - 2], dp[i][j] + 1);
                    if (i - 2 >= 0) dp[i - 2][j + 1] = Math.min(dp[i - 2][j + 1], dp[i][j] + 1);
                    // System.out.println("i=" + i + " j=" + j);
                    // if ((i == x-1 && j == y-1) || (i == y-1 && j == x-1)) {
                    //     return false;
                    // }
                    if ((i == x && j == y) || (i == y && j == x)) {
                        return false;
                    }
                }
            }
        }
        if (!find) {
            return false;
        }
        return true;
    }

    public void print(int[][] dp) {
        for (int i = 0; i < dp.length; i++) {
            for (int j = 0; j < dp[0].length; j++) {
                String x = dp[i][j] == Integer.MAX_VALUE ? "_" : dp[i][j] + "";
                System.out.print(x + "  ");
            }
            System.out.println();
        }
    }
}