package Domain.Interfaces;

public interface IMovementPattern {
    float[] updatePosition(float x, float y, float stepSize);
    String patternName();
}
