package dev.carsten.basicgame;

public class Launcher {
    public static void main(String[] args) {
        Game game = new Game("Game", 900, 900);
        game.start();
    }
}
