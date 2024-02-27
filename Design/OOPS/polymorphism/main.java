package Design.OOPS.polymorphism;

import java.util.Arrays;

public class main {
    public static void main(String[] args) {
        ipl IPL=new ipl("INDIA",10000);
        bbl BBL=new bbl("AUS",900);
        hundred HUN=new hundred("ENGLAND",10);
        cr[] arr={IPL,BBL,HUN};
        for(cr C:arr){
            System.out.println(C.getClass().getName().toUpperCase().split("\\.")[C.getClass().getName().toUpperCase().split("\\.").length-1]+"  "+C.origin()+" "+C.viwership());
        }
    }
}
