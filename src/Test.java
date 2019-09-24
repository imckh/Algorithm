
import java.io.ByteArrayOutputStream;
import java.io.OutputStreamWriter;
import java.nio.charset.Charset;
import java.util.PriorityQueue;
import java.util.LinkedList;
import java.util.Map;
import java.util.*;
 
class Test {
    public static void main(String[] args) {
        Test s = new Test();
        String command = "URR";
        
        int[][] obstacles = {{4, 2},{4, 3},{4, 4},{5, 2},{6, 2}, {2, 2}}; //[[4, 2]], 
        // int[][] obstacles = {}; //[[4, 2]], 
        int x = 3, y = 2;


        System.out.println(s.robot(command, obstacles, x, y));
    }

    public boolean robot(String command, int[][] obstacles, int x, int y) {
        Map<Integer, Set<Integer>> pair = new HashMap<>();
            
        for (int i = 0; i < obstacles.length; i++) {
            if (pair.containsKey(obstacles[i][0])) {
                pair.get(obstacles[i][0]).add(obstacles[i][1]);
            } else {
                pair.put(obstacles[i][0], new HashSet<>());
                pair.get(obstacles[i][0]).add(obstacles[i][1]);
            }
        }

        System.out.println(pair);

        int cx = 0, cy = 0;
        while (true) {
            int i = 0;
            for (;i < command.length(); i++) {
                char move = command.charAt(i);
                if ('U' == move) {
                    ++cy;
                    if (pair.containsKey(cx) && pair.get(cx).contains(cy)) return false;
                } else if ('R' == move) {
                    ++cx;
                    if (pair.containsKey(cx) && pair.get(cx).contains(cy)) return false;
                }
                
                System.out.println("[" + cx + "," + cy + "]");
                if (cx == x && cy == y) {
                    return true;
                }

                if (cx > x || cy > y) { // 到达不了终点
                    return false;
                }
            }
        }
    }

}