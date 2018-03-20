package algorithm.c4_graph;

import edu.princeton.cs.algs4.Bag;
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;

/**
 * 表示无向图的数据类型
 *
 * @author CKH
 * @date 2018/3/18 17:16
 */
public class Graph {
    private final int V;    // 顶点数目
    private int E;          // 边的数目
    private Bag<Integer>[] adj; // 邻接表

    public Graph(int V) {
        this.V = V;
        this.E = 0;
        adj = (Bag<Integer>[]) new Bag[V];  // 创建邻接表
        for (int i = 0; i < V; i++) {       // 将所有链表初始化为空
            adj[i] = new Bag<Integer>();
        }
    }

    public Graph(In in) {
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
        return V;
    }

    public int E() {
        return E;
    }

    public void addEdge(int v, int w) {
        adj[v].add(w);  // 添加w到v的链表中
        adj[w].add(v);  // 添加v到w的链表中
        E++;
    }

    public Iterable<Integer> adj(int v) {
        return adj[v];
    }

    public int degree(int v) {
        return adj[v].size();
    }

    @Override
    public String toString() {
        StringBuilder s = new StringBuilder();
        s.append(V + " vertices, " + E + " edges " + "\n");
        for (int v = 0; v < V; v++) {
            s.append(v + ": ");
            for (int w : adj[v]) {
                s.append(w + " ");
            }
            s.append("\n");
        }
        return s.toString();
    }

    // java algorithm.c4_graph.SymbolGraph algs4-data\tinyG.txt
    public static void main(String[] args) {
        In in = new In(args[0]);
        Graph G = new Graph(in);
        StdOut.println(G);
    }
}
