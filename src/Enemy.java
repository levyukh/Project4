public class Enemy extends LivingEntity{
    private Vector2 targetPos;
    private Attack attack=new Attack(this);
    private int attackStat;
    public Enemy(Room room, int w, int h, int x, int y, String spitePath, int hp, int speed,int attack) {
        super(room, w, h, x, y, spitePath, hp, speed);
        attackStat=attack;
    }

    public int getAttackStat() {
        return attackStat;
    }

    public Vector2 getTargetPos() {
        return targetPos;
    }
    public void setTargetPos(Vector2 newTargetPos){
        targetPos=newTargetPos;
    }

    @Override
    protected void movementLogic() {
        if(getRoom().getPlayer()!=null) targetPos=getRoom().getPlayer().getPosition();
        if(targetPos!=null){
            attack();
            int xSpeed=0;
            int ySpeed=0;
            if(getPosition().addVector(getWidth()/2,getHeight()/2).distance(targetPos.addVector(getRoom().getPlayer().getWidth()/2,getRoom().getPlayer().getHeight()/2))<150) {
                int enemyLeft = getX();
                int enemyRight = getX() + getWidth();
                int enemyTop = getY();
                int enemyBottom = getY() + getHeight();
                int playerLeft = getRoom().getPlayer().getX();
                int playerRight = getRoom().getPlayer().getX() + getRoom().getPlayer().getWidth();
                int playerTop = getRoom().getPlayer().getY();
                int playerBottom = getRoom().getPlayer().getY() + getRoom().getPlayer().getHeight();
                if (enemyLeft > playerRight) {
                    xSpeed = getSpeedStat();
                }
                if (enemyRight < playerLeft) {
                    xSpeed = -getSpeedStat();
                }


                if (enemyTop > playerBottom) {
                    ySpeed = getSpeedStat();
                }
                if (enemyBottom < playerTop) {
                    ySpeed = -getSpeedStat();
                }
            }
            setSpeed(xSpeed,ySpeed);

        }
    }

    @Override
    public void move() {
        attack.updateCooldown();
        super.move();
    }

    public void attack(){
        attack.doAttack();
    }
}
