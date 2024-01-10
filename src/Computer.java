import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.util.Random;

public class Computer {
    private Tiles tiles;
    private Random random;

    /**
     * Computer Class Constructor
     * @param tiles Tiles object
     */
    public Computer(Tiles tiles){
        this.tiles = tiles;
        random = new Random();
    }

    /**
     * It is the function that the computer moves.
     * @param arr Includes characters and monsters on the board.
     * @param textArr String array to write Text Area
     */
    public void PlayGameComputer(CharacterMonsterCreat[] arr, String [] textArr){
        MakeMove();
        if(!tiles.controlMatches()){
            tiles.UpdateTextArea("Computer made an invalid move.",textArr);
        }
        while (tiles.controlMatches()){
            MatchTiles(arr, textArr);
        }
    }

    /**
     * The computer finds the first move that can be made on the board and generates its coordinates.
     * Board is updated
     */
    private void MakeMove(){
        int x_coordinate_first = 0;
        int y_coordinate_first = 0;
        int x_coordinate_second = 0;
        int y_coordinate_second = 0;
        for(int i=0;i<6;i++){
            for(int j=0;j<9;j++){
                if(i+3 < 6){ // down
                    if(tiles.getGameArray()[i][j].equals(tiles.getGameArray()[i+2][j])){
                        if(tiles.getGameArray()[i][j].equals(tiles.getGameArray()[i+3][j])){
                            x_coordinate_first = j;
                            y_coordinate_first = i;
                            x_coordinate_second = j;
                            y_coordinate_second = i + 1;
                            break;
                        }
                    }
                }
                if(j+3<9){
                    if(tiles.getGameArray()[i][j].equals(tiles.getGameArray()[i][j+2])){
                        if(tiles.getGameArray()[i][j].equals(tiles.getGameArray()[i][j+3])){
                            x_coordinate_first = j;
                            y_coordinate_first = i;
                            x_coordinate_second = j+1;
                            y_coordinate_second = i;
                            break;
                        }
                    }
                }
            }
        }
        String temp = tiles.getGameArray()[y_coordinate_first][x_coordinate_first];
        tiles.setGameArray(tiles.getGameArray()[y_coordinate_second][x_coordinate_second],x_coordinate_first,y_coordinate_first);
        tiles.setGameArray(temp,x_coordinate_second,y_coordinate_second);

        BufferedImage temp_image = tiles.getImages()[y_coordinate_first][x_coordinate_first];
        tiles.setImages(tiles.getImages()[y_coordinate_second][x_coordinate_second],x_coordinate_first,y_coordinate_first);
        tiles.setImages(temp_image,x_coordinate_second,y_coordinate_second);
    }

    /**
     * On the updated board, the stones that match three of the same color are deleted
     * and the scrolling process takes place from below.
     * Damage is applied to the corresponding character accordingly.
     * @param arr Includes characters and monsters on the board.
     * @param textArr String array to write Text Area
     */
    private void MatchTiles(CharacterMonsterCreat[] arr, String [] textArr){
        for(int i=0;i<6;i++){
            for(int j=0;j<9;j++){
                if(i+2 < 6){
                    if(tiles.getGameArray()[i][j].equals(tiles.getGameArray()[i+1][j])){
                        if(tiles.getGameArray()[i][j].equals(tiles.getGameArray()[i+2][j])){
                            tiles.scrollColumn1(i,j);
                            HealthUpdate(arr,j,i,1,textArr);
                        }
                    }
                }
                if(j+1 < 9 && j+2 < 9){
                    if(tiles.getGameArray()[i][j].equals(tiles.getGameArray()[i][j+1])){
                        if(tiles.getGameArray()[i][j].equals(tiles.getGameArray()[i][j+2])){
                            tiles.scrollColumn3(i,j);
                            tiles.scrollColumn3(i,j+1);
                            tiles.scrollColumn3(i,j+2);
                            HealthUpdate(arr,j,i,3,textArr);
                        }
                    }
                }
            }
        }
    }

    /**
     * The corresponding character's health is reduced. If the health is zero, the damage action is not applied.
     * @param arr Includes characters and monsters on the board.
     * @param index_x index x
     * @param index_y index y
     * @param control Checking how many stones match
     * @param textArr String array to write Text Area
     */
    private void HealthUpdate(CharacterMonsterCreat[] arr, int index_x, int index_y, int control,String[] textArr){
        String [][] gameArray = tiles.getGameArray();
        if(control == 1){
            if(index_x < 4){
                if(arr[0].getHealth() != 0){
                    arr[0].UpdateHealth(gameArray[index_y][index_x]);
                    tiles.UpdateTextArea("Computer damaged " + arr[0].getName(), textArr);
                    if(arr[0].getHealth() == 0){
                        tiles.UpdateTextArea("Character " + arr[0].getName() + " died", textArr);
                    }
                }
            }
            else if(index_x > 5){
                if(arr[2].getHealth() != 0){
                    arr[2].UpdateHealth(gameArray[index_y][index_x]);
                    tiles.UpdateTextArea("Computer damaged " + arr[2].getName(), textArr);
                    if(arr[2].getHealth() == 0){
                        tiles.UpdateTextArea("Character " + arr[2].getName() + " died", textArr);
                    }
                }
            }
            else{
                if(arr[1].getHealth() != 0){
                    arr[1].UpdateHealth(gameArray[index_y][index_x]);
                    tiles.UpdateTextArea("Computer damaged " + arr[1].getName(), textArr);
                    if(arr[1].getHealth() == 0){
                        tiles.UpdateTextArea("Character " + arr[1].getName() + " died", textArr);
                    }
                }
            }
        }
        else{
            switch (index_x){
                case 0 -> {
                    if(arr[0].getHealth() != 0){
                        arr[0].UpdateHealth(gameArray[index_y][index_x]);
                        arr[0].UpdateHealth(gameArray[index_y][index_x]);
                        arr[0].UpdateHealth(gameArray[index_y][index_x]);
                        tiles.UpdateTextArea("Computer damaged " + arr[0].getName(), textArr);
                        if(arr[0].getHealth() == 0){
                            tiles.UpdateTextArea("Character " + arr[0].getName() + " died", textArr);
                        }
                    }
                }
                case 1 ->{
                    if(arr[0].getHealth() != 0 && arr[1].getHealth() != 0){
                        arr[0].UpdateHealth(gameArray[index_y][index_x]);
                        arr[0].UpdateHealth(gameArray[index_y][index_x]);
                        arr[1].UpdateHealth(gameArray[index_y][index_x]);
                        tiles.UpdateTextArea("Computer damaged " + arr[0].getName(), textArr);
                        tiles.UpdateTextArea("Computer damaged " + arr[1].getName(), textArr);
                        if(arr[0].getHealth() == 0){
                            tiles.UpdateTextArea("Character " + arr[0].getName() + " died", textArr);
                        }
                        if(arr[1].getHealth() == 0){
                            tiles.UpdateTextArea("Character " + arr[1].getName() + " died", textArr);
                        }
                    }
                }
                case 2 ->{
                    if(arr[0].getHealth() != 0 && arr[1].getHealth() != 0){
                        arr[0].UpdateHealth(gameArray[index_y][index_x]);
                        arr[1].UpdateHealth(gameArray[index_y][index_x]);
                        arr[1].UpdateHealth(gameArray[index_y][index_x]);
                        tiles.UpdateTextArea("Computer damaged " + arr[0].getName(), textArr);
                        tiles.UpdateTextArea("Computer damaged " + arr[1].getName(), textArr);
                        if(arr[0].getHealth() == 0){
                            tiles.UpdateTextArea("Character " + arr[0].getName() + " died", textArr);
                        }
                        if(arr[1].getHealth() == 0){
                            tiles.UpdateTextArea("Character " + arr[1].getName() + " died", textArr);
                        }
                    }
                }
                case 3 -> {
                    if(arr[1].getHealth() != 0){
                        arr[1].UpdateHealth(gameArray[index_y][index_x]);
                        arr[1].UpdateHealth(gameArray[index_y][index_x]);
                        arr[1].UpdateHealth(gameArray[index_y][index_x]);
                        tiles.UpdateTextArea("Computer damaged " + arr[1].getName(), textArr);
                        if(arr[1].getHealth() == 0){
                            tiles.UpdateTextArea("Character " + arr[1].getName() + " died", textArr);
                        }
                    }

                }
                case 4 ->{
                    if(arr[1].getHealth() != 0 && arr[2].getHealth() != 0){
                        arr[1].UpdateHealth(gameArray[index_y][index_x]);
                        arr[1].UpdateHealth(gameArray[index_y][index_x]);
                        arr[2].UpdateHealth(gameArray[index_y][index_x]);
                        tiles.UpdateTextArea("Computer damaged " + arr[1].getName(), textArr);
                        tiles.UpdateTextArea("Computer damaged " + arr[2].getName(), textArr);
                        if(arr[1].getHealth() == 0){
                            tiles.UpdateTextArea("Character " + arr[1].getName() + " died", textArr);
                        }
                        if(arr[2].getHealth() == 0){
                            tiles.UpdateTextArea("Character " + arr[2].getName() + " died", textArr);
                        }
                    }

                }
                case 5 ->{
                    if(arr[1].getHealth() != 0 && arr[2].getHealth() != 0){
                        arr[1].UpdateHealth(gameArray[index_y][index_x]);
                        arr[2].UpdateHealth(gameArray[index_y][index_x]);
                        arr[2].UpdateHealth(gameArray[index_y][index_x]);
                    }
                    tiles.UpdateTextArea("Computer damaged " + arr[1].getName(), textArr);
                    tiles.UpdateTextArea("Computer damaged " + arr[2].getName(), textArr);
                    if(arr[1].getHealth() == 0){
                        tiles.UpdateTextArea("Character " + arr[1].getName() + " died", textArr);
                    }
                    if(arr[2].getHealth() == 0){
                        tiles.UpdateTextArea("Character " + arr[2].getName() + " died", textArr);
                    }
                }
                case 6 -> {
                    if(arr[2].getHealth() != 0){
                        arr[2].UpdateHealth(gameArray[index_y][index_x]);
                        arr[2].UpdateHealth(gameArray[index_y][index_x]);
                        arr[2].UpdateHealth(gameArray[index_y][index_x]);
                        tiles.UpdateTextArea("Computer damaged " + arr[2].getName(), textArr);
                        if(arr[2].getHealth() == 0){
                            tiles.UpdateTextArea("Character " + arr[2].getName() + " died", textArr);
                        }
                    }
                }
                default -> {
                }
            }
        }
    }

}
