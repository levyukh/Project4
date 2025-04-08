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

    public void setAttacker(Enemy attacker) {
        this.attacker = attacker;
    }

    public boolean canAttack() {
        return canAttack;
    }

    public Enemy getAttacker() {
        return attacker;
    }

    public void setCooldown(double cooldown) {
        this.cooldown = cooldown;
    }
    public void cantAttack() {
        canAttack=false;
    }

    public void doAttack(){

        if(attacker.getTargetPos()!=null&&canAttack) {
            new Projectile(attacker.getRoom(), 40, 40, attacker.getX()+attacker.getWidth()/2, attacker.getY()+attacker.getHeight()/2, TileMap.loadImagesFromDirectory("Sprites/SlimeAttack"),
            attacker.getAttackStat(), 10, attacker, attacker.getTargetPos().getX(), attacker.getTargetPos().getY(), 600);
            canAttack=false;
            cooldown=2;
        }
    };
}
