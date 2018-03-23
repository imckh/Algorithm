package algorithm.c4_graph;

/**
 * 传递闭包(顶点对的可达性)
 *
 * 构造函数所需要的空间和V^2成正比
 * 所需的时间和V(V+E)成正比
 *
 * @author CKH
 * @date 2018/3/23 10:45
 */
public class TransitiveClosure {
    private DirectedDFS[] all;

    public TransitiveClosure(Digraph G) {
        all = new DirectedDFS[G.V()];

        for (int v = 0; v < G.V(); v++) {
            all[v] = new DirectedDFS(G, v);
        }
    }

    boolean reachable(int v, int w) {
        return all[v].marked(w);
    }
}
