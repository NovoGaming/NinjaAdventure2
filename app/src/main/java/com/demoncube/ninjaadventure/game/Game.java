package com.demoncube.ninjaadventure.game;

import static com.demoncube.ninjaadventure.GameActivity.SCREEN_WIDTH;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.MotionEvent;
import android.view.SurfaceHolder;

import com.demoncube.ninjaadventure.game.gamestates.states.DeathScreen;
import com.demoncube.ninjaadventure.game.gamestates.states.Menu;
import com.demoncube.ninjaadventure.game.gamestates.states.Playing;


public class Game {

    private SurfaceHolder holder;
    GameLoop gameLoop;

    //---------- Game States constants ----------//
    private Menu menu;
    private Playing playing;
    private DeathScreen deathScreen;
    GameState currentGameState = GameState.PLAYING;
    //---------- DEBUG ----------//
    private final Paint DebugPaint;

    public Game (SurfaceHolder holder) {
        this.holder = holder;
        gameLoop = new GameLoop(this);
        initGameStates();

        DebugPaint = new Paint();
        DebugPaint.setStyle(Paint.Style.FILL_AND_STROKE);
        DebugPaint.setColor(GameSettings.debug.FPS_COLOR);
        DebugPaint.setTextSize(40);
    }

    public  void update(double delta) {
        switch (currentGameState) {
            case MENU:
                menu.update(delta);
                break;
            case PLAYING:
                playing.update(delta);
                break;
            case DEATH_SCREEN:
                deathScreen.update(delta);
                break;
        }
    }

    public void render(){
        Canvas canvas = holder.lockCanvas();
        if (canvas == null) return;
        canvas.drawColor(Color.BLACK);

        switch (currentGameState) {
            case MENU:
                menu.render(canvas);
                break;
            case PLAYING:
                playing.render(canvas);
                break;
            case DEATH_SCREEN:
                deathScreen.render(canvas);
                break;
        }

        if (GameSettings.debug.SHOW_FPS) drawFps(canvas);
        holder.unlockCanvasAndPost(canvas);
    }

    private void drawFps(Canvas c){
        c.drawText(gameLoop.getFps() + " FPS", SCREEN_WIDTH- 140, 35, DebugPaint);
    }

    public boolean touchEvents(MotionEvent event) {
        switch (currentGameState) {
            case MENU:
                menu.touchEvents(event);
                break;
            case PLAYING:
                playing.touchEvents(event);
                break;
            case DEATH_SCREEN:
                deathScreen.touchEvents(event);
                break;
        }
        return true;
    }

    public void stop(){
        gameLoop.stopGameLoop();
    }
    public void start(){
        gameLoop.startGameLoop();
    }
    public void isAvailable(boolean available) {
        gameLoop.available = available;
    }


    //---------- Game States functions ----------//

    private void initGameStates(){
        menu = new Menu(this);
        playing = new Playing(this);
        deathScreen = new DeathScreen(this);
    }

    public enum GameState{
        MENU,
        PLAYING,
        DEATH_SCREEN;
    }

}
