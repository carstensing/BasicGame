package dev.carsten.basicgame.tiles;

import dev.carsten.basicgame.gfx.Assets;

public class RockTile extends Tile {

    public RockTile(int id) {
        super(Assets.getTiles()[1], id, true);
    }
}
