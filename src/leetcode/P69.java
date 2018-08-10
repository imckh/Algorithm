// https://leetcode-cn.com/problems/sqrtx/description/
public class P69 {
    public static void main(String[] args) {
        System.out.println("------P69------");
        
        Solution solution = new Solution();

        System.out.println(solution.mySqrt(8));
        System.out.println(solution.sqrt(8.0, 0.000000001));

        System.out.println("------P69------");
    }
}
class Solution {
    // http://www.matongxue.com/madocs/205.html
    // https://www.cnblogs.com/Matrix_Yao/archive/2009/07/28/1532883.html
    public int mySqrt(int x) {
        int count = 0;
        long n = x;
        // (x(n+1)) = (xn) - (f(xn) / f'(xn))
        while (n * n > x) {
            n = (n + x / n) >> 1; // same as ÷ 2
            count++;
        }
        System.out.println(count);
        return (int) n;
    }

    /**
     * 牛顿迭代法求平方根
     * 
     * @param number   求值的数
     * @param accuracy 精度
     * @return Double
     */
    public static double sqrt(double number, double accuracy) {
        // 第一个猜测值
        double guess = number / 2;
        int count = 0;
        if (number < 0) {
            return Double.NaN;
        }
        // 当两个猜测的差值大于精度即return
        while (Math.abs(guess - (number / guess)) > accuracy) {
            // 迭代公式推导而成
            guess = (guess + (number / guess)) / 2;
            count++;
            System.out.printf("try count = %d, guess = %f\n", count, guess);
        }
        System.out.printf("final result = %f\n", guess);
        return guess;
    }
}