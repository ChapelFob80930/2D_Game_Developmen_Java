package Main;

import entity.Player;

import javax.swing.*;
import java.awt.*;

public class gamePanel extends JPanel implements Runnable{
    //SCREEN SETTINGS
    final int originalTileSize = 16; //16x16 time
    final int scale= 3;

    public final int tileSize=originalTileSize*scale; //48x48 title
    final int maxScreenCol = 16;
    final int maxScreenRow = 12;
    final int screenWidth = tileSize * maxScreenCol; // 768 pixels
    final int screenHeight = tileSize * maxScreenRow; // 576 pixels

    //FPS
    int fps = 60;

    KeyHandler keyH = new KeyHandler();
    Thread gameThread;
    Player player = new Player(this,keyH);

    //variables to set payer's default position
    int playerX = 100;
    int playerY = 100;
    int playerSpeed = 4;

    public gamePanel(){
        this.setPreferredSize(new Dimension(screenWidth,screenHeight));
        this.setBackground(Color.black);
        this.setDoubleBuffered(true);
        this.addKeyListener(keyH);
        this.setFocusable(true);
    }

    public void startGameThread(){
        gameThread = new Thread(this);
        gameThread.start();
    }

//GAME LOOP 1 : SLEEP METHOD

//    @Override
//    public void run() {
//
//        double drawInterval = 1000000000/fps;  //1 second= 1000000000 nanoseconds (using nanoseconds as for more precise calculation)
//        // 1000000000/60 = 16,666,666.66666667 nanoseconds i.e. we draw the screen every 16,666,666.66666667 nanoseconds i.e. every 0.0167 seconds
//
//        double nextDrawTime = System.nanoTime() + drawInterval;
//        //if we draw the screen at a particular time (say now) the next draw time will be current time + draw interval (0.0167 seconds later)
//
//        while(gameThread!=null){
//
//            //1. UPDATE: Updating information such as character information
//            update();
//
//            //2. DRAW: Drawing the screen with the updated information
//            repaint();
//
//            try {
//                double remainingTime = nextDrawTime - System.nanoTime();
//                //returns how much time remaining for the next draw time, and we need to let the thread sleep for the remaining time
//
//                remainingTime = remainingTime/1000000;
//                // 1 second = 1000000000 nanosecond
//                // 1 second = 1000 millisecond
//                // converting nanosecond to millisecond as sleep accepts the time in milliseconds
//
//                if(remainingTime < 0)
//                {
//                    remainingTime = 0;
//                }
//
//                Thread.sleep((long)remainingTime);
//
//                nextDrawTime += drawInterval;
//                // next draw time is 0.0167 seconds later
//            }
//
//            catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//        }
//
//
//    }



// GAME LOOP 2 : DELTA or ACCUMULATOR METHOD
    @Override
    public void run() {

        double drawInterval = 1000000000/fps;
        double delta = 0;
        long lastTime = System.nanoTime();
        long currentTime;
        long timer = 0; //not part of method, used to display the fps
        int drawCount = 0;

        while(gameThread!=null){

            currentTime = System.nanoTime();

            delta += (currentTime - lastTime) / drawInterval;
            timer += (currentTime - lastTime);
            lastTime = currentTime;

            if(delta >= 1){
                update();
                repaint();
                delta--;
                drawCount++;
            }

            if(timer >= 1000000000){
                System.out.println("FPS: "+drawCount);
                drawCount = 0;
                timer = 0;
            }

        }

    }




    public void update(){
        player.update();
    }

    public void paintComponent(Graphics g){
        super.paintComponent(g);

        Graphics2D g2 = (Graphics2D)g;

        player.draw(g2);

        g2.dispose();
    }


}
