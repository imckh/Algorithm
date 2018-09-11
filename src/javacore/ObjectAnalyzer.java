package javacore;

import java.lang.reflect.AccessibleObject;
import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * 使用反射编写可供任意类使用的toString方法
 * @author CKH
 * @date 2018/9/11 21:30
 */
public class ObjectAnalyzer {
    /**
     * 循环引用将有可能导致无限递归,
     * 因此记录已经被访问过的对象
     */
    private ArrayList<Object> visited = new ArrayList<>();

    /**
     * 将一个对象转为列出所有属性的字符串
     * @param obj 对象
     * @return 包含对象的属性名和值
     */
    public String toString(Object obj) {
        if (obj == null) {
            return "null";
        }

        if (visited.contains(obj)) {
            return "...";
        }

        visited.add(obj);

        Class cl = obj.getClass();
        if (cl == String.class) {
            return (String) obj;
        }

        if (cl.isArray()) {
            StringBuilder r = new StringBuilder(cl.getComponentType() + "[]{");
            for (int i = 0; i < Array.getLength(obj); i++) {
                if (i > 0) {
                    r.append(",");
                }
                Object val = Array.get(obj, i);
                // 原始类型 int char ....
                // java这里并不能判断自动拆箱装箱的类是不是原始类型 update: 前边的注释是错的, 我想错了 Java可以的判断
                if (cl.getComponentType().isPrimitive()) {
//                if (cl.getComponentType().isPrimitive() || isWrapperType(cl.getComponentType())) {
                    r.append(val);
                } else {
                    r.append(toString(val));
                }
            }
            return r.append("}").toString();
        }

        StringBuilder r = new StringBuilder(cl.getName());
        // 属性,属性值, 与其父类
        do {
            r.append("[");
            Field[] fields = cl.getDeclaredFields();
            // 设置为可访问的
            AccessibleObject.setAccessible(fields, true);
            // 得到变量名和值
            for (Field f : fields) {
                // 去掉static变量
                if (!Modifier.isStatic(f.getModifiers())) {
                    if (!r.toString().endsWith("[")) {
                        r.append(",");
                    }
                    r.append(f.getName()).append("=");
                    try {
                        Class t = f.getType();
                        Object val = f.get(obj);
                        if (t.isPrimitive()) {
//                        if (t.isPrimitive() || isWrapperType(t)) {
                            r.append(val);
                        } else {
                            r.append(toString(val));
                        }
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    }
                }
            }
            r.append("]");
            cl = cl.getSuperclass();
        } while (cl != null);
        return r.toString();
    }

    private static final Set<Class> WRAPPER_TYPES = new HashSet(Arrays.asList(
            Boolean.class, Character.class, Byte.class, Short.class, Integer.class, Long.class, Float.class, Double.class, Void.class));

    public static boolean isWrapperType(Class clazz) {
        return WRAPPER_TYPES.contains(clazz);
    }

    public static void main(String[] args) {
        class F {
            int f = 1;
        }
        class S extends F {
            int s = 2;
            Double d = 2.3;
        }
        class Z {
            String z = "zzzz";
            S s = new S();
        }
        ArrayList<Integer> sq = new ArrayList<>();
        for (int i = 1; i < 5; i++) {
            sq.add(i * i);
        }
        System.out.println(new ObjectAnalyzer().toString(sq));
        System.out.println(new ObjectAnalyzer().toString(new Z()));
        /*
        java.util.ArrayList[elementData=class java.lang.Object[]{java.lang.Integer[value=1][][],java.lang.Integer[value=4][][],java.lang.Integer[value=9][][],java.lang.Integer[value=16][][],null,null,null,null,null,null},size=4][modCount=4][][]
        javacore.ObjectAnalyzer$1Z[z=zzzz,s=javacore.ObjectAnalyzer$1S[s=2,d=java.lang.Double[value=2.3][][]][f=1][]][]
         */
    }
}
