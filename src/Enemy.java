public class Enemy extends LivingEntity{
    private Vector2 targetPos;
    private Attack attack=new Attack(this);
    private double stun=0;
    private int attackStat;

    protected StaticSprite[] animations;

    protected static StaticSprite[] loadAnimations(String spritePath){
        return new StaticSprite[]{
                new AnimatedSprite(TileMap.loadImagesFromDirectory(spritePath + "/walkRight")),
                new AnimatedSprite(TileMap.loadImagesFromDirectory(spritePath + "/walkLeft")),
                new AnimatedSprite(TileMap.loadImagesFromDirectory(spritePath + "/walkUp")),
                new AnimatedSprite(TileMap.loadImagesFromDirectory(spritePath + "/walkDown")),
                new AnimatedSprite(TileMap.loadImagesFromDirectory(spritePath + "/idleRight")),
                new AnimatedSprite(TileMap.loadImagesFromDirectory(spritePath + "/idleLeft")),
                new AnimatedSprite(TileMap.loadImagesFromDirectory(spritePath + "/idleUp")),
                new AnimatedSprite(TileMap.loadImagesFromDirectory(spritePath + "/idleDown")),
                new AnimatedSprite(TileMap.makeImagesRedder(TileMap.loadImagesFromDirectory(spritePath + "/idleRight"))),
                new AnimatedSprite(TileMap.makeImagesRedder(TileMap.loadImagesFromDirectory(spritePath + "/idleLeft"))),
                new AnimatedSprite(TileMap.makeImagesRedder(TileMap.loadImagesFromDirectory(spritePath + "/idleUp"))),
                new AnimatedSprite(TileMap.makeImagesRedder(TileMap.loadImagesFromDirectory(spritePath + "/idleDown")))
        };
    }//0=right 1=left 2=up 3=down 4=idle r 5=idle l 6=idle u 7= idle d 8=dam r 9= dam l 10=dam u 11=dam d

    private int dir=1;//1=right 2=left 3=up 4=down
    public Enemy(Room room, int w, int h, int x, int y, int hp, int speed,int attack) {
        super(room, w, h, x, y, "Sprites/red.png", hp, speed);
        animations=loadAnimations("Sprites/SlimeEnemyAnimation");
        setSprite(animations[4]);
        attackStat=attack;
    }

    public int getAttackStat() {
        return attackStat;
    }

    public void setAttack(Attack attack) {
        this.attack = attack;
    }

    public void stun(int time) {
        stun=time;
    }

    public Vector2 getTargetPos() {
        return targetPos;
    }
    public void setTargetPos(Vector2 newTargetPos){
        targetPos=newTargetPos;
    }
    private void dir(){
        if(getSpeed().getX()>0) {
            dir = 1;
            setSprite(animations[0]);
        }
        else if(getSpeed().getX()<0) {
            dir = 2;
            setSprite(animations[1]);
        }
        else if(getSpeed().getY()<0) {
            dir = 3;
           setSprite(animations[2]);
        }
        else if(getSpeed().getY()>0) {
            dir = 4;
            setSprite(animations[3]);
        } else if (dir==1) {
            setSprite(animations[4]);

        }else if (dir==2) {
            setSprite(animations[5]);

        }else if (dir==3) {
            setSprite(animations[6]);

        }else if (dir==4) {
            setSprite(animations[7]);

        }

    }

    public double getStun() {
        return stun;
    }

    @Override
    protected void movementLogic() {
        if(getRoom().getPlayer()!=null) targetPos=getRoom().getPlayer().getPosition();
        if(targetPos!=null){
            attack();
            int xSpeed=0;
            int ySpeed=0;
            if(getPosition().addVector(getWidth()/2,getHeight()/2).distance(targetPos.addVector(getRoom().getPlayer().getWidth()/2,getRoom().getPlayer().getHeight()/2))<450) {
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
    protected void die() {
        super.die();
        if(Math.random()<0.9){
            new OverworldEssence(this,TileMap.loadImagesFromDirectory("Sprites/EssenceAnimation"));
        }
    }

    public int getDir() {
        return dir;
    }
    public void raiseAttack(int raise){
        attackStat+=raise;
    }
    @Override
    public void move() {
        attack.updateCooldown();
        if(stun<=0) {
            super.move();
            dir();
        }else stun-=deltaTime;
    }

    @Override
    public void hit(int damage) {
        super.hit(damage);

        if (dir==1) {
            setSprite(animations[8]);

        }else if (dir==2) {
            setSprite(animations[9]);

        }else if (dir==3) {
            setSprite(animations[10]);

        }else if (dir==4) {
            setSprite(animations[11]);
        }
        stun=0.2;
    }

    public Attack getAttack() {
        return attack;
    }

    public void attack(){
        attack.doAttack();
    }
}
