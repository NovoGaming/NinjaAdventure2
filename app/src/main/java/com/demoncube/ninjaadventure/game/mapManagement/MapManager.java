package com.demoncube.ninjaadventure.game.mapManagement;

import static com.demoncube.ninjaadventure.game.GameSettings.debug.DRAW_MAP_CHUNKS;
import static com.demoncube.ninjaadventure.game.helpers.GameConst.Sprite.ACTIVE_CHUNK_GRID_SIZE;
import static com.demoncube.ninjaadventure.game.helpers.GameConst.Sprite.CHUNK_GRID_SIZE;
import static com.demoncube.ninjaadventure.game.helpers.GameConst.Sprite.CHUNK_SIZE;
import static com.demoncube.ninjaadventure.game.helpers.GameConst.Sprite.SIZE;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

import com.demoncube.ninjaadventure.game.entities.Entity;
import com.demoncube.ninjaadventure.game.entities.Player;
import com.demoncube.ninjaadventure.game.helpers.GameMapStorage;
import com.demoncube.ninjaadventure.game.mapManagement.maps.Chunk;
import com.demoncube.ninjaadventure.game.mapManagement.maps.Tileset;
import com.demoncube.ninjaadventure.game.mapManagement.structures.Structure;

import java.util.ArrayList;
import java.util.Arrays;


public class MapManager {

    private Chunk[][] chunks;
    private int centerChunkX, centerChunkY;
    private int currentMapId = 0;

    public ArrayList<Player> players = new ArrayList<>();

    private final Paint debugPaint, debugPaint2;

    int renderRadius, offset;

    public MapManager(int currentMapId, int centerChunkX, int centerChunkY) {
        this.centerChunkX = centerChunkX;
        this.centerChunkY = centerChunkY;
        this.currentMapId = currentMapId;

        chunks = new Chunk[CHUNK_GRID_SIZE][CHUNK_GRID_SIZE];
        updateChunks(this.centerChunkX, this.centerChunkY);

        debugPaint = new Paint();
        debugPaint.setStyle(Paint.Style.STROKE);
        debugPaint.setStrokeWidth(5);
        debugPaint.setColor(Color.WHITE);

        debugPaint2 = new Paint();
        debugPaint2.setStyle(Paint.Style.STROKE);
        debugPaint2.setStrokeWidth(5);
        debugPaint2.setColor(Color.BLUE);


        if (ACTIVE_CHUNK_GRID_SIZE > CHUNK_GRID_SIZE) {
            renderRadius = CHUNK_GRID_SIZE / 2;
            offset = 0;
        } else {
            renderRadius = ACTIVE_CHUNK_GRID_SIZE / 2;
            offset = CHUNK_GRID_SIZE / 2 - renderRadius;
        }
    }

    private void updateChunks(int centerX, int centerY) {
        int chunkRadius = CHUNK_GRID_SIZE/2;
        for (int dx = -chunkRadius; dx <= chunkRadius; dx++) {
            for (int dy = -chunkRadius; dy <= chunkRadius; dy++) {
                int chunkX = centerX + dx;
                int chunkY = centerY + dy;

                Chunk chunk = GameMapStorage.getChunk(currentMapId, chunkX, chunkY);
                chunks[dx + chunkRadius][dy + chunkRadius] = chunk;
            }
        }
    }

    private void shiftChunks(int shiftX, int shiftY) {


    }

    //-----------------------------------------------------------------------//
    //                                  Update                               //
    //-----------------------------------------------------------------------//

    public Entity[] drawList;

    public void update(double delta, float cameraX, float cameraY, Player player) {

        int newChunkX = (int) (player.getPosition().x / (CHUNK_SIZE * SIZE));
        int newChunkY = (int) (player.getPosition().y / (CHUNK_SIZE * SIZE));

        if (newChunkX != centerChunkX || newChunkY != centerChunkY) {
            shiftChunks( centerChunkX - newChunkX, centerChunkY - newChunkY );    //Work in progress
            centerChunkX = newChunkX;
            centerChunkY = newChunkY;
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
                Chunk chunk = chunks[i][j];
                if (chunk == null) continue;
                if (chunk.structures != null)
                    for (Structure structure : chunk.structures) {
                        drawList[i++] = structure;
                    };
            }
        }

        if (players != null)
            for (Player player : players) {
                drawList[i++] = player;
            };
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
        if (players != null) amount += players.size();
        return amount;
    }

    //-----------------------------------------------------------------------//
    //                                  Render                               //
    //-----------------------------------------------------------------------//

    public void render(Canvas c, float cameraX, float cameraY) {
        for (int dx = -renderRadius; dx <= renderRadius; dx++) {
            for (int dy = -renderRadius; dy <= renderRadius; dy++) {
                Chunk chunk = chunks[dx + offset + renderRadius][dy + offset + renderRadius];
                if (chunk != null) {
                    renderChunk(c, cameraX, cameraY, chunk);
                    if (!DRAW_MAP_CHUNKS) continue;
                    c.drawRect(
                            chunk.posY * CHUNK_SIZE * SIZE + cameraX,
                            chunk.posX * CHUNK_SIZE * SIZE + cameraY,
                             chunk.posY *CHUNK_SIZE * SIZE + cameraX + CHUNK_SIZE * SIZE,
                             chunk.posX *CHUNK_SIZE * SIZE + cameraY + CHUNK_SIZE * SIZE,
                            debugPaint2
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




}
