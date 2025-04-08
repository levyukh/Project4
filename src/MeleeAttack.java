public class MeleeAttack extends Attack{
    public MeleeAttack(Enemy attacker) {
        super(attacker);
    }

    @Override
    public void doAttack() {
        if(canAttack()){

            int xSpawn;
            int ySpawn;
            int width=100;
            int height=60;
            if(getAttacker().getDir()==1){
                width^=height;
                height^=width;
                width^=height;
                ySpawn=getAttacker().getY();
                xSpawn=getAttacker().getX()+getAttacker().getWidth();

            }else if(getAttacker().getDir()==2){
                width^=height;
                height^=width;
                width^=height;
                ySpawn=getAttacker().getY();
                xSpawn=getAttacker().getX()-width;
            }else if(getAttacker().getDir()==3){
                ySpawn=getAttacker().getY()-height;
                xSpawn=getAttacker().getX();
            }else {
                ySpawn=getAttacker().getY()+getAttacker().getHeight();
                xSpawn=getAttacker().getX();
            }

            new DamagableZone(getAttacker().getRoom(),width,height,xSpawn,ySpawn,TileMap.loadImagesFromDirectory("Sprites/MeleeAttack"),2,0.1,getAttacker());
            cantAttack();
            setCooldown(1);
        }
    }
}
