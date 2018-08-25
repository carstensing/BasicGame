package dev.carsten.basicgame.entities.Statics;

import dev.carsten.basicgame.Handler;
import dev.carsten.basicgame.entities.Entity;

public abstract class StaticEntity extends Entity {

    public StaticEntity(Handler handler, float x, float y, int width, int height) {
        super(handler, x, y, width, height);
    }

}
