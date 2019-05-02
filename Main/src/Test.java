

public class Test {
    public static void main(String args[]) throws Exception {
        int num1=1, num2=1,num3;
        int n=10;
        for (int i = 3; i <= n; i++) {
            num3=num1+num2;
            num1=num2;
            num2=num3;
            System.out.println(num3*num3-num1*num1-num1*num3);
        }
    }
}

class Set<E>{

}