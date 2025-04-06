import java.awt.image.BufferedImage;
import java.util.HashSet;

public class DamagableZone extends IntersectableEntity{
    private HashSet<LivingEntity> entitiesHit=new HashSet<>();
    private int attack;
    private double timer;
    private LivingEntity creator;
    public DamagableZone(Room room, int w, int h, int x, int y, String spitePath,int attack, double timer, LivingEntity creator) {
        super(room, w, h, x, y, spitePath);
        HashSet<Entity> newCollidable = new HashSet<>(room.getDamagableEntities());
        setCollidable(newCollidable);
        this.attack=attack;
        this.timer=timer;
        this.creator=creator;
    }

    public DamagableZone(Room room, int w, int h, int x, int y, BufferedImage image,int attack, double timer,LivingEntity creator) {
        super(room, w, h, x, y, image);
        HashSet<Entity> newCollidable = new HashSet<>(room.getDamagableEntities());
        setCollidable(newCollidable);
        this.attack=attack;
        this.timer=timer;
        this.creator=creator;
    }

    public HashSet<LivingEntity> getEntitiesHit() {
        return entitiesHit;
    }

    public int getAttack() {
        return attack;
    }

    @Override
    public boolean willCollide(Entity other) {
        return other!=creator&&super.willCollide(other);
    }

    @Override
    public boolean checkCollision() {
        boolean collided=false;
        for(Entity entity:getCollidable()) {
            if (willCollide(entity)) {
                collided= true;
                entitiesHit.add((LivingEntity) entity);
            }
        }
        return collided;
    }
    public void countDown(){
        timer-=deltaTime;
        if(timer<=0){
            for(LivingEntity entity:entitiesHit) entity.hit(attack);
            despawn();
        }
    }

    public double getTimer() {
        return timer;
    }
    public void lowerTimer(double amount) {
        timer-=amount;
    }

    @Override
    public void move() {
        super.move();
        countDown();
    }
}
