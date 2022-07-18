public class sayDigit {
    public static void print(int n){
        while(n!=0){
            int sum=n%10;
            switch(sum){
                case 0:
                    System.out.println("Zero");
                    break;
                case 1:
                    System.out.println("One");
                    break;
                case 2:
                    System.out.println("Two");
                    break;
                case 3:
                    System.out.println("Three");
                    break;
                case 4:
                    System.out.println("Four");
                    break;
                case 5:
                    System.out.println("Five");
                    break;
                case 6:
                    System.out.println("Six");
                    break;
                case 7:
                    System.out.println("Seven");
                    break;
                case 8:
                    System.out.println("Eight");
                    break;
                case 9:
                    System.out.println("Nine");
                    break;
                case 10:
                    System.out.println("Ten");
                    break;
            }
            n=n/10;
        }

    }
    public static void approch2(int n,String[] s){
        if(n==0)
            return;
        int count=n%10;
        approch2(n/10,s);
        System.out.println(s[count]);

    }
    public static void main(String[] args) {
        //print(1234);
        String[] s={"zero","one","two","three","four","five","six","seven","eight","nine","ten"};
        approch2(1234,s);
    }
}
