package com.demoncube.ninjaadventure.game.mapManagement;

import static com.demoncube.ninjaadventure.game.helpers.GameConst.Sprite.CHUNK_RADIUS;

import com.demoncube.ninjaadventure.game.entities.Entity;
import com.demoncube.ninjaadventure.game.entities.Player;
import com.demoncube.ninjaadventure.game.helpers.GameMapStorage;
import com.demoncube.ninjaadventure.game.mapManagement.maps.Chunk;
import com.demoncube.ninjaadventure.game.mapManagement.maps.OldGameMap;
import com.demoncube.ninjaadventure.game.mapManagement.structures.Structure;


public class MapManager {

    private Chunk[][] chunks;
    int centerChunkX, centerChunkY;
    int CurrentMapId = 0;

    public MapManager(int currentMapId, int centerChunkX, int playerChunkY) {
        this.centerChunkX = centerChunkX;
        this.centerChunkY = playerChunkY;
        CurrentMapId = currentMapId;
        chunks = new Chunk[CHUNK_RADIUS][CHUNK_RADIUS];
        updateChunks(centerChunkX, centerChunkY);
    }

    public void updateChunks(int newX, int newY) {

        int shiftX = newX - centerChunkX;
        int shiftY = newY - centerChunkY;
        if (Math.abs(shiftX) == 1) {
            shiftChunks(shiftX, 0);
            return;
        }
        if (Math.abs(shiftY) == 1) {
            shiftChunks(0, shiftY);
            return;
        }

        for (int i = 0; i < CHUNK_RADIUS; i++) {
            for (int j = 0; j < CHUNK_RADIUS; j++) {
                int x = newX + i - 2;
                int y = newY + j - 2;

                chunks[i][j] = GameMapStorage.getChunk(CurrentMapId, x, y);
            }
        }

    }

    private void shiftChunks(int xShift, int yShift) {
    /*
        for (int i = 0; i < CHUNK_RADIUS; i++) {
            for (int j = 0; j < CHUNK_RADIUS; j++) {
                int x = xShift + i;
                int y = yShift + j;
                if (x < 0) continue;
                chunks[i][j] = chunks[y][x];
            }
        }
     */

    }

    //-----------------------------------------------------------------------//
    //                                  Update                               //
    //-----------------------------------------------------------------------//

    public void update(double delta, float cameraX, float cameraY) {
        getDrawableList();
        for (Entity e : drawList) {
            e.setLastCamYValue(cameraY);
        }
    }

    private void getDrawableList() {
        drawList = new Entity[getDrawableAmount(map)];
        int i = 0;
        if (map.getStructures() != null)
            for (Structure structure : map.getStructures()) {
                drawList[i++] = structure;
            };
        if (map.getPlayers() != null)
            for (Player player : map.getPlayers()) {
                drawList[i++] = player;
            };
    }

    private int getDrawableAmount(OldGameMap map) {
        int amount = 0;
        if (map.getStructures() != null) amount += map.getStructures().size();
        if (map.getPlayers() != null) amount += map.getPlayers().size();
        return amount;
    }
}
