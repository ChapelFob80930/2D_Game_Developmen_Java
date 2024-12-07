package Main;

import javax.swing.*;
import java.awt.*;

public class gamePanel extends JPanel implements Runnable{
    //SCREEN SETTINGS
    final int originalTileSize = 16; //16x16 time
    final int scale= 3;

    final int tileSize=originalTileSize*scale; //48x48 title
    final int maxScreenCol = 16;
    final int maxScreenRow = 12;
    final int screenWidth = tileSize * maxScreenCol; // 768 pixels
    final int screenHeight = tileSize * maxScreenRow; // 576 pixels

    Thread gameThread;

    public gamePanel(){
        this.setPreferredSize(new Dimension(screenWidth,screenHeight));
        this.setBackground(Color.black);
        this.setDoubleBuffered(true);
    }

    public void startGameThread(){
        gameThread=new Thread(this);
        gameThread.start();
    }


    @Override
    public void run() {

        while(gameThread!=null){

            //System.out.println("Game loop is running");


            //1. UPDATE: Updating information such as character information
            update();

            //2. DRAW: Drawing the screen with the updated information
            repaint();
        }


    }

    public void update(){

    }

    public void paintComponent(Graphics g){
        super.paintComponent(g);

        Graphics2D g2 = (Graphics2D)g;

        g2.setColor(Color.white);

        g2.fillRect(100,100,tileSize,tileSize);

        g2.dispose();
    }
}
