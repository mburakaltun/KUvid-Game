package Domain.SaveLoad;

import Domain.Interfaces.IMovable;
import Domain.Interfaces.IShootable;
import Domain.KuVidGame;
import Domain.Primitives.Shooter;
import Domain.StatisticsWindow;

import java.io.File;
import java.util.List;

public abstract class Saver {
    KuVidGame kuVidGame;
    String filePath;
    protected File file;

    public Saver(KuVidGame kuVidGame) {
        this.kuVidGame = kuVidGame;
        file = new File(kuVidGame.getFilePath());
    }

    public String compileIntoText() {
        StatisticsWindow stats = kuVidGame.getStats();
        Shooter shooter = kuVidGame.getShooter();
        IShootable shootersBullet = shooter.getBullet();
        List<IMovable> movableList = kuVidGame.getGameObjects().getMovableList();
        List<IShootable> shootableList = kuVidGame.getGameObjects().getShootableList();

        String savedGameString = "";
        savedGameString += "Stats";
        savedGameString += "\nTimeLeft " + stats.getTimeLeft();
        savedGameString += "\nHealth " + stats.getHealth();
        savedGameString += "\nScore " + stats.getScore();
        savedGameString += "\nPowerUpCounts " + stats.getPowerUpCountsString();
        savedGameString += "\nAtomCounts " + stats.getAtomCountsString();
        savedGameString += "\nShieldCounts " + stats.getShieldCountsString();
        savedGameString += "\nShooter " + shooter.getShooterString();
        savedGameString += "\nShootersBullet " + shootersBullet.toString();
        savedGameString += "\nGameObjects";

        for (IMovable movable : movableList) {
            savedGameString += "\n" + movable.toString();
        }
        for (IShootable shootable : shootableList) {
            savedGameString += "\n" + shootable.toString();
        }
        savedGameString += "\nend";
        return savedGameString;
    }

    public abstract void save() ;

}
