package Factory;

public class UnderwildNatureFactory implements CharacterMonsterFactory{
    @Override
    public Type creatType() {
        return new NatureType();
    }

    @Override
    public Style creatStyle() {
        return new UnderwildStyle();
    }
}
