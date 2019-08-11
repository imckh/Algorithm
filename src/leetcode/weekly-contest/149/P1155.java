/*
1155. 掷骰子的N种方法

这里有 d 个一样的骰子，每个骰子上都有 f 个面，分别标号为 1, 2, ..., f。

我们约定：掷骰子的得到总点数为各骰子面朝上的数字的总和。

如果需要掷出的总点数为 target，请你计算出有多少种不同的组合情况（所有的组合情况总共有 f^d 种），模 10^9 + 7 后返回。

 

示例 1：

输入：d = 1, f = 6, target = 3
输出：1
示例 2：

输入：d = 2, f = 6, target = 7
输出：6
示例 3：

输入：d = 2, f = 5, target = 10
输出：1
示例 4：

输入：d = 1, f = 2, target = 3
输出：0
示例 5：

输入：d = 30, f = 30, target = 500
输出：222616187
 

提示：

1 <= d, f <= 30
1 <= target <= 1000
*/
import java.text.*;
import java.util.Arrays;

public class P1155 {
    public static void main(String[] args) {
        int d = 30, f = 30, target = 100;
        System.out.println(new Solution().numRollsToTarget(d, f, target));

        // 1771
    }

    static class Solution2 {
        int count = 0;
        int result;
        int[] arr = new int[30]; // 当前各个骰子的点数
        // ( a + b) % c = ( ( a % c ) + ( b % c ) ) % c
        // ( a * b) % c = ( ( a % c ) * ( b % c ) ) % c
        // ( a – b) % c = ( ( a % c ) – ( b % c ) ) % c
        // 
        public int numRollsToTarget(int d, int f, int target) {
            if (d > target || d * f < target) {
                return 0;
            }
            if (d == target || d * f == target) {
                return 1;
            }
            helper(f, d, f, target);
            return result;
        }

        /**
         * @param lastValue 上次的值
         * @param d 剩余骰子个数
         * @param f
         * @param target 剩余点数
         * @return
         */
        public int helper(int lastValue, int d, int f, int target) {
            // System.out.println(d + " " + f + " " + target);
            System.out.println(count++);
            if (d == 1) {
                if (f >= target) { // 最后一个, 剩余点数比骰子面数小
                    result++;

                    arr[arr.length - d] = target;
                    System.out.println(Arrays.toString(arr));
                }
            } else {

                for (int i = 1; i <= f; i++) { // i == 当前骰子的点数
                    // 遍历可用点数
                    arr[arr.length - d] = i;
                    helper(i, d-1, f, target-i);
                }
            }
            return result;
        }
    }

    static class Solution {
        public int numRollsToTarget(int d, int f, int target) {
            if (d == 1)
                return target >= 1 && target <= f ? 1 : 0;
            int sum = 0;
            for (int k = 1; k <= f; k++)
                sum += numRollsToTarget(d - 1, f, target - k);
            return sum;
        }
    }
}