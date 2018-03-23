package algorithm.c4_graph;

/**
 * 拓扑排序
 *   给定一副有向图, 将所有的顶点排序,
 *   使得所有的有向边均从排在前边的元素指向排在后边的元素(或者说明无法做到这一点)
 *
 * @author CKH
 * @date 2018/3/21 20:30
 */
public class Topological {
    private Iterable<Integer> order;    // 顶点的拓扑顺序

    public Topological(Digraph G) {
        DirectedCycle cycleFinder = new DirectedCycle(G);

        if (!cycleFinder.hasCycle()) {
            DepthFirstOrder dfs = new DepthFirstOrder(G);
            order = dfs.reversePost();
        }
    }

    public Iterable<Integer> order() {
        return this.order;
    }

    /**
     * 有向无环图为DAG ( Directed Acyclic Graph)
     * @return
     */
    public boolean isDAG() {
        return this.order != null;
    }

    public static void main(String[] args) {
        String filename = args[0];
        String separator = args[1];
        SymbolGraphDirected sg = new SymbolGraphDirected(filename, separator);

        Topological top = new Topological(sg.G());

        for (int v : top.order()) {
            System.out.println(sg.name(v));
        }
    }
}
