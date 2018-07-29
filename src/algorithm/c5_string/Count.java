package algorithm.c5_string;

import edu.princeton.cs.algs4.Alphabet;
import edu.princeton.cs.algs4.StdIn;

/**
 * 从命令行接受一个字符串, 并打印出每个字符串的出现频率
 * @author CKH
 * @date 2018/7/22 16:55
 */
public class Count {
    public static void main(String[] args) {
        String str = "abcde";
        String fromStr = "abcdefacbdeacedacdfeacdasf";
        Alphabet alphabet = new Alphabet(str);
        int R = alphabet.R();
        int[] count = new int[R];

        String s = fromStr;
        int N = s.length();
        for (int i = 0; i < N; i++) {
            if (alphabet.contains(s.charAt(i))) {
                count[alphabet.toIndex(s.charAt(i))]++;
            }
        }

        for (int i = 0; i < R; i++) {
            System.out.println(alphabet.toChar(i) + " " + count[i]);
        }
    }
    /*
    a 6
    b 2
    c 5
    d 5
    e 4
     */
}
