package Base;

import com.googlecode.lanterna.terminal.Terminal;

import java.util.List;

public class Laser {

    //    private int nextX;
//    private int nextY;
    private int x;
    private int y;
    private int speed;
    private char appearence;

    public Laser(int x, int y, int speed, char appearence) {
        this.x = x;
        this.y = y;
        this.speed = speed;
        this.appearence = appearence;
    }

    public void move() {
        x = x + speed;

    }

    public void draw(Terminal terminal) {
        terminal.moveCursor(x, y);
        terminal.putCharacter(appearence);
    }

    public void hitsTarget(Player p1, Player p2) {

        for (Body b : p1.getBody()) {
            if (x == b.getX() && y == b.getY()) {
                p1.setHp(p1.getHp() - 1);
                b.setAppearence('D');
            }
        }
        for (Body b : p2.getBody()) {
            if (x == b.getX() && y == b.getY()) {
                p2.setHp(p2.getHp() - 1);
                b.setAppearence('w');
            }
        }
    }

    public void hitsLaser(List<Laser> lasers) {
        for (Laser l : lasers) {

        }
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }
}
