package com.demoncube.ninjaadventure.game.gamestates.states;

import static com.demoncube.ninjaadventure.GameActivity.*;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PointF;
import android.view.MotionEvent;

import com.demoncube.ninjaadventure.game.Game;
import com.demoncube.ninjaadventure.game.controlers.PlayerController;
import com.demoncube.ninjaadventure.game.entities.GameCharacters;
import com.demoncube.ninjaadventure.game.entities.Player;
import com.demoncube.ninjaadventure.game.gamestates.BaseState;
import com.demoncube.ninjaadventure.game.gamestates.GameStateInterface;
import com.demoncube.ninjaadventure.game.ui.UI;
import com.demoncube.ninjaadventure.game.ui.UIJoystick;

import java.util.ArrayList;

public class Playing extends BaseState implements GameStateInterface {

    PointF camera = new PointF(0,0);
    ArrayList<UI> ui = new ArrayList<>();

    Player player;
    PlayerController playerController;
    public Playing(Game game) {
        super(game);
        Paint circlePaint = new Paint();
        circlePaint.setStyle(Paint.Style.STROKE);
        circlePaint.setStrokeWidth(5);
        circlePaint.setColor(Color.GRAY);
        Paint circleDPaint = new Paint();
        circleDPaint.setStyle(Paint.Style.FILL_AND_STROKE);
        circleDPaint.setStrokeWidth(5);
        circleDPaint.setColor(Color.DKGRAY);
        UIJoystick joystick = new UIJoystick(new PointF( 180, SCREEN_HEIGHT-180),100, circlePaint, circleDPaint);
        ui.add(joystick);
        playerController = new PlayerController(joystick);
        player = new Player(GameCharacters.NINJA_RED, playerController);
    }

    @Override
    public void update(double delta) {
        camera.x -= playerController.getMoveVectors().x;
        camera.y -= playerController.getMoveVectors().y;
        player.update(delta, true);
    }

    @Override
    public void render(Canvas c) {
        player.render(c);
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
