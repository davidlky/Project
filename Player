import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.lang.Math;
import java.util.ArrayList;
import java.util.List;
/**
 * Write a description of class Player here.
 * 
 * @author Keith Wong
 * @version (a version number or a date)
 */
public class Player extends Collision
{
    //attributes
    private int health = 100;

    //moving
    private int speedY = 7;
    private int speedX = 6;
    private int gravity = 1;
    private int deltaY = 0;
    private int deltaX = 0;
    private int jumpCounter = 0;
    private boolean goingLeft = false;
    private boolean jumped = false;
    private int maxY = 20;

    //weapons
    private boolean bulletShot = false;
    private boolean shootDiagonal = false;
    private int bulletCD = 10;
    private int weaponChoice = 1; //1 is regular bullets, 2 is missiles, 3 is super missiles
    private int missileAmmo = 0;
    private int superMissileAmmo = 0;

    //position
    private boolean crouched = false;

    //image things
    private GreenfootImage g = new GreenfootImage("RIGHT/Right0.png");
    private String image = "RIGHT/Right0.png";

    //Collision things
    private Tile tile;
    private boolean stopLeft = false;
    private boolean stopRight = false;
    private boolean stopUp = false;
    private boolean grounded = false;
    private int imgHeight = g.getHeight()/2;
    private int imgWidth = g.getWidth()/2;
    private Tile horizontalTile;
    private boolean collided = false;
    private boolean corner = false;

    //graphics
    private int indexL = 0;
    private int indexR = 0;
    private SpritePlayer leftRun, rightRun;
    
    private List neighbours;
    public Player(){
        setImage(g);
        leftRun = new SpritePlayer("LEFT/Left", 5);
        rightRun = new SpritePlayer("RIGHT/Right", 5);
    }

    /**
     * Act - do whatever the Player wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act(){
        if(!((Map)(getWorld())).checkPause()){
            movement();
            weaponChoice();
            shooting();
        }
    }

    /**
     * Player movement.
     * 
     */
    private void movement(){
        deltaX = 0;
        image = "RIGHT/Right0.png";
        crouched = false;
        collided = false;

        horizontalTile = (Tile) getOneObjectAtOffset(imgWidth + 1, 0, Tile.class);
        if (horizontalTile == null){
            stopRight = false;
        }

        horizontalTile = (Tile) getOneObjectAtOffset(-imgWidth - 1, 0, Tile.class);
        if (horizontalTile == null){
            stopLeft = false;
        }

        //check for pause button
        if(Greenfoot.isKeyDown("P")){
            ((Map)(getWorld())).setPause(true);
            getWorld().addObject(new PauseScreen(),(((SWorld)(getWorld())).getScrolledX())+360, (((SWorld)(getWorld())).getScrolledY())+240);
        }

        //applys graivty if the player is above ground
        if(grounded == false){
            gravity();
        } else {
            stopUp = false;
            jumped = false;
        }

        //jumps
        if (Greenfoot.isKeyDown("x") && stopUp == false){
            jump();
        }
        //crouches
        if (Greenfoot.isKeyDown("down")){
            crouch();
        }

        //allows the player to move left and right if the player is either not crouched, or jumping
        if (crouched == false || jumped == true || grounded == true){
            //moves left
            if (Greenfoot.isKeyDown("left") && stopLeft == false){
                moveLeft();
                stopRight = false;
            }
            //moves right
            if (Greenfoot.isKeyDown("right") && stopRight == false){
                moveRight();
                stopLeft = false;
            }
        }

        //sets the new image of the player 
        g = new GreenfootImage(image);
        this.setImage(image);
        int imgWidth = g.getWidth()/2;
        int imgHeight = g.getHeight()/2;

        //collision detection
        tile = (Tile) getOneTouchedObject(Tile.class);
        if (tile != null){
            collision();
            collided = true;
        }

        if (onPlatform() == true){
            grounded = true;
        } else {
            grounded = false;
        }

        //speed limit for falling
        if (deltaY > maxY){
            deltaY = maxY;
        }
        if (deltaY < -maxY){
            deltaY = -maxY;
        }

        setLocation(getX() + deltaX, getY() + deltaY);
    }

    private void collision(){
        int nextY = 0;
        int nextX = 0;

        int thisX = getX();
        int thisY = getY();

        int tileX = tile.getX();
        int tileY = tile.getY();

        if (thisY - imgHeight < tileY - 24 && thisY + imgHeight >= tileY - 24 && thisY + imgHeight < tileY){ // object is above the tile
            nextY = ((tileY - 24) - (thisY + imgHeight));
            grounded = true;
            jumpCounter = 0;
            deltaY = 0;
        } else if (thisY + imgHeight > tileY + 24 && thisY - imgHeight <= tileY + 24 && thisY - imgHeight > tileY ) { //object is under the tile
            nextY = ((tileY + 24) - (thisY  - imgHeight));
            stopUp = true;
            deltaY = 0;
        }
        if (thisX + imgWidth > tileX - 24 && thisX - imgWidth <= tileX - 24 && thisX + imgWidth < tileX){ //object is on the left of the tile
            nextX = ((tileX - 24) - (thisX + imgWidth));
            stopRight = true;
            deltaX = 0;
        } else if (thisX - imgWidth <= tileX + 24 && thisX + imgWidth > tileX + 24 && thisX - imgWidth > tileX){ //object is on the right of the tile
            nextX = ((tileX + 24) - (thisX - imgWidth));
            stopLeft = true;
            deltaX = 0;
        }
        setLocation(thisX + nextX, thisY + nextY);
    }

    /**
     * Makes the character move left
     */
    private void moveLeft(){
        deltaX -= speedX;
        goingLeft = true;
        
        //image change
        indexR = 0;
        image = leftRun.getImageName(indexL);
        indexL++;
        if(indexL > 4){
            indexL = 0;
        }
    }

    /**
     * Makes the charcter move right
     * 
     */
    private void moveRight(){
        deltaX += speedX;
        goingLeft = false;
        
        //image change
        indexL = 0;
        image = rightRun.getImageName(indexR);
        indexR++;
        if (indexR > 4){
            indexR = 0;
        }
    }

    private void jump(){
        if (jumped == false){
            setLocation(getX(), getY() - speedY);
            jumped = true;
        }
        if (jumped == true){
            deltaY -= (speedY - jumpCounter);
            if (jumpCounter < 7){
                jumpCounter++;
            }
        }
        grounded = false;
    }

    private void crouch(){
        image = "tux.png";
        crouched = true;
    }

    private void gravity(){
        deltaY += gravity;
    }

    /**
     * Changes the current weapon
     * 
     */
    private void weaponChoice(){
        if (Greenfoot.isKeyDown("1"))
            weaponChoice = 1;
        if (Greenfoot.isKeyDown("2"))
            weaponChoice = 2;
        if (Greenfoot.isKeyDown("3"))
            weaponChoice = 3;
    }

    /**
     * Shoots the weapon
     */
    private void shooting(){
        if (Greenfoot.isKeyDown("c")){
            shootDiagonal = true;
        } else{
            shootDiagonal = false;
        }
        if(Greenfoot.isKeyDown("z") && bulletShot == false){
            if (weaponChoice == 1)
                shootBullet();
            if (weaponChoice == 2)
                shootMissile();
            if (weaponChoice == 3)
                shootSuperMissile();
            bulletShot = true;
        }

        if(bulletShot == true){
            bulletCD--;
        }
        if(bulletCD == 0){
            bulletShot = false;
            bulletCD = 10;
        }
    }

    /**
     * Shoots a bullet
     */
    private void shootBullet(){
        getWorld().addObject(new Bullet(goingLeft, shootDiagonal), (((SWorld) getWorld())).getScrolledX() + getX(), (((SWorld) getWorld())).getScrolledY() + getY()) ;//Because the bullet spawn moves around for some stupid reason
    }

    /**
     * Shoots a missile
     * 
     */
    private void shootMissile(){
        getWorld().addObject(new Missile(goingLeft, shootDiagonal),(((SWorld) getWorld())).getScrolledX() + getX(), (((SWorld) getWorld())).getScrolledY() + getY());//Because the bullet spawn moves around for some stupid reason
    }

    /**
     * Shoots a SuperMissile
     * 
     */
    private void shootSuperMissile(){
        getWorld().addObject(new SuperMissile(goingLeft, shootDiagonal), (((SWorld) getWorld())).getScrolledX() + getX(), (((SWorld) getWorld())).getScrolledY() + getY());//Because the bullet spawn moves around for some stupid reason
    }

    public int getHealth(){
        return health;
    }

    public int getMissileAmmo(){
        return missileAmmo;
    }
    
    public int getSuperMissileAmmo(){
        return superMissileAmmo;
    }
    
}
