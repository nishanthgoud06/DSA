package Design;
import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class pokemon{
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        boolean continuePlaying = true;

        while (continuePlaying) {
            System.out.println("Select the Tone");
            System.out.println("1. Opening");
            System.out.println("2. Professor Lab");
            System.out.println("3. Rivial Appear");
            System.out.println("4. Stop");
            int tone = sc.nextInt();

            String path = "";
            switch (tone) {
                case 1:
                    path = "/Users/nishanthgoud/Documents/DSA/src/Design/Audio/1-01. Opening.wav";
                    break;
                case 2:
                    path = "/Users/nishanthgoud/Documents/DSA/src/Design/Audio/1-03. Professor Oak.wav";
                    break;
                case 3:
                    path = "/Users/nishanthgoud/Documents/DSA/src/Design/Audio/1-05. Your Rival Appears.wav";
                    break;
                case 4:
                    continuePlaying = false;
                    break;
                default:
                    System.out.println("Invalid option!");
            }

            if (continuePlaying) {
                try (AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File(path));
                     Clip clip = AudioSystem.getClip()) {

                    clip.open(audioInputStream);
                    boolean playing = false;

                    while (true) {
                        System.out.println("1. Play 2. Pause 3. Stop 4. Exit");
                        int choice = sc.nextInt();

                        switch (choice) {
                            case 1:
                                if (!playing) {
                                    clip.start();
                                    playing = true;
                                }
                                break;
                            case 2:
                                if (playing) {
                                    clip.stop();
                                    playing = false;
                                }
                                break;
                            case 3:
                                clip.stop();
                                clip.setMicrosecondPosition(0);
                                playing = false;
                                break;
                            case 4:
                                clip.stop();
                                return;
                            default:
                                System.out.println("Invalid option!");
                        }
                    }
                } catch (UnsupportedAudioFileException | LineUnavailableException | IOException e) {
                    System.out.println("Error playing audio: " + e.getMessage());
                }
            }
        }
    }
}
