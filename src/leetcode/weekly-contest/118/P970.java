import java.util.*;

/*
970. 强整数 
https://leetcode-cn.com/contest/weekly-contest-118/problems/powerful-integers/
给定两个非负整数 x 和 y，如果某一整数等于 x^i + y^j，其中整数 i >= 0 且 j >= 0，那么我们认为该整数是一个强整数。

返回值小于或等于 bound 的所有强整数组成的列表。

你可以按任何顺序返回答案。在你的回答中，每个值最多出现一次。

 

示例 1：

输入：x = 2, y = 3, bound = 10
输出：[2,3,4,5,7,9,10]
解释： 
2 = 2^0 + 3^0
3 = 2^1 + 3^0
4 = 2^0 + 3^1
5 = 2^1 + 3^1
7 = 2^2 + 3^1
9 = 2^3 + 3^0
10 = 2^0 + 3^2
示例 2：

输入：x = 3, y = 5, bound = 15
输出：[2,4,6,8,10,14]
 

提示：

1 <= x <= 100
1 <= y <= 100
0 <= bound <= 10^6
*/
public class P970 {
    public static void main(String[] args) {
        System.out.println(new Solution().powerfulIntegers(1, 1, 0));
    }

    static class Solution {
        public List<Integer> powerfulIntegers(int x, int y, int bound) {
            if (bound < 2) {
                return Collections.emptyList();
            }
            Set<Integer> set = new HashSet<>(); // 只允许一次

            // 先找出只有x的bound，并保存结果
            int t = x, i = 0, j = 0;
            List<Integer> xlist = new ArrayList<>();
            if (x != 1) {
                while (t <= bound - 1) {
                    t = (int) Math.pow(x, i++);
                    //System.out.printf("%d = %d^%d + %d^%d\n", t + 1, x, i - 1, y, 0);
                    if (t <= bound - 1) {
                        set.add(t + 1); // 只有x^i + y^0
                        xlist.add(t);
                    }
                }
            } else {
                set.add(2); // 只有x^i + y^0
                xlist.add(1);
            }
            // 先找出只有y的bound，并保存结果
            List<Integer> ylist = new ArrayList<>();
            t = y;
            if (y != 1) {
                while (t <= bound - 1) {
                    t = (int)Math.pow(y, j++);
                    //System.out.printf("%d = %d^%d + %d^%d\n", t + 1, x, 0, y, j-1);
                    if (t <= bound - 1) {
                        set.add(t + 1); // 只有x^0 + y^j
                        ylist.add(t);
                    }
                }
            } else {
                set.add(2); // 只有x^i + y^0
                ylist.add(1);
            }
            //System.out.println(xlist);
            //System.out.println(ylist);
            //System.out.println(set);
            for (int k = 1; k < xlist.size(); k++) {
                for (int m = 1; m < ylist.size(); m++) {
                    t = xlist.get(k) + ylist.get(m);
                    if (t <= bound) {
                        //System.out.printf("%d = %d^%d + %d^%d\n", t, xlist.get(k), k, ylist.get(m), m);
                        set.add(t);
                    }
                }
            }
            List<Integer> list = new ArrayList<>();
            list.addAll(set);
            return list;
        }
    }
}