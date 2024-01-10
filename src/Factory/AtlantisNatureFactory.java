package Factory;

public class AtlantisNatureFactory implements CharacterMonsterFactory{
    @Override
    public Type creatType() {
        return new NatureType();
    }

    @Override
    public Style creatStyle() {
        return new AtlantisStyle();
    }
}
