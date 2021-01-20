package Domain.Primitives.Movables;

import Domain.Interfaces.IMovable;
import Domain.MVC.Message;
import Domain.Primitives.Element;
import Domain.Primitives.Shooter;
import Domain.StatisticsWindow;

public class PowerUp extends Element implements IMovable {

    public PowerUp(long id, int type, float x, float y) {
        super(id, type, x, y, 2);
    }

    @Override
    public float[] getPosition() {
        return new float[]{super.x, super.y};
    }

    @Override
    public void step(float stepSize) {
        super.y -= stepSize;
    }

    @Override
    public int getType() {
        return super.colorType;
    }

    @Override
    public void modifyStatisticsWindow(StatisticsWindow statisticsWindow, float efficiency) {
        statisticsWindow.incrementPowerUpCount(this.colorType);
    }

    @Override
    public boolean isMolecule() {
        return false;
    }

    @Override
    public boolean isReactionBlocker() {
        return false;
    }

    @Override
    public int removeOperation(StatisticsWindow statisticsWindow, Shooter shooter, float l) {
        float distance = Math.abs(shooter.getPosition()[0] - this.x);
        if(distance < 30) {
            statisticsWindow.incrementPowerUpCount(colorType);
            return 1;
        } else {
            return 0;
        }
    }

    @Override
    public String toString() {
        return "PowerUp " + colorType + " " + x + " " + y;
    }

    @Override
    public Message formatIntoMessage() {
        return new Message(super.id, super.objectType, super.colorType, 2, 0, super.x, super.y, false);
    }
}
