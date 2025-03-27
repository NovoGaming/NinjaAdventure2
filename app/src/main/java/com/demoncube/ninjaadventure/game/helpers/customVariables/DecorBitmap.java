package com.demoncube.ninjaadventure.game.helpers.customVariables;

import android.graphics.Bitmap;

public class DecorBitmap {
    public Bitmap bitmap;
    public int posX, posY;

    public DecorBitmap(Bitmap bitmap, int posX, int posY) {
        this.bitmap = bitmap;
        this.posX = posX;
        this.posY = posY;
    }

    public DecorBitmap() {
    }
}
