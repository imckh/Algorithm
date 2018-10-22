
import java.io.ByteArrayOutputStream;
import java.io.OutputStreamWriter;
import java.nio.charset.Charset;
import java.util.PriorityQueue;
import java.util.Map;
import java.util.Set;
 
class Test {
	public static void main(String[] args) {
        // System.out.println(0 % 6);
        // System.out.println("Default Charset=" + Charset.defaultCharset());
        // System.out.println("file.encoding=" + System.getProperty("file.encoding"));
        // System.out.println("Default Charset=" + Charset.defaultCharset());
        // System.out.println("Default Charset in Use=" + getDefaultCharSet());
        printMap(System.getenv());
        //testPriorityQueue();
    }
 
    private static String getDefaultCharSet() {
        OutputStreamWriter writer = new OutputStreamWriter(new ByteArrayOutputStream());
        String enc = writer.getEncoding();
        return enc;
    }
 
    public static void testPriorityQueue() {
        PriorityQueue<Integer> q = new PriorityQueue<Integer>();
        q.add(1);
        q.add(4);
        q.add(2);
        q.add(0);
        q.add(7);
        q.add(3);

        for (int var : q) {
            System.out.println(var);
        }

        System.out.println("Removing");

        while(!q.isEmpty()) {
            System.out.println(q.remove());
        }
    }

    public static void printMap(Map map) {
        Set<Map.Entry<String, String>> set = map.entrySet();
        set.add(null);
        for (Map.Entry<java.lang.String,java.lang.String> entry : set) {
            System.out.println("Key = " + entry.getKey() + ", Value = " + entry.getValue());
        }
    }
    // %JAVA_HOME%\bin;%JAVA_HOME%\jre\bin;
}