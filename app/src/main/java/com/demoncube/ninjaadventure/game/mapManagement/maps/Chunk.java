package com.demoncube.ninjaadventure.game.mapManagement.maps;

import static com.demoncube.ninjaadventure.game.helpers.GameConst.GameMap.CHUNK_SIZE;

import com.demoncube.ninjaadventure.game.entities.Character;
import com.demoncube.ninjaadventure.game.entities.structures.Structure;

import java.util.ArrayList;
import java.util.List;

public class Chunk {

    public int posX, posY;
    public short[][] tileIds;
    public short[][] tileSetIds;
    public List<Structure> structures;
    public final List<Character> NPCs;



    public Chunk (int x, int y) {
        this.posX = x;
        this.posY = y;

        tileIds = new short[CHUNK_SIZE][CHUNK_SIZE];
        tileSetIds = new short[CHUNK_SIZE][CHUNK_SIZE];
        structures = new ArrayList<>();
        NPCs = new ArrayList<>();
    }

    public Chunk(short[][] tileIds, short[][] tileSetIds, List<Structure> structures, List<Character> NPCs) {
        this.tileIds = tileIds;
        this.tileSetIds = tileSetIds;
        this.structures = structures;
        this.NPCs = NPCs;
    }
}
