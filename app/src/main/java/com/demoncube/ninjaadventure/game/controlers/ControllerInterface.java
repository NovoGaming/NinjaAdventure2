package com.demoncube.ninjaadventure.game.controlers;

import com.demoncube.ninjaadventure.game.entities.Entity;
import com.demoncube.ninjaadventure.game.helpers.customVariables.Victor;

public interface ControllerInterface {

    Victor getMoveVectors(float ownerPosX, float ownerPosY);
    boolean isAttacking();
    boolean isInteracting();
    void feedBack( double[] feedBack);

}
