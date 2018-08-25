package dev.carsten.basicgame.ui;

import dev.carsten.basicgame.Handler;

import java.awt.*;
import java.awt.event.MouseEvent;

public abstract class UIObject {

    protected Handler handler;
    protected float x, y;
    protected int width, height;
    protected Rectangle bounds;
    protected boolean hovering = false;

    public UIObject(Handler handler, float x, float y, int width, int height) {
        this.handler = handler;
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        bounds = new Rectangle((int) x, (int) y);
    }

    public abstract void tick();

    public abstract void render(Graphics g);

    public abstract void onClick();

    public void onMouseMove(MouseEvent e) {
        if(bounds.contains(e.getX(), e.getY())) {
            this.hovering = true;
        }
        else {
            this.hovering = false;
        }
    }

    public void onMouseRelease(MouseEvent e) {
        if(hovering && !handler.getMouseManager().isLeftPressed()) {
            onClick();
        }
    }

    public float getX() {
        return x;
    }

    public void setX(float x) {
        this.x = x;
    }

    public float getY() {
        return y;
    }

    public void setY(float y) {
        this.y = y;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public boolean isHovering() {
        return hovering;
    }

    public void setHovering(boolean hovering) {
        this.hovering = hovering;
    }
}
