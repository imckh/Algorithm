/*
5130. 等价多米诺骨牌对的数量

给你一个由一些多米诺骨牌组成的列表 dominoes。

如果其中某一张多米诺骨牌可以通过旋转 0 度或 180 度得到另一张多米诺骨牌，我们就认为这两张牌是等价的。

形式上，dominoes[i] = [a, b] 和 dominoes[j] = [c, d] 等价的前提是 a==c 且 b==d，或是 a==d 且 b==c。

在 0 <= i < j < dominoes.length 的前提下，找出满足 dominoes[i] 和 dominoes[j] 等价的骨牌对 (i, j) 的数量。

 

示例：

输入：dominoes = [[1,2],[2,1],[3,4],[5,6]]
输出：1
 

提示：

1 <= dominoes.length <= 40000
1 <= dominoes[i][j] <= 9
*/

import java.util.*;

public class P5130 {
    public static void main(String[] args) {
        // int[][] arr1 = {{ 5, 6 }, { 2, 9 },{9, 2 }, { 3, 8 }, { 6, 5 }, { 8, 3 }, 
        // { 1, 2 }, { 3, 4 }, { 2, 1 },  { 3, 4 },  };
        int[][] arr1 = {{ 1, 2 }, { 1, 2 },{1, 2 },{1, 1 },{2, 2 }};

        System.out.println(new Solution().numEquivDominoPairs(arr1));
    }

    static class Solution {

        public int numEquivDominoPairs(int[][] dominoes) {
            if (dominoes == null || dominoes.length <= 1) {
                return 0;
            }

            int[][] t = new int[10][10];
            for (int i = 0; i < dominoes.length; i++) {
                t[dominoes[i][0]][dominoes[i][1]]++;
            }

            for (int i = 0; i < 10; i++) {
                for (int j = 0; j < 10; j++) {
                    System.out.print(t[i][j] + " ");
                }
                System.out.println();
            }

            int result = 0;
            for (int i = 1; i < 10; i++) {
                for (int j = i; j < 10; j++) {
                    int temp = 0;
                    int sum = i == j ? t[i][j] : t[i][j] + t[j][i];

                    for (int k = 0; k < sum; k++) {
                        temp += k;
                    }
                    result+=temp;
                }
            }

            return result;
        }
    }
}