package com.demoncube.ninjaadventure.game.helpers.customVariables;

import android.graphics.Rect;

public class CollisionBox {
    public Rect rect; // Collision area within a tile (local coordinates)
    public int collisionGroup; // Type of collision

    public CollisionBox(Rect rect, int collisionGroup) {
        this.rect = rect;
        this.collisionGroup = collisionGroup;
    }

    public CollisionBox(CollisionBox collisionBox) {
        this.rect = new Rect(collisionBox.rect);
        this.collisionGroup = collisionBox.collisionGroup;
    }
}