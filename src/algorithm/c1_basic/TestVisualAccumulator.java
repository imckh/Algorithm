package algorithm.c1_basic;

import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

/**
 * @author CKH
 * @date 2018/3/11 9:32
 */
public class TestVisualAccumulator {
    public static void main(String[] args) {
        int T = 1000;
        VisualAccumulator a = new VisualAccumulator(T, 1.0);

        for (int i = 0; i < T; i++) {
            a.addDataValue(StdRandom.random());
        }
        StdOut.println(a);
    }
}
