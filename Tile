import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Choose 1 for horizontal, 2 for verticle, 3 for emd block
 * 
 * @author Jessie  
 * @version (a version number or a date)
 */
public class Tile extends Actor
{
    private boolean breakable = false;
    public Tile(int x){
        if(x==1){//normal horizontal
            setImage(new GreenfootImage("Tile1.png"));
        }else if(x==2){//normal verticle
            setImage(new GreenfootImage("Tile5.png"));
        }else if(x==3){//normal end block
            setImage(new GreenfootImage("Tile2.png"));
        }else if(x==4){
            setImage(new GreenfootImage("Tile3.png"));
            breakable = true;
        }else
        if(x==5){
            setImage(new GreenfootImage("Tile5.png"));
        }else
        if(x==6){
            setImage(new GreenfootImage("Tile5.png"));
        }else
        if(x==7){
            setImage(new GreenfootImage("Tile6.png"));
        }
    }

    public void act(){
        if(breakable){
            breakable();	
        }
    }

    private void breakable(){
        if(getOneIntersectingObject(SuperMissile.class) !=null){
            getWorld().removeObject(this);
        }
    }
}
