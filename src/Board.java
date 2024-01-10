import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Arrays;

public class Board extends JPanel implements ActionListener, MouseListener {
    private final int BOARD_W = 270;
    private final int BOARD_H = 590;
    private final int DELAY = 5;
    private Timer timer;
    private CharacterMonsterCreat character1;
    private CharacterMonsterCreat character2;
    private CharacterMonsterCreat character3;
    private CharacterMonsterCreat monster1;
    private CharacterMonsterCreat monster2;
    private CharacterMonsterCreat monster3;
    private Graphics graphics;
    private Tiles tiles;
    private boolean mouseClick = false;
    private MouseEvent mouseEventFirst;
    private MouseEvent mouseEventSecond;
    private boolean mouseControlFirst = true;
    private CharacterMonsterCreat[] monsters_characters;
    private JTextArea textArea;
    private String [] textAreaArr;
    private Computer computer;
    private boolean computerPlaying = false;

    /**
     * Board Class Constructor
     */
    public Board(){
        setPreferredSize(new Dimension(BOARD_W,BOARD_H));
        setBackground(Color.black);
        timer = new Timer(DELAY, this);
        timer.start();
        graphics = null;
        tiles = new Tiles();
        TextInit();
        this.add(textArea);
        textAreaArr = new String[12];
        Arrays.fill(textAreaArr, "\n");
        InitCharacterMonster();
        computer = new Computer(tiles);
    }

    /**
     * Swing constantly calls this function to refresh the window continuously.
     * @param g Graphics
     */
    @Override
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        this.graphics = g;
        textArea.setBounds(0, 390, BOARD_W, 200);
        if (TextCombined() != null) {
            textArea.setText(TextCombined());
        }
        DrawCharacterMonster(graphics);
        tiles.DrawTiles(graphics);
        if(computerPlaying){
            tiles.DrawTiles(graphics);
            computerPlaying = false;
        }

        Toolkit.getDefaultToolkit().sync();
    }

    /**
     * Swing constantly calls this function to update the window's background work
     * @param e ActionEvent
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        if(mouseClick){
            if(mouseEventFirst.getX() <= 270 && mouseEventFirst.getX() >= 0){
                if(mouseEventSecond.getX() <= 270 && mouseEventSecond.getX() >= 0){
                    if(mouseEventFirst.getY() <= 420 && mouseEventFirst.getY() >= 240){
                        if(mouseEventSecond.getY() <= 420 && mouseEventSecond.getY() >= 240){
                            UpdateTextArea("Player playing...");
                            mouseClick = tiles.PlayGame(mouseEventFirst,mouseEventSecond,monsters_characters,textAreaArr);
                            computerPlaying = true;
                            UpdateTextArea("Computer playing...");
                            computer.PlayGameComputer(monsters_characters,textAreaArr);

                        }
                     }
                }
            }
            mouseClick = false;
        }
        repaint();
    }

    /**
     * Function of Mouse Listener Interface
     * @param e MouseEvent
     */
    @Override
    public void mouseClicked(MouseEvent e) {
        /*if(mouseControlFirst){
            mouseEventFirst = e;
            mouseControlFirst = false;
        }
        else{
            mouseClick = true;
            mouseControlFirst = true;
            mouseEventSecond = e;
        }*/
    }

    /**
     * Function of Mouse Listener Interface
     * @param e MouseEvent
     */
    @Override
    public void mousePressed(MouseEvent e) {
        if(mouseControlFirst){
            mouseEventFirst = e;
            mouseControlFirst = false;
        }
        else{
            mouseClick = true;
            mouseControlFirst = true;
            mouseEventSecond = e;
        }
    }

    /**
     * Function of Mouse Listener Interface
     * @param e MouseEvent
     */
    @Override
    public void mouseReleased(MouseEvent e) {

    }

    /**
     * Function of Mouse Listener Interface
     * @param e MouseEvent
     */
    @Override
    public void mouseEntered(MouseEvent e) {

    }

    /**
     * Function of Mouse Listener Interface
     * @param e MouseEvent
     */
    @Override
    public void mouseExited(MouseEvent e) {

    }

    /**
     * Initialize Character and Monster
     */
    private void InitCharacterMonster(){
        monsters_characters = new CharacterMonsterCreat[6];
        character1 = new CharacterMonsterCreat("character",0,10);
        character2 = new CharacterMonsterCreat("character",90,10);
        character3 = new CharacterMonsterCreat("character",180,10);
        monster1 = new CharacterMonsterCreat("monster",0,110);
        monster2 = new CharacterMonsterCreat("monster",90,110);
        monster3 = new CharacterMonsterCreat("monster",180,110);
        monsters_characters[0] = character1;
        monsters_characters[1] = character2;
        monsters_characters[2] = character3;
        monsters_characters[3] = monster1;
        monsters_characters[4] = monster2;
        monsters_characters[5] = monster3;
        //System.out.println(textAreaArr[0]);
        UpdateTextArea("Character 1 : " + character1.getName());
        UpdateTextArea("Character 2 : " + character2.getName());
        UpdateTextArea("Character 3 : " + character3.getName());
        UpdateTextArea("Monster 1 : " + monster1.getName());
        UpdateTextArea("Monster 2 : " + monster2.getName());
        UpdateTextArea("Monster 3 : " + monster3.getName());
    }

    /**
     * Screen printing of characters and monsters
     * @param graphics Graphics object
     */
    private void DrawCharacterMonster(Graphics graphics){
        character1.PaintCharMonster(graphics);
        character2.PaintCharMonster(graphics);
        character3.PaintCharMonster(graphics);
        monster1.PaintCharMonster(graphics);
        monster2.PaintCharMonster(graphics);
        monster3.PaintCharMonster(graphics);
    }

    /**
     * JTextArea Initialize
     */
    private void TextInit(){
        textArea = new JTextArea();
        textArea.setBackground(Color.black);
        textArea.setForeground(Color.white);
        textArea.setEnabled(false);
        textArea.setVisible(true);
    }

    /**
     * Makes the elements of the string array into a one string.
     * @return String
     */
    private String TextCombined(){
        String s = " ";
        if(textAreaArr[0] != null){
            for (String value : textAreaArr) {
                s = s + value + "\n ";
            }
        }
        return s;
    }

    /**
     * Update Text Array for Text Area
     * @param mess New Message
     */
    private void UpdateTextArea(String mess){
        int index = -1;
        if(textAreaArr[0].equals("\n")){
            textAreaArr[0] = mess;
        }
        else{
            for(int i=0;i<textAreaArr.length;i++){
                if(textAreaArr[i].equals("\n")){
                    index = i;
                    break;
                }
            }
            if(index == -1){
                for(int i=1;i<textAreaArr.length;i++){
                    textAreaArr[i-1] = textAreaArr[i];
                }
                textAreaArr[textAreaArr.length-1] = mess;
            }
            else{
                textAreaArr[index] = mess;
            }
        }
    }
}
