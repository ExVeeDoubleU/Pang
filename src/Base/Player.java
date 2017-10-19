package Base;

import com.googlecode.lanterna.terminal.Terminal;

import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("ALL")
class Player {

    private int x;
    private int y;
    private int speed;
    private int length;
    private int hp;
    private char appearance;
    private List<Body> body;


    Player(int x, int y, int speed, int length, int hp, char appearance) {
        this.x = x;
        this.y = y;
        this.speed = speed;
        this.length = length;
        this.hp = hp;
        this.appearance = appearance;
    }

    void createBody() {
        this.body = new ArrayList<>();
        for (int i = 0; i < getLength(); i++) {
            getBody().add(new Body(getX(), (getY() - (getLength() / 2)) + i, getSpeed(), getAppearance()));
        }
    }

    void shoot(int startX, int dir, List<Laser> lasers, char appearance) {
//        lasers.add(new Laser(startX, getY(), dir, appearance));
//        lasers.add(new Laser(startX, getY() - 1, dir, appearance));
//        lasers.add(new Laser(startX, getY() + 1, dir, appearance));
        lasers.add(new Laser(startX, getY() - 2, dir, appearance));
        lasers.add(new Laser(startX, getY() + 2, dir, appearance));

    }


    void drawBody(Terminal terminal) {
        for (Body b : getBody()) {
            b.draw(terminal);

        }
    }

    void moveDown(Terminal terminal) {
        if (getY() > 25)
            return;
        setY(getY() + getSpeed());
        for (Body b : getBody()) {
            b.unDraw(terminal);
            b.setY(b.getY() + b.getSpeed());
        }
    }

    void moveUp(Terminal terminal) {
        if (getY() < 9)
            return;
        setY(getY() - getSpeed());
        for (Body b : getBody()) {
            b.unDraw(terminal);
            b.setY(b.getY() - b.getSpeed());
        }
    }


    //region getters and setters
    int getX() {
        return x;
    }

    void setX(int x) {
        this.x = x;
    }

    int getY() {
        return y;
    }

    void setY(int y) {
        this.y = y;
    }

    int getSpeed() {
        return speed;
    }

    void setSpeed(int speed) {
        this.speed = speed;
    }

    int getHp() {
        return hp;
    }

    void setHp(int hp) {
        this.hp = hp;
    }

    char getAppearance() {
        return appearance;
    }

    void setAppearance(char appearance) {
        this.appearance = appearance;
    }

    List<Body> getBody() {
        return body;
    }

    void setBody(List<Body> body) {
        this.body = body;
    }

    int getLength() {
        return length;
    }

    void setLength(int length) {
        this.length = length;
    }
    //endregion
}
