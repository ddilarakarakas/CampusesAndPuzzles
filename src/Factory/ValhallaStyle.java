package Factory;

public class ValhallaStyle implements Style{
    private final String styleName = "Valhalla";
    private final double strength = 1.3;
    private final double agility = 0.4;
    private final double health = 1.3;

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
