package Factory;

public class IceType implements Type{
    private final String styleName = "blue";
    private final int strength = 125;
    private final int agility = 75;
    private final int health = 100;
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
