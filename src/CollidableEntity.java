import java.awt.image.BufferedImage;


public class CollidableEntity extends IntersectableEntity{


    public CollidableEntity(Room room,int w, int h, int x, int y,String spitePath){
        super(room,w,h,x,y,spitePath);
        room.getCollidableEntities().add(this);
    }
    public CollidableEntity(Room room, int w, int h, int x, int y, BufferedImage image){
        super(room,w,h,x,y,image);
        room.getCollidableEntities().add(this);
    }


    @Override
    public boolean willCollide(Entity other){

        return other!=this&&getNextPos().getX() + getWidth() > other.getNextPos().getX() && getNextPos().getX() < other.getNextPos().getX() + other.getWidth() && getNextPos().getY() + getHeight() > other.getNextPos().getY() && getNextPos().getY() < other.getNextPos().getY() + other.getHeight();
    }
   @Override
    public void collisionLogic(){
        setSpeed(getSpeed().subtractVector(getSpeed()));
    }

}
