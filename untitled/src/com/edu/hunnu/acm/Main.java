package com.edu.hunnu.acm;


import java.awt.*;
import java.lang.reflect.Field;
import java.lang.reflect.Member;
import java.lang.reflect.Method;

public class Main{
   public static void main(String args[]){
        Object obj = new Color(1,1,1);
        Class cls = obj.getClass();
        System.out.println("类名"+cls.getName());
        Field[] fields = cls.getFields();
        for(Field field : fields){
            System.out.println("域:"+field.getName()+":"+field);
        }
       Method[] methods = cls.getMethods();
        for(Method method:methods){
            System.out.println("方法："+ method.getName()+":"+method);
        }
   }
}

