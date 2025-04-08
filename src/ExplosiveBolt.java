public class ExplosiveBolt extends Attack{
    public ExplosiveBolt(Enemy attacker) {
        super(attacker);
    }

    @Override
    public void doAttack() {
        if(getAttacker().getTargetPos()!=null&&canAttack()) {
           new DamagableZone(getAttacker().getRoom(), 200+getAttacker().getWidth()/2, 200+getAttacker().getWidth()/2, getAttacker().getX()- getAttacker().getWidth()/4, getAttacker().getY()- getAttacker().getHeight()/4, TileMap.loadImagesFromDirectory("Sprites/Explosion"),
                    getAttacker().getAttackStat()+2, 0.5,getAttacker());
            new Projectile(getAttacker().getRoom(), 30, 30, getAttacker().getX()+getAttacker().getWidth()/2, getAttacker().getY()+getAttacker().getHeight()/2, "Sprites/flamingStone.png",
                    getAttacker().getAttackStat(), 10, getAttacker(),0,0, 600);
            new Projectile(getAttacker().getRoom(), 30, 30, getAttacker().getX()+getAttacker().getWidth()/2, getAttacker().getY()+getAttacker().getHeight()/2, "Sprites/flamingStone.png",
                    getAttacker().getAttackStat(), 10, getAttacker(),1500,0, 600);
            new Projectile(getAttacker().getRoom(), 30, 30, getAttacker().getX()+getAttacker().getWidth()/2, getAttacker().getY()+getAttacker().getHeight()/2,"Sprites/flamingStone.png",
                    getAttacker().getAttackStat(), 10, getAttacker(),0,1500, 600);
            new Projectile(getAttacker().getRoom(), 30, 30, getAttacker().getX()+getAttacker().getWidth()/2, getAttacker().getY()+getAttacker().getHeight()/2,"Sprites/flamingStone.png",
                    getAttacker().getAttackStat(), 30, getAttacker(),1500,1500, 600);
            cantAttack();
            setCooldown(5);
        }
    }
}
