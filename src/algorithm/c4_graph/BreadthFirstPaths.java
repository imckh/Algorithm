package algorithm.c4_graph;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.Queue;
import edu.princeton.cs.algs4.Stack;
import edu.princeton.cs.algs4.StdOut;

/**
 * 使用广度优先搜索查找图的路径
 *
 * @author CKH
 * @date 2018/3/19 10:25
 */
public class BreadthFirstPaths {
    private boolean[] marked;   // 到达该点的最短路径已知吗
    private int[] edgeTo;       // 到达该点已知路径上的最后一个顶点
    private final int s;        // 起点

    public BreadthFirstPaths(Graph G, int s) {
        this.marked = new boolean[G.V()];
        this.edgeTo = new int[G.V()];
        this.s = s;
        bfs(G, s);
    }

    /**
     * 使用队列来保存所有已经被标记过但其邻接表还未被检查过的顶点.
     * 先将起点加入队列, 然后重复以下步骤直到队列为空
     * 1. 取队列中的下一个顶点v并标记它
     * 2. 将与v相邻的所有的为被标记过的顶点加入队列
     *
     * @param G
     * @param s
     */
    private void bfs(Graph G, int s) {
        Queue<Integer> queue = new Queue<>();
        marked[s] = true;   // 标记起点
        queue.enqueue(s);   // 将它加入队列
        while (!queue.isEmpty()) {
            int v = queue.dequeue();    // 从队列中删除下一顶点
            for (int w : G.adj(v)) {
                if (!marked[w]) {       // 对于每个为被标记过的顶点
                    edgeTo[w] = v;      // 保存最短路径的最后一条边
                    marked[w] = true;   // 标记它, 因为最短路径已知
                    queue.enqueue(w);   // 并将他添加到队列中
                }
            }
        }
    }

    public boolean hasPathTo(int v) {
        return marked[v];
    }

    public Iterable<Integer> pathTo(int v) {
        if (!hasPathTo(v)) {
            return null;
        }

        Stack<Integer> path = new Stack<>();

        for (int x = v; x != s; x = edgeTo[x]) {
            path.push(x);
        }

        path.push(s);

        return path;
    }

    /**
     * java algorithm.c4_graph.BreadthFirstPaths E:\Algorithm\algs4-data\tinyG.txt 0
     * @param args
     */
    public static void main(String[] args) {
        In in = new In(args[0]);
        Graph G = new Graph(in);
        int s = Integer.parseInt(args[1]);
        BreadthFirstPaths dfs = new BreadthFirstPaths(G, s);

        for (int v = 0; v < G.V(); v++) {
            if (dfs.hasPathTo(v)) {
                StdOut.printf("%d to %d:  ", s, v);
                for (int x : dfs.pathTo(v)) {
                    if (x == s) StdOut.print(x);
                    else        StdOut.print("-" + x);
                }
                StdOut.println();
            }

            else {
                StdOut.printf("%d to %d:  not connected\n", s, v);
            }

        }
    }
}