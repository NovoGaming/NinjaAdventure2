package com.demoncube.ninjaadventure.game;

import android.graphics.Color;
import android.graphics.Paint;

public class GameSettings {

    public static class baseSettings {
        public static int FRAME_RATE = 30;
        //public static int TILE_CACHE_SIZE = 400;
        public static int TILE_CACHE_SIZE = 4000;
    }

    public static final class debug {
        //-------------------- Enables --------------------
        public static boolean SHOW_FPS = true;                                                      //Show FPS on screan
        public static boolean CALL_FPS = false;                                                     //Writes fps int LogCat as System.out
        public static boolean DRAW_UI_DEBUG = false;
        public static boolean DRAW_MAP_CHUNKS = true;
        public static boolean DRAW_MAP_COLLISIONS = true;
        public static boolean DRAW_ENTITY_BOX = false;
        public static boolean DRAW_DEAD_ENTITY_BOX = true;
        public static boolean DRAW_TRIGGER_AREA = true;
        public static boolean DRAW_COLLISION_BOX = true;

        //-------------------- Styling --------------------
        public static int FPS_COLOR = Color.DKGRAY;
        public static int UI_COLOR = Color.YELLOW;

        public static int BOX_STROKE_WIDTH = 4;
        public static Paint.Style BOX_PAINT_STYLE = Paint.Style.STROKE;
        public static int COLLISION_BOX_COLOR = Color.WHITE;
        public static int PLAYER_BOX_COLOR = Color.GREEN;
        public static int WEAPON_BOX_COLOR = Color.BLUE;
        public static int ENEMY_BOX_COLOR = Color.RED;
        public static int DEAD_BOX_COLOR = Color.DKGRAY;
        public static int DROPPED_ITEM_BOX_COLOR = Color.CYAN;
        public static int TRIGGER_AREA_COLOR = Color.YELLOW;
        public static int OTHER_ENTITY_BOX_COLOR = Color.MAGENTA;
    }

}
