
import java.io.ByteArrayOutputStream;
import java.io.OutputStreamWriter;
import java.nio.charset.Charset;
import java.util.PriorityQueue;
import java.util.LinkedList;
import java.util.Map;
import java.util.Set;
 
class Test {
	public static void main(String[] args) {
        LinkedList<Integer> queue = new LinkedList<>();
        queue.add(null);
        queue.add(1);
        queue.add(2);
        queue.add(null);
        queue.add(3);
        queue.add(null);

        while (!queue.isEmpty()) {
            System.out.println(queue.removeFirst());
        }
    }

    public static void testDp(int n, int d) {
        int mod = (int)1e9 + 7;
        int N = 2001;
        int D = 2001;

        int[][] dp = new int[N][D];
        dp[0][0] = 1;

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < d; j++) {
                for (int k = 0; k <= 9; k++) {
                    if (k == 0 || k == 3) continue;
                    dp[i+1][(j*10 + k)%d] += dp[i][j];
                    dp[i+1][(j*10 + k)%d] %= mod;
                }
            }
        }

        System.out.println(dp[n][0]);
    }

    public static void myDp(int n, int d) {
        int mod = (int)1e9 + 7;

        long r = exp_mod(8, n, mod);

        System.err.println(r);
        long result = 0;

        for (long i = 1; i < r; i++) {
            /*
            if ((排除03后的个数) % c == ( ( 倍数 % c ) * ( 最终符合条件的个数 % c ) ) % c) {
                最终符合条件的数
            }
            */
            // 可以完全使用计算后的模
            if (r == ((d % mod) * i) % mod) {
                result = i;
                break;
            }
        }

        System.out.println(result);
    }

    public static long exp_mod(long a,long n,long b) {
        long t;
        if(n == 0) return 1 % b;
        if(n == 1) return a % b;
        t = exp_mod(a, n / 2, b);
        t = t * t % b;
        if((n & 1) == 1) t = t * a % b;
        return t;
    }
 
    private static String getDefaultCharSet() {
        OutputStreamWriter writer = new OutputStreamWriter(new ByteArrayOutputStream());
        String enc = writer.getEncoding();
        return enc;
    }
 
    public static void testPriorityQueue() {
        PriorityQueue<Integer> q = new PriorityQueue<Integer>();
        q.add(1);
        q.add(4);
        q.add(2);
        q.add(0);
        q.add(7);
        q.add(3);

        for (int var : q) {
            System.out.println(var);
        }

        System.out.println("Removing");

        while(!q.isEmpty()) {
            System.out.println(q.remove());
        }
    }

    public static void printMap(Map map) {
        Set<Map.Entry<String, String>> set = map.entrySet();
        set.add(null);
        for (Map.Entry<java.lang.String,java.lang.String> entry : set) {
            System.out.println("Key = " + entry.getKey() + ", Value = " + entry.getValue());
        }
    }
    // %JAVA_HOME%\bin;%JAVA_HOME%\jre\bin;
}