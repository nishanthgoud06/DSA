public class day4 {
    public static void bubble_sort_r(int[] a,int count){
        if(count==0||count==1)
            return;
        for(int i=0;i<a.length-1;i++){
            if(a[i]>a[i+1]){
                int temp=a[i];
                a[i]=a[i+1];
                a[i+1]=temp;
            }
        }
        bubble_sort_r(a,count-1);
    }
    public static void bubble_sort(int[] a){
        for(int i=0;i<a.length-1;i++){
            for(int j=0;j<a.length-i-1;j++){
                if(a[j]>a[j+1]){
                    int temp=a[j];
                    a[j]=a[j+1];
                    a[j+1]=temp;
                }
            }
        }
        for(int i=0;i<a.length;i++){
            System.out.println(a[i]);
        }
    }
    public static void printing(int[] a){
        for(int i=0;i<a.length;i++){
            System.out.println(a[i]);
        }
    }
    public static int power_i(int i,int j){
        if(j==0)
            return 1;
        if(j==1)
            return i;
        if(j%2!=0)
            return i*(power_i(i,j/2)*power_i(i,j/2));
        if(j%2==0)
            return power_i(i,j/2)*power_i(i,j/2);

        return -1;
    }
    public static int power(int i,int j){
        if(j==0)
            return 1;
        return i*power(i,j-1);
    }
    public static boolean palindrome(String s,int i,int j){
        if(i>=j)
            return true;
        if(s.charAt(i)==s.charAt(j))
            palindrome(s,i+1,j-1);
        return false;
    }
    public static void rev(String s){
        for(int i=s.length()-1;i>=0;i--){
            System.out.print(s.charAt(i));
        }
    }
    public static void rev_r(String s){
        if(s.length()==0)
            return;
        else{
            System.out.println(s.charAt(s.length()-1));
            rev_r(s.substring(0,s.length()-1));
        }
    }
    public static void main(String args[]){
        //rev_r("abcd");
        //String s="a";
        //System.out.println(s.substring(0,1));
        // System.out.println(palindrome("abba",0,3));
        // System.out.println(power(2,3));

        //System.out.println(power_i(2,4));
        int[] a={5,1,4,2,8};
        bubble_sort_r(a,5);
        printing(a);
    }
}
