package Domain.Factories;

public class IdFactory {
    private static IdFactory instance;
    private long idToGive;

    private IdFactory(){
        idToGive = -1;
    }

    public static IdFactory getInstance(){
        if(instance == null){
            instance = new IdFactory();
        }

        return instance;
    }

    public long createId(){
        idToGive++;
        return idToGive;
    }

}
