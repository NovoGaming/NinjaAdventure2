package com.demoncube.ninjaadventure.game.mapManagement.maps;

import static com.demoncube.ninjaadventure.game.helpers.GameConst.GameMap.CHUNK_GRID_SIZE;
import static com.demoncube.ninjaadventure.game.helpers.GameConst.GameMap.CHUNK_SIZE;
import static com.demoncube.ninjaadventure.game.helpers.GameConst.Sprite.SIZE;

import android.graphics.PointF;

import com.demoncube.ninjaadventure.game.entities.Character;
import com.demoncube.ninjaadventure.game.entities.structures.Structure;
import com.demoncube.ninjaadventure.game.handlers.CollisionHandler;

import java.util.ArrayList;
import java.util.List;

public class ChunkBuilder {

    //--------------------------------------------------------------------------------------------//
    //                                     Chunk functions                                        //
    //--------------------------------------------------------------------------------------------//

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
            System.out.println("Checking chunk " + posX + ":" + posY + " between [" + (posX * SIZE) + ":" + (posY * SIZE) + "] and  [" + ((posX + CHUNK_SIZE) * SIZE) + ":" + ((posY + CHUNK_SIZE) * SIZE) + "] for pos [" + pos.x + ":" + pos.y + "]");
            if (
                    pos.y >= posX * SIZE &&
                    pos.y < (posX + CHUNK_SIZE) * SIZE &&
                    pos.x >= posY * SIZE &&
                    pos.x < (posY + CHUNK_SIZE) * SIZE
            ) {
                System.out.println("Structure at " + pos.x + ":" + pos.y + "; loaded; " + posX + ":" + posY);
                chunk.structures.add(s);
            }
        }
        System.out.println("----------------------------------");

        return chunk;
    }

    //--------------------------------------------------------------------------------------------//
    //                                    Structure functions                                     //
    //--------------------------------------------------------------------------------------------//

    public static List<Structure> getStructures(int id) {
        return GameMaps.getById(id).structures;
    }

    //--------------------------------------------------------------------------------------------//
    //                                      NPCs functions                                        //
    //--------------------------------------------------------------------------------------------//

    private static List<Character> getNPCs(int id) {
        return GameMaps.getById(id).NPCs;
    }

    public static List<Character> getNPCs(int centerChunkX, int centerChunkY, int mapId, CollisionHandler collisionHandler) {

        List<Character> list = new ArrayList<>();
        int radius = CHUNK_GRID_SIZE / 2;

        for (Character character : getNPCs(mapId)) {
            if (
                    character.getBoundBox().top >= (centerChunkY - radius) * CHUNK_SIZE * SIZE &&
                    character.getBoundBox().top < (centerChunkY + radius + 1) * CHUNK_SIZE * SIZE &&
                    character.getBoundBox().left >= (centerChunkX - radius) * CHUNK_SIZE * SIZE &&
                    character.getBoundBox().left < (centerChunkX + radius + 1) * CHUNK_SIZE * SIZE
            ) {
                character.setCollisionHandler(collisionHandler);
                list.add(character);
            }
        }
        return list;
    }

    public static int getMapWidth(int id){
        return GameMaps.getById(id).tileIds[0].length;
    }

    public static int getMapHeight(int id){
        return GameMaps.getById(id).tileIds.length;
    }
}
