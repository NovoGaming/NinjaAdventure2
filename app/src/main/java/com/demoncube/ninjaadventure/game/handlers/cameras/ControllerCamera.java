package com.demoncube.ninjaadventure.game.handlers.cameras;

import com.demoncube.ninjaadventure.game.controlers.ControllerInterface;
import com.demoncube.ninjaadventure.game.handlers.CameraHandler;

public class ControllerCamera implements CameraHandler {

    public float x = 0;
    public float y = 0;
    public  int speed = 1;
    private ControllerInterface controller;

    public ControllerCamera(ControllerInterface controller, int moveSpeed) {
        this.controller = controller;
        this.speed = moveSpeed;
    }

    @Override
    public void update(double delta) {
        x -= controller.getMoveVectors(0,0).x * delta * speed;
        y -= controller.getMoveVectors(0,0).y * delta * speed;
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