import javax.swing.*;

public class Main {

    public static void main(String[] args) {

        System.out.println("Hello world!");

        int screenHeight = 800;
        int screenWidth = 800;

        JFrame screen = new JFrame("Snake");
        screen.setVisible(true);
        screen.setSize(screenWidth, screenHeight);
        screen.setLocationRelativeTo(null);
        screen.setResizable(true);
        screen.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    }

}