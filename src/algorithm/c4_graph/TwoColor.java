package algorithm.c4_graph;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;

import java.util.Arrays;
import java.util.Scanner;

/**
 * 二分图, 双色问题
 *
 * @author CKH
 * @date 2018/3/19 20:27
 */
public class TwoColor {
    private boolean[] marked;
    private boolean[] color;
    private boolean isTwocolorable = true;

    public TwoColor(Graph G) {
        marked = new boolean[G.V()];
        color = new boolean[G.V()];

        for (int s = 0; s < G.V(); s++) {
            if (!marked[s]) {
                dfs(G, s);
            }

        }
    }

    private void dfs(Graph G, int v) {
        marked[v] = true;
        for (int w : G.adj(v)) {
            if (!marked[w]) {
                color[w] = !color[v];
                dfs(G, w);
            } else if (color[w] == color[v]) {
                isTwocolorable = false;
            }
        }
    }

    public boolean isBipatite() {
        return isTwocolorable;
    }

    public boolean[] getColor() {
        return color;
    }

    public void showBipatite() {
        if (!isTwocolorable) {
            System.out.println("not two color");
            return;
        }

        for (int i = 0; i < color.length; i++) {
            if (color[i]) {
                System.out.print(i + " ");
            }
        }
        System.out.println();
        for (int i = 0; i < color.length; i++) {
            if (!color[i]) {
                System.out.print(i + " ");
            }
        }
    }

    public static void main(String[] args) {
        In in = new In(args[0]);
        //In in = new In("E:\\Algorithm\\algs4-data\\tinyCG.txt");
        //In in = new In(new Scanner(System.in));
        Graph G = new Graph(in);
        TwoColor twoColor = new TwoColor(G);

        twoColor.showBipatite();
    }
}
