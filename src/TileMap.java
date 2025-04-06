
import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;


public class TileMap {

    public static BufferedImage tileMapMaker(BufferedImage image,int x, int y)  {
        BufferedImage tileMap;
        tileMap=new BufferedImage(image.getWidth()*x,image.getHeight()*y,BufferedImage.TYPE_INT_ARGB);
        Graphics2D canvas = tileMap.createGraphics();
        for (int i = 0; i < tileMap.getHeight(); i+=image.getHeight()) {
            for (int j = 0; j < tileMap.getWidth(); j+=image.getWidth()) {
                canvas.drawImage(image,j,i,null);
            }
        }
        canvas.dispose();
        return tileMap;
    }
    public static BufferedImage tileMapMaker(String spritePath,int x, int y)  {
       BufferedImage image=null;
        try {
            image = ImageIO.read(new File(spritePath));
        } catch (IOException e) {
            System.out.println("nope");
        }

        BufferedImage tileMap;
        tileMap=new BufferedImage(image.getWidth()*x,image.getHeight()*y,BufferedImage.TYPE_INT_ARGB);
        Graphics2D canvas = tileMap.createGraphics();
        for (int i = 0; i < tileMap.getHeight(); i+=image.getHeight()) {
            for (int j = 0; j < tileMap.getWidth(); j+=image.getWidth()) {
                canvas.drawImage(image,j,i,null);
            }
        }
        canvas.dispose();
        return tileMap;
    }
    public static BufferedImage tileMapMaker(BufferedImage image,int x, int y, int tileWidth)  {
        BufferedImage tileMap;
        tileMap=new BufferedImage(tileWidth*x,tileWidth*y,BufferedImage.TYPE_INT_ARGB);
        Graphics2D canvas = tileMap.createGraphics();
        for (int i = 0; i < tileMap.getHeight(); i+=tileWidth) {
            for (int j = 0; j < tileMap.getWidth(); j+=tileWidth) {
                canvas.drawImage(image,j,i,null);
            }
        }
        canvas.dispose();
        return tileMap;
    }
    public static BufferedImage tileMapMaker(String spritePath,int x, int y, int tileWidth)  {
        BufferedImage image=null;
        try {
            image = ImageIO.read(new File(spritePath));
        } catch (IOException e) {
            System.out.println("nope");
        }

        BufferedImage tileMap;
        tileMap=new BufferedImage(tileWidth*x,tileWidth*y,BufferedImage.TYPE_INT_ARGB);
        Graphics2D canvas = tileMap.createGraphics();
        for (int i = 0; i < tileMap.getHeight(); i+=tileWidth) {
            for (int j = 0; j < tileMap.getWidth(); j+=tileWidth) {
                canvas.drawImage(image,j,i,null);
            }
        }
        canvas.dispose();
        return tileMap;
    }
}
