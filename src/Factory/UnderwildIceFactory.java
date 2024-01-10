package Factory;

public class UnderwildIceFactory implements CharacterMonsterFactory{
    @Override
    public Type creatType() {
        return new IceType();
    }

    @Override
    public Style creatStyle() {
        return new UnderwildStyle();
    }
}
