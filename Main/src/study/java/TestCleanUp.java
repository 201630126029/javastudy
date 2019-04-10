package study.java;


class TestCleanUp {
    public static void main(String[] args){
        PolyLine x=new PolyLine(47);
        try{

        }
        finally {
            x.cleanup();
        }
    }
}

class Shape{
    Shape(int i){
        System.out.println("shape constructor.");
    }
    void cleanup(){
        System.out.println("shape cleanup.");
    }
}

class Line extends Shape{
    private int start, end;
    Line(int start, int end){
        super(start);
        this.start=start;
        this.end=end;
        System.out.println("Drawing a Line:"+start+","+end);
    }

    void cleanup(){
        System.out.println("Enrasing a Line:"+start+","+end);
        super.cleanup();
    }
}

class PolyLine extends Shape{
    private Line[] lines = new Line[10];
    PolyLine(int i){
        super(i+1);
        for(i=0; i<10; i++){
            lines[i]=new Line(i, i*i);
        }
        System.out.println("PolyLine constructor.");
    }
    void cleanup(){
        System.out.println("PolyLine cleanup.");
        for(int i=0; i<10; i++){
            lines[i].cleanup();
        }
        super.cleanup();
    }
}
