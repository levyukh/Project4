import java.awt.image.BufferedImage;

public class Projectile extends DamagableZone{

    public Projectile(Room room, int w, int h, int x, int y, String spitePath, int attack, double timer, LivingEntity creator,int enemyX, int enemyY,int speed) {
        super(room, w, h, x, y, spitePath, attack, timer, creator);
        setSpeed(direction(enemyX,enemyY,speed));

    }
    public Projectile(Room room, int w, int h, int x, int y, BufferedImage image, int attack, double timer, LivingEntity creator,int enemyX, int enemyY,int speed) {
        super(room, w, h, x, y, image, attack, timer, creator);
        setSpeed(direction(enemyX,enemyY,speed));
    }
    private Vector2 direction(int x, int y, int speed){
        double angle = Math.atan2(y-getY(),x-getX());
        double xVelocity = (speed) * Math.cos(angle);
        double yVelocity = (speed) * Math.sin(angle);
        return new Vector2((int)xVelocity,(int)yVelocity);
    }
    @Override
    public void collisionLogic() {
        for(LivingEntity entity:getEntitiesHit()) entity.hit(getAttack());
        despawn();
    }

    @Override
    public void countDown() {
        lowerTimer(deltaTime);
        if(getTimer()<=0){
            despawn();
        }
    }
}
