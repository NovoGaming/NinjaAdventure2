package com.demoncube.ninjaadventure.game.helpers;

import android.graphics.Color;

public class GameConst {

    public static final  class FaceDir{
        public static final int DOWN = 0;
        public static final int UP = 1;
        public static final int LEFT = 2;
        public static final int RIGHT = 3;
    }

    public static final class Sprite{
        public static final int DEFAULT_SIZE = 16;
        public static final int SCALE_MULTIPLIER = 6;
        public static final int SIZE = DEFAULT_SIZE * SCALE_MULTIPLIER;
        public static final int CHUNK_SIZE = 8;
        public static final int CHUNK_GRID_SIZE = 5;
        public static final int ACTIVE_CHUNK_GRID_SIZE = 3;
    }

    public static final class Animation {
        public static int SPEED = 10;
        public static int AMOUNT = 4;

    }

    public static final class CharacterDamageTable {
        public static double LEVEL_MULTIPLIER = 1.2;
        public static int PLAYER = 25;
        public static int SKELETON = 25;
    }

    public static final class WeaponDamageTable {
        public static double LEVEL_MULTIPLIER = 1.2;
        public static int BIG_SWORD = 10;
    }

    public static final class CharacterHealthTable {
        public static int HEALTH_BAR_COLOR = Color.RED;
        public static int HEALTH_BAR_BG_COLOR = Color.BLACK;
        public static int PLAYER = 300;
        public static int SKELETON = 75;
        public static int BAMBOO = 50;
    }
}
