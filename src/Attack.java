public class Attack {
    private Enemy attacker;
    private boolean canAttack=true;
    private double cooldown=0;
    public Attack(Enemy attacker){
        this.attacker=attacker;
    }
    public void updateCooldown(){
        if(cooldown>0) {
            cooldown -= Entity.deltaTime;
        }else if(!canAttack) {
            canAttack=true;
        }

    }
    public void doAttack(){

        if(attacker.getTargetPos()!=null&&canAttack) {
            new Projectile(attacker.getRoom(), 20, 20, attacker.getX(), attacker.getY(), "Sprites/red.png",
            attacker.getAttackStat(), 10, attacker, attacker.getTargetPos().getX(), attacker.getTargetPos().getY(), 600);
            canAttack=false;
            cooldown=2;
        }
    };
}
