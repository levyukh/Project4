import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.HashSet;

public class CollidableEntity extends Entity{
    private HashSet<CollidableEntity> collidable;
    private int hp;

    public CollidableEntity(Room room,int w, int h, int x, int y,int hp,String spitePath){
        super(room,w,h,x,y,spitePath);
        this.hp=hp;
        this.collidable = room.getCollidableEntities();

        room.addCollidable(this);

    }
    public CollidableEntity(Room room, int w, int h, int x, int y, int hp, BufferedImage image){
        super(room,w,h,x,y,image);
        this.hp=hp;
        this.collidable = room.getCollidableEntities();

        room.addCollidable(this);

    }
    public Vector2 getNextPos(){
        return getPosition().addVector(getSpeed().multiply(deltaTime));
    }
    public boolean willCollide(CollidableEntity other){

        return other!=this&&getNextPos().getX() + getWidth() > other.getNextPos().getX() && getNextPos().getX() < other.getNextPos().getX() + other.getWidth() && getNextPos().getY() + getHeight() > other.getNextPos().getY() && getNextPos().getY() < other.getNextPos().getY() + other.getHeight();
    }
    public void damage(int hp){
        this.hp-=hp;
    }

    public void setCollidable(HashSet<CollidableEntity> collidable) {
        this.collidable = collidable;
    }

    @Override
    public void move() {
        for(CollidableEntity entity:collidable) {
            if (willCollide(entity)) {
                setSpeed(getSpeed().subtractVector(getSpeed()));
            }
        }
       super.move();
    }
}
