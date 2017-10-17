package Base;

import com.googlecode.lanterna.TerminalFacade;
import com.googlecode.lanterna.terminal.Terminal;


import java.nio.charset.Charset;

public class UI {

    Terminal terminal = TerminalFacade.createTerminal(System.in, System.out, Charset.forName("UTF-8"));

    public void createTerminal() {
        terminal.setCursorVisible(false);
        terminal.enterPrivateMode();
    }

    public void draw(Player player) {
        player.drawBody(terminal);
    }

    public void drawField() {
        for (int i = 0; i < 100; i++) {
            terminal.moveCursor(i, 5);
            terminal.putCharacter('-');
        }
        for (int i = 0; i < 100; i++) {
            terminal.moveCursor(i, 29);
            terminal.putCharacter('-');
        }


    }

    public void drawScoreBoard(Player p1, Player p2) {
        int i1 = p1.getHp();
        char c1 = Character.forDigit(i1, 10);
        int i2 = p2.getHp();
        char c2 = Character.forDigit(i2, 10);
        if (p1.getHp() <= 0) {
            terminal.moveCursor(2, 3);
            terminal.putCharacter('0');
        } else {
            terminal.moveCursor(2, 3);
            terminal.putCharacter(c1);
        }
        if (p2.getHp() <= 0) {
            terminal.moveCursor(97, 3);
            terminal.putCharacter('0');
        } else {
            terminal.moveCursor(97, 3);
            terminal.putCharacter(c2);
        }
    }
}
