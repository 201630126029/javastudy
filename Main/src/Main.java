import java.lang.ref.ReferenceQueue;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

interface Subject{
    public void request();
}

class RealSubject implements Subject{
    public RealSubject(){}
    public void request(){
        System.out.println("From real subject...");
    }
}

class DynamicSubject implements InvocationHandler{
    private Object sub;
    public DynamicSubject(){}
    public DynamicSubject(Object obj){
        this.sub=obj;
    }
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable{
        System.out.println("before calling "+method);
        method.invoke(sub, args);
        System.out.println("after calling "+method);
        return null;
    }
}

public class Main{
    public static void main(String args[]){
        RealSubject real = new RealSubject();  //创建真实对象
        InvocationHandler ih = new DynamicSubject(real);//用真实对象创建调用处理器
        Class cls = ih.getClass();  //得到调用处理器的类
        //通过调用处理器的类得到类加载器、接口，再加上本身来创建代理类
        Subject subject = (Subject) Proxy.newProxyInstance(cls.getClassLoader(), cls.getInterfaces(),ih);
        subject.request();
    }
}

