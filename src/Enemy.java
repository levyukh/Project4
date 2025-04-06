public class Enemy extends LivingEntity{
    private Vector2 targetPos;
    private Attack attack=new Attack(this);
    private int attackStat;
    private int dir=1;//1=right 2=left 3=up 4=down
    public Enemy(Room room, int w, int h, int x, int y, String spitePath, int hp, int speed,int attack) {
        super(room, w, h, x, y, spitePath, hp, speed);
        attackStat=attack;
    }

    public int getAttackStat() {
        return attackStat;
    }

    public void setAttack(Attack attack) {
        this.attack = attack;
    }

    public Vector2 getTargetPos() {
        return targetPos;
    }
    public void setTargetPos(Vector2 newTargetPos){
        targetPos=newTargetPos;
    }
    private void dir(){
        if(getSpeed().getX()>0) dir=1;
        else if(getSpeed().getX()<0) dir=2;
        else if(getSpeed().getY()<0) dir=3;
        else if(getSpeed().getY()>0) dir=4;

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

    public int getDir() {
        return dir;
    }

    @Override
    public void move() {
        attack.updateCooldown();
        super.move();
        dir();
    }

    public void attack(){
        attack.doAttack();
    }
}
