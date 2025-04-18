package com.demoncube.ninjaadventure.game.entities;

import android.graphics.PointF;

import com.demoncube.ninjaadventure.game.handlers.CollisionHandler;
import com.demoncube.ninjaadventure.game.controlers.ControllerInterface;
import com.demoncube.ninjaadventure.game.entities.characterEnums.GameCharacters;
import com.demoncube.ninjaadventure.game.helpers.GameConst;
import com.demoncube.ninjaadventure.game.helpers.customVariables.CollisionBox;

public abstract class Character extends Entity {

    protected int aniTick, aniIndex;
    protected int faceDir = GameConst.FaceDir.DOWN;
    protected boolean isMoving = false;
    protected int movementSpeed = 0;
    protected GameCharacters gameCharType;
    protected ControllerInterface controller;

    protected CollisionHandler collisionHandler;

    public Character(PointF pos, GameCharacters gameCharType, CollisionBox[] collisions) {
        super(pos, GameConst.Sprite.SIZE, GameConst.Sprite.SIZE, collisions);
        this.gameCharType = gameCharType;
        this.controller = null;
    }

    public Character(PointF pos, GameCharacters gameCharType, CollisionBox[] collisions, ControllerInterface controller) {
        super(pos, GameConst.Sprite.SIZE, GameConst.Sprite.SIZE, collisions);
        this.gameCharType = gameCharType;
        this.controller = controller;
    }

    protected void updateAnimation() {
        if (isMoving) moveAnimation();
    }
    private void moveAnimation(){
        aniTick++;
        if (aniTick >= GameConst.Animation.CHARACTER_SPEED) {
            aniTick = 0;
            aniIndex++;
            if (aniIndex >= GameConst.Animation.CHARACTER_FRAMES_AMOUNT) {
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

    public int getMovementSpeed() {
        return movementSpeed;
    }
    public void setMovementSpeed(int movementSpeed) {
        this.movementSpeed = movementSpeed;
    }

    public GameCharacters getGameCharType() {
        return gameCharType;
    }
    public void setGameCharType(GameCharacters charType){
        this.gameCharType = charType;
    }

    public ControllerInterface getController() {
        return controller;
    }
    public void setController(ControllerInterface controller) {
        this.controller = controller;
    }

    public CollisionHandler getCollisionHandler() {
        return collisionHandler;
    }
    public void setCollisionHandler(CollisionHandler collisionHandler) {
        this.collisionHandler = collisionHandler;
    }
}


