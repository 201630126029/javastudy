package test;

public class Main {
    public static void main(String args[]){  //String是直接生成另一个String，这点很重要
        StringBuffer sb = new StringBuffer("1");
        test(sb);
        System.out.println(sb.toString());
    }

    public static void test(StringBuffer str){
        str.append("2");
    }
}
