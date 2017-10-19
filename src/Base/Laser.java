package Base;

import com.googlecode.lanterna.terminal.Terminal;

import java.util.List;

@SuppressWarnings("All")
class Laser {

    //    private int nextX;
//    private int nextY;
    private int x;
    private int y;
    private int speed;
    private char appearance;

    Laser(int x, int y, int speed, char appearance) {
        this.x = x;
        this.y = y;
        this.speed = speed;
        this.appearance = appearance;
    }

    void move(Terminal terminal) {
        terminal.moveCursor(x, y);
        terminal.putCharacter(' ');
        x = x + speed;

    }

    void draw(Terminal terminal) {
        terminal.moveCursor(x, y);
        if (this.speed > 0)
            terminal.applyForegroundColor(Terminal.Color.RED);
        else if (this.speed < 0)
            terminal.applyForegroundColor(Terminal.Color.CYAN);
        else
            terminal.applyForegroundColor(Terminal.Color.DEFAULT);
        terminal.putCharacter(appearance);
        terminal.applyForegroundColor(Terminal.Color.DEFAULT);
    }

    void hitsTarget(Player p1, Player p2, MP3Player mp3, List<Laser> removeList) {

        for (Body b : p1.getBody()) {
            if (x == b.getX() && y == b.getY()) {
                p1.setHp(p1.getHp() - 1);
                removeList.add(this);
                mp3.playFX("Hit.wav");

            }
        }
        for (Body b : p2.getBody()) {
            if (x == b.getX() && y == b.getY()) {
                p2.setHp(p2.getHp() - 1);
                removeList.add(this);
                mp3.playFX("Hit.wav");
            }
        }
    }

    void hitsLaser(List<Laser> lasers, List<Laser> removeList) {
        for (int i = lasers.size() - 1; i >= 0; i--) {
            if (!this.equals(lasers.get(i))) {
                if (this.x == lasers.get(i).getX() && this.y == lasers.get(i).getY()) {
                    removeList.add(this);
                    removeList.add(lasers.get(i));
                }
            }

        }
    }

    //region getset
    int getX() {
        return x;
    }

    int getY() {
        return y;
    }

    void setY(int y) {
        this.y = y;
    }
    //endregion
}
