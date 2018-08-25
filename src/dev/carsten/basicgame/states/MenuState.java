package dev.carsten.basicgame.states;

import dev.carsten.basicgame.Handler;

import java.awt.*;

public class MenuState extends State {

    public MenuState(Handler handler) {
        super(handler);
    }

    public void tick() {
        System.out.println(handler.getMouseManager().getMouseX() + " : " + handler.getMouseManager().getMouseY());
    }

    public void render(Graphics g) {
        g.setColor(Color.BLACK);
        g.fillRect(handler.getMouseManager().getMouseX(), handler.getMouseManager().getMouseY(), 10, 10);
    }
}
