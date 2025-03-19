package com.demoncube.ninjaadventure.game.gamestates.states;

import android.graphics.Canvas;
import android.view.MotionEvent;

import com.demoncube.ninjaadventure.game.Game;
import com.demoncube.ninjaadventure.game.gamestates.BaseState;
import com.demoncube.ninjaadventure.game.gamestates.GameStateInterface;

public class DeathScreen extends BaseState implements GameStateInterface {

    public DeathScreen(Game game) {
        super(game);
    }

    @Override
    public void update(double delta) {

    }

    @Override
    public void render(Canvas c) {

    }

    @Override
    public void touchEvents(MotionEvent event) {

    }
}
