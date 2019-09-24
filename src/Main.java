import java.util.Arrays;

/**
 * @author CKH
 * @date 2018/2/22 21:26
 */
public class Main extends Test{
    public static void main(String[] args) {
        Solution s = new Solution();
        int[] cont = {53687106, 53687106,53687106, 53687106,53687106};
        // int[] cont = {0,0,3};


       System.out.println(Arrays.toString(s.fraction(cont)));
    //    int[] cont = {3, 2, 0, 2};
    //    System.out.println(Arrays.toString(s.fraction(cont)));
    }

    static class Solution {
        public int[] fraction(int[] cont) {
            long[] arr = new long[2];
            int[] result = new int[2];
            
            if (cont.length == 1) {
                result[0] = cont[0];
                result[1] = 0;
                return result;
            }
            
            arr[0] = cont[cont.length-1];
            arr[1] = 1;
            
            // if (cont.length < 2) return arr;
            
            for (int i = cont.length-2; i >= 0; i--) {
                long t = arr[1];
                arr[1] = arr[0];
                arr[0] = t;
                
                System.out.println(Arrays.toString(arr));

                long a = cont[i];
                System.out.println("a = " + a);
                arr[0] = arr[0] + (arr[1] * a);

                System.out.println("arr = " + arr[0] + "/" + arr[1]);
            }
            
            reducing(arr);

            result[0] = (int)arr[0];
            result[1] = (int)arr[1];

            return result;
        }

        public void reducing(long[] arr) {
            long denominator = arr[0];
            long numerator = arr[1];
            // 即求出最大公因数
            long smaller = numerator > denominator ? numerator : denominator;
            long maxCommonFactor = 1;
            for (int i = 1; i <= smaller; i++) {
                if (numerator % i == 0 && denominator % i == 0) {
                    maxCommonFactor = i;
                }
            }
            arr[0] = denominator / maxCommonFactor;
            arr[1] = numerator / maxCommonFactor;
            System.out.println("result:" + denominator / maxCommonFactor + "/" + numerator / maxCommonFactor);
        }
    }
}

