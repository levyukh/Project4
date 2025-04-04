import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;


public class Room {
    private BufferedImage floor;
    private ArrayList<Entity> drawableEntities=new ArrayList<Entity>();
    private HashSet<Entity> collidableEntities=new HashSet<Entity>();
    private HashSet<LivingEntity> damagableEntities=new HashSet<LivingEntity>();
    private HashMap<String,Exit> exits=new HashMap<String,Exit>();
    private Player player=null;

    public Room(String files,int width, int height,boolean hasTopExit,boolean hasBottomExit,boolean hasLeftExit,boolean hasRightExit){
        BufferedImage wall=null;

        try {
            floor = ImageIO.read(new File(files));
            wall= ImageIO.read(new File("Sprites/wall.png"));
        } catch (IOException e) {
            System.out.println("nope");
        }

        floor = TileMap.tileMapMaker(floor,width,height);
        new Entity(this,800,50,0,0,TileMap.tileMapMaker(wall,(int)(800/wall.getWidth()+1), 1),true);
        new Entity(this,800,50,0,640-2*wall.getHeight(),TileMap.tileMapMaker(wall,(int)(800/wall.getWidth()), 1),true);
        new Entity(this,50,640,0,0,TileMap.tileMapMaker(wall,1, (int)(640/wall.getHeight()+1)),true);
        new Entity(this,50,640,800-2*wall.getWidth(),0,TileMap.tileMapMaker(wall,1, (int)(640/wall.getHeight())),true);
        if(hasBottomExit)new Exit(this,"Bottom Exit");
        if(hasLeftExit)new Exit(this,"Left Exit");
        if(hasRightExit)new Exit(this,"Right Exit");
        if(hasTopExit)new Exit(this,"Top Exit");
    }

    public HashMap<String, Exit> getExits() {
        return exits;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public Player getPlayer() {
        return player;
    }


    public HashSet<LivingEntity> getDamagableEntities() {
        return damagableEntities;
    }

    public HashSet<Entity> getCollidableEntities() {
        return collidableEntities;
    }
    public void roomPhysics(){
        for (Entity entity:drawableEntities){
            entity.move();
        }

    }
    public void removePlayer(Player player){
        player=null;
        collidableEntities.remove(player);
        drawableEntities.remove(player);
    }
    public void drawRoom(Graphics2D screen){
        screen.drawImage(floor,0,0,null);
        for(Entity entity:drawableEntities) entity.draw(screen);

    }
    public void addDrawable(Entity entity){
        if(!drawableEntities.contains(entity)) {
            drawableEntities.add(entity);
        }
    }

    public void addCollidable(Entity entity){
        collidableEntities.add(entity);
    }
    public void addDamagable(LivingEntity entity){
        damagableEntities.add(entity);
    }


}
