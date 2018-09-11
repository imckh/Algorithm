/**
 * @author CKH
 * @date 2018/2/22 21:26
 */
public class Main extends Test{
    class Inner {}

    //public static void foo() { new Inner(); }

    public void bar() { new Inner(); }

    public static void main(String[] args) {
       /* Main m = new Main();
        System.out.println("m.getS() = " + m.getS());
        float f = (float) 3.4;
        System.out.println(f);

        String s1 = new StringBuilder("go")
                .append("od").toString();
        System.out.println(s1.intern() == s1);
        String s2 = new StringBuilder("ja")
                .append("va").toString();
        System.out.println(s2.intern() == s2);

        System.out.println(2 << 3);
        System.out.println(2 >> 1);*/

        String s1 = "Programming";
        String s2 = new String("Programming");
        String s3 = "Program";
        String s4 = "ming";
        String s5 = "Program" + "ming";
        String s6 = s3 + s4;

        System.out.println(s1 == s2);
        System.out.println(s1 == s5);
        System.out.println(s1 == s6);
        System.out.println(s1 == s6.intern());
        System.out.println(s2 == s2.intern());

        //new Inner();
        //Main.Inner i = Main.new Inner();
        new Main().bar();

        int i = 0;
        int j = 0;
        int k = 0;
        //int j = i++ + ++i;
        System.out.println(j++);
        System.out.println(++k);
        System.out.println(i++ + ++i);
    }



    class Test{
        private String s = "hello";

        public String getS() {
            return s;
        }

        public void setS(String s) {
            this.s = s;
        }
    }

}

