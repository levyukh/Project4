import java.awt.image.BufferedImage;

public class OverworldEssence extends Entity{
    private Essence essence;
    public OverworldEssence(Enemy enemy, String spitePath) {
        super(enemy.getRoom(), 50, 50, enemy.getX(), enemy.getY(), spitePath, false);
        essence=new Essence(enemy);
        getRoom().getEssences().add(this);
    }
    public OverworldEssence(Enemy enemy, BufferedImage[] images) {
        super(enemy.getRoom(), 50, 50, enemy.getX(), enemy.getY(), images, false);
        essence=new Essence(enemy);
        getRoom().getEssences().add(this);
    }

    public Essence getEssence() {
        return essence;
    }
    public void despawn() {
        super.despawn();
        getRoom().removeEssence(this);
    }

}
