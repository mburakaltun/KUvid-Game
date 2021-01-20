package Domain.Primitives.Shootables;

import Domain.Interfaces.IShootable;
import Domain.MVC.Message;
import java.util.Random;

public class Theta extends ShieldDecorator {
    Random random = new Random();

    public Theta(IShootable bullet) {
        super(bullet);
    }

    @Override
    public void step(float stepSize) {
        bullet.step((float) (stepSize * 0.91));
    }

    @Override
    public void setEfficiency(float efficiency) {
        float thetaEfficiencyBoost = (random.nextInt(11) + 5) / 100;
        float updatedEfficiency = (float) ((1 - efficiency) * thetaEfficiencyBoost);
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
        temp[1] = true;
        return temp;
    }
}
