package Domain.SaveLoad;

import Domain.KuVidGame;

import java.io.FileNotFoundException;

public class GameSaver {
    private Saver saver;

    public GameSaver(KuVidGame kuVidGame) {
        saver = new LocalSaver(kuVidGame);
    }

    public void save()  {
        saver.save();
    }
}

