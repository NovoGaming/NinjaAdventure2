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

    public Entity(PointF pos, float width, float height, CollisionBox[] collisions) {
        this.box = new RectF(pos.x, pos.y, pos.x + width, pos.y + height);
        this.collisions = collisions;
        if (collisions == null) return;
        for (CollisionBox collision: this.collisions) {
            collision.rect.top *= GameConst.Sprite.SCALE_MULTIPLIER;
            collision.rect.left *= GameConst.Sprite.SCALE_MULTIPLIER;
            collision.rect.bottom *= GameConst.Sprite.SCALE_MULTIPLIER;
            collision.rect.right *= GameConst.Sprite.SCALE_MULTIPLIER;
            System.out.println(collision.rect.top + ":" + collision.rect.left + ";" + collision.rect.bottom + ":" + collision.rect.right);
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
