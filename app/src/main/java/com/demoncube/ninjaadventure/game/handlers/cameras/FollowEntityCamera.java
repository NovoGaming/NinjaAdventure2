package com.demoncube.ninjaadventure.game.handlers.cameras;

import com.demoncube.ninjaadventure.game.entities.Entity;
import com.demoncube.ninjaadventure.game.handlers.CameraHandler;

public class FollowEntityCamera implements CameraHandler {

    float x = 0, offsetX = 0;
    float y = 0, offsetY = 0;

    Entity entityToFollow;

    public FollowEntityCamera(Entity entityToFollow, float offsetX, float offsetY) {
        this.entityToFollow = entityToFollow;
        this.offsetX = offsetX;
        this.offsetY = offsetY;
    }

    @Override
    public void update(double delta) {
        x = -entityToFollow.getPosition().x + offsetX;
        y = -entityToFollow.getPosition().y + offsetY;
    }

    @Override
    public void setX(float x) {
        this.x = x;
    }

    @Override
    public void setY(float y) {
        this.y = y;
    }

    @Override
    public float getX() {
        return x;
    }

    @Override
    public float getY() {
        return y;
    }
}