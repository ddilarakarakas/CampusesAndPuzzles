package Factory;

public class AtlantisIceFactory implements CharacterMonsterFactory{

    @Override
    public Type creatType() {
        return new IceType();
    }

    @Override
    public Style creatStyle() {
        return new AtlantisStyle();
    }
}
