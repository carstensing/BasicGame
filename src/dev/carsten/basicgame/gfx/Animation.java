package dev.carsten.basicgame.gfx;

import java.awt.image.BufferedImage;

public class Animation {

    private double speed;
    private long lastTime, timer;

    private int index;
    private BufferedImage[] frames;

    public Animation(double speed, BufferedImage[] frames) {
        this.speed = speed;
        this.frames = frames;

        this.index = 0;
        timer = 0;
        lastTime = System.nanoTime();
    }

    public void tick() {
        timer += (System.nanoTime() - lastTime);
        lastTime = System.nanoTime();

        if((timer/1000000000.0) >= speed) {
            index++;
            timer = 0;
            if(index >= frames.length) {
                index = 0;
            }
        }
    }

    public BufferedImage getCurrentFrame() {
        return this.frames[index];
    }
}
