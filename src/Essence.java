public class Essence {
    private Attack attack;
    private int attackRaise;
    private int hpRaise;
    private int speedRaise;
    public Essence(Enemy enemy) {
        attack=enemy.getAttack();
        attackRaise=(enemy.getAttackStat()/2)+1;
        hpRaise=1;
        speedRaise=50;
    }

    public Attack getAttack() {
        return attack;
    }

    public int getAttackRaise() {
        return attackRaise;
    }

    public int getHpRaise() {
        return hpRaise;
    }

    public int getSpeedRaise() {
        return speedRaise;
    }
}
