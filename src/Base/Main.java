package Base;

import com.googlecode.lanterna.input.Key;
import com.googlecode.lanterna.terminal.Terminal;

import java.util.ArrayList;
import java.util.List;


class Main {
    private static Key key;

    @SuppressWarnings("InfiniteLoopStatement")
    public static void main(String[] args) throws InterruptedException {
        UI ui = new UI(); //Instantiate UI.
        MP3Player mp3 = new MP3Player();//Instantiate Audio.
        ui.createTerminal();// Create terminal window.
        while (true) { // This is the main loop, which keeps the game running by switching between the menu- and game-loop.
            menu(ui, mp3); // Start menu loop.
            ui.terminal.clearScreen(); // Clears screen so that nothing from the previous screen carries over to the new loop.
            game(ui, mp3); // Start game loop.
            ui.terminal.clearScreen(); // Clears screen so that nothing from the previous screen carries over to the new loop.
        }
    }

    //MENU
    private static void menu(UI ui, MP3Player mp3) throws InterruptedException {
        //noinspection SpellCheckingInspection
        mp3.play("WLTP.wav", true); //Play background music on repeat.
        boolean inMenu = true;

        while (inMenu) {
            do {
                ui.menuDrawString();//Draw the menu as specified in UI class.
                Thread.sleep(20);
                key = ui.terminal.readInput();
            } while (key == null); //If key is pressed, exit loop.
            switch (key.getKind()) {
                case Escape:
                    System.exit(0);//Close program.
                    break;
                case Enter:
                    inMenu = false;
                    break;
            }
        }
        key = null;
    }

    //GAME
    private static void game(UI ui, MP3Player mp3) throws InterruptedException {
        Player p1 = new Player(1, 17, 1, 5, 6, 'X'); //Instantiate player 1
        Player p2 = new Player(98, 17, 1, 5, 6, 'O');// Instantiate player 2
        p1.createBody();//Create paddle of player 1
        p2.createBody();//Create paddle of player 2
        List<Laser> lasers = new ArrayList<>(); // Create list to store all laser objects.
        List<Laser> removeList = new ArrayList<>();// Create list to store laser objects that are waiting to be removed.
        ui.drawField(ui.terminal); // Draw playing field as specified in UI class.

        while (!someoneDead(p1, p2)) { // As long as nobody is dead, loop.

            do {
                checkLasers(lasers, removeList); // Check if any laser objects are outside the play area, if so, add them to removeList.
                moveLasers(lasers, removeList, ui.terminal); // Move all lasers. Remove all lasers that are stored in removeList.
                hitsTarget(lasers, p1, p2, mp3, removeList);// Check if any lasers hits a paddle and decrease health and play explosion sound if they do.
                ui.drawScoreBoard(p1, p2);//Update remaining health of players.
                ui.uiDrawString();//Draw GUI as specified in UI class.
                draw(ui, p1, p2, lasers);//Draw lasers and paddles.
                Thread.sleep(20);//Wait for 20 ms before proceeding.
                key = ui.terminal.readInput();//Get what key is pressed.
                //If someone is dead, show a victory screen depending on who wins,then return from method.
                if (someoneDead(p1, p2)) {
                    winScreen(ui, mp3, p1, p2);
                    Thread.sleep(2000);
                    return;
                }
            } while (key == null); //If a key is pressed, exit loop.
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
                            mp3.playFX("Pew.wav");//Play player 1's sound effect for firing a laser.
                            p1.shoot(p1.getX() + 1, 1, lasers, '\u25a0');//Create laser objects for player 1.
                            break;
                        case 'k':
                            p2.moveDown(ui.terminal);
                            break;
                        case 'o':
                            p2.moveUp(ui.terminal);
                            break;
                        case 'm':
                            mp3.playFX("Pou.wav");//Play player 2's sound effect for firing a laser.
                            p2.shoot(p2.getX() - 1, -1, lasers, '\u25a0');//Create laser objects for player 2.

                    }
                }
            }
            System.out.println(key.getKind() + " " + key.getCharacter());//Debug to check pressed keys.
        }
        //Safety net in-case loop is broken here.
        ui.terminal.clearScreen();
        key = null;
        menu(ui, mp3);
    }

    //Draws player paddles and lasers
    private static void draw(UI ui, Player p1, Player p2, List<Laser> lL) {
        ui.terminal.applyForegroundColor(Terminal.Color.RED);
        ui.terminal.applyBackgroundColor(Terminal.Color.RED);
        ui.draw(p1);
        ui.terminal.applyBackgroundColor(Terminal.Color.CYAN);
        ui.terminal.applyForegroundColor(Terminal.Color.CYAN);
        ui.draw(p2);
        ui.terminal.applyForegroundColor(Terminal.Color.DEFAULT);
        ui.terminal.applyBackgroundColor(Terminal.Color.DEFAULT);


        for (Laser l : lL) {
            l.draw(ui.terminal);
        }
    }

    //Update position of lasers, check if lasers collide with each other and remove lasers that are no longer in play.
    private static void moveLasers(List<Laser> lL, List<Laser> removeList, Terminal terminal) {
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

    //Check if lasers have left the play area.
    private static void checkLasers(List<Laser> lL, List<Laser> removeList) {
        for (int i = lL.size() - 1; i >= 0; i--) {

            if (lL.get(i).getX() < 0 || lL.get(i).getX() > 100) {
                removeList.add(lL.get(i));
            }

        }

    }

    //Check if a laser collides with a paddle, if yes, play explosion sound, decrease health of the player hit and add the laser to the removalList
    private static void hitsTarget(List<Laser> lL, Player p1, Player p2, MP3Player mp3, List<Laser> removeList) {
        for (Laser l : lL) {
            l.hitsTarget(p1, p2, mp3, removeList);

        }

    }

    //Check if someone is dead.
    private static boolean someoneDead(Player p1, Player p2) {
        return (p1.getHp() <= 0 || p2.getHp() <= 0);
    }

    //Create a victory screen and play victory sound, varies depending on who wins.
    private static void winScreen(UI ui, MP3Player mp3, Player p1, Player p2) {
        ui.terminal.clearScreen();
        if (p1.getHp() <= 0) {
            mp3.play("Yay.wav");
            ui.winDrawString(false, ui.terminal);
        } else if (p2.getHp() <= 0) {
            mp3.play("Yey.wav");
            ui.winDrawString(true, ui.terminal);
        }
    }
}
