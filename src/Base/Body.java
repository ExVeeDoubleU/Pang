package Base;


import com.googlecode.lanterna.terminal.Terminal;

public class Body {

    private int x;
    private int y;
    private int nextX;
    private int nextY;
    private int speed;
    private int playerNumber;
    private char appearance;

    public Body(int x, int y, int speed, int playerNumber, char appearance) {
        this.x = x;
        this.y = y;
        this.nextX = x;
        this.nextY = y;
        this.speed = speed;
        this.playerNumber = playerNumber;
        this.appearance = appearance;
    }
    public void draw(Terminal terminal){
        terminal.moveCursor(getX(), getY());
        terminal.putCharacter(getAppearance());
    }
    public void unDraw(Terminal terminal){
        terminal.moveCursor(getX(), getY());
        terminal.putCharacter(' ');
    }
//region getset
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

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public int getPlayerNumber() {
        return playerNumber;
    }

    public void setPlayerNumber(int playerNumber) {
        this.playerNumber = playerNumber;
    }

    public char getAppearance() {
        return appearance;
    }

    public void setAppearance(char appearance) {
        this.appearance = appearance;
    }
    //endregion
}
