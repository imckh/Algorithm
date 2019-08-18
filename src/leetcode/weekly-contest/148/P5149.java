import java.util.*;

public class P5149 {
    public static void main(String[] args) {
        SnapshotArray snapshotArr = new SnapshotArray(3); // 初始化一个长度为 3 的快照数组
        System.out.println(snapshotArr.snap());  // 获取快照，返回 snap_id = 0
        snapshotArr.set(0,5);  // 令 array[0] = 5
        snapshotArr.set(0,6);
        System.out.println(snapshotArr.snap());
        System.out.println(snapshotArr.get(0,0));  // 获取 snap_id = 0 的快照中 array[0] 的值，返回 5
        System.out.println(snapshotArr.get(0,1));  // 获取 snap_id = 1 的快照中 array[0] 的值，返回 5
    }

    static class SnapshotArray {
        private int snap;
        private List<int[]> steps = new LinkedList<>();
        private int length;
        public SnapshotArray(int length) {
            this.length = length;
            snap = 0;
            steps.add(new int[length]);
        }
        
        public void set(int index, int val) {
            if (index > this.length || index < 0) return;
            //array[index] = val;
            steps.get(snap)[index] = val;
        }
        
        public int snap() {
            steps.add(Arrays.copyOf(steps.get(snap), length));
            return snap++;
        }
        
        public int get(int index, int snap_id) {
            System.out.println(Arrays.toString(steps.get(snap_id)));
            return steps.get(snap_id)[index];
        }
    }
}