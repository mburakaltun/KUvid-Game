package Domain;

import Domain.Factories.GameObjectFactory;
import Domain.Factories.IdFactory;
import Domain.Interfaces.IMovable;
import Domain.Interfaces.IShootable;
import Domain.MVC.Message;
import Domain.Primitives.Element;
import Domain.Primitives.Shootables.*;
import Domain.Primitives.Shooter;
import Domain.SaveLoad.GameSaver;
import Domain.SaveLoad.Loader;

import java.io.IOException;
import java.util.*;

public class KuVidGame {

    private float windowHeight;
    private float windowWidth;
    private float stepSize;
    private float effectiveRadius;
    private float l = 0;

    Random random = new Random();

    private GameObjectFactory gameObjectFactory;
    private IdFactory idFactory;
    private StatisticsWindow stats;
    private GameObjects gameObjects;
    private Shooter shooter;
    private Blender blender;
    private GameSaver saver;
    private Loader loader;
    private ElementGenerator elementGenerator;
    private static final String filePath = "src/SaveGame/savedGame.txt";

    boolean isRunning = false;

    private class ElementCreationTask extends TimerTask {
        @Override
        public void run() {
            if (isRunning) {
                IMovable newObj = elementGenerator.generateMovable(windowHeight, windowWidth);
                addObject(newObj);
            }
        }
    }

    private class ElemUpdateTask extends TimerTask {
        @Override
        public void run() {
            if (isRunning)
                updateGame();
        }
    }

    private class TimeUpdateTask extends TimerTask {
        @Override
        public void run() {
            if (isRunning)
                stats.decrementTime();
        }
    }

    public KuVidGame(ElementGenerator elementGenerator, StatisticsWindow statisticsWindow,
                     float windowHeight, float windowWidth) {
        this.windowHeight = windowHeight;
        this.windowWidth = windowWidth;
        this.stepSize = 2f;

        this.effectiveRadius = 50f;
        this.gameObjectFactory = GameObjectFactory.getInstance();
        this.elementGenerator = elementGenerator;
        this.idFactory = IdFactory.getInstance();
        this.stats = statisticsWindow;
        stats.setWindowHeight(windowHeight);
        this.shooter = new Shooter(windowWidth / 2, 5.0f);
        IShootable initialAtom = this.gameObjectFactory.createAtomBullet(0, windowWidth / 2, 10, 90.0f, windowWidth);

        this.gameObjects = new GameObjects(stats, this.windowHeight, this.windowWidth, initialAtom, shooter);
        this.shooter.assignBullet(initialAtom);
        blender = new Blender(stats);

        Timer updateTimer = new Timer();
        Timer timeUpdateTimer = new Timer();

        TimerTask updateTask = new ElemUpdateTask();
        TimerTask timeUpdateTask = new TimeUpdateTask();

        updateTimer.schedule(updateTask, 10, 33);
        timeUpdateTimer.schedule(timeUpdateTask, 0, 1000);
        saver = new GameSaver(this);
        loader = new Loader(this);
    }

    public float getStepSize() {
        return this.stepSize;
    }

    public void addObject(IMovable obj) {
        gameObjects.addMovable(obj);
    }

    public void updateGame() {
        gameObjects.update(this.stepSize, this.effectiveRadius);
    }

    public void shoot() {
        shooter.getBullet().setEfficiency(shooter.getBullet().getEfficiency());
        gameObjects.shootShootersBullet();
        if (shooter.getBullet().isAtom()) {
            if(Arrays.stream(stats.getAtomCounts()).sum() > 0) {
                stats.decrementAtomCount(shooter.getBullet().getColorType());
            }
        } else {
            if(Arrays.stream(stats.getPowerUpCounts()).sum() > 0) {
                stats.decrementPowerUpCount(((Element) shooter.getBullet()).getColorType());
            }
        }

        if (Arrays.stream(stats.getAtomCounts()).sum() > 0) {
            float[] shooterPos = this.shooter.getPosition();
            List<Integer> availableAtomTypes = new ArrayList<Integer>();
            if (stats.getAtomCount(0) > 0) {
                availableAtomTypes.add(0);
            }
            if (stats.getAtomCount(1) > 0) {
                availableAtomTypes.add(1);
            }
            if (stats.getAtomCount(2) > 0) {
                availableAtomTypes.add(2);
            }
            if (stats.getAtomCount(3) > 0) {
                availableAtomTypes.add(3);
            }
            int randomType = availableAtomTypes.get(random.nextInt(availableAtomTypes.size()));
            IShootable newAtom = this.gameObjectFactory.createAtomBullet(randomType, shooterPos[0], shooterPos[1], 90.0f, windowWidth);
            shooter.assignBullet(newAtom);
            gameObjects.assignShootersBullet(newAtom);
        }
    }

    public void rotateShooter(float deltaAngle) {
        shooter.rotate(deltaAngle);
    }

    public void slideShooter(float deltaX) {
        if (isRunning)
            shooter.move(deltaX);
    }

    public List<Message> getMessageList() {
        return gameObjects.getMessageList();
    }

    public float[] getShooterInfo() {
        float[] position = shooter.getPosition();
        float angle = shooter.getAngle();
        return new float[]{position[0], angle};
    }

    public void pauseGame() {
        isRunning = false;
    }

    public void resumeGame() {
        isRunning = true;
    }

    public void switchShooterAtom() {
        if (Arrays.stream(stats.getAtomCounts()).sum() > 0) {
            float[] shooterPos = this.shooter.getPosition();
            List<Integer> availableAtomTypes = new ArrayList<Integer>();
            if (stats.getAtomCount(0) > 0) {
                availableAtomTypes.add(0);
            }
            if (stats.getAtomCount(1) > 0) {
                availableAtomTypes.add(1);
            }
            if (stats.getAtomCount(2) > 0) {
                availableAtomTypes.add(2);
            }
            if (stats.getAtomCount(3) > 0) {
                availableAtomTypes.add(3);
            }
            int randomType = availableAtomTypes.get(random.nextInt(availableAtomTypes.size()));
            IShootable newAtom = this.gameObjectFactory.createAtomBullet(randomType, shooterPos[0], shooterPos[1], 90.0f, windowWidth);
            shooter.assignBullet(newAtom);
            gameObjects.assignShootersBullet(newAtom);
        }
    }

    public StatisticsWindow getStats() {
        return stats;
    }

    public void blendOperation(int blended, int broken) {
        blender.blendOperation(blended, broken);
    }

    public void breakOperation(int created, int destroyed) {
        blender.breakOperation(created, destroyed);
    }

    public GameObjects getGameObjects() {
        return gameObjects;
    }

    public StatisticsWindow getStatisticsWindow() {
        return stats;
    }

    public Shooter getShooter() {
        return shooter;
    }

    public void saveGame() {
        saver.save();
    }

    public String getFilePath() {
        return filePath;
    }

    public float getWindowWidth() {
        return windowWidth;
    }

    public void loadGame() throws IOException {
        loader.load();
    }

    public void setLRatio(float lRatio) {
        l = (windowHeight * lRatio) / 100;
    }

    public void addShieldToShootersAtom(int shieldType) {
        IShootable modifiedAtom;
        if (shooter.getBullet().isAtom() && stats.getShieldCount(shieldType) > 0) {
            if (shieldType == 0) {
                modifiedAtom = new Eta(shooter.getBullet());
            } else if (shieldType == 1) {
                modifiedAtom = new Theta(shooter.getBullet());
            } else if (shieldType == 2) {
                modifiedAtom = new Lota(shooter.getBullet());
            } else {
                modifiedAtom = new Zeta(shooter.getBullet());
            }
            shooter.assignBullet(modifiedAtom);
            gameObjects.assignShootersBullet(modifiedAtom);
            stats.decrementShieldCount(shieldType);
        }
    }

    public void assignPowerUp(int powerUpType) {
        PowerUpBullet powerUpBullet;
        if (stats.getPowerUpCount(powerUpType) > 0) {
            powerUpBullet = new PowerUpBullet(idFactory.createId(), powerUpType, 0, 0, 0, windowWidth);
            shooter.assignBullet(powerUpBullet);
            gameObjects.assignShootersBullet(powerUpBullet);
        }
    }

    public void setDifficulty(int difficulty) {
        int period;
        switch (difficulty) {
            case 1:
                period = 1500;
                break;
            case 2:
                period = 750;
                break;
            default:
                period = 3000;
                break;
        }
        Timer creationTimer = new Timer();
        TimerTask creationTask = new ElementCreationTask();
        creationTimer.schedule(creationTask, 30, period);
    }
}