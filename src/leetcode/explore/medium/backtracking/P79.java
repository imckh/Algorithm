/*
79. 单词搜索

https://leetcode-cn.com/problems/word-search/
https://leetcode-cn.com/explore/interview/card/top-interview-questions-medium/49/backtracking/95/

给定一个二维网格和一个单词，找出该单词是否存在于网格中。

单词必须按照字母顺序，通过相邻的单元格内的字母构成，其中“相邻”单元格是那些水平相邻或垂直相邻的单元格。同一个单元格内的字母不允许被重复使用。

示例:

board =
[
  ['A','B','C','E'],
  ['S','F','C','S'],
  ['A','D','E','E']
]

给定 word = "ABCCED", 返回 true.
给定 word = "SEE", 返回 true.
给定 word = "ABCB", 返回 false.
*/

public class P79 {
    public static void main(String[] args) {
        char[][] board =
        {
          {'A','B','C','E'},
          {'S','F','C','S'},
          {'A','D','E','E'}
        };
        String word = "ABCCEDASFC";
        System.out.println(new Solution().exist(board, word));
    }

    static class Solution {
        // 这个题是找一条不重复的路径
        // 依次这条路径的每个点, 正好是给定单词
        // 可以用图的深度优先遍历
        static boolean[][] visited;
        public boolean exist(char[][] board, String word) {
            visited = new boolean[board.length][board[0].length];

            // 以二维位数组的每个节点开始
            for (int y = 0; y < board.length; y++) {
                for (int x = 0; x < board[y].length; x++) {
                    if ((word.charAt(0) == board[y][x]) && exist(board, word, y, x, 0)) {
                        return true;
                    }
                }
            }
            return false;
        }

        private boolean exist(char[][] board, String word, int y, int x, int index) {
            if (index == word.length()) {
                return true;
            }

            if (y >= board.length || y < 0
                || x >= board[y].length || x < 0 // 数组边界判断
                || board[y][x] != word.charAt(index) // 字符是否相同
                || visited[y][x]) { // 当前路径没有被访问过
                return false;
            }

            visited[y][x] = true; 

            // 上下左右
            if (exist(board, word, y-1, x, index + 1)
                || exist(board, word, y+1, x, index + 1)
                || exist(board, word, y, x-1, index + 1)
                || exist(board, word, y, x+1, index + 1)
                ) {
                return true;
            }
            visited[y][x] = false;
            return false;
        }
    }
}