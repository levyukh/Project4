import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Item {
    private Player player;
    int placeInInventory;
    private BufferedImage sprite;
    public Item(){
        loadImage();
    }
    protected void loadImage(){
        try {
            sprite = ImageIO.read(new File("Sprites/healingPotion.png"));
        } catch (IOException e) {
            System.out.println("nope");
        }
    }
    public void addItem(Player player){
        this.player=player;
        placeInInventory= player.getSelected();
        player.addToInventory(this);

    }

    public int getPlaceInInventory() {
        return placeInInventory;
    }

    public void useItem(){
        int healAmt = 4;
        if (player.getHP() > player.getMaxHP() - healAmt) {
            healAmt -= healAmt - (player.getMaxHP() - player.getHP());
        }
        player.heal(healAmt);
        player.removeFromInventory(placeInInventory);
        System.out.println(placeInInventory);
        System.out.println("healed");
    }

    public BufferedImage getSprite() {
        return sprite;
    }
}
