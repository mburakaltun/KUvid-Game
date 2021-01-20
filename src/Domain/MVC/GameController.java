package Domain.MVC;

import Domain.KuVidGame;
import Domain.Primitives.Shooter;

import java.io.FileNotFoundException;
import java.io.IOException;

public class GameController {
    KuVidGame kuVidGame;

    public GameController(KuVidGame kuVidGame) {
        this.kuVidGame = kuVidGame;
    }

    public void slideDomainShooter(int deltaX) {
        kuVidGame.slideShooter((float) deltaX);
    }

    public void rotateDomainShooter(int deltaAngle) {
        kuVidGame.rotateShooter((float) deltaAngle);
    }

    public void shootDomainShooter() {
        kuVidGame.shoot();
    }

    public void pauseDomainGame() {
        kuVidGame.pauseGame();
    }

    public void resumeDomainGame() {
        kuVidGame.resumeGame();
    }

    public void switchShooterAtom() {
        kuVidGame.switchShooterAtom();
    }

    public void blendOperation(int blended, int broken) {
        kuVidGame.blendOperation(blended, broken);
    }

    public void breakOperation(int created, int destroyed) {
        kuVidGame.breakOperation(created, destroyed);
    }

    public void saveGame() {
        kuVidGame.saveGame();
    }

    public void loadGame() throws IOException {
        kuVidGame.loadGame();
    }

    public void addShieldToShootersAtom(int shieldType) {
        kuVidGame.addShieldToShootersAtom(shieldType);
    }

    public void assignPowerUp(int powerUpType) {
        kuVidGame.assignPowerUp(powerUpType);
    }

}
