
package com.demoncube.ninjaadventure.game.handlers;

import static com.demoncube.ninjaadventure.game.helpers.GameConst.Sprite.CHUNK_GRID_SIZE;

import android.graphics.Rect;

import com.demoncube.ninjaadventure.game.entities.Character;
import com.demoncube.ninjaadventure.game.entities.Entity;
import com.demoncube.ninjaadventure.game.helpers.customVariables.CollisionBox;
import com.demoncube.ninjaadventure.game.mapManagement.MapManager;
import com.demoncube.ninjaadventure.game.mapManagement.maps.Chunk;

import java.util.ArrayList;
import java.util.List;

public class CollisionHandler {

    private MapManager mapManager;
    private List<CollisionBox> collisionMap;
    private Rect mapBounds;

    public CollisionHandler(MapManager mapManager) {
        this.mapManager = mapManager;
    }

    public void update(){
        collisionMap = new ArrayList<>();

        for (int i = 0; i < CHUNK_GRID_SIZE; i++) {
            for (int j = 0; j < CHUNK_GRID_SIZE; j++) {
                Chunk chunk = mapManager.getChunk(i, j);
                if (chunk == null) continue;

                for (Entity e : chunk.structures) {
                    for (CollisionBox c : e.getCollisions()) {
                        Rect transformedCollision = new Rect(c.rect);
                        transformedCollision.offset((int) e.getBoundBox().left, (int) e.getBoundBox().top);
                        collisionMap.add(new CollisionBox(transformedCollision, c.collisionGroup));
                    }
                }
            }
        }

        mapBounds = new Rect(
                0,
                0,
                mapManager.getMapWidth(),
                mapManager.getMapHeight()
        );
    }



    public int[] testCollisions (double delta, Character callingCharacter, double[] moveVector) {
        int[] returnStatus = {0,0};
        if (collisionMap == null) return returnStatus;
        if ((moveVector[0] == 0 && moveVector[1] == 0)) return returnStatus;

        float intendedMoveDistanceX = (float) (moveVector[0] * delta * callingCharacter.getMovementSpeed());
        float intendedMoveDistanceY = (float) (moveVector[1] * delta * callingCharacter.getMovementSpeed());

        for (CollisionBox collisionBox : collisionMap) {
            for (CollisionBox c : callingCharacter.getCollisions()){

                if (c.collisionGroup == 0) {
                    Rect transformedCollision = new Rect(c.rect);
                    transformedCollision.offset((int) callingCharacter.getBoundBox().left, (int) callingCharacter.getBoundBox().top);
                    switch (collisionBox.collisionGroup){
                        case 0: {
                            if (moveVector[0] != 0) { // check right and left
                                if (    //map bounds check
                                        transformedCollision.left + intendedMoveDistanceX < mapBounds.left ||
                                        transformedCollision.right + intendedMoveDistanceX > mapBounds.right
                                ) {
                                    if (moveVector[0] > 0) {
                                        returnStatus[0] = 1;
                                    } else {
                                        returnStatus[0] = -1;
                                    }
                                    moveVector[0] = 0;
                                } else if ( // other collisions
                                        transformedCollision.left  + intendedMoveDistanceX < collisionBox.rect.right  &&
                                        transformedCollision.right + intendedMoveDistanceX > collisionBox.rect.left   &&
                                        transformedCollision.top                           < collisionBox.rect.bottom &&
                                        transformedCollision.bottom                        > collisionBox.rect.top
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
                                if (    //map bounds check
                                        transformedCollision.top + intendedMoveDistanceY < mapBounds.top ||
                                        transformedCollision.bottom + intendedMoveDistanceY > mapBounds.bottom
                                ) {
                                    if (moveVector[1] > 0) {
                                        returnStatus[1] = 1;
                                    } else {
                                        returnStatus[1] = -1;
                                    }
                                    moveVector[1] = 0;
                                } else if ( //other collisions
                                        transformedCollision.left                           < collisionBox.rect.right  &&
                                        transformedCollision.right                          > collisionBox.rect.left   &&
                                        transformedCollision.top    + intendedMoveDistanceY < collisionBox.rect.bottom &&
                                        transformedCollision.bottom + intendedMoveDistanceY > collisionBox.rect.top
                                ) {
                                    if (moveVector[1] > 0) {
                                        returnStatus[1] = 1;
                                    } else {
                                        returnStatus[1] = -1;
                                    }
                                    moveVector[1] = 0;
                                }
                            }
                        } break;
                        case 1:{ //trigger box detectieon
                            if (
                                    collisionBox.rect.contains(
                                            (int) (transformedCollision.left + intendedMoveDistanceX),
                                            (int) (transformedCollision.top + intendedMoveDistanceY),
                                            (int) (transformedCollision.right + intendedMoveDistanceX),
                                            (int) (transformedCollision.bottom + intendedMoveDistanceY)
                                    )
                            ) {
                                System.out.println("Trigger");
                            }
                        }
                    }
                }
            }
        }

        return returnStatus;
    }
}
