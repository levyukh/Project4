
import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.HashSet;

public class Player extends Enemy{
    private HashSet<Integer> keysPressed=new HashSet<Integer>();
    private Room[][] rooms;
    private double dashTime=0.1;
    private double dashCooldown=0;
    private int dashSpeed=2000;
    private int roomX;
    private int roomY;
    private int xDir=0;
    private int yDir=0;
    private int lastXDir=xDir;
    private int lastYDir=yDir;
    private BufferedImage inventoryGui;
    private BufferedImage selectedGui;
    private boolean dash=false;
    private Item[] inventory=new Item[5];
    private int selected=0;

    public Player(Room[][] rooms,int w, int h, int x, int y,String spitePath,int hp,int speed,int attackStat,int roomX, int roomY){
        super(rooms[roomY][roomX],w,h,x,y,spitePath,hp,speed,attackStat);
        try {
            inventoryGui = ImageIO.read(new File("Sprites/inventoryGui.png"));
            selectedGui= ImageIO.read(new File("Sprites/selectedGui.png"));
        } catch (IOException e) {
            System.out.println("nope");
        }
        setRoom(rooms[roomY][roomX]);
        getRoom().setPlayer(this);
        this.rooms=rooms;
        this.roomX=roomX;
        this.roomY=roomY;
        setAttack(new MeleeAttack(this));

    }
    public void enterRoom(Room newRoom,String exit){
        getRoom().removePlayer(this);
        setRoom(newRoom);
        getRoom().setPlayer(this);
        setCollidable(getRoom().getCollidableEntities());
        setPosition(getRoom().getExits().get(exit).getPosition());
        getRoom().addDrawable(this,getRoom().getAddDrawableQueueLength());
        getRoom().addCollidable(this);
        getRoom().addDamagable(this);
    }

    public void addToInventory(Item item) {
        inventory[selected]=item;
    }
    public void removeFromInventory(int index){
        inventory[index]=null;
    }

    public void keyPressed(int keyCode) {

        keysPressed.add(keyCode);
    }
    public void keyReleased(int keyCode){
        keysPressed.remove(keyCode);
        if(keyCode>36&&keyCode<41){
            lastXDir=xDir;
            lastYDir=yDir;
        }
    }

    public int getSelected() {
        return selected;
    }
    public void drawInventory(Graphics2D graphic){
        for(int i=0;i<inventory.length;i++){
            if(i!=selected) {
                graphic.drawImage(inventoryGui, 780, 121 * i, 85, 121, null);
            }
            else graphic.drawImage(selectedGui, 780, 121 * i, 85, 121, null);
            if(inventory[i]!=null) graphic.drawImage(inventory[i].getSprite(),795,127*i+20,50,60,null);
        }
    }
    private void checkExitCollision(){
        if(getRoom().getExits().get("Bottom Exit")!=null&&willCollide(getRoom().getExits().get("Bottom Exit"))&&keysPressed.contains(69)) enterRoom(rooms[++roomY][roomX],"Top Exit");
        else if(getRoom().getExits().get("Top Exit")!=null&&willCollide(getRoom().getExits().get("Top Exit"))&&keysPressed.contains(69)) enterRoom(rooms[--roomY][roomX],"Bottom Exit");
        else if(getRoom().getExits().get("Right Exit")!=null&&willCollide(getRoom().getExits().get("Right Exit"))&&keysPressed.contains(69)) enterRoom(rooms[roomY][++roomX],"Left Exit");
        else if(getRoom().getExits().get("Left Exit")!=null&&willCollide(getRoom().getExits().get("Left Exit"))&&keysPressed.contains(69)) enterRoom(rooms[roomY][--roomX],"Right Exit");
        keysPressed.remove(69);
    }

    public Item[] getInventory() {
        return inventory;
    }

    @Override
    protected  void movementLogic(){
        if(keysPressed.contains(49)) selected=0;
        else if(keysPressed.contains(50)) selected=1;
        else if(keysPressed.contains(51)) selected=2;
        else if(keysPressed.contains(52)) selected=3;
        else if(keysPressed.contains(53)) selected=4;
       if(!dash) {
           getSpeed().setVector(getSpeedStat()*xDir,getSpeedStat()*yDir);
            xDir = (keysPressed.contains(37) ? -1 : 0) + (keysPressed.contains(39) ? 1 : 0);
            yDir = (keysPressed.contains(38) ? -1 : 0) + (keysPressed.contains(40) ? 1 : 0);

           if (dashCooldown > 0) dashCooldown -= deltaTime;
           else if (keysPressed.contains(32)) {
               dash = true;
               attack();
               getSpeed().setVector(dashSpeed*lastXDir,dashSpeed*lastYDir);
           }

       }else {
        if (dashTime > 0) {
            dashTime -= deltaTime;
        } else {
            getSpeed().setVector(0, 0);
            dash = false;
            dashTime = 0.2;
            dashCooldown = 2;
        }
       }


    }



    @Override
    public void move() {

        checkExitCollision();

        super.move();


    }


}
