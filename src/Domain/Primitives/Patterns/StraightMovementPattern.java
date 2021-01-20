package Domain.Primitives.Patterns;

import Domain.Interfaces.IMovementPattern;

public class StraightMovementPattern implements IMovementPattern {
    private static StraightMovementPattern instance;
    private StraightMovementPattern(){}

    public synchronized static StraightMovementPattern getInstance(){
        if(instance == null)
            instance = new StraightMovementPattern();

        return instance;
    }

    public synchronized float[] updatePosition(float x, float y, float stepSize) {
        return new float[]{x, y-stepSize};
    }

    public String patternName() {
        return "Straight";
    }
}
