import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class RoomTypes {
    public static void roomType1(Room room) {
        BufferedImage x = null;
        try {
            x = ImageIO.read(new File("Sprites/wall.png"));
        } catch (IOException e) {
            System.out.println("nope");
        }
        new Entity(room, 100, 100, 130, 210, TileMap.tileMapMaker(x,10,5), true);
        new LivingEntity(room, 100, 100, 80, 330, "Sprites/image.png", 3,300);
    }
}
