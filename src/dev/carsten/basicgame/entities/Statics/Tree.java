package dev.carsten.basicgame.entities.Statics;

import dev.carsten.basicgame.Handler;
import dev.carsten.basicgame.gfx.Assets;
import dev.carsten.basicgame.tiles.Tile;

import java.awt.*;

public class Tree extends StaticEntity {

    public Tree(Handler handler, float x, float y) {
        super(handler, x, y, Tile.TILE_WIDTH, Tile.TILE_HEIGHT);

        bounds.width = width;
        bounds.height = height;
        bounds.x = 0;
        bounds.y = 0;
    }

    @Override
    public void tick() {

    }

    @Override
    public void render(Graphics g) {
        g.drawImage(Assets.getTiles()[15], (int)(x - handler.getGameCamera().getxOffset()), (int)(y - handler.getGameCamera().getyOffset()), width, height, null);
    }
}
