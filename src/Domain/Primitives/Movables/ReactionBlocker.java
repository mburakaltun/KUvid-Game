package Domain.Primitives.Movables;

import Domain.Interfaces.IMovable;
import Domain.MVC.Message;
import Domain.Primitives.Element;
import Domain.Primitives.Shooter;
import Domain.StatisticsWindow;

public class ReactionBlocker extends Element implements IMovable {

    public ReactionBlocker(long id, int type, float x, float y) {
        super(id, type, x, y, 1);
    }

    public float[] getPosition() {
        return new float[]{super.x, super.y};
    }

    public void step(float stepSize) {
        super.y -= stepSize;
    }

    @Override
    public int getType() {
        return super.colorType;
    }

    @Override
    public void modifyStatisticsWindow(StatisticsWindow statisticsWindow, float efficiency) {

    }

    @Override
    public boolean isMolecule() {
        return false;
    }

    @Override
    public boolean isReactionBlocker() {
        return true;
    }

    @Override
    public int removeOperation(StatisticsWindow statisticsWindow, Shooter shooter, float l) {
        float distance = Math.abs(shooter.getPosition()[0] - x);
        float healthReduction;
        if(distance == 0) {
            healthReduction = 100;
        } else {
            healthReduction = statisticsWindow.getWindowHeight() / (distance);
        }
        statisticsWindow.decreaseHealth((int)healthReduction);
        return 0;
    }

    public String toString() {
        return "ReactionBlocker " + colorType + " " + x + " " + y;
    }

    @Override
    public Message formatIntoMessage() {
        return new Message(super.id, super.objectType, super.colorType, 2, 0, super.x, super.y, false);
    }
}
