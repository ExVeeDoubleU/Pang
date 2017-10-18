package Base;

import com.googlecode.lanterna.input.Key;
import com.googlecode.lanterna.terminal.Terminal;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;


public class Main {
    static Key key;

    public static void main(String[] args) throws InterruptedException {
        UI ui = new UI();
        ui.createTerminal();
        while(true){
            menu(ui);
            ui.terminal.clearScreen();
            Game(ui);
            ui.terminal.clearScreen();
        }
    }
    public static void menu(UI ui) throws InterruptedException{
        boolean inMenu = true;
        while(inMenu){
            do{
                Thread.sleep(20);
                key = ui.terminal.readInput();
            }while(key == null);
            switch (key.getKind()) {
                case Escape:
                    System.exit(0);
                    break;
                case ArrowUp:
                    break;
                case ArrowDown:
                    break;
                case Enter:
                case ArrowRight:
                    inMenu = false;
                    break;
            }
        }
    }


    public static void Game(UI ui) throws InterruptedException {
        Player p1 = new Player(1, 17, 1, 5, 3, 1, '\u2588');
        Player p2 = new Player(98, 17, 1, 5, 3, 2, '\u2588');
        p1.createBody();
        p2.createBody();
        List<Laser> lasers = new ArrayList<>();
        List<Laser> removeList = new ArrayList<>();
        while (!someoneDead(p1, p2)) {
            do {
//                ui.terminal.clearScreen();
                checkLasers(lasers, removeList);
                moveLasers(lasers, removeList, ui.terminal);
                hitsTarget(lasers, p1, p2);
                ui.drawScoreBoard(p1, p2);
                ui.terminal.applyBackgroundColor(Terminal.Color.WHITE);
                ui.drawField();
                ui.terminal.applyBackgroundColor(Terminal.Color.DEFAULT);
                ui.uiDrawString();
                draw(ui, p1, p2, lasers);
                Thread.sleep(20);
                key = ui.terminal.readInput();
            } while (key == null);
            switch (key.getKind()) {
                case Escape: {
                    return;
                }
                case NormalKey: {
                    switch (key.getCharacter()) {
                        case 'x':
                            p1.moveDown(ui.terminal);
                            break;
                        case 's':
                            p1.moveUp(ui.terminal);
                            break;
                        case 'w':
                            p1.shoot(p1.getX() + 1, 1, lasers, '\u25a0');
                            break;
                        case 'k':
                            p2.moveDown(ui.terminal);
                            break;
                        case 'o':
                            p2.moveUp(ui.terminal);
                            break;
                        case 'm':
                            p2.shoot(p2.getX() - 1, -1, lasers, '\u25a0');

                    }
                }
            }
            System.out.println(key.getKind() + " " + key.getCharacter());
        }
        ui.terminal.clearScreen();
        menu(ui);
    }

    public static void draw(UI ui, Player p1, Player p2, List<Laser> lL) {
        ui.terminal.applyBackgroundColor(Terminal.Color.WHITE);
        ui.draw(p1);
        ui.draw(p2);
        ui.terminal.applyBackgroundColor(Terminal.Color.DEFAULT);

        for (Laser l : lL) {
            l.draw(ui.terminal);
        }
    }

    public static void moveLasers(List<Laser> lL, List<Laser> removeList, Terminal terminal) {
        for (Laser l : lL) {
            l.move(terminal);
            l.hitsLaser(lL, removeList);
        }
        for (int i = removeList.size() - 1; i >= 0; i--) {
            for (int j = lL.size() - 1; j >= 0; j--) {
                if (removeList.get(i) == lL.get(j)) {
                    lL.remove(j);
                }

            }
            removeList.remove(i);

        }
    }

    public static void checkLasers(List<Laser> lL, List<Laser> removeList) {
        for (int i = lL.size() - 1; i >= 0; i--) {

            if (lL.get(i).getX() < 0 || lL.get(i).getX() > 100) {
                removeList.add(lL.get(i));
            }

        }

    }


    public static void hitsTarget(List<Laser> lL, Player p1, Player p2) {
        for (Laser l : lL) {
            l.hitsTarget(p1, p2);


        }

    }
    public static boolean someoneDead(Player p1, Player p2){
        if(p1.getHp() > 0 || p2.getHp() > 0)
            return false;
        else
            return true;
    }
}
