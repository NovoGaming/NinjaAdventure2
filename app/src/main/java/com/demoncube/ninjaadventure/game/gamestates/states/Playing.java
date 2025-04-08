package com.demoncube.ninjaadventure.game.gamestates.states;

import static com.demoncube.ninjaadventure.GameActivity.*;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

import android.graphics.PointF;
import android.view.MotionEvent;

import com.demoncube.ninjaadventure.game.Game;
import com.demoncube.ninjaadventure.game.handlers.CameraHandler;
import com.demoncube.ninjaadventure.game.handlers.CollisionHandler;
import com.demoncube.ninjaadventure.game.controlers.PlayerController;
import com.demoncube.ninjaadventure.game.entities.enums.GameCharacters;
import com.demoncube.ninjaadventure.game.entities.Player;
import com.demoncube.ninjaadventure.game.handlers.cameras.ControllerCamera;
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
    PlayerController playerController;

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

        mainPlayer = new Player(new PointF(SCREEN_CENTER_WIDTH - GameConst.Sprite.SIZE/2f, SCREEN_CENTER_HEIGHT - GameConst.Sprite.SIZE/2f) ,GameCharacters.NINJA_RED, playerController);
        mainPlayer.setCollisionHandler(collisionHandler);

        camera = new FollowEntityCamera(mainPlayer, SCREEN_CENTER_WIDTH - GameConst.Sprite.SIZE/2f, SCREEN_CENTER_HEIGHT - GameConst.Sprite.SIZE/2f);

        mapManager.players.add(mainPlayer);
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
    public void update(double delta) {
        mainPlayer.update(delta, true, 0, 0);
        camera.update(delta);

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
