package com.demoncube.ninjaadventure.game.helpers.customVariables;

import android.graphics.Bitmap;

public class Drawable {

    public Bitmap bitmap;
    public CollisionBox[] collisions;

    public Drawable(Bitmap bitmap, CollisionBox[] collisions) {
        this.bitmap = bitmap;
        this.collisions = this.collisions;
    }

    public Drawable(){}
}
