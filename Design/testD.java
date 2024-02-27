package Design;

public class testD {
    public static void main(String[] args) {
        Marks one=Marks.getInstance();
        one.addMarks("SAI",100);
        one.addMarks("Yashwanth",90);
        one.addMarks("Dinesh",80);
        one.addMarks("Ravi",20);
        System.out.println(one.getMarks("SAI"));
        Marks two=Marks.getInstance();
        System.out.println(two.getMarks("SAI"));
    }
}
