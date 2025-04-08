public class MindBolt extends Attack{
    public MindBolt(Enemy attacker) {
        super(attacker);
    }
    @Override
    public void doAttack() {
        if(getAttacker().getTargetPos()!=null&&canAttack()) {

            new Projectile(getAttacker().getRoom(), 20, 20, getAttacker().getTargetPos().getX()+150, getAttacker().getTargetPos().getY(), "Sprites/mindBolt.png",
                    getAttacker().getAttackStat(), 10, getAttacker(),getAttacker().getTargetPos().getX(),getAttacker().getTargetPos().getY(), 450);
            new Projectile(getAttacker().getRoom(), 20, 20, getAttacker().getTargetPos().getX()-150, getAttacker().getTargetPos().getY(), "Sprites/mindBolt.png",
                    getAttacker().getAttackStat(), 10, getAttacker(),getAttacker().getTargetPos().getX(),getAttacker().getTargetPos().getY(), 450);
            new Projectile(getAttacker().getRoom(), 20, 20, getAttacker().getX()+getAttacker().getWidth()/2, getAttacker().getY()+getAttacker().getHeight()/2,"Sprites/mindBolt.png",
                    getAttacker().getAttackStat(), 10, getAttacker(),getAttacker().getTargetPos().getX(), getAttacker().getTargetPos().getY()+200, 450);
            new Projectile(getAttacker().getRoom(), 20, 20, getAttacker().getX()+getAttacker().getWidth()/2, getAttacker().getY()+getAttacker().getHeight()/2,"Sprites/mindBolt.png",
                    getAttacker().getAttackStat(), 20, getAttacker(),getAttacker().getTargetPos().getX(), getAttacker().getTargetPos().getY()+200, 450);
            cantAttack();
            setCooldown(3);
        }
    }
}
