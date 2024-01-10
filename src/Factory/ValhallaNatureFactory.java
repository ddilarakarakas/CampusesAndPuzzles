package Factory;

public class ValhallaNatureFactory implements CharacterMonsterFactory{
    @Override
    public Type creatType() {
        return new NatureType();
    }

    @Override
    public Style creatStyle() {
        return new ValhallaStyle();
    }
}
