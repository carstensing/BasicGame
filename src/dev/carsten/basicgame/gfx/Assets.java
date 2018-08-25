package dev.carsten.basicgame.gfx;

import java.awt.image.BufferedImage;

public class Assets {

    private static BufferedImage[] character;
    private static BufferedImage[] tiles;
    private static BufferedImage[][] walking = new BufferedImage[8][2];
    private static BufferedImage[] startButton;

    public static void init() {

        character = new BufferedImage[60];
        crop(character, new SpriteSheet(ImageLoader.loadImage("/textures/knight_a.png")), 24, 24, 60);

        BufferedImage[] north = {character[2], character[12]};
        walking[0] = north;

        BufferedImage[] northEast = {character[1], character[11]};
        walking[1] = northEast;

        BufferedImage[] east = {character[0], character[10]};
        walking[2] = east;

        BufferedImage[] southEast = {character[7], character[17]};
        walking[3] = southEast;

        BufferedImage[] south = {character[6], character[16]};
        walking[4] = south;

        BufferedImage[] southWest = {character[5], character[15]};
        walking[5] = southWest;

        BufferedImage[] west = {character[4], character[14]};
        walking[6] = west;

        BufferedImage[] northWest = {character[3], character[13]};
        walking[7] = northWest;

        tiles = new BufferedImage[256];
        crop(tiles, new SpriteSheet(ImageLoader.loadImage("/textures/minecraft.png")), 16, 16, 256);

        startButton = new BufferedImage[2];
        startButton[0] = tiles[44];
        startButton[1] = tiles[61];
    }

    private static void crop(BufferedImage[] images, SpriteSheet sheet, final int WIDTH, final int HEIGHT, final int NUM_SPRITES) {
        int x = 0;
        int y = 0;

        for(int i = 0; i < NUM_SPRITES; i++) {

            try {
                images[i] = sheet.crop(x, y, WIDTH, HEIGHT);
                x += WIDTH;
            }
            catch(Exception e) {
                y += HEIGHT;
                x = 0;
                i--;
            }
        }
    }

    public static BufferedImage[] getCharacter() {
        return character;
    }

    public static BufferedImage[] getTiles() {
        return tiles;
    }

    public static BufferedImage[][] getWalking() {
        return walking;
    }

    public static BufferedImage[] getStartButton() {
        return startButton;
    }
}
