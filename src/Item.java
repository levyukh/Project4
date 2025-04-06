import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Item {
    private Player player;
    int placeInInventory;
    private BufferedImage sprite;
    public Item(Player player){
        this.player=player;
        player.addToInventory(this);
        placeInInventory= player.getSelected();
        loadImage();
    }
    protected void loadImage(){
        try {
            sprite = ImageIO.read(new File("Sprites/healingPotion.png"));
        } catch (IOException e) {
            System.out.println("nope");
        }
    }
    public void useItem(){
        player.heal(3);
        player.removeFromInventory(placeInInventory);
        System.out.println("healed");
    }

    public BufferedImage getSprite() {
        return sprite;
    }
}
