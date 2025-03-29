package com.demoncube.ninjaadventure.game.entities;

import android.graphics.Canvas;
import android.graphics.PointF;
import android.graphics.RectF;

import com.demoncube.ninjaadventure.game.helpers.GameConst;
import com.demoncube.ninjaadventure.game.helpers.customVariables.CollisionBox;

public abstract class Entity implements Comparable<Entity> {

    private float lastCamYValue = 0;
    private boolean lockCompare = false;

    protected RectF box;
    protected CollisionBox[] collisions;
    protected boolean active = true;

    static int test = 0;

    public Entity(PointF pos, float width, float height, CollisionBox[] collisions) {
        test++;
        this.box = new RectF(pos.x, pos.y, pos.x + width, pos.y + height);

        this.collisions = collisions;
        if (this.collisions == null) return;
        for (int i = 0; i < this.collisions.length; i++) {
            this.collisions[i] = new CollisionBox(this.collisions[i]);
            this.collisions[i].rect.top *= GameConst.Sprite.SCALE_MULTIPLIER;
            this.collisions[i].rect.left *= GameConst.Sprite.SCALE_MULTIPLIER;
            this.collisions[i].rect.bottom *= GameConst.Sprite.SCALE_MULTIPLIER;
            this.collisions[i].rect.right *= GameConst.Sprite.SCALE_MULTIPLIER;
        }
    }

    public void render(Canvas c, float cameraX, float cameraY) {}
    public void update(double delta, float cameraX, float cameraY) {

    }
    public RectF getBox() {
        return box;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public void setLastCamYValue(float lastCamYValue) {
        this.lastCamYValue = lastCamYValue;
    }

    public PointF getPosition() {
        return new PointF(box.left, box.top);
    }

    @Override
    public int compareTo(Entity other) {
        if (other.lockCompare) return -1;
        return Float.compare(box.bottom - lastCamYValue, other.box.bottom - other.lastCamYValue);
    }
}
