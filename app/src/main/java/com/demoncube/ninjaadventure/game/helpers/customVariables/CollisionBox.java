package com.demoncube.ninjaadventure.game.helpers.customVariables;

import android.graphics.Rect;

public class CollisionBox {
    public Rect rect; // Collision area within a tile (local coordinates)
    public int collisionGroup; // Type of collision
    public int id = 0;
    public boolean isActive = true;

    public CollisionBox(Rect rect, int collisionGroup) {
        this.rect = rect;
        this.collisionGroup = collisionGroup;
    }

    public CollisionBox(Rect rect, int collisionGroup, boolean isActive) {
        this.rect = rect;
        this.collisionGroup = collisionGroup;
        this.isActive = isActive;
    }

    public CollisionBox(Rect rect, int collisionGroup, int id) {
        this.rect = rect;
        this.collisionGroup = collisionGroup;
        this.id = id;
    }

    public CollisionBox(Rect rect, int collisionGroup, int id, boolean isActive) {
        this.rect = rect;
        this.collisionGroup = collisionGroup;
        this.id = id;
        this.isActive = isActive;
    }

    public CollisionBox(CollisionBox collisionBox) {
        this.rect = new Rect(collisionBox.rect);
        this.collisionGroup = collisionBox.collisionGroup;
        this.id = collisionBox.id;
        this.isActive = collisionBox.isActive;
    }
}