package Domain.Primitives.Shootables;

import Domain.Interfaces.IMovable;
import Domain.Interfaces.IShootable;

public abstract class ShieldDecorator implements IShootable {
    protected IShootable bullet;
    protected float originalEfficiency;

    public ShieldDecorator(IShootable bullet) {
        this.bullet = bullet;
        originalEfficiency = bullet.getEfficiency();
    }

    @Override
    public void updateAngle(float newAngle) {
        bullet.updateAngle(newAngle);
    }

    @Override
    public void slide(float x) {
        bullet.slide(x);
    }

    @Override
    public void setY(float y) {
        bullet.setY(y);
    }

    @Override
    public float[] getPosition() {
        return bullet.getPosition();
    }

    @Override
    public boolean hitCheck(IMovable i, float hitRadius) {
        return bullet.hitCheck(i, hitRadius);
    }

    @Override
    public String getType() {
        return bullet.getType();
    }

    @Override
    public float getEfficiency() {
        return bullet.getEfficiency();
    }

    public String toString() {
        return bullet.toString();
    }
}
