public class toh {
 //in this problem we are solving towers of Hanoi
 //where we are printing the number of steps required to be made so that all the disks move from source to destination

public static void towersOfHannoi(int a,char src,char des,char aux){
    if(a==0)
        return;
    towersOfHannoi(a-1,src,aux,des);
    System.out.println("move disk "+ a+" from "+ src+" to "+des);
    towersOfHannoi(a-1,aux,des,src);
}
    public static void main(String[] args) {

        towersOfHannoi(2,'a','c','b');
    }
}
