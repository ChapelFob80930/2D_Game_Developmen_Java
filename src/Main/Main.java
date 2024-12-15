// Learned from RyiSnow (https://youtube.com/playlist?list=PL_QPQmz5C6WUF-pOQDsbsKbaBZqXj4qSq&si=W3-kLM-SIfxPHTd_)


package Main;

import javax.swing.*;

public class Main {
    public static void main(String[] arg){
        JFrame window=new JFrame();
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setResizable(false);
        window.setTitle("2D adventure");

        gamePanel GamePanel = new gamePanel();
        window.add(GamePanel);

        window.pack();

        window.setLocationRelativeTo(null);
        window.setVisible(true);

        GamePanel.startGameThread();
    }
}
