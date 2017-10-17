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
    public void draw(Player player){
        player.drawBody(terminal);
    }
    public void drawField(){
        for (int i = 0; i < 100 ; i++) {
            terminal.moveCursor(i, 5);
            terminal.putCharacter('-');
        }
        for (int i = 0; i < 100 ; i++) {
            terminal.moveCursor(i, 29);
            terminal.putCharacter('-');
        }
    }

}
