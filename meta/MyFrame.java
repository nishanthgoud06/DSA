package meta;

import javax.swing.JFrame;
import javax.swing.JTextField;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class MyFrame extends JFrame {
    private JTextField textField;

    public MyFrame() {
        setTitle("Key Adapter Example");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        textField = new JTextField();
        textField.addKeyListener(new MyKeyListener());

        add(textField);

        setVisible(true);
    }

    private class MyKeyListener extends KeyAdapter {
        @Override
        public void keyTyped(KeyEvent e) {
            System.out.println("Key typed: " + e.getKeyChar());
        }

        @Override
        public void keyPressed(KeyEvent e) {
            System.out.println("Key pressed: " + e.getKeyCode());
        }

        @Override
        public void keyReleased(KeyEvent e) {
            System.out.println("Key released: " + e.getKeyCode());
        }
    }

    public static void main(String[] args) {
        MyFrame frame = new MyFrame();
    }
}
