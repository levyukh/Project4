public class FireEnemy extends Enemy{
    public FireEnemy(Room room, int w, int h, int x, int y, int hp, int speed, int attack) {
        super(room, w, h, x, y, hp, speed, attack);
        animations=loadAnimations("Sprites/FireEnemyAnimation");
        setAttack(new ExplosiveBolt(this));
    }
    @Override
    protected void movementLogic() {
        if(getRoom().getPlayer()!=null) setTargetPos(getRoom().getPlayer().getPosition());
        if(getTargetPos()!=null){
            attack();
            int xSpeed=0;
            int ySpeed=0;
            if(getPosition().addVector(getWidth()/2,getHeight()/2).distance(getTargetPos().addVector(getRoom().getPlayer().getWidth()/2,getRoom().getPlayer().getHeight()/2))<850) {
                int enemyLeft = getX();
                int enemyRight = getX() + getWidth();
                int enemyTop = getY();
                int enemyBottom = getY() + getHeight();
                int playerLeft = getRoom().getPlayer().getX();
                int playerRight = getRoom().getPlayer().getX() + getRoom().getPlayer().getWidth();
                int playerTop = getRoom().getPlayer().getY();
                int playerBottom = getRoom().getPlayer().getY() + getRoom().getPlayer().getHeight();
                if (enemyLeft > playerRight) {
                    xSpeed = -getSpeedStat();
                }
                if (enemyRight < playerLeft) {
                    xSpeed = getSpeedStat();
                }


                if (enemyTop > playerBottom) {
                    ySpeed = -getSpeedStat();
                }
                if (enemyBottom < playerTop) {
                    ySpeed = getSpeedStat();
                }
            }
            setSpeed(xSpeed,ySpeed);

        }
    }
}
