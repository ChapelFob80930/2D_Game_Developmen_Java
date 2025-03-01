package entity;

import Main.gamePanel;
import Main.KeyHandler;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Player extends Entity{
    gamePanel gp;
    KeyHandler keyH;

    public final int screenX;  //indicates where we draw the player on the screen
    public final int screenY;  //indicates where we draw the player on the screen

    public  Player(gamePanel gp, KeyHandler keyH){
        this.gp = gp;
        this.keyH = keyH;
        screenX = gp.screenWidth/2 - (gp.tileSize/2);   //we want the player to always be drawn at the center of the screen and the background only moves
        screenY = gp.screenHeight/2 - (gp.tileSize/2);  //we want the player to always be drawn at the center of the screen and the background only moves

        solidArea = new Rectangle();
        solidArea.x = 8;
        solidArea.y = 16;
        solidArea.width = 32;
        solidArea.height = 32;

        setDefaultValues();
        getPlayerImage();
    }

    //variables to set payer's default position
    public void setDefaultValues(){
        worldX = gp.tileSize*23; //player's position on the map, initial position taken respective to the row of the map where we want the player
        worldY = gp.tileSize*21; //player's position on the map, initial position taken respective to the column of the map where we want the player
        speed = 4;  //technically the distanced moved
        direction = "down";
    }


    public void getPlayerImage(){
        try{


//            up1 = ImageIO.read(getClass().getResourceAsStream("/Player/up1.png"));
//            up2 = ImageIO.read(getClass().getResourceAsStream("/Player/up2.png"));
//            down1 = ImageIO.read(getClass().getResourceAsStream("/Player/down1.png"));
//            down2 = ImageIO.read(getClass().getResourceAsStream("/Player/down2.png"));
//            left1 = ImageIO.read(getClass().getResourceAsStream("/Player/left1.png"));
//            left2 = ImageIO.read(getClass().getResourceAsStream("/Player/left2.png"));
//            right1 = ImageIO.read(getClass().getResourceAsStream("/Player/right1.png"));
//         right2 = ImageIO.read(getClass().getResourceAsStream("/Player/right2.png"));


            up1 = ImageIO.read(getClass().getResourceAsStream("/playerSpritesFromRyiSnow/boy_up_1.png"));
            up2 = ImageIO.read(getClass().getResourceAsStream("/playerSpritesFromRyiSnow/boy_up_2.png"));
            down1 = ImageIO.read(getClass().getResourceAsStream("/playerSpritesFromRyiSnow/boy_down_1.png"));
            down2 = ImageIO.read(getClass().getResourceAsStream("/playerSpritesFromRyiSnow/boy_down_2.png"));
            left1 = ImageIO.read(getClass().getResourceAsStream("/playerSpritesFromRyiSnow/boy_left_1.png"));
            left2 = ImageIO.read(getClass().getResourceAsStream("/playerSpritesFromRyiSnow/boy_left_2.png"));
            right1 = ImageIO.read(getClass().getResourceAsStream("/playerSpritesFromRyiSnow/boy_right_1.png"));
            right2 = ImageIO.read(getClass().getResourceAsStream("/playerSpritesFromRyiSnow/boy_right_2.png"));

        }catch(IOException e){

        }
    }



    public void update() {

    if (keyH.upPressed == true || keyH.downPressed == true || keyH.leftPressed == true || keyH.rightPressed == true) {

            if (keyH.upPressed == true) {
                direction = "up";

            }

            else if (keyH.downPressed == true) {
                direction = "down";

            }

            else if (keyH.rightPressed == true) {
                direction = "right";

            }

            else if (keyH.leftPressed == true) {
                direction = "left";

            }

            //check tile collision
            collisionOn = false;
            gp.cc.checkTile(this);

            //if collision is false player can move
            if(collisionOn == false){
                switch(direction){
                    case "up":
                        worldY -= speed;
                        break;

                    case "down":
                        worldY += speed;
                        break;

                    case "left":
                        worldX -= speed;
                        break;

                    case "right":
                        worldX += speed;
                        break;
                }
            }


            spriteCounter++;
            if (spriteCounter > 12) {
                if (spriteNumber == 1) {
                    spriteNumber = 2;
                } else if (spriteNumber == 2) {
                    spriteNumber = 1;
                }
                spriteCounter = 0;

            }
        }
    }

    public void draw(Graphics2D g2){
//        g2.setColor(Color.white);
//        g2.fillRect(x,y,gp.tileSize,gp.tileSize);

        BufferedImage image =null;
        switch(direction){
            case "up":
                if (spriteNumber==1){
                    image=up1;
                }
                if(spriteNumber==2){
                    image=up2;
                }
                break;
            case "down":
                if (spriteNumber==1){
                    image=down1;
                }
                if(spriteNumber==2){
                    image=down2;
                }
                break;
            case "left":
                if (spriteNumber==1){
                    image=left1;
                }
                if(spriteNumber==2){
                    image=left2;
                }
                break;
            case "right":
                if (spriteNumber==1){
                    image=right1;
                }
                if(spriteNumber==2){
                    image=right2;
                }
                break;
        }

        g2.drawImage(image,screenX,screenY,gp.tileSize,gp.tileSize,null);

    }
}
