/*
204. 计数质数

https://leetcode-cn.com/explore/interview/card/top-interview-questions-easy/25/math/61/
https://leetcode-cn.com/problems/count-primes/

统计所有小于非负整数 n 的质数的数量。

示例:

输入: 10
输出: 4
解释: 小于 10 的质数一共有 4 个, 它们是 2, 3, 5, 7 。
*/
import java.util.*;
public class P204 {
    public static void main(String[] args) {
        System.out.println(new Solution().countPrimes(15));
        System.out.println(new Solution_traditional().countPrimes(15));
        System.out.println(new Solution_sqrt().countPrimes(15));
    }

    static class Solution {
        /*
        可以使用厄拉多塞筛法, 可以查看同文件夹下的 P204_Sieve_of_Eratosthenes_animation.gif
        比Math.sqrt()效率更优秀

        使用一个boolean[] isDelete 全部设置为false;
        然后遍历
        对应 为false的情况 取出来加一
        然后在遍历 times = 1 i * times < n times ++;
        isDelete[i * times] = true;

        这样就把 对应除以i的数字 找出来 并未true了

        厄拉多塞筛法的步骤：建立从2到n的集合G={2, 3, 4, ..., n}，每次从集合中取出最小的数A，这个数就是质数；
        然后将数A * times从集合中删除，其中1<=times<=n/A。得到一个新的集合G'，重复上述步骤直到集合为空，就取出了所有质数。
        举例一个集合{2, 3, 4, ..., 12}：
        stp1：最小值为2，取出2并删除2，4，6，8，10，12，集合变为{3, 5, 7, 9, 11}；
        stp2：最小值为3，取出3并删除3，6，9，集合变为{5, 7, 11}

        注意 isDeleted[i] == false 的肯定是质数
        */
        public int countPrimes(int n) {
            boolean[] isDeleted = new boolean[n];

            int count = 0;
            for (int i = 2; i < n; i++) {
                if (!isDeleted[i]) {
                    count++;

                    // 当前i一定是质数
                    for (int times = 1; times * i < n; times++) {
                        isDeleted[times * i] = true;
                    }
                }
            }
            return count;
        }
    }

    /* 传统方法
    从2到n - 1, 逐一判断每个数是否为质数。是就累加到count中。
    质数的定义是一个正整数只能被1和本身整除。
    注意在isPrime函数中的循环终止条件是 n * n <= num（利用整除的对称性）,不用从头判断到num本身。
    */
    static class Solution_traditional {
        public int countPrimes(int n) {
            int count = 0;
            for (int i = 2; i < n; i++) {
                if (isPrime(i)) {
                    count++;
                }
            }
            return count;
        }
        
        private boolean isPrime(int num) {
            //check if a given number is a prime number or not
            for (int n = 2; n * n <= num; n++) {
                if (num % n == 0) {
                    return false;
                }
            }
            return true;
        }
    }

    /*
    只遍历之前找到的prime, 判断质数只需要和比自己小的质数不整除就可以。 
    然后利用Math.sprt(num) 来优化
    */
    static class Solution_sqrt {
        /**
         * @param n: a integer
         * @return: return a integer
         */
        public int countPrimes(int n) {
            // write your code here
            List<Integer> primes = new ArrayList<>();
            
            if (n == 1 || n == 2) {
                return 0;
            }
            
            if (n == 3) {
                return 1;
            }
            
            primes.add(2);
            primes.add(3);
        
            for (int i = 4; i < n; i++) {
                if (isPrime(i, primes)) {
                    primes.add(i);
                }
            } 
            
            return primes.size();
        }
        
        private boolean isPrime(int num, List<Integer> primes) {
            for (int prime : primes) {
                if (prime > Math.sqrt(num)) {
                    return true;
                }
                if (num % prime == 0) {
                    return false;
                }
            }
    
            return true;
        }
    }
}