package games;

import javax.swing.*;

public class GameFrame extends JFrame {
    public GameFrame(){
        GamePanel gamePanel=new GamePanel();
        this.add(gamePanel);
        this.setTitle("Snake");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(true);
        this.pack();
        this.setVisible(true);
        this.setLocationRelativeTo(null);
    }

}
