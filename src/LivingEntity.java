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

    public int getSpeedStat() {
        return speed;
    }

    @Override
    public void move() {
        movementLogic();
        super.move();
    }
}
