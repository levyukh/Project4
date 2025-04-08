public class LeafEnemy extends Enemy{
    public LeafEnemy(Room room, int w, int h, int x, int y, int hp, int speed, int attack) {
        super(room, w, h, x, y, hp, speed, attack);
        animations=loadAnimations("Sprites/LeafEnemyAnimation");
        setAttack(new VineAoe(this));
        getSpeed().setVector(getSpeedStat(),0);
    }
    @Override
    protected void movementLogic() {
        if(collided()) flipSpeed();
        getSpeed().setVector(getSpeedStat(),0);
        attack();

    }
}
