package Factory;

public class NatureType implements Type{
    private final String styleName = "green";
    private final int strength = 75;
    private final int agility = 100;
    private final int health = 125;
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
