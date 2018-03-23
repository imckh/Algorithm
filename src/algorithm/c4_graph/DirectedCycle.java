package algorithm.c4_graph;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.Stack;
import edu.princeton.cs.algs4.StdOut;

/**
 * 寻找有向环
 * 基于深度优先遍历解决, 因为由系统维护的递归调用的栈正是"当前"正在遍历的有向路径.
 * 一旦我们找到了一条有向边v->w, 且w已经存在于栈中, 就找到了一个环,
 * 因为栈表示的是一条从w到v的有向路径, 而v->w正好补全了这个环.
 * 同时如果没有找到这个边, 那就意味着这个有向图是无环的.
 *
 * @author CKH
 * @date 2018/3/21 17:34
 */
public class DirectedCycle {
    private boolean[] marked;
    private int[] edgeTo;
    private Stack<Integer> cycle;   // 有向环中的所有顶点(若存在)
    private boolean[] onStack;      // 递归调用的栈上的所有顶点

    public DirectedCycle(Digraph G) {
        onStack = new boolean[G.V()];
        marked = new boolean[G.V()];
        edgeTo = new int[G.V()];

        // 遍历每个顶点
        for (int v = 0; v < G.V(); v++) {
            if (!marked[v]) {
                dfs(G, v);
            }
        }
    }

    private void dfs(Digraph G, int v) {
        onStack[v] = true;
        marked[v] = true;
        for (int w : G.adj(v)) {
            if (this.hasCycle()) {// 已经存在环了
                return;
            } else if (!marked[w]) {// 深度优先遍历寻找有环的路径
                edgeTo[w] = v;
                dfs(G, w);
            } else if (onStack[w]) {    // 找到了环
                cycle = new Stack<>();  // 将环的路径入栈
                for (int x = v; x != w; x = edgeTo[x]) {
                    cycle.push(x);
                }
                cycle.push(w);  // 当前顶点
                cycle.push(v);  // 当前顶点的上一个顶点
            }
        }
        onStack[v] = false;
    }

    public boolean hasCycle() {
        return cycle != null;
    }

    public Iterable<Integer> cycle() {
        return cycle;
    }

    // java algorithm.c4_graph.DirectedCycle E:\Algorithm\algs4-data\tinyDG.txt
    public static void main(String[] args) {
        In in = new In(args[0]);
        Digraph G = new Digraph(in);

        DirectedCycle finder = new DirectedCycle(G);
        if (finder.hasCycle()) {
            StdOut.print("Directed cycle: ");
            for (int v : finder.cycle()) {
                StdOut.print(v + " ");
            }
            StdOut.println();
        }

        else {
            StdOut.println("No directed cycle");
        }
        StdOut.println();
    }
}
