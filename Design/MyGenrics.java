package Design;

public class MyGenrics <T1 ,T2>{
    T1 x;
    T2 y;
    public MyGenrics(T1 x,T2 y){
        this.x=x;
        this.y=y;
    }
    public T1 getMultiple(){
        return x;
    }
}
