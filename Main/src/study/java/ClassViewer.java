package study.java;


import static java.lang.System.*;
public class ClassViewer extends MyDate{
    public static void main(String[] args){
        MyDate m1=new MyDate(24,3,2001);
        MyDate m2=new ClassViewer(24,3,2001);
        out.println(m1);
        out.println(m2);
    }
    public ClassViewer(int i, int j, int k){
        super(i, j, k);
    }

    public String toString(){
        return year+"-"+month+"-"+day;
    }
}

class MyDate{
    int day, month, year;
    public MyDate(int i, int j, int k){
        day =i;
        month = j;
        year=k;
    }
}