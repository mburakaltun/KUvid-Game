package Domain.Factories;

import Domain.Interfaces.IMovable;
import Domain.Interfaces.IShootable;
import Domain.Primitives.Movables.Molecule;
import Domain.Primitives.Movables.PowerUp;
import Domain.Primitives.Movables.ReactionBlocker;
import Domain.Primitives.Shootables.AtomBullet;
import Domain.Primitives.Shootables.PowerUpBullet;

import java.util.Random;

public class GameObjectFactory {
    private static GameObjectFactory instance;
    private IdFactory idFactory;
    Random random = new Random();

    private GameObjectFactory() {
        idFactory = IdFactory.getInstance();
    }

    public static GameObjectFactory getInstance() {
        if (instance == null) {
            instance = new GameObjectFactory();
        }
        return instance;
    }

    public IMovable createMolecule(int type, int atomCount, float x, float y, String pattern) {
        return new Molecule(idFactory.createId(), type, x, y, atomCount, pattern);
    }

    public IMovable createReactionBlocker(int type, float x, float y) {
        return new ReactionBlocker(idFactory.createId(), type, x, y);
    }

    public IMovable createPowerUp(int type, float x, float y) {
        return new PowerUp(idFactory.createId(), type, x, y);
    }

    public IShootable createAtomBullet(int type, float x, float y, float shootingAngle, float windowWidth) {
        int[] alphaNeutronCounts = {7, 8, 9};
        int[] betaNeutronCounts = {15, 16, 17, 18, 21};
        int[] gammaNeutronCounts = {29, 32, 33};
        int[] sigmaNeutronCounts = {63, 64, 67};
        int neutronNumber = 0;
        int protonNumber = 0;
        switch (type) {
            case 0:
                neutronNumber = alphaNeutronCounts[random.nextInt(alphaNeutronCounts.length)];
                protonNumber = 8;
                break;
            case 1:
                neutronNumber = betaNeutronCounts[random.nextInt(betaNeutronCounts.length)];
                protonNumber = 16;
                break;
            case 2:
                neutronNumber = gammaNeutronCounts[random.nextInt(gammaNeutronCounts.length)];
                protonNumber = 32;
                break;
            case 3:
                neutronNumber = sigmaNeutronCounts[random.nextInt(sigmaNeutronCounts.length)];
                protonNumber = 64;
                break;
        }
        return new AtomBullet(idFactory.createId(), type, x, y, shootingAngle, windowWidth, protonNumber, neutronNumber);
    }

    public IShootable createAtomBullet(int type, float x, float y, float shootingAngle, float windowWidth, boolean movementLock) {
        int[] alphaNeutronCounts = {7, 8, 9};
        int[] betaNeutronCounts = {15, 16, 17, 18, 21};
        int[] gammaNeutronCounts = {29, 32, 33};
        int[] sigmaNeutronCounts = {63, 64, 67};
        int neutronNumber = 0;
        int protonNumber = 0;
        switch (type) {
            case 0:
                neutronNumber = alphaNeutronCounts[random.nextInt(alphaNeutronCounts.length)];
                protonNumber = 8;
                break;
            case 1:
                neutronNumber = betaNeutronCounts[random.nextInt(betaNeutronCounts.length)];
                protonNumber = 16;
                break;
            case 2:
                neutronNumber = gammaNeutronCounts[random.nextInt(gammaNeutronCounts.length)];
                protonNumber = 32;
                break;
            case 3:
                neutronNumber = sigmaNeutronCounts[random.nextInt(sigmaNeutronCounts.length)];
                protonNumber = 64;
                break;
        }
        return new AtomBullet(idFactory.createId(), type, x, y, shootingAngle, windowWidth, protonNumber, neutronNumber, movementLock);
    }

    public IShootable createPowerUpBullet(int type, float x, float y, float shootingAngle, float windowWidth) {
        return new PowerUpBullet(idFactory.createId(), type, x, y, shootingAngle, windowWidth);
    }

    public IShootable createPowerUpBullet(int type, float x, float y, float shootingAngle, float windowWidth, boolean movementLock) {
        return new PowerUpBullet(idFactory.createId(), type, x, y, shootingAngle, windowWidth, movementLock);
    }

    public IMovable createMovableObject(String objectName, int type, int atomCount, float x, float y, String pattern) {
        // REQUIRES: type 0 <= type <= 3, 2 <= atomCount <= 6
        // EFFECTS: Returns IMovable depending on objectName variable.
        switch (objectName) {
            case "Molecule":
                return createMolecule(type, atomCount, x, y, pattern);
            case "ReactionBlocker":
                return createReactionBlocker(type, x, y);
            case "PowerUp":
                return createPowerUp(type, x, y);
            default:
                return null;
        }
    }

    public IShootable createShootableObject(String objectName, int type, float x, float y, float shootingAngle, float windowWidth) {
        // REQUIRES: type 0 <= type <= 3
        // EFFECTS: Returns IShootable depending on objectName variable.
        switch (objectName) {
            case "AtomBullet":
                return createAtomBullet(type, x, y, shootingAngle, windowWidth);
            case "PowerUpBullet":
                return createPowerUpBullet(type, x, y, shootingAngle, windowWidth);
            default:
                return null;
        }
    }

}
