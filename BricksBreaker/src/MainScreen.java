import javax.swing.*;
import java.awt.*;

public class MainScreen {
    public int sirinaBloka;
    public int visinaBloka;
    public int[][] matricaBlokova;

    public MainScreen(int rows, int cols){
        matricaBlokova = new int[rows][cols];
        for(int i = 0; i < matricaBlokova.length; ++i){
            for (int j = 0; j < matricaBlokova[0].length; ++j){
                matricaBlokova[i][j] = 1;
            }
        }
        sirinaBloka = 550 / cols;
        visinaBloka = 150 / rows;
    }
    public void draw(Graphics2D g){
        for (int i = 0; i < matricaBlokova.length; ++i){
            for(int j = 0; j < matricaBlokova[0].length; ++j){
                if(matricaBlokova[i][j] > 0) {
                    g.setColor(Color.cyan);
                    g.fillRect(j * sirinaBloka + 80, i * visinaBloka + 50, sirinaBloka, visinaBloka);
                    g.setStroke(new BasicStroke(3));
                    g.setColor(Color.black);
                    g.drawRect(j * sirinaBloka + 80, i * visinaBloka + 50, sirinaBloka, visinaBloka);
                }
            }
        }
    }
    public void pocetnaVrednostBloka(int rows, int cols, int vrednost){
        matricaBlokova[rows][cols] = vrednost;
    }
}
