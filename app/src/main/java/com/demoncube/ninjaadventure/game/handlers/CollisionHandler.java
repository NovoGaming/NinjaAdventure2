
package com.demoncube.ninjaadventure.game.handlers;

import static com.demoncube.ninjaadventure.game.helpers.GameConst.GameMap.CHUNK_GRID_SIZE;
import static com.demoncube.ninjaadventure.game.helpers.GameConst.collisionHandler.BINARY_SEARCH_LOOP_AMOUNT;

import android.graphics.Rect;
import android.graphics.RectF;

import com.demoncube.ninjaadventure.game.entities.Character;
import com.demoncube.ninjaadventure.game.entities.Entity;
import com.demoncube.ninjaadventure.game.helpers.customVariables.CollisionBox;
import com.demoncube.ninjaadventure.game.mapManagement.MapManager;
import com.demoncube.ninjaadventure.game.mapManagement.maps.Chunk;

import java.util.ArrayList;
import java.util.List;

public class CollisionHandler {

    private static class EntityCollisionBox {
        CollisionBox box;
        Entity owner;

        EntityCollisionBox(CollisionBox box, Entity owner) {
            this.box = box;
            this.owner = owner;
        }
    }

    private final MapManager mapManager;

    private final List<EntityCollisionBox> collisionGroup1 = new ArrayList<>(); // Standard collisions
    private final List<EntityCollisionBox> collisionGroup2 = new ArrayList<>(); // Triggers
    private final List<EntityCollisionBox> collisionGroup3 = new ArrayList<>(); // hit boxes;
    private Rect mapBounds;

    public CollisionHandler(MapManager mapManager) {
        this.mapManager = mapManager;
    }

    public void update() {
        collisionGroup1.clear();

        for (int i = 0; i < CHUNK_GRID_SIZE; i++) {
            for (int j = 0; j < CHUNK_GRID_SIZE; j++) {
                Chunk chunk = mapManager.getChunk(i, j);
                if (chunk == null) continue;

                for (Entity e : chunk.structures) {
                    CollisionBox[] entityBoxes = e.getCollisions();
                    if (entityBoxes == null) continue;
                    for (CollisionBox c : entityBoxes) {
                        if (c == null) continue;
                        if (!c.isActive) continue;
                        Rect transformed = new Rect(c.rect);
                        transformed.offset((int) e.getBoundBox().left, (int) e.getBoundBox().top);
                        collisionGroup1.add(new EntityCollisionBox(new CollisionBox(transformed, c.collisionGroup, c.isActive), e));
                    }
                }
            }
        }

        mapBounds = new Rect(0, 0, mapManager.getMapWidth(), mapManager.getMapHeight());
    }



    // ------------ Move test collisions ------------ //
    public double[] testCollisions(Character caller, double[] moveVector) {
        double[] result = {0, 0};
        if (collisionGroup1.isEmpty() || (moveVector[0] == 0 && moveVector[1] == 0)) return result;

        RectF callerBounds = caller.getBoundBox();
        CollisionBox[] characterCollisions = caller.getCollisions();

        for (CollisionBox c : characterCollisions) {
            if (c == null || !c.isActive || c.collisionGroup != 0) continue;

            Rect testRect = new Rect(c.rect);
            testRect.offset((int) callerBounds.left, (int) callerBounds.top);

            // Check map bounds
            if (testRect.left + moveVector[0] < mapBounds.left || testRect.right + moveVector[0] > mapBounds.right) {
                result[0] = moveVector[0] > 0 ? 1 : -1;
                moveVector[0] = 0;
            }
            if (testRect.top + moveVector[1] < mapBounds.top || testRect.bottom + moveVector[1] > mapBounds.bottom) {
                result[1] = moveVector[1] > 0 ? 1 : -1;
                moveVector[1] = 0;
            }

            for (EntityCollisionBox ec : collisionGroup1) {
                CollisionBox cb = ec.box;
                if (!cb.isActive) continue;

                switch (cb.collisionGroup) {
                    case 0: {
                        // Check top, down;
                        if (moveVector[1] != 0) {
                            if (checkAABBCollision( testRect.left, (float) (testRect.top + moveVector[1]), testRect.right, (float) (testRect.bottom + moveVector[1]), cb.rect)) {
                                if (moveVector[1] > 0) {
                                    double low = 0, high = moveVector[1], best = 0;
                                    float mid;
                                    for (int i = 0; i < BINARY_SEARCH_LOOP_AMOUNT; i++) {
                                        mid = (float) ((low + high) / 2);

                                        if (checkAABBCollision(testRect.left, testRect.top + mid, testRect.right, testRect.bottom + mid, cb.rect)) {
                                            high = mid; // Still colliding, move less
                                        } else {
                                            best = mid;
                                            low = mid;  // Safe so far, move more
                                        }
                                    }

                                    result[1] = 1;
                                    moveVector[1] = best;

                                } else {
                                    double low = moveVector[1], high = 0, best = 0;
                                    float mid;

                                    for (int i = 0; i < BINARY_SEARCH_LOOP_AMOUNT; i++) {
                                        mid = (float) ((low + high) /2);


                                        if (checkAABBCollision(testRect.left, testRect.top + mid, testRect.right, testRect.bottom + mid, cb.rect)) {
                                            low = mid;
                                        } else {
                                            high = mid;
                                            best = mid;
                                        }
                                    }

                                    result[1] = -1;
                                    moveVector[1] = best;
                                }
                            }
                        }

                        //check left right
                        if (moveVector[0] != 0) {
                            if (checkAABBCollision((float) (testRect.left + moveVector[0]), testRect.top, (float) (testRect.right + moveVector[0]), testRect.bottom, cb.rect)) {
                                if (moveVector[0] > 0) {
                                    double low = 0, high = moveVector[0], best = 0;
                                    float mid;
                                    for (int i = 0; i < BINARY_SEARCH_LOOP_AMOUNT; i++) {
                                        mid = (float) ((low + high) / 2);

                                        if (checkAABBCollision(testRect.left + mid, testRect.top, testRect.right + mid, testRect.bottom, cb.rect)) {
                                            high = mid; // Still colliding, move less
                                        } else {
                                            best = mid;
                                            low = mid;  // Safe so far, move more
                                        }
                                    }

                                    result[0] = 1;
                                    moveVector[0] = best;

                                } else {
                                    double low = moveVector[0], high = 0, best = 0;
                                    float mid;

                                    for (int i = 0; i < BINARY_SEARCH_LOOP_AMOUNT; i++) {
                                        mid = (float) ((low + high) /2);


                                        if (checkAABBCollision(testRect.left + mid, testRect.top, testRect.right + mid, testRect.bottom, cb.rect)) {
                                            low = mid;
                                        } else {
                                            high = mid;
                                            best = mid;
                                        }
                                    }
                                    System.out.println(best + "(" + moveVector[0] + ":" + moveVector[1] + ")");

                                    result[0] = -1;
                                    moveVector[0] = best;
                                }
                            }
                        }
                        break;
                    }
                    case 1: {
                        if (cb.rect.contains((int) (testRect.left + moveVector[0] + testRect.right + moveVector[0]) / 2, (int) (testRect.top + moveVector[1] + testRect.bottom + moveVector[1]) / 2)) {
                            System.out.println("Triggered: " + ec.owner.getClass().getSimpleName());
                            // Access entity via ec.owner
                        }
                        break;
                    }
                }
            }
        }

        return result;
    }

    private boolean checkAABBCollision(float l1, float t1, float r1, float b1, Rect r2) {
        return l1 < r2.right && r1 > r2.left && t1 < r2.bottom && b1 > r2.top;
    }
}