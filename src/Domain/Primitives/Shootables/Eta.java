package Domain.Primitives.Shootables;

import Domain.Interfaces.IShootable;
import Domain.MVC.Message;

public class Eta extends ShieldDecorator {


    public Eta(IShootable bullet) {
        super(bullet);
    }

    @Override
    public void step(float stepSize) {
        bullet.step((float) (stepSize * 0.95));
    }

    @Override
    public void setEfficiency(float efficiency) {
        float updatedEfficiency;
        int protonCount = bullet.getProtonCount();
        int neutronCount = bullet.getNeutronCount();
        if (protonCount != neutronCount) {
            updatedEfficiency = (1 - efficiency) * Math.abs(neutronCount - protonCount) / protonCount;
        } else {
            updatedEfficiency = (float) ((1 - efficiency) * 0.05);
        }
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
        temp[0] = true;
        return temp;
    }
}
