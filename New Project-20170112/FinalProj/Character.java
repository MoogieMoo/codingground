public abstract class Character {

    protected String name;
    protected char symbol;
    protected int health;
    protected int attack;
    public abstract int adjustHealth();
    public abstract void die();

}
