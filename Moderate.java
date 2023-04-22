import java.awt.print.Pageable;
import java.io.FileNotFoundException;
import java.security.Key;
import java.util.*;
import java.util.regex.Pattern;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Moderate {
    //number swapper
    public static void numSwapper(int a,int b){
        System.out.println("before "+a+" "+b);
        a=a^b;
        b=a^b;
        a=a^b;
        System.out.println("after "+a+" "+b);
    }
    //word frequencies
    public static int wordFreq(String book,String target){
        int[] t = new int[26];
        int result = 0;
        target = target.toLowerCase();
        for (int i = 0; i < target.length(); i++) {
            t[target.charAt(i) - 'a']++;
        }
        String[] books = book.split(" ");
        for (String s : books) {
            if (wordFreqHelper(s.toLowerCase(), t))
                result++;
        }
        return result;
    }
    public static boolean wordFreqHelper(String s,int[] target){
        int[] targetCopy = Arrays.copyOf(target, target.length);
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c >= 'a' && c <= 'z') {
                targetCopy[c - 'a']--;
            }
        }
        for (int i = 0; i < 26; i++) {
            if (targetCopy[i] != 0) {
                return false;
            }
        }
        return true;
    }
    //solving using HashMap
    public static int Freq(String book,String target){
        HashMap<String,Integer> hm=new HashMap<>();
        String[] str=book.split(" ");
        for(String s:str){
            hm.put(s,hm.getOrDefault(s,0)+1);
        }
        return hm.get(target);
    }
    static class list{
        int val;
        list next;
        public list(int val){
            this.val=val;
        }
        public list(int val,list next){
            this.val=val;
            this.next=next;
        }
    }
    //Intersection
    public static int Intersection(list list1,list list2){
        int len1=length(list1);
        int len2=length(list2);
        if(len1>len2){
            list1=adjust(list1,len1-len2);
        }else{
            list2=adjust(list2,len2-len1);
        }
        while(list1!=null && list2!=null){
            if(list1.val== list2.val)
                return list1.val;
            list1=list1.next;
            list2=list2.next;
        }
        return -1;
    }
    public static list adjust(list l,int num){
        while(num!=0){
            l=l.next;
            num=num-1;
        }
        return l;
    }
    public static int length(list l){
        int result=0;
        while(l!=null){
            result++;
            l=l.next;
        }
        return result;
    }
    //Tic-Tac-Toe
    enum Piece{
        Empty,Red,Blue
    };
    public static Piece hashWon(Piece[][] board){
        int size=board.length;
        if(board[0].length!= size)
            return Piece.Empty;
        Piece first;
        //first we are going to check for the row
        for(int i=0;i<size;i++){
            first=board[i][0];
            if(first==Piece.Empty)
                continue;
            for(int j=1;j<size;j++){
                if(board[i][j]!=first){
                    break;
                }else if(j==size-1){
                    return first;
                }
            }
        }
        //now we are going to check for the column
        for(int i=0;i<size;i++){
            first=board[0][i];
            if(first==Piece.Empty)
                continue;
            for(int j=1;j<size;j++){
                if(board[j][i]!=first){
                    break;
                }else if(j==size-1)
                    return first;
            }
        }
        //check diagonal
        first=board[0][0];
        if(first!=Piece.Empty){
            for(int i=1;i<size;i++){
                if(board[i][i]!=first){
                    break;
                }else if(i==size-1){
                    return first;
                }
            }
        }
        first=board[0][size-1];
        if(first!=Piece.Empty){
            for(int i=1;i<size;i++){
                if(board[i][size-1-i]!=first){
                    break;
                }else if(i==size-1){
                    return first;
                }
            }
        }
        return Piece.Empty;
    }
    //approch where we are going to design with less duplication
    static class check{
        public int row,col;
        private int rowInc,colInc;
        public  check(int row,int col,int rowInc,int colInc){
            this.row=row;
            this.col=col;
            this.rowInc=rowInc;
            this.colInc=colInc;
        }
        public void increment(){
            row+= rowInc;
            col+=colInc;
        }
        public boolean inBound(int size){
            return row>=0&&col>=0&&row<size&&col<size;
        }
    }
    public static Piece checkWinner(Piece[][] board){
        if(board[0].length!= board.length)
            return Piece.Empty;
        int size= board.length;
        ArrayList<check> arraylist=new ArrayList<>();
        for(int i=0;i<size;i++){
            arraylist.add(new check(0,i,1,0));
            arraylist.add(new check(i,0,0,1));
        }
        arraylist.add(new check(0,0,1,1));
        arraylist.add(new check(0,size-1,1,-1));
        for(check c:arraylist){
            Piece Winner=winit(board,c);
            if(Winner!=Piece.Empty)
                return Winner;
        }
        return Piece.Empty;
    }
    public static Piece winit(Piece[][] board,check c){
        Piece current=board[c.row][c.col];
        while(c.inBound(board.length)){
            if(board[c.row][c.col]!=current){
                return Piece.Empty;
            }
            c.increment();
        }
        return current;
    }
    //Factorial Zeros
    public static int factZero(int num){
        int result=0;
        for(int i=5;num/i>0;i=i*2){
            result+=num/i;
        }
        return result;
    }
    //smallest Difference
    public static int[] smallDiff(int[] array1,int[] array2){
        if(array1.length==0||array2.length==0)
            return new int[0];
        int[] result=new int[2];
        int min=Integer.MAX_VALUE;
        for(int i=0;i<array1.length;i++){
            for(int j=0;j< array2.length;j++){
                if(Math.abs(array1[i]-array2[j])<min){
                    min=Math.abs(array1[i]-array2[j]);
                    result[0]=array1[i];
                    result[1]=array2[j];
                }
            }
        }
        System.out.println(min);
        return result;
    }
    //Number Max
    public static int numMax(int a,int b){
        PriorityQueue<Integer> pq=new PriorityQueue<>();
        pq.offer(a);
        pq.offer(b);
        pq.poll();
        return pq.peek();
    }
    //Operations
    public static int minus(int a,int b){
        return a+negate(b);
    }
    public static int negate(int a){
        int neg=0;
        int newNed=a<0?1:-1;
        while(a!=0){
            neg+=newNed;
            a+=newNed;
        }
        return neg;
    }
    public static int multiply(int a,int b){
        int result=0;
        int count=0;
        while(result!=b){
            count+=a;
            result++;
        }
        return count;
    }
    //Living Person
    static class person{
        public int birth;
        public int death;
        public person(int birth,int death){
            this.birth=birth;
            this.death=death;
        }
    }
    public static int maxAliveyear(person[] people,int min,int max){
        int maxAliv=0;
        int maxAliveYear=0;
        for(int year=min;year<=max;year++){
            int alive=0;
            for(person p:people){
                if(p.birth<=year && year<=p.death){
                    alive++;
                }
            }
            if(alive>maxAliv){
                maxAliv=alive;
                maxAliveYear=year;
            }
        }
        return maxAliveYear;
    }
    //Diving Board
    public static List<Integer> DivingBoard(int shorter,int longer,int k){
        List<Integer> result=new ArrayList<>();
        if(k==0)
            return result;
        int j=k;
        for(int i=0;i<=k;i++){
            result.add(i*shorter+j*longer);
            j--;
        }
        return result;
    }
    //using memorization
    public static List<Integer> DivingBoard1(int shorter,int longer,int k){
        if(k==0)
            return new ArrayList<>();
        HashSet<Integer> result=new HashSet<>();
        DivingBoard1Helper(shorter,longer,k,0,result);
        return new ArrayList<>(result);
    }
    public static void  DivingBoard1Helper(int shorter,int longer,int k,int current,HashSet<Integer> result){
        if(k==0){
            result.add(current);
            return;
        }
        DivingBoard1Helper(shorter,longer,k-1,current+shorter,result);
        DivingBoard1Helper(shorter,longer,k-1,current+longer,result);
    }
    //using Memo
    public static int[] DivingBoard2(int shorter,int longer,int K){
        if(K==0)
            return new int[0];
        HashSet<Integer> result=new HashSet<>();
        HashSet<String> visited=new HashSet<>();
        DivingBoard2Helper(shorter,longer,K,result,visited,0);
        return result.stream().mapToInt(Integer::intValue).toArray();
    }
    public static void DivingBoard2Helper(int shorter,int longer,int k,HashSet<Integer> result,HashSet<String> visted,int current){
        if(k==0){
            result.add(current);
            return;
        }
        String key=k+" "+current;
        if(visted.contains(key))
            return;
        DivingBoard2Helper(shorter,longer,k-1,result,visted,current+shorter);
        DivingBoard2Helper(shorter,longer,k-1,result,visted,current+longer);
        visted.add(key);
    }
    //Xml Encoding
    public static String XmlEncoding() throws FileNotFoundException{
        String filePath = "/Users/nishanthgoud/Documents/DSA/src/test.xml";
        String result = "";
        HashMap<String, Integer> hashmap = new HashMap<>();
        hashmap.put("family", 1);
        hashmap.put("person", 2);
        hashmap.put("firstname", 3);
        hashmap.put("lastname", 4);
        hashmap.put("state", 5);
        try (Scanner sc = new Scanner(new File(filePath))) {
            while (sc.hasNextLine()) {
                String current = sc.nextLine();
                current= current.replaceAll("<[^>]*>|&[a-zA-Z]+;", "");
                System.out.println(current);
                String[] s=current.split(" ");
                for(String str:s){
                    if(hashmap.containsKey(str)){
                        result+=hashmap.get(str);
                    }else{
                        result+=str;
                    }
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("File not found: " + filePath);
        }
        return result;
    }
    static class MasterMind{
        private int numofguesses;
        private int codeLimit;
        private String SecretCode;
        private boolean hashWon;
        public MasterMind(int numofguesses,int codeLimit){
            this.numofguesses=numofguesses;
            this.codeLimit=codeLimit;
            this.hashWon=false;
            this.SecretCode=getSecretCode();
        }
        public String getSecretCode(){
            Random random=new Random();
            String code="";
            for(int i=0;i<codeLimit;i++){
                int num=random.nextInt(10);
                code+=Integer.toString(num);
            }
            return code;
        }
        public void play(){
            System.out.println("Welcome lets play the game");
            System.out.println("Rules");
            System.out.println("you need to guess the code of length "+codeLimit+" and you get a total of "+numofguesses+" guesses to make");
            Scanner sc=new Scanner(System.in);
            int totalRight=0;
            int closeAns=0;
            for(int i=1;i<numofguesses;i++){
                System.out.println("enter the code");
                String guess=sc.nextLine();
                if(guess.length()!=codeLimit){
                    System.out.println("the length should be of size "+codeLimit);
                    i--;
                    continue;
                }
                boolean[] checkList=new boolean[codeLimit];
                if(guess.equals(SecretCode)){
                    System.out.println("Congrats you have won the game");
                    hashWon=true;
                    break;
                }
                for(int j=0;j<codeLimit;j++){
                    char c=guess.charAt(j);
                    if(c==SecretCode.charAt(j)){
                        totalRight++;
                        checkList[j]=true;
                    }else if(SecretCode.indexOf(c)!=-1&&!checkList[j]){
                        closeAns++;
                    }
                }
                System.out.println("your guess "+guess);
                System.out.println("total number of guess that are right are" +totalRight);
                System.out.println("total number of guess that are close are" +closeAns);
            }
            if(!hashWon){
                System.out.println("Better luck next Time");
                System.out.println("the code was "+SecretCode);
            }
            sc.close();
        }
    }

    public static void main(String[] args) {
        //test case for master Mind
        MasterMind test=new MasterMind(4,4);
        test.play();
        //test case for XML encoding

//        try{
//            System.out.println(XmlEncoding());
//        }catch(Exception e){
//            System.out.println(e);
//        }
        //test case for Diving Board Memorization
//        System.out.println(DivingBoard2(2,3,3));
        //test case for Diving Board
//        System.out.println(DivingBoard(2,3,3));
//        System.out.println(DivingBoard1(2,3,3));
        //test cases for MaxAlive Year
//        person[] people = new person[10];
//        people[0] = new person(1910, 1920);
//        people[1] = new person(1920, 1930);
//        people[2] = new person(1930, 1940);
//        people[3] = new person(1940, 1950);
//        people[4] = new person(1950, 1960);
//        people[5] = new person(1960, 1970);
//        people[6] = new person(1970, 1980);
//        people[7] = new person(1980, 1990);
//        people[8] = new person(1990, 2000);
//        people[9] = new person(2000, 2010);
//
//        int minYear = 1915;
//        int maxYear = 1995;
//        int expectedOutput = 1920;
//
//        int result = maxAliveyear(people, minYear, maxYear);
//        System.out.println("Expected output: " + expectedOutput);
//        System.out.println("Actual output: " + result);
        //test case fro minus using +
//        System.out.println(minus(5,3));
//        System.out.println(multiply(5,3));
        //test case for Max number without using if-else or comparsion operator
//        System.out.println(numMax(8,10));
        //test case for the smallest Difference
//        int[] a=smallDiff(new int[]{1,3,15,11,2},new int[]{23,127,235,19,8});
//        System.out.println(a[0]+" "+a[1]);
        //test case for Factorial Zeros
//        System.out.println(factZero(10));
        //test case for Tic Tac Toe
//        Piece[][] test=new Piece[3][3];
//        test[0][0]=Piece.Red;
//        test[0][1]=Piece.Blue;
//        test[0][2]=Piece.Red;
//        test[1][0]=Piece.Red;
//        test[1][1]=Piece.Blue;
//        test[1][2]=Piece.Blue;
//        test[2][0]=Piece.Blue;
//        test[2][1]=Piece.Blue;
//        test[2][2]=Piece.Red;
//        System.out.println(checkWinner(test));
        //test case for number swapper
//        numSwapper(5,10);
        //test case for Word Frequency
//        System.out.println(Freq("In publishing and graphic design, Lorem ipsum is a placeholder text commonly used to demonstrate the visual form of a document or a typeface without relying on meaningful content. Lorem ipsum may be used as a placeholder before the final copy is available.","Lorem"));
        //test case for Intersection
//        list a1 = new list(1);
//        list a2 = new list(2);
//        list a3 = new list(3);
//        list b1 = new list(4);
//        list b2 = new list(5);
//        list b3 = new list(6);
//        list c1 = new list(7);
//        list c2 = new list(8);
//        list c3 = new list(9);

// construct linked lists
//        a1.next = a2;
//        a2.next = a3;
//        a3.next = c1;
//        c1.next = c2;
//        c2.next = c3;
//
//        b1.next = b2;
//        b2.next = b3;
//        b3.next = c1;
//        System.out.println(Intersection(a1,b1));
    }
}
