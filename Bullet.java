import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Bullet here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Bullet extends Weapon
{
    private boolean goingLeft;
    private boolean diagonal;
    private int speed = 10;
    private int damage = 1;
    public Bullet(boolean goingLeft, boolean diagonal){
        this.goingLeft = goingLeft;
        this.diagonal = diagonal;
    }

        /**
     * Act - do whatever the Bullet wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act() 
    {
        if(diagonal == false){
            if(goingLeft == true)
                setLocation(getX() - speed, getY());
            else if (goingLeft == false)
                setLocation(getX() + speed, getY());
        } else {
            if (goingLeft == true)
                setLocation(getX() - speed, getY() - speed);
            else if (goingLeft == false)
                setLocation(getX() + speed, getY() - speed);
        }

        if (touch(Tile.class) == true || touch(Enemy.class) == true){
            getWorld().removeObject(this);
        }
    }    
}
