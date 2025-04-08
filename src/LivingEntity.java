import java.util.HashSet;

public class LivingEntity extends CollidableEntity{
    private int hp;
    private int speed;
    public LivingEntity(Room room, int w, int h, int x, int y, String spitePath,int hp, int speed) {
        super(room, w, h, x, y, spitePath);
        this.hp=hp;
        this.speed=speed;
        room.addDamagable(this);

    }
    public void flipSpeed(){
        speed=-speed;
    }
    protected void movementLogic(){
        if(collided()) speed=-speed;
        getSpeed().setVector(speed,0);
    }
    public void damage(int damage){
        hp-=damage;
    }
    public void heal(int healing){
        hp+=healing;
    }

    public int getHp() {
        return hp;
    }
    protected void die(){
        despawn();
    }
    public void raiseSpeedStat(int raise){
        speed+=raise;
    }
    public int getSpeedStat() {
        return speed;
    }
    public void hit(int damage){
        damage(damage);
        System.out.println("got hit");
        if(hp<=0) die();
    }
    @Override
    public void despawn() {
        super.despawn();
        getRoom().getDamagableEntities().remove(this);
    }

    @Override
    public void move() {
        movementLogic();
        super.move();
    }
}
