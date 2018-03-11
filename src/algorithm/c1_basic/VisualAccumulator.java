package algorithm.c1_basic;

import edu.princeton.cs.algs4.Accumulator;
import edu.princeton.cs.algs4.StdDraw;

/**
 * @author CKH
 * @date 2018/3/11 9:34
 */
public class VisualAccumulator extends Accumulator {
    private double total;
    private int N;

    public VisualAccumulator() {
        super();
    }

    public VisualAccumulator(int trials, double max) {
        StdDraw.setXscale(0, trials);
        StdDraw.setYscale(0, max);
        StdDraw.setPenRadius(.005);
    }


    @Override
    public void addDataValue(double val) {
        N++;
        total += val;

        StdDraw.setPenColor(StdDraw.DARK_GRAY);
        StdDraw.point(N, val);
        StdDraw.setPenColor(StdDraw.RED);
        StdDraw.point(N, total/N);
    }

    @Override
    public double mean() {
        return total / N;
    }

    @Override
    public String toString() {
        return "Mean (" + N + "values): " + String.format("%7.5f", mean());
    }
}
