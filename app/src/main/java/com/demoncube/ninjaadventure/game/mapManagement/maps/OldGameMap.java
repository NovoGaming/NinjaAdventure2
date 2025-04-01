package com.demoncube.ninjaadventure.game.mapManagement.maps;

import com.demoncube.ninjaadventure.game.entities.Player;
import com.demoncube.ninjaadventure.game.entities.enemies.Skeleton;
import com.demoncube.ninjaadventure.game.helpers.GameConst;
import com.demoncube.ninjaadventure.game.mapManagement.structures.Structure;

import java.util.ArrayList;

public class OldGameMap {

    private short[][] spritesIds;
    private short[][] tilesetIds;

    private ArrayList<Structure> structures;
    private ArrayList<Skeleton> enemies;
    private ArrayList<Player> players = new ArrayList<>();


    public OldGameMap(short[][] spritesIds, short[][] tilesetIds, ArrayList<Structure> structures) {
        if (structures == null) this.structures = new ArrayList<>(); else this.structures = structures;
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

    public ArrayList<Structure> getStructures() {
        return structures;
    }
    public ArrayList<Player> getPlayers() {
        return players;
    }
}