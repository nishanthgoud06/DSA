import java.util.regex.Pattern;

public class ipvalidator {
    public static boolean checking(String ipAddress){
        if(ipAddress==null||ipAddress.isEmpty())
            return false;
        String zeroTo255
                = "(\\d{1,2}|(0|1)\\"
                + "d{2}|2[0-4]\\d|25[0-5])";

        // Regex for a digit from 0 to 255 and
        // followed by a dot, repeat 4 times.
        // this is the regex to validate an IP address.
        String regex
                = zeroTo255 + "\\."
                + zeroTo255 + "\\."
                + zeroTo255 + "\\."
                + zeroTo255;
        Pattern pattern= Pattern.compile(regex);
        return pattern.matcher(ipAddress).matches();
    }
    public static void main(String[] args) {
        System.out.println(checking("1.1.1.1"));
    }
}
