import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class MainGame extends JPanel implements ActionListener, KeyListener {
    private int score = 0;
    private int playerPosX = 350;
    private int pozicijaLoptePoX = 350;
    private int pozicijaLoptePoY = 500;
    private boolean igraSe = true;
    private int brojBlokova = 24;
    private Timer timer;
    private int delay = 5;
    private MainScreen mainScreen;
    private int udaracLopticePoX = -1;
    private int udaracLopticePoY = -2;

    public MainGame(){
        mainScreen = new MainScreen(3, 8);
        setFocusable(true);
        setFocusTraversalKeysEnabled(false);
        addKeyListener(this);
        timer = new Timer(delay, this);
        timer.start();

    }
    public void paint(Graphics g){
        g.setColor(Color.GRAY);
        g.fillRect(1, 1, 700, 700);

        mainScreen.draw((Graphics2D) g);

        /*g.fillRect(0, 0, 3, 592);
        g.fillRect(0, 0, 692, 3);
        g.fillRect(691, 0, 3, 592);*/

        g.setColor(Color.orange);
        g.fillOval(pozicijaLoptePoX, pozicijaLoptePoY, 30, 30);

        g.setColor(Color.green);
        g.fillRect(playerPosX, 600, 100, 40);

        g.setFont(new Font("Arial", Font.BOLD, 35));
        g.setColor(Color.black);
        g.drawString("Score: " + score, 500, 50);

        if(brojBlokova <= 0){
            igraSe = false;
            udaracLopticePoX = 0;
            udaracLopticePoY = 0;
            g.setColor(Color.black);
            g.setFont(new Font("Times New Roman", Font.BOLD, 40));
            g.drawString("Pobedio si! Score: " + score, 200, 350);

            g.setFont(new Font("Arial", Font.BOLD, 20));
            g.drawString("Pritisni ENTER da igras ponovo!", 40, 400);
        }
        if(pozicijaLoptePoY >= 700){
            igraSe = false;
            udaracLopticePoX = 0;
            udaracLopticePoY = 0;
            g.setColor(Color.black);
            g.setFont(new Font("Arial", Font.BOLD, 40));
            g.drawString("Izgubio si! Score: " + score, 200, 350);
            g.drawString("Pristisni ENTER da igras ponovo!", 40, 400);
        }
        g.dispose();
    }
    public void actionPerformed(ActionEvent event){
        timer.start();
        if(igraSe){
            if(new Rectangle(pozicijaLoptePoX, pozicijaLoptePoY, 30, 30).intersects(new Rectangle(playerPosX, 600, 100, 40))){
                udaracLopticePoY = -udaracLopticePoY;
            }
            for (int i = 0; i < mainScreen.matricaBlokova.length; ++i) {
                for (int j = 0; j < mainScreen.matricaBlokova[0].length; ++j){
                    if(mainScreen.matricaBlokova[i][j] > 0){
                        int blokX = j * mainScreen.sirinaBloka + 80;
                        int blokY = i * mainScreen.visinaBloka + 50;
                        int blokSirina = mainScreen.sirinaBloka;
                        int blokVisina = mainScreen.visinaBloka;

                        Rectangle loptica = new Rectangle(pozicijaLoptePoX, pozicijaLoptePoY, 30, 30);
                        Rectangle blok = new Rectangle(blokX, blokY, blokSirina, blokVisina);
                        Rectangle blokRect = blok;

                        if(blokRect.intersects(loptica)){
                            mainScreen.pocetnaVrednostBloka(i, j, 0);
                            udaracLopticePoY = -udaracLopticePoY;
                            brojBlokova--;
                            score = score + 10;
                        }
                    }
                }
            }
        }
        pozicijaLoptePoX += udaracLopticePoX;
        pozicijaLoptePoY += udaracLopticePoY;
        if(pozicijaLoptePoY < 0){
            udaracLopticePoY = -udaracLopticePoY;
        }
        if(pozicijaLoptePoX < 0){
            udaracLopticePoX = -udaracLopticePoX;
        }
        if(pozicijaLoptePoX > 650){
            udaracLopticePoX = -udaracLopticePoX;
        }
        repaint();
    }
    public void keyTyped(KeyEvent event1){

    }
    public void keyPressed(KeyEvent event1){
        if(event1.getKeyCode() == KeyEvent.VK_ENTER){
            igraSe = true;
            score = 0;
            playerPosX = 350;
            pozicijaLoptePoX = 350;
            pozicijaLoptePoY = 500;
            brojBlokova = 20;
            udaracLopticePoX = -1;
            udaracLopticePoY = -2;
            mainScreen = new MainScreen(3, 7);

            repaint();
        }
        if(event1.getKeyCode() == KeyEvent.VK_RIGHT){
            if(playerPosX >= 600){
                igraSe = true;
                playerPosX = 600;
            }else {
                idiDesno();
            }
        }
        if(event1.getKeyCode() == KeyEvent.VK_LEFT){
            if(playerPosX <= 0){
                igraSe=true;
                playerPosX = 0;
            }else{
                idiLevo();
            }
        }

    }
    public void idiLevo(){
        playerPosX -= 30;
    }
    public void idiDesno(){
        playerPosX += 30;
    }
    public void keyReleased(KeyEvent event1){

    }
}
