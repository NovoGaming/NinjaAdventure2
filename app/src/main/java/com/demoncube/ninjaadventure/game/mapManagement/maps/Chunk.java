package com.demoncube.ninjaadventure.game.mapManagement.maps;

import static com.demoncube.ninjaadventure.game.helpers.GameConst.Sprite.CHUNK_SIZE;

import com.demoncube.ninjaadventure.game.mapManagement.structures.Structure;

import java.util.ArrayList;
import java.util.List;

public class Chunk {

    public int posX, posY;
    public int[][] tileIds;
    public int[][] tileSetIds;
    public List<Structure> structures;


    public Chunk () {
        tileIds = new int[CHUNK_SIZE][CHUNK_SIZE];
        tileSetIds = new int[CHUNK_SIZE][CHUNK_SIZE];
        structures = new ArrayList<>();
    }

    public Chunk(int[][] tileIds, int[][] tileSetIds, List<Structure> structures) {
        this.tileIds = tileIds;
        this.tileSetIds = tileSetIds;
        this.structures = structures;
    }
}
