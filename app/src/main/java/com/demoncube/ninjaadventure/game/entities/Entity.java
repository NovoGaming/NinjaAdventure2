package com.demoncube.ninjaadventure.game.entities;

import android.graphics.PointF;
import android.graphics.RectF;

public abstract class Entity implements Comparable<Entity> {

    private float lastCamYValue = 0;

    protected RectF hitbox;
    protected boolean active = true;

    public Entity(PointF pos, float width, float height) {
        this.hitbox = new RectF(pos.x, pos.y, pos.x + width, pos.y + height);
    }

    public RectF getHitbox() {
        return hitbox;
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
        return new PointF(hitbox.left, hitbox.top);
    }

    @Override
    public int compareTo(Entity other) {
        return Float.compare(hitbox.top - lastCamYValue, other.hitbox.top - other.lastCamYValue);
    }
}
