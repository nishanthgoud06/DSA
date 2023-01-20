import java.util.HashSet;

public class le75 {
    //happy number
    public static boolean happy(int num){
        if(num<=0)
            return false;
        int sumnum,remain;
        HashSet<Integer> hs=new HashSet<>();
        while(hs.add(num)){
            sumnum=0;
            while(num>0){
                remain=num%10;
                sumnum+=remain*remain;
                num=num/10;
            }
            if(sumnum==1)
                return true;
            else
                num=sumnum;
        }
        return false;
    }

    public static void main(String[] args) {
        System.out.println(happy(19));
    }
}
