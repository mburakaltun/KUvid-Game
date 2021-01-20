package Domain.Primitives.Movables;

import Domain.Interfaces.IMovable;
import Domain.Interfaces.IMovementPattern;
import Domain.MVC.Message;
import Domain.Primitives.Element;
import Domain.Primitives.Shooter;
import Domain.Primitives.Patterns.StraightMovementPattern;
import Domain.Primitives.Patterns.ZigZagMovementPattern;
import Domain.StatisticsWindow;

import java.util.Random;

public class Molecule extends Element implements IMovable {
    private final int atomCount;
    private IMovementPattern pattern;
    private int patternType;
    Random random;

    public Molecule(long id, int type, float x, float y, int atomCount, String pat) {
        super(id, type, x, y, 0);
        this.atomCount = atomCount;
        random = new Random();
        int movementDirection = random.nextInt(2) * 2 - 1;

        if (type == 2 || type == 3) {
            this.pattern = StraightMovementPattern.getInstance();
            patternType = 0;
        } else {
            if (pat == "ZigZag") {
                this.pattern = new ZigZagMovementPattern(x, movementDirection);
                patternType = 1;
            } else {
                this.pattern = StraightMovementPattern.getInstance();
                patternType = 0;
            }
        }

    }

    public float[] getPosition() {
        return new float[]{super.x, super.y};
    }

    public void step(float stepSize) {
        this.setPosition(pattern.updatePosition(super.x, super.y, stepSize));
    }

    @Override
    public int getType() {
        return super.colorType;
    }

    @Override
    public void modifyStatisticsWindow(StatisticsWindow statisticsWindow, float efficiency) {
        statisticsWindow.increaseAtomCount(this.colorType, atomCount);
        statisticsWindow.increaseScore(efficiency);
    }

    @Override
    public boolean isMolecule() {
        return true;
    }

    @Override
    public boolean isReactionBlocker() {
        return false;
    }

    @Override
    public int removeOperation(StatisticsWindow statisticsWindow, Shooter shooter, float l) {
        return 0;
    }

    public int getAtomCount() {
        return this.atomCount;
    }

    public String toString() {
        return "Molecule " + colorType + " " + atomCount + " " + x + " " + y + " " + patternType;
    }

    @Override
    public Message formatIntoMessage() {
        return new Message(super.id, super.objectType, super.colorType, this.patternType, this.atomCount, super.x, super.y, false);
    }


}
