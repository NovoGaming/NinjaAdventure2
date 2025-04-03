package com.demoncube.ninjaadventure.game.mapManagement.maps;

import static com.demoncube.ninjaadventure.game.helpers.GameConst.Sprite.CHUNK_SIZE;
import static com.demoncube.ninjaadventure.game.helpers.GameConst.Sprite.SIZE;

import android.graphics.PointF;

import com.demoncube.ninjaadventure.game.mapManagement.structures.Structure;

import java.util.List;

public class ChunkBuilder {

    public static List<Structure> getStructures(int id) {
        return GameMaps.getById(id).structures;
    }

    public static Chunk getChunk(int mapId, int x, int y) {

        int posX = x * CHUNK_SIZE;
        int posY = y * CHUNK_SIZE;

        GameMaps map = GameMaps.getById(mapId);
        short[][] tileIds = map.tileIds;

        if (posX < 0 || posY < 0 || posX >= tileIds.length || posY >= tileIds[0].length) {
            return null;
        }

        Chunk chunk = new Chunk(x, y);

        short[][] tileSetIds = map.tileSetIds;

        for (int i = 0; i < CHUNK_SIZE; i++) {
            for (int j = 0; j < CHUNK_SIZE; j++) {
                if (posX + i  >= tileIds.length || posY + j  >= tileIds[0].length) {
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
                    pos.x > posX * SIZE &&
                    pos.x < (posX + CHUNK_SIZE) * SIZE &&
                    pos.y > posY * SIZE &&
                    pos.y < (posY + CHUNK_SIZE) * SIZE
            ) {
                System.out.println("Structure at " + pos.x + ":" + pos.y + "; loaded; " + posX + ":" + posY);
                chunk.structures.add(s);
            }
        }

        return chunk;
    }
}
