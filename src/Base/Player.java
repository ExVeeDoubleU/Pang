package Base;

import com.googlecode.lanterna.terminal.Terminal;

import java.util.ArrayList;
import java.util.List;

public class Player {

    private int x;
    private int y;
    private int nextX;
    private int nextY;
    private int speed;
    private int length;
    private int hp;
    private int playerNumber;
    private char appearance;
    private List<Body> body;


    public Player(int x, int y, int speed, int length, int hp, int playerNumber, char appearance) {
        this.x = x;
        this.y = y;
        this.speed = speed;
        this.length = length;
        this.hp = hp;
        this.playerNumber = playerNumber;
        this.appearance = appearance;
    }

    public void createBody() {
        this.body = new ArrayList<Body>();
        for (int i = 0; i < getLength(); i++) {
            getBody().add(new Body(getX(), (getY() - (getLength() / 2)) + i, getSpeed(), getPlayerNumber(), getappearance()));
        }
    }

    public void shoot(int startX, int dir, List<Laser> lasers, char appearance){
        lasers.add(new Laser(startX, getY(), 1*dir, appearance));

        }


    public void drawBody(Terminal terminal) {
        for (Body b : getBody()) {
            b.draw(terminal);

        }
    }

    public void moveDown(Terminal terminal) {
        if (getY() > 25)
            return;
        setY(getY() + getSpeed());
        for (Body b : getBody()) {
            b.unDraw(terminal);
            b.setY(b.getY() + b.getSpeed());
        }
    }

    public void moveUp(Terminal terminal) {
        if (getY() < 9)
            return;
        setY(getY() - getSpeed());
        for (Body b : getBody()) {
            b.unDraw(terminal);
            b.setY(b.getY() - b.getSpeed());
        }
    }


    //region getters and setters
    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getNextX() {
        return nextX;
    }

    public void setNextX(int nextX) {
        this.nextX = nextX;
    }

    public int getNextY() {
        return nextY;
    }

    public void setNextY(int nextY) {
        this.nextY = nextY;
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public int getHp() {
        return hp;
    }

    public void setHp(int hp) {
        this.hp = hp;
    }

    public int getPlayerNumber() {
        return playerNumber;
    }

    public void setPlayerNumber(int playerNumber) {
        this.playerNumber = playerNumber;
    }

    public char getappearance() {
        return appearance;
    }

    public void setappearance(char appearance) {
        this.appearance = appearance;
    }

    public List<Body> getBody() {
        return body;
    }

    public void setBody(List<Body> body) {
        this.body = body;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }
    //endregion
}
