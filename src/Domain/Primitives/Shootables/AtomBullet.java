package Domain.Primitives.Shootables;

import Domain.Interfaces.IMovable;
import Domain.Interfaces.IShootable;
import Domain.MVC.Message;
import Domain.Primitives.Element;

public class AtomBullet extends Element implements IShootable {
    private float shootingAngle;
    private float deltaX;
    private float deltaY;
    private float windowWidth;
    private final int proton;
    private final int neutron;
    private float efficiency;
    private boolean movementLock;

    public AtomBullet(long id, int type, float x, float y, float shootingAngle, float windowWidth, int proton, int neutron) {
        super(id, type, x, y, 3);
        this.shootingAngle = shootingAngle;
        this.windowWidth = windowWidth;
        this.proton = proton;
        this.neutron = neutron;
        updateDeltas();
        calculateEfficiency();
        movementLock = true;
    }

    public AtomBullet(long id, int type, float x, float y, float shootingAngle, float windowWidth, int proton, int neutron, boolean movementLock) {
        super(id, type, x, y, 3);
        this.shootingAngle = shootingAngle;
        this.windowWidth = windowWidth;
        this.proton = proton;
        this.neutron = neutron;
        updateDeltas();
        calculateEfficiency();
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
            super.y = y;
    }

    @Override
    public boolean hitCheck(IMovable i, float hitRadius) {
        // REQUIRES: effective radius > 0
        // EFFECTS: return 0 if euclidean distance is between first argument's position and object's position smaller than 0
        // and types of both of these objects are equal

        boolean retVal = false;
        if (super.colorType == i.getType() && i.isMolecule()) { //If movementLock is true then bullet is still inside shooter

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

    public void step(float stepSize) {
        if(!movementLock)
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
        return "Atom " + colorType + " " + x + " " + y + " " + shootingAngle;
    }

    public int getProton() {
        return proton;
    }


    public int getNeutron() {
        return neutron;
    }

    private void calculateEfficiency() {
        if (colorType == 0) {
            efficiency = (float) ((1 - Math.abs(neutron - proton) / proton) * 0.85);
        } else if (colorType == 1) {
            efficiency = (float) (0.9 - (0.5 * Math.abs(neutron - proton) / proton));
        } else if (colorType == 2) {
            efficiency = (float) (0.8 + (Math.abs(neutron - proton) / (2 * proton)));
        } else if (colorType == 3) {
            efficiency = (float) (0.85 + (Math.abs(neutron - proton) / proton));
        }
    }

    public float getEfficiency() {
        return efficiency;
    }

    @Override
    public void setEfficiency(float efficiency) {
        this.efficiency = efficiency;
    }

    @Override
    public int getProtonCount() {
        return proton;
    }

    @Override
    public int getNeutronCount() {
        return neutron;
    }

    @Override
    public boolean isAtom() {
        return true;
    }
}
