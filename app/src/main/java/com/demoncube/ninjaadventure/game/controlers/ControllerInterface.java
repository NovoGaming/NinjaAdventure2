package com.demoncube.ninjaadventure.game.controlers;

import com.demoncube.ninjaadventure.game.helpers.customVariables.Victor;

public interface ControllerInterface {

    Victor getMoveVectors();
    boolean isAttacking();
    boolean isInteracting();

}
