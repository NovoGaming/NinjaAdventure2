package com.demoncube.ninjaadventure.game;

import android.graphics.Color;
import android.graphics.Paint;

public class GameSettings {

    public static class baseSettings {
        public static int FRAME_RATE = 60;
    }

    public static final class debug {
        //-------------------- Enables --------------------
        public static boolean SHOW_FPS = true;                                                      //Show FPS on screan
        public static boolean CALL_FPS = false;                                                      //Writes fps int LogCat as System.out
        public static boolean DRAW_UI_DEBUG = false;
        public static boolean DRAW_ENTITY_HITBOX = true;
        public static boolean DRAW_DEAD_ENTITY_HITBOX = true;
        public static boolean DRAW_TRIGGER_HITBOX = true;

        //-------------------- Styling --------------------
        public static int FPS_COLOR = Color.RED;
        public static int UI_COLOR = Color.YELLOW;

        public static int HITBOX_STROKE_WIDTH = 4;
        public static Paint.Style HITBOX_PAINT_STYLE = Paint.Style.STROKE;
        public static int PLAYER_HITBOX_COLOR = Color.GREEN;
        public static int WEAPON_HITBOX_COLOR = Color.BLUE;
        public static int ENEMY_HITBOX_COLOR = Color.RED;
        public static int DEAD_HITBOX_COLOR = Color.DKGRAY;
        public static int DROPPED_ITEM_HITBOX_COLOR = Color.CYAN;
        public static int TRIGGER_HITBOX_COLOR = Color.YELLOW;
        public static int OTHER_ENTITY_HITBOX_COLOR = Color.MAGENTA;
    }

}
