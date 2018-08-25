package dev.carsten.basicgame.entities.creatures;

import dev.carsten.basicgame.entities.Entity;
import dev.carsten.basicgame.Handler;
import dev.carsten.basicgame.tiles.Tile;

public abstract class Creature extends Entity{

    public static final int DEFAULT_HEALTH = 10;
    public static final float DEFAULT_SPEED = 4.0f;
    public static final int DEFAULT_CREATURE_WIDTH = 64;
    public static final int DEFAULT_CREATURE_HEIGHT = 64;

    protected int health;
    protected float speed;
    protected float xMove, yMove;

    public Creature(Handler handler, float x, float y, int width, int height) {
        super(handler, x, y, width, height);
        this.health = DEFAULT_HEALTH;
        this.speed = DEFAULT_SPEED;
        this.xMove = 0;
        this.yMove = 0;
    }

    public void move(){
        if(!checkEntityCollisions(xMove, 0f))
            moveX();

        if(!checkEntityCollisions(0f, yMove))
            moveY();
    }

    public void moveX(){
        int tx;

        //System.out.println("xMove " + xMove);

        if(xMove > 0){//Moving right
            tx = (int) Math.floor((x + xMove + bounds.x + bounds.width) / Tile.TILE_WIDTH);

            if(!collisionWithTile(tx, (int)Math.floor((y + bounds.y) / Tile.TILE_HEIGHT)) &&
                    !collisionWithTile(tx, (int)Math.floor((y + bounds.y + bounds.height) / Tile.TILE_HEIGHT))) {
                x += xMove;
            }
            else {
                x = tx * Tile.TILE_WIDTH - bounds.x - bounds.width - 1;
            }
        }
        else if(xMove < 0){//Moving left
            tx = (int) Math.floor((x + xMove + bounds.x) / Tile.TILE_WIDTH);

            if(!collisionWithTile(tx, (int)Math.floor((y + bounds.y) / Tile.TILE_HEIGHT)) &&
                    !collisionWithTile(tx, (int)Math.floor((y + bounds.y + bounds.height) / Tile.TILE_HEIGHT))) {
                x += xMove;
            }
            else {
                x = tx * Tile.TILE_WIDTH + Tile.TILE_WIDTH - bounds.x;
            }
        }
    }

    public void moveY(){
        int ty;

        if(yMove < 0){//Up
            ty = (int) Math.floor((y + yMove + bounds.y) / Tile.TILE_HEIGHT);

            if(!collisionWithTile((int)Math.floor((x + bounds.x) / Tile.TILE_WIDTH), ty) &&
                    !collisionWithTile((int)Math.floor((x + bounds.x + bounds.width) / Tile.TILE_WIDTH), ty)) {
                y += yMove;
            }
            else {
                y = ty * Tile.TILE_HEIGHT + Tile.TILE_HEIGHT - bounds.y;
            }
        }
        else if(yMove > 0){//Down
            ty = (int) Math.floor((y + yMove + bounds.y + bounds.height) / Tile.TILE_HEIGHT);

            if(!collisionWithTile((int)Math.floor((x + bounds.x) / Tile.TILE_WIDTH), ty) &&
                    !collisionWithTile((int)Math.floor((x + bounds.x + bounds.width) / Tile.TILE_WIDTH), ty)) {
                y += yMove;
            }
            else {
                y = ty * Tile.TILE_HEIGHT - bounds.y - bounds.height - 1;
            }
        }
    }

    protected boolean collisionWithTile(int x, int y){
        return handler.getWorld().getTile(x, y).isSolid();
    }

    //GETTERS & SETTERS

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public float getSpeed() {
        return speed;
    }

    public void setSpeed(float speed) {
        this.speed = speed;
    }

    public float getxMove() {
        return xMove;
    }

    public void setxMove(float xMove) {
        this.xMove = xMove;
    }

    public float getyMove() {
        return yMove;
    }

    public void setyMove(float yMove) {
        this.yMove = yMove;
    }
}
