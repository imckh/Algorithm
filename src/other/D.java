// 题意见 D.png
public class D {
    public static void main(String[] args) {
        long startt, endt;
        startt = System.currentTimeMillis();
        f3();
        endt = System.currentTimeMillis();
        System.out.println(endt - startt + "ms\n");

        startt = System.currentTimeMillis();
        f1();
        endt = System.currentTimeMillis();
        System.out.println(endt - startt + "ms\n");

        startt = System.currentTimeMillis();
        f2();
        endt = System.currentTimeMillis();
        System.out.println(endt - startt + "ms\n");

    }

    
    public static int sum(int n) {
        int sum = 0;
        while (n != 0) {
            sum += n % 10;
            n /= 10;
        }
        return sum;
    }

    public static void f3() {
        int MAX = 999999999;
        int a = 3, 
            b = 2, 
            c = 8;

        int r = 0;
        for (int i = 1; i <= 81; i++) {
            int x = ((int)Math.pow(i, a)) * b + c;
            if (sum(x) == i) {
                System.out.println(x);
            }
        }
        System.out.println(r);
    }

    public static void f2() {
        int MAX = 999999999;
        int a = 3, 
            b = 2, 
            c = 8;

        int r = 0;
        for (int i = 1; i <= MAX; i++) {
            int x = i - c;
            if (x % b != 0) continue; 
            int s = sum(i);
            // s(x)^a
            if (x == Math.pow(s, a) * b) {
                System.out.println(i + " ");
                r++;
            }
        }
        System.out.println(r);
    }

    public static void f1() {
        int MAX = 999999999;
        int a = 3, 
            b = 2, 
            c = 8;

        int r = 0;
        outer:
        for (int i = 1; i <= MAX; i++) {
            int x = i - c;
            if (x % b != 0) continue; 
            int s = sum(i);
            // s(x)^a
            // if (x == Math.pow(s, a) * b) {
            //     System.out.println(i + " ");
            //     r++;
            // }
            int y = x / b;
            for (int j = 0; j < a; j++) {
                if (y % s != 0) {
                    continue outer;
                } else {
                    y /= s;
                }
            }
            if (y == 1) {
                System.out.println(i + " ");
                r++;
            }
        }
        System.out.println(r);
    }
}