package Domain.Primitives.Shootables;

import Domain.Interfaces.IShootable;
import Domain.MVC.Message;

public class Zeta extends ShieldDecorator {
    public Zeta(IShootable bullet) {
        super(bullet);
    }

    @Override
    public void step(float stepSize) {
        bullet.step((float) (stepSize * 0.89));
    }

    @Override
    public void setEfficiency(float efficiency) {
        if (bullet.getProtonCount() == bullet.getNeutronCount()) {
            float updatedEfficiency = (float) ((1 - efficiency) * 0.2);
            bullet.setEfficiency((1 + updatedEfficiency) * efficiency);
        }
        bullet.setEfficiency(efficiency);
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
        temp[3] = true;
        return temp;
    }
}
