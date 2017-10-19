package Base;


import com.googlecode.lanterna.terminal.Terminal;

 @SuppressWarnings("ALL")
 class Body {

    private int x;
    private int y;
    private int speed;
    private char appearance;

    Body(int x, int y, int speed, char appearance) {
        this.x = x;
        this.y = y;
        this.speed = speed;
        this.appearance = appearance;
    }
    void draw(Terminal terminal){
        terminal.moveCursor(getX(), getY());
        terminal.putCharacter(getAppearance());
    }
    void unDraw(Terminal terminal){
        terminal.moveCursor(getX(), getY());
        terminal.putCharacter(' ');
    }
//region getset
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

     char getAppearance() {
        return appearance;
    }

     void setAppearance(char appearance) {
        this.appearance = appearance;
    }
    //endregion
}
