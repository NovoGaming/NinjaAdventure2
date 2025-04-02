package com.demoncube.ninjaadventure.game.mapManagement.maps;

import static com.demoncube.ninjaadventure.game.helpers.GameConst.Sprite.CHUNK_SIZE;

import com.demoncube.ninjaadventure.game.mapManagement.structures.Structure;

import java.util.ArrayList;
import java.util.List;

public class Chunk {

    public int posX, posY;
    public short[][] tileIds;
    public short[][] tileSetIds;
    public List<Structure> structures;


    public Chunk (int x, int y) {
        this.posX = x;
        this.posY = y;

        tileIds = new short[CHUNK_SIZE][CHUNK_SIZE];
        tileSetIds = new short[CHUNK_SIZE][CHUNK_SIZE];
        structures = new ArrayList<>();
    }

    public Chunk(short[][] tileIds, short[][] tileSetIds, List<Structure> structures) {
        this.tileIds = tileIds;
        this.tileSetIds = tileSetIds;
        this.structures = structures;
    }
}
