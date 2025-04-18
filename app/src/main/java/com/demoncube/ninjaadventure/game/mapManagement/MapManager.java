package com.demoncube.ninjaadventure.game.mapManagement;

import static com.demoncube.ninjaadventure.game.GameSettings.debug.BOX_STROKE_WIDTH;
import static com.demoncube.ninjaadventure.game.GameSettings.debug.CHUNK_BORDER_COLOR;
import static com.demoncube.ninjaadventure.game.GameSettings.debug.DRAW_MAP_CHUNKS;
import static com.demoncube.ninjaadventure.game.helpers.GameConst.GameMap.ACTIVE_CHUNK_GRID_SIZE;
import static com.demoncube.ninjaadventure.game.helpers.GameConst.GameMap.RENDER_CHUNK_GRID_SIZE;
import static com.demoncube.ninjaadventure.game.helpers.GameConst.GameMap.CHUNK_GRID_SIZE;
import static com.demoncube.ninjaadventure.game.helpers.GameConst.GameMap.CHUNK_SIZE;
import static com.demoncube.ninjaadventure.game.helpers.GameConst.Sprite.SIZE;

import android.graphics.Canvas;
import android.graphics.Paint;

import com.demoncube.ninjaadventure.game.entities.Character;
import com.demoncube.ninjaadventure.game.entities.Entity;
import com.demoncube.ninjaadventure.game.entities.Player;
import com.demoncube.ninjaadventure.game.handlers.CollisionHandler;
import com.demoncube.ninjaadventure.game.mapManagement.maps.ChunkBuilder;
import com.demoncube.ninjaadventure.game.mapManagement.maps.Chunk;
import com.demoncube.ninjaadventure.game.mapManagement.maps.Tileset;
import com.demoncube.ninjaadventure.game.entities.structures.Structure;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class MapManager {

    private Chunk[][] chunks;
    private int centerChunkY, centerChunkX;
    private int currentMapId = 0;

    public List<Player> players = new ArrayList<>();
    public List<Character> NPCs = new ArrayList<>();
    private CollisionHandler collisionHandler;

    private final Paint debugPaint;

    int renderRadius, activeRadius, renderOffset;

    public MapManager(int currentMapId) {
        this.centerChunkY = 0;
        this.centerChunkX = 0;
        this.currentMapId = currentMapId;

        chunks = new Chunk[CHUNK_GRID_SIZE][CHUNK_GRID_SIZE];

        debugPaint = new Paint();
        debugPaint.setStyle(Paint.Style.STROKE);
        debugPaint.setStrokeWidth(BOX_STROKE_WIDTH);
        debugPaint.setColor(CHUNK_BORDER_COLOR);


        if (RENDER_CHUNK_GRID_SIZE >= CHUNK_GRID_SIZE) {
            renderRadius = CHUNK_GRID_SIZE / 2;
            renderOffset = 0;
        } else {
            renderRadius = RENDER_CHUNK_GRID_SIZE / 2;
            renderOffset = CHUNK_GRID_SIZE / 2 - renderRadius;
        }

        if (ACTIVE_CHUNK_GRID_SIZE >= CHUNK_GRID_SIZE) {
            activeRadius = CHUNK_GRID_SIZE / 2;
        } else {
            activeRadius = ACTIVE_CHUNK_GRID_SIZE / 2;
        }
    }

    private void updateChunks(int centerX, int centerY) {
        int chunkRadius = CHUNK_GRID_SIZE/2;
        for (int dx = -chunkRadius; dx <= chunkRadius; dx++) {
            for (int dy = -chunkRadius; dy <= chunkRadius; dy++) {
                int chunkX = centerX + dx;
                int chunkY = centerY + dy;

                Chunk chunk = ChunkBuilder.getChunk(currentMapId, chunkX, chunkY);
                chunks[dx + chunkRadius][dy + chunkRadius] = chunk;
            }
        }
    }

    private void shiftChunks(int shiftX, int shiftY) {

        //---------------------------------------- Y shift ----------------------------------------//

        if (shiftY > 0) { // +Y shift
            for (int i = 0; i < CHUNK_GRID_SIZE - shiftY; i++) {
                for (int j = 0; j < CHUNK_GRID_SIZE; j++) {
                    chunks[i][j] = chunks[i + shiftY][j];
                }
            }

            for (int i =  CHUNK_GRID_SIZE - shiftY; i < CHUNK_GRID_SIZE; i++) {
                for (int j = 0; j < CHUNK_GRID_SIZE; j++) {
                    if (chunks[i][j] != null) {
                        chunks[i][j] = ChunkBuilder.getChunk(currentMapId, chunks[i][j].posX + shiftY, chunks[i][j].posY);
                    }
                }
            }
        }
        if (shiftY < 0) { // -Y shift
            for (int i = CHUNK_GRID_SIZE -1 ; i > -shiftY -1; i--) {
                for (int j = 0; j < CHUNK_GRID_SIZE; j++) {
                    chunks[i][j] = chunks[i + shiftY][j];
                }
            }

            for (int i = 0; i < -shiftY; i++) {
                for (int j = 0; j < CHUNK_GRID_SIZE; j++) {
                    if (chunks[i][j] != null) {
                        chunks[i][j] = ChunkBuilder.getChunk(currentMapId, chunks[i][j].posX + shiftY, chunks[i][j].posY);
                    }
                }
            }
        }

        //---------------------------------------- X shift ----------------------------------------//

        if (shiftX > 0) { // +X shift
            for (int i = 0; i < CHUNK_GRID_SIZE - shiftX; i++) {
                for (int j = 0; j < CHUNK_GRID_SIZE; j++) {
                    chunks[j][i] = chunks[j][i + shiftX];
                }
            }
            for (int i =  CHUNK_GRID_SIZE - shiftX; i < CHUNK_GRID_SIZE; i++) {
                for (int j = 0; j < CHUNK_GRID_SIZE; j++) {
                    if (chunks[j][i] != null) {
                        chunks[j][i] = ChunkBuilder.getChunk(currentMapId, chunks[j][i].posX, chunks[j][i].posY + shiftX);
                    }
                }
            }
        }
        if (shiftX < 0) { // -X shift
            for (int i = CHUNK_GRID_SIZE -1 ; i > -shiftX -1; i--) {
                for (int j = 0; j < CHUNK_GRID_SIZE; j++) {
                    chunks[j][i] = chunks[j][i + shiftX];
                }
            }
            for (int i = 0; i < -shiftX; i++) {
                for (int j = 0; j < CHUNK_GRID_SIZE; j++) {
                    if (chunks[j][i] != null) {
                        chunks[j][i] = ChunkBuilder.getChunk(currentMapId, chunks[j][i].posX, chunks[j][i].posY + shiftX);
                    }
                }
            }
        }
    }

    public Chunk[][] getChunks() {
        return chunks;
    }

    public Chunk getChunk(int x, int y) {
        if (x < 0 || x > CHUNK_GRID_SIZE) return null;
        if (y < 0 || y > CHUNK_GRID_SIZE) return null;

        return chunks[y][x];
    }


    //-----------------------------------------------------------------------//
    //                                  Update                               //
    //-----------------------------------------------------------------------//

    public Entity[] drawList;

    public void update(double delta, float cameraX, float cameraY, Player player) {
        update(delta, cameraX, cameraY, player, false);
    }

    public void update(double delta, float cameraX, float cameraY, Player player, boolean forceUpdate) {

        int newChunkY = (int) ((player.getPosition().x + SIZE/2) / (CHUNK_SIZE * SIZE));
        int newChunkX = (int) ((player.getPosition().y + SIZE/2) / (CHUNK_SIZE * SIZE));

        if ( forceUpdate || (newChunkY != centerChunkX || newChunkX != centerChunkY)) {
            int shiftX = newChunkY - centerChunkX;
            int shiftY = newChunkX - centerChunkY;


            if (((shiftX < 5 && shiftX > -5) || (shiftY < 5 && shiftY > -5)) && !forceUpdate) {
                shiftChunks(shiftX, shiftY);
            } else {
                updateChunks(newChunkX, newChunkY);
            }

            centerChunkX = newChunkY;
            centerChunkY = newChunkX;
            if (collisionHandler == null) return;
            NPCs = ChunkBuilder.getNPCs(centerChunkX, centerChunkY, currentMapId, collisionHandler);
        }

        if (NPCs != null)
            for (Character npc : NPCs) {
                if (
                        npc.getBoundBox().top >= (centerChunkY - activeRadius) * CHUNK_SIZE * SIZE &&
                        npc.getBoundBox().top < (centerChunkY + activeRadius + 1) * CHUNK_SIZE * SIZE &&
                        npc.getBoundBox().left >= (centerChunkX - activeRadius) * CHUNK_SIZE * SIZE &&
                        npc.getBoundBox().left < (centerChunkX + activeRadius + 1) * CHUNK_SIZE * SIZE
                ) {
                    npc.setActive(true);
                    npc.update(delta,cameraX,cameraY);
                } else {
                    npc.setActive(false);
                }
            }


        getDrawableList();
        for (Entity e : drawList) {
            e.setLastCamYValue(cameraY);
        }
    }

    private void getDrawableList() {
        drawList = new Entity[getDrawableAmount()];
        int i = 0;

        for (int j = 0; j < CHUNK_GRID_SIZE; j++) {
            for (int k = 0; k < CHUNK_GRID_SIZE; k++) {
                Chunk chunk = chunks[j][k];
                if (chunk == null) continue;
                if (chunk.structures != null)
                    for (Structure structure : chunk.structures) {
                        drawList[i++] = structure;
                    }
            }
        }

        if (NPCs != null)
            for (Character npc : NPCs) {
                drawList[i++] = npc;
            }

        if (players != null)
            for (Player player : players) {
                drawList[i++] = player;
            }
    }

    private int getDrawableAmount() {
        int amount = 0;
        for (int i = 0; i < CHUNK_GRID_SIZE; i++) {
            for (int j = 0; j < CHUNK_GRID_SIZE; j++) {
                Chunk chunk = chunks[i][j];
                if (chunk == null) continue;
                if (chunk.structures != null) amount += chunk.structures.size();
            }
        }

        if (NPCs != null)
            amount += NPCs.size();


        if (players != null) amount += players.size();
        return amount;
    }

    //-----------------------------------------------------------------------//
    //                                  Render                               //
    //-----------------------------------------------------------------------//

    public void render(Canvas c, float cameraX, float cameraY) {
        for (int dx = -renderRadius; dx <= renderRadius; dx++) {
            for (int dy = -renderRadius; dy <= renderRadius; dy++) {
                Chunk chunk = chunks[dx + renderOffset + renderRadius][dy + renderOffset + renderRadius];
                if (chunk != null) {
                    renderChunk(c, cameraX, cameraY, chunk);
                    if (!DRAW_MAP_CHUNKS) continue;
                    c.drawRect(
                            chunk.posY * CHUNK_SIZE * SIZE + cameraX,
                            chunk.posX * CHUNK_SIZE * SIZE + cameraY,
                             chunk.posY *CHUNK_SIZE * SIZE + cameraX + CHUNK_SIZE * SIZE,
                             chunk.posX *CHUNK_SIZE * SIZE + cameraY + CHUNK_SIZE * SIZE,
                            debugPaint
                    );
                }
            }
        }

        Arrays.sort(drawList);
        for (Entity e : drawList) {
            e.render(c, cameraX, cameraY);
        }

    }

    private void renderChunk(Canvas c, float cameraX, float cameraY, Chunk chunk) {
        for (int i = 0; i < CHUNK_SIZE; i++) {
            for (int j = 0; j < CHUNK_SIZE; j++) {
                int spriteId = chunk.tileIds[i][j];

                Tileset tileset;
                switch (chunk.tileSetIds[i][j]) {
                    case 0: tileset = Tileset.FLOOR; break;
                    case 1: tileset = Tileset.CLIFF; break;
                    default: {
                        tileset = Tileset.FLOOR;
                        spriteId = 10;
                    }
                }
                c.drawBitmap(
                        tileset.getSprite(spriteId),
                        j * SIZE + chunk.posY * CHUNK_SIZE * SIZE + cameraX,
                        i * SIZE + chunk.posX * CHUNK_SIZE * SIZE + cameraY,
                        null
                );
            }
        }
    }

    //-----------------------------------------------------------------------//
    //                              Other Functions                          //
    //-----------------------------------------------------------------------//

    public int getMapWidth(){
        return ChunkBuilder.getMapWidth(currentMapId) * SIZE;
    }

    public int getMapHeight(){
        return ChunkBuilder.getMapHeight(currentMapId) * SIZE;
    }

    public void setCollisionHandlers (CollisionHandler collisionHandler) {
        this.collisionHandler = collisionHandler;
    }
}
