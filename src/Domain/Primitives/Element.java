package Domain.Primitives;

import Domain.MVC.Message;

public abstract class Element {
    protected float x;
    protected float y;
    protected final int colorType;
    protected final long id;
    protected final int objectType;

    protected Element(long id, int colorType, float x, float y, int objectType){
        this.id = id;
        this.colorType = colorType;
        this.x = x;
        this.y = y;
        this.objectType = objectType;
    }

    protected void setPosition(float[] newPosition){
        this.x = newPosition[0];
        this.y = newPosition[1];
    }

    public long getId(){
        return this.id;
    }

    public float[] getPosition(){
        return new float[]{this.x, this.y};
    }

    public abstract Message formatIntoMessage();

    @Override
    public abstract String toString();

    public int getColorType() {
        return colorType;
    }

    public int getObjectType() {
        return objectType;
    }
}
