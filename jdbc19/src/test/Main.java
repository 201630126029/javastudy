package test;

import java.lang.reflect.Type;

import ok.Customer;
import org.junit.Test;

public class Main extends Customer implements Type {
    @Test
    public void main(){
        Type type = Main.class.getGenericSuperclass();
        System.out.println(type);
    }
}
