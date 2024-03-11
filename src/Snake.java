import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.Random;

public class Snake extends JPanel implements ActionListener, KeyListener {

    private class Tilemap {
        int x, y;

        Tilemap(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    private final int screenHeight;
    private final int screenWidth;
    private final int tileSize = 25;

    private Tilemap snakeHead;
    private final ArrayList<Tilemap> snakeBody;

    private final Tilemap food;
    private final Random random;
    private Boolean gameOver;

    private final Timer gameLoop;
    private int vX, vY;

    public Snake(int height, int width) {
        this.screenHeight = height;
        this.screenWidth = width;
        setPreferredSize(new Dimension(this.screenWidth, screenHeight));
        setBackground(Color.BLACK);

        snakeHead = new Tilemap(5, 5);
        snakeBody = new ArrayList<Tilemap>();

        addKeyListener(this);
        setFocusable(true);

        food = new Tilemap(10, 10);
        random = new Random();
        placeFood();

        vX = 0;
        vY = 0;

        gameOver = false;

        gameLoop = new Timer(100, this);
        gameLoop.start();
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        draw(g);
    }

    public void draw(Graphics g) {
        g.setColor(Color.RED);
        g.fillRect(food.x * tileSize, food.y * tileSize, tileSize, tileSize);

        g.setColor(Color.GREEN);
        g.fillRect(snakeHead.x * tileSize, snakeHead.y * tileSize, tileSize, tileSize);

        for (int i = 0; i < snakeBody.size(); i++) {
            Tilemap snakePart = snakeBody.get(i);
            g.fillRect(snakePart.x * tileSize, snakePart.y * tileSize, tileSize, tileSize);
        }
        g.setFont(new Font("Arial", Font.PLAIN, 16));
        if (gameOver) {
            g.setColor(Color.RED);
            g.drawString("Game Over: " + String.valueOf(snakeBody.size()), tileSize - 16, tileSize);
        } else {
            g.drawString("Score: " + String.valueOf(snakeBody.size()), tileSize - 16, tileSize);
        }
    }

    public boolean collision(Tilemap tile1, Tilemap tile2) {
        return tile1.x == tile2.x && tile1.y == tile2.y;
    }

    public void move() {
        Tilemap newHead = new Tilemap(snakeHead.x + vX, snakeHead.y + vY);

        for (Tilemap snakePart : snakeBody) {
            if (collision(newHead, snakePart)) {
                gameOver = true;
                gameLoop.stop();
                return;
            }
        }

        for (int i = snakeBody.size() - 1; i >= 0; i--) {
            Tilemap snakePart = snakeBody.get(i);
            if (i == 0) {
                snakePart.x = newHead.x;
                snakePart.y = newHead.y;
            } else {
                Tilemap prevSnakePart = snakeBody.get(i - 1);
                snakePart.x = prevSnakePart.x;
                snakePart.y = prevSnakePart.y;
            }
        }

        snakeHead = newHead;

        if (collision(snakeHead, food)) {
            snakeBody.add(new Tilemap(food.x, food.y));
            placeFood();
        }

        if (snakeHead.x * tileSize < 0 || snakeHead.x * tileSize > screenWidth ||
                snakeHead.y * tileSize < 0 || snakeHead.y * tileSize > screenHeight
        ) {
            gameOver = true;
            gameLoop.stop();
        }
    }

    public void placeFood() {
        food.x = random.nextInt(screenWidth / tileSize);
        food.y = random.nextInt(screenHeight / tileSize);

    }

    //region KEYS CONTROLLER
    @Override
    public void actionPerformed(ActionEvent e) {
        move();
        repaint();
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_W && vY != 1) {
            vX = 0;
            vY = -1;
        }
        if (e.getKeyCode() == KeyEvent.VK_S && vY != -1) {
            vX = 0;
            vY = 1;
        } else if (e.getKeyCode() == KeyEvent.VK_A && vX != 1) {
            vX = -1;
            vY = 0;
        } else if (e.getKeyCode() == KeyEvent.VK_D && vX != -1) {
            vX = 1;
            vY = 0;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }
    //endregion
}
