package com.demoncube.ninjaadventure.game.gamestates.states;

import static com.demoncube.ninjaadventure.GameActivity.*;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

import android.graphics.PointF;
import android.view.MotionEvent;

import com.demoncube.ninjaadventure.game.Game;
import com.demoncube.ninjaadventure.game.controlers.ControllerInterface;
import com.demoncube.ninjaadventure.game.controlers.EnemyAIController;
import com.demoncube.ninjaadventure.game.controlers.RandomIAController;
import com.demoncube.ninjaadventure.game.handlers.CameraHandler;
import com.demoncube.ninjaadventure.game.handlers.CollisionHandler;
import com.demoncube.ninjaadventure.game.controlers.PlayerController;
import com.demoncube.ninjaadventure.game.entities.characterEnums.GameCharacters;
import com.demoncube.ninjaadventure.game.entities.Player;
import com.demoncube.ninjaadventure.game.handlers.cameras.FollowEntityCamera;
import com.demoncube.ninjaadventure.game.mapManagement.MapManager;
import com.demoncube.ninjaadventure.game.gamestates.BaseState;
import com.demoncube.ninjaadventure.game.gamestates.GameStateInterface;
import com.demoncube.ninjaadventure.game.helpers.GameConst;
import com.demoncube.ninjaadventure.game.ui.UI;
import com.demoncube.ninjaadventure.game.ui.UIJoystick;

import java.util.ArrayList;

public class Playing extends BaseState implements GameStateInterface {

    CameraHandler camera;
    float cameraX = 0, cameraY = 0;
    ArrayList<UI> ui = new ArrayList<>();
    MapManager mapManager;
    CollisionHandler collisionHandler;

    Player mainPlayer;
    ControllerInterface playerController;
    EnemyAIController controller;

    //------------- DEBUG -------------//
    Paint circlePaint, circleDPaint;

    //-------------- CODE -------------//
    public Playing(Game game) {
        super(game);
        initDebug();

        mapManager = new MapManager(0);
        collisionHandler = new CollisionHandler(mapManager);

        UIJoystick joystick = new UIJoystick(new PointF( 180, SCREEN_HEIGHT-180),100, circlePaint, circleDPaint);
        ui.add(joystick);

        playerController = new PlayerController(joystick);
        controller = new EnemyAIController();
        controller.setMoveToPos(new PointF(10,10));

        mainPlayer = new Player(new PointF(1000,1000) ,GameCharacters.NINJA_RED, controller);
        mainPlayer.setCollisionHandler(collisionHandler);

        camera = new FollowEntityCamera(mainPlayer, SCREEN_CENTER_WIDTH - GameConst.Sprite.SIZE/2f, SCREEN_CENTER_HEIGHT - GameConst.Sprite.SIZE/2f);

        mapManager.players.add(mainPlayer);

        mapManager.setCollisionHandlers(collisionHandler);
        mapManager.update(0, cameraX, cameraY, mainPlayer, true);
    }

    private void initDebug() {
        circlePaint = new Paint();
        circlePaint.setStyle(Paint.Style.STROKE);
        circlePaint.setStrokeWidth(5);
        circlePaint.setColor(Color.GRAY);

        circleDPaint = new Paint();
        circleDPaint.setStyle(Paint.Style.FILL_AND_STROKE);
        circleDPaint.setStrokeWidth(5);
        circleDPaint.setColor(Color.DKGRAY);
    }

    @Override
    public void update(double delta, int frame) {
        collisionHandler.update();

        mainPlayer.update(delta, 0, 0);
        camera.update(delta);

        if (controller.getMoveToPos() == null) mainPlayer.setController(playerController);

        mapManager.update(delta, camera.getX(), camera.getY(), mainPlayer);

    }

    @Override
    public void render(Canvas c) {
        mapManager.render(c, camera.getX(), camera.getY());

        for (UI element: ui) {
            element.render(c);
        }
    }

    @Override
    public void touchEvents(MotionEvent event) {
        for (UI element: ui) {
            element.touchEvent(event);
        }
    }
}
