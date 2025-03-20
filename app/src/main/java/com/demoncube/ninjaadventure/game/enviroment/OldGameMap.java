package com.demoncube.ninjaadventure.game.enviroment;

import com.demoncube.ninjaadventure.game.helpers.GameConst;

public class OldGameMap {

    private int[][] spritesIds;
    private Tileset tileset;


    public OldGameMap(int[][] spritesIds, Tileset tileset) {
        this.spritesIds = spritesIds;
        this.tileset = tileset;
    }

    public Tileset getTileset() {
        return tileset;
    }

    public int getSpriteID(int xIndex, int yIndex) {
        return spritesIds[yIndex][xIndex];
    }

    public int getArrayWidth() {
        return spritesIds[0].length;
    }
    public int getArrayHeight() {
        return spritesIds.length;
    }

    public int getMapWidth() {
        return getArrayWidth() * GameConst.Sprite.SIZE;
    }
    public int getMapHeight() {
        return getArrayHeight() * GameConst.Sprite.SIZE;
    }

}