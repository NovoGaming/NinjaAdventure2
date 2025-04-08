
package com.demoncube.ninjaadventure.game.handlers;

import static com.demoncube.ninjaadventure.game.helpers.GameConst.Sprite.ACTIVE_CHUNK_GRID_SIZE;
import static com.demoncube.ninjaadventure.game.helpers.GameConst.Sprite.CHUNK_GRID_SIZE;

import android.graphics.RectF;

import com.demoncube.ninjaadventure.game.entities.Character;
import com.demoncube.ninjaadventure.game.entities.Entity;
import com.demoncube.ninjaadventure.game.helpers.customVariables.CollisionBox;
import com.demoncube.ninjaadventure.game.mapManagement.MapManager;
import com.demoncube.ninjaadventure.game.mapManagement.maps.Chunk;
import com.demoncube.ninjaadventure.game.mapManagement.structures.Structure;

import java.util.ArrayList;
import java.util.List;

public class CollisionHandler {

    private MapManager mapManager;

    public CollisionHandler(MapManager mapManager) {
        this.mapManager = mapManager;
    }

    public int[] testCollisions (double delta, Character callingCharacter, double[] moveVector) {
        int[] returnStatus = {0,0};

        if ((moveVector[0] == 0 && moveVector[1] == 0)) return returnStatus;

        float intendedMoveDistanceX = (float) (moveVector[0] * delta * callingCharacter.getMovementSpeed());
        float intendedMoveDistanceY = (float) (moveVector[1] * delta * callingCharacter.getMovementSpeed());

        List<Entity> toCheck = new ArrayList<>();

        short chunkOffset = (CHUNK_GRID_SIZE - ACTIVE_CHUNK_GRID_SIZE) / 2;
        for (int i = chunkOffset; i < ACTIVE_CHUNK_GRID_SIZE; i++) {
            for (int j = chunkOffset; j < ACTIVE_CHUNK_GRID_SIZE; j++) {
                Chunk chunk = mapManager.getChunk(i, j);
                if (chunk == null) continue;
                toCheck.addAll(chunk.structures);
            }
        }

        if (toCheck.size() > 0) {
            for (Entity e : toCheck) {
                for (CollisionBox c : e.getCollisions()) {
                    switch (c.collisionGroup) {

                        case 0: {   // Check block collision
                            for (CollisionBox cc : callingCharacter.getCollisions()) {
                                if (cc.collisionGroup == 0) {

                                    if (moveVector[0] != 0) { // check right and left
                                        if (
                                                callingCharacter.getBoundBox().left + cc.rect.left  + intendedMoveDistanceX < e.getBoundBox().left + c.rect.right  &&
                                                callingCharacter.getBoundBox().left + cc.rect.right + intendedMoveDistanceX > e.getBoundBox().left + c.rect.left   &&
                                                callingCharacter.getBoundBox().top  + cc.rect.top                           < e.getBoundBox().top  + c.rect.bottom &&
                                                callingCharacter.getBoundBox().top  + cc.rect.bottom                        > e.getBoundBox().top  + c.rect.top
                                        ) {
                                            if (moveVector[0] > 0) {
                                                returnStatus[0] = 1;
                                            } else {
                                                returnStatus[0] = -1;
                                            }
                                            moveVector[0] = 0;
                                        }
                                    }

                                    if (moveVector[1] != 0) { // check up and down
                                        if (
                                                callingCharacter.getBoundBox().left + cc.rect.left                           < e.getBoundBox().left + c.rect.right  &&
                                                callingCharacter.getBoundBox().left + cc.rect.right                          > e.getBoundBox().left + c.rect.left   &&
                                                callingCharacter.getBoundBox().top  + cc.rect.top    + intendedMoveDistanceY < e.getBoundBox().top  + c.rect.bottom &&
                                                callingCharacter.getBoundBox().top  + cc.rect.bottom + intendedMoveDistanceY > e.getBoundBox().top  + c.rect.top
                                        ) {
                                            if (moveVector[1] > 0) {
                                                returnStatus[1] = 1;
                                            } else {
                                                returnStatus[1] = -1;
                                            }
                                            moveVector[1] = 0;
                                        }
                                    }
                                }
                            }
                        }
                        break;

                        case 1: {   // check trigger collisions
                            System.out.println("trigger box check");
                        }
                        break;
                    }
                }
            }
        }





        System.out.println(returnStatus[0] + ":" + returnStatus[1]);
        return returnStatus;
    }

}
