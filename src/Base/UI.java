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
            terminal.putCharacter('\u2588');
        }
        for (int i = 0; i < 87; i++) {
            terminal.moveCursor(i, 29);
            terminal.putCharacter('\u2588');
        }


    }

    public void drawScoreBoard(Player p1, Player p2) {
        int i1 = p1.getHp();
        char c1 = Character.forDigit(i1, 10);
        int i2 = p2.getHp();
        char c2 = Character.forDigit(i2, 10);
        if (p1.getHp() <= 0) {
            terminal.moveCursor(9, 3);
            terminal.putCharacter('0');
        } else {
            terminal.moveCursor(9, 3);
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
    public void drawString(int startX, int startY, boolean isHorizontal, String string){

        if(isHorizontal){
            for (int i = 0; i < string.length(); i++) {
                terminal.moveCursor(startX+i, startY);
                terminal.putCharacter(string.charAt(i));
            }

        }
        if(!isHorizontal){
            for (int i = 0; i < string.length(); i++) {
                terminal.moveCursor(startX, startY+i);
                terminal.putCharacter(string.charAt(i));
            }

        }

    }
    public void menuDrawString() {
        terminal.applyForegroundColor(Terminal.Color.GREEN);
        drawString(31, 8, true, " _____      __      ___      __     ");
        drawString(31, 9, true, "/\\  __`\\  / __`\\  /  _ `\\  / _ `\\   ");
        drawString(31, 10, true, "\\ \\ \\_\\ \\/\\ \\_\\ \\_/\\ \\/\\ \\/\\ \\_\\ \\  ");
        drawString(31, 11, true, " \\ \\  __/\\ \\__/ \\_\\ \\_\\ \\_\\ \\____ \\ ");
        drawString(31, 12, true, "  \\ \\ \\/  \\/__/\\/_/\\/_/\\/_/\\/___ \\ \\");
        drawString(31, 13, true, "   \\ \\_\\                     /\\____/");
        drawString(31, 14, true, "    \\/_/      by mojoroj     \\_/__/ ");
        terminal.applyForegroundColor(Terminal.Color.DEFAULT);
        drawString(41, 17, true, "Push Enter to start");
        drawString(45, 19, true, "Esc to quit");
        drawString(47, 24, true, "Controls");
        drawString(44, 26, false, "SXW");
        drawString(56, 26, false, "OKM");
        drawString(50, 26, false, "▲▼");
        drawString(50, 28, true, "\u25a0");


    }

    public void uiDrawString() {
        drawString(2, 2, true, "Player 1");
        drawString(90, 2, true, "Player 2");
        drawString(2, 3, true, "Lives: ");
        drawString(90, 3, true, "Lives: ");
        drawString(31, 13, true, " _____      __      ___      __     ");
        drawString(31, 14, true, "/\\  __`\\  / __`\\  /  _ `\\  / _ `\\   ");
        drawString(31, 15, true, "\\ \\ \\_\\ \\/\\ \\_\\ \\_/\\ \\/\\ \\/\\ \\_\\ \\  ");
        drawString(31, 16, true, " \\ \\  __/\\ \\__/ \\_\\ \\_\\ \\_\\ \\____ \\ ");
        drawString(31, 17, true, "  \\ \\ \\/  \\/__/\\/_/\\/_/\\/_/\\/___ \\ \\");
        drawString(31, 18, true, "   \\ \\_\\                     /\\____/");
        drawString(31, 19, true, "    \\/_/      by mojoroj     \\_/__/ ");
        drawString(88, 29, true, "Esc to quit");
    }

}
