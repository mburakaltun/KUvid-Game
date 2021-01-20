package Domain.Primitives.Shootables;

import Domain.Interfaces.IMovable;
import Domain.Interfaces.IShootable;
import Domain.MVC.Message;
import Domain.Primitives.Element;

public class PowerUpBullet extends Element implements IShootable {
    private float shootingAngle;
    private float deltaX;
    private float deltaY;
    private float windowWidth;
    private boolean movementLock;

    public PowerUpBullet(long id, int type, float x, float y, float shootingAngle, float windowWidth) {
        super(id, type, x, y, 4);
        this.shootingAngle = shootingAngle;
        this.windowWidth = windowWidth;
        updateDeltas();
        movementLock = true;
    }

    public PowerUpBullet(long id, int type, float x, float y, float shootingAngle, float windowWidth, boolean movementLock) {
        super(id, type, x, y, 4);
        this.shootingAngle = shootingAngle;
        this.windowWidth = windowWidth;
        updateDeltas();
        this.movementLock = movementLock;
    }

    private void updateDeltas() {
        this.deltaX = (float) Math.cos(shootingAngle * Math.PI / 180.0f);
        this.deltaY = (float) Math.sin(shootingAngle * Math.PI / 180.0f);
    }

    public float[] getPosition() {
        return new float[]{super.x, super.y};
    }

    @Override
    public void updateAngle(float newAngle) {
        if(movementLock) {
            this.shootingAngle = newAngle;
            updateDeltas();
        }
    }

    @Override
    public void slide(float x) {
        if(movementLock)
            super.x = x;
    }

    public void setY(float y) {
        if(movementLock)
            this.y = y;
    }

    @Override
    public boolean hitCheck(IMovable i, float hitRadius) {

        boolean retVal = false;
        if (super.colorType == i.getType() && i.isReactionBlocker()) {

            float[] iPos = i.getPosition();
            float[] thisPos = this.getPosition();

            float distance = (float) Math.sqrt(Math.pow(iPos[0] - thisPos[0], 2) + Math.pow(iPos[1] - thisPos[1], 2));

            if (distance <= hitRadius)
                retVal = true;
        }

        return retVal;
    }

    @Override
    public String getType() {
        switch (colorType) {
            case 0:
                return "Alpha";
            case 1:
                return "Beta";
            case 2:
                return "Gamma";
            case 3:
                return "Sigma";
            default:
                return "";
        }
    }

    @Override
    public float getEfficiency() {
        return 0;
    }

    @Override
    public void setEfficiency(float efficiency) {

    }

    @Override
    public int getProtonCount() {
        return 0;
    }

    @Override
    public int getNeutronCount() {
        return 0;
    }

    @Override
    public boolean isAtom() {
        return false;
    }

    public void step(float stepSize) {
        if(!movementLock) {
            super.x += deltaX * stepSize;
            super.y += deltaY * stepSize;
            if (super.x >= windowWidth) {
                deltaX = -deltaX;
                super.x = windowWidth - 0.1f;
            }
            if (super.x <= 0) {
                deltaX = -deltaX;
                super.x = 0.1f;
            }
        }
    }

    @Override
    public Message formatIntoMessage() {
        return new Message(super.id, super.objectType, super.colorType, 2, 0, super.x, super.y, movementLock);
    }

    @Override
    public void allowMovement() {
        movementLock = false;
    }

    @Override
    public boolean[] getShieldStatus() {
        return new boolean[]{false, false, false, false};
    }

    @Override
    public String toString() {
        return "PowerUpBullet " + colorType + " " + x + " " + y;
    }
}
