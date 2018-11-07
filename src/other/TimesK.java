import java.sql.Timestamp;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

/*
题目描述
给你一个长度为n的数组和一个正整数k，问从数组中任选两个数使其和是k的倍数，有多少种选法
对于数组a1=1 , a2=2 , a3=2而言：
(a1,a2)和(a2,a1)被认为是同一种选法；
(a1,a2)和(a1,a3)被认为是不同的选法。

输入数据
第一行有两个正整数n，k。n<=1000000，k<=1000000 第二行有n个正整数，每个数的大小不超过1e9

输出数据
选出一对数使其和是k的倍数的选法个数

样例输入
5 6
1 2 3 4 5
样例输出
2
样例说明
样例解释：
a1+a5=6，a2+a4=6，都是6的倍数
所以符合条件的选法有（1，5），（2，4）
*/
public class TimesK {
    public static void main(String[] args) {
        // int n = 1000000;
        // //int[] arr = new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 0, 12, 18};
        // // int[] arr = new int[]{2, 7, 12, 17};
        // int[] arr = new int[n];
        // for (int i = 0; i < n; i++) {
        //     arr[i] = i;
        // }
        // int k = 999999;
        // System.out.println("k : " + k);
        // System.out.println("array length : " + n);
        // long start = new Date().getTime();

        // System.out.println("getK \t\t getKfor");
        // for (int i = 2; i < 100000; i+=53) {
            
        //     int randomN = new Random().nextInt(100000);
        //     int[] arr = new int[randomN];
        //     for (int j = 0; j < randomN; j++) {
        //         arr[j] = new Random().nextInt(1000000000);;
        //     }
        //     int k = i - 1;
        //     k = new Random().nextInt(randomN-1);
        //     System.out.print("k:" + k + "\ti:" + randomN + "\t\t");

        //     long start = new Date().getTime();
        //     long kmap = getK(arr, k);
        //     long end = new Date().getTime();
        //     long kmaptime = end - start;

        //     start = new Date().getTime();
        //     long kfor = getKfor(arr, k);
        //     end = new Date().getTime();
        //     long kfortime = end - start;

        //     System.out.println(kmap + " " + kmaptime + "ms" + " \t\t " + kfor + " " + kfor + "ms");// + " " + Arrays.toString(arr));
        //     if (kmap != kfor) {
        //         System.out.println("error");
        //         return;
        //     }
        // }
        // System.out.println("ALL CORRECT END");
        
        int n = 199999;
        int[] arr = new int[n + 1];
        for (int i = 0; i <= n; i++) {
            arr[i] = i + 1;
        }
        //System.out.println(Arrays.toString(arr));
        int k = 12;
        System.out.println("k : " + k);
        System.out.println("array length : " + n);
        long start = new Date().getTime();

        System.out.println(getK(arr, k));

        long end = new Date().getTime();
        System.out.println(end - start + "ms");

        start = new Date().getTime();
        long kfor = getKfor(arr, k);
        end = new Date().getTime();
        System.out.println("n^2算法: " + kfor + "  " + (end - start) + "ms");
        /*
        时间差距
        k:23171 i:30369         19635 16ms               19635 19635ms
        k:6300  i:7101          4045 4ms                 4045 4045ms
        */
    }

    public static long getK357(int[] arr, int k) {
        return 0L;
    }

    public static long getKfor(int[] arr, int k) {
        int res = 0;
        for (int i = 0; i < arr.length; i++) {
            for (int j = i + 1; j < arr.length; j++) {
                if ((arr[i] + arr[j]) % k == 0) {
                    res++;
                }
            }
        }
        return res;
    }

    public static long getK(int[] arr, int k) {
        long result = 0;
        Map<Integer, Integer> remainder = new HashMap<Integer, Integer>();

        // 按余数分类
        for (int a : arr) {
            remainder.put(a % k, remainder.containsKey(a % k) ? remainder.get(a % k) + 1: 1);
        }
        //printMap(remainder);
        // 余数为0
        // 如果k = 6, arr=[0, 6, 12, 18]
        // result = 3 + 2 + 1;
        if (remainder.containsKey(0)) {
            for (int i = remainder.get(0); i > 0; i--) {
                result += i-1;
            }
            remainder.remove(0);
        }

        // 奇数需要到 < (k / 2 + 1), 偶数因为k/2下一步会用到, 所以 < (k/2)
        int kmax = (k % 2 == 0) ? (k / 2) : (k / 2 + 1);
        for (int i = 1; i < kmax; i++) {
            if (!(remainder.containsKey(i) && remainder.containsKey(k - i))) {
                continue;
            }
            // 如果余数和为k(且余数不为0), 则result = 余数为i的个数 * 余数为k-i的个数
            result += remainder.get(i) * remainder.get(k - i);
            
            remainder.remove(i);
            remainder.remove((k - i));
        }

        // 余数为k/2, 6/2 = 3
        // 如果k = 6, arr=[3, 9, 15, 21]
        // result = 3 + 2 + 1;
        if (k%2 == 0 && remainder.containsKey((k/2))) {
            for (int i = remainder.get(k/2); i > 0; i--) {
                result += i-1;
            }
            remainder.remove(k/2);
        }
        
        return result;
    }

    public static void printMap(Map<Integer, Integer> map) {
        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            System.out.println("Key = " + entry.getKey() + ", Value = " + entry.getValue());
        }
    }
}
/*
k : 6
array length : 10000000
Key = 0, Value = 1666667
Key = 1, Value = 1666667
Key = 2, Value = 1666667
Key = 3, Value = 1666667
Key = 4, Value = 1666666
Key = 5, Value = 1666666
result: 1385690422908
time: 901ms
*/