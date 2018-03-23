package algorithm.c4_graph;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.Queue;
import edu.princeton.cs.algs4.StdOut;

/**
 * 有向图的强连通分量
 *   仅仅是CC算法添加了几行代码
 *   1. 在给定有向图G中, 使用DepthFirstOrder来计算他的反向图Gr的逆后续排列
 *   2. 在G中进行标准的深度优先搜索, 但是要按照刚才计算得到的顺序(逆后续)而非标准顺序来访问所有未被标记的点
 *   3. 在构造函数中, 所有在同一个递归dfs()中被访问到的顶点都在同一个强连通分量中, 将他们按照和CC相同的方式识别出来
 *
 * 核心的思想或许可以总结成两点：
 *   1. 圈反过来也是圈
 *   2. 把圈反一半过来会得到两条路径
 * 可以认为第一次搜索之后按倒过来的顺序重新搜，就是为了避开上次走过的路径。如果这次能找到另一条路径，和上次那条拼起来就是个圈了
 * https://www.zhihu.com/question/58926821
 *
 * @author CKH
 * @date 2018/3/23 9:52
 */
public class KosarajuSCC {
    private boolean[] marked;
    private int[] id;       // 强连通分量的标识符
    private int count;      // 强连通分量的数量

    public KosarajuSCC(Digraph G) {
        marked = new boolean[G.V()];
        id = new int[G.V()];

        // 在反向图中进行深度优先搜索来将顶点排序,(搜索顺序的逆后序)
        DepthFirstOrder order = new DepthFirstOrder(G.reverse());
        // 在有向图中用这个顺序在进行一次深度优先搜索
        for (int s : order.reversePost()) {
            if (!marked[s]) {
                dfs(G, s);
                count++;
            }
        }
    }

    private void dfs(Digraph G, int v) {
        marked[v] = true;
        id[v] = count;
        for (int w : G.adj(v)) {
            if (!marked[w]) {
                dfs(G, w);
            }
        }
    }

    public boolean stronglyConnected(int v, int w) {
        return id[v] == id[w];
    }

    public int id(int v) {
        return id[v];
    }

    public int count() {
        return count;
    }

    // java E:\Algorithm\algs4-data\tinyDG.txt
    public static void main(String[] args) {
        In in = new In(args[0]);
        Digraph G = new Digraph(in);
        KosarajuSCC scc = new KosarajuSCC(G);

        // number of connected components
        int m = scc.count();
        StdOut.println(m + " strong components");

        // compute list of vertices in each strong component
        Queue<Integer>[] components = (Queue<Integer>[]) new Queue[m];
        for (int i = 0; i < m; i++) {
            components[i] = new Queue<Integer>();
        }
        for (int v = 0; v < G.V(); v++) {
            components[scc.id(v)].enqueue(v);
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
