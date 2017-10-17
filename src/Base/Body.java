package Base;


import com.googlecode.lanterna.terminal.Terminal;

public class Body {

    private int x;
    private int y;
    private int nextX;
    private int nextY;
    private int speed;
    private int playerNumber;
    private char appearence;

    public Body(int x, int y, int speed, int playerNumber, char appearence) {
        this.x = x;
        this.y = y;
        this.nextX = x;
        this.nextY = y;
        this.speed = speed;
        this.playerNumber = playerNumber;
        this.appearence = appearence;
    }
    public void draw(Terminal terminal){
        terminal.moveCursor(getX(), getY());
        terminal.putCharacter(getAppearence());
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

    public char getAppearence() {
        return appearence;
    }

    public void setAppearence(char appearence) {
        this.appearence = appearence;
    }
    //endregion
}
