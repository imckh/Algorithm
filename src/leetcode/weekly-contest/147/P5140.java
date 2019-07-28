/*
5140. 字母板上的路径

我们从一块字母板上的位置 (0, 0) 出发，该坐标对应的字符为 board[0][0]。

在本题里，字母板为board = ["abcde", "fghij", "klmno", "pqrst", "uvwxy", "z"].

我们可以按下面的指令规则行动：

如果方格存在，'U' 意味着将我们的位置上移一行；
如果方格存在，'D' 意味着将我们的位置下移一行；
如果方格存在，'L' 意味着将我们的位置左移一列；
如果方格存在，'R' 意味着将我们的位置右移一列；
'!' 会把在我们当前位置 (r, c) 的字符 board[r][c] 添加到答案中。
返回指令序列，用最小的行动次数让答案和目标 target 相同。你可以返回任何达成目标的路径。

 

示例 1：

输入：target = "leet"
输出："DDR!UURRR!!DDD!"
示例 2：

输入：target = "code"
输出："RR!DDRR!UUL!R!"
 

提示：

1 <= target.length <= 100
target 仅含有小写英文字母。
*/
import java.util.*;

public class P5140 {
    public static void main(String[] args) {
        System.out.println(new Solution().alphabetBoardPath("codezd"));
    }

    static class Solution {
        char[][] board = {  "abcde".toCharArray(), 
                            "fghij".toCharArray(), 
                            "klmno".toCharArray(), 
                            "pqrst".toCharArray(), 
                            "uvwxy".toCharArray(), 
                            "z".toCharArray()};

        public String alphabetBoardPath(String target) {
            char[] chars = target.toCharArray();

            StringBuilder result = new StringBuilder();
            int x = 0, y = 0, targetX, targetY;
            for (int i = 0; i < chars.length; i++) {

                int[] pos = findCharPos(chars[i]);
                

                targetX = pos[0];
                targetY = pos[1];

                result.append(charInBoard(x, y, targetX, targetY));

                x = targetX;
                y = targetY;
            }
            return result.toString();
        }

        // xy 当前位置
        // test 比较的字符
        public StringBuilder charInBoard(int x, int y, int targetX, int targetY) {
            StringBuilder step = new StringBuilder();

            if (x != 5) {
                while (y != targetY) {
                    if (y > targetY) {
                        step.append("L");
                        y--;
                    } else {
                        step.append("R");
                        y++;
                    }
                }
                while (x != targetX) {
                    if (x > targetX) {
                        step.append("U");
                        x--;
                    } else {
                        step.append("D");
                        x++;
                    }
                }
            } else { // 从z出发 先垂直移动
                while (x != targetX) {
                    if (x > targetX) {
                        step.append("U");
                        x--;
                    } else {
                        step.append("D");
                        x++;
                    }
                }
                while (y != targetY) {
                    if (y > targetY) {
                        step.append("L");
                        y--;
                    } else {
                        step.append("R");
                        y++;
                    }
                }
            }

            if (x == targetX && y == targetY) {
                step.append("!");
            }
            return step;
        }

        public int[] findCharPos(char c) {
            if (c == 'z') {
                return new int[]{5, 0};
            }
            int[] pos = new int[]{0, 4};
            while (board[pos[0]][pos[1]] != c) {
                if (c > board[pos[0]][pos[1]]) {
                    pos[0]++;
                } else {
                    pos[1]--;
                }
            }
            return pos;
        }
    }
}