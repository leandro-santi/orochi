import javax.swing.*;

public class Main {

    public static void main(String[] args) {

        System.out.println("Welcome to Orochi's Snake Game");

        int screenHeight = 800;
        int screenWidth = 800;

        JFrame screen = new JFrame("Orochi");
        screen.setVisible(true);
        screen.setSize(screenWidth, screenHeight);
        screen.setLocationRelativeTo(null);
        screen.setResizable(true);
        screen.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        Snake snakeGame = new Snake(600, 800);
        screen.add(snakeGame);

        screen.setVisible(true);
        snakeGame.requestFocus();

    }

}