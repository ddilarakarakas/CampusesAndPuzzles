package Factory;

public class ValhallaFireFactory implements CharacterMonsterFactory{
    @Override
    public Type creatType() {
        return new FireType();
    }

    @Override
    public Style creatStyle() {
        return new ValhallaStyle();
    }
}
