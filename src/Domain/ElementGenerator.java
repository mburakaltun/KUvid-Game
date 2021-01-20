package Domain;

import Domain.Factories.GameObjectFactory;
import Domain.Interfaces.IMovable;

import java.util.Arrays;
import java.util.Random;

public class ElementGenerator {
    Random random;
    GameObjectFactory gameObjectFactory;

    private int alphaMoleculeMode; //linear or other
    private int betaMoleculeMode; //linear or other
    private int alphaMoleculeLinearSpinMode;
    private int betaMoleculeLinearSpinMode;

    private int[] moleculeCount;
    private int[] powerUpCount;
    private int[] reactionBlockerCount;
    private int totalNumberOfElements;

    public ElementGenerator() {
        random = new Random();
        gameObjectFactory = GameObjectFactory.getInstance();
        totalNumberOfElements = 0;
    }

    public ElementGenerator(int[] moleculeCount, int[] powerUpCount, int[] reactionBlockerCount,
                            int alphaMoleculeLinearSpinMode, int betaMoleculeLinearSpinMode) {
        this.random = new Random();
        this.moleculeCount = moleculeCount;
        this.powerUpCount = powerUpCount;
        this.reactionBlockerCount = reactionBlockerCount;
        this.totalNumberOfElements = Arrays.stream(moleculeCount).sum()
                + Arrays.stream(powerUpCount).sum()
                + Arrays.stream(reactionBlockerCount).sum();

        this.gameObjectFactory = GameObjectFactory.getInstance();
    }

    public IMovable generateMovable(float windowHeight, float windowWidth) {
        int elementType = slotMachine();
        int colorType = random.nextInt(4);
        int patterType = random.nextInt(2);
        float x = (float) (random.nextDouble() * windowWidth);
        int atomCount = random.nextInt(6) + 1;

        String pattern;
        if (patterType == 0) {
            pattern = "ZigZag";
        } else {
            pattern = "Straight";
        }

        IMovable newElement;

        switch (elementType) {
            case 0:
                newElement = gameObjectFactory.createMolecule(0, atomCount, x, windowHeight, pattern);
                break;
            case 1:
                newElement = gameObjectFactory.createMolecule(1, atomCount, x, windowHeight, pattern);
                break;
            case 2:
                newElement = gameObjectFactory.createMolecule(2, atomCount, x, windowHeight, pattern);
                break;
            case 3:
                newElement = gameObjectFactory.createMolecule(3, atomCount, x, windowHeight, pattern);
                break;
            case 4:
                newElement = gameObjectFactory.createPowerUp(0, x, windowHeight);
                break;
            case 5:
                newElement = gameObjectFactory.createPowerUp(1, x, windowHeight);
                break;
            case 6:
                newElement = gameObjectFactory.createPowerUp(2, x, windowHeight);
                break;
            case 7:
                newElement = gameObjectFactory.createPowerUp(3, x, windowHeight);
                break;
            case 8:
                newElement = gameObjectFactory.createReactionBlocker(0, x, windowHeight);
                break;
            case 9:
                newElement = gameObjectFactory.createReactionBlocker(1, x, windowHeight);
                break;
            case 10:
                newElement = gameObjectFactory.createReactionBlocker(2, x, windowHeight);
                break;
            case 11:
                newElement = gameObjectFactory.createReactionBlocker(3, x, windowHeight);
                break;
            default:
                newElement = gameObjectFactory.createMolecule(colorType, atomCount, x, windowHeight, pattern);
        }
        return newElement;
    }

    private int slotMachine() {
        int randomNum = random.nextInt(totalNumberOfElements) + 1;
        int alphaMoleculeRange = moleculeCount[0];
        int betaMoleculeRange = alphaMoleculeRange + moleculeCount[1];
        int gammaMoleculeRange = betaMoleculeRange + moleculeCount[2];
        int sigmaMoleculeRange = gammaMoleculeRange + moleculeCount[3];

        int alphaPowerUpRange = sigmaMoleculeRange + powerUpCount[0];
        int betaPowerUpRange = alphaPowerUpRange + moleculeCount[1];
        int gammaPowerUpRange = betaPowerUpRange + moleculeCount[2];
        int sigmaPowerUpRange = gammaPowerUpRange + moleculeCount[3];

        int alphaReactionBlockerRange = sigmaPowerUpRange + powerUpCount[0];
        int betaReactionBlockerRange = alphaReactionBlockerRange + moleculeCount[1];
        int gammaReactionBlockerRange = betaReactionBlockerRange + moleculeCount[2];
        int sigmaReactionBlockerRange = gammaReactionBlockerRange + moleculeCount[3];

        int elementType = 0;
        if (randomNum <= alphaMoleculeRange) {
            elementType = 0;
            moleculeCount[0]--;
        } else if (randomNum <= betaMoleculeRange) {
            elementType = 1;
            moleculeCount[1]--;
        } else if (randomNum <= gammaMoleculeRange) {
            elementType = 2;
            moleculeCount[2]--;
        } else if (randomNum <= sigmaMoleculeRange) {
            elementType = 3;
            moleculeCount[3]--;
        } else if (randomNum <= alphaPowerUpRange) {
            elementType = 4;
            powerUpCount[0]--;
        } else if (randomNum <= betaPowerUpRange) {
            elementType = 5;
            powerUpCount[1]--;
        } else if (randomNum <= gammaPowerUpRange) {
            elementType = 6;
            powerUpCount[2]--;
        } else if (randomNum <= sigmaPowerUpRange) {
            elementType = 7;
            powerUpCount[3]--;
        } else if (randomNum <= alphaReactionBlockerRange) {
            elementType = 8;
            reactionBlockerCount[0]--;
        } else if (randomNum <= betaReactionBlockerRange) {
            elementType = 9;
            reactionBlockerCount[1]--;
        } else if (randomNum <= gammaReactionBlockerRange) {
            elementType = 10;
            reactionBlockerCount[2]--;
        } else if (randomNum <= sigmaReactionBlockerRange) {
            elementType = 11;
            reactionBlockerCount[3]--;
        }
        totalNumberOfElements--;
        return elementType;
    }

    public Random getRandom() {
        return random;
    }

    public GameObjectFactory getGameObjectFactory() {
        return gameObjectFactory;
    }

    public int getAlphaMoleculeMode() {
        return alphaMoleculeMode;
    }

    public int getBetaMoleculeMode() {
        return betaMoleculeMode;
    }

    public int getAlphaMoleculeLinearSpinMode() {
        return alphaMoleculeLinearSpinMode;
    }

    public int getBetaMoleculeLinearSpinMode() {
        return betaMoleculeLinearSpinMode;
    }

    public int[] getMoleculeCount() {
        return moleculeCount;
    }

    public int[] getPowerUpCount() {
        return powerUpCount;
    }

    public int[] getReactionBlockerCount() {
        return reactionBlockerCount;
    }

    public int getTotalNumberOfElements() {
        return totalNumberOfElements;
    }

    public void setRandom(Random random) {
        this.random = random;
    }

    public void setGameObjectFactory(GameObjectFactory gameObjectFactory) {
        this.gameObjectFactory = gameObjectFactory;
    }

    public void setAlphaMoleculeMode(int alphaMoleculeMode) {
        this.alphaMoleculeMode = alphaMoleculeMode;
    }

    public void setBetaMoleculeMode(int betaMoleculeMode) {
        this.betaMoleculeMode = betaMoleculeMode;
    }

    public void setAlphaMoleculeLinearSpinMode(int alphaMoleculeLinearSpinMode) {
        this.alphaMoleculeLinearSpinMode = alphaMoleculeLinearSpinMode;
    }

    public void setBetaMoleculeLinearSpinMode(int betaMoleculeLinearSpinMode) {
        this.betaMoleculeLinearSpinMode = betaMoleculeLinearSpinMode;
    }

    public void setMoleculeCount(int[] moleculeCount) {
        this.moleculeCount = moleculeCount;
    }

    public void setPowerUpCount(int[] powerUpCount) {
        this.powerUpCount = powerUpCount;
    }

    public void setReactionBlockerCount(int[] reactionBlockerCount) {
        this.reactionBlockerCount = reactionBlockerCount;
    }

    public void setTotalNumberOfElements(int totalNumberOfElements) {
        this.totalNumberOfElements = totalNumberOfElements;
    }
}
