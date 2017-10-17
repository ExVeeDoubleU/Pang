package Base;

import com.googlecode.lanterna.input.Key;

import java.util.ArrayList;
import java.util.List;


public class Main {
    static Key key;

    public static void main(String[] args) throws InterruptedException {
        UI ui = new UI();
        Player p1 = new Player(1, 13, 1, 5, 3, 1, 'X');
        Player p2 = new Player(98, 13, 1, 5, 3, 2, 'O');
        ui.createTerminal();
        p1.createBody();
        p2.createBody();

        List<Laser> lasers = new ArrayList<>();


        while (true) {
            do {
                ui.terminal.clearScreen();
                checkLasers(lasers);
                moveLasers(lasers);
                hitsTarget(lasers, p1, p2);
                ui.drawScoreBoard(p1, p2);
                ui.drawField();
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
                            p1.shoot(p1.getX() + 1, 1, lasers, '\u27FE');
                            break;
                        case 'k':
                            p2.moveDown();
                            break;
                        case 'o':
                            p2.moveUp();
                            break;
                        case 'm':
                            p2.shoot(p2.getX() - 1, -1, lasers, '\u27FD');

                    }
                }
            }
            System.out.println(key.getKind() + " " + key.getCharacter());
        }
    }

    public static void draw(UI ui, Player p1, Player p2, List<Laser> lL) {
        ui.draw(p1);
        ui.draw(p2);

        for (Laser l : lL) {
            l.draw(ui.terminal);
        }
    }

    public static void moveLasers(List<Laser> lL) {
        for (Laser l : lL) {
            l.move();
        }
    }

    public static void checkLasers(List<Laser> lL) {
        for (int i = lL.size() - 1; i >= 0; i--) {

            if (lL.get(i).getX() < 0 || lL.get(i).getX() > 100) {
                lL.remove(i);
            }

        }
    }

    public static void hitsTarget(List<Laser> lL, Player p1, Player p2) {
        for (Laser l : lL) {
            l.hitsTarget(p1, p2);


        }

    }
}
