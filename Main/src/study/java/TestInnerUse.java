package study.java;

import static java.lang.System.*;
public class TestInnerUse {
    public static void main(String[] args){

    }
    @Auther(value = 123, name = "zhang",telephones = {"1234", "2234"})
    public void haha(){

    }
}

@interface Auther{
    int value();
    String name();
    String[] telephones();
}
