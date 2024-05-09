package main;


public class Loop implements Runnable{

    private static final int fps = 60;

    private Panel game;
    private boolean running;
    public Loop(Panel _game) {
        game = _game;
    }

    @Override
    public void run() {
        running = true;

        double drawInterval = (double) 1000000000 / fps;
        double deltaTime = 0;
        long lastTime = System.nanoTime();
        long currentTime;
        long timer = 0;
        long drawCount = 0;

        while (running) {
            currentTime = System.nanoTime();
            deltaTime += (currentTime - lastTime) / drawInterval;
            timer += currentTime - lastTime;
            lastTime = currentTime;
            if (timer >= 1000000000) {
                System.out.println(STR."fps check: \{drawCount}");
                drawCount = 0;
                timer = 0;
            }
            if (deltaTime < 1) continue;
            deltaTime--;
            drawCount++;

            update();
            draw();
        }
    }

    private void update() {
        game.update();
    }

    private void draw() {
        game.repaint();
    }

}