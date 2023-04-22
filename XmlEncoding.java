import java.io.*;
import java.util.*;

public class XmlEncoding {
    public static String xmlEncode(String filePath) throws FileNotFoundException {
        // Create a hashmap to store the encoding values for each tag
        HashMap<String, String> encodingMap = new HashMap<String, String>();
        encodingMap.put("family", "1");
        encodingMap.put("person", "2");
        encodingMap.put("firstName", "3");
        encodingMap.put("lastName", "4");
        encodingMap.put("state", "5");

        // Read the input file line by line and perform encoding
        StringBuilder result = new StringBuilder();
        try (Scanner sc = new Scanner(new File(filePath))) {
            while (sc.hasNextLine()) {
                String line = sc.nextLine();
                String encodedLine = encodeLine(line, encodingMap);
                result.append(encodedLine).append("\n");
            }
        } catch (FileNotFoundException e) {
            throw new FileNotFoundException("File not found: " + filePath);
        }
        return result.toString();
    }

    private static String encodeLine(String line, HashMap<String, String> encodingMap) {
        StringBuilder result = new StringBuilder();
        int i = 0;
        while (i < line.length()) {
            if (line.charAt(i) == '<') {
                // Found a new tag, find its end and perform encoding
                int j = i + 1;
                while (j < line.length() && line.charAt(j) != '>') {
                    j++;
                }
                String tag = line.substring(i + 1, j);
                String encodedTag = encodingMap.getOrDefault(tag, tag);
                result.append("<").append(encodedTag).append(">");
                i = j + 1;
            } else {
                // Copy the current character as is
                result.append(line.charAt(i));
                i++;
            }
        }
        return result.toString();
    }
    static class MasterMind{
        private boolean hasWon;
        private int codelength;
        private int numOfGusses;
        private String secretCode;
        public MasterMind(int codeLength,int numOfGusses){
            this.codelength=codeLength;
            this.numOfGusses=numOfGusses;
            this.secretCode=getSecretCode();
            this.hasWon=false;
        }
        public String getSecretCode(){
            Random random=new Random();
            String code="";
            for(int i=0;i<code.length();i++){
                int digit=random.nextInt(10);
                code+=Integer.toString(digit);
            }
            return code;
        }
        public void play(){
            System.out.println("Welcome Welcome Welcome");
            System.out.println("Are you ready to get to be AWESOME");
            System.out.println("Let's go");
            System.out.println("here you would be getting "+numOfGusses+"to get the right combination of words of length "+codelength);
            Scanner sc=new Scanner(System.in);
            for(int i=1;i<=numOfGusses;i++){
                System.out.println("enter guess number "+i);
                String guess=sc.nextLine();
                if(guess.length()!=codelength){
                    System.out.println("you need to engter the length of size "+ codelength);
                    i--;
                    continue;
                }
                int numCorrect=0;
                int numClose=0;
                boolean[] isCorrect=new boolean[codelength];
                for(int j=0;j<codelength;j++){
                    char isChar=guess.charAt(j);
                    if(isChar==secretCode.charAt(j)){
                        isCorrect[j]=true;
                        numCorrect++;
                    }
                }
                if(numCorrect==codelength){
                    System.out.println("Congrats you are king now");
                    hasWon=true;
                    break;
                }
                for(int j=0;j<codelength;j++){
                    if(!isCorrect[j] && secretCode.indexOf(guess.charAt(j))!=-1){
                        numClose++;
                    }
                }
                System.out.println("your guess "+guess);
                System.out.println("no of guess which are in the correct position "+numCorrect);
                System.out.println("no of guess whuch are in the wrong psition "+numClose);
            }
            if(!hasWon){
                System.out.println("Better luck next time");
                System.out.println("the secret code is "+secretCode);
            }
            sc.close();
        }
    }

    public static void main(String[] args) {
        String filePath = "/Users/nishanthgoud/Documents/DSA/src/test.xml";
        try {
            String encodedXml = xmlEncode(filePath);
            System.out.println(encodedXml);
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }
}
