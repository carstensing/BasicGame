package dev.carsten.basicgame;

import dev.carsten.basicgame.display.Display;
import dev.carsten.basicgame.gfx.Assets;
import dev.carsten.basicgame.gfx.GameCamera;
import dev.carsten.basicgame.input.KeyManager;
import dev.carsten.basicgame.input.MouseManager;
import dev.carsten.basicgame.states.GameState;
import dev.carsten.basicgame.states.MenuState;
import dev.carsten.basicgame.states.State;

import java.awt.image.BufferStrategy;
import java.awt.Graphics;

public class Game implements Runnable {

    private Display display;
    private int width, height;
    private String title;
    private boolean running = false;

    private Thread thread;

    private BufferStrategy bs;
    private Graphics g;

    //States
    public State gameState;
    public State menuState;

    //Input
    private KeyManager keyManager;
    private MouseManager mouseManager;

    //Camera
    private GameCamera gameCamera;

    //Handler
    private Handler handler;

    public Game(String title, int width, int height) {
        this.width = width;
        this.height = height;
        this.title = title;
        keyManager = new KeyManager();
        mouseManager = new MouseManager();
    }

    private void init() {

        display = new Display(title, width, height);
        display.getFrame().addKeyListener(keyManager);
        display.getFrame().addMouseListener(mouseManager);
        display.getFrame().addMouseMotionListener(mouseManager);
        display.getCanvas().addMouseListener(mouseManager);
        display.getCanvas().addMouseMotionListener(mouseManager);
        Assets.init();

        handler = new Handler(this);
        gameCamera = new GameCamera(handler, 0, 0);

        gameState = new GameState(handler);
        menuState = new MenuState(handler);
        State.setState(menuState);
    }

    private void tick() {
        keyManager.tick();

        if(State.getState() != null) {
            State.getState().tick();
        }
    }

    private void render() {
        bs = display.getCanvas().getBufferStrategy();
        if(bs == null) {
            display.getCanvas().createBufferStrategy(3);
            return;
        }
        g = bs.getDrawGraphics();

        //Clear Screen
        g.clearRect(0, 0, width, height);

        //Draw

        if(State.getState() != null) {
            State.getState().render(g);
        }
        //End draw

        bs.show();
        g.dispose();
    }

    public void run() {
        init();

        int fps = 60;
        double timePerFrame = 1000000000 / fps;
        long startOfFrame;
        long endOfFrame = System.nanoTime();

        long frameTimer = 0;
        int frameCounter = 0;

        long startOfSecond = 0;

        while(running) {
            startOfFrame = System.nanoTime();

            if (frameCounter == 0) {
                startOfSecond = System.nanoTime();
                this.tick();
                this.render();
            }

            if (frameTimer == 0) {
                this.tick();
                this.render();
            }

            frameTimer += (startOfFrame - endOfFrame);
            endOfFrame = startOfFrame;

            if (frameTimer >= timePerFrame) {
                frameTimer = 0;
                frameCounter++;
            }

            if (System.nanoTime() - startOfSecond >= 1000000000) {
                System.out.println(frameCounter);
                frameCounter = 0;
            }
        }
        this.stop();
    }

    public KeyManager getKeyManager() {
        return this.keyManager;
    }

    public MouseManager getMouseManager() {
        return this.mouseManager;
    }

    public GameCamera getGameCamera() {
        return this.gameCamera;
    }

    public int getWidth() {
        return this.width;
    }

    public int getHeight() {
        return this.height;
    }

    public synchronized void start() {
        if(running)
            return;
        running = true;
        thread = new Thread(this);
        thread.start();
    }

    public synchronized void stop() {
        if(!running)
            return;
        running = false;
        try {
            thread.join();
        }
        catch(InterruptedException e) {
            e.printStackTrace();
        }
    }
}
