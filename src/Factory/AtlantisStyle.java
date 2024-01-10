package Factory;

public class AtlantisStyle implements Style{
    private final String styleName = "Atlantis";
    private final double strength = 0.8;
    private final double agility = 1.2;
    private final double health = 1.2;
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
