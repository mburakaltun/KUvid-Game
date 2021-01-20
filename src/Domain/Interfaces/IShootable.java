package Domain.Interfaces;

import Domain.MVC.Message;

public interface IShootable{
    void updateAngle(float newAngle);
    void slide(float x);
    void setY(float y);
    float[] getPosition();
    void step(float stepSize); //Will be used to step the object through the game.
    boolean hitCheck(IMovable i, float hitRadius); //checks if bullet collides with another movable element
    String getType();
    float getEfficiency();
    void setEfficiency(float efficiency);
    int getProtonCount();
    int getNeutronCount();
    boolean isAtom();
    long getId();
    Message formatIntoMessage();
    void allowMovement();
    int getColorType();
    boolean[] getShieldStatus();
}
