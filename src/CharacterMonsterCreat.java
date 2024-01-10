import Factory.*;
 import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Random;

public class CharacterMonsterCreat {
    private CharacterMonsterFactory character;
    private Random random;
    private String type;
    private int coordinate_x;
    private int coordinate_y;
    private BufferedImage image;
    private Style style;
    private Type type_;
    private double strength;
    private double agility;
    private double health;
    private double damage;
    private String name;

    /**
     * CharacterMonsterCreat Class Constructor
     * @param type character or monster
     * @param x coordinate x
     * @param y coordinate y
     */
    public CharacterMonsterCreat(String type, int x, int y){
        this.type = type;
        this.random = new Random();
        this.coordinate_x = x;
        this.coordinate_y = y;
        int randomNumber = random.nextInt(9);
        switch (randomNumber){
            case 0 -> character = new AtlantisFireFactory();
            case 1 -> character = new AtlantisIceFactory();
            case 2 -> character = new AtlantisNatureFactory();
            case 3 -> character = new UnderwildFireFactory();
            case 4 -> character = new UnderwildIceFactory();
            case 5 -> character = new UnderwildNatureFactory();
            case 6 -> character = new ValhallaFireFactory();
            case 7 -> character = new ValhallaIceFactory();
            case 8 -> character = new ValhallaNatureFactory();
        }
        AddImage(randomNumber);
        Init();
    }

    public String getName() {
        return name;
    }

    public Type getType_() {
        return type_;
    }

    /**
     * Add image random
     * @param random random number
     */
    private void AddImage(int random){
        try {
            if(random == 0){
                if(type.equals("monster"))
                    image = ImageIO.read(new File("images/atlantis-fire-monster.png"));
                else
                    image = ImageIO.read(new File("images/atlantis-fire-char.png"));
            }
            else if (random == 1) {
                if(type.equals("monster"))
                    image = ImageIO.read(new File("images/atlantis-ice-monster.png"));
                else
                    image = ImageIO.read(new File("images/atlantis-ice-char.png"));
            }
            else if(random == 2){
                if(type.equals("monster"))
                    image = ImageIO.read(new File("images/atlantis-nature-monster.png"));
                else
                    image = ImageIO.read(new File("images/atlantis-nature-char.png"));
            }
            else if(random == 3){
                if(type.equals("monster"))
                    image = ImageIO.read(new File("images/underwild-fire-monster.png"));
                else
                    image = ImageIO.read(new File("images/underwild-fire-char.png"));
            }
            else if (random == 4) {
                if(type.equals("monster"))
                    image = ImageIO.read(new File("images/underwild-ice-monster.png"));
                else
                    image = ImageIO.read(new File("images/underwild-ice-char.png"));
            }
            else if(random == 5){
                if(type.equals("monster"))
                    image = ImageIO.read(new File("images/underwild-nature-monster.png"));
                else
                    image = ImageIO.read(new File("images/underwild-nature-char.png"));
            }
            else if(random == 6){
                if(type.equals("monster"))
                    image = ImageIO.read(new File("images/valhalla-fire-monster.png"));
                else
                    image = ImageIO.read(new File("images/valhalla-fire-char.png"));
            }
            else if (random == 7) {
                if(type.equals("monster"))
                    image = ImageIO.read(new File("images/valhalla-ice-monster.png"));
                else
                    image = ImageIO.read(new File("images/valhalla-ice-char.png"));
            }
            else if (random == 8){
                if(type.equals("monster"))
                    image = ImageIO.read(new File("images/valhalla-nature-monster.png"));
                else
                    image = ImageIO.read(new File("images/valhalla-nature-char.png"));
            }
        }
        catch (IOException exc) {
            System.out.println("Error opening image file: "+exc.getMessage());
        }
    }

    /**
     * Initialize (creat)
     */
    private void Init(){
        style = character.creatStyle();
        type_ = character.creatType();
        strength = style.GetStrength() * type_.GetStrength();
        health = style.GetHealth() * type_.GetHealth();
        agility = style.GetAgility() * type_.GetAgility();
        damage = 100 * Math.pow((strength / agility), 1.35);
        if(type_.GetTypeName().equals("blue")){
            name = style.GetStyleName() + "-Ice";
        }
        else if(type_.GetTypeName().equals("red")){
            name = style.GetStyleName() + "-Fire";
        }
        else if(type_.GetTypeName().equals("green")){
            name = style.GetStyleName() + "-Nature " ;
        }
    }

    /**
     * Screen printing of character or monster
     * @param graphics Graphics object
     */
    public void PaintCharMonster(Graphics graphics){
        graphics.drawImage(image,coordinate_x,coordinate_y,null);
    }

    /**
     * Calculate damage and drop health according to the color of the matched stone
     * @param color tiles color
     */
    public void UpdateHealth(String color){
        if(color.equals("red")){
            if(type_.GetTypeName().equals(color)){ // red -> red x1 damage
                health -= damage;
            }
            else if(type_.GetTypeName().equals("green")){ // red -> green x2 damage
                health -= 2*damage;
            }
            else{ // red -> blue x1/2 damage
                health -= damage/2;
            }
        }
        else if(color.equals("blue")){
            if(getType_().GetTypeName().equals(color)){ //blue -> blue x1 damage
                health -= damage;
            }
            else if(type_.GetTypeName().equals("red")){ //blue -> red x2 damage
                health -= 2*damage;
            }
            else{ // blue -> green x1/2 damage
                health -= damage/2;
            }
        }
        else{ // green
            if(type_.GetTypeName().equals(color)){ // green -> green x1 damage
                health -= damage;
            }
            else if(type_.GetTypeName().equals("blue")){ // green -> blue x2 damage
                health -= 2*damage;
            }
            else{ // green -> red x1/2 damage
                health -= damage/2;
            }
        }
        if(health < 0){
            health = 0;
        }
    }

    public double getHealth() {
        return health;
    }
}
