package Domain.SaveLoad;

import Domain.GameObjects;
import Domain.Factories.GameObjectFactory;
import Domain.Interfaces.IMovable;
import Domain.Interfaces.IShootable;
import Domain.KuVidGame;
import Domain.Primitives.Shooter;
import Domain.StatisticsWindow;

import java.io.*;
import java.util.LinkedList;
import java.util.List;

public class Loader {
    private BufferedReader bufferedReader;
    private String filePath;
    private StatisticsWindow statisticsWindow;
    private Shooter shooter;
    private GameObjects gameObjects;
    private GameObjectFactory gameObjectFactory;
    private KuVidGame kuVidGame;
    private List<IMovable> movableList;
    private List<IShootable> shootableList;

    public Loader(KuVidGame kuVidGame) {
        filePath = kuVidGame.getFilePath();
        statisticsWindow = kuVidGame.getStatisticsWindow();
        shooter = kuVidGame.getShooter();
        gameObjects = kuVidGame.getGameObjects();
        gameObjectFactory = GameObjectFactory.getInstance();
        movableList = new LinkedList<>();
        shootableList = new LinkedList<>();
        this.kuVidGame = kuVidGame;
    }

    public void load() throws IOException {
        File file = new File(filePath);
        FileReader fileReader = new FileReader(file.getAbsolutePath());
        bufferedReader = new BufferedReader(fileReader);
        String line = bufferedReader.readLine();
        int state = 0;

        while (line != null && !line.equals("end")) {
            if (line.equals("Stats")) {
                state = 1;
            } else if (line.equals("GameObjects")) {
                state = 2;
            }

            if(state == 1) {
                decodeLineIntoStats(line);
            } else if (state == 2) {
                decodeLineIntoGameObjects(line);
            }
            line = bufferedReader.readLine();
        }
        gameObjects.setMovableList(movableList);
        gameObjects.setBulletList(shootableList);
        movableList = new LinkedList<>();
        shootableList = new LinkedList<>();
    }

    public void decodeLineIntoStats(String line) {
        // MODIFIES: loader.KuVidGame.stats
        String[] lineElements = line.split(" ");
        switch (lineElements[0]) {
            case "TimeLeft":
                statisticsWindow.setTimeLeft(Integer.parseInt(lineElements[1]));
                break;
            case "Health":
                statisticsWindow.setHealth(Integer.parseInt(lineElements[1]));
                break;
            case "Score":
                statisticsWindow.setScore(Float.parseFloat(lineElements[1]));
                break;
            case "AtomCounts":
                int alphaCount = Integer.parseInt(lineElements[1]);
                int betaCount = Integer.parseInt(lineElements[2]);
                int gammaCount = Integer.parseInt(lineElements[3]);
                int sigmaCount = Integer.parseInt(lineElements[4]);
                int[] atomCounts = new int[]{alphaCount, betaCount, gammaCount, sigmaCount};
                statisticsWindow.setAtomCounts(atomCounts);
                break;
            case "PowerUpCounts":
                int alphaPowerUpCount = Integer.parseInt(lineElements[1]);
                int betaPowerUpCount = Integer.parseInt(lineElements[2]);
                int gammaPowerUpCount = Integer.parseInt(lineElements[3]);
                int sigmaPowerUpCount = Integer.parseInt(lineElements[4]);
                int[] powerUpCounts = new int[]{alphaPowerUpCount, betaPowerUpCount, gammaPowerUpCount, sigmaPowerUpCount};
                statisticsWindow.setPowerUpCounts(powerUpCounts);
                break;
            case "ShieldCounts":
                int etaCount = Integer.parseInt(lineElements[1]);
                int thetaCount = Integer.parseInt(lineElements[2]);
                int lotaCount = Integer.parseInt(lineElements[3]);
                int zetaCount = Integer.parseInt(lineElements[4]);
                int[] shieldCounts = new int[]{etaCount, thetaCount, lotaCount, zetaCount};
                statisticsWindow.setShieldCounts(shieldCounts);
                break;
            case "Shooter":
                float x_position = Float.parseFloat(lineElements[1]);
                float angle = Float.parseFloat(lineElements[2]);
                shooter.setX(x_position);
                shooter.setAngle(angle);
                break;
            case "ShootersBullet":
                int type = Integer.parseInt(lineElements[2]);
                IShootable bullet;
                switch (lineElements[1]) {
                    case "Atom":
                        bullet = gameObjectFactory.createAtomBullet(type, 0.0f, 0.0f, 0.0f, kuVidGame.getWindowWidth());
                        shooter.assignBullet(bullet);
                        gameObjects.assignShootersBullet(bullet);
                        break;
                    case "PowerUp":
                        bullet = gameObjectFactory.createPowerUpBullet(type, 0.0f, 0.0f, 0.0f, kuVidGame.getWindowWidth());
                        shooter.assignBullet(bullet);
                        gameObjects.assignShootersBullet(bullet);
                        break;
                }
                break;
        }
    }

    private void decodeLineIntoGameObjects(String line) {
        String[] lineElements = line.split(" ");
        switch (lineElements[0]) {
            case "Molecule":
                int moleculeTpe = Integer.parseInt(lineElements[1]);
                int atomCount = Integer.parseInt(lineElements[2]);
                float moleculeX = Float.parseFloat(lineElements[3]);
                float moleculeY = Float.parseFloat(lineElements[4]);
                int patternType = Integer.parseInt(lineElements[5]);
                String pattern;
                if (patternType == 0) {
                    pattern = "Straight";
                } else {
                    pattern = "ZigZag";
                }
                movableList.add(gameObjectFactory.createMolecule(moleculeTpe, atomCount, moleculeX, moleculeY, pattern));
                break;
            case "PowerUp":
                int powerUpType = Integer.parseInt(lineElements[1]);
                float powerUpX = Float.parseFloat(lineElements[2]);
                float powerUpY = Float.parseFloat(lineElements[3]);
                movableList.add(gameObjectFactory.createPowerUp(powerUpType, powerUpX, powerUpY));
                break;
            case "ReactionBlocker":
                int reactionBlockerType = Integer.parseInt(lineElements[1]);
                float reactionBlockerX = Float.parseFloat(lineElements[2]);
                float reactionBlockerY = Float.parseFloat(lineElements[3]);
                movableList.add(gameObjectFactory.createReactionBlocker(reactionBlockerType, reactionBlockerX, reactionBlockerY));
                break;
            case "Atom":
                int atomType = Integer.parseInt(lineElements[1]);
                float atomX = Float.parseFloat(lineElements[2]);
                float atomY = Float.parseFloat(lineElements[3]);
                float atomAngle = Float.parseFloat(lineElements[4]);
                shootableList.add(gameObjectFactory.createAtomBullet(atomType, atomX, atomY, atomAngle, kuVidGame.getWindowWidth(), false));
                break;
            case "PowerUpBullet":
                int powerUpBulletType = Integer.parseInt(lineElements[1]);
                float powerUpBulletX = Float.parseFloat(lineElements[2]);
                float powerUpBulletY = Float.parseFloat(lineElements[3]);
                float powerUpBulletAngle = Float.parseFloat(lineElements[4]);
                shootableList.add(gameObjectFactory.createPowerUpBullet(powerUpBulletType, powerUpBulletX, powerUpBulletY, powerUpBulletAngle, kuVidGame.getWindowWidth(), false));
                break;
        }
    }
}
