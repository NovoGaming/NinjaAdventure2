package com.demoncube.ninjaadventure;

import android.content.Context;
import android.os.Bundle;
import android.util.DisplayMetrics;

import com.demoncube.ninjaadventure.game.GameView;

public class GameActivity extends BaseActivity {

    private static Context gameContext;
    private GameView gameView;
    public static int SCREEN_WIDTH, SCREEN_HEIGHT;
    public static int SCREEN_CENTER_WIDTH, SCREEN_CENTER_HEIGHT;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        gameContext = this;

        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getRealMetrics(dm);
        SCREEN_WIDTH = dm.widthPixels;
        SCREEN_HEIGHT = dm.heightPixels;

        SCREEN_CENTER_WIDTH = SCREEN_WIDTH/2;
        SCREEN_CENTER_HEIGHT = SCREEN_HEIGHT/2;

        gameView = new GameView(this);
        setContentView(gameView);
    }

    @Override
    protected void onResume() {
        setUiFlags();
        super.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onDestroy() {
        gameView.destroy();
        super.onDestroy();
    }

    public static Context getGameContext() {
        return gameContext;
    }
}