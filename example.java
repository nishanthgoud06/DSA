import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class example {
    public static void main(String[] args) {
        //1.extract numbers from text
        String text="one1two2THREE3Four4";
        String dilimitter="\\d";
        Pattern pattern=Pattern.compile(dilimitter,Pattern.CASE_INSENSITIVE);
        String[] result=pattern.split(text);
        for(String s:result){
            System.out.println(s);
        }
    }
}
