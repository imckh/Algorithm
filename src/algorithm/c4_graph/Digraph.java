package algorithm.c4_graph;

import edu.princeton.cs.algs4.Bag;
import edu.princeton.cs.algs4.In;

/**
 * 有向图的表示
 *
 * @author CKH
 * @date 2018/3/21 8:55
 */
public class Digraph {
    private final int V;        // 顶点个数
    private int E;              // 有向边的条数
    private Bag<Integer>[] adj; // 邻接矩阵


    public Digraph(int V) {
        this.V = V;
        this.E = 0;
        adj = (Bag<Integer>[]) new Bag[V];
        for (int i = 0; i < V; i++) {
            adj[i] = new Bag<Integer>();
        }
    }

    public Digraph(In in) {
        this(in.readInt());             // 读取V并将其初始化
        int E = in.readInt();           // 读取E
        for (int i = 0; i < E; i++) {
            // 添加一条边
            int v = in.readInt();       // 读取一个顶点
            int w = in.readInt();       // 读取另一个顶点
            addEdge(v, w);              // 添加一条连接他们的边
        }
    }

    public int V() {
        return this.V;
    }

    public int E() {
        return this.E;
    }

    public void addEdge(int v, int w) {
        adj[v].add(w);
        E++;
    }

    public Iterable<Integer> adj(int v) {
        return adj[v];
    }

    @Override
    public String toString() {
        StringBuilder s = new StringBuilder();
        s.append(V).append(" vertices, ").append(E).append(" edges ").append("\n");
        for (int v = 0; v < V; v++) {
            s.append(v).append(": ");
            for (int w : adj[v]) {
                s.append(w).append(" ");
            }
            s.append("\n");
        }
        return s.toString();
    }

    // 反转方向
    public Digraph reverse() {
        Digraph R = new Digraph(V);
        for (int v = 0; v < V; v++) {
            for (int w : adj(v)) {
                R.addEdge(w, v);
            }
        }

        return R;
    }

    public static void main(String[] args) {
        Digraph G = new Digraph(new In(args[0]));

        System.out.println(G);
        System.out.println();
        System.out.println(G.reverse());
    }
}
