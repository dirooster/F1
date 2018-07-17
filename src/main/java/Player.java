import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;

/**
 * Created by Dimasik on 13.05.2018.
 */
public class Player {

    public static final int MAX_V = 100;
    public static final int MAX_TOP = 20;
    public static final int MAX_BOTTOM = 480;

    Image img_C = new ImageIcon("res/Player.png").getImage();
    Image img_L = new ImageIcon("res/Player_Left.png").getImage();
    Image img_R = new ImageIcon("res/Player_Right.png").getImage();
    Image img = img_C;

    int v = 0;
    int dv = 0;
    int s = 0;

    int layer1 = 0;
    int layer2 = 1100;

    int x = 100;
    int y = 250;
    int dy = 0;

    public Rectangle getRect() {
        return new Rectangle(x, y, 160, 56);
    }

    public void move(){
        s += v;
        v += dv;
        if (v <= 0) v = 0;
        if (v >= MAX_V) v = MAX_V;
        y -= dy;
        if (y <= MAX_TOP) y = MAX_TOP;
        if (y >= MAX_BOTTOM) y = MAX_BOTTOM;
        if (layer2 - v <= 0){
            layer1 = 0;
            layer2 = 1100;
        }
        else {
            layer1 -= v;
            layer2 -= v;
        }
    }

    public void keyPressed(KeyEvent e){
        int key = e.getKeyCode();
        if (key == KeyEvent.VK_RIGHT) {
            dv = 5;
        }
        if (key == KeyEvent.VK_LEFT) {
            dv = -5;
        }
        if (key == KeyEvent.VK_UP) {
            dy = 10;
            img = img_L;
        }
        if (key == KeyEvent.VK_DOWN) {
            dy = -10;
            img = img_R;
        }
    }
    public void keyReleased(KeyEvent e){
        int key = e.getKeyCode();
        if (key == KeyEvent.VK_RIGHT || key == KeyEvent.VK_LEFT) {
            dv = 0;
        }
        if (key == KeyEvent.VK_UP || key == KeyEvent.VK_DOWN) {
            dy = 0;
            img = img_C;
        }
    }
}
