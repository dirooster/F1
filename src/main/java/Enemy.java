import javax.swing.*;
import java.awt.*;

/**
 * Created by Dimasik on 13.05.2018.
 */
public class Enemy {
    Image img = new ImageIcon("res/Enemy.png").getImage();

    int v = 0;
    int x = 0;
    int y = 0;
    Road road;

    public Rectangle getRect() {
        return new Rectangle(x, y, 138, 62);
    }

    public Enemy(int x, int y, int v, Road road){
        this.x = x;
        this.y = y;
        this.v = v;
        this.road = road;
    }

    public void move() {
        x = x - road.p.v + v;
    }
}
