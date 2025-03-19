package com.demoncube.ninjaadventure.game.controlers;

import com.demoncube.ninjaadventure.game.helpers.customVariables.vector;

public interface ControllerInterface {

    vector getMoveVectors();
    boolean isAttacking();
    boolean isInteracting();

}
