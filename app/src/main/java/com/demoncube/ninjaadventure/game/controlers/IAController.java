package com.demoncube.ninjaadventure.game.controlers;

import com.demoncube.ninjaadventure.game.helpers.customVariables.vector;

public class IAController implements ControllerInterface {

    @Override
    public vector getMoveVectors() {
        return null;
    }

    @Override
    public boolean isAttacking() {
        return false;
    }

    @Override
    public boolean isInteracting() {
        return false;
    }
}
