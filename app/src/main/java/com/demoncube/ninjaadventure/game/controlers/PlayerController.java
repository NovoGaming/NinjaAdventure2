package com.demoncube.ninjaadventure.game.controlers;

import com.demoncube.ninjaadventure.game.helpers.customVariables.Victor;
import com.demoncube.ninjaadventure.game.ui.UIJoystick;

public class PlayerController implements ControllerInterface {

    UIJoystick joystick = null;

    public PlayerController(UIJoystick joystick) {
        this.joystick = joystick;
    }

    @Override
    public Victor getMoveVectors() {
        return joystick.getOutputVector();
    }

    @Override
    public boolean isAttacking() {
        return false;
    }

    @Override
    public boolean isInteracting() {
        return false;
    }

    @Override
    public void feedBack(double[] feedBack) {

    }


}
