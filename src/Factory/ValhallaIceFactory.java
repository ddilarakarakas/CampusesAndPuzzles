package Factory;

public class ValhallaIceFactory implements CharacterMonsterFactory{
    @Override
    public Type creatType() {
        return new IceType();
    }

    @Override
    public Style creatStyle() {
        return new ValhallaStyle();
    }
}
