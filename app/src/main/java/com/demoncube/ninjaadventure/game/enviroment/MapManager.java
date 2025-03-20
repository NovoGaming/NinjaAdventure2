package com.demoncube.ninjaadventure.game.enviroment;

import android.graphics.Bitmap;
import android.graphics.Canvas;

import com.demoncube.ninjaadventure.game.helpers.GameConst;

public class MapManager {
    private GameMap map;
    private int tileSize = GameConst.Sprite.DEFAULT_SIZE;

    public MapManager(GameMap map) {
        this.map = map;
    }

}