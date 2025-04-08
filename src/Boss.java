public class Boss extends Enemy{
    public Boss(Room room, int w, int h, int x, int y, int hp, int speed, int attack) {
        super(room, w, h, x, y, hp, speed, attack);
        animations=loadAnimations("Sprites/FinalBoss");
        setAttack(new MoneyCannon(this));
    }

    @Override
    protected void die() {
        System.out.println("you won");
        getRoom().getPlayer().setGameState(3);
    }
}
