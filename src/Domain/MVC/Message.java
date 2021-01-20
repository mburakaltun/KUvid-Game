package Domain.MVC;

public class Message {
    public final long id;
    public final int objectType;
    public final int colorType;
    public final int patternType;
    public final int atomCount;
    public float x;
    public float y;
    public boolean updatedFlag;
    public boolean isShootersAtom;

    public Message(long id, int objectType, int colorType, int patternType, int atomCount, float x, float y, boolean isShootersAtom) {
        this.id = id;
        this.objectType = objectType;
        this.colorType = colorType;
        this.patternType = patternType;
        this.atomCount = atomCount;
        this.x = x;
        this.y = y;
        updatedFlag = true;
        this.isShootersAtom = isShootersAtom;
    }

    public void updateMessage(float[] newPos) {
        this.x = newPos[0];
        this.y = newPos[1];
        this.updatedFlag = true;
    }

    public boolean isUpdated() {
        return this.updatedFlag;
    }

    public void resetFlag() {
        this.updatedFlag = false;
    }
}
