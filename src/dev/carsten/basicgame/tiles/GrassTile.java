package dev.carsten.basicgame.tiles;

import dev.carsten.basicgame.gfx.Assets;

public class GrassTile extends Tile{

    public GrassTile(int id) {
        super(Assets.getTiles()[0], id, false);
    }
}
