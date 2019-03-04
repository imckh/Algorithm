/*
621. 任务调度器

https://leetcode-cn.com/problems/task-scheduler/
https://leetcode-cn.com/explore/interview/card/top-interview-questions-medium/54/others/122/

给定一个用字符数组表示的 CPU 需要执行的任务列表。其中包含使用大写的 A - Z 字母表示的26 种不同种类的任务。任务可以以任意顺序执行，并且每个任务都可以在 1 个单位时间内执行完。CPU 在任何一个单位时间内都可以执行一个任务，或者在待命状态。

然而，两个相同种类的任务之间必须有长度为 n 的冷却时间，因此至少有连续 n 个单位时间内 CPU 在执行不同的任务，或者在待命状态。

你需要计算完成所有任务所需要的最短时间。

示例 1：

输入: tasks = ["A","A","A","B","B","B"], n = 2
输出: 8
执行顺序: A -> B -> (待命) -> A -> B -> (待命) -> A -> B.

注：

    1. 任务的总个数为 [1, 10000]。
    2. n 的取值范围为 [0, 100]。
*/
import java.util.*;
public class P621 {
    public static void main(String[] args) {
        System.out.println(new Solution().leastInterval(new char[]{'A','A','A','B','B','B'}, 2));
    }

    static class Solution {
        /*
        从举例子中我们可以得出任务调度的规律。
        如给定：
            AAABBCD，n=2。
            那么我们满足个数最多的任务所需的数量，即可以满足任务间隔要求，即：AXXAXXA；
            （其中，X表示需要填充任务或者idle的间隔）

        如果有两种或两种以上的任务具有相同的最多的任务数，如：AAAABBBBCCDE，n=3。
            那么我们将具有相同个数的任务A和B视为一个任务对，
            最终满足要求的分配为：ABXXABXXABXXAB，
            剩余的任务在不违背要求间隔的情况下穿插进间隔位置即可，空缺位置补idle。

        由上面的分析我们可以得到最终需要最少的任务时间：
            （最多任务数-1）*（n + 1） + （相同最多任务的任务个数）。
 
        有上面的例子来说就是：(num(A)-1) * (3+1) + (2)。
        */
        public int leastInterval(char[] tasks, int n) {
            int[] count = new int[26];

            for (char c : tasks) {
                count[c - 'A']++;
            }
            Arrays.sort(count);

            int i = 25;
            while (i >= 0 && count[i] == count[25]) {
                i--;
            }
            return Math.max(tasks.length, (count[25] - 1) * (n + 1) + (25 - i));
        }
    }
}