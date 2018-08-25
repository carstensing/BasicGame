package dev.carsten.basicgame.tiles;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

public class Tile {

    //STATIC STUFF

    public static Tile[] tiles = new Tile[256];
    public static Tile grassTile = new GrassTile(0);
    public static Tile rockTile = new RockTile(1);

    //CLASS

    public static final int TILE_WIDTH = 64;
    public static final int TILE_HEIGHT = 64;

    protected BufferedImage texture;
    protected final int id;
    protected final boolean isSolid;

    public Tile(BufferedImage texture, int id, boolean isSolid) {
        this.texture = texture;
        this.id = id;
        this.isSolid = isSolid;

        tiles[id] = this;
    }

    public void tick() {

    }

    public void render(Graphics g, int x, int y) {
        g.drawImage(this.texture, x, y, TILE_WIDTH, TILE_HEIGHT, null);
    }

    public boolean isSolid() {
        return this.isSolid;
    }

    public int getId() {
        return this.id;
    }
}
