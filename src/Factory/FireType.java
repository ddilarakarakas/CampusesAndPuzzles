package Factory;

public class FireType implements Type{
    private final String styleName = "red";
    private final int strength = 100;
    private final int agility = 125;
    private final int health = 75;
    @Override
    public String GetTypeName() {
        return this.styleName;
    }

    @Override
    public int GetStrength() {
        return this.strength;
    }

    @Override
    public int GetAgility() {
        return this.agility;
    }

    @Override
    public int GetHealth() {
        return this.health;
    }
}
