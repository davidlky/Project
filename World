import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.io.File;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.Arrays;
import java.io.FileNotFoundException;
/**
 * Imports and load up a map that is playable. Loaded with textfiles (*.txt). 
 * 
 * @author David Liu
 * @version beta 1.2
 */
public class Map extends SWorld
{
    //variables
    private Tile[][] tiles;
    private char[][] map;
    private Player player = new Player();
    private int stage;//stage number
    private boolean paused = false; //is the game paused
    
    /**
     * When everything is first loaded up, changes the screen to the Title Screen
     */
    public Map()
    {   
        super(720, 480, 1, 1855 ,3700); 
        Greenfoot.setWorld(new Screen(2));
    }
    
    /**
     * Starts a map with the asked file and adds the player's x and y location
     * 
     * @param fileName The name of the file
     * @param xq The x location of the player
     * @param yq The y location of the player
     */
    public Map (String fileName, int xq, int yq){
        super(720, 480, 1, 6000 ,1800);   
        map= importMap(fileName);
        stage = Character.getNumericValue(fileName.charAt(3));//thats the stage
        addMainActor(player,xq,yq,0,0);
        GreenfootImage bg = new GreenfootImage("bk.png");
        setScrollingBackground(bg);
        loadMap();
        addObject(new HUD(),720/2,20);
    }
    
    /**
     * Loads up the map with the given array of chars
     */
    private void loadMap(){
        tiles = new Tile[map[0].length][map.length];//new tile grid with size
        for (int y = 0; y < tiles.length; y++){
            for (int x = 0; x < tiles[y].length; x++){
                switch (map[x][y]){//find the value at the certain point
                    case '1'://tile type 1 - horizontal
                    addObject (new Tile(1),48*y+24,48*x+24);
                    break;
                    case '2'://tile type 2 - verticle
                    addObject (new Tile(2),48*y+24,48*x+24);                   
                    break;
                    case '3'://tile type 3 - end tiles
                    addObject (new Tile(3),48*y+24,48*x+24);                    
                    break;
                    case '4':
                    addObject (new Tile(4),48*y+24,48*x+24);                    
                    break;
                    case '5':
                    System.out.println(map[x][y]);
                    addObject (new Tile(5),48*y+24,48*x+24);                   
                    break;
                    case '6':
                    addObject (new Tile(6),48*y+24,48*x+24);                    
                    break;
                    case 'd'://door type 1 - faces left
                    addObject(new Door(stage, 1), 48*y+24,48*x);                        
                    case 'p'://door type 2 - faces right
                    addObject(new Door(stage, 2), 48*y+24,48*x);   
                    break;
                }
            }
        }
    }
    
    /**
     * Reads the txt file for the map, will report if file is invalid(not found)
     * 
     * @param fileName The name of the text file to be imported from
     * @return chars[][] the arrays of blocks to be loaded with the loadMap()
     */
    private char[][] importMap(String fileName){
        ArrayList<String> lines = new ArrayList<String>();//add all the lines of teh text file to this
        try{
            Scanner scanFile = new Scanner(new File(fileName));
            while(scanFile.hasNext()){
                lines.add(scanFile.next());//load all the lines 
            }            
            scanFile.close();
        }catch (FileNotFoundException e){
            System.out.println("invalid File");
        }
        char[][] importedMap = new char[0][0];
        for (int i = 0; i<lines.size(); i++){            
            String[] tempList= lines.get(i).split(",");//split each line by the commas
            if(i ==0){
                importedMap = new char[lines.size()][tempList.length];//if the first time running initiallize the 2D array
            }
            for(int j = 0; j<tempList.length; j++){
                importedMap[i][j] = tempList[j].charAt(0);//load the first character, whatever it is
            }
        }
        return importedMap;
    }

    /**
     * @return Paused or not
     */ 
    public boolean checkPause(){
        return paused;
    }   

    /**
     * Pauses/Unpauses the game
     * 
     * @param input Is the game paused or not
     */
    public void setPause(boolean input){
        paused = input;
    }
    
    /**
     * @return The player's X location
     */
    public int getPlayerX(){
        return player.getX();
    }
    
    /**
     * @return The player's Y location
     */
    public int getPlayerY(){
        return player.getY();
    }
    
    /**
     * Return the player itself
     */
    public Player getPlayer(){
        return player;
    }
}
