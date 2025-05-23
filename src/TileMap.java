
import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;


public class TileMap {
    public static BufferedImage[] makeImagesRedder(BufferedImage[] input) {
        BufferedImage[] returnInput=new BufferedImage[input.length];
        for (int i = 0; i < input.length; i++) {
            returnInput[i]=makeImageRedder(input[i]);
        }
        return returnInput;
    }
    public static BufferedImage makeImageRedder(BufferedImage input) {
        int width = input.getWidth();
        int height = input.getHeight();
        BufferedImage redderImage = new BufferedImage(width, height, input.getType());
        int redOffset = 50;
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                Color color = new Color(input.getRGB(x, y), true);
                int newRed = Math.min(color.getRed() + redOffset, 255);
                Color newColor = new Color(newRed, color.getGreen(), color.getBlue(), color.getAlpha());
                redderImage.setRGB(x, y, newColor.getRGB());
            }
        }
        return redderImage;
    }
    public static BufferedImage[] loadImagesFromDirectory(String directoryPath){

        File[] files = new File(directoryPath).listFiles();
        BufferedImage[] images=new BufferedImage[files.length];
        for (int i = 0; i <images.length ; i++) {
            try {
                images[i] = ImageIO.read(files[i]);
            } catch (IOException e) {
                System.out.println("nuh uh");
            }
        }
        return images;
    }
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
