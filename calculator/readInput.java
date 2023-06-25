package calculator;

import java.util.Scanner;

public class readInput {
    public static String input(){
        Scanner sc=new Scanner(System.in);
        System.out.println("please enter the combination of number and operation that you want to perform");
        String result=sc.next();
        sc.close();
        return result;
    }
}
