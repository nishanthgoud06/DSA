package calculator;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class mainApp {
    public static void main(String[] args) {
       final String input=readInput.input();
        Queue<String> operations;
        Queue<String> numbers;
        String[] numberArr=input.split("[-+*/]");
        String[] operation=input.split("[0-9]+");
        operations=new LinkedList<>(Arrays.asList(operation));
        numbers= new LinkedList<>(Arrays.asList(numberArr));
        Double first=Double.parseDouble(numbers.poll());
        while(!numbers.isEmpty()){
            String opr=operations.poll();
            operate op;
            switch(opr){
                case "+":
                    op=new add();
                    break;
                case "-":
                    op=new substract();
                    break;
                case "/":
                    op=new divide();
                    break;
                case "*":
                    op=new multiply();
                    break;
                default:
                    continue;
            }
            Double second=Double.parseDouble(numbers.poll());
            first=op.getResult(first,second);
        }
        System.out.println(first);
    }
}
