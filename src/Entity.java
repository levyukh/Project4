import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;


public class Entity {
    private Vector2 position;
    private final int height;
    private final int width;
    private StaticSprite sprite;
    private Vector2 speed=new Vector2(0,0);
    private Room room;
    protected static double deltaTime;
    public Entity(Room room,int width, int height, int x, int y, String spitePath,boolean addToCollidables){
        position=new Vector2(x,y);
        this.width=width;
        this.height=height;
        sprite=new StaticSprite(spitePath);
        room.addDrawable(this);
        if(addToCollidables)room.addCollidable(this);
        this.room=room;
    }

    public void setRoom(Room room) {
        this.room = room;
    }

    public Room getRoom() {
        return room;
    }
    public void despawn(){
        room.removeDrawable(this);
        room.getCollidableEntities().remove(this);
    }
    public Entity(Room room, int width, int height, int x, int y, BufferedImage image, boolean addToCollidables){
        position=new Vector2(x,y);
        this.width=width;
        this.height=height;
        sprite=new StaticSprite(image);
        room.addDrawable(this);
        if(addToCollidables)room.addCollidable(this);

        this.room=room;
    }
    public Entity(Room room, int width, int height, int x, int y, BufferedImage[] image, boolean addToCollidables){
        position=new Vector2(x,y);
        this.width=width;
        this.height=height;
        sprite=new AnimatedSprite(image);
        room.addDrawable(this);
        if(addToCollidables)room.addCollidable(this);

        this.room=room;
    }

    public Vector2 getNextPos(){
        return getPosition().addVector(getSpeed().multiply(deltaTime));
    }
    public static void setDeltaTime(double deltaTime){
        Entity.deltaTime=deltaTime;
    }

    public int getWidth() {
        return width;
    }

    public Vector2 getSpeed() {
        return speed;
    }

    public void setSpeed(Vector2 speed) {
        this.speed = speed;
    }
    public void setSpeed(int x, int y) {
        speed.setX(x);
        speed.setY(y);
    }

    public int getHeight() {
        return height;
    }

    public Vector2 getPosition() {
        return position;
    }

    public int getX() {
        return position.getX();
    }

    public int getY() {
        return position.getY();
    }



    public void setPosition(Vector2 position) {
        this.position = position;
    }
    public void setPosition(int x, int y) {
        position.setX(x);
        position.setY(y);
    }
    public void move(){

        position=position.addVector(speed.multiply(deltaTime));
    }
    public void draw(Graphics2D graphic){
        sprite.draw(graphic,position.getX(),position.getY(),width,height);
    }
}
