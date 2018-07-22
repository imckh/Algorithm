package algorithm.c4_graph.mintree;

import edu.princeton.cs.algs4.Bag;
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;

/**
 * 加权无向图的数据类型
 *
 * @author CKH
 * @date 2018/3/23 16:42
 */
@SuppressWarnings("unchecked")
public class EdgeWeightedGraph {
    private final int V;    // 顶点总数
    private int E;          // 边的总数
    private Bag<Edge>[] adj;// 邻接表

    // 创建一幅含有V个顶点的空图
    public EdgeWeightedGraph(int V) {
        this.V = V;
        this.E = 0;
        this.adj = (Bag<Edge>[]) new Bag[V];
        for (int v = 0; v < V; v++) {
            adj[v] = new Bag<>();
        }
    }

    // 从输入流中读取图
    public EdgeWeightedGraph(In in) {
        this(in.readInt());
        int E = in.readInt();
        if (E < 0) throw new IllegalArgumentException("Number of edges must be nonnegative");
        for (int i = 0; i < E; i++) {
            int v = in.readInt();
            int w = in.readInt();
            double weight = in.readDouble();
            Edge e = new Edge(v, w, weight);
            addEdge(e);
        }
    }

    // 向图中添加一条边e
    public void addEdge(Edge e) {
        int v = e.either(), w = e.other(v);
        adj[v].add(e);
        adj[w].add(e);
        E++;
    }

    // 和v相关联的所有边
    public Iterable<Edge> adj(int v) {
        return adj[v];
    }

    // 返回加权无向图中的所有边
    public Iterable<Edge> edges() {
        Bag<Edge> b = new Bag<>();

        for (int v = 0; v < V; v++) {
            for (Edge e : adj[v]) {
                if (e.other(v) > v) {
                    b.add(e);
                }
            }
        }

        return b;
    }

    public int V() {
        return V;
    }

    public int E() {
        return E;
    }

    public String toString() {
        StringBuilder s = new StringBuilder();
        s.append(V).append(" ").append(E).append("\n");
        for (int v = 0; v < V; v++) {
            s.append(v).append(": ");
            for (Edge e : adj[v]) {
                s.append(e).append("  ");
            }
            s.append("\n");
        }
        return s.toString();
    }

    // java E:\Algorithm\algs4-data\tinyDG.txt
    public static void main(String[] args) {
        In in = new In(args[0]);
        EdgeWeightedGraph G = new EdgeWeightedGraph(in);
        StdOut.println(G);
        /*
        8 16
        0: 6-0 0.58  0-2 0.26  0-4 0.38  0-7 0.16
        1: 1-3 0.29  1-2 0.36  1-7 0.19  1-5 0.32
        2: 6-2 0.40  2-7 0.34  1-2 0.36  0-2 0.26  2-3 0.17
        3: 3-6 0.52  1-3 0.29  2-3 0.17
        4: 6-4 0.93  0-4 0.38  4-7 0.37  4-5 0.35
        5: 1-5 0.32  5-7 0.28  4-5 0.35
        6: 6-4 0.93  6-0 0.58  3-6 0.52  6-2 0.40
        7: 2-7 0.34  1-7 0.19  0-7 0.16  5-7 0.28  4-7 0.37
         */
    }

}
