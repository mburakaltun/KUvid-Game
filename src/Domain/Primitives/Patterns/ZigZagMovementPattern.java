package Domain.Primitives.Patterns;

import Domain.Interfaces.IMovementPattern;

public class ZigZagMovementPattern implements IMovementPattern {

    float leftBound;
    float rightBound;
    int movementDirection;

    public ZigZagMovementPattern(float moleculeX, int movementDirection) {
        leftBound = moleculeX - 50;
        rightBound = moleculeX + 50;
        this.movementDirection = movementDirection;
    }


    public synchronized float[] updatePosition(float x, float y, float stepSize) {
        if (x >= rightBound || x <= leftBound) {
            movementDirection *= -1;
        }
        return new float[]{x + stepSize * movementDirection, y - stepSize};
    }

    public String patternName() {
        return "ZigZag";
    }
}
