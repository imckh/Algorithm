import java.util.Arrays;

/*
289. 生命游戏

https://leetcode-cn.com/problems/game-of-life/
https://leetcode-cn.com/explore/interview/card/top-interview-questions-hard/55/array-and-strings/127/

根据百度百科，生命游戏，简称为生命，是英国数学家约翰·何顿·康威在1970年发明的细胞自动机。

给定一个包含 m × n 个格子的面板，每一个格子都可以看成是一个细胞。每个细胞具有一个初始状态 live（1）即为活细胞， 或 dead（0）即为死细胞。每个细胞与其八个相邻位置（水平，垂直，对角线）的细胞都遵循以下四条生存定律：

如果活细胞周围八个位置的活细胞数少于两个，则该位置活细胞死亡；
如果活细胞周围八个位置有两个或三个活细胞，则该位置活细胞仍然存活；
如果活细胞周围八个位置有超过三个活细胞，则该位置活细胞死亡；
如果死细胞周围正好有三个活细胞，则该位置死细胞复活；
根据当前状态，写一个函数来计算面板上细胞的下一个（一次更新后的）状态。下一个状态是通过将上述规则同时应用于当前状态下的每个细胞所形成的，其中细胞的出生和死亡是同时发生的。

示例:

输入: 
[
  [0,1,0],
  [0,0,1],
  [1,1,1],
  [0,0,0]
]
输出: 
[
  [0,0,0],
  [1,0,1],
  [0,1,1],
  [0,1,0]
]
进阶:

你可以使用原地算法解决本题吗？请注意，面板上所有格子需要同时被更新：你不能先更新某些格子，然后使用它们的更新后的值再更新其他格子。
本题中，我们使用二维数组来表示面板。原则上，面板是无限的，但当活细胞侵占了面板边界时会造成问题。你将如何解决这些问题？
*/
public class P289 {
    public static void main(String[] args) {
        int[][] board = {
            {0,1,0},
            {0,0,1},
            {1,1,1},
            {0,0,0}
        };
        print(board);
        new Solution().gameOfLife(board);
        print(board);
    }
    
    static class Solution {
        /*
        1.首先根据题意我们可以得出周围细胞存活数量对当前细胞的影响：
            <2 死亡
            =2 原状态
            =3 存活
            >3 死亡
        2.由于要使用原地算法，我们需要在原二维数组对细胞下一次状态进行重新编码：
            0： 死亡=>死亡  0=>0    0 : 上一轮是0，这一轮过后还是0
            1： 存活=>存活  1=>1    1 : 上一轮是1，这一轮过后还是1
            2： 存活=>死亡  1=>0    2 : 上一轮是1，这一轮过后变为0
            3： 死亡=>存活  0=>1    3 : 上一轮是0，这一轮过后变为1
            对于一个节点来说，如果它周边的点是1或者2，就说明那个点上一轮是活的。
        3. 对于编码后的数组要一次性重置状态，需要将0和2置为0；1和3置为1。将0，1，2，3对2取余就可以了。
        */
        public void gameOfLife(int[][] board) {
            int yl = board.length;
            int xl = board[0].length;
            for (int y = 0; y < yl; y++) {
                for (int x = 0; x < xl; x++) {
                    int lives = 0; // 周围存活数量

                    // 上
                    if (y > 0) {
                        lives += board[y - 1][x] == 1 || board[y - 1][x] == 2 ? 1 : 0;
                    }
                    // 判断左边
                    if(x > 0){
                        lives += board[y][x - 1] == 1 || board[y][x - 1] == 2 ? 1 : 0;
                    }
                    // 判断下边
                    if(y < yl - 1){
                        lives += board[y + 1][x] == 1 || board[y + 1][x] == 2 ? 1 : 0;
                    }
                    // 判断右边
                    if(x < xl - 1){
                        lives += board[y][x + 1] == 1 || board[y][x + 1] == 2 ? 1 : 0;
                    }
                    // 判断左上角
                    if(y > 0 && x > 0){
                        lives += board[y - 1][x - 1] == 1 || board[y - 1][x - 1] == 2 ? 1 : 0;
                    }
                    //判断右下角
                    if(y < yl - 1 && x < xl - 1){
                        lives += board[y + 1][x + 1] == 1 || board[y + 1][x + 1] == 2 ? 1 : 0;
                    }
                    // 判断右上角
                    if(y > 0 && x < xl - 1){
                        lives += board[y - 1][x + 1] == 1 || board[y - 1][x + 1] == 2 ? 1 : 0;
                    }
                    // 判断左下角
                    if(y < yl - 1 && x > 0){
                        lives += board[y + 1][x - 1] == 1 || board[y + 1][x - 1] == 2 ? 1 : 0;
                    }

                    // 根据周边存活数量更新当前点，结果是0和1的情况不用更新
                    if (board[y][x] == 0 && lives == 3) {
                        board[y][x] = 3;
                    } else if (board[y][x] == 1) {
                        if(lives < 2 || lives > 3) board[y][x] = 2;
                    }
                }
            }
            // 解码
            for(int i = 0; i < yl; i++){
                for(int j = 0; j < xl; j++){
                    board[i][j] = board[i][j] % 2;
                }
            }
        }
    }

    static void print(int[][] nums) {
        for (int[] c : nums) {
            System.out.println(Arrays.toString(c));
        }
        System.out.println();
    }
} 