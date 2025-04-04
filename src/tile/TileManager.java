package tile;

import Main.gamePanel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class TileManager {

    gamePanel gp;
    public Tile[] tile;
    public int[][] mapTileNumber;

    public TileManager(gamePanel gp){

        this.gp=gp;
        tile = new Tile[10];
        mapTileNumber = new int[gp.maxWorldCol][gp.maxWorldRow];//generates 10 tiles
        getTileImage();
        loadMap("/maps/world01.txt");
    }

    public void getTileImage(){
        try{

            tile[0] = new Tile();
            tile[0].image = ImageIO.read(getClass().getResourceAsStream("/tiles/grass.png"));

            tile[1] = new Tile();
            tile[1].image = ImageIO.read(getClass().getResourceAsStream("/tiles/wall.png"));
            tile[1].collision = true;

            tile[2] = new Tile();
            tile[2].image = ImageIO.read(getClass().getResourceAsStream("/tiles/water.png"));
            tile[2].collision = true;

            tile[3] = new Tile();
            tile[3].image = ImageIO.read(getClass().getResourceAsStream("/tiles/earth.png"));

            tile[4] = new Tile();
            tile[4].image = ImageIO.read(getClass().getResourceAsStream("/tiles/tree.png"));
            tile[4].collision = true;

            tile[5] = new Tile();
            tile[5].image = ImageIO.read(getClass().getResourceAsStream("/tiles/sand.png"));


        }catch(IOException e){
            e.printStackTrace();
        }
    }

    public void loadMap(String filePath){
        //read our text file line by line, divide the strings number by number and store them in mapTileNumber
        try{

            InputStream is = getClass().getResourceAsStream(filePath);  // used to import our text file
            BufferedReader br = new BufferedReader(new InputStreamReader(is));  //buffered reader used to read text files

            int col = 0;
            int row = 0;

            while (col < gp.maxWorldCol && row < gp.maxWorldRow){
                String line = br.readLine();  //.readline reads a single line and adds it to line variable

                while(col < gp.maxWorldCol){

                    String numbers[] = line.split(" "); // splitting the string at spaces i.e. " "

                    int num = Integer.parseInt(numbers[col]);  //converts string to integer and uses col as an index

                    mapTileNumber[col][row] = num;
                    col++;
                }
                if(col == gp.maxWorldCol){
                    col = 0;
                    row++;
                }
            }
            br.close();
        }catch (Exception e){

        }
    }

    public  void draw(Graphics2D g2){

        int worldCol = 0;
        int worldRow = 0;

         while(worldCol < gp.maxWorldCol && worldRow < gp.maxWorldRow){

             int tileNum = mapTileNumber[worldCol][worldRow]; //extracting a tile number stored at mapTileNumber[0][0] .....mapTile[n][n]

             int worldX = worldCol * gp.tileSize; //tile position the map/world
             int worldY = worldRow * gp.tileSize; //tile position the map/world
             int screenX = worldX-gp.player.worldX + gp.player.screenX; //tile position on the screen, and we need to draw it with respect to the player's current position
             int screenY = worldY-gp.player.worldY + gp.player.screenY; //tile position on the screen, and we need to draw it with respect to the player's current position

             if(worldX + gp.tileSize > gp.player.worldX-gp.player.screenX &&
                worldX - gp.tileSize< gp.player.worldX+gp.player.screenX &&
                worldY + gp.tileSize > gp.player.worldY-gp.player.screenY &&
                worldY - gp.tileSize < gp.player.worldY+gp.player.screenY){
                 //we basically  create a boundary such that, as long as the tile is in this boundary we draw it (helps in better rendering)
                 g2.drawImage(tile[tileNum].image, screenX, screenY, gp.tileSize, gp.tileSize, null);
             }
             worldCol++;

             if(worldCol == gp.maxWorldCol){
                 worldCol=0;
                 worldRow++;
             }
         }

    }
}
