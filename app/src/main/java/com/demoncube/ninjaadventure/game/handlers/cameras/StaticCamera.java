package com.demoncube.ninjaadventure.game.handlers.cameras;

import com.demoncube.ninjaadventure.game.handlers.CameraHandler;

public class StaticCamera implements CameraHandler {

    float x = 0;
    float y = 0;

    public StaticCamera(float x, float y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public void update(double delta) {

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
