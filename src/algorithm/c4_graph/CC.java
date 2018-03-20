package algorithm.c4_graph;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.Queue;
import edu.princeton.cs.algs4.StdOut;

/**
 * 使用深度优先搜索找出图中的所有连通分量
 *
 * @author CKH
 * @date 2018/3/19 16:05
 */
public class CC {
    private boolean[] marked;
    private int[] id;   // 连通分量id, 如果v属于第i个连通分量, 则id[v]的值为i
    private int count;  // 连通分量的个数

    public CC(Graph G) {
        marked = new boolean[G.V()];
        id = new int[G.V()];

        /*
        找出一个为被标记的顶点并调用递归函数dfs()来标记并区分出所有和它连通的顶点
        如此重复, 直到所有顶点都被标出
         */
        for (int s = 0; s < G.V(); s++) {
            if (!marked[s]) {
                dfs(G, s); // 从该顶点dfs完之后
                count++;    // count++, 下一个连通分量
            }
        }
    }

    private void dfs(Graph G, int v) {
        marked[v] = true;
        id[v] = count;  // 将该顶点标记为该连通分量
        for (int w : G.adj(v)) {
            if (!marked[w]) {
                dfs(G, w);
            }
        }
    }

    public boolean connected(int v, int w) {
        return id[v] == id[w];
    }

    public int id(int v) {
        return id[v];
    }

    public int count() {
        return count;
    }

    public static void main(String[] args) {
        In in = new In(args[0]);
        Graph G = new Graph(in);
        CC cc = new CC(G);

        // number of connected components
        int m = cc.count();
        StdOut.println(m + " components");

        // compute list of vertices in each connected component
        Queue<Integer>[] components = (Queue<Integer>[]) new Queue[m];
        for (int i = 0; i < m; i++) {
            components[i] = new Queue<Integer>();
        }
        for (int v = 0; v < G.V(); v++) {
            components[cc.id(v)].enqueue(v);
        }

        // print results
        for (int i = 0; i < m; i++) {
            for (int v : components[i]) {
                StdOut.print(v + " ");
            }
            StdOut.println();
        }
    }
}
