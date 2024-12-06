package Main;

import javax.swing.*;
import java.awt.*;

public class gamePanel extends JPanel {
    //SCREEN SETTINGS
    final int originalTitleSize = 16; //16x16 time
    final int scale= 3;
    final int titleSize=originalTitleSize*scale; //48x48 title
    final int maxScreenCol = 16;
    final int maxScreenRow = 12;
    final int screenWidth = titleSize * maxScreenCol; // 768 pixels
    final int screenHeight = titleSize * maxScreenRow; // 576 pixels

    public gamePanel(){
        this.setPreferredSize(new Dimension(screenWidth,screenHeight));
        this.setBackground(Color.black);
        this.setDoubleBuffered(true);
    }


}
