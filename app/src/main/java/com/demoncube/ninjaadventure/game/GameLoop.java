package com.demoncube.ninjaadventure.game;


import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class GameLoop {

    // Between class comunication links
    private Game game;

    // loop run variables
    private ScheduledExecutorService updateExecutor = Executors.newSingleThreadScheduledExecutor();
    private final long frameTimeNs = 1_000_000_000 / GameSettings.baseSettings.FRAME_RATE;
    private boolean running = false;
    public boolean available = true;
    private long delay, lastDelta, lastFpsCheck, nanoSec = 1_000_000_000;
    private int fps, fpsCounter;


    // Constructor
    public GameLoop (Game game) {
        this.game = game;
    }

    // Loop

    private void scheduleNextUpdate() {
        updateExecutor.schedule(() -> {
            if (!running) return;

            long startTime = System.nanoTime();
            double delta = (startTime - lastDelta) / (double) nanoSec;
            lastDelta = startTime;

            game.update(delta);
            if (available) game.render();

            //---------- FPS Calculation ----------//
            fpsCounter++;
            long now = System.currentTimeMillis();
            if (now - lastFpsCheck >= 1000) {                                                       // Update FPS every second
                fps = fpsCounter;
                fpsCounter = 0;
                lastFpsCheck = now;
                if (GameSettings.debug.CALL_FPS) System.out.println("FPS: " + fps);
            }

            //---------- Schedule next update ----------//
            long elapsedTime = System.nanoTime() - startTime;
            delay = Math.max(frameTimeNs - elapsedTime, 0);
            scheduleNextUpdate();

        }, delay, TimeUnit.NANOSECONDS);
    }

    // Control functions
    public void startGameLoop() {
        System.out.println("Ready to start: " + !running);
        if (running) return;
        lastFpsCheck = System.currentTimeMillis();
        fps = 0;
        fpsCounter = 0;
        lastDelta = System.nanoTime();
        running = true;

        scheduleNextUpdate();
    }

    public void stopGameLoop() {
        running = false;
        updateExecutor.shutdown();
    }

    public int getFps() {
        return fps;
    }
}
