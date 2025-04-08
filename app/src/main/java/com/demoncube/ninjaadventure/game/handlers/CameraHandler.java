package com.demoncube.ninjaadventure.game.handlers;

public interface CameraHandler {

    void update (double delta);

    void setX(float x);
    void setY(float y);

    float getX();
    float getY();
}
