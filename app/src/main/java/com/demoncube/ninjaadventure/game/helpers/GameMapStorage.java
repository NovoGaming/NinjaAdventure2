package com.demoncube.ninjaadventure.game.helpers;

import static com.demoncube.ninjaadventure.game.helpers.GameConst.Sprite.CHUNK_SIZE;
import static com.demoncube.ninjaadventure.game.helpers.GameConst.Sprite.SIZE;

import android.graphics.PointF;

import com.demoncube.ninjaadventure.game.mapManagement.maps.Chunk;
import com.demoncube.ninjaadventure.game.mapManagement.structures.Structure;
import com.demoncube.ninjaadventure.game.mapManagement.structures.StructureSet;

import java.util.ArrayList;
import java.util.List;

public class GameMapStorage {

    public static int[][] getTileIds(int id) {
        switch (id) {
            default: return MainMap.TILE_IDS;
        }
    }

    public static int[][] getTileSetIds(int id) {
        switch (id) {
            default: return MainMap.TILESET_IDS;
        }
    }

    public static List<Structure> getStructures(int id) {
        switch (id) {
            default: return MainMap.STRUCTURES();
        }
    }

    public static Chunk getChunk(int mapId, int x, int y) {
        System.out.println("Preparing chunk " + x + ":" + y);

        int posX = x * CHUNK_SIZE;
        int posY = y * CHUNK_SIZE;

        int[][] tileIds = getTileIds(mapId);

        System.out.println(posX + ":" + posY + " | " + tileIds[0].length + ":" + tileIds.length);

        if (posX >= tileIds.length || posX < 0) return null;
        if (posY >= tileIds[0].length || posY < 0) return null;

        Chunk chunk = new Chunk();
        chunk.posX = x;
        chunk.posY = y;

        int[][] tileSetIds = getTileSetIds(mapId);

        for (int i = 0; i < CHUNK_SIZE; i++) {
            for (int j = 0; j < CHUNK_SIZE; j++) {
                if (posX + i  >= tileIds.length) {
                    chunk.tileIds[i][j] = 10;
                    chunk.tileSetIds[i][j] = 0;
                    continue;
                }
                if (posY + j  >= tileIds[0].length) {
                    chunk.tileIds[i][j] = 10;
                    chunk.tileSetIds[i][j] = 0;
                    continue;
                }
                chunk.tileIds[i][j] = tileIds[posX + i][posY + j];
                chunk.tileSetIds[i][j] = tileSetIds[posX + i][posY + j];
            }
        }

        List<Structure> structures = getStructures(mapId);

        for (Structure s: structures) {
            PointF pos = s.getPosition();
            if (
                    pos.x > posX &&
                    pos.x < posX + CHUNK_SIZE &&
                    pos.y > posY &&
                    pos.y < posY + CHUNK_SIZE
            ) {
                chunk.structures.add(s);
            }
        }

        return chunk;
    }

    public static final class MainMap {

        //-------------------------------- Layer 1 --------------------------------//
        public static final int[][] TILE_IDS = {
                {188, 189, 279, 275, 187, 189, 279, 275, 279, 276, 275, 279, 275, 275, 279, 275, 278, 276, 275, 278, 275, 279, 275},
                {188, 189, 275, 279, 187, 189, 276, 275, 279, 275, 277, 275, 275, 277, 276, 275, 279, 278, 278, 275, 275, 279, 275},
                {188, 189, 275, 276, 187, 189, 276, 279, 275, 278, 279, 279, 275, 275, 278, 278, 275, 275, 275, 276, 275, 279, 275},
                {254, 189, 275, 279, 187, 214, 166, 166, 166, 166, 166, 166, 166, 167, 275, 276, 275, 276, 279, 277, 275, 279, 275},
                {188, 189, 275, 275, 209, 210, 210, 210, 210, 195, 210, 210, 193, 189, 275, 277, 168, 275, 278, 275, 275, 276, 275},
                {188, 189, 279, 276, 279, 275, 276, 275, 277, 190, 275, 279, 187, 189, 275, 279, 190, 275, 279, 275, 275, 279, 275},
                {188, 189, 275, 275, 275, 279, 278, 275, 275, 190, 276, 277, 187, 258, 232, 232, 239, 232, 232, 232, 232, 233, 275},
                {188, 189, 275, 279, 275, 275, 231, 232, 232, 238, 275, 275, 187, 189, 275, 275, 275, 275, 275, 275, 275, 275, 275},
                {188, 189, 276, 279, 278, 275, 276, 275, 275, 275, 275, 276, 187, 189, 276, 275, 277, 275, 279, 275, 279, 275, 276},
                {188, 189, 275, 275, 279, 275, 279, 275, 276, 275, 275, 277, 187, 189, 279, 275, 275, 275, 275, 275, 275, 275, 275},
                {188, 214, 167, 276, 275, 277, 275, 275, 278, 275, 276, 275, 187, 189, 275, 275, 278, 275, 275, 276, 275, 277, 275},
                {254, 188, 214, 167, 275, 278, 275, 275, 275, 275, 279, 275, 187, 189, 275, 275, 275, 168, 275, 275, 275, 275, 278},
                {188, 188, 188, 214, 167, 279, 275, 277, 275, 277, 276, 275, 187, 258, 232, 232, 232, 238, 275, 279, 275, 275, 279},
                {188, 188, 188, 253, 214, 167, 275, 277, 168, 275, 275, 275, 187, 189, 275, 275, 275, 275, 275, 279, 275, 275, 275},
                {253, 188, 188, 188, 256, 214, 167, 275, 235, 232, 232, 232, 259, 189, 279, 275, 275, 277, 275, 275, 275, 279, 275},
                {188, 188, 188, 254, 188, 256, 214, 167, 275, 275, 277, 275, 187, 189, 275, 278, 275, 275, 279, 275, 279, 278, 275}
        };

        public static final int[][] TILESET_IDS = {
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}
        };

        public static ArrayList<Structure> STRUCTURES() {
            ArrayList<Structure> array = new ArrayList<>();
            array.add(new Structure(new PointF(5 * SIZE,3 * SIZE), StructureSet.VILLAGE,8));
            array.add(new Structure(new PointF(15 * SIZE,2 * SIZE), StructureSet.VILLAGE,0));
            array.add(new Structure(new PointF(16 * SIZE,9 * SIZE), StructureSet.VILLAGE,2));
            array.add(new Structure(new PointF(7 * SIZE,10 * SIZE), StructureSet.VILLAGE,4));
            return array;
        }
    }
}
