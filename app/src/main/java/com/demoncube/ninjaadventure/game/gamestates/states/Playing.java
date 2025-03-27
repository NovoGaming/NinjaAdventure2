package com.demoncube.ninjaadventure.game.gamestates.states;

import static com.demoncube.ninjaadventure.GameActivity.*;
import static com.demoncube.ninjaadventure.game.helpers.GameConst.Sprite.*;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

import android.graphics.PointF;
import android.view.MotionEvent;

import com.demoncube.ninjaadventure.game.Game;
import com.demoncube.ninjaadventure.game.controlers.PlayerController;
import com.demoncube.ninjaadventure.game.entities.GameCharacters;
import com.demoncube.ninjaadventure.game.entities.Player;
import com.demoncube.ninjaadventure.game.entities.structures.Structure;
import com.demoncube.ninjaadventure.game.entities.structures.StructureSet;
import com.demoncube.ninjaadventure.game.mapManagement.MapManager;
import com.demoncube.ninjaadventure.game.gamestates.BaseState;
import com.demoncube.ninjaadventure.game.gamestates.GameStateInterface;
import com.demoncube.ninjaadventure.game.helpers.GameConst;
import com.demoncube.ninjaadventure.game.ui.UI;
import com.demoncube.ninjaadventure.game.ui.UIJoystick;

import java.util.ArrayList;

public class Playing extends BaseState implements GameStateInterface {

    float cameraX = 0, cameraY = 0;
    ArrayList<UI> ui = new ArrayList<>();
    ArrayList<Structure> structures = new ArrayList<>();

    MapManager mapManager = new MapManager();

    Player mainPlayer;
    PlayerController playerController;

    //------------- DEBUG -------------//
    Paint circlePaint, circleDPaint;

    //-------------- CODE -------------//
    public Playing(Game game) {
        super(game);
        initDebug();

        UIJoystick joystick = new UIJoystick(new PointF( 180, SCREEN_HEIGHT-180),100, circlePaint, circleDPaint);
        ui.add(joystick);
        structures.add(new Structure(new PointF(0,0),StructureSet.VILLAGE, 1));
        structures.add(new Structure(new PointF(5*SIZE,0),StructureSet.VILLAGE, 4));
        structures.add(new Structure(new PointF(10*SIZE,0),StructureSet.VILLAGE, 5));
        structures.add(new Structure(new PointF(15*SIZE,0),StructureSet.VILLAGE, 6));
        structures.add(new Structure(new PointF(0,5*SIZE),StructureSet.VILLAGE, 7));
        structures.add(new Structure(new PointF(5*SIZE,5*SIZE),StructureSet.VILLAGE, 8));
        playerController = new PlayerController(joystick);
        mainPlayer = new Player(new PointF(SCREEN_WIDTH/2f - GameConst.Sprite.SIZE/2f, SCREEN_HEIGHT/2f - GameConst.Sprite.SIZE/2f) ,GameCharacters.NINJA_RED, playerController);
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

    int i = 0;

    @Override
    public void update(double delta) {
        cameraX -= playerController.getMoveVectors().x * mainPlayer.getMovementSpeed() * delta;
        cameraY -= playerController.getMoveVectors().y * mainPlayer.getMovementSpeed() * delta;

        mainPlayer.update(delta, true);

        i++;
        if (i == 120) structures.get(0).addDecor(StructureSet.VILLAGE, 12,1,1);
    }

    @Override
    public void render(Canvas c) {
        mapManager.drawTiles(c, cameraX, cameraY);
        mainPlayer.render(c, cameraX, cameraY);

        for (Structure element: structures) {
            element.render(c, cameraX, cameraY);
        }

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
