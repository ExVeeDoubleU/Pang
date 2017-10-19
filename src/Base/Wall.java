package Base;

import com.googlecode.lanterna.terminal.Terminal;

import java.util.List;
import java.util.Random;

class Wall {
    Random rand = new Random();
    private int x;
    private int y;
    private int nextX;
    private int nextY;
    private int boundX;
    private int boundY;
    private int boundModifier;
    private char appearance;

    Wall(int x, int y, int boundX, int boundY, int boundModifier, char appearance) {
        this.x = x;
        this.y = y;
        this.boundX = boundX;
        this.boundY = boundY;
        this.appearance = appearance;
        this.boundModifier = boundModifier;
    }

    void draw(int x, int y, Terminal terminal){
        terminal.moveCursor(x, y);
        terminal.putCharacter(appearance);
    }
    void undraw(int x, int y, Terminal terminal){
        terminal.moveCursor(x, y);
        terminal.putCharacter(' ');
    }
    void move(){
        this.x = this.nextX;
        this.y = this.nextY;
    }
    void nextPos(){
        this.nextX = rand.nextInt(boundX) + boundModifier;
        this.nextY = rand.nextInt(boundY) + 6;
    }
void blockLaser(List<Laser> lasers, List<Laser> removeList, Terminal terminal){
    for (Laser l:lasers) {
        if (l.getX() == this.x && l.getY() == this.y){
            removeList.add(l);
            this.undraw(this.x, this.y, terminal);
            this.nextPos();
            this.move();
            this.draw(this.x, this.y, terminal);
        }

    }
}
//region Get Set
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

    public int getBoundX() {
        return boundX;
    }

    public void setBoundX(int boundX) {
        this.boundX = boundX;
    }

    public int getBoundY() {
        return boundY;
    }

    public void setBoundY(int boundY) {
        this.boundY = boundY;
    }

    public char getAppearance() {
        return appearance;
    }

    public void setAppearance(char appearence) {
        this.appearance = appearence;
    }

    public int getBoundModifier() {
        return boundModifier;
    }

    public void setBoundModifier(int boundModifier) {
        this.boundModifier = boundModifier;
    }
    //endregion
}
