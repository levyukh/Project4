import java.awt.image.BufferedImage;
import java.util.HashSet;

public class IntersectableEntity extends Entity{
    private HashSet<Entity> collidable;
    private boolean collided=false;
    public IntersectableEntity(Room room, int w, int h, int x, int y, String spitePath){
        super(room,w,h,x,y,spitePath,false);
        this.collidable = room.getCollidableEntities();

    }
    public IntersectableEntity(Room room, int w, int h, int x, int y, BufferedImage image){
        super(room,w,h,x,y,image,false);
        this.collidable = room.getCollidableEntities();
    }
    public IntersectableEntity(Room room, int w, int h, int x, int y, BufferedImage[] image){
        super(room,w,h,x,y,image,false);
        this.collidable = room.getCollidableEntities();
    }

    public void collisionLogic() {

    }


    public boolean willCollide(Entity other) {
        return other!=this&&getPosition().getX() + getWidth() > other.getPosition().getX() && getPosition().getX() < other.getPosition().getX() + other.getWidth() && getPosition().getY() + getHeight() > other.getPosition().getY() && getPosition().getY() < other.getPosition().getY() + other.getHeight();
    }
    public HashSet<Entity> getCollidable() {
        return collidable;
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
        if(collided) collisionLogic();
        super.move();
    }
}
