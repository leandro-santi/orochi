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

    private int panelHeight, panelWidth, tileSize = 25;

    private Tilemap snakeHead;
    private ArrayList<Tilemap> snakeBody;

    private Tilemap food;
    private Random random;
    private Boolean gameOver;

    private Timer gameLoop;
    private int vX, vY;

    public Snake(int height, int width) {
        this.panelHeight = height;
        this.panelWidth = width;
        setPreferredSize(new Dimension(this.panelWidth, panelHeight));
        setBackground(Color.BLACK);

         snakeHead = new Tilemap(5, 5);
         snakeBody = new ArrayList<Tilemap>();

        addKeyListener(this);
        setFocusable(true);

         food = new Tilemap(10, 10);
        random = new Random();
//         placeFood();

        vX = 0;
        vY = 0;

        gameLoop = new Timer(100, this);
        gameLoop.start();
    }




    //region KEYS CONTROLLER
    @Override
    public void actionPerformed(ActionEvent e) {

    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {

    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
    //endregion
}
