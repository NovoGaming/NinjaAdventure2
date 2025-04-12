package com.demoncube.ninjaadventure.game.controlers;

import com.demoncube.ninjaadventure.game.helpers.customVariables.Victor;

import java.util.Random;

public class RandomIAController implements ControllerInterface {

    int updateTime;
    long lastTimerUpdate;

    Victor moveVector;
    int[] lastFeedBack;

    Random randomGenerator;

    public RandomIAController(int updateTime) {
        this.updateTime = updateTime;
        lastTimerUpdate = System.currentTimeMillis();
        moveVector = new Victor(0,0);
        this.randomGenerator = new Random();
        lastFeedBack = new int[] {0, 0};
    }


    @Override
    public Victor getMoveVectors() {
        if (System.currentTimeMillis() >= lastTimerUpdate + updateTime) {
            moveVector.x = (randomGenerator.nextInt(21) - 10)/10f;
            moveVector.y = (randomGenerator.nextInt(21) - 10)/10f;

            lastTimerUpdate = System.currentTimeMillis();

        }
        if (lastFeedBack[0] != 0)
            moveVector.x = (randomGenerator.nextInt(21) - 10)/10f;
        if (lastFeedBack[1] != 0)
            moveVector.y = (randomGenerator.nextInt(21) - 10)/10f;

        return moveVector;
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
    public void feedBack(int[] feedBack) {
        lastFeedBack = feedBack;
    }
}
