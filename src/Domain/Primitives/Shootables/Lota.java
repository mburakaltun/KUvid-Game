package Domain.Primitives.Shootables;

import Domain.Interfaces.IShootable;
import Domain.MVC.Message;

public class Lota extends ShieldDecorator {
    public Lota(IShootable bullet) {
        super(bullet);
    }

    @Override
    public void step(float stepSize) {
        bullet.step((float) (stepSize * 0.93));
    }

    @Override
    public void setEfficiency(float efficiency) {
        float updatedEfficiency = (float) ((1 - efficiency) * 0.1);
        bullet.setEfficiency((1 + updatedEfficiency) * efficiency);
    }

    @Override
    public int getProtonCount() {
        return bullet.getProtonCount();
    }

    @Override
    public int getNeutronCount() {
        return bullet.getNeutronCount();
    }

    @Override
    public boolean isAtom() {
        return true;
    }

    @Override
    public long getId() {
        return bullet.getId();
    }

    @Override
    public Message formatIntoMessage() {
        return bullet.formatIntoMessage();
    }

    @Override
    public void allowMovement() {
        bullet.allowMovement();
    }

    @Override
    public int getColorType() {
        return bullet.getColorType();
    }

    @Override
    public boolean[] getShieldStatus() {
        boolean[] temp = bullet.getShieldStatus();
        temp[2] = true;
        return temp;
    }
}
