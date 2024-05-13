package main;


public class Loop implements Runnable {

    private static final int fps = 60;

    private Panel panel;
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
        panel.update();
    }

    private void draw() {
        panel.repaint();
    }

}