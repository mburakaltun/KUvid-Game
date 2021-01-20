package Domain.Primitives;

import Domain.Interfaces.IShootable;
import org.junit.Test;

public class Shooter {
    private float x;
    private float y;
    private float angle;
    private IShootable bullet;
    private boolean isBulletAvailable = true;

    public Shooter(float x, float y) {
        this.x = x;
        this.y = y;
        this.angle = 90.0f;
    }

    public IShootable getBullet() {
        return this.bullet;
    }

    public float[] getPosition() {
        return new float[]{this.x, this.y};
    }

    public void assignBullet(IShootable newBullet) {
        this.bullet = newBullet;
        bullet.updateAngle(this.angle);
        setBulletCoordinates();
        isBulletAvailable = true;
    }

    public void rotate(float deltaAngle) {
        this.angle += deltaAngle;

        if (this.angle > 150.0f)
            this.angle = 150.0f;
        else if (this.angle < 30.0f)
            this.angle = 30.0f;

        bullet.updateAngle(angle);

        setBulletCoordinates();
    }

    public void move(float deltaX) {
        this.x += deltaX;

        setBulletCoordinates();
    }

    public float getAngle() {
        return angle;
    }

    public void setBulletCoordinates() {
        bullet.setY(110.0f * (float) Math.sin(this.angle * Math.PI / 180.0f));
        bullet.slide(this.x + 110.0f * (float) Math.cos(this.angle * Math.PI / 180.0f));
    }

    public String getShooterString() {
        return x + " " + angle;
    }

    public void setX(float x) {
        this.x = x;
    }

    public void setY(float y) {
        this.y = y;
    }

    public void setAngle(float angle) {
        this.angle = angle;
    }

    public boolean isBulletAvailable() {
        return isBulletAvailable;
    }

    public void setBulletAvailable(boolean bulletAvailable) {
        isBulletAvailable = bulletAvailable;
    }
}
