package main;
public class Loop implements Runnable {

    private static final int fps = 60;
    private final Panel panel;
    public static long now;
    public Loop(Panel _panel) {
        panel = _panel;
    }
    @Override
    public void run() {

        double drawInterval = (double) 1_000_000_000 / fps;
        double deltaTime = 0;
        long lastTime = System.nanoTime();
        long currentTime;

        while (true) {
            now = System.nanoTime();
            currentTime = now;
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