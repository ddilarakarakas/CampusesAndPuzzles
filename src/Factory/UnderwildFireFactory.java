package Factory;

public class UnderwildFireFactory implements CharacterMonsterFactory{
    @Override
    public Type creatType() {
        return new FireType();
    }

    @Override
    public Style creatStyle() {
        return new UnderwildStyle();
    }
}
