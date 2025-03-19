package com.demoncube.ninjaadventure.game.gamestates;

import com.demoncube.ninjaadventure.game.Game;

public abstract class BaseState {
    protected Game game;

    public BaseState(Game game) {
        this.game = game;
    }

    public Game getGame() {
        return game;
    }
}
