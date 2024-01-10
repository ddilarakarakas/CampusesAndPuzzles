import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Random;

public class Tiles {
    private String [][] gameArray;
    private Random random;
    private BufferedImage [][] images;

    /**
     * Tiles Class Constructor
     */
    public Tiles(){
        gameArray = new String[6][9];
        random = new Random();
        images = new BufferedImage[6][9];
        FillGameArray();
        while(controlMatches()){
            ControlInit();
        }
    }

    /**
     * Randomly filling the 2d string array where the game is played and the changes are kept
     * and the 2d Buffered Images array of the corresponding pictures
     */
    private void FillGameArray(){
        for(int i=0;i<6;i++){
            for(int j=0;j<9;j++){
                int temp = random.nextInt(3);
                try {
                    if(temp == 0) {//red
                        images[i][j] = ImageIO.read(new File("images/red-tile.png"));
                        gameArray[i][j] = "red";
                    }
                    else if(temp == 1){ //blue
                        images[i][j] = ImageIO.read(new File("images/blue-tile.png"));
                        gameArray[i][j] = "blue";
                    }
                    else{
                        images[i][j] = ImageIO.read(new File("images/green-tile.png"));
                        gameArray[i][j] = "green";
                    }
                }
                catch (IOException exc) {
                    System.out.println("Error opening image file: "+exc.getMessage());
                }
            }
        }
    }

    /**
     * Getter Game Array
     * @return String [][] game array
     */
    public String[][] getGameArray() {
        return gameArray;
    }

    /**
     * Setter value of game array
     * @param color String color
     * @param x index x
     * @param y index y
     */
    public void setGameArray(String color,int x,int y) {
        this.gameArray[y][x] = color;
    }

    /**
     * Getter Images
     * @return BufferedImage [][] images
     */
    public BufferedImage[][] getImages() {
        return images;
    }

    /**
     * Setter value of images
     * @param image BufferedImage images
     * @param x index x
     * @param y index y
     */
    public void setImages(BufferedImage image,int x,int y) {
        this.images[y][x] = image;
    }

    /**
     * Scanning the stones onto the screen
     * @param graphics Graphics
     */
    public void DrawTiles(Graphics graphics){
        for(int i=0;i<6;i++){
            for(int j=0;j<9;j++){
                graphics.drawImage(images[i][j],j*30,(i+7)*30,null);
            }
        }
    }

    /**
     * Playing the game according to the coordinates that the user enters with the mouse
     * @param mouseEventFirst First mouse click
     * @param mouseEventSecond Second mouse click
     * @param arr Includes characters and monsters on the board.
     * @param textArr String array to write Text Area
     * @return boolean
     */
    public boolean PlayGame(MouseEvent mouseEventFirst, MouseEvent mouseEventSecond, CharacterMonsterCreat[] arr, String [] textArr){
        int first_mouse_x_index = mouseEventFirst.getX() / 30;
        int first_mouse_y_index = (mouseEventFirst.getY() - 30) / 30 - 7;
        int second_mouse_x_index = mouseEventSecond.getX() / 30;
        int second_mouse_y_index = (mouseEventSecond.getY() - 30) / 30 - 7;
        if(ControlInputMouseClick(first_mouse_x_index,first_mouse_y_index,second_mouse_x_index,second_mouse_y_index)){
            String temp = gameArray[first_mouse_y_index][first_mouse_x_index];
            gameArray[first_mouse_y_index][first_mouse_x_index] = gameArray[second_mouse_y_index][second_mouse_x_index];
            gameArray[second_mouse_y_index][second_mouse_x_index] = temp;
            BufferedImage tempImage = images[first_mouse_y_index][first_mouse_x_index];
            images[first_mouse_y_index][first_mouse_x_index] = images[second_mouse_y_index][second_mouse_x_index];
            images[second_mouse_y_index][second_mouse_x_index] = tempImage;
            while(controlMatches()){
                controlMatchTiles(arr,textArr);
            }
        }
        return false;
    }

    /**
     * Controlling whether the values taken from the mouse are side by side
     * @param first_x First mouse click coordinate x
     * @param first_y First mouse click coordinate y
     * @param second_x Second mouse click coordinate x
     * @param second_y Second mouse click coordinate y
     * @return boolean
     */
    private boolean ControlInputMouseClick(int first_x, int first_y, int second_x, int second_y){
        if(first_x == second_x)
            return first_y == second_y - 1 || first_y == second_y + 1;
        if(first_y == second_y){
            return first_x == second_x - 1 || first_x == second_x + 1;
        }
        return false;
    }

    /**
     * Check if there are 3 same color stones next to each other.
     * @return true if there are 3 same color stones next to each other
     */
    public boolean controlMatches(){
        for(int i=0;i<6;i++){
            for(int j=0;j<9;j++){
                if(i+2 < 6){
                    if(gameArray[i][j].equals(gameArray[i+1][j])){ // down to equal color
                        if(gameArray[i][j].equals(gameArray[i+2][j])){
                            return true;
                        }
                    }
                }
                if(j+1 < 9 && j+2 < 9){
                    if(gameArray[i][j].equals(gameArray[i][j+1])){ // right to equal color
                        if(gameArray[i][j].equals(gameArray[i][j+2])){
                            return true;
                        }
                    }
                }
            }
        }
        return false;
    }

    /**
     * When initialize Check if there are 3 same color stones next to each other.
     */
    private void ControlInit(){
        for(int i=0;i<6;i++){
            for(int j=0;j<9;j++){
                if(i+2 < 6){
                    if(gameArray[i][j].equals(gameArray[i+1][j])){ // down to equal color
                        if(gameArray[i][j].equals(gameArray[i+2][j])){
                            try {
                                if(gameArray[i][j].equals("red")){
                                    gameArray[i][j] = "blue";
                                    images[i][j] = ImageIO.read(new File("images/blue-tile.png"));
                                }
                                else if(gameArray[i][j].equals("green")){
                                    images[i][j] = ImageIO.read(new File("images/red-tile.png"));
                                    gameArray[i][j] = "red";
                                }
                                else{
                                    images[i][j] = ImageIO.read(new File("images/green-tile.png"));
                                    gameArray[i][j] = "green";
                                }
                            }
                            catch (IOException exc) {
                                System.out.println("Error opening image file: "+exc.getMessage());
                            }
                        }
                    }
                }
                if(j+1 < 9 && j+2 < 9){
                    if(gameArray[i][j].equals(gameArray[i][j+1])){ // right to equal color
                        if(gameArray[i][j].equals(gameArray[i][j+2])){
                            try{
                                if(gameArray[i][j].equals("red")){
                                    gameArray[i][j] = "blue";
                                    images[i][j] = ImageIO.read(new File("images/blue-tile.png"));
                                }
                                else if(gameArray[i][j].equals("green")){
                                    images[i][j] = ImageIO.read(new File("images/red-tile.png"));
                                    gameArray[i][j] = "red";
                                }
                                else{
                                    images[i][j] = ImageIO.read(new File("images/green-tile.png"));
                                    gameArray[i][j] = "green";
                                }
                            }
                            catch (IOException exc) {
                                System.out.println("Error opening image file: "+exc.getMessage());
                            }
                        }
                    }
                }
            }
        }
    }

    /**
     * On the updated board, the stones that match three of the same color are deleted
     * and the scrolling process takes place from below.
     * Damage is applied to the corresponding monster accordingly.
     * @param arr Includes characters and monsters on the board.
     * @param textArr String array to write Text Area
     */
    private void controlMatchTiles(CharacterMonsterCreat[] arr,String [] textArr){
        for(int i=0;i<6;i++){
            for(int j=0;j<9;j++){
                if(i+2 < 6){
                    if(gameArray[i][j].equals(gameArray[i+1][j])){ // down to equal color
                        if(gameArray[i][j].equals(gameArray[i+2][j])){
                            scrollColumn1(i,j);
                            HealthUpdate(arr,j,i,1,textArr);
                        }
                    }
                }
                if(j+1 < 9 && j+2 < 9){
                    if(gameArray[i][j].equals(gameArray[i][j+1])){ // right to equal color
                        if(gameArray[i][j].equals(gameArray[i][j+2])){
                            scrollColumn3(i,j);
                            scrollColumn3(i,j+1);
                            scrollColumn3(i,j+2);
                            HealthUpdate(arr,j,i,3,textArr);
                        }
                    }
                }
            }
        }
    }

    /**
     * The corresponding monster's health is reduced. If the health is zero, the damage action is not applied.
     * @param arr Includes characters and monsters on the board.
     * @param index_x index x
     * @param index_y index y
     * @param control Checking how many stones match
     * @param textArr String array to write Text Area
     */
    private void HealthUpdate(CharacterMonsterCreat[] arr, int index_x, int index_y, int control,String[] textArr){
        if(control == 1){
            if(index_x < 4){
                if(arr[3].getHealth() != 0){
                    arr[3].UpdateHealth(gameArray[index_y][index_x]);
                    UpdateTextArea("Player damaged " + arr[3].getName(), textArr);
                    if(arr[3].getHealth() == 0){
                        UpdateTextArea("Monster " + arr[3].getName() + " died", textArr);
                    }
                }
            }
            else if(index_x > 5){
                if(arr[5].getHealth() != 0){
                    arr[5].UpdateHealth(gameArray[index_y][index_x]);
                    UpdateTextArea("Player damaged " + arr[5].getName(), textArr);
                    if(arr[5].getHealth() == 0){
                        UpdateTextArea("Monster " + arr[5].getName() + " died", textArr);
                    }
                }
            }
            else{
                if(arr[4].getHealth() != 0){
                    arr[4].UpdateHealth(gameArray[index_y][index_x]);
                    UpdateTextArea("Player damaged " + arr[4].getName(), textArr);
                    if(arr[4].getHealth() == 0){
                        UpdateTextArea("Monster " + arr[4].getName() + " died", textArr);
                    }
                }
            }
        }
        else{
            switch (index_x){
                case 0 -> {
                    if(arr[3].getHealth() != 0){
                        arr[3].UpdateHealth(gameArray[index_y][index_x]);
                        arr[3].UpdateHealth(gameArray[index_y][index_x]);
                        arr[3].UpdateHealth(gameArray[index_y][index_x]);
                        UpdateTextArea("Player damaged " + arr[3].getName(), textArr);
                        if(arr[3].getHealth() == 0){
                            UpdateTextArea("Monster " + arr[3].getName() + " died", textArr);
                        }
                    }
                }
                case 1 ->{
                    if(arr[3].getHealth() != 0 && arr[4].getHealth() != 0){
                        arr[3].UpdateHealth(gameArray[index_y][index_x]);
                        arr[3].UpdateHealth(gameArray[index_y][index_x]);
                        arr[4].UpdateHealth(gameArray[index_y][index_x]);
                        UpdateTextArea("Player damaged " + arr[3].getName(), textArr);
                        UpdateTextArea("Player damaged " + arr[4].getName(), textArr);
                        if(arr[3].getHealth() == 0){
                            UpdateTextArea("Monster " + arr[3].getName() + " died", textArr);
                        }
                        if(arr[4].getHealth() == 0){
                            UpdateTextArea("Monster " + arr[4].getName() + " died", textArr);
                        }
                    }
                }
                case 2 ->{
                    if(arr[3].getHealth() != 0 && arr[4].getHealth() != 0){
                        arr[3].UpdateHealth(gameArray[index_y][index_x]);
                        arr[4].UpdateHealth(gameArray[index_y][index_x]);
                        arr[4].UpdateHealth(gameArray[index_y][index_x]);
                        UpdateTextArea("Player damaged " + arr[3].getName(), textArr);
                        UpdateTextArea("Player damaged " + arr[4].getName(), textArr);
                        if(arr[3].getHealth() == 0){
                            UpdateTextArea("Monster " + arr[3].getName() + " died", textArr);
                        }
                        if(arr[4].getHealth() == 0){
                            UpdateTextArea("Monster " + arr[4].getName() + " died", textArr);
                        }
                    }
                }
                case 3 -> {
                    if(arr[4].getHealth() != 0){
                        arr[4].UpdateHealth(gameArray[index_y][index_x]);
                        arr[4].UpdateHealth(gameArray[index_y][index_x]);
                        arr[4].UpdateHealth(gameArray[index_y][index_x]);
                        UpdateTextArea("Player damaged " + arr[4].getName(), textArr);
                        if(arr[4].getHealth() == 0){
                            UpdateTextArea("Monster " + arr[4].getName() + " died", textArr);
                        }
                    }

                }
                case 4 ->{
                    if(arr[4].getHealth() != 0 && arr[5].getHealth() != 0){
                        arr[4].UpdateHealth(gameArray[index_y][index_x]);
                        arr[4].UpdateHealth(gameArray[index_y][index_x]);
                        arr[5].UpdateHealth(gameArray[index_y][index_x]);
                        UpdateTextArea("Player damaged " + arr[4].getName(), textArr);
                        UpdateTextArea("Player damaged " + arr[5].getName(), textArr);
                        if(arr[4].getHealth() == 0){
                            UpdateTextArea("Monster " + arr[4].getName() + " died", textArr);
                        }
                        if(arr[5].getHealth() == 0){
                            UpdateTextArea("Monster " + arr[5].getName() + " died", textArr);
                        }
                    }

                }
                case 5 ->{
                    if(arr[4].getHealth() != 0 && arr[5].getHealth() != 0){
                        arr[4].UpdateHealth(gameArray[index_y][index_x]);
                        arr[5].UpdateHealth(gameArray[index_y][index_x]);
                        arr[5].UpdateHealth(gameArray[index_y][index_x]);
                    }
                    UpdateTextArea("Player damaged " + arr[4].getName(), textArr);
                    UpdateTextArea("Player damaged " + arr[5].getName(), textArr);
                    if(arr[4].getHealth() == 0){
                        UpdateTextArea("Monster " + arr[4].getName() + " died", textArr);
                    }
                    if(arr[5].getHealth() == 0){
                        UpdateTextArea("Monster " + arr[5].getName() + " died", textArr);
                    }
                }
                case 6 -> {
                    if(arr[5].getHealth() != 0){
                        arr[5].UpdateHealth(gameArray[index_y][index_x]);
                        arr[5].UpdateHealth(gameArray[index_y][index_x]);
                        arr[5].UpdateHealth(gameArray[index_y][index_x]);
                        UpdateTextArea("Player damaged " + arr[5].getName(), textArr);
                        if(arr[5].getHealth() == 0){
                            UpdateTextArea("Monster " + arr[5].getName() + " died", textArr);
                        }
                    }
                }
                default -> {
                }
            }
        }
    }

    /**
     * Swipe action in top-down match
     * @param index_i index i
     * @param index_j index j
     */
    public void scrollColumn1(int index_i, int index_j){
        for(int i=index_i;i<6;i++){
            if(i+1 < 6){
                gameArray[i][index_j] = gameArray[i+1][index_j];
                images[i][index_j] = images[i+1][index_j];
            }
            else{
                int temp = random.nextInt(3);
                try {
                    if(temp == 0) {//red
                        images[i][index_j] = ImageIO.read(new File("images/red-tile.png"));
                        gameArray[i][index_j] = "red";
                    }
                    else if(temp == 1){ //blue
                        images[i][index_j] = ImageIO.read(new File("images/blue-tile.png"));
                        gameArray[i][index_j] = "blue";
                    }
                    else{
                        images[i][index_j] = ImageIO.read(new File("images/green-tile.png"));
                        gameArray[i][index_j] = "green";
                    }
                }
                catch (IOException exc) {
                    System.out.println("Error opening image file: "+exc.getMessage());
                }
            }
        }
    }

    /**
     * Swipe action in left to right match
     * @param index_i index i
     * @param index_j index j
     */
    public void scrollColumn3(int index_i,int index_j){
        for(int i=index_i;i<6;i++){
            if(i+3 < 6){
                gameArray[i][index_j] = gameArray[i+3][index_j];
                images[i][index_j] = images[i+3][index_j];
            }
            else{
                int temp = random.nextInt(3);
                try {
                    if(temp == 0) {//red
                        images[i][index_j] = ImageIO.read(new File("images/red-tile.png"));
                        gameArray[i][index_j] = "red";
                    }
                    else if(temp == 1){ //blue
                        images[i][index_j] = ImageIO.read(new File("images/blue-tile.png"));
                        gameArray[i][index_j] = "blue";
                    }
                    else{
                        images[i][index_j] = ImageIO.read(new File("images/green-tile.png"));
                        gameArray[i][index_j] = "green";
                    }
                }
                catch (IOException exc) {
                    System.out.println("Error opening image file: "+exc.getMessage());
                }
            }
        }
    }
    /**
     * Update Text Array for Text Area
     * @param mess New Message
     */
    public void UpdateTextArea(String mess,String[] textArray){
        int index = -1;
        if(textArray[0].equals("\n")){
            textArray[0] = mess;
        }
        else{
            for(int i=0;i<textArray.length;i++){
                if(textArray[i].equals("\n")){
                    index = i;
                    break;
                }
            }
            if(index == -1){
                for(int i=1;i<textArray.length;i++){
                    textArray[i-1] = textArray[i];
                }
                textArray[textArray.length-1] = mess;
            }
            else{
                textArray[index] = mess;
            }
        }
    }
}
