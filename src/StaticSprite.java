import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class StaticSprite {
    BufferedImage sprite;
    public StaticSprite(BufferedImage sprite){
        this.sprite=sprite;
    }
    public StaticSprite(String spritePath){
        try {
            sprite = ImageIO.read(new File(spritePath));
        } catch (IOException e) {
            System.out.println("nope");
        }
    }
    public void draw(Graphics2D graphic,int x, int y,int width,int height){
        graphic.drawImage(sprite,x,y,width,height,null);
    }

    public void setSprite(BufferedImage sprite) {
        this.sprite = sprite;
    }
}
