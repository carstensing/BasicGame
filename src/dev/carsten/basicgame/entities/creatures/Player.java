package dev.carsten.basicgame.entities.creatures;

import dev.carsten.basicgame.gfx.Animation;
import dev.carsten.basicgame.gfx.Assets;
import dev.carsten.basicgame.Handler;
import dev.carsten.basicgame.gfx.StepCounter;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Player extends Creature{

    private static final int PLAYER_WIDTH = 100;
    private static final int PLAYER_HEIGHT = 100;

    //Animations
    private StepCounter walk;

    private BufferedImage lastFrame;

    public Player(Handler handler, float x, float y) {
        super(handler, x, y, PLAYER_WIDTH, PLAYER_HEIGHT);

        bounds.width = ((int)(width * .25) - 1) + 2;
        bounds.height = (int)(height * .3) - 1;
        bounds.x = (width - bounds.width)/2;
        bounds.y = (height - bounds.height-1) - 28;

        //Animations
        walk = new StepCounter((int)x, (int)y, (int)(speed*15), Assets.getWalking()[0].length);

        lastFrame = Assets.getWalking()[4][0];
    }

    @Override
    public void tick() {
        //Animations
        walk.tick((int)x, (int)y);

        //Movement
        getInput();
        move();
        handler.getGameCamera().centerOnEntity(this);
    }

    private void getInput(){
        xMove = 0;
        yMove = 0;

        //Up
        if(handler.getKeyManager().up && handler.getKeyManager().down && handler.getKeyManager().upHold < handler.getKeyManager().downHold) {
            yMove = -speed;
        }
        //Down
        else if(handler.getKeyManager().down && handler.getKeyManager().up && handler.getKeyManager().downHold < handler.getKeyManager().upHold) {
            yMove = speed;
        }
        //Up
        else if(handler.getKeyManager().up) {
            yMove = -speed;
        }
        //Down
        else if(handler.getKeyManager().down) {
            yMove = speed;
        }

        //Left
        if(handler.getKeyManager().left && handler.getKeyManager().right && handler.getKeyManager().leftHold < handler.getKeyManager().rightHold) {
            xMove = -speed;
        }
        //Right
        else if(handler.getKeyManager().right && handler.getKeyManager().left && handler.getKeyManager().rightHold < handler.getKeyManager().leftHold) {
            xMove = speed;
        }
        //Left
        else if(handler.getKeyManager().left) {
            xMove = -speed;
        }
        //Right
        else if(handler.getKeyManager().right) {
            xMove = speed;
        }
    }

    @Override
    public void render(Graphics g) {
        g.drawImage(getCurrentAnimationFrame(), (int)(x - handler.getGameCamera().getxOffset()), (int)(y - handler.getGameCamera().getyOffset()), width, height, null);

        /*g.setColor(Color.RED);
        g.fillRect((int) (x + bounds.x - handler.getGameCamera().getxOffset()),
                (int) (y + bounds.y - handler.getGameCamera().getyOffset()),
                bounds.width, bounds.height);*/
    }

    private BufferedImage getCurrentAnimationFrame() {

        if(yMove < 0 && xMove > 0) { //NorthEast
            lastFrame = Assets.getWalking()[1][walk.getIndex()];
            return Assets.getWalking()[1][walk.getIndex()];
        }
        else if(yMove > 0 && xMove > 0) { //SouthEast
            lastFrame = Assets.getWalking()[3][walk.getIndex()];
            return Assets.getWalking()[3][walk.getIndex()];
        }
        else if(yMove > 0 && xMove < 0) { //SouthWest
            lastFrame = Assets.getWalking()[5][walk.getIndex()];
            return Assets.getWalking()[5][walk.getIndex()];
        }
        else if(yMove < 0 && xMove < 0) { //NorthWest
            lastFrame = Assets.getWalking()[7][walk.getIndex()];
            return Assets.getWalking()[7][walk.getIndex()];
        }
        else if(yMove < 0) { //North
            lastFrame = Assets.getWalking()[0][walk.getIndex()];
            return Assets.getWalking()[0][walk.getIndex()];
        }
        else if(yMove > 0) { //South
            lastFrame = Assets.getWalking()[4][walk.getIndex()];
            return Assets.getWalking()[4][walk.getIndex()];
        }
        else if(xMove > 0) { //East
            lastFrame = Assets.getWalking()[2][walk.getIndex()];
            return Assets.getWalking()[2][walk.getIndex()];
        }
        else if(xMove < 0) { //West
            lastFrame = Assets.getWalking()[6][walk.getIndex()];
            return Assets.getWalking()[6][walk.getIndex()];
        }
        else {
            return lastFrame;
        }
    }
}
