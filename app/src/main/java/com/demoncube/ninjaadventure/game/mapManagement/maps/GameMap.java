package com.demoncube.ninjaadventure.game.mapManagement.maps;

import com.demoncube.ninjaadventure.game.helpers.GameConst;

public class GameMap {

    private int[][] spritesIds;
    private int[][] tilesetIds;


    public GameMap(int[][] spritesIds, int[][] tilesetIds) {
        this.spritesIds = spritesIds;
        this.tilesetIds = tilesetIds;
    }

    public int getTileset(int xIndex, int yIndex) {
        return tilesetIds[yIndex][xIndex];
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