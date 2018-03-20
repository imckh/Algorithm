package algorithm.c4_graph;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.ST;
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

/**
 * 符号图
 *
 * @author CKH
 * @date 2018/3/20 9:37
 */
public class SymbolGraph {
    // ST内部是使用了Java的TreeMap来实现的
    private ST<String, Integer> st;     // 符号名 -> 索引
    private String[] keys;              // 索引 -> 符号名(作为反向索引)
    private Graph G;                    // 图

    /**
     * 构造两遍, 因为构造Graph对象需要定点总数V
     * 允许使用字符串代替数组索引来表示图中的顶点
     * 他维护了
     *  实例变量st(符号表用来映射顶点名和索引),
     *  keys(数组用来映射索引和顶点名)
     *  G()使用索引表表示顶点的图
     *
     * 定义的每一行都包含一个顶点和他的相邻顶点列表, 用分隔符sp分开
     * @param steam
     * @param sp
     */
    public SymbolGraph(String steam, String sp) {
        st = new ST<>();

        In in = new In(steam);                      // 第一遍
        while (in.hasNextLine()) {                  // 构造索引
            String[] a = in.readLine().split(sp);   // 读取字符串

            for (int i = 0; i <a.length; i++) {     // 为每个不同的字符串关联一个索引
                if (!st.contains(a[i])) {
                    st.put(a[i], st.size());
                }
            }
        }

        keys = new String[st.size()];               // 用获得的顶点名的反向索引是一个数组

        for (String name : st.keys()) {
            keys[st.get(name)] = name;
        }

        G = new Graph(st.size());
        in = new In(steam); // 第二遍
        while (in.hasNextLine()) { //构造图
            String[] a = in.readLine().split(sp); // 每一行的第一个顶点和该行的其他顶点相连接
            int v = st.get(a[0]);
            for (int i = 1; i < a.length; i++) {
                G.addEdge(v, st.get(a[i]));
            }
        }
    }

    public boolean contains(String s) {
        return st.contains(s);
    }

    public int index(String s) {
        return st.get(s);
    }

    public String name(int v) {
        return keys[v];
    }

    public Graph G() {
        return G;
    }

    // java algorithm.c4_graph.SymbolGraph algs4-data\routes.txt " "
    public static void main(String[] args) {
        String filename  = args[0];
        String delimiter = args[1];
        SymbolGraph sg = new SymbolGraph(filename, delimiter);
        Graph graph = sg.G();
        while (StdIn.hasNextLine()) {
            String source = StdIn.readLine();
            if (sg.contains(source)) {
                int s = sg.index(source);
                for (int v : graph.adj(s)) {
                    StdOut.println("   " + sg.name(v));
                }
            }
            else {
                StdOut.println("input not contain '" + source + "'");
            }
        }

        /*
        JFK
           ORD
           ATL
           MCO
        LAX
           LAS
           PHX
        LAS
           PHX
           LAX
           DEN

         */
    }
}
