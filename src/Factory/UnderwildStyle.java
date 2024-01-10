package Factory;

public class UnderwildStyle implements Style{
    private final String styleName = "Underwild";
    private final double strength = 0.8;
    private final double agility = 1.6;
    private final double health = 0.8;

    @Override
    public String GetStyleName() {
        return this.styleName;
    }

    @Override
    public double GetStrength() {
        return this.strength;
    }

    @Override
    public double GetAgility() {
        return this.agility;
    }

    @Override
    public double GetHealth() {
        return this.health;
    }
}
