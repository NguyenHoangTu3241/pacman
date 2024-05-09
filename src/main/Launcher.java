package main;
public class Launcher {
    public static void main(String[] args) {
        Thread PacmanGame = new Thread(new Loop(new Panel()));
        PacmanGame.start();
    }
}