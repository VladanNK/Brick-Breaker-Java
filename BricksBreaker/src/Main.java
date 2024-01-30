import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        JFrame obj = new JFrame();
        MainGame mainGame = new MainGame();
        obj.setBounds(10, 10, 700, 700);
        obj.setTitle("Bricks Breaker");
        obj.setSize(700, 700);
        obj.setResizable(false);
        obj.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        obj.setVisible(true);
        obj.add(mainGame);
    }
}