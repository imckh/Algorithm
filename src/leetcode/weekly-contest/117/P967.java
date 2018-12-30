/*
967. 连续差相同的数字

https://leetcode-cn.com/contest/weekly-contest-117/problems/numbers-with-same-consecutive-differences/

题目难度 Medium
返回所有长度为 N 且满足其每两个连续位上的数字之间的差的绝对值为 K 的非负整数。

请注意，除了数字 0 本身之外，答案中的每个数字都不能有前导零。例如，01 因为有一个前导零，所以是无效的；但 0 是有效的。

你可以按任何顺序返回答案。

 

示例 1：

输入：N = 3, K = 7
输出：[181,292,707,818,929]
解释：注意，070 不是一个有效的数字，因为它有前导零。
示例 2：

输入：N = 2, K = 1
输出：[10,12,21,23,32,34,43,45,54,56,65,67,76,78,87,89,98]
 

提示：

1 <= N <= 9
0 <= K <= 9
*/
import java.util.*;
public class P967 {
    public static void main(String[] args) {
        System.out.println(Arrays.toString(new Solution().numsSameConsecDiff(1, 6)));
        //System.out.println(new Solution().numsSameConsecDiff(3, 7));
    }
    static class Solution {
        Set<Integer> result = new HashSet<>();
        public int[] numsSameConsecDiff(int N, int K) {
            if (N < 1) return new int[0];
            if (N == 1) {
                return new int[]{0,1,2,3,4,5,6,7,8,9};
            }
            bfs("", N, K, 0, true);
            int[] arr = new int[result.size()];
            int i = 0;
            for (int v : result) {
                arr[i++] = v;
            }
            return arr;
        }

        public void bfs(String last, int N, int K, int len, boolean isfirst) {
            if (len == N) {
                result.add(Integer.valueOf(last));
                return;
            }
            if (isfirst) {
                for (int i =  N == 1 ? 0 : 1; i < 10; i++) {
                    if (!(i + K >= 0 && i + K <= 9) && !((i - K >= 0 && i - K <= 9))) {
                        continue;
                    }
                    // 找到差的绝对值是K的非负整数
                    if (i + K >= 0 && i + K <= 9) {
                        String next = last + i;
                        //System.out.println(next);
                        bfs(next, N, K, len + 1, false);
                    }
                    if (i - K >= 0 && i - K <= 9) {
                        String next = last + i;
                        //System.out.println(next);
                        bfs(next, N, K, len + 1, false);
                    }
                }
            } else {
                //System.out.println("last:len" + last + " " + len);
                int lastN = last.charAt(len-1) - '0';
                if (!(lastN + K >= 0 && lastN + K <= 9) && !((lastN - K >= 0 && lastN - K <= 9))) {
                    return;
                }
                // 找到差的绝对值是K的非负整数
                if (lastN + K >= 0 && lastN + K <= 9) {
                    String next = last + (lastN + K);
                    //System.out.println(next);
                    bfs(next, N, K, len + 1, false);
                }
                if (lastN - K >= 0 && lastN - K <= 9) {
                    String next = last + (lastN - K);
                    //System.out.println(next);
                    bfs(next, N, K, len + 1, false);
                }
            }
        }
    }
}