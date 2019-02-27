import java.util.Set;

/**
202. 快乐数

https://leetcode-cn.com/problems/happy-number/
https://leetcode-cn.com/explore/interview/card/top-interview-questions-medium/53/math/112/

编写一个算法来判断一个数是不是“快乐数”。

一个“快乐数”定义为：对于一个正整数，每一次将该数替换为它每个位置上的数字的平方和，然后重复这个过程直到这个数变为 1，也可能是无限循环但始终变不到 1。如果可以变为 1，那么这个数就是快乐数。

示例: 

输入: 19
输出: true
解释: 
12 + 92 = 82
82 + 22 = 68
62 + 82 = 100
12 + 02 + 02 = 1
 */
import java.util.*;
public class P202 {
    public static void main(String[] args) {
        System.out.println(new Solution().isHappy(19));
        System.out.println(new Solution().isHappy(4));
    }
    // HashSet 判断数字是否出现过
    static class Solution {
        Set<Integer> set = new HashSet<>();
        public boolean isHappy(int n) {
            if (set.contains(n)) {
                return false;
            }

            if (n == 1) {
                return true;
            }

            set.add(n);

            int next = 0;
            while (n > 0) {
                next += Math.pow(n % 10, 2);
                n /= 10;
            }

            return isHappy(next);
        }
    }
}