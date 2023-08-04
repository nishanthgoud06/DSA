import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class TicTakToe {
    char[][] grid;
    int size;
    public TicTakToe(int size){
        this.size=size;
        grid=new char[size][size];
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                grid[i][j] = ' ';
            }
        }

    }
    public void move(int row,int col,char player){
        grid[row][col]=player;
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                System.out.print(grid[i][j] + " ");
            }
            System.out.println();
        }
    }
    public boolean hashWon(int row,int col,char player){
        return checkR(row, col, player)||checkC(row, col, player)||checkD(row, col, player);
    }
    public boolean checkR(int row,int col,char player){
        for(int i=0;i<size;i++){
            if(grid[i][col]!=player){
                return false;
            }
        }
        return true;
    }
    public boolean checkC(int row,int col,char player){
        for(int i=0;i<size;i++){
            if(grid[row][i]!=player){
                return false;
            }
        }
        return true;
    }
    public boolean checkD(int row,int col,char player){
        boolean state1=true,state2=true;
        for(int i=0;i<size;i++){
            if(grid[i][i]!=player)
                state1=false;
        }
        int j=size-1;
        for(int i=0;i<size/2;i++){
            if(grid[i][j]!=player || grid[j][i]!=player)
                state2=false;
            j--;
        }
        return state1||state2;
    }
    public boolean isvalid(int row, int col) {
        return row >= 0 && row < size && col >= 0 && col < size && grid[row][col]==' ';
    }
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        System.out.println("Enter the Size of the Grid");
        int size=sc.nextInt();
        TicTakToe test=new TicTakToe(size);
        int moves=size*size;
        while(moves!=0){
            System.out.println("who is playing 1 or 2");
            char currentPlayer=sc.next().charAt(0);
            System.out.println("Player "+currentPlayer+" please select the row and col number ");
            System.out.println("make sure you have enter with the grid size 3");
            int row=sc.nextInt();
            int col=sc.nextInt();
            System.out.println("Entered row: " + row);
            System.out.println("Entered col: " + col);
            if(test.isvalid(row,col)){
            test.move(row,col,currentPlayer);
            if(test.hashWon(row,col,currentPlayer)){
                    System.out.println(currentPlayer+" is the winner");
                    break;
                }
                moves=moves-1;
            }else{
                System.out.println("not a valid move");
            }
        }
    }
}
