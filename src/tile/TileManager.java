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
    Tile[] tile;
    int mapTileNumber[][];

    public TileManager(gamePanel gp){

        this.gp=gp;
        tile = new Tile[10];
        mapTileNumber = new int[gp.maxScreenCol][gp.maxScreenRow];//generates 10 tiles
        getTileImage();
        loadMap("/maps/map01.txt");
    }

    public void getTileImage(){
        try{

            tile[0] = new Tile();
            tile[0].image = ImageIO.read(getClass().getResourceAsStream("/tiles/grass.png"));

            tile[1] = new Tile();
            tile[1].image = ImageIO.read(getClass().getResourceAsStream("/tiles/wall.png"));

            tile[2] = new Tile();
            tile[2].image = ImageIO.read(getClass().getResourceAsStream("/tiles/water.png"));


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

            while (col < gp.maxScreenCol && row < gp.maxScreenRow){
                String line = br.readLine();  //.readline reads a single line and adds it to line variable

                while(col < gp.maxScreenCol){

                    String numbers[] = line.split(" "); // splitting the string at spaces i.e. " "

                    int num = Integer.parseInt(numbers[col]);  //converts string to integer and uses col as an index

                    mapTileNumber[col][row] = num;
                    col++;
                }
                if(col == gp.maxScreenCol){
                    col = 0;
                    row++;
                }
            }
            br.close();
        }catch (Exception e){

        }
    }

    public  void draw(Graphics2D g2){

        int col = 0;
        int row = 0;
        int x = 0;
        int y = 0;

         while(col < gp.maxScreenCol && row < gp.maxScreenRow){

             int tileNum = mapTileNumber[col][row]; //extracting a tile number stored at mapTileNumber[0][0] .....mapTile[n][n]

             g2.drawImage(tile[tileNum].image, x, y, gp.tileSize, gp.tileSize, null);
             col++;
             x += gp.tileSize;

             if(col == gp.maxScreenCol){
                 col=0;
                 x=0;
                 row++;
                 y += gp.tileSize;
             }
         }

    }
}
