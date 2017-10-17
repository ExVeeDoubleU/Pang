package Base;

import com.googlecode.lanterna.input.Key;
import com.googlecode.lanterna.terminal.Terminal;

import java.util.ArrayList;
import java.util.List;


public class Main {
    static Key key;

    public static void main(String[] args) throws InterruptedException {
        UI ui = new UI();
        Player p1 = new Player(1, 17, 1, 5, 3, 1, '\u2588');
        Player p2 = new Player(98, 17, 1, 5, 3, 2, '\u2588');
        ui.createTerminal();
        ui.terminal.applyBackgroundColor(Terminal.Color.WHITE);
        p1.createBody();
        p2.createBody();

        ui.terminal.applyBackgroundColor(Terminal.Color.DEFAULT);

        List<Laser> lasers = new ArrayList<>();
        List<Laser> removeList = new ArrayList<>();


        while (true) {
            do {
                ui.terminal.clearScreen();
                checkLasers(lasers, removeList);
                moveLasers(lasers);
                hitsTarget(lasers, p1, p2);
                removeLasers(removeList, lasers);
                ui.drawScoreBoard(p1, p2);
                ui.terminal.applyBackgroundColor(Terminal.Color.WHITE);
                ui.drawField();
                ui.terminal.applyBackgroundColor(Terminal.Color.DEFAULT);
                draw(ui, p1, p2, lasers);
                Thread.sleep(50);
                key = ui.terminal.readInput();
            } while (key == null);
            switch (key.getKind()) {
                case NormalKey: {
                    switch (key.getCharacter()) {
                        case 'x':
                            p1.moveDown();
                            break;
                        case 's':
                            p1.moveUp();
                            break;
                        case 'w':
                            p1.shoot(p1.getX() + 1, 1, lasers, '\u25a0');
                            break;
                        case 'k':
                            p2.moveDown();
                            break;
                        case 'o':
                            p2.moveUp();
                            break;
                        case 'm':
                            p2.shoot(p2.getX() - 1, -1, lasers, '\u25a0');

                    }
                }
            }
            System.out.println(key.getKind() + " " + key.getCharacter());
        }
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

    public static void moveLasers(List<Laser> lL) {
        for (Laser l : lL) {
            l.move();
        }
    }

    public static void checkLasers(List<Laser> lL, List<Laser> removeList) {
        for (int i = lL.size() - 1; i >= 0; i--) {

            if (lL.get(i).getX() < 0 || lL.get(i).getX() > 100) {
                removeList.add(lL.get(i));
            }

        }

    }
    public static void removeLasers(List<Laser> removeList, List<Laser> lasers){
        for (Laser lZ: lasers) {
            lZ.hitsLaser(lasers, removeList);

        }
        for (int i = removeList.size()-1; i >= 0; i--) {
            for (int j = lasers.size()-1; j >=0 ; j--) {
                if (removeList.get(i) == lasers.get(j)){
                    lasers.remove(j);
                }

            }
            removeList.remove(i);

        }
    }

    public static void hitsTarget(List<Laser> lL, Player p1, Player p2) {
        for (Laser l : lL) {
            l.hitsTarget(p1, p2);


        }

    }
}
