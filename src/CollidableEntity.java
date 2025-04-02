import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.HashSet;

public class CollidableEntity extends Entity{
    private HashSet<Entity> collidable;
    private boolean collided=false;

    public CollidableEntity(Room room,int w, int h, int x, int y,String spitePath){
        super(room,w,h,x,y,spitePath,true);
        this.collidable = room.getCollidableEntities();



    }
    public CollidableEntity(Room room, int w, int h, int x, int y, BufferedImage image){
        super(room,w,h,x,y,image,true);
        this.collidable = room.getCollidableEntities();

        room.addCollidable(this);

    }

    public boolean willCollide(Entity other){

        return other!=this&&getNextPos().getX() + getWidth() > other.getNextPos().getX() && getNextPos().getX() < other.getNextPos().getX() + other.getWidth() && getNextPos().getY() + getHeight() > other.getNextPos().getY() && getNextPos().getY() < other.getNextPos().getY() + other.getHeight();
    }
    public boolean checkCollision(){
        for(Entity entity:collidable) {
            if (willCollide(entity)) {
                return true;
            }
        }
        return false;
    }

    public boolean collided() {
        return collided;
    }

    public void setCollidable(HashSet<Entity> collidable) {
        this.collidable = collidable;
    }

    @Override
    public void move() {
       collided=checkCollision();
       if(collided) setSpeed(getSpeed().subtractVector(getSpeed()));
       super.move();
    }
}
