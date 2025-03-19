package com.demoncube.ninjaadventure.game.entities;

import static com.demoncube.ninjaadventure.game.helpers.GameConst.Sprite.HITBOX_SIZE;

import android.graphics.PointF;

import com.demoncube.ninjaadventure.game.controlers.ControllerInterface;
import com.demoncube.ninjaadventure.game.helpers.GameConst;

public abstract class Character extends Entity {

    protected int aniTick, aniIndex;
    protected int faceDir = GameConst.FaceDir.DOWN;
    protected boolean isMoving = false;
    protected GameCharacters gameCharType;
    protected ControllerInterface controller;

    public Character(PointF pos, GameCharacters gameCharType) {
        super(pos, GameConst.Sprite.SIZE, GameConst.Sprite.SIZE);
        this.gameCharType = gameCharType;
        this.controller = null;
    }

    public Character(PointF pos, GameCharacters gameCharType, ControllerInterface controller) {
        super(pos, GameConst.Sprite.SIZE, GameConst.Sprite.SIZE);
        this.gameCharType = gameCharType;
        this.controller = controller;
    }

    protected void updateAnimation() {
        if (isMoving) moveAnimation();
    }

    private void moveAnimation(){
        aniTick++;
        if (aniTick >= GameConst.Animation.SPEED) {
            aniTick = 0;
            aniIndex++;
            if (aniIndex >= GameConst.Animation.AMOUNT) {
                aniIndex = 0;
            }
        }
    }

    public void resetAnimation() {
        aniTick = 0;
        aniIndex = 0;
    }

    public int getAniIndex() {
        return aniIndex;
    }

    public int getFaceDir() {
        return faceDir;
    }

    public void setFaceDir(int faceDir) {
        this.faceDir = faceDir;
    }

    public GameCharacters getGameCharType() {
        return gameCharType;
    }
}


