package Factory;

public class AtlantisFireFactory implements CharacterMonsterFactory{
    @Override
    public Type creatType() {
        return new FireType();
    }

    @Override
    public Style creatStyle() {
        return new AtlantisStyle();
    }
}
