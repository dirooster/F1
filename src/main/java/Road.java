import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;

/**
 * Created by Dimasik on 13.05.2018.
 */
public class Road extends JPanel implements ActionListener, Runnable {
    Timer mainTimer = new Timer(20, this);
    Image img = new ImageIcon ("res/doroga.png").getImage();
    Player p = new Player();
    Thread enemiesFactory = new Thread(this);
    Thread audioThread = new Thread(new AudioThread());
    ArrayList<Enemy> enemies = new ArrayList();

    public Road() {
        mainTimer.start();
        enemiesFactory.start();
        audioThread.start();
        addKeyListener(new MyKeyAdapter());
        setFocusable(true);
    }

    @Override
    public void run() {
        while (true){
            Random rand = new Random();
            try {
                Thread.sleep(rand.nextInt(2000));
                enemies.add(new Enemy(1200, rand.nextInt(600), rand.nextInt(60), this));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private class MyKeyAdapter extends KeyAdapter {
        @Override
        public void keyPressed(KeyEvent e) {
            p.keyPressed(e);
        }

        @Override
        public void keyReleased(KeyEvent e) {
            p.keyReleased(e);
        }
    }

    public void paint(Graphics g){
        ((Graphics2D) g).drawImage(img, p.layer1, 0, null);
        ((Graphics2D) g).drawImage(img, p.layer2, 0, null);
        ((Graphics2D) g).drawImage(p.img, p.x, p.y, null);

        double speed = (200/Player.MAX_V) * p.v;
        g.setColor(Color.BLACK);
        Font font = new Font("Gothic", Font.ITALIC, 20);
        g.setFont(font);
        ((Graphics2D) g).drawString("Speed: " + speed + " km/h", 100, 30);


        Iterator<Enemy> i = enemies.iterator();
        while (i.hasNext()) {
            Enemy e = i.next();
            if (e.x >= 2400 || e.x <= -2400){
                i.remove();
            }
            else {
                e.move();
                ((Graphics2D) g).drawImage(e.img, e.x, e.y, null);
            }
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        p.move();
        repaint();
        testCollisionWithEnemies();
        testWin();
    }

    private void testWin() {
        if(p.s > 50000){
            JOptionPane.showMessageDialog(null,"You Win!");
            System.exit(0);
        }
    }

    private void testCollisionWithEnemies() {

        Iterator<Enemy> i = enemies.iterator();
        while (i.hasNext()) {
            Enemy e = i.next();
            if (p.getRect().intersects(e.getRect())){
                JOptionPane.showMessageDialog(null,"You lose!");
                System.exit(1);
            }

        }
    }
}
