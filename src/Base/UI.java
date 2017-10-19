package Base;

import com.googlecode.lanterna.TerminalFacade;
import com.googlecode.lanterna.terminal.Terminal;

import java.nio.charset.Charset;

class UI {

    Terminal terminal = TerminalFacade.createTerminal(System.in, System.out, Charset.forName("UTF-8"));

    void createTerminal() {
        terminal.setCursorVisible(false);
        terminal.enterPrivateMode();
    }

    void draw(Player player) {
        player.drawBody(terminal);
    }

    private void drawString(int startX, int startY, boolean isHorizontal, String string) {

        if (isHorizontal) {
            for (int i = 0; i < string.length(); i++) {
                terminal.moveCursor(startX + i, startY);
                terminal.putCharacter(string.charAt(i));
            }

        }
        if (!isHorizontal) {
            for (int i = 0; i < string.length(); i++) {
                terminal.moveCursor(startX, startY + i);
                terminal.putCharacter(string.charAt(i));
            }

        }

    }

    void drawField(Terminal terminal) {
        terminal.applyBackgroundColor(Terminal.Color.WHITE);
        for (int i = 0; i < 100; i++) {
            terminal.moveCursor(i, 5);
            terminal.putCharacter(' ');
        }
        for (int i = 0; i < 100; i++) {
            terminal.moveCursor(i, 29);
            terminal.putCharacter(' ');
        }
        terminal.applyBackgroundColor(Terminal.Color.DEFAULT);


    }

    void drawScoreBoard(Player p1, Player p2) {
        int i1 = p1.getHp();
        char c1 = Character.forDigit(i1, 10);
        int i2 = p2.getHp();
        char c2 = Character.forDigit(i2, 10);
        if (p1.getHp() <= 0) {
            terminal.moveCursor(9, 3);
        } else {
            terminal.moveCursor(9, 3);
            terminal.putCharacter(c1);
        }
        if (p2.getHp() <= 0) {
            terminal.moveCursor(96, 3);
        } else {
            terminal.moveCursor(96, 3);
            terminal.putCharacter(c2);
        }
    }

    void winDrawString(boolean isPlayer1, Terminal terminal) {
        if (isPlayer1) {
            for (int y = 0; y < 30; y++) {
                for (int x = 0; x < 100; x++) {
                    terminal.moveCursor(x, y);
                    terminal.applyBackgroundColor(Terminal.Color.RED);
                    terminal.putCharacter(' ');


                }

            }
            terminal.applyForegroundColor(Terminal.Color.BLACK);
            drawString(31, 13, true, " _____      __      ___      __     ");
            drawString(31, 14, true, "/\\  __`\\  / __`\\  /  _ `\\  / _ `\\   ");
            drawString(31, 15, true, "\\ \\ \\_\\ \\/\\ \\_\\ \\_/\\ \\/\\ \\/\\ \\_\\ \\  ");
            drawString(31, 16, true, " \\ \\  __/\\ \\__/ \\_\\ \\_\\ \\_\\ \\____ \\ ");
            drawString(31, 17, true, "  \\ \\ \\/  \\/__/\\/_/\\/_/\\/_/\\/___ \\ \\");
            drawString(31, 18, true, "   \\ \\_\\                     /\\____/");
            drawString(31, 19, true, "    \\/_/      by mojoroj     \\_/__/ ");
            drawString(42, 10, true, "Player 1 wins!");
            terminal.applyForegroundColor(Terminal.Color.DEFAULT);
            terminal.applyBackgroundColor(Terminal.Color.DEFAULT);
        } else {
            for (int y = 0; y < 30; y++) {
                for (int x = 0; x < 100; x++) {
                    terminal.moveCursor(x, y);
                    terminal.applyBackgroundColor(Terminal.Color.CYAN);
                    terminal.putCharacter(' ');


                }

            }
            terminal.applyForegroundColor(Terminal.Color.BLACK);
            drawString(31, 13, true, " _____      __      ___      __     ");
            drawString(31, 14, true, "/\\  __`\\  / __`\\  /  _ `\\  / _ `\\   ");
            drawString(31, 15, true, "\\ \\ \\_\\ \\/\\ \\_\\ \\_/\\ \\/\\ \\/\\ \\_\\ \\  ");
            drawString(31, 16, true, " \\ \\  __/\\ \\__/ \\_\\ \\_\\ \\_\\ \\____ \\ ");
            drawString(31, 17, true, "  \\ \\ \\/  \\/__/\\/_/\\/_/\\/_/\\/___ \\ \\");
            drawString(31, 18, true, "   \\ \\_\\                     /\\____/");
            drawString(31, 19, true, "    \\/_/      by mojoroj     \\_/__/ ");
            drawString(42, 10, true, "Player 2 wins!");
            terminal.applyForegroundColor(Terminal.Color.DEFAULT);
            terminal.applyBackgroundColor(Terminal.Color.DEFAULT);

        }

    }

    void menuDrawString() {
        drawString(0, 29, true, "by Max, Jesper, Robin & John");
        drawString(31, 13, true, " _____      __      ___      __     ");
        drawString(31, 14, true, "/\\  __`\\  / __`\\  /  _ `\\  / _ `\\   ");
        drawString(31, 15, true, "\\ \\ \\_\\ \\/\\ \\_\\ \\_/\\ \\/\\ \\/\\ \\_\\ \\  ");
        drawString(31, 16, true, " \\ \\  __/\\ \\__/ \\_\\ \\_\\ \\_\\ \\____ \\ ");
        drawString(31, 17, true, "  \\ \\ \\/  \\/__/\\/_/\\/_/\\/_/\\/___ \\ \\");
        drawString(31, 18, true, "   \\ \\_\\                     /\\____/");
        drawString(31, 19, true, "    \\/_/      by mojoroj     \\_/__/ ");
        drawString(41, 23, true, "Push Enter to start");
        drawString(45, 25, true, "Esc to quit");
        drawString(79, 2, true, "Controls");
        drawString(70, 4, true, "Player 1");
        terminal.applyForegroundColor(Terminal.Color.RED);
        drawString(79, 4, true, "■");
        terminal.applyForegroundColor(Terminal.Color.CYAN);
        drawString(85, 4, true, "■");
        terminal.applyForegroundColor(Terminal.Color.DEFAULT);
        drawString(87, 4, true, "Player 2");
        drawString(75, 6, false, "SXW");
        drawString(89, 6, false, "OKM");
        drawString(82, 6, false, "▲▼");
        drawString(82, 8, true, "■");


    }

    void uiDrawString() {
        drawString(2, 2, true, "Player 1");
        terminal.applyForegroundColor(Terminal.Color.RED);
        drawString(11, 2, true, "■");
        terminal.applyForegroundColor(Terminal.Color.CYAN);
        drawString(87, 2, true, "■");
        terminal.applyForegroundColor(Terminal.Color.DEFAULT);
        drawString(89, 2, true, "Player 2");
        drawString(2, 3, true, "Lives: ");
        drawString(89, 3, true, "Lives: ");
        drawString(31, 13, true, " _____      __      ___      __     ");
        drawString(31, 14, true, "/\\  __`\\  / __`\\  /  _ `\\  / _ `\\   ");
        drawString(31, 15, true, "\\ \\ \\_\\ \\/\\ \\_\\ \\_/\\ \\/\\ \\/\\ \\_\\ \\  ");
        drawString(31, 16, true, " \\ \\  __/\\ \\__/ \\_\\ \\_\\ \\_\\ \\____ \\ ");
        drawString(31, 17, true, "  \\ \\ \\/  \\/__/\\/_/\\/_/\\/_/\\/___ \\ \\");
        drawString(31, 18, true, "   \\ \\_\\                     /\\____/");
        drawString(31, 19, true, "    \\/_/      by mojoroj     \\_/__/ ");
        terminal.applyBackgroundColor(Terminal.Color.WHITE);
        terminal.applyForegroundColor(Terminal.Color.BLACK);
        drawString(88, 29, true, "Esc to quit");
        drawString(0, 29, true, "by Max, Jesper, Robin & John");
        terminal.applyForegroundColor(Terminal.Color.DEFAULT);
        terminal.applyBackgroundColor(Terminal.Color.DEFAULT);
    }

}
