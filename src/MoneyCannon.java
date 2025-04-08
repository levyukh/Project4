public class MoneyCannon extends Attack{
    public MoneyCannon(Enemy attacker) {
        super(attacker);
    }
    public void doAttack() {
        if(getAttacker().getTargetPos()!=null&&canAttack()) {

            new Projectile(getAttacker().getRoom(), 40, 20, getAttacker().getX(), getAttacker().getY(), "Sprites/money.png",
                    getAttacker().getAttackStat(), 10, getAttacker(),getAttacker().getTargetPos().getX(),getAttacker().getTargetPos().getY(), 800);
            new Projectile(getAttacker().getRoom(), 40, 20, getAttacker().getX(), getAttacker().getY(), "Sprites/money.png",
                    getAttacker().getAttackStat(), 10, getAttacker(),getAttacker().getTargetPos().getX()-20,getAttacker().getTargetPos().getY(), 800);
            new Projectile(getAttacker().getRoom(), 40, 20, getAttacker().getX()+getAttacker().getWidth()/2, getAttacker().getY()+getAttacker().getHeight()/2,"Sprites/money.png",
                    getAttacker().getAttackStat(), 10, getAttacker(),getAttacker().getTargetPos().getX()+20, getAttacker().getTargetPos().getY(), 800);

            cantAttack();
            setCooldown(0.5);
        }
    }
}
