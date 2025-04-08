public class VineAoe extends Attack{
    public VineAoe(Enemy attacker) {
        super(attacker);
    }


    @Override
    public void doAttack() {
        if(canAttack()) {
            new DamagableZone(getAttacker().getRoom(), 200 + getAttacker().getWidth(), 200 + getAttacker().getWidth(), getAttacker().getX() - (getAttacker().getWidth()/2), getAttacker().getY() -(getAttacker().getHeight()/2), TileMap.loadImagesFromDirectory("Sprites/VineAnim"), getAttacker().getAttackStat() + 1, 0.5, getAttacker());
            cantAttack();
            setCooldown(3);
        }
    }
}
