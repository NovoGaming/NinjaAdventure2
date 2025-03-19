package com.demoncube.ninjaadventure.game.helpers;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

public interface BitmapMethods {

    BitmapFactory.Options options = new BitmapFactory.Options();

    default Bitmap getScaledBitmap(Bitmap bitmap) {
        return Bitmap.createScaledBitmap(bitmap,bitmap.getWidth() * GameConst.Sprite.SCALE_MULTIPLIER, bitmap.getHeight()* GameConst.Sprite.SCALE_MULTIPLIER, false);
    }

}
