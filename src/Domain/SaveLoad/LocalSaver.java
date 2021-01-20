package Domain.SaveLoad;

import Domain.KuVidGame;

import java.io.FileNotFoundException;
import java.io.PrintWriter;

public class LocalSaver extends Saver {

    public LocalSaver(KuVidGame kuVidGame) {
        super(kuVidGame);
    }

    public void save() {
        try {
            String savedGameString = super.compileIntoText();
            PrintWriter myWriter = new PrintWriter(file.getAbsolutePath());
            myWriter.write(savedGameString);
            myWriter.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

    }
}
