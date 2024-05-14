package main;


public class Loop implements Runnable {

    private static final int fps = 60;

    private final Panel panel;
    private boolean running;
    public Loop(Panel _panel) {
        panel = _panel;
    }

    @Override
    public void run() {
        running = true;

        double drawInterval = (double) 1000000000 / fps;
        double deltaTime = 0;
        long lastTime = System.nanoTime();
        long currentTime;

        while (running) {
            currentTime = System.nanoTime();
            deltaTime += (currentTime - lastTime) / drawInterval;
            lastTime = currentTime;

            if (deltaTime < 1) continue;
            deltaTime--;

            update();
            draw();

        }
    }

    private void update() {
        panel.update();
    }

    private void draw() {
        panel.repaint();
    }

}