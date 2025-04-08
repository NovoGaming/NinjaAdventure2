package com.demoncube.ninjaadventure.game.controlers;

import com.demoncube.ninjaadventure.game.helpers.customVariables.Victor;

public class IAController implements ControllerInterface {

    @Override
    public Victor getMoveVectors() {
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
