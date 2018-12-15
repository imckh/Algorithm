/*
36. 有效的数独
判断一个 9x9 的数独是否有效。只需要根据以下规则，验证已经填入的数字是否有效即可。

数字 1-9 在每一行只能出现一次。
数字 1-9 在每一列只能出现一次。
数字 1-9 在每一个以粗实线分隔的 3x3 宫内只能出现一次。

https://upload.wikimedia.org/wikipedia/commons/thumb/f/ff/Sudoku-by-L2G-20050714.svg/250px-Sudoku-by-L2G-20050714.svg.png
上图是一个部分填充的有效的数独。

数独部分空格内已填入了数字，空白格用 '.' 表示。

示例 1:

输入:
[
  ["5","3",".",".","7",".",".",".","."],
  ["6",".",".","1","9","5",".",".","."],
  [".","9","8",".",".",".",".","6","."],
  ["8",".",".",".","6",".",".",".","3"],
  ["4",".",".","8",".","3",".",".","1"],
  ["7",".",".",".","2",".",".",".","6"],
  [".","6",".",".",".",".","2","8","."],
  [".",".",".","4","1","9",".",".","5"],
  [".",".",".",".","8",".",".","7","9"]
]
输出: true
示例 2:

输入:
[
  ["8","3",".",".","7",".",".",".","."],
  ["6",".",".","1","9","5",".",".","."],
  [".","9","8",".",".",".",".","6","."],
  ["8",".",".",".","6",".",".",".","3"],
  ["4",".",".","8",".","3",".",".","1"],
  ["7",".",".",".","2",".",".",".","6"],
  [".","6",".",".",".",".","2","8","."],
  [".",".",".","4","1","9",".",".","5"],
  [".",".",".",".","8",".",".","7","9"]
]
输出: false
解释: 除了第一行的第一个数字从 5 改为 8 以外，空格内其他数字均与 示例1 相同。
     但由于位于左上角的 3x3 宫内有两个 8 存在, 因此这个数独是无效的。
说明:

一个有效的数独（部分已被填充）不一定是可解的。
只需要根据以上规则，验证已经填入的数字是否有效即可。
给定数独序列只包含数字 1-9 和字符 '.' 。
给定数独永远是 9x9 形式的。
*/
import java.util.*;
public class P36 {
    public static void main(String[] args) {
        char[][] board = {
            {'8','3','.','.','7','.','.','.','.'},
            {'6','.','.','1','9','5','.','.','.'},
            {'.','9','8','.','.','.','.','6','.'},
            {'8','.','.','.','6','.','.','.','3'},
            {'4','.','.','8','.','3','.','.','1'},
            {'7','.','.','.','2','.','.','.','6'},
            {'.','6','.','.','.','.','2','8','.'},
            {'.','.','.','4','1','9','.','.','5'},
            {'.','.','.','.','8','.','.','7','9'}
          };
        //System.out.println(new Solution().isValidSudoku(board));
        System.out.println(new Solution2().isValidSudoku(board));
    }

    static class Solution {
        public boolean isValidSudoku(char[][] board) {
            boolean[] visited = new boolean[9];
            
            // row
            for(int i = 0; i<9; i++){
                Arrays.fill(visited, false);
                for(int j = 0; j<9; j++){
                    if(!process(visited, board[i][j]))
                        return false;
                }
            }
            
            //col
            for(int i = 0; i<9; i++){
                Arrays.fill(visited, false);
                for(int j = 0; j<9; j++){
                    if(!process(visited, board[j][i]))
                        return false;
                }
            }
            
            // sub matrix
            for(int i = 0; i<9; i+= 3){
                for(int j = 0; j<9; j+= 3){
                    Arrays.fill(visited, false);
                    for(int k = 0; k<9; k++){
                        if(!process(visited, board[i + k/3][ j + k%3]))
                        return false;                   
                    }
                }
            }
            return true;
            
        }
        
        // 判断当前字符是否符合规则, 而且有没有被访问过
        private boolean process(boolean[] visited, char digit){
            if(digit == '.'){
                return true;
            }
            
            int num = digit - '0';
            if ( num < 1 || num > 9 || visited[num-1]){
                return false;
            }
            
            visited[num-1] = true;
            return true;
        }
    }

    static class Solution2 {
        public boolean isValidSudoku(char[][] board) {
            // boolean[i][char] i代表第i(行/列/个子矩阵), char代表1~9个字符的出现
            boolean[][] markRow = new boolean[9][10]; // 第i行的字符标记, 标记已被使用过为true, 有重复的直接return false
            boolean[][] markCol = new boolean[9][10]; // 第j列的字符标记, 标记已被使用过
            boolean[][] markThree = new boolean[9][10];
            for (int i = 0; i < 9; i++) {
                for (int j = 0; j < 9; j++) {
                    if ('.' != board[i][j]) {
                        int temp = board[i][j] - '0';
                        int index = j / 3 + i / 3 * 3;
                        if (markRow[i][temp]) {
                            return false;
                        } else {
                            markRow[i][temp] = true;
                        }
                        if (markCol[j][temp]) {
                            return false;
                        } else {
                            markCol[j][temp] = true;
                        }
                        if (markThree[index][temp]) {
                            return false;
                        } else {
                            markThree[index][temp] = true;
                        }
                    }

                }
            }
            return true;
        }
    }

    public static void print2DArray(boolean[][] arr) {
        for (int i = 0; i < arr.length; i++) {
            //for (int j = 0; j < arr[0].length; j++) {
                System.out.println(Arrays.toString(arr[i]));
            //}
            //System.out.println();
        }
    }
}